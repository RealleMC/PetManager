package com.reallemc;

import co.aikar.commands.PaperCommandManager;
import com.reallemc.pm.commands.MenuCommand;
import com.reallemc.pm.listeners.ClickPet;
import com.reallemc.util.DataWorks;
import com.reallemc.util.Settings;
import com.reallemc.util.SimpleConfig;
import fr.minuskube.inv.InventoryManager;
import fr.minuskube.inv.SmartInvsPlugin;
import org.bukkit.permissions.Permission;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.UUID;


public class PetManager extends JavaPlugin {

    private static PetManager instance;
    private SimpleConfig cfg;


    public static InventoryManager inventoryManager;
    public static String pre;
    //    public final Permission<ClickPet> clickPetPermission;
    public UUID ownerid, petid;
    public Permission perm;

    @Override
    public void onEnable() {
        instance = this;
        Common.log("Checking for config file...");
        cfg = new SimpleConfig("config.yml");
        pre = cfg.getString("pets.prefix");
        Common.log("Loading Settings...");
        Settings.init();
        Common.log("Registering commands...");
        registerCommand();
        Common.log("Setting up menus...");
        inventoryManager = SmartInvsPlugin.manager();
        Common.log("Setting up listeners...");
        getServer().getPluginManager().registerEvents(new ClickPet(), this);
        Common.log("enabled.");
        new DataWorks(instance);
    }

    private void registerCommand() {
        PaperCommandManager CM = new PaperCommandManager(instance);
        CM.registerCommand(new MenuCommand());
    }

    @Override
    public void onDisable() {
        Common.log("Cleaning up Memory...");
        pre = null;
        cfg = null;
        instance = null;
        Common.log("Disabled");
    }

    public static PetManager getInstance() {
        return instance;
    }

    public SimpleConfig getCfg() {
        return cfg;
    }

    public static String getPre() {
        return pre;
    }
}
