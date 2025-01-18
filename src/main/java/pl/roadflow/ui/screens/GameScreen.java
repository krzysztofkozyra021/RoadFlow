package src.main.java.pl.roadflow.ui.screens;

import javax.swing.*;

public class GameScreen extends JFrame {

    public final int WIDTH = 1920;
    public final int HEIGHT = 1080;
    public final String TITLE = "Road Flow";

    public GameScreen() {
        setTitle(TITLE);
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }
}
