package de.evikookie.jbban.utils;

import java.io.File;
import java.io.IOException;

import de.evikookie.jbban.JBBan;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class FM {


    public static File getMySQLFile() {
        return new File("plugins/JBBan/setup", "mysql.yml");
    }

    public static FileConfiguration getMySQLFileConfiguration() {
        return (FileConfiguration)YamlConfiguration.loadConfiguration(getMySQLFile());
    }



    public static void setStandartMySQL() {
        FileConfiguration cfg = getMySQLFileConfiguration();
        cfg.options().copyDefaults(true);
        cfg.options().header("Standart MySQL Port ist 3306! NUR wenn du ihn im MySQL Server gehast bitte auch hier \nUnd der Rest versteht sich hoffentlich von selbst!");
        cfg.addDefault("username", "username");
        cfg.addDefault("password", "password");
        cfg.addDefault("database", "datenbank");
        cfg.addDefault("host", "host");
        cfg.addDefault("port", "3306");
        try {
            cfg.save(getMySQLFile());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
