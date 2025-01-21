package src.main.java.pl.roadflow.utils.consts;

import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Class that loads images from assets folder during initialization.
 */
public class MapTileConsts {

    private static final String ASSET_FOLDER_PATH = "src/main/java/pl/roadflow/assets/de_dust/";
    private HashMap<Integer, ImageIcon> mapTileIcons;
    private ArrayList<Integer> obstacleTiles;

    public MapTileConsts() {
        mapTileIcons = new HashMap<>();
        obstacleTiles = new ArrayList<>();
        setMapTileIcons();
        setObstacleTiles();
    }

    public HashMap<Integer, ImageIcon> getMapTileIcons() {
        return mapTileIcons;
    }

    public ArrayList<Integer> getObstacleTiles() {
        return obstacleTiles;
    }

    public void setObstacleTiles() {
        for (Integer tile : mapTileIcons.keySet()) {
            if (!tile.equals(1) &&
                    !tile.equals(2) &&
                    !tile.equals(3) &&
                    !tile.equals(4) &&
                    !tile.equals(5) &&    // D - road horizontal
                    !tile.equals(6) &&    // G - ground
                    !tile.equals(7) &&    // P - road vertical
                    !tile.equals(8))      // S - start line
            {
                obstacleTiles.add(tile);
            }
        }
    }

    private void setMapTileIcons() {
        try {
            mapTileIcons.put(1, new ImageIcon(ASSET_FOLDER_PATH + "corner_ne.png")); // Corner NE
            mapTileIcons.put(2, new ImageIcon(ASSET_FOLDER_PATH + "corner_se.png")); // Corner SE
            mapTileIcons.put(3, new ImageIcon(ASSET_FOLDER_PATH + "corner_sw.png")); // Corner SW
            mapTileIcons.put(4, new ImageIcon(ASSET_FOLDER_PATH + "corner_nw.png")); // Corner NW
            mapTileIcons.put(5, new ImageIcon(ASSET_FOLDER_PATH + "road_horizontal.png")); // Road horizontal
            mapTileIcons.put(6, new ImageIcon(ASSET_FOLDER_PATH + "ground.png")); // Ground
            mapTileIcons.put(9, new ImageIcon(ASSET_FOLDER_PATH + "house.png")); // House (obstacle)
            mapTileIcons.put(7, new ImageIcon(ASSET_FOLDER_PATH + "road_vertical.png")); // Road vertical
            mapTileIcons.put(8, new ImageIcon(ASSET_FOLDER_PATH + "start_line.png")); // Start Line
            mapTileIcons.put(10, new ImageIcon(ASSET_FOLDER_PATH + "tree.png")); // Tree (obstacle)
            mapTileIcons.put(11, new ImageIcon(ASSET_FOLDER_PATH + "fan_sector1.png")); // Fan sector 1 (obstacle)
            mapTileIcons.put(12, new ImageIcon(ASSET_FOLDER_PATH + "fan_sector2.png")); // Fan sector 2 (obstacle)
            mapTileIcons.put(13, new ImageIcon(ASSET_FOLDER_PATH + "fan_sector3.png")); // Fan sector 3 (obstacle)
            mapTileIcons.put(14, new ImageIcon(ASSET_FOLDER_PATH + "fan_sector4.png")); // Fan sector 4 (obstacle)
        } catch (Exception e) {
            throw new RuntimeException("Cant load image tiles: " + e.getMessage());
        }
    }
}