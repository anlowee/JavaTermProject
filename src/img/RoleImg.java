package img;

import java.awt.image.BufferedImage;

import app.Common;

public class RoleImg implements Common {

	// 4 means 4 directions, 3 means 3 status in each direction
    public static BufferedImage[][] imgPlayer = new BufferedImage[4][3];
    //0-old man, 1-old woman, 2-healer, 3-mage, 4-roger, 5-warrior
    public static BufferedImage[][][] imgNPC = new BufferedImage[NPC_TOTAL_CATAGORIES][4][3];
}