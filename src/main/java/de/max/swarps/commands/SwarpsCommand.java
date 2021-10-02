package de.max.swarps.commands;

import de.max.swarps.api.SpielerWarpAPI;
import de.max.swarps.utils.FileManager;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SwarpsCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player p) {
            if (args.length == 0) {
                p.sendMessage(SpielerWarpAPI.getPrefix() + "§eBitte Benutze: /swarps (SpielerName)");
            }
            if (args.length == 1) {
                OfflinePlayer t = Bukkit.getOfflinePlayer(args[0]);
                int usenumber = 0;
                FileManager file = new FileManager("plugins/SpielerWarps/Warps/", t.getUniqueId() + ".yml");
                if (file.exist()) {
                    if (file.getBoolean("Wartung") == false) {
                        p.teleport(file.getLocation(t.getName()));
                        p.sendMessage(SpielerWarpAPI.getPrefix() + "§7Du hast dich zum Spieler-Warp von §e" + t.getName() + " §7teleportiert!");
                    }else if (file.getBoolean("Wartung") == true) {
                        p.sendMessage("§cne wartung!!!");
                        return true;
                    }
                } else {
                    p.sendMessage(SpielerWarpAPI.getPrefix() + "§cDieser Spieler hat kein Spieler-Warp!");
                }
            }
        }
        return false;
    }
}
