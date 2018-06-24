package com.chobocho.hexa;
import java.util.*;

/**
 * 
 */
public class HexaIdleState extends HexaGameState {

    /**
     * Default constructor
     */
    public HexaIdleState(Hexa Hexa) {
        HexaLog.d("HexaIdleState()");
        this.Hexa = Hexa;
    }
    public boolean isIdleState() {
       return true;
    }
}