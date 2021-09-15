package de.max.swarps;

import de.max.swarps.api.SpielerWarpAPI;
import de.max.swarps.commands.SWarpCommand;
import de.max.swarps.commands.SwarpsCommand;
import org.bukkit.plugin.java.JavaPlugin;

public final class Swarps extends JavaPlugin {

    Swarps plugin;

    @Override
    public void onEnable() {
        plugin = this;
        try {
            SpielerWarpAPI.createConfig();
        } catch (Exception e) {
            e.printStackTrace();
        }
        getCommand("swarp").setExecutor(new SWarpCommand());
        getCommand("swarps").setExecutor(new SwarpsCommand());
    }
}
