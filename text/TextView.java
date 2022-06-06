package rus.nsu.fit.oop.ms.text;

import rus.nsu.fit.oop.ms.GameModel;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import static rus.nsu.fit.oop.ms.text.ConstText.*;


public class TextView {
    public void print() throws IOException {
            int xCells = GameModel.getXCells();
            int yCells = GameModel.getYCells();
            for (int i = FIRST_YCELL; i < yCells; ++i) {
                for (int j = FIRST_XCELL; j < xCells; ++j) {
                    int cell = GameModel.getField(i * xCells + j);
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
            printGameResult();
        }

    private void printGameResult() throws IOException {
        if (!GameModel.isPlaying()) {
            if (GameModel.isItGG()) {
                System.out.println(WIN_MSG + GameModel.getScore() + SPACE + GameModel.getTimeCounter());
            }
            else {
                System.out.println(LOSE_MSG);
            }
        }
        else {
            System.out.println(GameModel.getMinesLeft() + MINE_LEFT_MSG);
        }
    }

    public void printHelp() {
        printMsg(NEW_GAME_CMD_NAME, NEW_GAME_CMD_ARG_COUNT, NEW_GAME_DESC);
        printMsg(PRINT_CMD_NAME, PRINT_CMD_ARG_COUNT, PRINT_CMD_DESC);
        printMsg(EXIT_CMD_NAME, EXIT_CMD_ARG_COUNT, EXIT_DESC);
        printMsg(ABOUT_CMD_NAME, ABOUT_CMD_ARG_COUNT, ABOUT_CMD_DESC);
        printMsg(HS_CMD_NAME, HS_CMD_ARG_COUNT, HS_CMD_DESC);
        printMsg(HELP_CMD_NAME, HELP_CMD_ARG_COUNT, HELP_CMD_DESC);
        printMsg(OPEN_CMD_NAME, OPEN_CMD_ARG_COUNT, OPEN_CMD_DESC);
        printMsg(FLAG_CMD_NAME, FLAG_CMD_ARG_COUNT, FLAG_CMD_DESC);
    }

    public void printMsg(String commandName, int argAmount, String instruction) {
        System.out.println(commandName + BRACKET_LEFT + argAmount + BRACKET_RIGHT + instruction);
    }

    public void printHS() throws IOException {
        FileReader fr = new FileReader(FILE_HS);
        BufferedReader reader = new BufferedReader(fr);
        int counter = 0;
        String line;
        while ((line = reader.readLine()) != null && counter != HS_MAX) {
            counter++;
            String str = counter + DASH_TEXT + line;
            System.out.println(str);
        }
        if (counter == COUNTER_INIT) {
            System.out.println(MSG_NOTHING);
        }
    }

    public void printAbout() {
        System.out.println(ABOUT_TEXT);
    }
}
