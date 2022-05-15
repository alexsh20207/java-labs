package rus.nsu.fit.oop.ms;

import javax.swing.*;
import java.io.IOException;
import java.util.Random;

import static rus.nsu.fit.oop.ms.Const.*;

public class GameModel {

    private static int xCells = LVL_EASY_X_CELLS;
    private static int yCells = LVL_EASY_Y_CELLS;
    private static int minesCount = LVL_EASY_MINES;
    private static int lvlNum = LVL_EASY_NUM;

    private static int allCells = xCells * yCells;
    private static int minesLeft = minesCount;
    private static int clickCounter = INIT_CLICK_COUNTER;
    private static int timeCounter = INIT_TIME_COUNTER;
    private static int score = INIT_SCORE;
    private static boolean playingCheck = true;
    private static boolean isSthOpen = false;
    private static boolean timerBool = true;
    private static boolean scoreWritten = false;

    private static Timer timer;
    private static int[] field;

    public void newGame() {
        clickCounter = INIT_CLICK_COUNTER;
        timeCounter = INIT_TIME_COUNTER;
        allCells = xCells * yCells;
        minesLeft = minesCount;
        scoreWritten = false;
        playingCheck = true;
        isSthOpen = false;

        field = new int[allCells];
        for (int i = FIRST_CELL_NUM; i < allCells; i++) {
            field[i] = COVER_FOR_CELL;
        }
        if (timerBool == true) {
            timer = new Timer(TIMER_DELAY, new TimeListener());
            timerBool = false;
        }
        timer.start();
    }

    void addFieldMineCount(int cell) {
        if (cell >= FIRST_CELL_NUM && cell < allCells) {
            if (field[cell] != COVERED_MINE_CELL) {
                field[cell]++;
            }
        }
    }

    void openNeighbor(int cell) throws IOException {
        if (cell >= FIRST_CELL_NUM && cell < allCells) {
            if (field[cell] >= COVER_FOR_CELL && field[cell] < MIN_COVER_FLAG_CELL) {
                setField(cell, field[cell] - COVER_FOR_CELL);
                if (field[cell] == EMPTY_CELL) {
                    find_empty_cells(cell);
                }
            }
        }
    }

    public boolean isItFlag(int cell) {
        if (cell >= FIRST_CELL_NUM && cell < allCells)
            return field[cell] > MIN_COVER_FLAG_CELL;
        return false;
    }

    public boolean canIOpenNeighbors(int cell) {
        int counter = 0;
        int cellX = cell % xCells;
        if (cellX > FIRST_CELL_NUM) {
            if (isItFlag(cell - 1 - xCells)) counter++;
            if (isItFlag(cell - 1)) counter++;
            if (isItFlag(cell - 1 + xCells)) counter++;
        }
        if (isItFlag(cell - xCells)) counter++;
        if (isItFlag(cell + xCells)) counter++;
        if (cellX < (xCells - 1)) {
            if (isItFlag(cell + 1 - xCells)) counter++;
            if (isItFlag(cell + 1 + xCells)) counter++;
            if (isItFlag(cell + 1)) counter++;
        }
        return counter >= field[cell];
    }

    public void putMines(int curXCell, int curYCell) {
        int mines = 0;
        Random random = new Random();
        while (mines < minesCount) {
            int pos = (int)(allCells * random.nextDouble());
            if (pos < allCells && field[pos] != COVERED_MINE_CELL
                    && ((pos % xCells != curXCell) || (pos / xCells != curYCell))) {
                int curX = pos % xCells;
                if (field[pos] < MIN_COVER_FLAG_CELL) {
                    field[pos] = COVERED_MINE_CELL;
                }
                else {
                    field[pos] += COVERED_MINE_CELL;
                }
                mines++;
                if (curX > FIRST_CELL_NUM) {
                    addFieldMineCount(pos - 1 - xCells);
                    addFieldMineCount(pos - 1);
                    addFieldMineCount(pos - 1 + xCells);
                }
                addFieldMineCount(pos - xCells);
                addFieldMineCount(pos + xCells);
                if (curX < (xCells - 1)) {
                    addFieldMineCount(pos + 1 - xCells);
                    addFieldMineCount(pos + 1 + xCells);
                    addFieldMineCount(pos + 1);
                }
            }
        }
    }

    public void find_empty_cells(int curCell) throws IOException {
        int current_col = curCell % xCells;
        if (current_col > FIRST_CELL_NUM) {
            openNeighbor(curCell - xCells - 1);
            openNeighbor(curCell - 1);
            openNeighbor(curCell + xCells - 1);
        }
        openNeighbor(curCell - xCells);
        openNeighbor(curCell + xCells);
        if (current_col < (xCells - 1)) {
            openNeighbor(curCell - xCells + 1);
            openNeighbor((curCell + xCells + 1));
            openNeighbor(curCell + 1);
        }
    }

    public boolean isItGG() throws IOException {
        for (int i = FIRST_CELL_NUM; i < allCells; ++i) {
            if (field[i] >= COVER_FOR_CELL && field[i] != COVERED_MINE_CELL + MIN_COVER_FLAG_CELL) {
                return false;
            }
        }
        score = (int)(((double)allCells / timeCounter) + ((double)allCells / clickCounter));
        if (scoreWritten == false) {
            ScoreFile sf = new ScoreFile();
            sf.write(score);
            scoreWritten = true;
        }
        return true;
    }
    public void gameOver() {
        for (int i = FIRST_CELL_NUM; i < allCells; ++i) {
            if (field[i] > MIN_COVER_FLAG_CELL && field[i] != MIN_COVER_FLAG_CELL + COVERED_MINE_CELL) {
                field[i] = WRONG_FLAG_CELL;
            }
            if (field[i] == COVERED_MINE_CELL) {
                field[i] = DRAW_MINE;
            }
        }
        playingCheck = false;
    }


    public int getXCells() {
        return xCells;
    }
    public int getYCells() {
        return yCells;
    }
    public int getMinesLeft() {
        return minesLeft;
    }
    public int getField(int i) {
        return field[i];
    }
    public boolean isPlaying() { return playingCheck;}
    public int getClickCounter() {return clickCounter;}
    public boolean getIsSthOpen() {return isSthOpen;}
    public int getTimeCounter() {
        return timeCounter;
    }
    public int getLvlNum() {
        return lvlNum;
    }
    public int getScore() {
        return score;
    }

    public void setMinesLeft(int minesLeft) { this.minesLeft = minesLeft;}
    public void setMinesCount(int minesCount) {
        this.minesCount = minesCount;
    }
    public void setLvlNum(int lvlNum) {
        this.lvlNum = lvlNum;
    }
    public void setTimeCounter(int val) {
        this.timeCounter = val;
    }
    public void setSize(int xCells, int yCells) {
        this.xCells = xCells;
        this.yCells = yCells;
    }
    public void setField(int i, int val) throws IOException {
        field[i] = val;
        if (field[i] == DRAW_MINE || isItGG() == true) {
            gameOver();
        }
    }
    public void addClick() { this.clickCounter++;}
    public void SthWasOpened() { isSthOpen = true;}
}
