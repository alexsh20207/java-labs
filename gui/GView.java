package rus.nsu.fit.oop.ms.gui;

import rus.nsu.fit.oop.ms.GameModel;
import rus.nsu.fit.oop.ms.text.TextView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import static rus.nsu.fit.oop.ms.gui.ConstGUI.*;


public class GView extends JPanel implements ActionListener {
    GameModel gameModel = new GameModel();
    private Image[] img;
    private int widthSize = gameModel.getXCells() * CELL_SIZE ;
    private int heightSize = gameModel.getYCells() * CELL_SIZE + OFFSET_FOR_TEXT;
    private JLabel textMinesLeft, textTimer;
    Timer timer = new Timer(DELAY, this);
    TextView textView;

    public GView() throws IOException {
        textView = new TextView();
        textView.print();
        setLayout(new BorderLayout());

        textMinesLeft = new JLabel();
        textMinesLeft.setText(Integer.toString(gameModel.getMinesLeft()));
        textMinesLeft.setFont(new Font(FONT_NAME, Font.PLAIN, FONT_SIZE));
        textMinesLeft.setBackground(Color.black);

        textTimer = new JLabel();
        textTimer.setFont(new Font(FONT_NAME, Font.PLAIN, FONT_SIZE));
        textTimer.setBackground(Color.black);

        JPanel timerPanel = new JPanel(new BorderLayout());
        timerPanel.add(textTimer, BorderLayout.LINE_END);
        timerPanel.add(textMinesLeft, BorderLayout.LINE_START);
        add(timerPanel, BorderLayout.PAGE_END);

        setPreferredSize(new Dimension(widthSize, heightSize + OFFSET_Y));
        img = new Image[NUM_IMAGES];
        for (int i = 0; i < NUM_IMAGES; i++) {
            var path = PATH1 + i + PATH2;
            img[i] = (new ImageIcon(path)).getImage();
        }
        addMouseListener(new MouseController() {
            @Override
            public void updBoard() throws IOException {
                textView.print();
            }
        });
        timer.start();
    }
    public void actionPerformed(ActionEvent ev) {
        if (ev.getSource() == timer) {
            repaint();
        }
    }
    @Override
    public void paintComponent(Graphics g) {
        widthSize = gameModel.getXCells() * CELL_SIZE;
        heightSize = gameModel.getYCells() * CELL_SIZE + OFFSET_FOR_TEXT;
        setSize(new Dimension(widthSize,heightSize + OFFSET_Y));
        g.fillRect(0,0,widthSize,heightSize);
        int xCells = gameModel.getXCells();
        int yCells = gameModel.getYCells();
        for (int i = FIRST_YCELL; i < yCells; ++i) {
            for (int j = FIRST_XCELL; j < xCells; ++j) {
                int cell = gameModel.getField(i * xCells + j);
                if (cell > FIRST_GOOD_CLOSED_CELL_CODE && cell < FIRST_FLAG_CELL_CODE) {
                    cell = DRAW_CLOSED_CELL;
                }
                if (cell >= FIRST_FLAG_CELL_CODE && cell < WRONG_FLAG_CODE) {
                    cell = DRAW_FLAG;
                }
                if (cell == WRONG_FLAG_CODE) {
                    cell = DRAW_WRONG_FLAG;
                }
                g.drawImage(img[cell], (j * CELL_SIZE), (i * CELL_SIZE), this);
            }
        }
        if (gameModel.isPlaying() == false) {
            try {
                if (gameModel.isItGG() == true) {
                    textMinesLeft.setText(WIN_MSG);
                    textTimer.setText(Integer.toString(gameModel.getScore()));
                }
                else {
                    textMinesLeft.setText(LOSE_MSG);
                    textTimer.setText(SAD_FACE);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else {
            textMinesLeft.setText(Integer.toString(gameModel.getMinesLeft()));
            textTimer.setText(Integer.toString(gameModel.getTimeCounter()));
        }
    }
}

