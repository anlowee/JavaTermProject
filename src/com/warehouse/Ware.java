package com.warehouse;

import com.crops.Crops;
import com.role.Player;

import app.Common;

public class Ware implements Common {

	// use array to store inventories
    public static int[] juiceInventory = new int[CROPS_TOTAL_CATAGORIES];
    public static int[] seedsInventory = new int[CROPS_TOTAL_CATAGORIES];
    public static int[] rawInventory = new int[CROPS_TOTAL_CATAGORIES];
    public static int[] itemInventory = new int[ITEM_TOTAL_CATAGORIES];

    public static void buy() {	// this method is realized in code segment in shop frame
    	//TODO
    }

    public static int sell(int n, int id) {

        rawInventory[id] -= n;	// remove what you sold from warehouse
        Player.money += (int)(VALUE_BUFF[Crops.valueBuff] * CROPS_VALUE[id] * n);	// earn money

        return (int)(VALUE_BUFF[Crops.valueBuff] * CROPS_VALUE[id] * n);	// return how much you earn
    }

    protected static int money;	// useless
}