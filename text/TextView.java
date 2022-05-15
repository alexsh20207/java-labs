package rus.nsu.fit.oop.ms.text;

import rus.nsu.fit.oop.ms.GameModel;
import java.io.IOException;
import static rus.nsu.fit.oop.ms.text.ConstText.*;


public class TextView {

    GameModel gameModel;
    public TextView() {
        gameModel = new GameModel();
    }
    public void print() throws IOException {
            int xCells = gameModel.getXCells();
            int yCells = gameModel.getYCells();
            for (int i = FIRST_YCELL; i < yCells; ++i) {
                for (int j = FIRST_XCELL; j < xCells; ++j) {
                    int cell = gameModel.getField(i * xCells + j);
                    String sym;
                    if (cell >= FIRST_GOOD_CLOSED_CELL_CODE && cell < FIRST_FLAG_CELL_CODE) {
                        sym = EMPTY_CELL_SYM;
                    }
                    else if (cell >= FIRST_FLAG_CELL_CODE && cell < WRONG_FLAG_CODE) {
                        sym = FLAG_SYM;
                    }
                    else if (cell == WRONG_FLAG_CODE) {
                        sym = WRONG_FLAG_SYM;
                    }
                    else if (cell == MINE_CELL_CODE) {
                        sym = MINE_SYM;
                    }
                    else sym = Integer.toString(cell);
                    System.out.print(sym + TAB_SYM);
                }
                System.out.println();
            }
            if (gameModel.isPlaying() == false) {
                if (gameModel.isItGG() == true) {
                    System.out.println(WIN_MSG + gameModel.getScore() + SPACE + gameModel.getTimeCounter());
                }
                else {
                    System.out.println(LOSE_MSG);
                }
            }
            else {
                System.out.println(gameModel.getMinesLeft() + MINE_LEFT_MSG);
            }
        }
}
