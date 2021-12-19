package de.evikookie.jbban.listener;
import de.evikookie.jbban.utils.BanManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class Join implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        if (BanManager.isBanned(BanManager.getUUID(p.getDisplayName())))
            BanManager.unban(BanManager.getUUID(p.getDisplayName()));
    }
}