package com.role;

import app.Common;
import img.RoleImg;

public class NPCAction extends Thread implements Common {

    public Role who;
    public int randomDelay = 0;

    public NPCAction(Role r) {

        who = r;
        who.tGif = 0;
        who.gif = 2;
    }

    @Override
    public void run() {

        while (who.isLived) {

            randomDelay++;
            if (randomDelay >= 20) {
                randomDelay = 0;
                who.randomAction();
            } 

            if (who.dx != 0 || who.dy != 0) {
                who.tGif++;
                if (who.tGif >= 10) {
                    who.gif++;
                    who.tGif = 0;
                }
                if (who.gif > 1)
                    who.gif = 0;
            } else {
                who.gif = 2;
            }
            who.img = RoleImg.imgNPC[who.id][who.dir][who.gif];

            if (!who.isTalking) {
                who.move();
            }
            try {
                Thread.sleep(DELAY);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}