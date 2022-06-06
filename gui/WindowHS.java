package rus.nsu.fit.oop.ms.gui;

import rus.nsu.fit.oop.ms.ScoreFile;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import static rus.nsu.fit.oop.ms.gui.ConstGUI.*;

public class WindowHS {
    static private final String HTML_START = "<html>";
    static private final int FONT_SIZE = 30;
    static private final String HTML_NEW_LINE = "<br/>";
    static private final String MSG_NOTHING = "There's nothing yet..";
    static private final String DASH_TEXT = " - ";
    static private final int xSize = 300;
    static private final int ySize = 450;
    static private final int HS_MAX = 10;
    static private final int COUNTER_INIT = 0;

    JFrame frame;
    String line;
    int counter;

    public WindowHS() throws IOException {
        ScoreFile scoreFile = new ScoreFile();
        scoreFile.sort();
        frame = new JFrame();
        frame.setSize(xSize, ySize);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        FileReader fr = new FileReader(FILE_HS);
        BufferedReader reader = new BufferedReader(fr);
        JLabel text = new JLabel(HTML_START);
        text.setFont(new Font(FONT_NAME, Font.PLAIN, FONT_SIZE));
        text.setOpaque(false);
        counter = COUNTER_INIT;
        while ((line = reader.readLine()) != null && counter != HS_MAX) {
            counter++;
            String str = counter + DASH_TEXT + line;
            text.setText(text.getText() + str + HTML_NEW_LINE);
        }
        if (counter == COUNTER_INIT) {
            text.setText(MSG_NOTHING);
        }
        frame.add(text, BorderLayout.BEFORE_FIRST_LINE);
    }
}