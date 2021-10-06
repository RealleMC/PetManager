package com.reallemc.pm.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CatchUnknown;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.Default;
import com.reallemc.PetManager;
import com.reallemc.pm.menus.SmartMain;
import com.reallemc.util.CheckSender;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.mineacademy.fo.Common;

@CommandAlias("petman")
public class MenuCommand extends BaseCommand {

    public MenuCommand() {
    }

    /*@Subcommand("showcache")
    public void onShow(CommandSender sender) {
        for (Object cachedPlayer : CacheManager.getPLAYER_CACHE().keySet()) {
            Player player = (Player) cachedPlayer;
            Component message = Component.text(player.getName());
            sender.sendMessage(message);
        }
    }*/

    @Default
    @CatchUnknown
    protected void onPMn(CommandSender sender) {
        if (CheckSender.isCommand(sender)) {
            return;
        }
        Common.tell((Player) sender, PetManager.pre + "&aTest successful.");
        if (CheckSender.isPlayer(sender)) {
            Player p = (Player) sender;
            SmartMain.MAIN.open(p);
        }
    }
}




