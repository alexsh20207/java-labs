package rus.nsu.fit.oop.ms.gui;

import rus.nsu.fit.oop.ms.GameModel;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

import static rus.nsu.fit.oop.ms.gui.ConstGUI.*;

public class MouseController extends MouseAdapter {
    @Override
    public void mouseReleased(MouseEvent e) {
        GameModel gameModel = new GameModel();
        int xCell = e.getX() / CELL_SIZE % gameModel.getXCells();
        int yCell = e.getY() / CELL_SIZE;
        int cell = yCell * gameModel.getXCells() + xCell;

        if (xCell < gameModel.getXCells() && yCell < gameModel.getYCells() && gameModel.isPlaying() == true) {
            if (e.getButton() == MouseEvent.BUTTON1 && gameModel.isItFlag(cell) == false) {
                if (gameModel.getIsSthOpen() == false) {
                        if (gameModel.getClickCounter() == 0) {
                            gameModel.newGame();
                        }
                        gameModel.putMines(xCell, yCell);
                        gameModel.SthWasOpened();
                    }
                if (gameModel.getField(cell) >= FIRST_GOOD_CLOSED_CELL_CODE) {
                    try {
                        gameModel.setField(cell, gameModel.getField(cell) - FIRST_GOOD_CLOSED_CELL_CODE);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
                if (gameModel.canIOpenNeighbors(cell)) {
                    try {
                        gameModel.find_empty_cells(cell);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            }
            if (e.getButton() == MouseEvent.BUTTON3) {
                if (xCell < gameModel.getXCells() && yCell < gameModel.getYCells()) {
                    int curCell = gameModel.getField(cell);
                    if (curCell >= FIRST_GOOD_CLOSED_CELL_CODE) {
                        if (curCell < FIRST_FLAG_CELL_CODE) {
                            try {
                                gameModel.setField(cell, curCell + FIRST_FLAG_CELL_CODE);
                            } catch (IOException ex) {
                                ex.printStackTrace();
                            }
                            gameModel.setMinesLeft(gameModel.getMinesLeft() - 1);
                        } else {
                            try {
                                gameModel.setField(cell, curCell - FIRST_FLAG_CELL_CODE);
                            } catch (IOException ex) {
                                ex.printStackTrace();
                            }
                            gameModel.setMinesLeft(gameModel.getMinesLeft() + 1);
                        }
                    }
                }
            }
            gameModel.addClick();
            try {
                updBoard();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    public void updBoard() throws IOException {
    }

}