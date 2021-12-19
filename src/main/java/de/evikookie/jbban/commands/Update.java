package de.evikookie.jbban.commands;

import de.evikookie.jbban.JBBan;
import de.evikookie.jbban.utils.BanManager;
import de.evikookie.jbban.utils.Config;
import de.evikookie.jbban.utils.MySQL;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class Update implements CommandExecutor {
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender.hasPermission("jbban.update")) {
            if (args.length >= 2) {
                String playername = args[0];
                String reason = "";
                if (BanManager.isBanned(BanManager.getUUID(playername))) {
                    for (int i = 1; i < args.length; i++)
                        reason = String.valueOf(reason) + args[i] + " ";
                    MySQL.update("UPDATE bannedplayers SET Grund='" + reason + "' WHERE UUID='" + BanManager.getUUID(playername) + "'");
                    sender.sendMessage(Config.error() + "Du hast den Bangrund von + playername + "+ BanManager.getUUID(playername) + "zu + reason + " );
                } else {
                    sender.sendMessage(Config.error() + "Konnte Befehl nicht erfolgreich ausf");
                            sender.sendMessage(Config.info() + "Spieler ist nicht gebannt!");
                }
            } else {
                sender.sendMessage(Config.error() + "Konnte Befehl nicht erfolgreich ausf");
                        sender.sendMessage(Config.info() + "Bitte benutze die Syntax: /update <Spieler> <NeuerGrund>");
            }
        } else {
            sender.sendMessage(Config.error() + "Konnte Befehl nicht erfolgreich ausf");
                    sender.sendMessage(Config.info() + "Du besitzt nicht die benRechte!");
        }
        return false;
    }
}
