package de.evikookie.jbban;

import de.evikookie.jbban.commands.*;
import de.evikookie.jbban.listener.Join;
import de.evikookie.jbban.listener.Login;
import de.evikookie.jbban.utils.Config;
import de.evikookie.jbban.utils.FM;
import de.evikookie.jbban.utils.MySQL;
import de.evikookie.jbban.utils.SetupMySQL;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

public final class JBBan extends JavaPlugin {

    public static boolean save = false;

    public static boolean autoLock = true;

    public static JBBan instance;

    public static Plugin plugin;

    public void onEnable() {
        plugin = this;
        instance = this;
        registerEvents();
        registerCommands();
        SetupMySQL.initMySQL();
        Config.initConfig();
        MySQL.connect();
        MySQL.createTable();




        Bukkit.getConsoleSender().sendMessage(Config.Prefix() + "Enabled!");
    }


    public void registerCommands() {
        getCommand("ban").setExecutor((CommandExecutor) new Ban());
        getCommand("check").setExecutor((CommandExecutor) new check());
        getCommand("tempban").setExecutor((CommandExecutor) new Tempban());
        getCommand("unban").setExecutor((CommandExecutor) new UnBan());
        getCommand("update").setExecutor((CommandExecutor) new Update());

    }

    public void onDisable() {
        MySQL.close();
    }

    public static void saveYourConfig(FileConfiguration cfg, File file) {
        try {
            cfg.save(file);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    private void registerEvents() {
        getServer().getPluginManager().registerEvents((Listener)new Login(), (Plugin)this);
        getServer().getPluginManager().registerEvents((Listener)new Join(), (Plugin)this);
    }




    public static JBBan getInstance() {
        return instance;
    }
    public Plugin getPlugin() {
        return plugin;
    }
}
