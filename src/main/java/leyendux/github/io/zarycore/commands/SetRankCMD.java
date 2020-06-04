package leyendux.github.io.zarycore.commands;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import leyendux.github.io.zarycore.Main;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class SetRankCMD extends Command {

    public SetRankCMD() {
        super("setrank", "zarycore.manager", "");
    }

    @Override
    public void execute(CommandSender commandSender, String[] args) {
        if(commandSender instanceof ProxiedPlayer) {
            ProxiedPlayer sender = (ProxiedPlayer) commandSender;

            if(args.length < 2) {
                sender.sendMessage(new TextComponent(ChatColor.RED + "/setrank <player> <rank>"));
                return;
            }

            sendData(sender, args[0], args[1]);
            Main.getInstance().getLogger().info("Sending message to HUB [" + args[0] + ", " + args[1] + "]");
        } else {
            commandSender.sendMessage(new TextComponent(ChatColor.RED + "You can't use this command!"));
        }
    }

    private void sendData(ProxiedPlayer proxiedPlayer, String target, String rank) {
        ByteArrayDataOutput out = ByteStreams.newDataOutput();

        out.writeUTF("LuckPerms");
        out.writeUTF(target);
        out.writeUTF(rank);

        proxiedPlayer.getServer().getInfo().sendData("ZaryCore", out.toByteArray());
    }
}
