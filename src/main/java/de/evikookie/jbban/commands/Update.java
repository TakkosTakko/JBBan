package de.evikookie.jbban.commands;

import de.evikookie.jbban.JBBan;
import de.evikookie.jbban.utils.BanManager;
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
                    sender.sendMessage(String.valueOf(JBBan.info) + "Du hast den Bangrund von + playername + "+ BanManager.getUUID(playername) + "zu + reason + " );
                } else {
                    sender.sendMessage(String.valueOf(JBBan.error) + "Konnte Befehl nicht erfolgreich ausf");
                            sender.sendMessage(String.valueOf(JBBan.info) + "Spieler ist nicht gebannt!");
                }
            } else {
                sender.sendMessage(String.valueOf(JBBan.error) + "Konnte Befehl nicht erfolgreich ausf");
                        sender.sendMessage(String.valueOf(JBBan.info) + "Bitte benutze die Syntax: /update <Spieler> <NeuerGrund>");
            }
        } else {
            sender.sendMessage(String.valueOf(JBBan.error) + "Konnte Befehl nicht erfolgreich ausf");
                    sender.sendMessage(String.valueOf(JBBan.info) + "Du besitzt nicht die benRechte!");
        }
        return false;
    }
}
