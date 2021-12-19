package de.evikookie.jbban.commands;

import de.evikookie.jbban.JBBan;
import de.evikookie.jbban.utils.BanManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class UnBan implements CommandExecutor {
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender.hasPermission("jbban.unban")) {
            if (args.length == 1) {
                String playername = args[0];
                if (!BanManager.isBanned(BanManager.getUUID(playername))) {
                    sender.sendMessage(String.valueOf(JBBan.error) + "Konnte Befehl nicht erfolgreich ausf");
                            sender.sendMessage(String.valueOf(JBBan.info) + "Dieser Spieler ist nicht gebannt!");
                } else {
                    BanManager.unban(BanManager.getUUID(playername));
                    sender.sendMessage(String.valueOf(JBBan.info) + "Du hast den Spieler" + playername + ""+ BanManager.getUUID(playername) + "entbannt!");
                }
            } else {
                sender.sendMessage(String.valueOf(JBBan.error) + "Konnte Befehl nicht erfolgreich ausf");
                        sender.sendMessage(String.valueOf(JBBan.info) + "Bitte benutze die Syntax: /unban <Spieler>");
            }
        } else {
            sender.sendMessage(String.valueOf(JBBan.error) + "Konnte Befehl nicht erfolgreich ausf");
                    sender.sendMessage(String.valueOf(JBBan.info) + "Du besitzt nicht die benRechte!");
        }
        return false;
    }
}
