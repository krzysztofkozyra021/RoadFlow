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
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class GameScreen extends JFrame {
    MapTileConsts mapTileConsts = new MapTileConsts();
    GameConsts gameConsts = new GameConsts();
    public final int WIDTH = 60;
    public final int HEIGHT = 33;
    public final int TILE_SIZE = 32;
    public ArrayList<String> mapData;
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

        mapData = new ArrayList<>();
        loadMapData();
        getStartTilePosition();

        // Spawn Car at the S (Start) tile
        car = new SportCar((int) startTilePosition.getX(), (int) startTilePosition.getY());
        addObstaclesToCar();

        // Add key listener to the window
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

        // Create time to update the game
        gameTimer = new Timer(16, e -> {
            car.update();
            repaint();
        });
        gameTimer.start();

        setVisible(true);
        // Make sure that the window can handle key events
        setFocusable(true);
        requestFocusInWindow();
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
    }

    private void getStartTilePosition() {
        for (int i = 0; i < mapData.size(); i++) {
            for (int j = 0; j < mapData.get(i).length(); j++) {
                if (mapData.get(i).charAt(j) == 'S') {
                    startTilePosition.setLocation(j * TILE_SIZE, i * TILE_SIZE);
                    return;
                }
            }
        }
    }

    private void addObstaclesToCar() {
        for (int i = 0; i < mapData.size(); i++) {
            for (int j = 0; j < mapData.get(i).length(); j++) {
                char tile = mapData.get(i).charAt(j);
                if(mapTileConsts.getObstacleTiles().contains(tile)){
                    Rectangle obstacle = new Rectangle(
                            j * TILE_SIZE,      // x position
                            i * TILE_SIZE,      // y position
                            TILE_SIZE,          // width
                            TILE_SIZE           // height
                    );
                    car.addObstacle(obstacle);
                }
            }
        }
    }

    @Override
    public void paint(Graphics g) {

        Image offscreen = createImage(getWidth(), getHeight());
        Graphics2D offgc = (Graphics2D) offscreen.getGraphics();

        // Enable antialiasing
        offgc.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        // Draw map
        for (int i = 0; i < mapData.size(); i++) {
            for (int j = 0; j < mapData.get(i).length(); j++) {
                offgc.drawImage(
                        mapTileConsts.getMapTileIcons().get(mapData.get(i).charAt(j)).getImage(),
                        j * TILE_SIZE,
                        i * TILE_SIZE,
                        null
                );
            }
        }

        car.draw(offgc);
        car.setCurrentPositionTile(getTileAtPosition((int) car.getX(), (int) car.getY()));
        // Move buffer to the screen
        g.drawImage(offscreen, 0, 0, this);
    }

    public void loadMapData() {
        try{
            BufferedReader reader = new BufferedReader(new FileReader(gameConsts.MAP_FILE_PATH));
            String line;
            while ((line = reader.readLine()) != null) {
                mapData.add(line);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public char getTileAtPosition(int x, int y) {
        int tileX = x / TILE_SIZE;
        int tileY = y / TILE_SIZE;

        if (tileY >= 0 && tileY < mapData.size() &&
                tileX >= 0 && tileX < mapData.get(tileY).length()) {
            return mapData.get(tileY).charAt(tileX);
        }

        return 'G';
    }



    public Point getTileCenter(int x, int y) {
        int tileX = (x / TILE_SIZE) * TILE_SIZE + TILE_SIZE / 2;
        int tileY = (y / TILE_SIZE) * TILE_SIZE + TILE_SIZE / 2;
        return new Point(tileX, tileY);
    }
}
