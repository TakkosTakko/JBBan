package de.evikookie.jbban.commands;

import de.evikookie.jbban.JBBan;
import de.evikookie.jbban.utils.BanManager;
import de.evikookie.jbban.utils.Config;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class UnBan implements CommandExecutor {
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender.hasPermission("jbban.unban")) {
            if (args.length == 1) {
                String playername = args[0];
                if (!BanManager.isBanned(BanManager.getUUID(playername))) {
                    sender.sendMessage(Config.error() + "Konnte Befehl nicht erfolgreich ausf");
                            sender.sendMessage(Config.error() + "Dieser Spieler ist nicht gebannt!");
                } else {
                    BanManager.unban(BanManager.getUUID(playername));
                    sender.sendMessage(Config.error() + "Du hast den Spieler" + playername + ""+ BanManager.getUUID(playername) + "entbannt!");
                }
            } else {
                sender.sendMessage(Config.error() + "Konnte Befehl nicht erfolgreich ausf");
                        sender.sendMessage(Config.error() + "Bitte benutze die Syntax: /unban <Spieler>");
            }
        } else {
            sender.sendMessage(Config.error() + "Konnte Befehl nicht erfolgreich ausf");
                    sender.sendMessage(Config.error() + "Du besitzt nicht die benRechte!");
        }
        return false;
    }
}
