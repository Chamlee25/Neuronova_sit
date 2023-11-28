package Mnist;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.swing.*;

public class MnistImageViewer {

    public static void main(String[] args) throws IOException {
        MnistLoader m = new MnistLoader(0);
        for(int i =0; i<98; i++){
            m.getNextMnist();
        }
        double[] imageData = m.getNextMnist().data;
        int enlargedSize = 40;
        BufferedImage enlargedImage = new BufferedImage(28 * enlargedSize, 28 * enlargedSize, BufferedImage.TYPE_INT_RGB);
        for (int y = 0; y < 28; y++) {
            for (int x = 0; x < 28; x++) {
                int pixelValue = (int)imageData[y * 28 + x];
                Color color = new Color(pixelValue, pixelValue, pixelValue);
                for (int i = 0; i < enlargedSize; i++) {
                    for (int j = 0; j < enlargedSize; j++) {
                        enlargedImage.setRGB(x * enlargedSize + i, y * enlargedSize + j, color.getRGB());
                    }
                }
            }
        }
        JFrame frame = new JFrame("MNIST Image Viewer");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(new JLabel(new ImageIcon(enlargedImage)));
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}