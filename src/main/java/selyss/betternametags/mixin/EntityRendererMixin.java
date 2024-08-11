package selyss.betternametags.mixin;

import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.text.Text;
import org.joml.Matrix4f;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import net.minecraft.client.render.entity.EntityRenderer;
import org.spongepowered.asm.mixin.injection.Redirect;
import selyss.betternametags.manager.ConfigManager;

@Mixin(EntityRenderer.class)
public class EntityRendererMixin {
	@Redirect(method = "renderLabelIfPresent", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/font/TextRenderer;draw(Lnet/minecraft/text/Text;FFIZLorg/joml/Matrix4f;Lnet/minecraft/client/render/VertexConsumerProvider;Lnet/minecraft/client/font/TextRenderer$TextLayerType;II)I"))
	private int borderlessNameTags(TextRenderer textRenderer, Text text, float x, float y, int color, boolean shadow, Matrix4f matrix, VertexConsumerProvider vertexConsumers, TextRenderer.TextLayerType layerType, int backgroundColor, int light) {

		if (!ConfigManager.modVisualsEnabled) {
			return textRenderer.draw(text, x, y, color, shadow, matrix, vertexConsumers, layerType, backgroundColor, light);
		}

		// texttype shouldn't be hardcoded cause it makes stuff look weird and makes shadows cover parts of the name

		// bandaid fix for weird bg shadow bug (jk this doesnt really work if nametag color is changed at all)
		if (ConfigManager.bgARGB == 0x3F000000) {
			return textRenderer.draw(text, x, y, ConfigManager.textARGB, ConfigManager.shadowEnabled, matrix, vertexConsumers, layerType, backgroundColor, light);
		}
		return textRenderer.draw(text, x, y, ConfigManager.textARGB, ConfigManager.shadowEnabled, matrix, vertexConsumers, layerType, ConfigManager.bgARGB, light);
	}
}
