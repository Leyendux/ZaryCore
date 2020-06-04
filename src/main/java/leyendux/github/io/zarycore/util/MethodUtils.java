package leyendux.github.io.zarycore.util;

import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider;
import net.luckperms.api.cacheddata.CachedMetaData;
import net.luckperms.api.context.ImmutableContextSet;
import net.luckperms.api.model.group.Group;
import net.luckperms.api.model.user.User;
import net.luckperms.api.query.QueryOptions;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class MethodUtils {

    private static List<UUID> staffChat = new ArrayList<>();

    public static void setStaffChat(boolean b, ProxiedPlayer proxiedPlayer) {
        if (b) {
            staffChat.add(proxiedPlayer.getUniqueId());
        } else {
            staffChat.remove(proxiedPlayer.getUniqueId());
        }
    }

    public static StringBuilder setTogether(int n, String[] args) {
        StringBuilder msg = new StringBuilder();
        for (int x = n; x < args.length; x++) {
            msg.append(args[x] + " ");
        }
        return msg;
    }

    public static void sendStaffChat(ProxiedPlayer proxiedPlayer, String msg) {
        for(ProxiedPlayer player : ProxyServer.getInstance().getPlayers()) {
            if(player.hasPermission("zarycore.trial")) {
                player.sendMessage(new TextComponent("§8(§f§l" + proxiedPlayer.getServer().getInfo().getName() + "§8) §b§lStaff §8» " + getPrefix(proxiedPlayer) + " §b" + proxiedPlayer.getDisplayName() + ": §f" + msg));
            }
        }
    }

    public static boolean isStaffChatToggled(ProxiedPlayer proxiedPlayer) {
        return staffChat.contains(proxiedPlayer.getUniqueId());
    }

    public static String getPrefix(ProxiedPlayer proxiedPlayer) {
        LuckPerms api = LuckPermsProvider.get();

        QueryOptions queryOptions = api.getContextManager().getQueryOptions(proxiedPlayer);

        User user = api.getUserManager().getUser(proxiedPlayer.getUniqueId());

        CachedMetaData metaData = user.getCachedData().getMetaData(queryOptions);
        return metaData.getPrefix().replace('&', '§');
    }
}
