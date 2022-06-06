package rus.nsu.fit.oop.ms.text;

public class ConstText {
    final static String EMPTY_CELL_SYM = "A";
    final static String FLAG_SYM = "$";
    final static String WRONG_FLAG_SYM = "#";
    final static String MINE_SYM = "X";
    final static String WIN_MSG = "GG ";
    final static String LOSE_MSG = "Game Over...";
    final static String MINE_LEFT_MSG = " mines left";

    final static int FIRST_GOOD_CLOSED_CELL_CODE = 10;
    final static int FIRST_FLAG_CELL_CODE = 20;
    final static int WRONG_FLAG_CODE = 40;
    final static int MINE_CELL_CODE = 9;
    final static int FIRST_XCELL = 0;
    final static int FIRST_YCELL = 0;

    final static String TAB_SYM = "\t";
    final static String SPACE = " ";

    final static String FILE_HS = "src/main/java/hs.txt";

    final static String MSG_NOTHING = "There's nothing yet..";
    final static String DASH_TEXT = " - ";
    final static int COUNTER_INIT = 0;
    final static int HS_MAX = 10;

    final static String SPACES = "\\s+";
    final static String WRONG_INPUT = "Wrong input. Type \"HELP\" for help";
    final static String NEW_GAME_CMD_NAME = "NG";
    final static String EXIT_CMD_NAME = "EXIT";
    final static String ABOUT_CMD_NAME = "ABOUT";
    final static String HS_CMD_NAME = "HS";
    final static String HELP_CMD_NAME = "HELP";
    final static String PRINT_CMD_NAME = "PRINT";
    final static String OPEN_CMD_NAME = "OPEN";
    final static String FLAG_CMD_NAME = "FLAG";

    final static String NEW_GAME_DESC = "start new game";
    final static String EXIT_DESC = "exit the game";
    final static String ABOUT_CMD_DESC = "print info About";
    final static String HS_CMD_DESC = "print high scores";
    final static String HELP_CMD_DESC = "guess what it is :)";
    final static String PRINT_CMD_DESC = "print field";
    final static String OPEN_CMD_DESC = "open cell[x][y]";
    final static String FLAG_CMD_DESC = "put flag in cell[x][y]";

    final static int NEW_GAME_CMD_ARG_COUNT = 0;
    final static int EXIT_CMD_ARG_COUNT = 0;
    final static int ABOUT_CMD_ARG_COUNT = 0;
    final static int HS_CMD_ARG_COUNT = 0;
    final static int HELP_CMD_ARG_COUNT = 0;
    final static int PRINT_CMD_ARG_COUNT = 0;
    final static int OPEN_CMD_ARG_COUNT = 2;
    final static int FLAG_CMD_ARG_COUNT = 2;


    final static int NUM_FOR_CMD_NAME = 0;
    final static int OFFSET_FOR_CMD_ARGS = 1;

    final static int ARG_INIT = -1;
    final static int ARG_ZERO = 0;
    final static int ARG_TWO = 2;

    final static int FIRST_ARG = 1;
    final static int SECOND_ARG = 2;

    final static int ONE = 1;

    final static String ABOUT_TEXT = "Minesweeper. Bombeznaya igra";
    final static String BRACKET_RIGHT = ") - ";
    final static String BRACKET_LEFT = "(";
}
