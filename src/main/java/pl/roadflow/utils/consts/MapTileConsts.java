package src.main.java.pl.roadflow.utils.consts;

import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Class that loads images from assets folder during initialization.
 */
public class MapTileConsts {

    private static final String ASSET_FOLDER_PATH = "src/main/java/pl/roadflow/assets/de_dust/";
    private HashMap<Character, ImageIcon> mapTileIcons;
    private ArrayList<Character> obstacleTiles;
    public MapTileConsts() {
        mapTileIcons = new HashMap<>();
        obstacleTiles = new ArrayList<>();
        setMapTileIcons();
        setObstacleTiles();
    }

    public HashMap<Character, ImageIcon> getMapTileIcons() {
        return mapTileIcons;
    }

    public ArrayList<Character> getObstacleTiles() {
        return obstacleTiles;
    }

    public void setObstacleTiles() {
        for (Character tile : mapTileIcons.keySet()) {
            if (!tile.equals('1') &&
                    !tile.equals('2') &&
                    !tile.equals('3') &&
                    !tile.equals('4') &&
                    !tile.equals('D') &&
                    !tile.equals('G') &&
                    !tile.equals('P') &&
                    !tile.equals('S'))
            {
                obstacleTiles.add(tile);
            }
        }
    }

    private void setMapTileIcons() {
        try {
            mapTileIcons.put('1', new ImageIcon(ASSET_FOLDER_PATH + "1.png")); // Corner NE
            mapTileIcons.put('2', new ImageIcon(ASSET_FOLDER_PATH + "2.png")); // Corner SE
            mapTileIcons.put('3', new ImageIcon(ASSET_FOLDER_PATH + "3.png")); // Corner SW
            mapTileIcons.put('4', new ImageIcon(ASSET_FOLDER_PATH + "4.png")); // Corner NW
            mapTileIcons.put('D', new ImageIcon(ASSET_FOLDER_PATH + "D.png")); // Road horizontal
            mapTileIcons.put('G', new ImageIcon(ASSET_FOLDER_PATH + "G.png")); // Ground
            mapTileIcons.put('H', new ImageIcon(ASSET_FOLDER_PATH + "H.png")); // House (obstacle)
            mapTileIcons.put('P', new ImageIcon(ASSET_FOLDER_PATH + "P.png")); // Road vertical
            mapTileIcons.put('S', new ImageIcon(ASSET_FOLDER_PATH + "S.png")); // Start Line
            mapTileIcons.put('T', new ImageIcon(ASSET_FOLDER_PATH + "T.png")); // Tree (obstacle)
            mapTileIcons.put('C', new ImageIcon(ASSET_FOLDER_PATH + "C.png")); // Fan sector 1 (obstacle)
            mapTileIcons.put('V', new ImageIcon(ASSET_FOLDER_PATH + "V.png")); // Fan sector 2 (obstacle)
            mapTileIcons.put('X', new ImageIcon(ASSET_FOLDER_PATH + "X.png")); // Fan sector 3 (obstacle)
            mapTileIcons.put('Z', new ImageIcon(ASSET_FOLDER_PATH + "Z.png")); // Fan sector 4 (obstacle)
        } catch (Exception e) {
            throw new RuntimeException("Cant load image tiles: " + e.getMessage());
        }
    }
}