package src.main.java.pl.roadflow.ui.screens;

import src.main.java.pl.roadflow.utils.consts.GameConsts;
import src.main.java.pl.roadflow.utils.consts.MapTileConsts;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class GameScreen extends JFrame {

    MapTileConsts mapTileConsts = new MapTileConsts();
    GameConsts gameConsts = new GameConsts();
    public final int WIDTH = 60; // 1920 / 32 (Tile size) = 60
    public final int HEIGHT = 33; // 1080 / 32 (Tile size) = 33.75
    public final int TILE_SIZE = 32;
    public ArrayList<String> mapData;

    public final String TITLE = "Road Flow";

    public GameScreen() {
        setTitle(TITLE);
        setSize(WIDTH * TILE_SIZE, HEIGHT * TILE_SIZE);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);

        mapData = new ArrayList<>();
        loadMapData();

        System.out.println(mapData);
    }

    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        for (int i = 0; i < mapData.size(); i++) {
            for (int j = 0; j < mapData.get(i).length(); j++) {
                g2d.drawImage(mapTileConsts.getMapTileIcons().get(mapData.get(i).charAt(j)).getImage(), j * TILE_SIZE, i * TILE_SIZE, null);
            }
        }
    }

    public void loadMapData() {
        try{
            BufferedReader reader = new BufferedReader(new FileReader(gameConsts.MAP_FILE_PATH));
            String line;
            while ((line = reader.readLine()) != null) {
                mapData.add(line);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
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

    public boolean isRoadTile(int x, int y) {
        char tile = getTileAtPosition(x, y);
        return tile == 'D' || tile == 'P' ||
                tile == '1' || tile == '2' || tile == '3' || tile == '4';
    }

    public Point getTileCenter(int x, int y) {
        int tileX = (x / TILE_SIZE) * TILE_SIZE + TILE_SIZE / 2;
        int tileY = (y / TILE_SIZE) * TILE_SIZE + TILE_SIZE / 2;
        return new Point(tileX, tileY);
    }


}
