package com.farm;

import java.util.ArrayList;
import java.util.Random;

import com.crops.Crops;
import com.warehouse.Ware;

import app.Common;
import app.Map;

public class Farm implements Common {

    public static ArrayList<Crops> cropsArrayList = new ArrayList<Crops>();// used to store all crops in farm currently
    public static Buff bf = new Buff();

    public static void checkStatus() {
        int it = 0;
        while (it < cropsArrayList.size()) {// delete the dead
            if (cropsArrayList.get(it).getIsDead()) {
                int col = cropsArrayList.get(it).absX / TILE_WIDTH;
                int row = cropsArrayList.get(it).absY / TILE_HEIGHT;
                Map.tileSets[col][row] = SOIL;
                cropsArrayList.remove(it);
                continue;
            } else if (!cropsArrayList.get(it).gl.isAlive())
            	cropsArrayList.get(it).gl.start();
            it++;
        }
    }

    public static boolean plant(int i, int x, int y) {// i is crops' ID, etc. 5-Melon, (x, y) is positon

        if (Ware.seedsInventory[i] <= 0)
            return false;

        if (Map.tileSets[x / TILE_WIDTH][y / TILE_HEIGHT] != SOIL)
            return false;

        Map.tileSets[x / TILE_WIDTH][y / TILE_HEIGHT] = i;
        Crops m = new Crops(i);
        m.absX = x;
        m.absY = y;
        cropsArrayList.add(m);
        Ware.seedsInventory[i]--;

        return true;
    }

    public static boolean hay(Crops c) {
        int i = c.getCropsID();// id
        short s = c.getStatus();// status;
        if (!c.getIsFruited())// deny unmature
            return false;

        Crops m = c;
        int f;// fruits
        int se;// seeds

        f = m.getNumFruits();
        double bias = new Random().nextDouble() * (2.00);
        se = (int) (m.getNumSeeds() * bias);
        Ware.seedsInventory[i] += se;
        
        for (int j = 0; j < f; j++) {
            int id = m.getCropsID();

            Ware.rawInventory[id]++;
        }

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

        int id = c.getCropsID();
        c.setIsDead(true);
        Ware.seedsInventory[id]++;
    }
}