package src.main.java.pl.roadflow.ui.screens;

import javax.swing.*;
import java.awt.*;

public class CarChangeScreen extends JFrame {
    public CarChangeScreen() {
        setTitle("Car Change");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
        GridLayout layout = new GridLayout(5, 4);
        setLayout(layout);
    }
}
