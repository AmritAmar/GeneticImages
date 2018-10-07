package main.fitness;

import java.awt.image.BufferedImage;

public class ImageCompare {

    int stepSize;

    public ImageCompare(int stepSize) {
        this.stepSize = stepSize;
    }

    public double compare(BufferedImage b1, BufferedImage b2) {
        double error = 0;
        for (int x = 0; x < b1.getWidth(); x += stepSize) {
            for (int y = 0; y < b1.getHeight(); y += stepSize) {
                int b1RGB = b1.getRGB(x, y);
                int b2RGB = b2.getRGB(x, y);
                error += (double)Math.abs((b1RGB & 0xFF) - (b2RGB & 0xFF));
                error += (double)Math.abs(((b1RGB >> 8) & 0xFF) - ((b2RGB >> 8) & 0xFF));
                error += (double)Math.abs(((b1RGB >> 16) & 0xFF) - ((b2RGB >> 16) & 0xFF));
            }
        }
        return error;
    }

}
