package com.chobocho.player;

import com.chobocho.hexa.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Player implements IPlayer, ActionListener, IHexaObserver {
    final int BOARD_WIDTH = 7;
    final int BOARD_HEIGHT = 20;

    IHexa hexa;
    ChoboHexa.HexaBoardGui board;
    IPlayerDraw playerDraw;
    IPlayerAction playerAction;
    Timer hexaTimer;

    private int     gameSpeed = 0;

    public Player(ChoboHexa.HexaBoardGui board, IPlayerDraw playerDraw, IPlayerAction playerAction) {
        this.board = board;

        this.hexa = new Hexa(BOARD_WIDTH, BOARD_HEIGHT);
        this.hexa.register(this);
        this.hexa.init();

        this.playerDraw = playerDraw;
        this.playerDraw.setHexa(this.hexa);

        this.playerAction = playerAction;
        this.playerAction.setPlayer(this);

        this.hexaTimer = new Timer(0, this);
    }

    public void onDraw(Graphics g, int startX, int startY, int blockWidth, int blockHeight) {
        playerDraw.onDraw(g, startX, startY, blockWidth, blockHeight);
    }

    public void onPressKey(int k) {
        playerAction.onKeyEvent(k);
    }

    public void init() {
        hexa.init();
    }

    public void moveLeft() {
        hexa.moveLeft();
    }

    public void moveRight() {
        hexa.moveRight();
    }

    public void moveDown() {
        hexa.moveDown();
    }

    public void rotate() {
        hexa.rotate();
    }

    public void moveBottom() {
        hexa.moveBottom();
        hexa.moveDown();
    }

    public void play() {
        hexa.play();
        hexaTimer.start();
    }

    public void pause() {
        hexa.pause();
        hexaTimer.stop();
    }

    public void resume() {
        hexa.resume();
        hexaTimer.start();
    }

    public void update() {
        this.board.update();
    }

    public boolean isIdleState() {
        return hexa.isIdleState();
    }

    public boolean isGameOverState() {
        return hexa.isGameOverState();
    }

    public boolean isPlayState() {
        return hexa.isPlayState();
    }

    public boolean isPauseState() {
        return hexa.isPauseState();
    }

    public void actionPerformed(ActionEvent e) {
        System.out.println("Tetris (d) There is event");
        if (hexa == null ) {
            return;
        }
        if (hexa.isPlayState()) {
            hexa.moveDown();
            gameSpeed = 700 - (hexa.getScore() / 10000);
            hexaTimer.setDelay(gameSpeed);
        } else if (hexa.isGameOverState()) {
            hexaTimer.stop();
        }
    }
}
