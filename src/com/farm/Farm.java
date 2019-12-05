package com.farm;

import java.util.ArrayList;
import java.util.Random;

import com.crops.Crops;
import com.warehouse.Ware;

import app.Common;
import app.Map;

public class Farm implements Common {
	
	// this class include all things of a farm

    public static ArrayList<Crops> cropsArrayList = new ArrayList<Crops>();	// used to store all crops in farm currently
    public static Buff bf = new Buff();	// buff status

    public static void checkStatus() {
        int it = 0;
        while (it < cropsArrayList.size()) {	
            if (cropsArrayList.get(it).getIsDead()) {	// delete the dead
            	// locate tile at which tile
                int col = cropsArrayList.get(it).absX / TILE_WIDTH;	
                int row = cropsArrayList.get(it).absY / TILE_HEIGHT;
                // remove dead tile, set its ID as soil
                Map.tileSets[col][row] = SOIL;	
                cropsArrayList.remove(it);
                continue;
            } else if (!cropsArrayList.get(it).gl.isAlive())	// make sure alive crops keep doing grow loop after load the save file
            	cropsArrayList.get(it).gl.start();
            it++;
        }
    }

    public static boolean plant(int i, int x, int y) {	// i is crops' ID, etc. 5-Melon, (x, y) is position

        if (Ware.seedsInventory[i] <= 0)	// if you don't have seeds, you cannot plant
            return false;

        if (Map.tileSets[x / TILE_WIDTH][y / TILE_HEIGHT] != SOIL)	// if this tile is not soil, you cannot plant on it
            return false;

        // plant it
        Map.tileSets[x / TILE_WIDTH][y / TILE_HEIGHT] = i;	// set tile ID as crops ID
        // initialize a new crop object which you have planted
        Crops m = new Crops(i);
        m.absX = x;
        m.absY = y;
        cropsArrayList.add(m);
        Ware.seedsInventory[i]--;	// cost seeds

        return true;
    }

    public static boolean hay(Crops c) {
    	
        int i = c.getCropsID();	// id
        short s = c.getStatus();	// status;
        if (!c.getIsFruited())	// deny not mature
            return false;

        Crops m = c;
        int f;	// fruits
        int se;	// seeds

        // get fruit and seed
        f = m.getNumFruits();
        double bias = new Random().nextDouble() * (2.00);
        se = (int) (m.getNumSeeds() * bias);	// seeds random again
        Ware.seedsInventory[i] += se;	// put seeds in warehouse
        
        for (int j = 0; j < f; j++) {
            int id = m.getCropsID();

            Ware.rawInventory[id]++;	// put fruit in warehouse
        }

        // after haying, crops dead
        s = 5;	
        m.setIsDead(true);
        m.gl.halt = false;
        m.setIsFruited(false);

        m.setNumFruits(f);
        m.setNumSeeds(se);
        m.setStatus(s);

        return true;
    }

    public static void dig(Crops c) {

    	// dig out this crops, recycle a seed
        int id = c.getCropsID();
        c.setIsDead(true);
        Ware.seedsInventory[id]++;
    }
}