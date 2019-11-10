package com.role;

import java.awt.Color;
import java.util.Random;

import javax.swing.JOptionPane;

import com.warehouse.Ware;

import app.Common;
import img.RoleImg;

public class OldWoman extends Role implements Common {

    private String[] wordsList;
    private Talkloop tl = new Talkloop();

    public OldWoman() {

        absX = OLDWOMAN_START_X - MAP_LEFTUP_X;
        absY = OLDWOMAN_START_Y - MAP_LEFTUP_Y;
        dx = 0;
        dy = 0;
        dir = 2;
        id = 1;
        img = RoleImg.imgNPC[id][2][2];
        name = NPC_NAME[id];
        color = Color.DARK_GRAY;

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
            case 1:
                // ask player come
                wordsList = new String[4];
                wordsList[0] = "\"Come here!\"";
                wordsList[1] = "\"Have you bought thing alredy?\"";
                wordsList[2] = "\"Here!\"";
                wordsList[3] = "\"Let me tell you something!\"";

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

                // mission 2, teach player buy in shop
                init(4);
                wordsList = new String[5];
                wordsList[0] = "\"There is a shop!\"";
                wordsList[1] = "\"Maybe you have see that...\"";
                wordsList[2] = "\"It's like a...auto seller?\"";
                wordsList[3] = "\"The old man is mean, I have more.\"";
                wordsList[4] = "\"Press 4, buy sth for urslf!\"";

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

                JOptionPane.showMessageDialog(null, "You get $1000!");
                Player.money += 1000;
                Player.misson++;
                words = "";
            case 2:
                init(3);
                wordsList = new String[4];
                wordsList[0] = "\"Priest is my daughter.\"";
                wordsList[1] = "\"She is a good girl.\"";
                wordsList[2] = "\"She can cure almost everthing.\"";
                wordsList[3] = "\"Let her teach you plant.\"";

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

                Player.words = "\"Ok, I'll go.\"";
                Player.isTalking = false;
                isTalking = false;
            default:
                wordsList = new String[4];
                wordsList[0] = "\"I know lots of warriors.\"";
                wordsList[1] = "\"I was beautiful...\"";
                wordsList[2] = "\"Do you see my glasses?\"";
                wordsList[3] = "\"I like rice, honsetly.\"";

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
                    	if (!isRecruiting) {
                            words = "\"Hello.\"";
                        } else {
                            if (Player.money >= 1000 && Ware.rawInventory[10] >= 2000) {
                                words = "\"Roger reporting!\"";
                                NPCs.numWarrior++;
                                NPCs.flagWarrior = true;
                            } else {
                                words = "\"You dont have $1000 or 2000 wheat...\"";
                            }
                            isRecruiting = false;
                        }
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

        wordsList = new String[4];
        wordsList[0] = "\"What's that?\"";
        wordsList[1] = "\"Let me think...\"";
        wordsList[2] = "\"Hah?\"";
        wordsList[3] = "\"Likely saw you somewhere...\"";

        int index = (int) (new Random().nextDouble() * (4.0 - 0.0));
        index = Math.max(index, 0);
        index = Math.min(index, 3);
        words = wordsList[index];
    }
}