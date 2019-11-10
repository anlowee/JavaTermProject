package com.role;

import java.awt.Color;
import java.util.Random;

import app.Common;
import img.RoleImg;

public class Warrior extends Role implements Common {

    private String[] wordsList;
    private Talkloop tl = new Talkloop();

    public Warrior() {

        absX = WARRIOR_START_X - MAP_LEFTUP_X;
        absY = WARRIOR_START_Y - MAP_LEFTUP_Y;
        dx = 0;
        dy = 0;
        dir = 2;
        id = 5;
        img = RoleImg.imgNPC[id][2][2];
        name = NPC_NAME[id];
        color = Color.CYAN;

        tl.start();
    }

    private class Talkloop extends Thread {

        @Override
        public void run() {

            switch (Player.misson) {
            default:
                wordsList = new String[4];
                wordsList[0] = "\"Fight or death!\"";
                wordsList[1] = "\"Let's resolve this quickly!\"";
                wordsList[2] = "\"I'm the strongest one!\"";
                wordsList[3] = "\"Bread gives me power!\"";

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
             
                        words = "\"Fight!\"";
                        
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
        wordsList[0] = "\"Hah!\"";
        wordsList[1] = "\"You?\"";
        wordsList[2] = "\"So weak...\"";

        int index = (int) (new Random().nextDouble() * (3.0 - 0.0));
        index = Math.max(index, 0);
        index = Math.min(index, 2);
        words = wordsList[index];
    }
}