package de.evikookie.jbban.listener;
import de.evikookie.jbban.JBBan;
import de.evikookie.jbban.utils.BanManager;
import de.evikookie.jbban.utils.Config;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;

public class Login implements Listener {
    @EventHandler
    public void onLogin(PlayerLoginEvent e) {
        if (!JBBan.save) {
            if (JBBan.autoLock) {
                e.disallow(PlayerLoginEvent.Result.KICK_OTHER, Config.error() + "Die Verbindung zur Datenbank ist fehlgeschlagen! (JBBan)");
            } else {
                Player p = e.getPlayer();
                if (BanManager.isBanned(p.getUniqueId().toString())) {
                    long current = System.currentTimeMillis();
                    long end = BanManager.getEnd(p.getUniqueId().toString());
                    if (current < end || end == -1L) {
                        e.disallow(PlayerLoginEvent.Result.KICK_BANNED, Config.error() + "\n\nwurdest Global gebannt!\n\nGrund:" + BanManager.getReason(p.getUniqueId().toString()) + "\n\n"+ BanManager.getRemainingTime(p.getUniqueId().toString()));
                    } else {
                        e.allow();
                    }
                } else {
                    e.allow();
                }
            }
        } else {
            Player p = e.getPlayer();
            if (BanManager.isBanned(p.getUniqueId().toString())) {
                long current = System.currentTimeMillis();
                long end = BanManager.getEnd(p.getUniqueId().toString());
                if (current < end || end == -1L) {
                    e.disallow(PlayerLoginEvent.Result.KICK_BANNED, Config.error() + "\n\nwurdest Global gebannt!\n\nGrund:" + BanManager.getReason(p.getUniqueId().toString()) + "\n\n"+ BanManager.getRemainingTime(p.getUniqueId().toString()));
                } else {
                    e.allow();
                }
            } else {
                e.allow();
            }
        }
    }
}
