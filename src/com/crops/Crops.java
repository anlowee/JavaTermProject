package com.crops;

import java.util.Random;

import javax.swing.JLabel;

import app.Common;

public class Crops extends JLabel implements Common {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public int absX;// absolute position
    public int absY;
    private boolean isFruited;
    private boolean isDead;
    private int numFlowers;
    private int numFruits;
    private int numSeeds;
    protected int cropsID;
    public int value;
    // 0-Seed, 1-Bud, 2-Seedling, 3-Flowered, 4-Fruited, 5-dead
    private short status;
    public long growth;
    public long current;
    public Growloop gl;
    // 0-null, 1-primary, 2-normal, 3-senior, 4-super
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
        public boolean halt = true;

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

                if (status == 3) {// sim the procedure of fruiting
                    double bias = 0.95 + new Random().nextDouble() * (1.20 - 0.95);
                    numFruits = (int) ((int) numFlowers * bias * FRUIT_BUFF[fruitBuff]);
                }
                if (status == 4) // gain seeds
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

        super();
        isFruited = false;
        isDead = false;

        numFlowers = 0;
        numFruits = 0;

        status = 0;

        cropsID = CROPS_ID[id];
        value = CROPS_VALUE[id];

        // initialize parts of parameters

        setNumFlowers(((int) (100.0 / value)) + ((int) (new Random().nextFloat() * (5.0))));

        // set grow time
        current = System.currentTimeMillis();

        growth = (long) (GROW_BUFF[growBuff] * BASIC_GROW_TIME / BASIC_SPEED) * 1000;

        gl = new Growloop();
        gl.start();
    }
}