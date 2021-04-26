package me.magnum.petmanager.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CatchUnknown;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.Default;
import com.reallemc.Common;
import me.magnum.petmanager.menus.SmartMain;
import me.magnum.petmanager.util.CheckSender;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static me.magnum.petmanager.PetManager.pre;

@CommandAlias("petman")
public class MenuCommand extends BaseCommand {

    public MenuCommand() {
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




