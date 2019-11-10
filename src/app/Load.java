package app;

import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

import com.crops.Crops;
import com.farm.Farm;
import com.role.Healer;
import com.role.Mage;
import com.role.NPCs;
import com.role.OldMan;
import com.role.OldWoman;
import com.role.Player;
import com.role.Roger;
import com.role.Warrior;
import com.warehouse.Ware;

import img.CropsImg;
import img.ItemImg;
import img.MapImg;
import img.RoleImg;

public class Load implements Common {

    final static int width = 16;
    final static int height = 16;
    private static URL imageUrl;

    public Load() throws IOException {
        
    	loadData();
        loadCropsImg();
        loadMapImg();
        loadRoleImg();
        loadItemImg();
    }
    
    public static void loadData() throws IOException {
    	
    	new Map();
    	new Farm();
    	new Ware();
    	
    	File file = new File("D:\\Save.dat");
    	if (!file.exists()) {
    		Player.init();
    	} else {
    		FileInputStream fileInputStream = new FileInputStream(file);
    		BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
    		@SuppressWarnings("resource")
			DataInputStream dataInputStream = new DataInputStream(bufferedInputStream);
    		
    		Player.x = dataInputStream.readInt();
    		Player.y = dataInputStream.readInt();
    		Player.absX = dataInputStream.readInt();
    		Player.absY = dataInputStream.readInt();
    		Player.dx = dataInputStream.readInt();
    		Player.dy = dataInputStream.readInt();
    		Player.dir = dataInputStream.readInt();
    		Player.gif = dataInputStream.readInt();
    		Player.tGif = dataInputStream.readLong();
    		Player.seedsSelect = dataInputStream.readInt();
    		Player.misson = dataInputStream.readInt();
    		Player.money = dataInputStream.readInt();
    		Player.isMission4Complted = dataInputStream.readBoolean();
    		
    		Map.mapX = dataInputStream.readInt();
    		Map.mapY = dataInputStream.readInt();
    		Map.isOnMapDownSide = dataInputStream.readBoolean();
    		Map.isOnMapLeftSide = dataInputStream.readBoolean();
    		Map.isOnMapRightSide = dataInputStream.readBoolean();
    		Map.isOnMapUpSide = dataInputStream.readBoolean();
    		for (int i = 0; i < TILE_COL; i++)
    			for (int j = 0; j < TILE_ROW; j++)
    				Map.tileSets[i][j] = dataInputStream.readInt();
    		
    		for (int i = 0; i < CROPS_TOTAL_CATAGORIES; i++)
    			Ware.rawInventory[i] = dataInputStream.readInt();
    		for (int i = 0; i < CROPS_TOTAL_CATAGORIES; i++)
    			Ware.seedsInventory[i] = dataInputStream.readInt();
    		for (int i = 0; i < ITEM_TOTAL_CATAGORIES; i++)
    			Ware.itemInventory[i] = dataInputStream.readInt();
    		NPCs.numRoger = dataInputStream.readInt();
    		NPCs.numWarrior = dataInputStream.readInt();
    		
    		Crops.growBuff = dataInputStream.readShort();
    		Crops.fruitBuff = dataInputStream.readShort();
    		Crops.valueBuff = dataInputStream.readShort();
    		Farm.bf.whichBuff = dataInputStream.readInt();
    		Farm.bf.buffTime = dataInputStream.readLong();
    		Farm.bf.current = dataInputStream.readLong();
    		if (Farm.bf.whichBuff != -1)
    			Farm.bf.start();
    		
    		int len = dataInputStream.readInt();
    		for (int i = 0; i < len; i++) {
    			int id = dataInputStream.readInt();
    			Crops c = new Crops(id);
    			c.absX = dataInputStream.readInt();
    			c.absY = dataInputStream.readInt();
    			boolean flag = dataInputStream.readBoolean();
    			c.setIsFruited(flag);
    			flag = dataInputStream.readBoolean();
    			c.setIsDead(flag);
    			c.setNumFlowers(dataInputStream.readInt());
    			c.setNumFruits(dataInputStream.readInt());
    			c.setNumSeeds(dataInputStream.readInt());
    			c.setStatus(dataInputStream.readShort());
    			c.value = dataInputStream.readInt();
    			c.growth = dataInputStream.readLong();
    			c.current = dataInputStream.readLong();
    			
    			Farm.cropsArrayList.add(c);
    		}
    		NPCs.flagHealer = dataInputStream.readBoolean();
    		NPCs.flagOldWoman = dataInputStream.readBoolean();
    		NPCs.flagMage = dataInputStream.readBoolean();
    		NPCs.flagRoger = dataInputStream.readBoolean();
    		NPCs.flagWarrior = dataInputStream.readBoolean();
    		len = dataInputStream.readInt();
    		for (int i = 0; i < len; i++) {
    			int id = dataInputStream.readInt();
    			switch (id) {
				case 0:
					OldMan oldMan = new OldMan();
					oldMan.absX = dataInputStream.readInt();
	    			oldMan.absY = dataInputStream.readInt();
	    			oldMan.act = dataInputStream.readInt();
	    			oldMan.dir = dataInputStream.readInt();
	    			oldMan.tGif = dataInputStream.readLong();
	    			oldMan.gif = dataInputStream.readInt();
					NPCs.npc.add(oldMan);
					break;
				case 1:
					OldWoman oldWoman = new OldWoman();
					oldWoman.absX = dataInputStream.readInt();
	    			oldWoman.absY = dataInputStream.readInt();
	    			oldWoman.act = dataInputStream.readInt();
	    			oldWoman.dir = dataInputStream.readInt();
	    			oldWoman.tGif = dataInputStream.readLong();
	    			oldWoman.gif = dataInputStream.readInt();
					NPCs.npc.add(oldWoman);
					break;
				case 2:
					Healer healer = new Healer();
					healer.absX = dataInputStream.readInt();
	    			healer.absY = dataInputStream.readInt();
	    			healer.act = dataInputStream.readInt();
	    			healer.dir = dataInputStream.readInt();
	    			healer.tGif = dataInputStream.readLong();
	    			healer.gif = dataInputStream.readInt();
					NPCs.npc.add(healer);
					break;
				case 3:
					Mage mage = new Mage();
					mage.absX = dataInputStream.readInt();
	    			mage.absY = dataInputStream.readInt();
	    			mage.act = dataInputStream.readInt();
	    			mage.dir = dataInputStream.readInt();
	    			mage.tGif = dataInputStream.readLong();
	    			mage.gif = dataInputStream.readInt();
					NPCs.npc.add(mage);
					break;
				case 4:
					Roger roger = new Roger();
					roger.absX = dataInputStream.readInt();
	    			roger.absY = dataInputStream.readInt();
	    			roger.act = dataInputStream.readInt();
	    			roger.dir = dataInputStream.readInt();
	    			roger.tGif = dataInputStream.readLong();
	    			roger.gif = dataInputStream.readInt();
					NPCs.npc.add(roger);
					break;
				case 5:
					Warrior warrior = new Warrior();
					warrior.absX = dataInputStream.readInt();
	    			warrior.absY = dataInputStream.readInt();
	    			warrior.act = dataInputStream.readInt();
	    			warrior.dir = dataInputStream.readInt();
	    			warrior.tGif = dataInputStream.readLong();
	    			warrior.gif = dataInputStream.readInt();
					NPCs.npc.add(warrior);
					break;
				default:
					break;
				}
    		}
    		
    		dataInputStream.close();
    	}
    }

