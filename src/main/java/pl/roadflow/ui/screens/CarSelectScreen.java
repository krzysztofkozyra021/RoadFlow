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

    private Car selectedCar = new SportCar();

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

        // Left container
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
        gbc.insets = new Insets(0, 0, 0, 0);
        leftContainer.add(carsGrid, gbc);

        mainPanel.add(leftContainer);

        JPanel rightPanel = new JPanel(new GridBagLayout());
        rightPanel.setBackground(BACKGROUND_COLOR);
        rightPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));


        JPanel contentPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 0));
        contentPanel.setBackground(BACKGROUND_COLOR);

        // Smaller preview panel
        JPanel previewPanel = new JPanel(new BorderLayout());
        previewPanel.setBackground(BACKGROUND_COLOR);
        previewPanel.setPreferredSize(new Dimension(250, 250));
        carPreviewLabel = new JLabel("", SwingConstants.CENTER);
        previewPanel.add(carPreviewLabel, BorderLayout.CENTER);

        // Centered stats panel
        statsPanel = new JPanel();
        statsPanel.setLayout(new BoxLayout(statsPanel, BoxLayout.Y_AXIS));
        statsPanel.setBackground(BACKGROUND_COLOR);
        statsPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        createStatBars();

        contentPanel.add(previewPanel);
        contentPanel.add(statsPanel);
        rightPanel.add(contentPanel);

        mainPanel.add(rightPanel);
        add(mainPanel);
    }

    private void createStatBars() {
        createStatBar("ACCELERATION", selectedCar.getCarAcceleration());
        createStatBar("TOP SPEED", selectedCar.getCarMaxSpeed() / 100);
        createStatBar("HANDLING", selectedCar.getCarGrip() * 10);
    }

    private void updateTitle() {
        titleLabel.setText(getSelectedCarName());
    }


    private String getSelectedCarName() {
        String name = selectedCar.getClass().getSimpleName();
        return name.replaceAll("(?<!^)(?=[A-Z])", " ");
    }

    private void updateStatBars(){
        statsPanel.removeAll();
        createStatBars();
        statsPanel.revalidate();
        statsPanel.repaint();
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
                JPanel carContainer = new JPanel(new GridBagLayout());
                carContainer.setBackground(SECONDARY_COLOR);
                carContainer.setBorder(defaultBorder);

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
                        carContainer.setBackground(HOVER_COLOR);
                        selectedCar = createCarBasedOnId(carId);
                        updateStatBars();
                        updateTitle();
                        carPreviewLabel.setIcon(new ImageIcon(carImage.getScaledInstance(300, 300, Image.SCALE_SMOOTH)));
                    }

                    @Override
                    public void mouseExited(MouseEvent e) {
                        carContainer.setBorder(defaultBorder);
                        carContainer.setBackground(SECONDARY_COLOR);
                    }

                    @Override
                    public void mouseClicked(MouseEvent e) {
                        for (Component comp : panel.getComponents()) {
                            if (comp instanceof JPanel) {
                                comp.setBackground(SECONDARY_COLOR);
                                ((JPanel) comp).setBorder(defaultBorder);
                            }
                        }
                        carContainer.setBackground(ACCENT_COLOR);
                        carContainer.setBorder(selectedBorder);
                        startGame();
                    }
                });

                panel.add(carContainer);
            }
        }
    }

    public Car createCarBasedOnId(int id){
        return switch (id) {
            case 1 -> new MiniVan();
            case 2 -> new RaceCar();
            case 3 -> new Hatchback();
            case 4 -> new FamilySedan();
            case 5 -> new Sedan();
            case 6 -> new PoliceCar();
            case 7 -> new CompactCar();
            case 8 -> new Taxi();
            case 9 -> new Tank();
            case 10 -> new Suv();
            case 11 -> new Pickup();
            case 12 -> new SportCar();
            case 13 -> new Truck();
            case 14 -> new MuscleCar();
            case 15 -> new Cabrio();
            case 16 -> new Lowrider();
            default -> null;
        };
    }

    public void startGame(){
        if(selectedCar != null){
            GameScreen gameScreen = new GameScreen();
            gameScreen.setCar(selectedCar);
            dispose();
        }
    }
}