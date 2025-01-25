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
                    !tile.equals(5) &&
                    !tile.equals(6) &&
                    !tile.equals(7) &&
                    !tile.equals(8) &&
                    !tile.equals(9) &&
                    !tile.equals(19) &&
                    !tile.equals(20) &&
                    !tile.equals(21) &&
                    !tile.equals(22) &&
                    !tile.equals(23) &&
                    !tile.equals(24) &&
                    !tile.equals(25) &&
                    !tile.equals(26) &&
                    !tile.equals(27) &&
                    !tile.equals(28) &&
                    !tile.equals(29) &&
                    !tile.equals(30) &&
                    !tile.equals(32) &&
                    !tile.equals(33) &&
                    !tile.equals(34) &&
                    !tile.equals(35) &&
                    !tile.equals(36) &&
                    !tile.equals(37) &&
                    !tile.equals(49) &&
                    !tile.equals(50) &&
                    !tile.equals(51) &&
                    !tile.equals(52) &&
                    !tile.equals(53) &&
                    !tile.equals(54) &&
                    !tile.equals(58) &&
                    !tile.equals(59) &&
                    !tile.equals(60) &&
                    !tile.equals(61) &&
                    !tile.equals(62) &&
                    !tile.equals(63) &&
                    !tile.equals(64) &&
                    !tile.equals(65) &&
                    !tile.equals(66) &&
                    !tile.equals(67) &&
                    !tile.equals(68) &&
                    !tile.equals(69) &&
                    !tile.equals(70) &&
                    !tile.equals(71) &&
                    !tile.equals(72) &&
                    !tile.equals(73) &&
                    !tile.equals(74) &&
                    !tile.equals(75) &&
                    !tile.equals(76) &&
                    !tile.equals(77) &&
                    !tile.equals(78) &&
                    !tile.equals(102) &&
                    !tile.equals(103) &&
                    !tile.equals(104) &&
                    !tile.equals(107) &&
                    !tile.equals(108) &&
                    !tile.equals(109) &&
                    !tile.equals(110) &&
                    !tile.equals(111) &&
                    !tile.equals(112) &&
                    !tile.equals(106) &&
                    !tile.equals(105) &&
                    !tile.equals(18))
            {
                obstacleTiles.add(tile);
            }
        }
    }

    private void setMapTileIcons() {
        try {
            mapTileIcons.put(1, new ImageIcon(ASSET_TILES_FOLDER_PATH + "grass.png"));
            //Road tiles
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
            mapTileIcons.put(27, new ImageIcon(ASSET_TILES_FOLDER_PATH + "track_road_11_1.png"));
            mapTileIcons.put(28, new ImageIcon(ASSET_TILES_FOLDER_PATH + "track_road_11_2.png"));
            mapTileIcons.put(29, new ImageIcon(ASSET_TILES_FOLDER_PATH + "track_road_11_3.png"));
            mapTileIcons.put(30, new ImageIcon(ASSET_TILES_FOLDER_PATH + "track_road_11_4.png"));

            mapTileIcons.put(31, new ImageIcon(ASSET_TILES_FOLDER_PATH + "track_water_04.png"));
            mapTileIcons.put(32, new ImageIcon(ASSET_TILES_FOLDER_PATH + "track_water_06.png"));
            mapTileIcons.put(33, new ImageIcon(ASSET_TILES_FOLDER_PATH + "track_water_05.png"));
            mapTileIcons.put(34, new ImageIcon(ASSET_TILES_FOLDER_PATH + "track_water_05_2.png"));
            mapTileIcons.put(35, new ImageIcon(ASSET_TILES_FOLDER_PATH + "track_water_06_rev.png"));
            mapTileIcons.put(36, new ImageIcon(ASSET_TILES_FOLDER_PATH + "track_water_05_2_rev.png"));
            mapTileIcons.put(37, new ImageIcon(ASSET_TILES_FOLDER_PATH + "track_water_05_rev.png"));
            mapTileIcons.put(38, new ImageIcon(ASSET_TILES_FOLDER_PATH + "track_water_03_LEFT.png"));
            mapTileIcons.put(39, new ImageIcon(ASSET_TILES_FOLDER_PATH + "track_water_03_right.png"));
            mapTileIcons.put(40, new ImageIcon(ASSET_TILES_FOLDER_PATH + "track_water_03_up.png"));
            mapTileIcons.put(41, new ImageIcon(ASSET_TILES_FOLDER_PATH + "track_water_03_down.png"));
            mapTileIcons.put(42, new ImageIcon(ASSET_TILES_FOLDER_PATH + "track_water_curve2_1.png"));
            mapTileIcons.put(43, new ImageIcon(ASSET_TILES_FOLDER_PATH + "track_water_curve2_2.png"));
            mapTileIcons.put(44, new ImageIcon(ASSET_TILES_FOLDER_PATH + "track_water_curve2_3.png"));
            mapTileIcons.put(45, new ImageIcon(ASSET_TILES_FOLDER_PATH + "track_water_curve2_4.png"));
            mapTileIcons.put(46, new ImageIcon(ASSET_TILES_FOLDER_PATH + "track_water_curve1_1.png"));
            mapTileIcons.put(47, new ImageIcon(ASSET_TILES_FOLDER_PATH + "track_water_curve1_2.png"));
            mapTileIcons.put(48, new ImageIcon(ASSET_TILES_FOLDER_PATH + "track_water_curve1_01_3.png"));
            mapTileIcons.put(49, new ImageIcon(ASSET_TILES_FOLDER_PATH + "track_water_06_right.png"));
            mapTileIcons.put(50, new ImageIcon(ASSET_TILES_FOLDER_PATH + "track_water_06_left.png"));
            mapTileIcons.put(51, new ImageIcon(ASSET_TILES_FOLDER_PATH + "track_water_05_1.png"));
            mapTileIcons.put(52, new ImageIcon(ASSET_TILES_FOLDER_PATH + "track_water_05_22.png"));
            mapTileIcons.put(53, new ImageIcon(ASSET_TILES_FOLDER_PATH + "track_water_05_3.png"));
            mapTileIcons.put(54, new ImageIcon(ASSET_TILES_FOLDER_PATH + "track_water_05_4.png"));
            mapTileIcons.put(55, new ImageIcon(ASSET_OBJECTS_AND_DECORATIONS_FOLDER_PATH + "tressure1.png"));
            mapTileIcons.put(56, new ImageIcon(ASSET_OBJECTS_AND_DECORATIONS_FOLDER_PATH + "tressure2.png"));
            mapTileIcons.put(57, new ImageIcon(ASSET_OBJECTS_AND_DECORATIONS_FOLDER_PATH + "tree.png"));
            mapTileIcons.put(58, new ImageIcon(ASSET_TILES_FOLDER_PATH+ "track_dirt_02.png"));
            mapTileIcons.put(59, new ImageIcon(ASSET_TILES_FOLDER_PATH + "track_dirt_02_2.png"));
            mapTileIcons.put(60, new ImageIcon(ASSET_TILES_FOLDER_PATH + "track_dirt_02_3.png"));
            mapTileIcons.put(61, new ImageIcon(ASSET_TILES_FOLDER_PATH + "track_dirt_02_4.png"));
            mapTileIcons.put(62, new ImageIcon(ASSET_TILES_FOLDER_PATH + "track_dirt_04_1.png"));
            mapTileIcons.put(63, new ImageIcon(ASSET_TILES_FOLDER_PATH + "track_dirt_04_2.png"));
            mapTileIcons.put(64, new ImageIcon(ASSET_TILES_FOLDER_PATH + "track_dirt_01.png"));
            mapTileIcons.put(65, new ImageIcon(ASSET_TILES_FOLDER_PATH + "track_dirt_05_1.png"));
            mapTileIcons.put(66, new ImageIcon(ASSET_TILES_FOLDER_PATH + "track_dirt_05_2.png"));
            mapTileIcons.put(67, new ImageIcon(ASSET_TILES_FOLDER_PATH + "track_dirt_05_3.png"));
            mapTileIcons.put(68, new ImageIcon(ASSET_TILES_FOLDER_PATH + "track_dirt_05_4.png"));
            mapTileIcons.put(69, new ImageIcon(ASSET_TILES_FOLDER_PATH + "track_dirt_05_4_2.png"));
            mapTileIcons.put(70, new ImageIcon(ASSET_TILES_FOLDER_PATH + "track_dirt_03.png"));
            mapTileIcons.put(71, new ImageIcon(ASSET_TILES_FOLDER_PATH + "track_dirt_05_5_1.png"));
            mapTileIcons.put(72, new ImageIcon(ASSET_TILES_FOLDER_PATH + "track_dirt_03_top.png"));
            mapTileIcons.put(73, new ImageIcon(ASSET_TILES_FOLDER_PATH + "track_dirt_03_left.png"));
            mapTileIcons.put(74, new ImageIcon(ASSET_TILES_FOLDER_PATH + "track_dirt_05_6.png"));
            mapTileIcons.put(75, new ImageIcon(ASSET_TILES_FOLDER_PATH + "track_dirt_05_6_1.png"));
            mapTileIcons.put(76, new ImageIcon(ASSET_TILES_FOLDER_PATH + "track_dirt_03_right.png"));
            mapTileIcons.put(77, new ImageIcon(ASSET_TILES_FOLDER_PATH + "track_dirt_03_down.png"));
            mapTileIcons.put(78, new ImageIcon(ASSET_TILES_FOLDER_PATH + "track_dirt_05_6_2.png"));
            mapTileIcons.put(79, new ImageIcon(ASSET_OBJECTS_AND_DECORATIONS_FOLDER_PATH + "tree_1_1.png"));
            mapTileIcons.put(80, new ImageIcon(ASSET_OBJECTS_AND_DECORATIONS_FOLDER_PATH + "tree_1_2.png"));
            mapTileIcons.put(81, new ImageIcon(ASSET_OBJECTS_AND_DECORATIONS_FOLDER_PATH + "tree_1_3.png"));
            mapTileIcons.put(82, new ImageIcon(ASSET_OBJECTS_AND_DECORATIONS_FOLDER_PATH + "tree_1_4.png"));
            mapTileIcons.put(83, new ImageIcon(ASSET_OBJECTS_AND_DECORATIONS_FOLDER_PATH + "waterstone_01_1.png"));
            mapTileIcons.put(84, new ImageIcon(ASSET_OBJECTS_AND_DECORATIONS_FOLDER_PATH + "waterstone_02_1.png"));
            mapTileIcons.put(85, new ImageIcon(ASSET_OBJECTS_AND_DECORATIONS_FOLDER_PATH + "waterstone_03_1.png"));
            mapTileIcons.put(86, new ImageIcon(ASSET_OBJECTS_AND_DECORATIONS_FOLDER_PATH + "block.png"));
            mapTileIcons.put(87, new ImageIcon(ASSET_OBJECTS_AND_DECORATIONS_FOLDER_PATH + "barrel_standup.png"));
            mapTileIcons.put(88, new ImageIcon(ASSET_OBJECTS_AND_DECORATIONS_FOLDER_PATH + "barrel_fallen.png"));
            mapTileIcons.put(89, new ImageIcon(ASSET_OBJECTS_AND_DECORATIONS_FOLDER_PATH + "block_1.png"));
            mapTileIcons.put(90, new ImageIcon(ASSET_OBJECTS_AND_DECORATIONS_FOLDER_PATH + "grassdecoration_01.png"));
            mapTileIcons.put(91, new ImageIcon(ASSET_OBJECTS_AND_DECORATIONS_FOLDER_PATH + "grassdecoration_02.png"));
            mapTileIcons.put(92, new ImageIcon(ASSET_OBJECTS_AND_DECORATIONS_FOLDER_PATH + "grassdecoration_03.png"));
            mapTileIcons.put(93, new ImageIcon(ASSET_OBJECTS_AND_DECORATIONS_FOLDER_PATH + "grassdecoration_04.png"));
            mapTileIcons.put(94, new ImageIcon(ASSET_OBJECTS_AND_DECORATIONS_FOLDER_PATH + "grassdecoration_05.png"));
            mapTileIcons.put(95, new ImageIcon(ASSET_OBJECTS_AND_DECORATIONS_FOLDER_PATH + "grassdecoration_06.png"));
            mapTileIcons.put(96, new ImageIcon(ASSET_OBJECTS_AND_DECORATIONS_FOLDER_PATH + "grassdecoration_07.png"));
            mapTileIcons.put(97, new ImageIcon(ASSET_OBJECTS_AND_DECORATIONS_FOLDER_PATH + "grassdecoration_08.png"));
            mapTileIcons.put(98, new ImageIcon(ASSET_OBJECTS_AND_DECORATIONS_FOLDER_PATH + "grassdecoration_09.png"));
            mapTileIcons.put(99, new ImageIcon(ASSET_OBJECTS_AND_DECORATIONS_FOLDER_PATH + "grassdecoration_10.png"));
            mapTileIcons.put(100, new ImageIcon(ASSET_OBJECTS_AND_DECORATIONS_FOLDER_PATH + "grassdecoration_11.png"));
            mapTileIcons.put(101, new ImageIcon(ASSET_OBJECTS_AND_DECORATIONS_FOLDER_PATH + "grassdecoration_12.png"));
            mapTileIcons.put(102, new ImageIcon(ASSET_TILES_FOLDER_PATH + "Startarea.png"));
            mapTileIcons.put(103, new ImageIcon(ASSET_TILES_FOLDER_PATH + "Startarea_1.png"));
            mapTileIcons.put(104, new ImageIcon(ASSET_TILES_FOLDER_PATH + "Startarea_2.png"));
            mapTileIcons.put(105, new ImageIcon(ASSET_TILES_FOLDER_PATH + "bridge.png"));
            mapTileIcons.put(106, new ImageIcon(ASSET_TILES_FOLDER_PATH + "bridge_1.png"));
            mapTileIcons.put(107, new ImageIcon(ASSET_TILES_FOLDER_PATH + "bridge_2.png"));
            mapTileIcons.put(108, new ImageIcon(ASSET_OBJECTS_AND_DECORATIONS_FOLDER_PATH + "flower_1.png"));
            mapTileIcons.put(109, new ImageIcon(ASSET_OBJECTS_AND_DECORATIONS_FOLDER_PATH + "flower_2.png"));
            mapTileIcons.put(110, new ImageIcon(ASSET_OBJECTS_AND_DECORATIONS_FOLDER_PATH + "flower_3.png"));
            mapTileIcons.put(111, new ImageIcon(ASSET_OBJECTS_AND_DECORATIONS_FOLDER_PATH + "flower_4.png"));
            mapTileIcons.put(112, new ImageIcon(ASSET_OBJECTS_AND_DECORATIONS_FOLDER_PATH + "flower_5.png"));
            mapTileIcons.put(113, new ImageIcon(ASSET_OBJECTS_AND_DECORATIONS_FOLDER_PATH + "house_left.png"));
            mapTileIcons.put(114, new ImageIcon(ASSET_OBJECTS_AND_DECORATIONS_FOLDER_PATH + "house_right.png"));
            mapTileIcons.put(115, new ImageIcon(ASSET_OBJECTS_AND_DECORATIONS_FOLDER_PATH + "car.png"));


        } catch (Exception e) {
            throw new RuntimeException("Cant load image tiles: " + e.getMessage());
        }
    }
}