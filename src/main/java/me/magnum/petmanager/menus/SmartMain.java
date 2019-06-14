package me.magnum.petmanager.menus;

import fr.minuskube.inv.ClickableItem;
import fr.minuskube.inv.SmartInventory;
import fr.minuskube.inv.content.InventoryContents;
import fr.minuskube.inv.content.InventoryProvider;
import me.magnum.lib.ItemBuilder;
import me.magnum.petmanager.PetManager;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class SmartMain implements InventoryProvider {
	
	public static final SmartInventory MAIN = SmartInventory.builder()
			.manager(PetManager.inventoryManager)
			.id("main")
			.provider(new SmartMain())
			.size(3, 9)
			.title("§aPet Manager")
			.build();
	
	@Override
	public void init (Player player, InventoryContents content) {
		content.fill(ClickableItem.empty(new ItemBuilder(Material.LIGHT_BLUE_STAINED_GLASS_PANE)
		.setName("§fEmpty Slot").toItemStack()));
		
		content.set(0, 8, ClickableItem.of(new ItemBuilder(Material.BARRIER)
		.setName("§cClose Menu").toItemStack(),
		                                   e -> player.closeInventory()));
	}
	@Override
	public void update(Player player, InventoryContents contents) {}
	
	
}
