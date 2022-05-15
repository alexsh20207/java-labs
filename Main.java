package rus.nsu.fit.oop.ms;

import java.awt.*;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            Minesweeper minesweeper = null;
            try {
                minesweeper = new Minesweeper();
            } catch (IOException e) {
                e.printStackTrace();
            }
            minesweeper.setVisible(true);
        });
    }
}
