package leyendux.github.io.zarycore.listeners;

import leyendux.github.io.zarycore.util.ValuesUtil;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.LoginEvent;
import net.md_5.bungee.api.event.PostLoginEvent;
import net.md_5.bungee.api.event.PreLoginEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class PostLoginListener implements Listener {

    @EventHandler
    public void onPostLoginEvent(PostLoginEvent event) {
        ProxiedPlayer player = event.getPlayer();
        if(ValuesUtil.MAINTENANCE.isEnabled()) {
            if(!player.hasPermission("zarycore.admin")) {
                player.disconnect(new TextComponent(ValuesUtil.MAINTENANCE_KICKMSG.getMessage()));
            } else {
                player.connect(ProxyServer.getInstance().getServerInfo("NA-HUB"));
            }
        }
    }
}
