package src.main.java.pl.roadflow.ui.screens;

import src.main.java.pl.roadflow.core.mechanics.car.Car;
import src.main.java.pl.roadflow.core.mechanics.car.carTypes.SportCar;
import src.main.java.pl.roadflow.utils.consts.GameConsts;
import src.main.java.pl.roadflow.utils.consts.MapTileConsts;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.geom.Point2D;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class GameScreen extends JFrame {
    MapTileConsts mapTileConsts = new MapTileConsts();
    GameConsts gameConsts = new GameConsts();
    public final int WIDTH = 60;
    public final int HEIGHT = 33;
    public final int TILE_SIZE = 32;
    private static Rectangle frameBounds;
    public ArrayList<Integer> mapData;
    public static ArrayList<Rectangle> mapObstacles;
    private Car car;
    private Timer gameTimer;
    Point2D startTilePosition = new Point();
    public final String TITLE = "Road Flow";

    // Buffering components
    private Image offscreenBuffer;
    private Graphics2D offscreenGraphics;

    // Cache for tile images
    private final Map<Integer, Image> tileImageCache = new HashMap<>();

    public GameScreen() {
        setTitle(TITLE);
        setSize(WIDTH * TILE_SIZE, HEIGHT * TILE_SIZE);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        frameBounds = new Rectangle(0, 0, WIDTH * TILE_SIZE, HEIGHT * TILE_SIZE);
        mapData = new ArrayList<>();
        mapObstacles = new ArrayList<>();

        loadMapData();
        getStartTilePosition();

        car = new SportCar((int) startTilePosition.getX(), (int) startTilePosition.getY());

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                car.handleKeyPressed(e.getKeyCode());
            }

            @Override
            public void keyReleased(KeyEvent e) {
                car.handleKeyReleased(e.getKeyCode());
            }
        });

        // Initialize graphics after component is displayed
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentShown(ComponentEvent e) {
                initializeGraphics();
                initializeTileCache();
                removeComponentListener(this);
            }
        });

        gameTimer = new Timer(16, e -> {
            updateGame();
            repaint();
        });

        setVisible(true);
        setFocusable(true);
        requestFocusInWindow();

        // Start timer after everything is initialized
        gameTimer.start();
    }

    private void initializeGraphics() {
        if (offscreenBuffer == null) {
            offscreenBuffer = createImage(getWidth(), getHeight());
            if (offscreenBuffer != null) {
                offscreenGraphics = (Graphics2D) offscreenBuffer.getGraphics();
                offscreenGraphics.setRenderingHint(
                        RenderingHints.KEY_ANTIALIASING,
                        RenderingHints.VALUE_ANTIALIAS_ON
                );
            }
        }
    }

    private void getStartTilePosition() {
        for (int y = 0; y < HEIGHT; y++) {
            for (int x = 0; x < WIDTH; x++) {
                int index = y * WIDTH + x;
                if (index < mapData.size() && mapData.get(index) == 8) {
                    startTilePosition.setLocation(x * TILE_SIZE, y * TILE_SIZE);
                    return;
                }
            }
        }
    }

    private void initializeTileCache() {
        MapTileConsts mapTileConsts = new MapTileConsts();
        for (Map.Entry<Integer, ImageIcon> entry : mapTileConsts.getMapTileIcons().entrySet()) {
            Image scaledImage = entry.getValue().getImage()
                    .getScaledInstance(TILE_SIZE, TILE_SIZE, Image.SCALE_SMOOTH);
            tileImageCache.put(entry.getKey(), scaledImage);
        }
    }

    private void updateGame() {
        car.update();
        car.setCurrentPositionTile(getTileAtPosition((int) car.getX(), (int) car.getY()));
    }


    @Override
    public void paint(Graphics g) {
        if (offscreenBuffer == null) {
            initializeGraphics();
            return;
        }

        // Clear the buffer
        offscreenGraphics.setColor(new Color(46, 72, 24));
        offscreenGraphics.fillRect(0, 0, getWidth(), getHeight());

        // Draw map using cached images
        for (int y = 0; y < HEIGHT; y++) {
            for (int x = 0; x < WIDTH; x++) {
                int index = y * WIDTH + x;
                if (index < mapData.size()) {
                    int tileNumber = mapData.get(index);
                    Image tileImage = tileImageCache.get(tileNumber);
                    if (tileImage != null) {
                        offscreenGraphics.drawImage(
                                tileImage,
                                x * TILE_SIZE,
                                y * TILE_SIZE,
                                null
                        );
                    }
                }
            }
        }

        if (GameConsts.DEBUG_MODE == 1) {
            offscreenGraphics.setColor(Color.BLUE);
            for (Rectangle obstacle : mapObstacles) {
                offscreenGraphics.draw(obstacle);
            }
        }

        car.draw(offscreenGraphics);

        // Draw the buffer to the screen
        g.drawImage(offscreenBuffer, 0, 0, this);
    }

    public void loadMapData() {
        try (BufferedReader reader = new BufferedReader(new FileReader(gameConsts.MAP_FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] tiles = line.split(",");
                for (String tile : tiles) {
                    tile = tile.trim();
                    if (!tile.isEmpty()) {
                        mapData.add(Integer.parseInt(tile));
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // Add obstacles
        for (int y = 0; y < HEIGHT; y++) {
            for (int x = 0; x < WIDTH; x++) {
                int index = y * WIDTH + x;
                if (index < mapData.size() && mapTileConsts.getObstacleTiles().contains(mapData.get(index))) {
                    Rectangle obstacle = new Rectangle(
                            x * TILE_SIZE,
                            y * TILE_SIZE,
                            TILE_SIZE,
                            TILE_SIZE
                    );
                    mapObstacles.add(obstacle);
                }
            }
        }
    }

    public Integer getTileAtPosition(int x, int y) {
        int tileX = x / TILE_SIZE;
        int tileY = y / TILE_SIZE;
        int index = tileY * WIDTH + tileX;

        if (index >= 0 && index < mapData.size()) {
            return mapData.get(index);
        }

        return 6;
    }


    public static ArrayList<Rectangle> getMapObstacles() {
        return mapObstacles;
    }

    public static Rectangle getFrameBounds() {
        return frameBounds;
    }
}