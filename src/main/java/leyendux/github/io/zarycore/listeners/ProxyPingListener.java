package leyendux.github.io.zarycore.listeners;

import leyendux.github.io.zarycore.util.ValuesUtil;
import net.md_5.bungee.api.ServerPing;
import net.md_5.bungee.api.event.ProxyPingEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class ProxyPingListener implements Listener {

    @EventHandler
    public void onProxyPingEvent(ProxyPingEvent event) {
        ServerPing response = event.getResponse();
        ServerPing.Protocol version = response.getVersion();
        int userVersion = event.getConnection().getVersion();
        if(ValuesUtil.MAINTENANCE.isEnabled()) {
            version.setName("Working on it!");
            version.setProtocol(0);
        } else {
            version.setName("1.7x-1.8x");
            if(userVersion > 47) {
                version.setProtocol(47);
            } else if(userVersion < 47){
                version.setProtocol(5);
            }
        }
    }
}
