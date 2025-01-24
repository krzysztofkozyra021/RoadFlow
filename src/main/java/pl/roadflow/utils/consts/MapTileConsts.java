package src.main.java.pl.roadflow.utils.consts;

import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Class that loads images from assets folder during initialization.
 */
public class MapTileConsts {

    private static final String ASSET_TILES_FOLDER_PATH = "src/main/java/pl/roadflow/assets/tiles/";
    private static final String ASSET_OBJECTS_AND_DECORATIONS_FOLDER_PATH = "src/main/java/pl/roadflow/assets/objectsAndDecoration/";
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
                    !tile.equals(8) &&     // S - start line
                    !tile.equals(9) &&     // S - start line
                    !tile.equals(19) &&     // S - start line
                    !tile.equals(20) &&     // S - start line
                    !tile.equals(21) &&     // S - start line
                    !tile.equals(22) &&     // S - start line
                    !tile.equals(18))
            {
                obstacleTiles.add(tile);
            }
        }
    }

    private void setMapTileIcons() {
        try {
            // TILES ICONS
            mapTileIcons.put(1, new ImageIcon(ASSET_TILES_FOLDER_PATH + "grass.png"));
            mapTileIcons.put(2, new ImageIcon(ASSET_TILES_FOLDER_PATH + "track_road_curve3_00_1.png"));
            mapTileIcons.put(3, new ImageIcon(ASSET_TILES_FOLDER_PATH + "track_road_curve3_00_2.png"));
            mapTileIcons.put(4, new ImageIcon(ASSET_TILES_FOLDER_PATH + "track_road_curve3_00_3.png"));
            mapTileIcons.put(5, new ImageIcon(ASSET_TILES_FOLDER_PATH + "track_road_curve3_00_4.png"));
            mapTileIcons.put(6, new ImageIcon(ASSET_TILES_FOLDER_PATH + "track_road_curve3_00_5.png"));
            mapTileIcons.put(7, new ImageIcon(ASSET_TILES_FOLDER_PATH + "track_road_curve3_00_6.png"));
            mapTileIcons.put(8, new ImageIcon(ASSET_TILES_FOLDER_PATH + "track_road_curve3_00_7.png"));
            mapTileIcons.put(9, new ImageIcon(ASSET_TILES_FOLDER_PATH + "track_road_curve3_00_8.png"));
            mapTileIcons.put(10, new ImageIcon(ASSET_TILES_FOLDER_PATH + "track_road_curve1_01.png"));
            mapTileIcons.put(11, new ImageIcon(ASSET_TILES_FOLDER_PATH + "track_road_curve1_02.png"));
            mapTileIcons.put(12, new ImageIcon(ASSET_TILES_FOLDER_PATH + "track_road_curve1_03.png"));
            mapTileIcons.put(13, new ImageIcon(ASSET_TILES_FOLDER_PATH + "track_road_curve2_00.png"));
            mapTileIcons.put(14, new ImageIcon(ASSET_TILES_FOLDER_PATH + "track_road_curve2_01.png"));
            mapTileIcons.put(15, new ImageIcon(ASSET_TILES_FOLDER_PATH + "A.png"));
            mapTileIcons.put(16, new ImageIcon(ASSET_TILES_FOLDER_PATH + "track_road_curve3_01.png"));
            mapTileIcons.put(17, new ImageIcon(ASSET_TILES_FOLDER_PATH + "track_road_curve4.png"));
            mapTileIcons.put(18, new ImageIcon(ASSET_TILES_FOLDER_PATH + "track_road_fill.png"));
            mapTileIcons.put(19, new ImageIcon(ASSET_TILES_FOLDER_PATH + "track_road_side_left.png"));
            mapTileIcons.put(20, new ImageIcon(ASSET_TILES_FOLDER_PATH + "track_road_side_right.png"));
            mapTileIcons.put(21, new ImageIcon(ASSET_TILES_FOLDER_PATH + "track_road_top.png"));
            mapTileIcons.put(22, new ImageIcon(ASSET_TILES_FOLDER_PATH + "track_road_down.png"));
            mapTileIcons.put(23, new ImageIcon(ASSET_TILES_FOLDER_PATH + "track_road_curve3_01_1.png"));
            mapTileIcons.put(24, new ImageIcon(ASSET_TILES_FOLDER_PATH + "track_road_curve3_01_2.png"));
            mapTileIcons.put(25, new ImageIcon(ASSET_TILES_FOLDER_PATH + "track_road_curve3_01_3.png"));
            mapTileIcons.put(26, new ImageIcon(ASSET_TILES_FOLDER_PATH + "track_road_curve3_01_4.png"));
            mapTileIcons.put(27, new ImageIcon(ASSET_TILES_FOLDER_PATH + "track_water_curve1_00.png"));
            mapTileIcons.put(28, new ImageIcon(ASSET_TILES_FOLDER_PATH + "track_water_curve1_01.png"));
            mapTileIcons.put(29, new ImageIcon(ASSET_TILES_FOLDER_PATH + "track_water_curve2.png"));

            // OBJECTS AND DECORATIONS ICONS
            mapTileIcons.put(30, new ImageIcon(ASSET_OBJECTS_AND_DECORATIONS_FOLDER_PATH + "barrel_fallen.png"));
            mapTileIcons.put(31, new ImageIcon(ASSET_OBJECTS_AND_DECORATIONS_FOLDER_PATH + "barrel_standup.png"));
            mapTileIcons.put(32, new ImageIcon(ASSET_OBJECTS_AND_DECORATIONS_FOLDER_PATH + "block.png"));
            mapTileIcons.put(33, new ImageIcon(ASSET_OBJECTS_AND_DECORATIONS_FOLDER_PATH + "cone.png"));
            mapTileIcons.put(34, new ImageIcon(ASSET_OBJECTS_AND_DECORATIONS_FOLDER_PATH + "cone_standup.png"));
            mapTileIcons.put(35, new ImageIcon(ASSET_OBJECTS_AND_DECORATIONS_FOLDER_PATH + "goal_standup.png"));
            mapTileIcons.put(36, new ImageIcon(ASSET_OBJECTS_AND_DECORATIONS_FOLDER_PATH + "grassdecoration_01.png"));
            mapTileIcons.put(37, new ImageIcon(ASSET_OBJECTS_AND_DECORATIONS_FOLDER_PATH + "grassdecoration_02.png"));
            mapTileIcons.put(38, new ImageIcon(ASSET_OBJECTS_AND_DECORATIONS_FOLDER_PATH + "grassdecoration_03.png"));
            mapTileIcons.put(39, new ImageIcon(ASSET_OBJECTS_AND_DECORATIONS_FOLDER_PATH + "grassdecoration_04.png"));
            mapTileIcons.put(40, new ImageIcon(ASSET_OBJECTS_AND_DECORATIONS_FOLDER_PATH + "grassdecoration_05.png"));
            mapTileIcons.put(41, new ImageIcon(ASSET_OBJECTS_AND_DECORATIONS_FOLDER_PATH + "grassdecoration_06.png"));
            mapTileIcons.put(42, new ImageIcon(ASSET_OBJECTS_AND_DECORATIONS_FOLDER_PATH + "grassdecoration_07.png"));
            mapTileIcons.put(43, new ImageIcon(ASSET_OBJECTS_AND_DECORATIONS_FOLDER_PATH + "grassdecoration_08.png"));
            mapTileIcons.put(44, new ImageIcon(ASSET_OBJECTS_AND_DECORATIONS_FOLDER_PATH + "grassdecoration_09.png"));
            mapTileIcons.put(45, new ImageIcon(ASSET_OBJECTS_AND_DECORATIONS_FOLDER_PATH + "grassdecoration_10.png"));
            mapTileIcons.put(46, new ImageIcon(ASSET_OBJECTS_AND_DECORATIONS_FOLDER_PATH + "grassdecoration_11.png"));
            mapTileIcons.put(47, new ImageIcon(ASSET_OBJECTS_AND_DECORATIONS_FOLDER_PATH + "grassdecoration_12.png"));
            mapTileIcons.put(48, new ImageIcon(ASSET_OBJECTS_AND_DECORATIONS_FOLDER_PATH + "road_arrow.png"));
            mapTileIcons.put(49, new ImageIcon(ASSET_OBJECTS_AND_DECORATIONS_FOLDER_PATH + "road_triangle.png"));
            mapTileIcons.put(50, new ImageIcon(ASSET_OBJECTS_AND_DECORATIONS_FOLDER_PATH + "startarea.png"));
            mapTileIcons.put(51, new ImageIcon(ASSET_OBJECTS_AND_DECORATIONS_FOLDER_PATH + "track_tileset_tiles_35.png"));
            mapTileIcons.put(52, new ImageIcon(ASSET_OBJECTS_AND_DECORATIONS_FOLDER_PATH + "tree.png"));
            mapTileIcons.put(53, new ImageIcon(ASSET_OBJECTS_AND_DECORATIONS_FOLDER_PATH + "tree_0001.png"));
            mapTileIcons.put(54, new ImageIcon(ASSET_OBJECTS_AND_DECORATIONS_FOLDER_PATH + "tree_0010.png"));
            mapTileIcons.put(55, new ImageIcon(ASSET_OBJECTS_AND_DECORATIONS_FOLDER_PATH + "tree_0100.png"));
            mapTileIcons.put(56, new ImageIcon(ASSET_OBJECTS_AND_DECORATIONS_FOLDER_PATH + "tree_1000.png"));
            mapTileIcons.put(57, new ImageIcon(ASSET_OBJECTS_AND_DECORATIONS_FOLDER_PATH + "waterstone_01.png"));
            mapTileIcons.put(58, new ImageIcon(ASSET_OBJECTS_AND_DECORATIONS_FOLDER_PATH + "waterstone_02.png"));
            mapTileIcons.put(59, new ImageIcon(ASSET_OBJECTS_AND_DECORATIONS_FOLDER_PATH + "waterstone_03.png"));


        } catch (Exception e) {
            throw new RuntimeException("Cant load image tiles: " + e.getMessage());
        }
    }
}