package me.magnum.petmanager;

import co.aikar.commands.BukkitCommandManager;
import fr.minuskube.inv.InventoryManager;
import fr.minuskube.inv.SmartInvsPlugin;
import me.magnum.petmanager.commands.MenuCommand;
import me.magnum.petmanager.listeners.ClickPet;
import me.magnum.petmanager.util.Settings;
import me.magnum.petmanager.util.SimpleConfig;
import org.bukkit.permissions.Permission;
import org.bukkit.plugin.java.JavaPlugin;
import org.mineacademy.fo.Common;

import java.util.UUID;


public class PetManager extends JavaPlugin {
	
	private static PetManager instance;
	public SimpleConfig cfg;
	
	public static InventoryManager inventoryManager;
	public static String pre;
	//    public final Permission<ClickPet> clickPetPermission;
	public UUID ownerid, petid;
	public Permission perm;
	
	@Override
	public void onEnable () {
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
	}
	
	private void registerCommand () {
		BukkitCommandManager CM = new BukkitCommandManager(instance);
		CM.registerCommand(new MenuCommand());
	}
	
	@Override
	public void onDisable () {
		Common.log("Cleaning up Memory...");
		pre=null;
		cfg=null;
		instance=null;
		Common.log("Disabled");
	}
	
	public static PetManager getInstance () { return instance; }
	public SimpleConfig getCfg () { return cfg; }
	public static String getPre () { return pre; }
}
