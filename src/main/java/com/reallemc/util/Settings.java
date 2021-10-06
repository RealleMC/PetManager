package com.reallemc.util;

import com.reallemc.PetManager;
import de.leonhard.storage.Toml;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class Settings {


    private final PetManager plugin;
    private final Toml config;

    private void onLoad() {
//         config.setHost(getString("sql.host"));
//         Config.setPort(getString("sql.port"));
//         Config.setUser(getString("sql.user"));
//         Config.setPassword(getString("sql.password"));
//         Config.setDatabase(getString("sql.database" + "?useSSL=false"));

    }

    public static void init() {
        new Settings(PetManager.getInstance(),PetManager.getToml()).onLoad();
    }

}
