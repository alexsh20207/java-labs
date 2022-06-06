package rus.nsu.fit.oop.ms.gui;

import rus.nsu.fit.oop.ms.GameModel;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

import static rus.nsu.fit.oop.ms.gui.ConstGUI.*;

public class MouseController extends MouseAdapter {
    @Override
    public void mouseReleased(MouseEvent e) {
        int xCell = e.getX() / CELL_SIZE % GameModel.getXCells();
        int yCell = e.getY() / CELL_SIZE;
        int cell = yCell * GameModel.getXCells() + xCell;

        if (xCell < GameModel.getXCells() && yCell < GameModel.getYCells() && GameModel.isPlaying()) {
            if (e.getButton() == MouseEvent.BUTTON1 && !GameModel.isItFlag(cell)) {
                if (!GameModel.getIsSthOpen()) {
                        if (GameModel.getStepAmount() == 0) {
                            GameModel.newGame();
                        }
                        GameModel.putMines(xCell, yCell);
                        GameModel.SthWasOpened();
                    }
                if (GameModel.getField(cell) >= FIRST_GOOD_CLOSED_CELL_CODE) {
                    try {
                        GameModel.setField(cell, GameModel.getField(cell) - FIRST_GOOD_CLOSED_CELL_CODE);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
                if (GameModel.canIOpenNeighbors(cell)) {
                    try {
                        GameModel.findEmptyCells(cell);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            }
            if (e.getButton() == MouseEvent.BUTTON3) {
                if (xCell < GameModel.getXCells() && yCell < GameModel.getYCells()) {
                    int curCell = GameModel.getField(cell);
                    if (curCell >= FIRST_GOOD_CLOSED_CELL_CODE) {
                        if (curCell < FIRST_FLAG_CELL_CODE) {
                            try {
                                GameModel.setField(cell, curCell + FIRST_FLAG_CELL_CODE);
                            } catch (IOException ex) {
                                ex.printStackTrace();
                            }
                            GameModel.setMinesLeft(GameModel.getMinesLeft() - 1);
                        } else {
                            try {
                                GameModel.setField(cell, curCell - FIRST_FLAG_CELL_CODE);
                            } catch (IOException ex) {
                                ex.printStackTrace();
                            }
                            GameModel.setMinesLeft(GameModel.getMinesLeft() + 1);
                        }
                    }
                }
            }
            GameModel.addStep();
            try {
                updBoard();
                GameModel.isItGG();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    public void updBoard() throws IOException {
    }

}