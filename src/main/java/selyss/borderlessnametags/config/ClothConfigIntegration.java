package selyss.borderlessnametags.config;

import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import me.shedaniel.clothconfig2.impl.builders.SubCategoryBuilder;
import me.shedaniel.math.Color;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;
import selyss.borderlessnametags.manager.ConfigManager;

import java.io.ObjectInputFilter;

public class ClothConfigIntegration {
    protected static Screen getConfigScreen(Screen parent) {
        ConfigBuilder builder = ConfigBuilder.create()
                .setParentScreen(MinecraftClient.getInstance().currentScreen)
                .setTitle(Text.literal("Borderless Name Tags Config"));

        ConfigCategory generalCategory = builder.getOrCreateCategory(Text.literal("General"));
        ConfigCategory visualsCategory = builder.getOrCreateCategory(Text.literal("Visuals"));
        ConfigEntryBuilder entryBuilder = builder.entryBuilder();

        generalCategory.addEntry(entryBuilder.startBooleanToggle(Text.literal("BorderlessNameTags Mod Enabled"), ConfigManager.modEnabled)
                .setDefaultValue(ConfigManager.modEnabled)
                .setTooltip(Text.literal("Should enable borderless name tags"))
                .setSaveConsumer(newValue -> {
                    ConfigManager.modEnabled = newValue;
                })
                .build());

        visualsCategory.addEntry(entryBuilder.startBooleanToggle(Text.literal("Text Shadow"), ConfigManager.shadowEnabled)
        .setDefaultValue(ConfigManager.shadowEnabled)
                .setTooltip(Text.literal("Should enable shadow for text"))
                .setSaveConsumer(newValue -> {
                    ConfigManager.shadowEnabled = newValue;
                })
                .build());

        SubCategoryBuilder textColorSub = entryBuilder.startSubCategory(Text.literal("Text Color"));

        textColorSub.add(entryBuilder.startIntSlider(Text.literal("Text Red"), ConfigManager.textRed, 0, 255)
                .setDefaultValue(ConfigManager.textRed)
                .setTooltip(Text.literal(""))
                .setSaveConsumer(newValue -> {
                    ConfigManager.textRed = newValue;
                })
                .build());

        textColorSub.add(entryBuilder.startIntSlider(Text.literal("Text Green"), ConfigManager.textGreen, 0, 255)
                .setDefaultValue(ConfigManager.textGreen)
                .setTooltip(Text.literal(""))
                .setSaveConsumer(newValue -> {
                    ConfigManager.textGreen = newValue;
                })
                .build());

        textColorSub.add(entryBuilder.startIntSlider(Text.literal("Text Blue"), ConfigManager.textBlue, 0, 255)
                .setDefaultValue(ConfigManager.textBlue)
                .setTooltip(Text.literal(""))
                .setSaveConsumer(newValue -> {
                    ConfigManager.textBlue = newValue;
                })
                .build());

        visualsCategory.addEntry(textColorSub.setExpanded(true).build());
        builder.setSavingRunnable(ConfigManager::save);
        return builder.build();
    }

}