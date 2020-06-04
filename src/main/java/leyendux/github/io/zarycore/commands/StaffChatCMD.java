package leyendux.github.io.zarycore.commands;

import leyendux.github.io.zarycore.util.MethodUtils;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class StaffChatCMD extends Command {

    public StaffChatCMD() {
        super("staffchat", "zarycore.trial", "schat", "staff", "st");
    }

    @Override
    public void execute(CommandSender commandSender, String[] args) {
        if(commandSender instanceof ProxiedPlayer) {
            ProxiedPlayer player = (ProxiedPlayer) commandSender;
            if(args.length < 1) {
                if(MethodUtils.isStaffChatToggled(player)) {
                    MethodUtils.setStaffChat(false, player);
                    player.sendMessage(new TextComponent("§cYour staff chat has been untoggled!"));
                } else {
                    MethodUtils.setStaffChat(true, player);
                    player.sendMessage(new TextComponent("§aYour staff chat has been toggled!"));
                }
            } else {
                String msg = String.valueOf(MethodUtils.setTogether(0, args));
                MethodUtils.sendStaffChat(player, msg);
            }
        } else {
            commandSender.sendMessage(new TextComponent("§cUnknown command."));
        }
    }
}
