package com.reallemc;

import co.aikar.commands.PaperCommandManager;
import com.reallemc.pm.commands.MenuCommand;
import com.reallemc.pm.listeners.ClickPet;
import com.reallemc.util.DataWorks;
import com.reallemc.util.Settings;
import de.leonhard.storage.Toml;
import fr.minuskube.inv.InventoryManager;
import fr.minuskube.inv.SmartInvsPlugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.mineacademy.fo.Common;


public class PetManager extends JavaPlugin {

    private static PetManager instance;
    private static Toml toml;
    public static InventoryManager inventoryManager;
    public static String pre;

    @Override
    public void onEnable() {
        instance = this;
        Common.log("Checking for config file...");
        toml = new Toml("config", getDataFolder().toString());
        pre = toml.getString("pets.prefix");
        Common.setLogPrefix(pre);
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
        toml = null;
        instance = null;
        Common.log("Disabled");
    }

    public static PetManager getInstance() {
        return instance;
    }

    public static Toml getToml() {
        return toml;
    }

    public static String getPre() {
        return pre;
    }
}
