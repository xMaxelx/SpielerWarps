package de.max.swarps.api;

import de.max.swarps.utils.FileManager;
import org.bukkit.Location;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class SpielerWarpAPI {

    public static void createConfig() {
        File file = new File("plugins/SpielerWarps/config.yml");
        YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);
        cfg.addDefault("Prefix", "§8▐§a SWarps §8» ");
        cfg.options().copyDefaults(true);
        try {
            cfg.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getPrefix() {
        FileManager manager = new FileManager("plugins/SpielerWarps/config.yml");
        return manager.getString("Prefix");
    }


    public static void createWarp(Player p, Location loc) {
        FileManager file = new FileManager("plugins/SpielerWarps/Warps/", p.getUniqueId() + ".yml");
        file.set("Owner", p.getName());
        file.set("Create Date", getDate());
        file.set(p.getName(), loc);
        p.sendMessage(getPrefix() + "§aDein Spieler-Warp wurde erstellt!");
        file.save();
    }

    public static void deleteWarp(Player p) {
        FileManager file = new FileManager("plugins/SpielerWarps/Warps/", p.getUniqueId() + ".yml");
        if (!file.exist()) {
            p.sendMessage(getPrefix() + "§cDu hast kein Spieler-Warp!");
            return;
        } else {
            file.delete();
            p.sendMessage(getPrefix() + "§cDu hast dein Spieler-Warp gelöscht!");
        }
    }

    public static String getDate() {
        Date currentDate = new Date();
        SimpleDateFormat s = new SimpleDateFormat("dd.MM.yyyy");
        return s.format(currentDate);
    }

    public static String getTime() {
        DateFormat dff = new SimpleDateFormat("HH:mm:ss");
        Date today = Calendar.getInstance().getTime();
        return dff.format(today);
    }
}
