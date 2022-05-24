package rus.nsu.fit.oop.ms;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TimeListener implements ActionListener {
    static private final int SECOND = 1;

    public void actionPerformed(ActionEvent e) {
        if (GameModel.getIsSthOpen() == true && GameModel.isPlaying() == true) {
            GameModel.setTimeCounter(GameModel.getTimeCounter() + SECOND);
        }
    }
}