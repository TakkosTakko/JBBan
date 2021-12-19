package de.evikookie.jbban.commands;

import de.evikookie.jbban.JBBan;
import de.evikookie.jbban.utils.BanManager;
import de.evikookie.jbban.utils.Config;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class Ban implements CommandExecutor {
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender.hasPermission("jbban.ban")) {
            if (args.length >= 2) {
                String playername = args[0];
                String reason = "";
                if (!BanManager.isBanned(BanManager.getUUID(playername))) {
                    for (int i = 1; i < args.length; i++)
                        reason = String.valueOf(reason) + args[i] + " ";
                    BanManager.ban(BanManager.getUUID(playername), playername, reason, -1L);
                    sender.sendMessage(Config.Prefix() + "Du hast den Spieler" + playername +  BanManager.getUUID(playername) + "vom Server gebannt!");
                } else {
                            sender.sendMessage(Config.Prefix() + "Spieler ist bereits gebannt!");
                }
            } else {
                        sender.sendMessage(Config.Prefix() + "Bitte benutze die Syntax: /ban <Spieler> <Grund>");
            }
        } else {
                    sender.sendMessage(Config.Prefix() + Config.NoRechte());
        }
        return false;
    }

}

