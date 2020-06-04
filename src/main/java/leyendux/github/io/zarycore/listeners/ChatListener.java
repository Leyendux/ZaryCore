package leyendux.github.io.zarycore.listeners;

import leyendux.github.io.zarycore.util.MethodUtils;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.ChatEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class ChatListener implements Listener {

    @EventHandler
    public void onChatEvent(ChatEvent event) {
        ProxiedPlayer player = (ProxiedPlayer) event.getSender();
        if(event.isCommand()) {
            return;
        }
        if(MethodUtils.isStaffChatToggled(player)) {
            event.setCancelled(true);
            MethodUtils.sendStaffChat(player, event.getMessage());
        }
    }
}
