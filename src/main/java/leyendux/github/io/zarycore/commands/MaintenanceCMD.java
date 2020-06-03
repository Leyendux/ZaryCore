package leyendux.github.io.zarycore.commands;

import leyendux.github.io.zarycore.Main;
import leyendux.github.io.zarycore.util.FileUtil;
import leyendux.github.io.zarycore.util.ValuesUtil;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

import java.io.IOException;

public class MaintenanceCMD extends Command {

    public MaintenanceCMD() {
        super("maintenance", "zarycore.admin", "");
    }

    @Override
    public void execute(CommandSender commandSender, String[] strings) {
        if(ValuesUtil.MAINTENANCE.isEnabled()) {
            ValuesUtil.MAINTENANCE.setValue(false);
            Main.getInstance().getConfig().set("maintenance", ValuesUtil.MAINTENANCE.isEnabled());
            try {
                FileUtil.saveConfig();
            } catch (IOException e) {
                e.printStackTrace();
            }
            ProxyServer.getInstance().broadcast(new TextComponent("§aServer is now open to the public!"));
        } else {
            ValuesUtil.MAINTENANCE.setValue(true);
            Main.getInstance().getConfig().set("maintenance", ValuesUtil.MAINTENANCE.isEnabled());
            try {
                FileUtil.saveConfig();
            } catch (IOException e) {
                e.printStackTrace();
            }
            ProxyServer.getInstance().broadcast(new TextComponent("§cServer is now closed for developers!"));
            for(ProxiedPlayer player : ProxyServer.getInstance().getPlayers()) {
                if(!player.hasPermission("zarycore.admin")) {
                    player.disconnect(new TextComponent(ValuesUtil.MAINTENANCE_KICKMSG.getMessage()));
                }
            }
        }
    }
}
