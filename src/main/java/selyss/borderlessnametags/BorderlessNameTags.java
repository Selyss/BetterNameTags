package selyss.borderlessnametags;

import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import selyss.borderlessnametags.manager.ConfigManager;

public class BorderlessNameTags implements ModInitializer {
    public static final Logger LOGGER = LoggerFactory.getLogger("borderless-name-tags");

	@Override
	public void onInitialize() {
		ConfigManager.load();
	}
}