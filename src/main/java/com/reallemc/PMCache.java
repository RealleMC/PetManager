package com.reallemc;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.reallemc.util.DataWorks;
import lombok.Getter;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Entity;

import java.util.HashSet;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Getter
public class PMCache {

    private static LoadingCache<OfflinePlayer, HashSet<UUID>> petList;

    public PMCache() {
    init();
    }

    public void init() {
        petList = CacheBuilder.newBuilder()
                .expireAfterAccess(30, TimeUnit.MINUTES)
                .build(new CacheLoader<>() {

                    @Override
                    public HashSet<UUID> load(OfflinePlayer player) {
                        return DataWorks.getPetList(player.getUniqueId());
                    }
                });
    }
}

