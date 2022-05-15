package rus.nsu.fit.oop.ms;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TimeListener implements ActionListener {
    static private final int SECOND = 1;

    GameModel gameModel = new GameModel();

    public void actionPerformed(ActionEvent e) {
        if (gameModel.getIsSthOpen() == true && gameModel.isPlaying() == true) {
            gameModel.setTimeCounter(gameModel.getTimeCounter() + SECOND);
        }
    }
}