package sample;
//package com.bham.pij.assignments.edgedetector;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class EdgeDetector {

    public Image filterImage(Image image) {
        Color[][] pixels = applyFilter(applyGreyscale(getPixelDataExtended(image)), createFilter());
        WritableImage wimg = new WritableImage(pixels.length, pixels[0].length);
        PixelWriter pw = wimg.getPixelWriter();
        for (int i = 0; i < pixels.length; i++) {
            for (int j = 0; j < pixels[0].length; j++) {
                pw.setColor(i, j, pixels[i][j]);
            }
        }
        return wimg;
    }

    public Color[][] getPixelDataExtended(Image image) {
        Color[][] colors = new Color[(int) image.getWidth() + 2][(int) image.getHeight() + 2];
        for (int x = 0; x < colors.length; x++) {
            for (int y = 0; y < colors[0].length; y++) {
                if (x == 0 | x == colors.length - 1 | y == 0 | y == colors[0].length - 1) {
                    colors[x][y] = new Color(0, 0, 0, 1.0);
                } else {
                    colors[x][y] = image.getPixelReader().getColor(x - 1, y - 1);
                }
            }
        }
        return colors;
    }

    public Color[][] getPixelData(Image image) {
        Color[][] colors = new Color[(int) image.getWidth()][(int) image.getHeight()];
        for (int x = 0; x < image.getWidth(); x++) {
            for (int y = 0; y < image.getHeight(); y++) {
                colors[x][y] = image.getPixelReader().getColor(x, y);
            }
        }
        return colors;
    }

    public Color[][] applyGreyscale(Color[][] pixels) {
        for (int x = 0; x < pixels.length; x++) {
            for (int y = 0; y < pixels[0].length; y++) {
                double colour = (pixels[x][y].getRed() + pixels[x][y].getGreen() + pixels[x][y].getBlue()) / 3;
                pixels[x][y] = new Color(colour, colour, colour, 1.0);
            }
        }
        return pixels;
    }

    private void saveImage(Color[][] pixels, String filename) {
        WritableImage wimg = new WritableImage(pixels.length, pixels.length);
        PixelWriter pw = wimg.getPixelWriter();
        for (int i = 0; i < pixels.length; i++) {
            for (int j = 0; j < pixels.length; j++) {
                pw.setColor(i, j, pixels[i][j]);
            }
        }
        BufferedImage bImage = SwingFXUtils.fromFXImage(wimg, null);
        try {
            ImageIO.write(bImage, "png", new File(filename));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public float[][] createFilter() {
        return new float[][]{{-1, -1, -1}, {-1, 8, -1}, {-1, -1, -1}};
    }

    private Color[][] pixs;

    public Color[][] applyFilter(Color[][] pixels, float[][] filter) {
        pixs = new Color[pixels.length - 2][pixels[0].length - 2];
        for (int x = 1; x < pixels.length - 1; x++) {
            for (int y = 1; y < pixels[0].length - 1; y++) {
                Color[] one = {pixels[x - 1][y - 1], pixels[x][y - 1], pixels[x + 1][y - 1]};
                Color[] two = {pixels[x - 1][y], pixels[x][y], pixels[x + 1][y]};
                Color[] three = {pixels[x - 1][y + 1], pixels[x][y + 1], pixels[x + 1][y + 1]};
                Color[][] focus = {one, two, three};
                double red = 0;
                double green = 0;
                double blue = 0;
                int counter = 0;
                for (Color[] layer : focus) {
                    for (Color pixel : layer) {
                        int a = (int) (counter / 3.0);
                        int b = counter % 3;
                        red += filter[a][b] * pixel.getRed();
                        green += filter[a][b] * pixel.getGreen();
                        blue += filter[a][b] * pixel.getBlue();
                        counter += 1;
                    }
                }
                if (red > 1.0) {
                    red = 1.0;
                }
                if (green > 1.0) {
                    green = 1.0;
                }
                if (blue > 1.0) {
                    blue = 1.0;
                }
                if (red < 0.0) {
                    red = 0.0;
                }
                if (green < 0.0) {
                    green = 0.0;
                }
                if (blue < 0.0) {
                    blue = 0.0;
                }
                pixs[x - 1][y - 1] = new Color(red, green, blue, 1.0);
            }
        }
        return pixs;
    }

}
