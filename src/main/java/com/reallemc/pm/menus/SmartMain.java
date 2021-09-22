package com.reallemc.pm.menus;

import com.reallemc.ItemFactory;
import fr.minuskube.inv.ClickableItem;
import fr.minuskube.inv.SmartInventory;
import fr.minuskube.inv.content.InventoryContents;
import fr.minuskube.inv.content.InventoryProvider;
import com.reallemc.pm.PetManager;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class SmartMain implements InventoryProvider {

    public static final SmartInventory MAIN = SmartInventory.builder()
            .manager(PetManager.inventoryManager)
            .id("main")
            .provider(new SmartMain())
            .size(3, 9)
            .title("§aPet Manager")
            .build();

    @Override
    public void init(Player player, InventoryContents content) {
        content.fill(ClickableItem.empty(new ItemFactory(
                Material.LIGHT_BLUE_STAINED_GLASS_PANE)
                .setDisplayName("§fEmpty Slot")
                .build()));

        content.set(0, 8, ClickableItem.of(new ItemFactory(Material.BARRIER)
                        .setDisplayName("§cClose Menu").build(),
                e -> player.closeInventory()));
    }

    @Override
    public void update(Player player, InventoryContents contents) {
    }


}
