package leyendux.github.io.zarycore.util;

import leyendux.github.io.zarycore.Main;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;

public class FileUtil {

    public static void loadConfig() throws IOException {
        Main.getInstance().setConfig(ConfigurationProvider.getProvider(YamlConfiguration.class).load(new File(Main.getInstance().getDataFolder(), "config.yml")));
    }

    public static void saveConfig() throws IOException {
        ConfigurationProvider.getProvider(YamlConfiguration.class).save(Main.getInstance().getConfig(), new File(Main.getInstance().getDataFolder(), "config.yml"));
    }

    public static void loadDefaultConfig() {
        if (!Main.getInstance().getDataFolder().exists())
            Main.getInstance().getDataFolder().mkdir();

        File file = new File(Main.getInstance().getDataFolder(), "config.yml");

        if (!file.exists()) {
            try (InputStream in = Main.getInstance().getResourceAsStream("config.yml")) {
                Files.copy(in, file.toPath());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
