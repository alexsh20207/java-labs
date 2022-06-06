package rus.nsu.fit.oop.ms.text;

import rus.nsu.fit.oop.ms.GameModel;

import java.io.IOException;
import java.util.Locale;
import java.util.Scanner;

import static rus.nsu.fit.oop.ms.text.ConstText.*;

public class TextController {
    private final Scanner scanner;
    private final TextView textView = new TextView();
    public TextController() {
        scanner = new Scanner(System.in);
    }

    public void work() throws IOException {
        String line;
        while ((line = scanner.nextLine()) != null) {
            line = line.toUpperCase(Locale.ROOT);
            String[] commandLine = line.split(SPACES);
            if (validate(commandLine)) {
                if (execute(commandLine) == 0) {
                    break;
                }
            }
            else {
                System.out.println(WRONG_INPUT);
            }
        }
    }

    private boolean validate(String[] commandLine) {
        int argAmount = ARG_INIT;
        String cmdName = commandLine[NUM_FOR_CMD_NAME];
        if (cmdName.equals(NEW_GAME_CMD_NAME) || cmdName.equals(EXIT_CMD_NAME)
                || cmdName.equals(ABOUT_CMD_NAME) || cmdName.equals(HS_CMD_NAME)
                || cmdName.equals(HELP_CMD_NAME) || cmdName.equals(PRINT_CMD_NAME)) {
            argAmount = ARG_ZERO;
        }
        if (cmdName.equals(OPEN_CMD_NAME) || cmdName.equals(FLAG_CMD_NAME)) {
            argAmount = ARG_TWO;
        }
        return argCheck(commandLine, argAmount);
    }

    private boolean argCheck(String[] commandLine, int argAmount) {
        if (commandLine.length != argAmount + OFFSET_FOR_CMD_ARGS) {
            return false;
        }
        for (int i = OFFSET_FOR_CMD_ARGS; i < commandLine.length; ++i) {
            if (!isInteger(commandLine[i])) {
                return false;
            }
        }
        return true;
    }

    private int execute(String[] commandLine) throws IOException {
        String cmdName = commandLine[NUM_FOR_CMD_NAME];
        switch(cmdName) {
            case NEW_GAME_CMD_NAME -> {
                GameModel.newGame();
                textView.print();
            }
            case PRINT_CMD_NAME -> textView.print();
            case ABOUT_CMD_NAME -> textView.printAbout();
            case HS_CMD_NAME -> textView.printHS();
            case HELP_CMD_NAME -> textView.printHelp();
            case OPEN_CMD_NAME -> openCell(Integer.parseInt(commandLine[FIRST_ARG]),
                    Integer.parseInt(commandLine[SECOND_ARG]));
            case FLAG_CMD_NAME -> putFlag(Integer.parseInt(commandLine[FIRST_ARG]),
                    Integer.parseInt(commandLine[SECOND_ARG]));
            case EXIT_CMD_NAME -> {
                return appExit();
            }
        }
        return 1;
    }

    private void putFlag(int xCell, int yCell) throws IOException {
        if (xCell < GameModel.getXCells() && yCell < GameModel.getYCells() && GameModel.isPlaying()) {
            int cell = yCell * GameModel.getXCells() + xCell;
            int curCell = GameModel.getField(cell);
            if (curCell >= FIRST_GOOD_CLOSED_CELL_CODE) {
                if (curCell < FIRST_FLAG_CELL_CODE) {
                    try {
                        GameModel.setField(cell, curCell + FIRST_FLAG_CELL_CODE);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                    GameModel.setMinesLeft(GameModel.getMinesLeft() - ONE);
                } else {
                    try {
                        GameModel.setField(cell, curCell - FIRST_FLAG_CELL_CODE);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                    GameModel.setMinesLeft(GameModel.getMinesLeft() + ONE);
                }
            }
            GameModel.addStep();
            textView.print();
        }
        try {
            GameModel.isItGG();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void openCell(int xCell, int yCell) throws IOException {
        if (xCell < GameModel.getXCells() && yCell < GameModel.getYCells() && GameModel.isPlaying()) {
            int cell = yCell * GameModel.getXCells() + xCell;
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
            GameModel.addStep();
            textView.print();
        }
        try {
            GameModel.isItGG();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private int appExit() {
        return 0;
    }

    public boolean isInteger(String s) {
        try {
            Integer.parseInt(s);
        } catch(NumberFormatException | NullPointerException e) {
            return false;
        }
        return true;
    }

}
