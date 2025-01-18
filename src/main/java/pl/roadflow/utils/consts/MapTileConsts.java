package src.main.java.pl.roadflow.utils.consts;

import javax.swing.*;
import java.util.HashMap;

/**
 * Class that loads images from assets folder during initialization.
 */
public class MapTileConsts {

    private static final String ASSET_FOLDER_PATH = "src/main/java/pl/roadflow/assets/de_snow/";
    private HashMap<Character, ImageIcon> mapTileIcons;

    public MapTileConsts() {
        mapTileIcons = new HashMap<>();
        setMapTileIcons();
    }

    public HashMap<Character, ImageIcon> getMapTileIcons() {
        return mapTileIcons;
    }

    private void setMapTileIcons() {
        try {
            mapTileIcons.put('1', new ImageIcon(ASSET_FOLDER_PATH + "1.png"));
            mapTileIcons.put('2', new ImageIcon(ASSET_FOLDER_PATH + "2.png"));
            mapTileIcons.put('3', new ImageIcon(ASSET_FOLDER_PATH + "3.png"));
            mapTileIcons.put('4', new ImageIcon(ASSET_FOLDER_PATH + "4.png"));
            mapTileIcons.put('C', new ImageIcon(ASSET_FOLDER_PATH + "C.png"));
            mapTileIcons.put('D', new ImageIcon(ASSET_FOLDER_PATH + "D.png"));
            mapTileIcons.put('G', new ImageIcon(ASSET_FOLDER_PATH + "G.png"));
            mapTileIcons.put('H', new ImageIcon(ASSET_FOLDER_PATH + "H.png"));
            mapTileIcons.put('P', new ImageIcon(ASSET_FOLDER_PATH + "P.png"));
            mapTileIcons.put('S', new ImageIcon(ASSET_FOLDER_PATH + "S.png"));
            mapTileIcons.put('T', new ImageIcon(ASSET_FOLDER_PATH + "T.png"));
            mapTileIcons.put('V', new ImageIcon(ASSET_FOLDER_PATH + "V.png"));
            mapTileIcons.put('X', new ImageIcon(ASSET_FOLDER_PATH + "X.png"));
            mapTileIcons.put('Z', new ImageIcon(ASSET_FOLDER_PATH + "Z.png"));
        } catch (Exception e) {
            throw new RuntimeException("Cant load image tiles: " + e.getMessage());
        }
    }
}