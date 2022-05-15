package rus.nsu.fit.oop.ms;

import javax.swing.*;
import java.awt.*;


public class WindowAbout {
    static private final String PATH = "src/main/java/resources/about.png";
    static private final int xSize = 600;
    static private final int ySize = 438;

    JFrame frame;
    Image img;
    WindowAbout() {
        frame = new JFrame();
        frame.setSize(xSize, ySize);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        img = (new ImageIcon(PATH)).getImage();
        JPanel pane = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(img, 0, 0, null);
            }
        };
        frame.add(pane);
    }
}