package rus.nsu.fit.oop.ms;

import rus.nsu.fit.oop.ms.text.TextView;

import javax.swing.*;
import java.io.IOException;
import java.util.Random;

import static rus.nsu.fit.oop.ms.Const.*;

public class GameModel {
    private static int xCells = LVL_EASY_X_CELLS;
    private static int yCells = LVL_EASY_Y_CELLS;
    private static int minesAmount = LVL_EASY_MINES;

    private static int allCells = xCells * yCells;
    private static int minesLeft = minesAmount;
    private static int stepAmount = INIT_STEP_AMOUNT;
    private static int timeCounter = INIT_TIME_COUNTER;
    private static int score = INIT_SCORE;
    private static boolean playingCheck = true;
    private static boolean isSthOpen = false;
    private static boolean timerBool = true;
    private static boolean scoreWritten = false;

    private static Timer timer;
    private static int[] field;

    public static void newGame() {
        stepAmount = INIT_STEP_AMOUNT;
        timeCounter = INIT_TIME_COUNTER;
        allCells = xCells * yCells;
        minesLeft = minesAmount;
        scoreWritten = false;
        playingCheck = true;
        isSthOpen = false;

        field = new int[allCells];
        for (int i = FIRST_CELL_NUM; i < allCells; i++) {
            field[i] = COVER_FOR_CELL;
        }
        if (timerBool) {
            timer = new Timer(TIMER_DELAY, new TimeListener());
            timerBool = false;
        }
        timer.start();
    }

    public static void addFieldMineCount(int cell) {
        if (cell >= FIRST_CELL_NUM && cell < allCells) {
            if (field[cell] != COVERED_MINE_CELL) {
                field[cell]++;
            }
        }
    }

    public static void openNeighbor(int cell) throws IOException {
        if (cell >= FIRST_CELL_NUM && cell < allCells) {
            if (field[cell] >= COVER_FOR_CELL && field[cell] < MIN_COVER_FLAG_CELL) {
                setField(cell, field[cell] - COVER_FOR_CELL);
                if (field[cell] == EMPTY_CELL) {
                    findEmptyCells(cell);
                }
            }
        }
    }

    public static boolean isItFlag(int cell) {
        if (cell >= FIRST_CELL_NUM && cell < allCells)
            return field[cell] > MIN_COVER_FLAG_CELL;
        return false;
    }

    public static boolean canIOpenNeighbors(int cell) {
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

    public static void putMines(int curXCell, int curYCell) {
        int mines = 0;
        Random random = new Random();
        while (mines < minesAmount) {
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

    public static void findEmptyCells(int curCell) throws IOException {
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

    public static boolean isItGG() throws IOException {
        for (int i = FIRST_CELL_NUM; i < allCells; ++i) {
            if (field[i] >= COVER_FOR_CELL && field[i] != COVERED_MINE_CELL + MIN_COVER_FLAG_CELL) {
                return false;
            }
        }
        calcScore();
        playingCheck = false;
        gameOver();
        return true;
    }
    public static void calcScore() throws IOException {
        score = (int)(100 * (((double)allCells / timeCounter) + ((double)allCells / stepAmount)));
        if (!scoreWritten) {
            ScoreFile sf = new ScoreFile();
            sf.write(score);
            scoreWritten = true;
        }
    }
    public static void gameOver() {
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

    public static void changeField(int newXCells, int newYCells, int newMinesAmount) {
        if (checkForField(newXCells, newYCells, newMinesAmount)) {
            setSize(newXCells, newYCells);
            setMinesAmount(newMinesAmount);
            newGame();
            TextView textView = new TextView();
            try {
                textView.print();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
    public static boolean checkForField(int newXCells, int newYCells, int newMinesAmount) {
        return xCells != newXCells
                || yCells != newYCells
                || minesAmount != newMinesAmount;
    }
    public static int getXCells() {
        return xCells;
    }
    public static int getYCells() {
        return yCells;
    }
    public static int getMinesLeft() {
        return minesLeft;
    }
    public static int getField(int i) {
        return field[i];
    }
    public static boolean isPlaying() { return playingCheck;}
    public static int getStepAmount() {return stepAmount;}
    public static boolean getIsSthOpen() {return isSthOpen;}
    public static int getTimeCounter() {
        return timeCounter;
    }
    public static int getScore() {
        return score;
    }
    public static void setMinesLeft(int val) { minesLeft = val;}
    public static void setMinesAmount(int val) {
        minesAmount = val;
    }
    public static void setTimeCounter(int val) {
        timeCounter = val;
    }
    public static void setSize(int xVal, int yVal) {
        xCells = xVal;
        yCells = yVal;
    }
    public static void setField(int i, int val) throws IOException {
        if (val == DRAW_MINE) {
            gameOver();
        }
        else {
            field[i] = val;
        }
    }
    public static void addStep() { stepAmount++;}
    public static void SthWasOpened() { isSthOpen = true;}

}
