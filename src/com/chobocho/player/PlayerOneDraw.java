package com.chobocho.player;

import com.chobocho.hexa.*;
import com.sun.tools.javac.Main;


import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class PlayerOneDraw implements IPlayerDraw {
    IHexa hexa;
    BufferedImage[] blockImages = null;

    public PlayerOneDraw () {
        blockImages = new BufferedImage[9];
        try {
            for (int i = 0; i < 8; i++) {
                blockImages[i+1] = ImageIO.read(new File("src\\com\\chobocho\\res\\" + i +".png"));
            }
            HexaLog.e("Load image Success!");
        } catch (IOException e) {
            HexaLog.e("Load image Error!!");
        }
    }

    public void setHexa(com.chobocho.hexa.IHexa hexa) {
        this.hexa = hexa;
    }

    public void onDraw(Graphics g, int startX, int startY, int blockWidth, int blockHeight) {
        HexaLog.d("onDraw");
        // Draw board
        int i = 0, j = 0;

        int[][] board = hexa.getBoard();

        for (i = 0; i < hexa.getWidth(); i++) {
            for (j = 0; j < hexa.getHeight(); j++) {
                if (board[j][i] == Hexa.EMPTY) {
                    drawRectangle(g, startX + i * blockWidth,
                            startY + j * blockHeight, board[j][i], blockWidth, blockHeight);
                } else {
                    drawBlockImage(g, startX + i * blockWidth,
                            startY + j * blockHeight, board[j][i], blockWidth, blockHeight);
                }
            }
        }

        if (hexa.isPlayState()) {
            System.out.println("Hexa (d) PlayerOne DrawBlock!");

            HexaBlock currentBlock = hexa.getCurrentBlock();
            drawBlock(g, currentBlock, startX, startY, blockWidth, blockHeight);

            HexaBlock nextblock = hexa.getNextBlock();
            int nextBlockX = startX + blockWidth * (hexa.getWidth()-2);
            int nextBlockY = startY + blockHeight * 4;

            drawBlock(g, nextblock, nextBlockX, nextBlockY, blockWidth, blockHeight);
        }

        int scorePointX =  startX + blockWidth * (hexa.getWidth()+1);
        int scorePointY =  startY + blockHeight * 8;
        g.setColor(Color.CYAN);
        g.setFont(new Font("Purisa", Font.PLAIN, 14));
        g.drawString(String.valueOf(hexa.getScore()), scorePointX, scorePointY);
    }


    private void drawBlock(Graphics g, HexaBlock block, int startX, int startY, int blockWidth, int blockHeight) {
        int i = 0;
        int[] ablock = block.getBlock();
        int sh = block.getHeight();
        int sx = block.getX();
        int sy = block.getY();

        for (i = 0; i < sh; i++) {
            drawBlockImage(g, startX + sx * blockWidth,
                    startY + (sy + i) * blockHeight, ablock[i], blockWidth, blockHeight);
        }
    }

    private void drawBlockImage(Graphics g, int x, int y, int type, int blockWidth, int blockHeight) {
        if (type < 0) {
            return;
        }
        g.drawImage(blockImages[type], x, y, blockWidth, blockHeight,null);
        //g.drawImage(blockImages[type], x, y, null);
    }

    private void drawRectangle(Graphics g, int x, int y, int type, int blockWidth, int blockHeight)
    {
        Color colors[] = { new Color(90, 90, 90), new Color(204, 102, 102),
                new Color(102, 204, 102), new Color(102, 102, 204),
                new Color(204, 204, 102), new Color(204, 102, 204),
                new Color(102, 204, 204), new Color(218, 170, 0)
        };

        Color color = colors[type];

        g.setColor(color);
        g.fillRect(x + 1, y + 1, blockWidth - 2, blockHeight - 2);

        g.setColor(color.brighter());
        g.drawLine(x, y + blockHeight - 1, x, y);
        g.drawLine(x, y, x + blockWidth - 1, y);

        g.setColor(color.darker());
        g.drawLine(x + 1, y + blockHeight - 1,
                x + blockWidth - 1, y + blockHeight - 1);
        g.drawLine(x + blockWidth - 1, y + blockHeight - 1,
                x + blockWidth - 1, y + 1);
    }

}
