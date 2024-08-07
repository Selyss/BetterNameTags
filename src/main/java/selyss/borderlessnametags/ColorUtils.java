package selyss.borderlessnametags;

import java.awt.Color;

public class ColorUtils {
    public static int colorToARGB(Color color) {
        int alpha = color.getAlpha();
        int red = color.getRed();
        int green = color.getGreen();
        int blue = color.getBlue();

        return (alpha << 24) | (red << 16) | (green << 8) | blue;
    }
}
