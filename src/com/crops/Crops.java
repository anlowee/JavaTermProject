package com.crops;

import java.util.Random;

import javax.swing.JLabel;

import app.Common;

public class Crops extends JLabel implements Common {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    // absolute position
    public int absX;
    public int absY;
    
    // check status
    private boolean isFruited;
    private boolean isDead;
    
    // some parameters
    private int numFlowers;	// how many flowers will bloom
    private int numFruits;	// how many fruits when mature
    private int numSeeds;	// how many seeds will you get
    protected int cropsID;	// 0~20
    public int value;
    
    // status
    // 0-Seed, 1-Bud, 2-Seedling, 3-Flowered, 4-Fruited, 5-dead
    private short status;	// number by above
    public long growth;	// growth time
    public long current;	// current time
    public Growloop gl;	// it's a threat, do grow loop
    
    // buff status
    // 0-no-buff, 1-primary, 2-normal, 3-senior, 4-super
    public static short growBuff;
    public static short fruitBuff;
    public static short valueBuff;

    public void setIsFruited(boolean b) {
        isFruited = b;
    }

    public boolean getIsFruited() {
        return isFruited;
    }

    public void setIsDead(boolean b) {
        isDead = b;
    }

    public boolean getIsDead() {
        return isDead;
    }

    public void setNumFlowers(int i) {
        numFlowers = i;
    }

    public int getNumFlowers() {
        return numFlowers;
    }

    public void setNumFruits(int i) {
        numFruits = i;
    }

    public int getNumFruits() {
        return numFruits;
    }

    public void setNumSeeds(int i) {
        numSeeds = i;
    }

    public int getNumSeeds() {
        return numSeeds;
    }

    public int getCropsID() {
        return cropsID;
    }

    public void setStatus(short s) {

        status = s;
    }

    public short getStatus() {

        return status;
    }

    public class Growloop extends Thread {

        long current = 0;
        public boolean halt = true;	// use to halt the threat

        @Override
        public void run() {

            while (halt) {

                // do grow loop
                current += DELAY;
                growth = (long) (GROW_BUFF[growBuff] * BASIC_GROW_TIME / BASIC_SPEED) * 1000;
                if (current >= growth && status <= 4) {
                    current = 0;
                    status++;
                }

                if (status == 3) {	// fruits, less than flowers
                    double bias = 0.95 + new Random().nextDouble() * (1.20 - 0.95);
                    numFruits = (int) ((int) numFlowers * bias * FRUIT_BUFF[fruitBuff]);
                }
                if (status == 4)	// gain seeds, random between 0~3
                    numSeeds = 0 + ((int) (new Random().nextFloat() * (3.0 - 0.0)));
                if (status == 5) {
                    isFruited = true;
                }

                try {
                    Thread.sleep(DELAY);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public Crops(int id) {

    	// initialize
        super();
        isFruited = false;
        isDead = false;

        numFlowers = 0;
        numFruits = 0;

        status = 0;

        cropsID = CROPS_ID[id];
        value = CROPS_VALUE[id];

        setNumFlowers(((int) (100.0 / value)) + ((int) (new Random().nextFloat() * (5.0))));	// flowers of crops is determined when it's planted

        // set grow time
        current = System.currentTimeMillis();
        growth = (long) (GROW_BUFF[growBuff] * BASIC_GROW_TIME / BASIC_SPEED) * 1000;

        // do grow loop
        gl = new Growloop();
        gl.start();
    }
}