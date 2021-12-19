package de.evikookie.jbban.utils;

import de.evikookie.jbban.JBBan;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class SetupMySQL {
    public static File file = new File("plugins/JBBan/setup/", "mysql.yml");

    public static FileConfiguration cfg = YamlConfiguration.loadConfiguration(file);


    public static void initMySQL() {

        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (file.length() == 0) {
            cfg.set("username", "username");;
            cfg.set("password", "password");
            cfg.set("database", "database");
            cfg.set("host", "host");
            cfg.set("port", "3306");

            cfg.options().copyDefaults(true);

            JBBan.saveYourConfig(cfg, file);
        }
    }

    public static String username () {
        return cfg.getString("username");
    }
    public static String password () {
        return cfg.getString("password");
    }


    public static String database () {
        return cfg.getString("database");
    }

    public static String host () {
        return cfg.getString("host");
    }

    public static String port () {
        return cfg.getString("3306");
    }


}
