package rus.nsu.fit.oop.ms;

import rus.nsu.fit.oop.ms.gui.GView;
import rus.nsu.fit.oop.ms.gui.WindowAbout;
import rus.nsu.fit.oop.ms.gui.WindowHS;
import rus.nsu.fit.oop.ms.text.TextController;
import rus.nsu.fit.oop.ms.text.TextView;
import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.io.IOException;
import static rus.nsu.fit.oop.ms.Const.*;

public class Minesweeper extends JFrame {

    private final TextController textController = new TextController();

    public Minesweeper() throws IOException {
        GameModel.newGame();
        initUI();
    }

    private void initUI() throws IOException {
        add(new GView());
        setResizable(false);
        pack();
        createMenu();
        setVisible(true);
        setTitle(TITLE_NAME);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        textController.work();
        gameExit();
    }

    private void createMenu() {
        JRootPane rootPane = new JRootPane();
        JMenuBar menuBar = new JMenuBar();

        JMenu menuLvl = new JMenu(LVL_TEXT);
        menuLvl.add(createLvlMenu(LVL_EASY_TEXT, LVL_EASY_X_CELLS, LVL_EASY_Y_CELLS, LVL_EASY_MINES));
        menuLvl.add(createLvlMenu(LVL_NORMAL_TEXT, LVL_NORMAL_X_CELLS, LVL_NORMAL_Y_CELLS, LVL_NORMAL_MINES));
        menuLvl.add(createLvlMenu(LVL_HARD_TEXT, LVL_HARD_X_CELLS, LVL_HARD_Y_CELLS, LVL_HARD_MINES));
        menuBar.add(menuLvl);

        menuBar.add(createMenuNG());
        menuBar.add(createMenuHS());
        menuBar.add(createMenuAbout());
        menuBar.add(createMenuExit());

        rootPane.setJMenuBar(menuBar);
        add(rootPane, BorderLayout.BEFORE_FIRST_LINE);
    }

    private JMenuItem createMenuExit() {
        JMenuItem menuExit = new JMenuItem(EXIT_TEXT);
        menuExit.addActionListener(e -> gameExit());
        return menuExit;
    }

    private void gameExit() {
        dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
    }


    private JMenuItem createMenuAbout() {
        JMenuItem menuAbout = new JMenuItem(ABOUT_TEXT);
        menuAbout.addActionListener(e -> {
            WindowAbout windowAbout = new WindowAbout();
            TextView textView = new TextView();
            textView.printAbout();
        });
        return menuAbout;
    }

    private JMenuItem createMenuHS() {
        JMenuItem menuHS = new JMenuItem(HS_TEXT);
        menuHS.addActionListener(e -> {
            try {
                WindowHS whs = new WindowHS();
                TextView textView = new TextView();
                textView.printHS();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
        return menuHS;
    }

    private JMenuItem createMenuNG() {
        JMenuItem menuNG = new JMenuItem(NEW_GAME_TEXT);
        menuNG.addActionListener(e -> {
            GameModel.newGame();
            TextView textView = new TextView();
            try {
                textView.print();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
        return menuNG;
    }

    private void changeFieldInfo(int newXCells, int newYCells, int newMinesAmount) {
        GameModel.changeField(newXCells, newYCells, newMinesAmount);
        setSize(new Dimension(GameModel.getXCells() * CELL_SIZE + OFFSET_X,
                GameModel.getYCells() * CELL_SIZE + OFFSET_Y));
    }

    private JMenuItem createLvlMenu(String name, int xCells, int yCells, int minesAmount) {
        JMenuItem menuLvl = new JMenuItem(name);
        menuLvl.addActionListener(e -> changeFieldInfo(xCells, yCells, minesAmount));
        return menuLvl;
    }
}