    public static void loadItemImg() throws IOException {

    	imageUrl = Load.class.getResource("/image/item/coin.png");
        BufferedImage bufimg = ImageIO.read(imageUrl);
        ItemImg.coinImg = bufimg;

        imageUrl = Load.class.getResource("/image/item/itemicon.png");
        bufimg = ImageIO.read(imageUrl);
        ItemImg.swordImg = bufimg;

        imageUrl = Load.class.getResource("/image/item/help.png");
        bufimg = ImageIO.read(imageUrl);
        ItemImg.helpImg = bufimg;
        
        //load buff image
        imageUrl = Load.class.getResource("/image/item/gpb.png");
        bufimg = ImageIO.read(imageUrl);
        ItemImg.buggBufferedImages[0] = bufimg;
        
        imageUrl = Load.class.getResource("/image/item/gnb.png");
        bufimg = ImageIO.read(imageUrl);
        ItemImg.buggBufferedImages[1] = bufimg;
        
        imageUrl = Load.class.getResource("/image/item/gsb.png");
        bufimg = ImageIO.read(imageUrl);
        ItemImg.buggBufferedImages[2] = bufimg;
        
        imageUrl = Load.class.getResource("/image/item/gspb.png");
        bufimg = ImageIO.read(imageUrl);
        ItemImg.buggBufferedImages[3] = bufimg;
        
        imageUrl = Load.class.getResource("/image/item/fpb.png");
        bufimg = ImageIO.read(imageUrl);
        ItemImg.buggBufferedImages[4] = bufimg;
        
        imageUrl = Load.class.getResource("/image/item/fnb.png");
        bufimg = ImageIO.read(imageUrl);
        ItemImg.buggBufferedImages[5] = bufimg;

        imageUrl = Load.class.getResource("/image/item/fsb.png");
        bufimg = ImageIO.read(imageUrl);
        ItemImg.buggBufferedImages[6] = bufimg;
        
        imageUrl = Load.class.getResource("/image/item/fspb.png");
        bufimg = ImageIO.read(imageUrl);
        ItemImg.buggBufferedImages[7] = bufimg;
        
        imageUrl = Load.class.getResource("/image/item/vpb.png");
        bufimg = ImageIO.read(imageUrl);
        ItemImg.buggBufferedImages[8] = bufimg;
        
        imageUrl = Load.class.getResource("/image/item/vnb.png");
        bufimg = ImageIO.read(imageUrl);
        ItemImg.buggBufferedImages[9] = bufimg;
        
        imageUrl = Load.class.getResource("/image/item/vsb.png");
        bufimg = ImageIO.read(imageUrl);
        ItemImg.buggBufferedImages[10] = bufimg;
        
        imageUrl = Load.class.getResource("/image/item/vspb.png");
        bufimg = ImageIO.read(imageUrl);
        ItemImg.buggBufferedImages[11] = bufimg;
    }

