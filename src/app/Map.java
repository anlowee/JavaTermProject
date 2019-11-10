package app;

public class Map implements Common {

    public static int mapX;
    public static int mapY;

    public static boolean isOnMapDownSide;
    public static boolean isOnMapUpSide;
    public static boolean isOnMapLeftSide;
    public static boolean isOnMapRightSide;

    public static int[][] tileSets = new int[TILE_COL][TILE_ROW];

    public void initTile() {

        //100-grass, 101-soil, 102-sand, 103-water, 104-house, 0~19-crops
        for (int i = 0; i < TILE_COL; i++)
            for (int j = 0; j < TILE_COL; j++) 
                tileSets[i][j] = GRASS;
        
        //load field 1 7*5
        for (int i = 8; i < 15; i++)
            for (int j = 11; j < 16; j++) 
                tileSets[i][j] = SOIL;
        //load field 2 7*5
        for (int i = 19; i < 26; i++)
            for (int j = 11; j < 16; j++) 
                tileSets[i][j] = SOIL; 
        //load field 3 7*5
        for (int i = 8; i < 15; i++)
            for (int j = 19; j < 24; j++) 
                tileSets[i][j] = SOIL;
        //load field 4 7*5
        for (int i = 19; i < 26; i++)
            for (int j = 19; j < 24; j++) 
                tileSets[i][j] = SOIL;
        //load field 5 7*5
        for (int i = 8; i < 15; i++)
            for (int j = 27; j < 32; j++) 
                tileSets[i][j] = SOIL;
        //load field 6 7*5
        for (int i = 19; i < 26; i++)
            for (int j = 27; j < 32; j++) 
                tileSets[i][j] = SOIL;
        
        //load house 5*7
        for (int i = 33; i < 36; i++)
            for (int j = 11; j < 18; j++)
                tileSets[i][j] = HOUSE;
        tileSets[32][11] = GRASS;
        tileSets[35][11] = GRASS;
        
        //load sand

        //load water
    }

    public Map() {

        initTile();

        mapX = MAP_LEFTUP_X;
        mapY = MAP_LEFTUP_Y;

        isOnMapDownSide = false;
        isOnMapLeftSide = false;
        isOnMapUpSide = false;
        isOnMapRightSide = false;
    }

    public static boolean isMapOnSide() {

        boolean flag = false;

        if (mapX == 0) {
            isOnMapLeftSide = true;
            flag = true;
        } else 
            isOnMapLeftSide = false;

        if (mapX == MAP_X_MIN) {
            isOnMapRightSide = true;
            flag = true;
        } else 
            isOnMapRightSide = false;

        if (mapY == 0) {
            isOnMapUpSide = true;
            flag = true;
        } else    
            isOnMapUpSide = false;

        if (mapY == MAP_Y_MIN) {
            isOnMapDownSide = true;
            flag = true;
        } else
            isOnMapDownSide = false;

        return flag;
    }
}