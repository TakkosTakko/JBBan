package de.evikookie.jbban.utils;

import de.evikookie.jbban.JBBan;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class Config {
    public static File file = new File("plugins/JBBan/setup/", "config.yml");

    public static FileConfiguration cfg = YamlConfiguration.loadConfiguration(file);


    public static void initConfig() {

        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (file.length() == 0) {
            cfg.set("Prefix", "&7> &c&lTest &7< ");
            cfg.set("Permission0", "jbban.ban");
            cfg.set("Permission1", "jbban.ban");
            cfg.set("Permission2", "jbban.check");
            cfg.set("Permission3", "jbban.unban");
            cfg.set("Permission4", "jbban.update");
            cfg.set("AutoLock", Boolean.TRUE);
            cfg.set("NoRechte", "&cDu hast keine Zugriff auf diesen Befehl!");
            cfg.set("Nothere", "&cDies ist kein Spieler!");
            cfg.set("error", "&4Error!");
            cfg.set("info", "&bInfo!");
            cfg.options().copyDefaults(true);

            JBBan.saveYourConfig(cfg, file);
        }
    }

    public static String Prefix () {
        return cfg.getString("Prefix").replaceAll("&", "§");
    }
    public static boolean AutoLock () {
        return cfg.getBoolean("AutoLock");
    }


    public static String Permission0 () {
        return cfg.getString("Permission0").replaceAll("&", "§");
    }

    public static String error () {
        return cfg.getString("error").replaceAll("&", "§");
    }
    public static String info () {
        return cfg.getString("info").replaceAll("&", "§");
    }

    public static String Permission1 () {
        return cfg.getString("Permission1").replaceAll("&", "§");
    }

    public static String Permission2 () {
        return cfg.getString("Permission2").replaceAll("&", "§");
    }

    public static String Permission3 () {
        return cfg.getString("Permission3").replaceAll("&", "§");
    }

    public static String Permission4 () {
        return cfg.getString("Permission4").replaceAll("&", "§");
    }

    public static String NoRechte () {
        return cfg.getString("NoRechte").replaceAll("&", "§");
    }

    public static String Nothere () {
        return cfg.getString("Nothere").replaceAll("&", "§");
    }

}