    public static void loadCropsImg() throws IOException {

    	imageUrl = Load.class.getResource("/image/crops/Crop_Spritesheet.png");
        BufferedImage bufimg = ImageIO.read(imageUrl);
        //BufferedImage bufimg = ImageIO.read(new File("src/image/crops/Crop_Spritesheet.png"));

        CropsImg.imgCrops[0][5] = bufimg.getSubimage(0, 0, width, height);//crops
        CropsImg.imgCrops[0][4] = bufimg.getSubimage(width, 0, width, height);//fruited
        CropsImg.imgCrops[0][3] = bufimg.getSubimage(width * 2, 0, width, height);//flowerd
        CropsImg.imgCrops[0][2] = bufimg.getSubimage(width * 3, 0, width, height);//seedling
        CropsImg.imgCrops[0][1] = bufimg.getSubimage(width * 4, 0, width, height);//bud
        CropsImg.imgCrops[0][0] = bufimg.getSubimage(width * 5, 0, width, height);//seed

        CropsImg.imgCrops[1][5] = bufimg.getSubimage(width * 6, 0, width, height);
        CropsImg.imgCrops[1][4] = bufimg.getSubimage(width * 7, 0, width, height);
        CropsImg.imgCrops[1][3] = bufimg.getSubimage(width * 8, 0, width, height);
        CropsImg.imgCrops[1][2] = bufimg.getSubimage(width * 9, 0, width, height);
        CropsImg.imgCrops[1][1] = bufimg.getSubimage(width * 10, 0, width, height);
        CropsImg.imgCrops[1][0] = bufimg.getSubimage(width * 11, 0, width, height);

        CropsImg.imgCrops[2][5] = bufimg.getSubimage(0, height, width, height);
        CropsImg.imgCrops[2][4] = bufimg.getSubimage(width, height, width, height);
        CropsImg.imgCrops[2][3] = bufimg.getSubimage(width * 2, height, width, height);
        CropsImg.imgCrops[2][2] = bufimg.getSubimage(width * 3, height, width, height);
        CropsImg.imgCrops[2][1] = bufimg.getSubimage(width * 4, height, width, height);
        CropsImg.imgCrops[2][0] = bufimg.getSubimage(width * 5, height, width, height);

        CropsImg.imgCrops[3][5] = bufimg.getSubimage(width * 6, height, width, height);
        CropsImg.imgCrops[3][4] = bufimg.getSubimage(width * 7, height, width, height);
        CropsImg.imgCrops[3][3] = bufimg.getSubimage(width * 8, height, width, height);
        CropsImg.imgCrops[3][2] = bufimg.getSubimage(width * 9, height, width, height);
        CropsImg.imgCrops[3][1] = bufimg.getSubimage(width * 10, height, width, height);
        CropsImg.imgCrops[3][0] = bufimg.getSubimage(width * 11, height, width, height);

        CropsImg.imgCrops[4][5] = bufimg.getSubimage(0, height * 2, width, height);
        CropsImg.imgCrops[4][4] = bufimg.getSubimage(width, height * 2, width, height);
        CropsImg.imgCrops[4][3] = bufimg.getSubimage(width * 2, height * 2, width, height);
        CropsImg.imgCrops[4][2] = bufimg.getSubimage(width * 3, height * 2, width, height);
        CropsImg.imgCrops[4][1] = bufimg.getSubimage(width * 4, height * 2, width, height);
        CropsImg.imgCrops[4][0] = bufimg.getSubimage(width * 5, height * 2, width, height);

        CropsImg.imgCrops[5][5] = bufimg.getSubimage(width * 6, height * 2, width, height);
        CropsImg.imgCrops[5][4] = bufimg.getSubimage(width * 7, height * 2, width, height);
        CropsImg.imgCrops[5][3] = bufimg.getSubimage(width * 8, height * 2, width, height);
        CropsImg.imgCrops[5][2] = bufimg.getSubimage(width * 9, height * 2, width, height);
        CropsImg.imgCrops[5][1] = bufimg.getSubimage(width * 10, height * 2, width, height);
        CropsImg.imgCrops[5][0] = bufimg.getSubimage(width * 11, height * 2, width, height);

        CropsImg.imgCrops[6][5] = bufimg.getSubimage(width * 0, height * 3, width, height);
        CropsImg.imgCrops[6][4] = bufimg.getSubimage(width * 1, height * 3, width, height);
        CropsImg.imgCrops[6][3] = bufimg.getSubimage(width * 2, height * 3, width, height);
        CropsImg.imgCrops[6][2] = bufimg.getSubimage(width * 3, height * 3, width, height);
        CropsImg.imgCrops[6][1] = bufimg.getSubimage(width * 4, height * 3, width, height);
        CropsImg.imgCrops[6][0] = bufimg.getSubimage(width * 5, height * 3, width, height);

        CropsImg.imgCrops[7][5] = bufimg.getSubimage(width * 6, height * 3, width, height);
        CropsImg.imgCrops[7][4] = bufimg.getSubimage(width * 7, height * 3, width, height);
        CropsImg.imgCrops[7][3] = bufimg.getSubimage(width * 8, height * 3, width, height);
        CropsImg.imgCrops[7][2] = bufimg.getSubimage(width * 9, height * 3, width, height);
        CropsImg.imgCrops[7][1] = bufimg.getSubimage(width * 10, height * 3, width, height);
        CropsImg.imgCrops[7][0] = bufimg.getSubimage(width * 11, height * 3, width, height);

        CropsImg.imgCrops[8][5] = bufimg.getSubimage(width * 0, height * 4, width, height);
        CropsImg.imgCrops[8][4] = bufimg.getSubimage(width * 1, height * 4, width, height);
        CropsImg.imgCrops[8][3] = bufimg.getSubimage(width * 2, height * 4, width, height);
        CropsImg.imgCrops[8][2] = bufimg.getSubimage(width * 3, height * 4, width, height);
        CropsImg.imgCrops[8][1] = bufimg.getSubimage(width * 4, height * 4, width, height);
        CropsImg.imgCrops[8][0] = bufimg.getSubimage(width * 5, height * 4, width, height);

        CropsImg.imgCrops[9][5] = bufimg.getSubimage(width * 6, height * 4, width, height);
        CropsImg.imgCrops[9][4] = bufimg.getSubimage(width * 7, height * 4, width, height);
        CropsImg.imgCrops[9][3] = bufimg.getSubimage(width * 8, height * 4, width, height);
        CropsImg.imgCrops[9][2] = bufimg.getSubimage(width * 9, height * 4, width, height);
        CropsImg.imgCrops[9][1] = bufimg.getSubimage(width * 10, height * 4, width, height);
        CropsImg.imgCrops[9][0] = bufimg.getSubimage(width * 11, height * 4, width, height);

        CropsImg.imgCrops[10][5] = bufimg.getSubimage(width * 0, height * 5, width, height);
        CropsImg.imgCrops[10][4] = bufimg.getSubimage(width * 1, height * 5, width, height);
        CropsImg.imgCrops[10][3] = bufimg.getSubimage(width * 2, height * 5, width, height);
        CropsImg.imgCrops[10][2] = bufimg.getSubimage(width * 3, height * 5, width, height);
        CropsImg.imgCrops[10][1] = bufimg.getSubimage(width * 4, height * 5, width, height);
        CropsImg.imgCrops[10][0] = bufimg.getSubimage(width * 5, height * 5, width, height);

        CropsImg.imgCrops[11][5] = bufimg.getSubimage(width * 6, height * 5, width, height);
        CropsImg.imgCrops[11][4] = bufimg.getSubimage(width * 7, height * 5, width, height);
        CropsImg.imgCrops[11][3] = bufimg.getSubimage(width * 8, height * 5, width, height);
        CropsImg.imgCrops[11][2] = bufimg.getSubimage(width * 9, height * 5, width, height);
        CropsImg.imgCrops[11][1] = bufimg.getSubimage(width * 10, height * 5, width, height);
        CropsImg.imgCrops[11][0] = bufimg.getSubimage(width * 11, height * 5, width, height);

        CropsImg.imgCrops[12][5] = bufimg.getSubimage(width * 0, height * 6, width, height);
        CropsImg.imgCrops[12][4] = bufimg.getSubimage(width * 1, height * 6, width, height);
        CropsImg.imgCrops[12][3] = bufimg.getSubimage(width * 2, height * 6, width, height);
        CropsImg.imgCrops[12][2] = bufimg.getSubimage(width * 3, height * 6, width, height);
        CropsImg.imgCrops[12][1] = bufimg.getSubimage(width * 4, height * 6, width, height);
        CropsImg.imgCrops[12][0] = bufimg.getSubimage(width * 5, height * 6, width, height);

        CropsImg.imgCrops[13][5] = bufimg.getSubimage(width * 6, height * 6, width, height);
        CropsImg.imgCrops[13][4] = bufimg.getSubimage(width * 7, height * 6, width, height);
        CropsImg.imgCrops[13][3] = bufimg.getSubimage(width * 8, height * 6, width, height);
        CropsImg.imgCrops[13][2] = bufimg.getSubimage(width * 9, height * 6, width, height);
        CropsImg.imgCrops[13][1] = bufimg.getSubimage(width * 10, height * 6, width, height);
        CropsImg.imgCrops[13][0] = bufimg.getSubimage(width * 11, height * 6, width, height);

        CropsImg.imgCrops[14][5] = bufimg.getSubimage(width * 0, height * 7, width, height);
        CropsImg.imgCrops[14][4] = bufimg.getSubimage(width * 1, height * 7, width, height);
        CropsImg.imgCrops[14][3] = bufimg.getSubimage(width * 2, height * 7, width, height);
        CropsImg.imgCrops[14][2] = bufimg.getSubimage(width * 3, height * 7, width, height);
        CropsImg.imgCrops[14][1] = bufimg.getSubimage(width * 4, height * 7, width, height);
        CropsImg.imgCrops[14][0] = bufimg.getSubimage(width * 5, height * 7, width, height);

        CropsImg.imgCrops[15][5] = bufimg.getSubimage(width * 6, height * 7, width, height);
        CropsImg.imgCrops[15][4] = bufimg.getSubimage(width * 7, height * 7, width, height);
        CropsImg.imgCrops[15][3] = bufimg.getSubimage(width * 8, height * 7, width, height);
        CropsImg.imgCrops[15][2] = bufimg.getSubimage(width * 9, height * 7, width, height);
        CropsImg.imgCrops[15][1] = bufimg.getSubimage(width * 10, height * 7, width, height);
        CropsImg.imgCrops[15][0] = bufimg.getSubimage(width * 11, height * 7, width, height);

        CropsImg.imgCrops[16][5] = bufimg.getSubimage(width * 0, height * 8, width, height);
        CropsImg.imgCrops[16][4] = bufimg.getSubimage(width * 1, height * 8, width, height);
        CropsImg.imgCrops[16][3] = bufimg.getSubimage(width * 2, height * 8, width, height);
        CropsImg.imgCrops[16][2] = bufimg.getSubimage(width * 3, height * 8, width, height);
        CropsImg.imgCrops[16][1] = bufimg.getSubimage(width * 4, height * 8, width, height);
        CropsImg.imgCrops[16][0] = bufimg.getSubimage(width * 5, height * 8, width, height);

        CropsImg.imgCrops[17][5] = bufimg.getSubimage(width * 6, height * 8, width, height);
        CropsImg.imgCrops[17][4] = bufimg.getSubimage(width * 7, height * 8, width, height);
        CropsImg.imgCrops[17][3] = bufimg.getSubimage(width * 8, height * 8, width, height);
        CropsImg.imgCrops[17][2] = bufimg.getSubimage(width * 9, height * 8, width, height);
        CropsImg.imgCrops[17][1] = bufimg.getSubimage(width * 10, height * 8, width, height);
        CropsImg.imgCrops[17][0] = bufimg.getSubimage(width * 11, height * 8, width, height);

        CropsImg.imgCrops[18][5] = bufimg.getSubimage(width * 0, height * 9, width, height);
        CropsImg.imgCrops[18][4] = bufimg.getSubimage(width * 1, height * 9, width, height);
        CropsImg.imgCrops[18][3] = bufimg.getSubimage(width * 2, height * 9, width, height);
        CropsImg.imgCrops[18][2] = bufimg.getSubimage(width * 3, height * 9, width, height);
        CropsImg.imgCrops[18][1] = bufimg.getSubimage(width * 4, height * 9, width, height);
        CropsImg.imgCrops[18][0] = bufimg.getSubimage(width * 5, height * 9, width, height);

        CropsImg.imgCrops[19][5] = bufimg.getSubimage(width * 6, height * 9, width, height);
        CropsImg.imgCrops[19][4] = bufimg.getSubimage(width * 7, height * 9, width, height);
        CropsImg.imgCrops[19][3] = bufimg.getSubimage(width * 8, height * 9, width, height);
        CropsImg.imgCrops[19][2] = bufimg.getSubimage(width * 9, height * 9, width, height);
        CropsImg.imgCrops[19][1] = bufimg.getSubimage(width * 10, height * 9, width, height);
        CropsImg.imgCrops[19][0] = bufimg.getSubimage(width * 11, height * 9, width, height);
    }

