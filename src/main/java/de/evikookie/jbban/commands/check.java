package de.evikookie.jbban.commands;

import de.evikookie.jbban.JBBan;
import de.evikookie.jbban.utils.BanManager;
import de.evikookie.jbban.utils.Config;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.List;

public class check implements CommandExecutor {
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender.hasPermission("jbban.check")) {
            if (args.length == 1) {
                if (args[0].equalsIgnoreCase("list")) {
                    List<String> list = BanManager.getBannedPlayers();
                    if (list.size() == 0) {
                        sender.sendMessage(Config.Prefix() + "Es sind keine Spieler gebannt!");
                    } else {
                        sender.sendMessage(Config.Prefix() + "---------- Ban Infos ----------");
                        for (String allBanned : BanManager.getBannedPlayers())
                            sender.sendMessage(Config.Prefix() + "§e"+ allBanned + " Grund: "+ BanManager.getReason(BanManager.getUUID(allBanned)) + "");
                        sender.sendMessage(Config.Prefix() + "---------- Ban Infos ----------");
                    }
                } else {
                    String playername = args[0];
                    sender.sendMessage(Config.Prefix() + "---------- Ban Infos ----------");
                            sender.sendMessage(Config.Prefix() + "Name: "+ playername);
                                    sender.sendMessage(Config.Prefix() + "UUID: "+ BanManager.getUUID(playername));
                                            sender.sendMessage(Config.Prefix() + "Gebannt: " + (BanManager.isBanned(BanManager.getUUID(playername)) ? "Ja": "Nein"));
                    if (BanManager.isBanned(BanManager.getUUID(playername))) {
                        sender.sendMessage(Config.Prefix() + "Grund: "+ BanManager.getReason(BanManager.getUUID(playername)));
                                sender.sendMessage(Config.Prefix() + "Zeit: " + BanManager.getRemainingTime(BanManager.getUUID(playername)));
                    }
                    sender.sendMessage(Config.Prefix() + "---------- Ban Infos ----------");
                }
            } else {
                        sender.sendMessage(Config.Prefix() + "Bitte benutze die Syntax: /check (list) <Spieler>");
            }
        } else {
                    sender.sendMessage(Config.Prefix() + Config.NoRechte());
        }
        return false;
    }

}

