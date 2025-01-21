package src.main.java.pl.roadflow.ui.screens;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartingMenu extends JFrame {
    public StartingMenu(){
        setTitle("RoadFlow");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);

        setButtons();

    }
    private void setButtons(){
        JButton startButton = new JButton("Start Game");
        startButton.setBounds(150, 100, 100, 40); // Ustawienie rozmiaru i pozycji przycisku
        add(startButton);

        JButton exitButton = new JButton("Exit");
        exitButton.setBounds(150, 150, 100, 40); // Ustawienie rozmiaru i pozycji przycisku
        add(exitButton);

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GameScreen gameScreen = new GameScreen();
                setVisible(false);
            }
        });

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0); // Zamykanie aplikacji
            }
        });

    }
}
