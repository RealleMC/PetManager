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

    public DataWorks(PetManager plugin) {
        if (PetManager.getToml().getBoolean("sql.use"))
            setupSQL();
        else setupSQLite();
        init();
    }

    private void setupSQL() {
        int port = PetManager.getToml().getInt("sql.port");
        String host = PetManager.getToml().getString("sql.host")+":"+port;
        String user = PetManager.getToml().getString("sql.user");
        String password = PetManager.getToml().getString("sql.password");
        String database = PetManager.getToml().getString("sql.database");

        DatabaseOptions options = DatabaseOptions.builder().mysql(user, password, database, host).build();
        Database db = PooledDatabaseOptions.builder().options(options).createHikariDatabase();
        DB.setGlobalDatabase(db);
    }

    private void setupSQLite() {
        DatabaseOptions options = DatabaseOptions.builder().sqlite(PetManager.getInstance().getDataFolder() + File.separator + "pets.db").build();
        Database db = PooledDatabaseOptions.builder().options(options).createHikariDatabase();
        DB.setGlobalDatabase(db);
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