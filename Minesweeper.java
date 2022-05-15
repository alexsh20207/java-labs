package rus.nsu.fit.oop.ms;

import rus.nsu.fit.oop.ms.gui.GView;
import rus.nsu.fit.oop.ms.text.TextView;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import static rus.nsu.fit.oop.ms.Const.*;

public class Minesweeper extends JFrame {
    public Minesweeper() throws IOException {
        GameModel gm = new GameModel();
        gm.newGame();
        initUI();
    }

    private void initUI() throws IOException {
        add(new GView());
        setResizable(false);
        pack();
        setTitle(TITLE_NAME);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        createMenu();
    }

    private void createMenu() {
        JRootPane rootPane = new JRootPane();
        GameModel gameModel = new GameModel();
        JMenuBar menuBar = new JMenuBar();

        JMenu menuLvl = new JMenu(LVL_TEXT);
        JMenuItem menuLvlEasy = new JMenuItem(LVL_EASY_TEXT);
        menuLvlEasy.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (gameModel.getLvlNum() != LVL_EASY_NUM) {
                    gameModel.setSize(LVL_EASY_X_CELLS, LVL_EASY_Y_CELLS);
                    gameModel.setMinesCount(LVL_EASY_MINES);
                    gameModel.setLvlNum(LVL_EASY_NUM);
                    gameModel.newGame();
                    TextView textView = new TextView();
                    try {
                        textView.print();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                    setSize(new Dimension(gameModel.getXCells() * CELL_SIZE + OFFSET_X,
                            gameModel.getYCells() * CELL_SIZE + OFFSET_Y));
                }
            }
        });
        menuLvl.add(menuLvlEasy);
        JMenuItem menuLvlNormal = new JMenuItem(LVL_NORMAL_TEXT);
        menuLvlNormal.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (gameModel.getLvlNum() != LVL_NORMAL_NUM) {
                    gameModel.setSize(LVL_NORMAL_X_CELLS, LVL_NORMAL_Y_CELLS);
                    gameModel.setMinesCount(LVL_NORMAL_MINES);
                    gameModel.setLvlNum(LVL_NORMAL_NUM);
                    gameModel.newGame();
                    TextView textView = new TextView();
                    try {
                        textView.print();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                    setSize(new Dimension(gameModel.getXCells() * CELL_SIZE + OFFSET_X,
                            gameModel.getYCells() * CELL_SIZE + OFFSET_Y));
                }
            }
        });
        menuLvl.add(menuLvlNormal);
        JMenuItem menuLvlHard = new JMenuItem(LVL_HARD_TEXT);
        menuLvlHard.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (gameModel.getLvlNum() != LVL_HARD_NUM) {
                    gameModel.setSize(LVL_HARD_X_CELLS, LVL_HARD_Y_CELLS);
                    gameModel.setMinesCount(LVL_HARD_MINES);
                    gameModel.setLvlNum(LVL_HARD_NUM);
                    gameModel.newGame();
                    TextView textView = new TextView();
                    try {
                        textView.print();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                    setSize(new Dimension(gameModel.getXCells() * CELL_SIZE + OFFSET_X,
                            gameModel.getYCells() * CELL_SIZE + OFFSET_Y));
                }
            }
        });
        menuLvl.add(menuLvlHard);
        menuBar.add(menuLvl);
        JMenuItem menuNG = new JMenuItem(NEW_GAME_TEXT);
        menuNG.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameModel.newGame();
                TextView textView = new TextView();
                try {
                    textView.print();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
        menuBar.add(menuNG);

        JMenuItem menuHS = new JMenuItem(HS_TEXT);
        menuHS.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    WindowHS whs = new WindowHS();
                } catch (FileNotFoundException ex) {
                    ex.printStackTrace();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
        menuBar.add(menuHS);

        JMenuItem menuAbout = new JMenuItem(ABOUT_TEXT);
        menuAbout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                WindowAbout wa = new WindowAbout();
            }
        });
        menuBar.add(menuAbout);

        JMenuItem menuExit = new JMenuItem(EXIT_TEXT);
        menuExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        menuBar.add(menuExit);
        rootPane.setJMenuBar(menuBar);
        add(rootPane, BorderLayout.BEFORE_FIRST_LINE);
    }
}