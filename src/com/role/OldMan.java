package com.role;

import java.awt.Color;
import java.util.Random;

import javax.swing.JOptionPane;

import com.warehouse.Ware;

import app.Common;
import img.RoleImg;

public class OldMan extends Role implements Common {

    private String[] wordsList;
    private Talkloop tl = new Talkloop();

    public OldMan() {

        absX = OLDMAN_START_X - MAP_LEFTUP_X;
        absY = OLDMAN_START_Y - MAP_LEFTUP_Y;
        dx = 0;
        dy = 0;
        dir = 2;
        id = 0;
        img = RoleImg.imgNPC[id][2][2];
        name = NPC_NAME[id];
        color = Color.ORANGE;

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
            case 0:
                // ask player come
                wordsList = new String[4];
                wordsList[0] = "\"Come here!\"";
                wordsList[1] = "\"Here! I have sth for you!\"";
                wordsList[2] = "\"You're new here?\"";
                wordsList[3] = "(Caugh)";

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

                // mission 1, give the player stuff
                init(4);
                wordsList = new String[5];
                wordsList[0] = "\"Welcom to Ozchi Country!\"";
                wordsList[1] = "\"People here very like farming!\"";
                wordsList[2] = "\"We have 20 kinds of seeds!\"";
                wordsList[3] = "\"My name is Beast Senpai!\"";
                wordsList[4] = "\"To begin with, here you are!\"";

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

                JOptionPane.showMessageDialog(null, "You get 10 corn seeds\n and $100!");
                Ware.seedsInventory[18] += 10;
                Player.money += 100;
                Player.misson++;
                Player.words = "\"So kind of you!\"";
                words = "";
            case 1:
                init(1);
                wordsList = new String[2];
                wordsList[0] = "\"She is my wife.\"";
                wordsList[1] = "\"Talk to her.\"";

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

                Player.words = "\"No problem.\"";
                Player.isTalking = false;
                isTalking = false;
            default:
                wordsList = new String[4];
                wordsList[0] = "\"When I'm 24, I'm a student.\"";
                wordsList[1] = "\"I like red tea very much.\"";
                wordsList[2] = "\"I'm old now...Used to be a roger.\"";
                wordsList[3] = "(Caugh)";

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
                            words = "\"Where am I?\"";
                        } else {
                            if (Player.money >= 1000 && Ware.rawInventory[5] >= 1000) {
                            	if (NPCs.numRoger < 5) {
                                    words = "\"Roger reporting!\"";
                                    NPCs.numRoger++;
                                    NPCs.flagRoger = true;	
                            	} else 
                            		words = "\"No more rogers!\"";
                            } else {
                                words = "\"You dont have $1000 or 1000 melons...\"";
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
        wordsList[0] = "\"Ha! Good!\"";
        wordsList[1] = "\"Well done!\"";
        wordsList[2] = "\"Emmm...\"";
        wordsList[3] = "\"Ok, then...\"";

        int index = (int) (new Random().nextDouble() * (4.0 - 0.0));
        index = Math.max(index, 0);
        index = Math.min(index, 3);
        words = wordsList[index];
    }
}