package com.chobocho.player;

import java.awt.event.KeyEvent;

public class PlayerTwoAction implements IPlayerAction {
    IPlayer player = null;

    public void onKeyEvent(int keycode) {
        System.out.println("Tetris (d) Player2 Press key : " + keycode);

        if (player == null) {
            return;
        }

        if (player.isIdleState()) {
            if (keycode == 's' || keycode == 'S'){
                System.out.println("Start!");
                player.play();
            }
            return;
        }

        if (player.isGameOverState()) {
            if (keycode == 's' || keycode == 'S'){
                player.init();
            }
            return;
        }

        if (player.isPauseState()) {
            if (keycode =='p' || keycode == 'P'){
                player.resume();
            }
            return;
        }

        if (player.isPlayState() == false) {
            return;
        }

        switch (keycode) {
            case 'J':
            case 'j':
                player.moveLeft();
                break;
            case 'L':
            case 'l':
                player.moveRight();
                break;
            case 'K':
            case 'k':
                player.moveDown();
                break;
            case 'I':
            case 'i':
                player.rotate();
                break;
            case 'f':
            case 'F':
                player.moveBottom();
                break;
            case 'p':
            case'P':
                player.pause();
                break;
        }
    }

    public void setPlayer(IPlayer player) {
        this.player = player;
    }
}
