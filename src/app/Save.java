package app;

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import com.crops.Crops;
import com.farm.Farm;
import com.role.NPCs;
import com.role.Player;
import com.role.Role;
import com.warehouse.Ware;

public class Save implements Common {

	// relative with class Load, use output stream to write in Save.dat file
	
	public Save() throws IOException {

		File file = new File("D:\\Save.dat");
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		FileOutputStream fileOutputStream = new FileOutputStream(file);
		BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
		DataOutputStream dataOutputStream = new DataOutputStream(bufferedOutputStream);
		
		dataOutputStream.writeInt(Player.x);
		dataOutputStream.writeInt(Player.y);
		dataOutputStream.writeInt(Player.absX);
		dataOutputStream.writeInt(Player.absY);
		dataOutputStream.writeInt(Player.dx);
		dataOutputStream.writeInt(Player.dy);
		dataOutputStream.writeInt(Player.dir);
		dataOutputStream.writeInt(Player.gif);
		dataOutputStream.writeLong(Player.tGif);
		dataOutputStream.writeInt(Player.seedsSelect);
		dataOutputStream.writeInt(Player.misson);
		dataOutputStream.writeInt(Player.money);
		dataOutputStream.writeBoolean(Player.isMission4Complted);
		
		dataOutputStream.writeInt(Map.mapX);
		dataOutputStream.writeInt(Map.mapY);
		dataOutputStream.writeBoolean(Map.isOnMapDownSide);
		dataOutputStream.writeBoolean(Map.isOnMapLeftSide);
		dataOutputStream.writeBoolean(Map.isOnMapRightSide);
		dataOutputStream.writeBoolean(Map.isOnMapUpSide);		
		for (int i = 0; i < TILE_COL; i++) 
			for (int j = 0; j < TILE_ROW; j++)
				dataOutputStream.writeInt(Map.tileSets[i][j]);
		
		for (int i = 0; i < CROPS_TOTAL_CATAGORIES; i++) 
			dataOutputStream.writeInt(Ware.rawInventory[i]);
		for (int i = 0; i < CROPS_TOTAL_CATAGORIES; i++) 
			dataOutputStream.writeInt(Ware.seedsInventory[i]);
		for (int i = 0; i < ITEM_TOTAL_CATAGORIES; i++) 
			dataOutputStream.writeInt(Ware.itemInventory[i]);

		int roger = NPCs.numRoger;
		int warrior = NPCs.numWarrior;
		roger = Math.min(5, roger);
		warrior = Math.min(5, warrior);
		dataOutputStream.writeInt(roger);
		dataOutputStream.writeInt(warrior);
		
		dataOutputStream.writeShort(Crops.growBuff);
		dataOutputStream.writeShort(Crops.fruitBuff);
		dataOutputStream.writeShort(Crops.valueBuff);
		dataOutputStream.writeInt(Farm.bf.whichBuff);
		dataOutputStream.writeLong(Farm.bf.buffTime);
		dataOutputStream.writeLong(Farm.bf.current);
		
		dataOutputStream.writeInt(Farm.cropsArrayList.size());
		for (Crops crops: Farm.cropsArrayList) {
			dataOutputStream.writeInt(crops.getCropsID());
			dataOutputStream.writeInt(crops.absX);
			dataOutputStream.writeInt(crops.absY);
			dataOutputStream.writeBoolean(crops.getIsFruited());
			dataOutputStream.writeBoolean(crops.getIsDead());
			dataOutputStream.writeInt(crops.getNumFlowers());
			dataOutputStream.writeInt(crops.getNumFruits());
			dataOutputStream.writeInt(crops.getNumSeeds());
			dataOutputStream.writeShort(crops.getStatus());
			dataOutputStream.writeInt(crops.value);
			dataOutputStream.writeLong(crops.growth);
			dataOutputStream.writeLong(crops.current);
		}
		
		dataOutputStream.writeBoolean(NPCs.flagHealer);
		dataOutputStream.writeBoolean(NPCs.flagOldWoman);
		dataOutputStream.writeBoolean(NPCs.flagMage);
		dataOutputStream.writeBoolean(NPCs.flagRoger);
		dataOutputStream.writeBoolean(NPCs.flagWarrior);
		dataOutputStream.writeInt(NPCs.npc.size());
		for (Role role: NPCs.npc) {
			dataOutputStream.writeInt(role.id);
			dataOutputStream.writeInt(role.absX);
			dataOutputStream.writeInt(role.absY);
			dataOutputStream.writeInt(role.act);
			dataOutputStream.writeInt(role.dir);
			dataOutputStream.writeLong(role.tGif);
			dataOutputStream.writeInt(role.gif);
		}
		
		dataOutputStream.close();
	}
}
