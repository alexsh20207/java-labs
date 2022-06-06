package rus.nsu.fit.oop.ms.gui;

import javax.swing.*;
import java.awt.*;

import static rus.nsu.fit.oop.ms.gui.ConstGUI.*;

public class WindowAbout {
    static private final int xSize = 600;
    static private final int ySize = 438;

    JFrame frame;
    Image img;
    public WindowAbout() {
        frame = new JFrame();
        frame.setSize(xSize, ySize);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        img = (new ImageIcon(PATH1 + FILE_ABOUT)).getImage();
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