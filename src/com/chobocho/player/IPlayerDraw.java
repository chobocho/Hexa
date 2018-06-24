package com.chobocho.player;

import java.awt.*;
import com.chobocho.hexa.*;

public interface IPlayerDraw {
    public void onDraw(Graphics g, int startX, int startY, int blockwidth, int blockHeight);
    public void setHexa(IHexa hexa);
}
