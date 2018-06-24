package ChoboHexa;

import com.chobocho.player.*;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class HexaBoardGui extends JPanel {
    int BOARD_WIDTH = 10;
    final int BOARD_HEIGHT = 20;
    int players = 1;

    IPlayer playerOne;
    IPlayerDraw playerOneDraw;
    IPlayerAction playerOneAction;


    IPlayer playerTwo;
    IPlayerDraw playerTwoDraw;
    IPlayerAction playerTwoAction;

    JLabel statusbar;

    private Image screenBuffer = null;
    private Graphics graphicsBuffer = null;


    public HexaBoardGui(HexaMain parent) {
        playerOneDraw = new PlayerOneDraw();
        playerOneAction = new PlayerOneAction();
        playerOne = new Player(this, playerOneDraw, playerOneAction);

        playerTwoDraw = new PlayerOneDraw();
        playerTwoAction = new PlayerTwoAction();
        playerTwo = new Player(this, playerTwoDraw, playerTwoAction);

        statusbar = parent.getStatusBar();
        setFocusable(true);
        addKeyListener(new TetrisKeyAdapter());
    }

    public void setPlayer(int n) {
        if (n == 1) {
            players = 1;
            BOARD_WIDTH = 10;
        } else {
            players = 2;
            BOARD_WIDTH = 20;
        }
    }

    public void update() {
        // System.out.println("Tetris (d) View.update()");
        repaint();
    }
    private int blockWidth() { return (int) getSize().getWidth() / BOARD_WIDTH; }
    private int blockHeight() { return (int) getSize().getHeight() / BOARD_HEIGHT; }


    public void start()
    {
        playerOne.init();
        playerTwo.init();
        statusbar.setText("Press S to start game!");
    }

    public void paint(Graphics g)
    {
        super.paint(g);
        Dimension size = getSize();

        int width = (int)size.getWidth();
        int height = (int)size.getHeight();

        if (screenBuffer == null) {
            screenBuffer = createImage(width, height);
        }

        graphicsBuffer = screenBuffer.getGraphics();
        graphicsBuffer.setColor(Color.DARK_GRAY);
        graphicsBuffer.fillRect(0, 0, width, height);

        int boardY = (int) size.getHeight() - BOARD_HEIGHT * blockHeight();
        int boardX = (int) (size.getWidth() - BOARD_WIDTH * blockWidth())/2;

        playerOne.onDraw(graphicsBuffer, boardX, boardY, blockWidth(), blockHeight());
        if (players == 2) {
            int player2BoardX = (int) size.getWidth() / 2;
            int player2BoardY = boardY;
            playerTwo.onDraw(graphicsBuffer, player2BoardX, player2BoardY, blockWidth(), blockHeight());
        }
        g.drawImage(screenBuffer, 0, 0, null);

        screenBuffer = null;
    }

    class TetrisKeyAdapter extends KeyAdapter {
        public void keyPressed(KeyEvent e) {
            int keycode = e.getKeyCode();
            playerOne.onPressKey(keycode);
            if (players == 2) {
                playerTwo.onPressKey(keycode);
            }
        }
    }
}