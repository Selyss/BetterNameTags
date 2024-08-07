package selyss.borderlessnametags.manager;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.fabricmc.loader.api.FabricLoader;

import java.awt.*;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;


public class ConfigManager {
    public static boolean modEnabled = true;
    public static boolean shadowEnabled = true;
    public static int textARGB = 0xFFFFFFFF;
//    public static int bgOpacity = 255 // highest in ARGB spec
    // TODO: add bgRed, bgGreen, bgBlue

    // credit to https://github.com/Walksy/ShieldStatus/blob/main/src/main/java/walksy/shieldstatus/manager/ConfigManager.java for config stuff
    private static final Path configDir = FabricLoader.getInstance().getConfigDir();
    private static final File configFile = configDir.resolve("borderlessnametags.json").toFile();
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    public static void save() {
        ConfigData configData = new ConfigData(modEnabled, shadowEnabled, textARGB);

        try (FileWriter writer = new FileWriter(configFile)) {
            GSON.toJson(configData, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void load() {
        if (configFile.exists()) {
            try (FileReader reader = new FileReader(configFile)) {
                ConfigData configData = GSON.fromJson(reader, ConfigData.class);
                modEnabled = configData.modEnabled;
                shadowEnabled = configData.shadowEnabled;
                textARGB = configData.textARGB;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static class ConfigData {
        boolean modEnabled;
        boolean shadowEnabled;

        int textARGB;
        ConfigData(boolean modEnabled, boolean shadowEnabled, int textARGB) {
            this.modEnabled = modEnabled;
            this.shadowEnabled = shadowEnabled;
            this.textARGB = textARGB;
        }
    }

}
