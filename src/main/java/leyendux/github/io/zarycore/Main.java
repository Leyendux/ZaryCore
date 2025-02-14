package leyendux.github.io.zarycore;

import leyendux.github.io.zarycore.commands.MaintenanceCMD;
import leyendux.github.io.zarycore.commands.SetRankCMD;
import leyendux.github.io.zarycore.commands.StaffChatCMD;
import leyendux.github.io.zarycore.listeners.ChatListener;
import leyendux.github.io.zarycore.listeners.PostLoginListener;
import leyendux.github.io.zarycore.listeners.ProxyPingListener;
import leyendux.github.io.zarycore.util.FileUtil;
import leyendux.github.io.zarycore.util.ValuesUtil;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.config.Configuration;

import java.io.IOException;

public class Main extends Plugin {

    private static Main instance;

    private Configuration config;

    @Override
    public void onEnable() {
        super.onEnable();
        instance = this;

        FileUtil.loadDefaultConfig();
        try {
            FileUtil.loadConfig();
        } catch (IOException e) {
            e.printStackTrace();
        }
        ValuesUtil.MAINTENANCE.setValue(config.getBoolean("maintenance"));
        ValuesUtil.MAINTENANCE_KICKMSG.setMessage(config.getString("maintenance_message"));

        registerCommands();
        registerListeners();
        registerChannels();
    }

    @Override
    public void onDisable() {
        super.onDisable();
        instance = null;
    }

    public static Main getInstance() {
        return instance;
    }

    public Configuration getConfig() {
        return config;
    }

    public void setConfig(Configuration config) {
        this.config = config;
    }

    private void registerCommands() {
        getProxy().getPluginManager().registerCommand(this, new MaintenanceCMD());
        getProxy().getPluginManager().registerCommand(this, new StaffChatCMD());
        getProxy().getPluginManager().registerCommand(this, new SetRankCMD());
    }

    private void registerListeners() {
        getProxy().getPluginManager().registerListener(this, new ProxyPingListener());
        getProxy().getPluginManager().registerListener(this, new PostLoginListener());
        getProxy().getPluginManager().registerListener(this, new ChatListener());
    }

    private void registerChannels() {
        getProxy().registerChannel("ZaryCore");
    }
}
