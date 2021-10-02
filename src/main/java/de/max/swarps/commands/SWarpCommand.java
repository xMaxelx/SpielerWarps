package de.max.swarps.commands;

import de.max.swarps.api.SpielerWarpAPI;
import de.max.swarps.utils.FileManager;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SWarpCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player p) {
            FileManager file = new FileManager("plugins/SpielerWarps/Warps/", p.getUniqueId() + ".yml");
            if (p.hasPermission("sw.use")) {
                if (args.length == 0) {
                    p.sendMessage("§8§m--------[§aSpielerWarp§8§m]--------");
                    p.sendMessage("");
                    p.sendMessage("§c/swarp create §8| §eErstelle dein Spieler-Warp oder wenn du schon ein hast setzte ihn um!");
                    p.sendMessage("§c/swarp delete §8| §eLösche dein Spieler-Warp");
                    p.sendMessage("");
                    p.sendMessage("§8§m--------[§aSpielerWarp§8§m]--------");
                }
                if (args.length == 1) {
                    if (args[0].equalsIgnoreCase("create")) {
                        SpielerWarpAPI.createWarp(p, p.getLocation());
                    } else if (args[0].equalsIgnoreCase("delete")) {
                        SpielerWarpAPI.deleteWarp(p);
                    } else if (args[0].equalsIgnoreCase("wartung")) {
                        if(file.exist()) {
                            if (!file.getBoolean("Wartung")) {
                                file.delete("Wartung");
                                file.set("Wartung", true);
                                file.save();
                                p.sendMessage(SpielerWarpAPI.getPrefix() +"§aWartung wurde für dein Spieler-Warp aktiviert!");
                            } else if (file.getBoolean("Wartung")) {
                                file.delete("Wartung");
                                file.set("Wartung", false);
                                file.save();
                                p.sendMessage(SpielerWarpAPI.getPrefix() +"§cWartung wurde für dein Spieler-Warp deaktiviert!");
                            }
                        } else if(!file.exist()) {
                            p.sendMessage("jibts net");
                        }
                    }
                }
            }
        }
        return false;
    }
}
