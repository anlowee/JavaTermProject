package com.role;

import java.awt.Color;
import java.util.Random;

import javax.swing.JOptionPane;

import com.farm.Farm;
import com.warehouse.Ware;

import app.Common;
import img.RoleImg;

public class Mage extends Role implements Common {

    private String[] wordsList;
    private Talkloop tl = new Talkloop();

    public Mage() {

        absX = MAGE_START_X - MAP_LEFTUP_X;
        absY = MAGE_START_Y - MAP_LEFTUP_Y;
        dx = 0;
        dy = 0;
        dir = 2;
        id = 3;
        img = RoleImg.imgNPC[id][2][2];
        name = NPC_NAME[id];
        color = Color.YELLOW;

        tl.start();
    }
    
    private class Talkloop extends Thread {

        int cnt, tot;
        boolean flag;

        public void init(int x) {
            // init
            cnt = 0;
            tot = x;
            flag = true;
        }

        @Override
        public void run() {

            switch (Player.misson) {
                case 3:
                // ask player come
                wordsList = new String[4];
                wordsList[0] = "\"You got a BUFF?\"";
                wordsList[1] = "\"I guess you dont know what to do!\"";
                wordsList[2] = "\"Here! Dont waste them!\"";
                wordsList[3] = "\"My patience is precious!\"";

                while (!isTalking) {
                    double mood = new Random().nextDouble();
                    if (mood >= 0.60) {// is mood good enough to talk
                        int index = (int) (new Random().nextDouble() * (4.0 - 0.0));
                        index = Math.max(index, 0);
                        index = Math.min(index, 3);
                        words = wordsList[index];
                    } else
                        words = "";

                    try {
                        Thread.sleep(DELAY * 60);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                // mission 4, teach player use buff
                init(11);
                wordsList = new String[12];
                wordsList[0] = "\"Holy light is useless!\"";
                wordsList[1] = "\"And magic is the power!\"";
                wordsList[2] = "\"All these buff made by me!\"";
                wordsList[3] = "\"Grow, Fruit, Value.\"";
                wordsList[4] = "\"Grow BUFF help to accelerate the growth.\"";
                wordsList[5] = "\"Fruit BUFF help to make more yield.\"";
                wordsList[6] = "\"Value BUFF help to sell more and earn more!\"";
                wordsList[7] = "\"Each BUFF have 4 levels.\"";
                wordsList[8] = "\"Primary, Normal, Senior, Super\"";
                wordsList[9] = "\"The higher level make stronger effection!\"";
                wordsList[10] = "\"But you can use one level BUFF of each BUFF.\"";
                wordsList[11] = "\"Now press 5 and use one.\"";

                while (flag) {

                    words = wordsList[cnt];
                    cnt++;

                    if (cnt > tot) {
                        flag = false;
                        cnt = 0;
                    }

                    try {
                        Thread.sleep(DELAY * 60);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                JOptionPane.showMessageDialog(null, "Use a BUFF and return to the Mage!");
                words = "";
                Player.isTalking = false;
                isTalking = false;

                // wait for player complete the misson
                init(2);
                wordsList = new String[3];
                wordsList[0] = "\"Hury up!\"";
                wordsList[1] = "\"Press P!\"";
                wordsList[2] = "\"Chop! Chop!\"";

                while (Farm.cropsArrayList.size() == 0 || !Player.isTalking) {
                    double mood = new Random().nextDouble();
                    if (mood >= 0.60) {// is mood good enough to talk
                        int index = (int) (new Random().nextDouble() * (3.0 - 0.0));
                        index = Math.max(index, 0);
                        index = Math.min(index, 3);
                        words = wordsList[index];
                    } else
                        words = "";

                    try {
                        Thread.sleep(DELAY * 60);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                // finish the misson
                init(3);
                wordsList = new String[4];
                wordsList[0] = "\"Suprisingly!\"";
                wordsList[1] = "\"And this is the power of magic!\"";
                wordsList[2] = "\"You can buy more BUFF in shop.\"";
                wordsList[3] = "\"I can give you some now.\"";

                while (flag) {

                    words = wordsList[cnt];
                    cnt++;

                    if (cnt > tot) {
                        flag = false;
                        cnt = 0;
                    }

                    try {
                        Thread.sleep(DELAY * 60);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                JOptionPane.showMessageDialog(null, "You get all kinds of Super Buff!");
                Ware.itemInventory[3]++;
                Ware.itemInventory[7]++;
                Ware.itemInventory[11]++;
                Player.misson++;
            case 4:
                init(12);
                wordsList = new String[13];
                wordsList[0] = "\"I here there're some evil bandits.\"";
                wordsList[1] = "\"We need to recruit some protectors $1000 per.\"";
                wordsList[2] = "\"Rogers're good at shooting.\"";
                wordsList[3] = "\"They like melons.\"";
                wordsList[4] = "\"Warriors're strong and powerful.\"";
                wordsList[5] = "\"But their favorates are bread.\"";
                wordsList[6] = "\"Which are made of wheat.\"";
                wordsList[7] = "\"Recuit each roger needs 1000 melons.\"";
                wordsList[8] = "\"Recuit each warrior needs 2000 wheat\"";
                wordsList[9] = "\"We need 5 rogers and 5 warriors.\"";
                wordsList[10] = "\"Recruit rogers from old man, press R.\"";
                wordsList[11] = "\"Recruit warriors form old woman.\"";
                wordsList[12] = "\"Bandits will atack soon, so hurry up!\"";

                while (flag) {

                    words = wordsList[cnt];
                    cnt++;

                    if (cnt > tot) {
                        flag = false;
                        cnt = 0;
                    }

                    try {
                        Thread.sleep(DELAY * 60);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                Player.words = "\"I know.\"";
                Player.isTalking = false;
                isTalking = false;
            default:
                wordsList = new String[5];
                wordsList[0] = "\"You'll regret this!\"";
                wordsList[1] = "\"My magic'll tear everything!\"";
                wordsList[2] = "\"&^!^$@&&^(taboo...)\"";
                wordsList[3] = "\"I hate that priest!\"";
                wordsList[4] = "\"Tomatoes seem to be powerful...\"";

                while (!isTalking) {
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
                        words = "\"You're wasting my time.\"";
                        isTalking = false;
                        Player.words = "\"Sorry.\"";
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
        wordsList[0] = "\"Magic is power!\"";
        wordsList[1] = "\"Concede me!\"";
        wordsList[2] = "\"A noob...\"";

        int index = (int) (new Random().nextDouble() * (3.0 - 0.0));
        index = Math.max(index, 0);
        index = Math.min(index, 2);
        words = wordsList[index];
    }
}