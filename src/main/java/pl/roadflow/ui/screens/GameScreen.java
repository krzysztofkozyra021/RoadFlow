package src.main.java.pl.roadflow.ui.screens;

import src.main.java.pl.roadflow.core.mechanics.car.Car;
import src.main.java.pl.roadflow.core.mechanics.car.carTypes.SportCar;
import src.main.java.pl.roadflow.utils.consts.GameConsts;
import src.main.java.pl.roadflow.utils.consts.MapTileConsts;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.geom.Point2D;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class GameScreen extends JFrame {
    MapTileConsts mapTileConsts = new MapTileConsts();
    GameConsts gameConsts = new GameConsts();
    public final int WIDTH = 60;
    public final int HEIGHT = 33;
    public final int TILE_SIZE = 32;
    private static Rectangle frameBounds;
    public ArrayList<Character> mapData;
    public static ArrayList<Rectangle> mapObstacles;
    private Car car;
    private Timer gameTimer;
    Point2D startTilePosition = new Point();
    public final String TITLE = "Road Flow";

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

        // Spawn Car at the S (Start) tile
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

        gameTimer = new Timer(16, e -> {
            car.update();
            repaint();
        });
        gameTimer.start();

        setVisible(true);
        setFocusable(true);
        requestFocusInWindow();
    }

    private void getStartTilePosition() {
        for (int y = 0; y < HEIGHT; y++) {
            for (int x = 0; x < WIDTH; x++) {
                int index = y * WIDTH + x;
                if (index < mapData.size() && mapData.get(index) == 'S') {
                    startTilePosition.setLocation(x * TILE_SIZE, y * TILE_SIZE);
                    return;
                }
            }
        }
    }

    @Override
    public void paint(Graphics g) {
        Image offscreen = createImage(getWidth(), getHeight());
        Graphics2D offgc = (Graphics2D) offscreen.getGraphics();

        offgc.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        // Draw map
        for (int y = 0; y < HEIGHT; y++) {
            for (int x = 0; x < WIDTH; x++) {
                int index = y * WIDTH + x;
                if (index < mapData.size()) {
                    char tileChar = mapData.get(index);
                    ImageIcon tileIcon = mapTileConsts.getMapTileIcons().get(tileChar);
                    if (tileIcon != null) {
                        offgc.drawImage(
                                tileIcon.getImage(),
                                x * TILE_SIZE,
                                y * TILE_SIZE,
                                TILE_SIZE,
                                TILE_SIZE,
                                null
                        );
                    }
                }
            }
        }

        if(GameConsts.DEBUG_MODE == 1){
            offgc.setColor(Color.BLUE);
            for (Rectangle obstacle : mapObstacles) {
                offgc.draw(obstacle);
            }
        }

        car.draw(offgc);
        car.setCurrentPositionTile(getTileAtPosition((int) car.getX(), (int) car.getY()));
        g.drawImage(offscreen, 0, 0, this);
    }

    public void loadMapData() {
        try (BufferedReader reader = new BufferedReader(new FileReader(gameConsts.MAP_FILE_PATH))) {
            int character;
            while ((character = reader.read()) != -1) {
                // Skip newline and carriage return characters
                if (character != '\n' && character != '\r') {
                    mapData.add((char) character);
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

    public char getTileAtPosition(int x, int y) {
        int tileX = x / TILE_SIZE;
        int tileY = y / TILE_SIZE;
        int index = tileY * WIDTH + tileX;

        if (index >= 0 && index < mapData.size()) {
            return mapData.get(index);
        }

        return 'G';
    }


    public static ArrayList<Rectangle> getMapObstacles() {
        return mapObstacles;
    }

    public static Rectangle getFrameBounds() {
        return frameBounds;
    }
}