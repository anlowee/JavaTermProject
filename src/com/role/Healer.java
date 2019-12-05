package com.role;

import java.awt.Color;
import java.util.Random;

import javax.swing.JOptionPane;

import com.farm.Farm;
import com.warehouse.Ware;

import app.Common;
import img.RoleImg;

public class Healer extends Role implements Common {

    private String[] wordsList;
    private Talkloop tl = new Talkloop();

    public Healer() {

    	// initialize
        absX = HEALER_START_X - MAP_LEFTUP_X;
        absY = HEALER_START_Y - MAP_LEFTUP_Y;
        dx = 0;
        dy = 0;
        dir = 2;
        id = 2;
        img = RoleImg.imgNPC[id][2][2];
        name = NPC_NAME[id];
        color = Color.BLUE;

        tl.start();
    }

    private class Talkloop extends Thread {

        int cnt, tot;
        boolean flag;

        public void init(int x) {
            // initial
            cnt = 0;
            tot = x;
            flag = true;
        }

        @Override
        public void run() {

            switch (Player.misson) {
            case 2:
                // ask player come, if player don't, healer will keep talking the words below
                wordsList = new String[4];
                wordsList[0] = "\"Noob!\"";
                wordsList[1] = "\"Have you planted anything?\"";
                wordsList[2] = "\"Here!\"";
                wordsList[3] = "\"My patience is precious!\"";
                // keep talking, until player start mission
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

                // mission 3, teach player plant
                init(7);
                wordsList = new String[8];
                wordsList[0] = "\"My dad gived you seeds.\"";
                wordsList[1] = "\"My mother give you money.\"";
                wordsList[2] = "\"No free lunch right?\"";
                wordsList[3] = "\"Go and plant a crop!\"";
                wordsList[4] = "\"Press 1 select what to plant first.\"";
                wordsList[5] = "\"Use Up-Down-Left-Right to choose.\"";
                wordsList[6] = "\"Press ESC to quit the menu.\"";
                wordsList[7] = "\"Walk to field and press P.\"";

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

                JOptionPane.showMessageDialog(null, "Plant a crop and return to the Priest!");
                words = "";
                Player.isTalking = false;
                isTalking = false;

                // wait for player complete the mission
                init(3);
                wordsList = new String[4];
                wordsList[0] = "\"Hury up!\"";
                wordsList[1] = "\"You don't know fields?\"";
                wordsList[2] = "\"Press P!\"";
                wordsList[3] = "\"Chop! Chop!\"";

                while (Farm.cropsArrayList.size() == 0 || !Player.isTalking) {
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

                // finish the mission
                init(6);
                wordsList = new String[7];
                wordsList[0] = "\"Finally.\"";
                wordsList[1] = "\"But well done.\"";
                wordsList[2] = "\"You can sell the fruits in 2.\"";
                wordsList[3] = "\"Money is always useful.\"";
                wordsList[4] = "\"You can remove them and get a seed by C.\"";
                wordsList[5] = "\"These thing will help you.\"";
                wordsList[6] = "\"Let the mage teach u how to use.\"";

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

                JOptionPane.showMessageDialog(null, "You get a Grow Primary Buff!(GPB)");
                Ware.itemInventory[0]++;
                Player.misson++;
            case 3:
                init(3);
                wordsList = new String[4];
                wordsList[0] = "\"She is my sister.\"";
                wordsList[1] = "\"But a bit crazy mage.\"";
                wordsList[2] = "\"Be careful.\"";
                wordsList[3] = "\"She is less patient than me.\"";

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

                Player.words = "\"Thank you.\"";
                Player.isTalking = false;
                isTalking = false;
            default:
            	// this part is similar in other NPC, so just annotation here
            	// when mission completed, healer will randomly talk words below
                wordsList = new String[4];
                wordsList[0] = "\"I'm the only priest.\"";
                wordsList[1] = "\"By the holy light!\"";
                wordsList[2] = "\"I love flowers, really.\"";
                wordsList[3] = "\"What to do...What to do...\"";

                // when mission completed, if player talk with healer, she will respond the words below
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
                        words = "\"Boring now.\"";
                        isTalking = false;
                        Player.words = "\"Have a nice day!\"";
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
        
    	// this part is similar in other NPC, so just annotation here
    	// randomly respond to player
        isTalking = true;

        wordsList = new String[3];
        wordsList[0] = "\"Fresh man?\"";
        wordsList[1] = "\"By the holy light!\"";
        wordsList[2] = "\"Waste of time...\"";

        int index = (int) (new Random().nextDouble() * (3.0 - 0.0));
        index = Math.max(index, 0);
        index = Math.min(index, 2);
        words = wordsList[index];
    }
}