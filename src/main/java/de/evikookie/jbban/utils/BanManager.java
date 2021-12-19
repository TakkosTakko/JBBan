package de.evikookie.jbban.utils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import de.evikookie.jbban.JBBan;
import org.bukkit.Bukkit;

public class BanManager {
    public static void ban(String uuid, String playername, String reason, long seconds) {
        long end = 0L;
        if (seconds == -1L) {
            end = -1L;
        } else {
            long current = System.currentTimeMillis();
            long millis = seconds * 1000L;
            end = current + millis;
        }
        MySQL.update("INSERT INTO BannedPlayers (SpielerName, UUID, Ende, Grund) VALUES ('" + playername + "', '" + uuid + "', '" + end + "', '" + reason + "')");
        if (Bukkit.getPlayer(playername) != null)
            Bukkit.getPlayer(playername).kickPlayer(String.valueOf(JBBan.error) + "\n\nwurdest Global gebannt!\n\nGrund:" + getReason(uuid) + "\n\n" + getRemainingTime(uuid));
    }

    public static void unban(String uuid) {
        MySQL.update("DELETE FROM BannedPlayers WHERE UUID='" + uuid + "'");
    }

    public static boolean isBanned(String uuid) {
        ResultSet rs = MySQL.getResult("SELECT Ende FROM BannedPlayers WHERE UUID='" + uuid + "'");
        try {
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static String getReason(String uuid) {
        ResultSet rs = MySQL.getResult("SELECT * FROM BannedPlayers WHERE UUID='" + uuid + "'");
        try {
            if (rs.next())
                return rs.getString("Grund");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static Long getEnd(String uuid) {
        ResultSet rs = MySQL.getResult("SELECT * FROM BannedPlayers WHERE UUID='" + uuid + "'");
        try {
            if (rs.next())
                return Long.valueOf(rs.getLong("Ende"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static List<String> getBannedPlayers() {
        List<String> list = new ArrayList<>();
        ResultSet rs = MySQL.getResult("SELECT * FROM BannedPlayers");
        try {
            while (rs.next())
                list.add(rs.getString("SpielerName"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static String getRemainingTime(String uuid) {
        long current = System.currentTimeMillis();
        long end = getEnd(uuid).longValue();
        if (end == -1L)
            return "";
        long millis = end - current;
        long seconds = 0L;
        long minutes = 0L;
        long hours = 0L;
        long days = 0L;
        long weeks = 0L;
        while (millis > 1000L) {
            millis -= 1000L;
            seconds++;
        }
        while (seconds > 60L) {
            seconds -= 60L;
            minutes++;
        }
        while (minutes > 60L) {
            minutes -= 60L;
            hours++;
        }
        while (hours > 24L) {
            hours -= 24L;
            days++;
        }
        while (days > 7L) {
            days -= 7L;
            weeks++;
        }
        return ""+ weeks + " Woche(n) " + days + " Tag(e) " + hours + " Stunde(n) " + minutes + " Minute(n) " + seconds + " Sekunde(n)";
    }

    public static String getUUID(String playername) {
        return Bukkit.getOfflinePlayer(playername).getUniqueId().toString();
    }
}
