package com.farm;

import com.crops.Crops;

import app.Common;

public class Buff extends Thread implements Common {

    public long current;
    public int whichBuff = -1;
    public long buffTime;

    public void setBuff(int w, int b) {

        whichBuff = w;
        current = 0;
        switch (b) {
        case 1:
            buffTime = PRIMARY_BUFF_TIME;
            break;
        case 2:
            buffTime = NORMAL_BUFF_TIME;
            break;
        case 3:
            buffTime = SENIOR_BUFF_TIME;
            break;
        case 4:
            buffTime = SUPER_BUFF_TIME;
            break;
        }
    }

    @Override
    public void run() {

        while (true) {

            current += DELAY;
            if (current >= buffTime) {
                current = 0;
                // 0-grow, 1-fruit, 2-value
                int x = whichBuff;
                whichBuff = -1;
                switch (x) {
                case 0:
                    Crops.growBuff = 0;
                 
                    break;
                case 1:
                    Crops.fruitBuff = 0;
                    break;
                case 2:
                    Crops.valueBuff = 0;
                    break;
                }
                // System.out.println("Stop " + Crops.growBuff);
                break;
            }

            try {
                Thread.sleep(DELAY);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    } 
}