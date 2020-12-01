package me.magnum.petmanager.listeners;

import de.leonhard.storage.Yaml;
import me.magnum.petmanager.PetManager;
import me.magnum.petmanager.PlayerCache;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.io.File;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class JoinQuitListener implements Listener {
    @EventHandler
    public void onJoin(PlayerJoinEvent joinEvent) {
        Player player = joinEvent.getPlayer();
        String playerId = player.getUniqueId().toString();

        Yaml playerFile = new Yaml(playerId, PetManager.getInstance().getDataFolder().toString() + File.separator + "playerData");
        Set<String> horses = playerFile.getOrDefault(playerId, null);
        Set<UUID> uuidSet = new HashSet<>();
        if (horses != null)
        {
            for (String horse : horses){
                uuidSet.add(UUID.fromString(horse));
            }
            PlayerCache.getPlayerCache().put(player, uuidSet);
        }
    }
    @EventHandler
    public void onQuit(PlayerQuitEvent quitEvent){
        Player player = quitEvent.getPlayer();
        if (PlayerCache.getPlayerCache().containsKey(player)){
            Yaml playerFile = new Yaml(quitEvent.getPlayer().getUniqueId().toString(), PetManager.getInstance().getDataFolder().toString() + File.separator + "playerData");
            playerFile.set(player.getUniqueId().toString(),PlayerCache.getPlayerCache().get(player));
            playerFile.write();
            PlayerCache.getPlayerCache().remove(player);
        }
    }
    // TODO Add listener to prevent mounting untamed horses

    // TODO Work on letting claimed horses to move around.


}
