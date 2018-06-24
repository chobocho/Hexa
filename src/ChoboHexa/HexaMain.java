package ChoboHexa;

import javax.swing.*;
import java.awt.*;

public class HexaMain extends JFrame {
    JLabel statusbar;
    HexaBoardGui hexaBoardGui;
    int    players = 1;

    public int getPlayers() {
        return this.players;
    }

    public void resetBoardSize() {
        if (players == 1) {
            setSize(300, 620);
        } else {
            setSize(600,620);
        }
        if (hexaBoardGui != null) {
            hexaBoardGui.setPlayer(getPlayers());
        }
    }

    public HexaMain() {
        JMenuBar hexaMenu = new JMenuBar();
        JMenu JMPlayers = new JMenu("Players");
        hexaMenu.add(JMPlayers);

        ButtonGroup playerGroup = new ButtonGroup();
        JRadioButtonMenuItem menuItemOnePlayer = new JRadioButtonMenuItem("One player");
        menuItemOnePlayer.addActionListener(e-> { this.players = 1; resetBoardSize(); });
        playerGroup.add(menuItemOnePlayer);
        JMPlayers.add(menuItemOnePlayer);

        JRadioButtonMenuItem menuItemTwoPlayer = new JRadioButtonMenuItem("Two players");
        menuItemTwoPlayer.addActionListener(e-> { this.players = 2; resetBoardSize(); });
        playerGroup.add(menuItemTwoPlayer);
        JMPlayers.add(menuItemTwoPlayer);

        setJMenuBar(hexaMenu);

        statusbar = new JLabel("Press S to play game");
        statusbar.setFont(new Font(statusbar.getFont().getFontName(), Font.PLAIN, 18));
        add(statusbar, BorderLayout.SOUTH);
        hexaBoardGui = new HexaBoardGui(this);
        add(hexaBoardGui);

        hexaBoardGui.start();

        setSize(300, 620);
        setResizable(true);
        setTitle("ChoboHexa");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public JLabel getStatusBar() {
        return statusbar;
    }

    public static void main(String[] args) {
        HexaMain tetrisGui = new HexaMain();
        tetrisGui.setLocationRelativeTo(null);
        tetrisGui.setVisible(true);
    }
}