package com.reallemc.util;

import co.aikar.idb.DB;
import co.aikar.idb.Database;
import co.aikar.idb.DatabaseOptions;
import co.aikar.idb.PooledDatabaseOptions;
import com.google.common.reflect.TypeToken;
import com.reallemc.PetManager;

import java.io.File;
import java.lang.reflect.Type;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;

public class DataWorks {

//    private final PetManager plugin;

    public DataWorks(PetManager plugin) {
//        this.plugin = plugin;
        DatabaseOptions options = DatabaseOptions.builder().sqlite(plugin.getDataFolder() + File.separator + "pets.db").build();
        Database db = PooledDatabaseOptions.builder().options(options).createHikariDatabase();
        DB.setGlobalDatabase(db);
        init();
    }

    public void init() {
        try {
            DB.executeUpdate("CREATE TABLE pm_horse (" +
                    "id                   varchar(36) NOT NULL  PRIMARY KEY  ," +
                    "name                 varchar(36) NOT NULL," +
                    "type                 varchar(16) NOT NULL," +
                    "owner                varchar(36) NOT NULL," +
                    "trusted              varchar(3600) NOT NULL)");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static HashSet<UUID> getPetList(UUID uniqueId) {
        HashSet<UUID> set = new HashSet<>();
        try {
            List<String> result = DB.getFirstColumnResults("SELECT id where owner = ?", uniqueId.toString());
            set = toUUID(result);
            return set;
        } catch (SQLException e) {
            e.printStackTrace();
            PetManager.getInstance().getLogger().warning("Error getting user from db");
        }
        return set;
    }

    private static HashSet<UUID> toUUID(List<String> result) {
        Type type = new TypeToken<UUID>() {
        }.getType();
        HashSet<UUID> set = new HashSet<>();
        for (String string : result) {
            set.add(UUID.fromString(string));
        }
        return set;
    }
}