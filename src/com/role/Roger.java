package com.role;

import java.awt.Color;
import java.util.Random;

import app.Common;
import img.RoleImg;

public class Roger extends Role implements Common {

    private String[] wordsList;
    private Talkloop tl = new Talkloop();

    public Roger() {

        absX = ROGER_START_X - MAP_LEFTUP_X;
        absY = ROGER_START_Y - MAP_LEFTUP_Y;
        dx = 0;
        dy = 0;
        dir = 2;
        id = 4;
        img = RoleImg.imgNPC[id][2][2];
        name = NPC_NAME[id];
        color = Color.WHITE;

        tl.start();
    }

    private class Talkloop extends Thread {

        @Override
        public void run() {

            switch (Player.misson) {
            default:
                wordsList = new String[4];
                wordsList[0] = "\"Shoot! Shoot!\"";
                wordsList[1] = "\"I protect this place.\"";
                wordsList[2] = "\"Windary...\"";
                wordsList[3] = "\"Just melon is ok!\"";

                while (true) {
                	if (!isTalking) {
                        double mood = new Random().nextDouble();
                        if (mood >= 0.60) {// is mood good enough to talk
                            int index = (int) (new Random().nextDouble() * (4.0 - 0.0));
                            index = Math.max(index, 0);
                            index = Math.min(index, 3);
                            words = wordsList[index];
                        } else
                            words = "";
                    } else {
             
                        words = "\"Protect!\"";
                        
                        isTalking = false;
                        Player.words = "\"See u later.\"";
                        Player.isTalking = false;
                    }

                    try {
                        Thread.sleep(DELAY * 60);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    @Override
    public void talk() {
        
    	isTalking = true;

        wordsList = new String[3];
        wordsList[0] = "\"Anything Ok?\"";
        wordsList[1] = "\"Doing my time!\"";
        wordsList[2] = "\"Ready for fight!\"";

        int index = (int) (new Random().nextDouble() * (3.0 - 0.0));
        index = Math.max(index, 0);
        index = Math.min(index, 2);
        words = wordsList[index];
    }
}