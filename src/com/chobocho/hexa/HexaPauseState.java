package com.chobocho.hexa;


public class HexaPauseState extends HexaGameState {

    /**
     * Default constructor
     */
    public HexaPauseState(Hexa hexa) {
        this.Hexa = hexa;
    }

    public boolean isPauseState() { return true; }
}