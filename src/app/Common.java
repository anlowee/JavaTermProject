package app;

public interface Common {

	// this interface include all constants used in game
	
    int INF = 9999;	// INF is the maximum number in game
    
    // move speed
    int HUMAN_MOVE_SPEED = 6;	// player's move speed
    int NPC_MOVE_SPEED = 5;	// NPC's move speed
    
    // the entire map is made of many tiles
    int TILE_ROW = 50;	// the rows of tiles in map
    int TILE_COL = 50;	//the columns of tiles in map
    
    // all characters' initial position
    int PLAYER_START_X = 464;	
    int PLAYER_START_Y = 444;
    int OLDMAN_START_X = 550;
    int OLDMAN_START_Y = 550;
    int OLDWOMAN_START_X = 464;
    int OLDWOMAN_START_Y = 500;
    int HEALER_START_X = 300;
    int HEALER_START_Y = 600;
    int MAGE_START_X = 500;
    int MAGE_START_Y = 500;
    int ROGER_START_X = 550;
    int ROGER_START_Y = 550;
    int WARRIOR_START_X = 500;
    int WARRIOR_START_Y = 500;
    
    // size
    // characters' size
    int PLAYER_WIDTH = 32;
    int PLAYER_HEIGHT = 36;
    // crops' size
    int CROPS_WIDTH = 16;
    int CROPS_HEIGHT = 16;
    // tiles' size
    int TILE_WIDTH = 32;
    int TILE_HEIGHT = 32;
    // frame size
    int FRAME_WIDTH = 960;
    int FRAME_HEIGHT = 960;
    // sub frame size
    int SUBFRAME_WIDTH = 640;
    int SUBFRAME_HEIGHT = 640;
    
    // map info
    // map's left-up corner's initial position
    int MAP_LEFTUP_X = -630;
    int MAP_LEFTUP_Y = -136;
    // map's size
    int MAP_WIDTH = 1600;
    int MAP_HEIGHT = 1600;
    // map's left-up corner's boundary
    int MAP_X_MIN = FRAME_WIDTH - MAP_WIDTH;
    int MAP_Y_MIN = FRAME_HEIGHT - MAP_HEIGHT;
    // the center area(make sure when map is not on the boundary, player's view in this center area)
    // this is four boundary of a rectangle
    int PLAYER_VIEW_DOWNSIDE = 720;
    int PLAYER_VIEW_UPSIDE = 240;
    int PLAYER_VIEW_LEFTSIDE = 240;
    int PLAYER_VIEW_RIGHTSIDE = 720;
    
    int PANEL_GRID_COL = 30;	// panel's grid columns
    
    // tile ID, every tile has ID to be identified
    int GRASS = 100;
    int SOIL = 101;
    int SAND = 102;
    int WATER = 103;
    int HOUSE = 104;
    
    // time
    int DELAY = 50;	// each 50ms, update everything
    long BASIC_GROW_TIME = 300;	// // the time of growth of crops
    double BASIC_SPEED = 10.0;
    // how long the buff make affection
    long PRIMARY_BUFF_TIME = 12000;
    long NORMAL_BUFF_TIME = 15000;
    long SENIOR_BUFF_TIME = 18000;
    long SUPER_BUFF_TIME = 21000;
    
    // categories
    int CROPS_TOTAL_CATAGORIES = 20;	// there are 20 kinds of crops in game
    int NPC_TOTAL_CATAGORIES = 8;	// there are 8 kinds of NPC in game
    int ITEM_TOTAL_CATAGORIES = 12;	// there are 12 kinds of items in game
    
    // item
    // each column means: no-buff, primary-buff, normal0buff, senior-buff, super-buff
    double[] GROW_BUFF = { 1.0, 0.9, 0.8, 0.7, 0.6 };	// grow time discount
    double[] FRUIT_BUFF = { 1.0, 1.1, 1.2, 1.3, 1.4 };	// fruit buff
    double[] VALUE_BUFF = { 1.0, 1.1, 1.2, 1.3, 1.4 };	// value buff
    
    // shop price
    int[] ITEM_PRICE = { 50, 80, 110, 140, 50, 80, 110, 140, 100, 200, 300, 400 };
    int[] SEEDS_PRICE = { 100, 35, 75, 50, 30, 50, 115, 90, 115, 100, 25, 85, 25, 60, 200, 70, 115, 80, 15, 115 };

    // name
    String[] CROPS_NAME = { "Turnip", "Rose", "Cucumber", "Tuilip", "Tomato", "Melon", "Eggpplant", "Lemon",
            "Pineapple", "Rice", "Wheat", "Grapes", "Stawberry", "Canssava", "Potato", "Coffee", "Orange", "Avocado",
            "Corn", "Sunflower" };

    String[] ITEM_NAME = { "GPB", "GNB", "GSB", "GSPB",
            "FPB", "FNB", "FSB", "FSPB", "VPB",
            "VNB", "VSB", "VSPB" };

    String[] NPC_NAME = { "Old Man", "Old Woman", "Preist", "Mage", "Roger", "Warrior" };

    String[] MISSION_NAME = { "Talk to old man!", "Talk to old woman!", "Talk to healer and plant a crop!", "Talk to mage and use a BUFF!", "Recuit 5 rogers and 5 warriors!" };

    // help
    String HELP = "W-up\nS-down\nA-left\nD-right\n↑ ↓ ← → -select\nP-plant\nT-talk\nR-recruit\nC-remove\nH-pick";

    // crops relative
    double[] JUICE_MIN = { 35, 0, 0, 0, 70, 75, 0, 60, 20, 0, 0, 60, 10, 0, 0, 0, 60, 5, 30, 0 };
    double[] JUICE_MAX = { 50, 1, 20, 1, 95, 100, 10, 90, 50, 1, 1, 90, 30, 5, 5, 1, 90, 25, 50, 1 };
    double[] SUGAR_MIN = { 40, 0, 30, 0, 70, 75, 25, 10, 40, 0, 0, 60, 75, 25, 10, 40, 75, 40, 30, 0 };
    double[] SUGAR_MAX = { 65, 1, 50, 1, 80, 100, 35, 20, 80, 1, 1, 90, 100, 35, 20, 60, 100, 60, 50, 1 };
    double[] STARCH_MIN = { 20, 0, 10, 0, 0, 0, 40, 0, 0, 90, 90, 0, 0, 35, 80, 50, 0, 0, 70, 0 };
    double[] STARCH_MAX = { 30, 1, 15, 1, 5, 5, 50, 5, 10, 100, 100, 5, 5, 60, 100, 70, 5, 30, 90, 1 };
    int[] CROPS_ID = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19 };
    int[] CROPS_VALUE = { 20, 7, 15, 10, 6, 10, 23, 18, 23, 20, 5, 17, 5, 12, 40, 14, 23, 16, 3, 23 };
}