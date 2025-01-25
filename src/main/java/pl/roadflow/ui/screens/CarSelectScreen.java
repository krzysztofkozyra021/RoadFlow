package src.main.java.pl.roadflow.ui.screens;

import src.main.java.pl.roadflow.core.mechanics.car.Car;
import src.main.java.pl.roadflow.core.mechanics.car.carTypes.*;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;

public class CarSelectScreen extends JFrame {
    private static final Color BACKGROUND_COLOR = new Color(18, 18, 24);
    private static final Color ACCENT_COLOR = new Color(79, 70, 229);
    private static final Color HOVER_COLOR = new Color(99, 90, 255);
    private static final Color SECONDARY_COLOR = new Color(30, 30, 40);
    private static final Color TEXT_COLOR = new Color(229, 231, 235);
    private static final Color STAT_BAR_FILLED = new Color(79, 70, 229);
    private static final Color STAT_BAR_EMPTY = new Color(45, 45, 60);

    private JLabel carPreviewLabel;
    private JPanel statsPanel;
    private JLabel titleLabel;
    private JPanel colorPanel;

    private Car selectedCar;
    private String selectedColor = "armygreen";
    private int currentCarId = 1;

    public CarSelectScreen() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(20, 20));
        getContentPane().setBackground(BACKGROUND_COLOR);
        setupMainContent();

        GraphicsDevice device = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        if (device.isFullScreenSupported()) {
            setUndecorated(true);
            device.setFullScreenWindow(this);
        }

        setVisible(true);
    }

    private void setupMainContent() {
        JPanel mainPanel = new JPanel(new GridLayout(1, 2, 40, 0));
        mainPanel.setBackground(BACKGROUND_COLOR);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(30, 40, 40, 40));

        setupLeftPanel(mainPanel);
        setupRightPanel(mainPanel);

        add(mainPanel);
        updateSelectedCar();
    }

    private void setupLeftPanel(JPanel mainPanel) {
        JPanel leftContainer = new JPanel(new GridBagLayout());
        leftContainer.setBackground(BACKGROUND_COLOR);
        GridBagConstraints gbc = new GridBagConstraints();

        // Title
        JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        titlePanel.setBackground(BACKGROUND_COLOR);
        titleLabel = new JLabel("Select your car");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 36));
        titleLabel.setForeground(TEXT_COLOR);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));
        titlePanel.add(titleLabel);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(0, 0, 20, 0);
        leftContainer.add(titlePanel, gbc);

        // Cars grid
        JPanel carsGrid = new JPanel(new GridLayout(4, 4, 12, 12));
        carsGrid.setBackground(BACKGROUND_COLOR);
        addCarsFromImage(carsGrid);

        gbc.gridy = 1;
        gbc.insets = new Insets(0, 0, 20, 0);
        leftContainer.add(carsGrid, gbc);

        // Color panel under cars grid
        colorPanel = new JPanel(new GridLayout(2, 4, 8, 8));
        colorPanel.setBackground(BACKGROUND_COLOR);
        setupColorPanel(colorPanel);

        gbc.gridy = 2;
        gbc.insets = new Insets(0, 0, 0, 0);
        leftContainer.add(colorPanel, gbc);

        mainPanel.add(leftContainer);
    }

    private void setupColorPanel(JPanel panel) {
        String[] colors = {"armygreen", "blue", "brown", "red", "neongreen", "pink", "purple", "yellow"};
        for (String color : colors) {
            JPanel colorSwitch = new JPanel();
            colorSwitch.setPreferredSize(new Dimension(30, 30));
            colorSwitch.setBackground(getColorFromName(color));
            colorSwitch.setBorder(BorderFactory.createLineBorder(SECONDARY_COLOR, 2));

            colorSwitch.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    resetColorBorders();
                    selectedColor = color;
                    colorSwitch.setBorder(BorderFactory.createLineBorder(ACCENT_COLOR, 2));
                    updateSelectedCar();
                }
            });

            panel.add(colorSwitch);
        }
    }

    private void setupRightPanel(JPanel mainPanel) {
        JPanel rightPanel = new JPanel(new BorderLayout(20, 20));
        rightPanel.setBackground(BACKGROUND_COLOR);
        rightPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        setupColorPanel(rightPanel);

        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.X_AXIS));
        contentPanel.setBackground(BACKGROUND_COLOR);

        JPanel previewPanel = new JPanel(new BorderLayout());
        previewPanel.setBackground(BACKGROUND_COLOR);
        previewPanel.setPreferredSize(new Dimension(250, 250));
        carPreviewLabel = new JLabel("", SwingConstants.CENTER);
        previewPanel.add(carPreviewLabel, BorderLayout.CENTER);

        statsPanel = new JPanel();
        statsPanel.setLayout(new BoxLayout(statsPanel, BoxLayout.Y_AXIS));
        statsPanel.setBackground(BACKGROUND_COLOR);
        statsPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 0));

        contentPanel.add(previewPanel);
        contentPanel.add(statsPanel);

        rightPanel.add(contentPanel, BorderLayout.CENTER);
        mainPanel.add(rightPanel);
    }


    private void resetColorBorders() {
        for (Component comp : colorPanel.getComponents()) {
            if (comp instanceof JPanel) {
                JPanel panel = (JPanel) comp;
                panel.setBorder(BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(SECONDARY_COLOR, 2),
                        BorderFactory.createLineBorder(BACKGROUND_COLOR, 2)
                ));
            }
        }
    }

    private Color getColorFromName(String colorName) {
        switch (colorName.toLowerCase()) {
            case "armygreen": return new Color(75, 83, 32);
            case "blue": return new Color(0, 120, 215);
            case "brown": return new Color(139, 69, 19);
            case "red": return new Color(192, 28, 40);
            case "neongreen": return new Color(57, 255, 20);
            case "pink": return new Color(255, 105, 180);
            case "purple": return new Color(147, 51, 234);
            case "yellow": return new Color(255, 225, 25);
            default: return Color.GRAY;
        }
    }

    private void updateSelectedCar() {
        selectedCar = createCarBasedOnId(currentCarId, selectedColor);
        updateStatBars();
        updateTitle();
        updateCarPreview();
    }

    private void updateCarPreview() {
        if (selectedCar == null) return;
        ImageIcon originalIcon = selectedCar.getCarModelIcon();
        if (originalIcon == null) return;

        Image originalImage = originalIcon.getImage();
        Dimension previewSize = new Dimension(280, 200);

        double widthRatio = previewSize.getWidth() / (double) originalIcon.getIconWidth();
        double heightRatio = previewSize.getHeight() / (double) originalIcon.getIconHeight();
        double ratio = Math.min(widthRatio, heightRatio);

        int newWidth = (int) (originalIcon.getIconWidth() * ratio);
        int newHeight = (int) (originalIcon.getIconHeight() * ratio);

        Image scaled = originalImage.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
        carPreviewLabel.setIcon(new ImageIcon(scaled));
    }

    private void updateTitle() {
        titleLabel.setText(getSelectedCarName());
    }

    private String getSelectedCarName() {
        return selectedCar.getClass().getSimpleName().replaceAll("(?<!^)(?=[A-Z])", " ");
    }

    private void updateStatBars() {
        statsPanel.removeAll();
        createStatBars();
        statsPanel.revalidate();
        statsPanel.repaint();
    }

    private void createStatBars() {
        createStatBar("ACCELERATION", selectedCar.getCarAcceleration());
        createStatBar("TOP SPEED", selectedCar.getCarMaxSpeed() / 100);
        createStatBar("HANDLING", selectedCar.getCarGrip() * 10);
    }

    private void createStatBar(String statName, float value) {
        JPanel statPanel = new JPanel();
        statPanel.setLayout(new BoxLayout(statPanel, BoxLayout.Y_AXIS));
        statPanel.setBackground(BACKGROUND_COLOR);
        statPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));
        statPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel nameLabel = new JLabel(statName);
        nameLabel.setForeground(TEXT_COLOR);
        nameLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));

        JPanel barPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                int width = getWidth();
                int height = getHeight();

                // Draw background bar
                g2d.setColor(STAT_BAR_EMPTY);
                g2d.fill(new RoundRectangle2D.Double(0, 0, width, height, height, height));

                // Draw filled segments - assuming max value is 10.0f
                g2d.setColor(STAT_BAR_FILLED);
                int filledWidth = (int) ((value) * width);
                g2d.fill(new RoundRectangle2D.Double(0, 0, filledWidth, height, height, height));
            }
        };
        barPanel.setPreferredSize(new Dimension(200, 8));
        barPanel.setMaximumSize(new Dimension(200, 8));
        barPanel.setBackground(SECONDARY_COLOR);

        statPanel.add(nameLabel);
        statPanel.add(Box.createVerticalStrut(5));
        statPanel.add(barPanel);
        statsPanel.add(statPanel);
    }


    private void addCarsFromImage(JPanel panel) {
        ImageIcon carsIcon = new ImageIcon("src/main/java/pl/roadflow/assets/menu/Cars.png");
        Image carsImage = carsIcon.getImage();

        int carWidth = carsImage.getWidth(null) / 4;
        int carHeight = carsImage.getHeight(null) / 4;

        Border defaultBorder = BorderFactory.createLineBorder(SECONDARY_COLOR, 2);
        Border selectedBorder = BorderFactory.createLineBorder(ACCENT_COLOR, 2);

        for (int row = 0; row < 4; row++) {
            for (int col = 0; col < 4; col++) {
                int carId = (row * 4 + col) + 1;
                JPanel carContainer = createCarContainer(carId, carWidth, carHeight, defaultBorder, selectedBorder, carsImage, col, row);
                panel.add(carContainer);
            }
        }
    }

    private JPanel createCarContainer(int carId, int carWidth, int carHeight, Border defaultBorder, Border selectedBorder, Image carsImage, int col, int row) {
        JPanel carContainer = new JPanel(new GridBagLayout());
        carContainer.setBackground(SECONDARY_COLOR);
        carContainer.setBorder(defaultBorder);

        BufferedImage carImage = new BufferedImage(carWidth, carHeight, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = carImage.createGraphics();
        g.drawImage(carsImage, 0, 0, carWidth, carHeight,
                col * carWidth, row * carHeight,
                (col + 1) * carWidth, (row + 1) * carHeight, null);
        g.dispose();

        Image scaledImage = carImage.getScaledInstance(carWidth * 2, carHeight * 2, Image.SCALE_SMOOTH);
        JLabel carLabel = new JLabel(new ImageIcon(scaledImage));
        carContainer.add(carLabel);

        carContainer.addMouseListener(new CarSelectionListener(carContainer, carId));
        return carContainer;
    }

    private class CarSelectionListener extends MouseAdapter {
        private final JPanel carContainer;
        private final int carId;

        public CarSelectionListener(JPanel carContainer, int carId) {
            this.carContainer = carContainer;
            this.carId = carId;
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            carContainer.setBorder(BorderFactory.createLineBorder(ACCENT_COLOR, 2));
            carContainer.setBackground(HOVER_COLOR);
            currentCarId = carId;
            updateSelectedCar();
        }

        @Override
        public void mouseExited(MouseEvent e) {
            carContainer.setBorder(BorderFactory.createLineBorder(SECONDARY_COLOR, 2));
            carContainer.setBackground(SECONDARY_COLOR);
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            resetCarSelections();
            carContainer.setBackground(ACCENT_COLOR);
            carContainer.setBorder(BorderFactory.createLineBorder(ACCENT_COLOR, 2));
            startGame();
        }

        private void resetCarSelections() {
            for (Component comp : carContainer.getParent().getComponents()) {
                if (comp instanceof JPanel) {
                    comp.setBackground(SECONDARY_COLOR);
                    ((JPanel) comp).setBorder(BorderFactory.createLineBorder(SECONDARY_COLOR, 2));
                }
            }
        }
    }

    public Car createCarBasedOnId(int id, String color) {
        return switch (id) {
            case 1 -> new MiniVan(color);
            case 2 -> new RaceCar(color);
            case 3 -> new Hatchback(color);
            case 4 -> new FamilySedan(color);
            case 5 -> new Sedan(color);
            case 6 -> new PoliceCar(color);
            case 7 -> new CompactCar(color);
            case 8 -> new Taxi(color);
            case 9 -> new Tank(color);
            case 10 -> new Suv(color);
            case 11 -> new Pickup(color);
            case 12 -> new SportCar(color);
            case 13 -> new Truck(color);
            case 14 -> new MuscleCar(color);
            case 15 -> new Cabrio(color);
            case 16 -> new Lowrider(color);
            default -> null;
        };
    }

    public void startGame() {
        if (selectedCar != null) {
            GameScreen gameScreen = new GameScreen();
            gameScreen.setCar(selectedCar);
            dispose();
        }
    }
}