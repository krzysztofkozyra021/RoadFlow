package src.main.java.pl.roadflow.ui.screens;

import src.main.java.pl.roadflow.utils.consts.GameConsts;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.EventListener;
import java.util.HashMap;

public class CarChangeScreen extends JFrame {
    HashMap<String, String> carNames = new HashMap<String, String>();
    public CarChangeScreen() {
        setTitle("Car Change");
        setSize(600, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        BackgroundPanel backgroundPanel = new BackgroundPanel();
        backgroundPanel.setLayout(new GridLayout(5, 4));
        add(backgroundPanel);

        addPanels(backgroundPanel);

        setVisible(true);
    }

    private void addPanels(JPanel backgroundPanel) {
        for (int i = 0; i < 20; i++) {
            JPanel panel = new JPanel();
            String car = "src/main/java/pl/roadflow/assets/cars/car"+(i + 1)+"_red.png";
            panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            panel.setBackground(new Color(0, 0, 0, 0));
            panel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    GameConsts.CAR_FILE_PATH = car;
                    GameScreen gameScreen = new GameScreen();
                    dispose();
                }
            });
            backgroundPanel.add(panel);
        }
    }

    private static class BackgroundPanel extends JPanel {
        private Image backgroundImage;

        public BackgroundPanel() {
            ImageIcon icon = new ImageIcon("src/main/java/pl/roadflow/assets/cars/file.png");
            backgroundImage = icon.getImage();
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }
}
