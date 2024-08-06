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
        ConfigCategory colorCategory = builder.getOrCreateCategory(Text.literal("Colors"));
        ConfigEntryBuilder entryBuilder = builder.entryBuilder();

        generalCategory.addEntry(entryBuilder.startBooleanToggle(Text.literal("BorderlessNameTags Enabled"), ConfigManager.modEnabled)
                .setDefaultValue(ConfigManager.modEnabled)
                .setTooltip(Text.literal("Should enable borderless name tags"))
                .setSaveConsumer(newValue -> {
                    ConfigManager.modEnabled = newValue;
                })
                .build());

        SubCategoryBuilder textColorSub = entryBuilder.startSubCategory(Text.literal("Enabled Text Color"));

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

        colorCategory.addEntry(textColorSub.setExpanded(true).build());
        builder.setSavingRunnable(ConfigManager::save);
        return builder.build();
    }

}