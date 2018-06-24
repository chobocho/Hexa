package com.chobocho.hexa;

public class HexaPlayState extends HexaGameState {
    private HexaBlock currentHexaBlock;
    private HexaBlock nextHexaBlock;
    private HexaBoard HexaBoard;
    private int additionalPoint = 1;

    public HexaPlayState(Hexa hexa, HexaBoard board) {
        this.Hexa = hexa;
        this.HexaBoard = board;
        currentHexaBlock = HexaBlockFactory.create();
        nextHexaBlock = HexaBlockFactory.create();
    }

    public void init() {
        this.HexaBoard.init();
        currentHexaBlock = HexaBlockFactory.create();
        nextHexaBlock = HexaBlockFactory.create();
        additionalPoint = 1;
    }

    public void moveLeft() {
       HexaLog.d("HexaPlayState.moveLeft()");
        currentHexaBlock.moveLeft();
        if (HexaBoard.isAcceptable(currentHexaBlock) == false) {
            currentHexaBlock.moveRight();
            HexaLog.d("Not Accept");
        } else {
            HexaLog.d("Accept");
        }
    }

    public void moveRight() {
        HexaLog.d("HexaPlayState.moveRight()");
        currentHexaBlock.moveRight();
        if (HexaBoard.isAcceptable(currentHexaBlock) == false) {
            currentHexaBlock.moveLeft();
            HexaLog.d("Not Accept");
        } else {
            HexaLog.d("Accept");
        }
    }

    public void rotate() {
        HexaLog.d("HexaPlayState.rotate()");
        currentHexaBlock.rotate();
    }


    public void moveDown() {
        HexaLog.d("HexaPlayState.moveDown()");
        currentHexaBlock.moveDown();
        if (HexaBoard.isAcceptable(currentHexaBlock) == false) {
            currentHexaBlock.moveUp();
            HexaLog.d("Can not move down");
            fixCurrentBlock();
            updateBoard();
            updateBlock() ;
        } else {
            HexaLog.d("Accept");
        }
    }

    public void moveBottom() {
        HexaLog.d("HexaPlayState.moveBottom()");
        while(HexaBoard.isAcceptable(currentHexaBlock)) {
            currentHexaBlock.moveDown();
        }
        if (HexaBoard.isAcceptable(currentHexaBlock) == false) {
            currentHexaBlock.moveUp();
        }
    }

    public void fixCurrentBlock() {
        HexaBoard.addHexaBlock(currentHexaBlock);
    }

    public void updateBlock() {
        currentHexaBlock = nextHexaBlock;
        nextHexaBlock = HexaBlockFactory.create();
    }

    public boolean gameOver() {
        HexaLog.d("Check Game over!");
        return (HexaBoard.isAcceptable(currentHexaBlock) == false);
    }

    public void updateBoard() {
        int removedLine = HexaBoard.arrange();
        int point = calculatorScore(removedLine);
        Hexa.addSore(point);
    }

    private int calculatorScore(int removedLineCount) {
        if (removedLineCount == 0) {
            additionalPoint = 1;
            return 0;
        }

        int lineScore = 22;
        if (removedLineCount >= 4) {
            removedLineCount = 4;
            lineScore = 888;
        } else {
            lineScore *= removedLineCount;
        }
        if (additionalPoint > 10000) {
            additionalPoint = 10000;
        }
        additionalPoint <<= removedLineCount;
        HexaLog.d("calculatorScore : " + additionalPoint + " : " + removedLineCount);
        return  (removedLineCount * 10 * additionalPoint + lineScore);
    }

    public HexaBlock getCurrentHexaBlock() {
        return currentHexaBlock;
    }

    public HexaBlock getNextHexaBlock() {
        return nextHexaBlock;
    }

    public boolean isPlayState() {
        return true;
    }
}