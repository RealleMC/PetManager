package me.magnum.petmanager.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CatchUnknown;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.Default;
import co.aikar.commands.annotation.Subcommand;
import com.reallemc.Common;
import me.magnum.petmanager.CacheManager;
import me.magnum.petmanager.menus.SmartMain;
import me.magnum.petmanager.util.CheckSender;
import net.kyori.adventure.text.Component;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static me.magnum.petmanager.PetManager.pre;

@CommandAlias("petman")
public class MenuCommand extends BaseCommand {

    public MenuCommand() {
    }

    @Subcommand("showcache")
    public void onShow(CommandSender sender) {
        for (Object cachedPlayer : CacheManager.getPLAYER_CACHE().keySet()) {
            Player player = (Player) cachedPlayer;
            Component message = Component.text(player.getName());
            sender.sendMessage(message);
        }
    }

    @Default
    @CatchUnknown
    protected void onPMn(CommandSender sender) {
        if (CheckSender.isCommand(sender)) {
            return;
        }
        Common.tell((Player) sender, pre + "&aTest successful.");
        if (CheckSender.isPlayer(sender)) {
            Player p = (Player) sender;
            SmartMain.MAIN.open(p);
        }
    }
}




