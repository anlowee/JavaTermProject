package com.warehouse;

import com.crops.Crops;
import com.role.Player;

import app.Common;

public class Ware implements Common {

    public static int[] juiceInventory = new int[CROPS_TOTAL_CATAGORIES];
    public static int[] seedsInventory = new int[CROPS_TOTAL_CATAGORIES];
    public static int[] rawInventory = new int[CROPS_TOTAL_CATAGORIES];
    public static int[] itemInventory = new int[ITEM_TOTAL_CATAGORIES];

    public static void buy() {

    }

    public static int sell(int n, int id) {

        rawInventory[id] -= n;

        Player.money += (int)(VALUE_BUFF[Crops.valueBuff] * CROPS_VALUE[id] * n);

        return (int)(VALUE_BUFF[Crops.valueBuff] * CROPS_VALUE[id] * n);
    }

    protected static int money;
}