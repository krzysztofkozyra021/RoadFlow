package src.main.java.pl.roadflow.ui.screens;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class CarSelectScreen extends JFrame {
    private JLabel carPreviewLabel;
    private JPanel statsPanel;

    public CarSelectScreen() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(20, 20));
        getContentPane().setBackground(Color.BLACK);

        setupTitle();
        setupMainContent();

        GraphicsDevice device = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        if (device.isFullScreenSupported()) {
            this.setUndecorated(true);
            device.setFullScreenWindow(this);
        }

        setVisible(true);
    }

    private void setupTitle() {
        JLabel titleLabel = new JLabel("SELECT A CAR", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 48));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        add(titleLabel, BorderLayout.NORTH);
    }

    private void setupMainContent() {
        JPanel mainPanel = new JPanel(new GridLayout(1, 2, 20, 0));
        mainPanel.setBackground(Color.BLACK);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(0, 20, 20, 20));

        JPanel carsGrid = new JPanel(new GridLayout(4, 4, 7, 7));
        carsGrid.setBackground(Color.BLACK);
        carsGrid.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        addCarsFromImage(carsGrid);

        // GridBagLayout ensures proper centering of the cars grid
        JPanel leftContainer = new JPanel(new GridBagLayout());
        leftContainer.setBackground(Color.BLACK);
        leftContainer.add(carsGrid);
        mainPanel.add(leftContainer);

        JPanel rightPanel = new JPanel(new GridLayout(2, 1, 0, 10));
        rightPanel.setBackground(Color.BLACK);

        JPanel previewPanel = new JPanel(new BorderLayout());
        previewPanel.setBackground(Color.BLACK);
        carPreviewLabel = new JLabel("", SwingConstants.CENTER);
        previewPanel.add(carPreviewLabel, BorderLayout.CENTER);

        JPanel statsContainer = new JPanel(new FlowLayout(FlowLayout.CENTER));
        statsContainer.setBackground(Color.BLACK);

        statsPanel = new JPanel();
        statsPanel.setLayout(new BoxLayout(statsPanel, BoxLayout.Y_AXIS));
        statsPanel.setBackground(Color.BLACK);
        statsPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));

        createStatBars();
        statsContainer.add(statsPanel);
        rightPanel.add(previewPanel);
        rightPanel.add(statsContainer);

        mainPanel.add(rightPanel);
        add(mainPanel);
    }

    private void createStatBars() {
        createStatBar("Acceleration", 7);
        createStatBar("Max Speed", 8);
        createStatBar("Grip", 6);
    }

    private void createStatBar(String statName, int value) {
        JPanel statPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        statPanel.setBackground(Color.BLACK);

        JLabel nameLabel = new JLabel(statName);
        nameLabel.setForeground(Color.WHITE);
        nameLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        nameLabel.setPreferredSize(new Dimension(80, 15));

        // Create a 10-segment progress bar
        JPanel barsPanel = new JPanel(new GridLayout(1, 10, 2, 0));
        barsPanel.setBackground(Color.BLACK);
        barsPanel.setPreferredSize(new Dimension(150, 12));

        for (int i = 0; i < 10; i++) {
            JPanel bar = new JPanel();
            bar.setPreferredSize(new Dimension(12, 8));
            // Color segments up to the value yellow, remaining segments gray
            bar.setBackground(i < value ? Color.YELLOW : new Color(50, 50, 50));
            barsPanel.add(bar);
        }

        statPanel.add(nameLabel);
        statPanel.add(barsPanel);
        statsPanel.add(statPanel);
        statsPanel.add(Box.createVerticalStrut(5));
    }

    private void addCarsFromImage(JPanel panel) {
        ImageIcon carsIcon = new ImageIcon("src/main/java/pl/roadflow/assets/menu/Cars.png");
        Image carsImage = carsIcon.getImage();

        // Assuming the sprite sheet is a 4x4 grid of car images
        int carWidth = carsImage.getWidth(null) / 4;
        int carHeight = carsImage.getHeight(null) / 4;

        Border defaultBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
        Border selectedBorder = BorderFactory.createLineBorder(Color.YELLOW, 1);

        for (int row = 0; row < 4; row++) {
            for (int col = 0; col < 4; col++) {
                JPanel carContainer = new JPanel(new GridBagLayout());
                carContainer.setBackground(Color.BLACK);
                carContainer.setBorder(defaultBorder);

                // Extract individual car image from sprite sheet
                BufferedImage carImage = new BufferedImage(carWidth, carHeight, BufferedImage.TYPE_INT_ARGB);
                Graphics2D g = carImage.createGraphics();
                g.drawImage(carsImage, 0, 0, carWidth, carHeight,
                        col * carWidth, row * carHeight,
                        (col + 1) * carWidth, (row + 1) * carHeight, null);
                g.dispose();

                Image scaledImage = carImage.getScaledInstance(carWidth*2, carHeight*2, Image.SCALE_SMOOTH);
                JLabel carLabel = new JLabel(new ImageIcon(scaledImage));
                carContainer.add(carLabel);

                carContainer.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseEntered(MouseEvent e) {
                        carContainer.setBorder(selectedBorder);
                        carPreviewLabel.setIcon(new ImageIcon(carImage.getScaledInstance(200, 200, Image.SCALE_SMOOTH)));
                    }

                    @Override
                    public void mouseExited(MouseEvent e) {
                        carContainer.setBorder(defaultBorder);
                    }

                    @Override
                    public void mouseClicked(MouseEvent e) {
                        // Reset background color for all car containers
                        for (Component comp : panel.getComponents()) {
                            if (comp instanceof JPanel) {
                                comp.setBackground(Color.BLACK);
                            }
                        }
                        // Highlight selected car
                        carContainer.setBackground(new Color(30, 30, 30));
                    }
                });

                panel.add(carContainer);
            }
        }
    }
}