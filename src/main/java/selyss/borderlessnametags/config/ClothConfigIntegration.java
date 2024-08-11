package selyss.borderlessnametags.config;

import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;
import selyss.borderlessnametags.manager.ConfigManager;

public class ClothConfigIntegration {
    protected static Screen getConfigScreen(Screen parent) {
        ConfigBuilder builder = ConfigBuilder.create()
                .setParentScreen(MinecraftClient.getInstance().currentScreen)
                .setTitle(Text.literal("Borderless Name Tags Config"));

        ConfigCategory generalCategory = builder.getOrCreateCategory(Text.literal("General"));
        ConfigCategory visualsCategory = builder.getOrCreateCategory(Text.literal("Visuals"));
        ConfigEntryBuilder entryBuilder = builder.entryBuilder();

        generalCategory.addEntry(entryBuilder.startBooleanToggle(Text.literal("Mod Visuals Enabled"), ConfigManager.modVisualsEnabled)
                .setDefaultValue(true)
                .setTooltip(Text.literal("Should enable borderless name tags"))
                .setSaveConsumer(newValue -> {
                    ConfigManager.modVisualsEnabled = newValue;
                })
                .build());

        generalCategory.addEntry(entryBuilder.startBooleanToggle(Text.literal("Personal Name Tag Enabled"), ConfigManager.personalNameTagEnabled)
                .setDefaultValue(false)
                .setTooltip(Text.literal("Should enable personal name tags"))
                .setSaveConsumer(newValue -> {
                    ConfigManager.personalNameTagEnabled = newValue;
                })
                .build());

        visualsCategory.addEntry(entryBuilder.startBooleanToggle(Text.literal("Text Shadow"), ConfigManager.shadowEnabled)
        .setDefaultValue(false)
                .setTooltip(Text.literal("Should enable shadow for text"))
                .setSaveConsumer(newValue -> {
                    ConfigManager.shadowEnabled = newValue;
                })
                .build());

        visualsCategory.addEntry(entryBuilder.startAlphaColorField(Text.literal("Text Color"), ConfigManager.textARGB)
                .setDefaultValue(0xFFFFFFFF)
                .setTooltip(Text.literal("The color of the text"))
                .setSaveConsumer(newValue -> {
                    ConfigManager.textARGB = newValue;
                })
                .build());

        visualsCategory.addEntry(entryBuilder.startAlphaColorField(Text.literal("Background Color"), ConfigManager.bgARGB)
                .setDefaultValue(0x3F000000)
                .setTooltip(Text.literal("The color of the background"))
                .setSaveConsumer(newValue -> {
                    ConfigManager.bgARGB = newValue;
                })
                .build());


        builder.setSavingRunnable(ConfigManager::save);
        return builder.build();
    }

}