package de.max.swarps.commands;

import de.max.swarps.api.SpielerWarpAPI;
import de.max.swarps.utils.FileManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SWarpCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player p) {
            if (p.hasPermission("sw.use")) {
                if (args.length == 1) {
                    if (args[0].equalsIgnoreCase("create")) {
                        SpielerWarpAPI.createWarp(p, p.getLocation());
                    } else if (args[0].equalsIgnoreCase("delete")) {
                        SpielerWarpAPI.deleteWarp(p);
                    } else if (args[0].equalsIgnoreCase("infos")) {
                        FileManager file = new FileManager("plugins/SpielerWarps/Warps/", p.getUniqueId() + ".yml");
                        p.sendMessage("§c" + p.getName() + "'s§7 Spieler-Warp Information");
                        p.sendMessage("");
                        p.sendMessage("§7Owner: " + file.getString("Owner"));
                        p.sendMessage("§7Erstellt am: " + file.getString("Create Date"));
                        p.sendMessage("");
                        p.sendMessage("§c" + p.getName() + "'s§7 Spieler-Warp Information");
                    }
                }
            }
        }
        return false;
    }
}
