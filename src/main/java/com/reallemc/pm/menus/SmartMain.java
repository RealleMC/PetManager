package com.reallemc.pm.menus;

import com.reallemc.PetManager;
import fr.minuskube.inv.ClickableItem;
import fr.minuskube.inv.SmartInventory;
import fr.minuskube.inv.content.InventoryContents;
import fr.minuskube.inv.content.InventoryProvider;
import org.bukkit.entity.Player;
import org.mineacademy.fo.menu.model.ItemCreator;
import org.mineacademy.fo.remain.CompMaterial;

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

        content.fill(ClickableItem.empty(ItemCreator.builder()
                .material(CompMaterial.LIGHT_BLUE_STAINED_GLASS_PANE)
                .name("Empty Slot").build().makeMenuTool()));


        content.set(0, 8, ClickableItem.of(ItemCreator.builder()
                        .material(CompMaterial.BARRIER)
                        .name("Close Menu").build().makeMenuTool()
                , e -> player.closeInventory()));
    }

    @Override
    public void update(Player player, InventoryContents contents) {
    }


}
