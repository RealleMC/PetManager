package me.magnum.petmanager;

import lombok.Getter;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Set;
import java.util.UUID;

public class PlayerCache {
    @Getter
    private static HashMap<Player, Set<UUID>> playerCache;
}
