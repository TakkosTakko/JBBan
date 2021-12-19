package de.evikookie.jbban.commands;
import java.util.List;

import de.evikookie.jbban.JBBan;
import de.evikookie.jbban.utils.Ban;
import de.evikookie.jbban.utils.BanManager;
import de.evikookie.jbban.utils.Config;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class Tempban implements CommandExecutor {
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender.hasPermission("jb.ban")) {
            if (args.length >= 4) {
                String playername = args[0];
                if (!BanManager.isBanned(BanManager.getUUID(playername))) {
                    long value;
                    try {
                        value = Integer.parseInt(args[1]);
                    } catch (NumberFormatException ex) {
                        sender.sendMessage(Config.error() + "Konnte Befehl nicht erfolgreich ausf");
                                sender.sendMessage(Config.error() + "<Zahlenwert> muss eine Zahl sein!");
                        return true;
                    }
                    if (value >= 500L) {
                        sender.sendMessage(String.valueOf(Config.error()) + "Konnte Befehl nicht erfolgreich ausf");
                                sender.sendMessage(String.valueOf(Config.error()) + "Diese Zahl muss unter 500 liegen!");
                    }
                    String unitString = args[2];
                    String reason = "";
                    for (int i = 3; i < args.length; i++)
                        reason = String.valueOf(reason) + args[i] + " ";
                    List<String> unitList = Ban.getUnitsAsString();
                    if (unitList.contains(unitString.toLowerCase())) {
                        Ban unit = Ban.getUnit(unitString);
                        long seconds = value * unit.getToSecond();
                        BanManager.ban(BanManager.getUUID(playername), playername, reason, seconds);
                        sender.sendMessage(Config.Prefix() + "Du hast den Spieler + playername + "+ BanManager.getUUID(playername) + "f"+ value + " " + unit.getName() + " vom Server gebannt!");
                        return true;
                    }
                    sender.sendMessage(Config.Prefix() + "Konnte Befehl nicht erfolgreich ausf");
                            sender.sendMessage(Config.Prefix() + "Diese <Einheit> existiert nicht!");
                } else {
                    sender.sendMessage(Config.Prefix() + "Konnte Befehl nicht erfolgreich ausf");
                            sender.sendMessage(Config.Prefix() + "Spieler ist bereits gebannt!");
                }
            } else {
                sender.sendMessage(Config.Prefix() + "Konnte Befehl nicht erfolgreich ausf");
                        sender.sendMessage(Config.Prefix() + "Bitte benutze die Syntax: /tempban <Spieler> <Zahlenwert> <Einheit> <Grund>");
            }
        } else {
            sender.sendMessage(Config.Prefix() + "Konnte Befehl nicht erfolgreich ausf");
                    sender.sendMessage(Config.Prefix() + "Du besitzt nicht die benRechte!");
        }
        return false;
    }
}
