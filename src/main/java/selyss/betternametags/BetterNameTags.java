package selyss.betternametags;

import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import selyss.betternametags.manager.ConfigManager;

public class BetterNameTags implements ModInitializer {
    public static final Logger LOGGER = LoggerFactory.getLogger("better-name-tags");

	@Override
	public void onInitialize() {
		ConfigManager.load();
	}
}