    public static void loadMapImg() throws IOException {

    	imageUrl = Load.class.getResource("/image/map/map.png");
        BufferedImage bufimg = ImageIO.read(imageUrl);
		MapImg.imgMap = bufimg;
    }

    public static void loadRoleImg() throws IOException {

        BufferedImage bufimg;

        // load player img
        imageUrl = Load.class.getResource("/image/role/player.png");
        bufimg = ImageIO.read(imageUrl);
        //bufimg = ImageIO.read(new File("src/image/role/player.png"));
        //up-0, right-1, donw-2, left-3
        RoleImg.imgPlayer[0][0] = bufimg.getSubimage(32 * 0, 36 * 0, 32, 36);
        RoleImg.imgPlayer[0][1] = bufimg.getSubimage(32 * 2, 36 * 0, 32, 36);
        RoleImg.imgPlayer[0][2] = bufimg.getSubimage(32 * 1, 36 * 0, 32, 36);

        RoleImg.imgPlayer[1][0] = bufimg.getSubimage(32 * 0, 36 * 1, 32, 36);
        RoleImg.imgPlayer[1][1] = bufimg.getSubimage(32 * 2, 36 * 1, 32, 36);
        RoleImg.imgPlayer[1][2] = bufimg.getSubimage(32 * 1, 36 * 1, 32, 36);

        RoleImg.imgPlayer[2][0] = bufimg.getSubimage(32 * 0, 36 * 2, 32, 36);
        RoleImg.imgPlayer[2][1] = bufimg.getSubimage(32 * 2, 36 * 2, 32, 36);
        RoleImg.imgPlayer[2][2] = bufimg.getSubimage(32 * 1, 36 * 2, 32, 36);

        RoleImg.imgPlayer[3][0] = bufimg.getSubimage(32 * 0, 36 * 3, 32, 36);
        RoleImg.imgPlayer[3][1] = bufimg.getSubimage(32 * 2, 36 * 3, 32, 36);
        RoleImg.imgPlayer[3][2] = bufimg.getSubimage(32 * 1, 36 * 3, 32, 36);

        //load old man img
        imageUrl = Load.class.getResource("/image/role/townfolk1_m.png");
        bufimg = ImageIO.read(imageUrl);
        //bufimg = ImageIO.read(new File("src/image/role/townfolk1_m.png"));
        RoleImg.imgNPC[0][0][0] = bufimg.getSubimage(32 * 0, 36 * 0, 32, 36);
        RoleImg.imgNPC[0][0][1] = bufimg.getSubimage(32 * 2, 36 * 0, 32, 36);
        RoleImg.imgNPC[0][0][2] = bufimg.getSubimage(32 * 1, 36 * 0, 32, 36);

        RoleImg.imgNPC[0][1][0] = bufimg.getSubimage(32 * 0, 36 * 1, 32, 36);
        RoleImg.imgNPC[0][1][1] = bufimg.getSubimage(32 * 2, 36 * 1, 32, 36);
        RoleImg.imgNPC[0][1][2] = bufimg.getSubimage(32 * 1, 36 * 1, 32, 36);

        RoleImg.imgNPC[0][2][0] = bufimg.getSubimage(32 * 0, 36 * 2, 32, 36);
        RoleImg.imgNPC[0][2][1] = bufimg.getSubimage(32 * 2, 36 * 2, 32, 36);
        RoleImg.imgNPC[0][2][2] = bufimg.getSubimage(32 * 1, 36 * 2, 32, 36);

        RoleImg.imgNPC[0][3][0] = bufimg.getSubimage(32 * 0, 36 * 3, 32, 36);
        RoleImg.imgNPC[0][3][1] = bufimg.getSubimage(32 * 2, 36 * 3, 32, 36);
        RoleImg.imgNPC[0][3][2] = bufimg.getSubimage(32 * 1, 36 * 3, 32, 36);

        //load old woman img
        imageUrl = Load.class.getResource("/image/role/townfolk1_f.png");
        bufimg = ImageIO.read(imageUrl);
        //bufimg = ImageIO.read(new File("src/image/role/townfolk1_f.png"));
        RoleImg.imgNPC[1][0][0] = bufimg.getSubimage(32 * 0, 36 * 0, 32, 36);
        RoleImg.imgNPC[1][0][1] = bufimg.getSubimage(32 * 2, 36 * 0, 32, 36);
        RoleImg.imgNPC[1][0][2] = bufimg.getSubimage(32 * 1, 36 * 0, 32, 36);

        RoleImg.imgNPC[1][1][0] = bufimg.getSubimage(32 * 0, 36 * 1, 32, 36);
        RoleImg.imgNPC[1][1][1] = bufimg.getSubimage(32 * 2, 36 * 1, 32, 36);
        RoleImg.imgNPC[1][1][2] = bufimg.getSubimage(32 * 1, 36 * 1, 32, 36);

        RoleImg.imgNPC[1][2][0] = bufimg.getSubimage(32 * 0, 36 * 2, 32, 36);
        RoleImg.imgNPC[1][2][1] = bufimg.getSubimage(32 * 2, 36 * 2, 32, 36);
        RoleImg.imgNPC[1][2][2] = bufimg.getSubimage(32 * 1, 36 * 2, 32, 36);

        RoleImg.imgNPC[1][3][0] = bufimg.getSubimage(32 * 0, 36 * 3, 32, 36);
        RoleImg.imgNPC[1][3][1] = bufimg.getSubimage(32 * 2, 36 * 3, 32, 36);
        RoleImg.imgNPC[1][3][2] = bufimg.getSubimage(32 * 1, 36 * 3, 32, 36);

        //load healer img
        imageUrl = Load.class.getResource("/image/role/healer_f.png");
        bufimg = ImageIO.read(imageUrl);
        //bufimg = ImageIO.read(new File("src/image/role/healer_f.png"));
        RoleImg.imgNPC[2][0][0] = bufimg.getSubimage(32 * 0, 36 * 0, 32, 36);
        RoleImg.imgNPC[2][0][1] = bufimg.getSubimage(32 * 2, 36 * 0, 32, 36);
        RoleImg.imgNPC[2][0][2] = bufimg.getSubimage(32 * 1, 36 * 0, 32, 36);

        RoleImg.imgNPC[2][1][0] = bufimg.getSubimage(32 * 0, 36 * 1, 32, 36);
        RoleImg.imgNPC[2][1][1] = bufimg.getSubimage(32 * 2, 36 * 1, 32, 36);
        RoleImg.imgNPC[2][1][2] = bufimg.getSubimage(32 * 1, 36 * 1, 32, 36);

        RoleImg.imgNPC[2][2][0] = bufimg.getSubimage(32 * 0, 36 * 2, 32, 36);
        RoleImg.imgNPC[2][2][1] = bufimg.getSubimage(32 * 2, 36 * 2, 32, 36);
        RoleImg.imgNPC[2][2][2] = bufimg.getSubimage(32 * 1, 36 * 2, 32, 36);

        RoleImg.imgNPC[2][3][0] = bufimg.getSubimage(32 * 0, 36 * 3, 32, 36);
        RoleImg.imgNPC[2][3][1] = bufimg.getSubimage(32 * 2, 36 * 3, 32, 36);
        RoleImg.imgNPC[2][3][2] = bufimg.getSubimage(32 * 1, 36 * 3, 32, 36);

        //load mage img
        imageUrl = Load.class.getResource("/image/role/mage_m.png");
        bufimg = ImageIO.read(imageUrl);
        //bufimg = ImageIO.read(new File("src/image/role/mage_m.png"));
        RoleImg.imgNPC[3][0][0] = bufimg.getSubimage(32 * 0, 36 * 0, 32, 36);
        RoleImg.imgNPC[3][0][1] = bufimg.getSubimage(32 * 2, 36 * 0, 32, 36);
        RoleImg.imgNPC[3][0][2] = bufimg.getSubimage(32 * 1, 36 * 0, 32, 36);

        RoleImg.imgNPC[3][1][0] = bufimg.getSubimage(32 * 0, 36 * 1, 32, 36);
        RoleImg.imgNPC[3][1][1] = bufimg.getSubimage(32 * 2, 36 * 1, 32, 36);
        RoleImg.imgNPC[3][1][2] = bufimg.getSubimage(32 * 1, 36 * 1, 32, 36);

        RoleImg.imgNPC[3][2][0] = bufimg.getSubimage(32 * 0, 36 * 2, 32, 36);
        RoleImg.imgNPC[3][2][1] = bufimg.getSubimage(32 * 2, 36 * 2, 32, 36);
        RoleImg.imgNPC[3][2][2] = bufimg.getSubimage(32 * 1, 36 * 2, 32, 36);

        RoleImg.imgNPC[3][3][0] = bufimg.getSubimage(32 * 0, 36 * 3, 32, 36);
        RoleImg.imgNPC[3][3][1] = bufimg.getSubimage(32 * 2, 36 * 3, 32, 36);
        RoleImg.imgNPC[3][3][2] = bufimg.getSubimage(32 * 1, 36 * 3, 32, 36);

        //load roger img
        imageUrl = Load.class.getResource("/image/role/roger.png");
        bufimg = ImageIO.read(imageUrl);
        //bufimg = ImageIO.read(new File("src/image/role/roger.png"));
        RoleImg.imgNPC[4][0][0] = bufimg.getSubimage(32 * 0, 36 * 0, 32, 36);
        RoleImg.imgNPC[4][0][1] = bufimg.getSubimage(32 * 2, 36 * 0, 32, 36);
        RoleImg.imgNPC[4][0][2] = bufimg.getSubimage(32 * 1, 36 * 0, 32, 36);

        RoleImg.imgNPC[4][1][0] = bufimg.getSubimage(32 * 0, 36 * 1, 32, 36);
        RoleImg.imgNPC[4][1][1] = bufimg.getSubimage(32 * 2, 36 * 1, 32, 36);
        RoleImg.imgNPC[4][1][2] = bufimg.getSubimage(32 * 1, 36 * 1, 32, 36);

        RoleImg.imgNPC[4][2][0] = bufimg.getSubimage(32 * 0, 36 * 2, 32, 36);
        RoleImg.imgNPC[4][2][1] = bufimg.getSubimage(32 * 2, 36 * 2, 32, 36);
        RoleImg.imgNPC[4][2][2] = bufimg.getSubimage(32 * 1, 36 * 2, 32, 36);

        RoleImg.imgNPC[4][3][0] = bufimg.getSubimage(32 * 0, 36 * 3, 32, 36);
        RoleImg.imgNPC[4][3][1] = bufimg.getSubimage(32 * 2, 36 * 3, 32, 36);
        RoleImg.imgNPC[4][3][2] = bufimg.getSubimage(32 * 1, 36 * 3, 32, 36);

        //load warrior img
        imageUrl = Load.class.getResource("/image/role/warrior_m.png");
        bufimg = ImageIO.read(imageUrl);
        //bufimg = ImageIO.read(new File("src/image/role/warrior_m.png"));
        RoleImg.imgNPC[5][0][0] = bufimg.getSubimage(32 * 0, 36 * 0, 32, 36);
        RoleImg.imgNPC[5][0][1] = bufimg.getSubimage(32 * 2, 36 * 0, 32, 36);
        RoleImg.imgNPC[5][0][2] = bufimg.getSubimage(32 * 1, 36 * 0, 32, 36);

        RoleImg.imgNPC[5][1][0] = bufimg.getSubimage(32 * 0, 36 * 1, 32, 36);
        RoleImg.imgNPC[5][1][1] = bufimg.getSubimage(32 * 2, 36 * 1, 32, 36);
        RoleImg.imgNPC[5][1][2] = bufimg.getSubimage(32 * 1, 36 * 1, 32, 36);

        RoleImg.imgNPC[5][2][0] = bufimg.getSubimage(32 * 0, 36 * 2, 32, 36);
        RoleImg.imgNPC[5][2][1] = bufimg.getSubimage(32 * 2, 36 * 2, 32, 36);
        RoleImg.imgNPC[5][2][2] = bufimg.getSubimage(32 * 1, 36 * 2, 32, 36);

        RoleImg.imgNPC[5][3][0] = bufimg.getSubimage(32 * 0, 36 * 3, 32, 36);
        RoleImg.imgNPC[5][3][1] = bufimg.getSubimage(32 * 2, 36 * 3, 32, 36);
        RoleImg.imgNPC[5][3][2] = bufimg.getSubimage(32 * 1, 36 * 3, 32, 36);
    }
}