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
                    if(p.getLocale().equalsIgnoreCase("de_DE")) {
                        p.sendMessage("§8§m--------[§aSpielerWarp§8§m]--------");
                        p.sendMessage("");
                        p.sendMessage("§c/swarp create §8| §eErstelle dein Spieler-Warp oder wenn du schon ein hast setzte ihn um!");
                        p.sendMessage("§c/swarp delete §8| §eLösche dein Spieler-Warp");
                        p.sendMessage("");
                        p.sendMessage("§8§m--------[§aSpielerWarp§8§m]--------");
                    } else {
                        p.sendMessage("§8§m--------[§aPlayerWarp§8§m]--------");
                        p.sendMessage("");
                        p.sendMessage("§c/swarp create §8| §eCreate your player warp or if you already have one implement it!");
                        p.sendMessage("§c/swarp delete §8| §eDelete your player warp!");
                        p.sendMessage("");
                        p.sendMessage("§8§m--------[§aPlayerWarp§8§m]--------");
                    }
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
                                if(p.getLocale().equalsIgnoreCase("de_DE")) {
                                    p.sendMessage(SpielerWarpAPI.getPrefix() + "§aWartung wurde für dein Spieler-Warp aktiviert!");
                                } else {
                                    p.sendMessage(SpielerWarpAPI.getPrefix() + "§aMaintenance has been activated for your player warp!");
                                }
                            } else if (file.getBoolean("Wartung")) {
                                file.delete("Wartung");
                                file.set("Wartung", false);
                                file.save();
                                if(p.getLocale().equalsIgnoreCase("de_DE")) {
                                    p.sendMessage(SpielerWarpAPI.getPrefix() + "§cWartung wurde für dein Spieler-Warp deaktiviert!");
                                } else {
                                    p.sendMessage(SpielerWarpAPI.getPrefix() + "§cMaintenance has been disabled for your player warp!");
                                }
                            }
                        } else if(!file.exist()) {
                            if(p.getLocale().equalsIgnoreCase("de_DE")) {
                                p.sendMessage(SpielerWarpAPI.getPrefix() + "§cDu hast kein Spieler-Warp!");
                            } else {
                                p.sendMessage(SpielerWarpAPI.getPrefix() + "You have no player warp!");
                            }
                        }
                    }
                }
            } else {
                if (p.getLocale().equalsIgnoreCase("de_DE")) {
                    p.sendMessage(SpielerWarpAPI.getPrefix() + "§cDu brauchst den Rang Premium oder höher!");
                } else {
                    p.sendMessage(SpielerWarpAPI.getPrefix() + "§cYou need the rank Premium or higher!");
                }
            }
        }
        return false;
    }
}
