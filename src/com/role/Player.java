package com.role;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.util.Random;

import javax.swing.JOptionPane;

import com.farm.Farm;
import app.SeedsFrame;
import app.ShopFrame;
import img.RoleImg;
import app.ItemFrame;
import app.Map;
import app.MenuFrame;
import app.RawFrame;

public class Player extends Role {

	// used to check if the map should be moved
    public static boolean isOnViewDownSide;
    public static boolean isOnViewUpSide;
    public static boolean isOnViewLeftSide;
    public static boolean isOnViewRightSide;
    
    public static boolean isTalking = false;	// check if player start a talking with others
    
    public static boolean isMission4Complted = false;	// check if mission completed
    
    // move parameter
    public static int dir;	// direction
    // coordinate in frame(used to draw)
    public static int x;
    public static int y;
    // absolute coordinate
    public static int absX;
    public static int absY;
    // move speed
    public static int dx;
    public static int dy;
    
    public static int id;	// player ID
    
    // GIF parameter
    public static int gif;	
    public static long tGif;
    public static Image img;	// current image(relative to current status)

    public static int seedsSelect;	// seeds'id that player select

    public static int money;
    public static int misson = 0;	// record the current mission ID

    public static String words = "";	// what player will talk
    public static String name = "";	// player's name

    // randomly talking words(10 sentences)
    private String[] wordsList = { "La~La~La~", "\"It's no use going back to yesterday.\"",
            "\"The choice must be yours.\"", "\"Which way I ought to go from here?\"", "\"Then it doesn't matter.\"",
            "\" There is a place.\"", "\"A land full of wonder!\"", "\"Are you going to die?\"", "\"Transform!\"",
            "\"Only if me can find it.\"", "\"Everything's got a moral.\"", "\"How late it's getting!\"",
            "\"Off with her head!\"", "\"Begin at the beginning and go on.\"", "\"Curiouser and curiouser.\"" };
    
    @Override
    public void getTalkArea() {
    	
    	// this method used to get talk area, which is a rectangle in front of player and NPC,
    	// when player enter another NPC's talk area, player can talk with him
    	switch (dir) {
        case 0:
        	// talk area left-up corner coordinate
            talkAreaX = absX - PLAYER_WIDTH;
            talkAreaY = absY - PLAYER_HEIGHT;
            // talk area rectangle width and height
            talkAreaWidth = 3 * PLAYER_WIDTH;
            talkAreaHeight = PLAYER_HEIGHT;
            break;
        case 2:
            talkAreaX = absX - PLAYER_WIDTH;
            talkAreaY = absY + PLAYER_HEIGHT;
            talkAreaWidth = 3 * PLAYER_WIDTH;
            talkAreaHeight = PLAYER_HEIGHT;
            break;
        case 3:
            talkAreaX = absX - PLAYER_WIDTH;
            talkAreaY = absY - PLAYER_HEIGHT / 2;
            talkAreaWidth = PLAYER_WIDTH;
            talkAreaHeight = 2 * PLAYER_HEIGHT;
            break;
        case 4:
            talkAreaX = absX + PLAYER_WIDTH;
            talkAreaY = absY - PLAYER_HEIGHT / 2;
            talkAreaWidth = PLAYER_WIDTH;
            talkAreaHeight = 2 * PLAYER_HEIGHT;
            break;
        default:
        	// meaningless, just ignore
            talkAreaX = absX + PLAYER_WIDTH / 2;
            talkAreaY = absY + PLAYER_HEIGHT / 2;
            talkAreaWidth = 0;
            talkAreaHeight = 0;
            break;
    }

    // talk area center's coordinate
    talkAreaCenterX = talkAreaX + talkAreaWidth / 2;
    talkAreaCenterY = talkAreaY + talkAreaHeight / 2;
    }
    
    public static void init() {
    	
    	// initialize
    	x = PLAYER_START_X;
        y = PLAYER_START_Y;

        absX = PLAYER_START_X - MAP_LEFTUP_X;
        absY = PLAYER_START_Y - MAP_LEFTUP_Y;

        dx = 0;
        dy = 0;
        dir = 2;

        seedsSelect = -1;	// default, select none

        money = 0;
        
        id = -1;	// player's special ID

        isOnViewDownSide = false;
        isOnViewUpSide = false;
        isOnViewLeftSide = false;
        isOnViewRightSide = false;
        
        gif = 0;
        tGif = 2;
        
        img = RoleImg.imgPlayer[dir][2];
    }

    public Player() {

        name = "You";
        color = Color.RED;
    }

    public boolean isOnViewSide(int x, int y) {

        Map.isMapOnSide();

        // check if player is on view boundary and then determine if map should be moved
        if (!Map.isOnMapRightSide && x + PLAYER_WIDTH >= PLAYER_VIEW_RIGHTSIDE) {
            isOnViewRightSide = true;
            return true;
        } else
            isOnViewRightSide = false;

        if (!Map.isOnMapLeftSide && x <= PLAYER_VIEW_LEFTSIDE) {
            isOnViewLeftSide = true;
            return true;
        } else
            isOnViewLeftSide = false;

        if (!Map.isOnMapDownSide && y + PLAYER_HEIGHT >= PLAYER_VIEW_DOWNSIDE) {
            isOnViewDownSide = true;
            return true;
        } else
            isOnViewDownSide = false;

        if (!Map.isOnMapUpSide && y <= PLAYER_VIEW_UPSIDE) {
            isOnViewUpSide = true;
            return true;
        } else
            isOnViewUpSide = false;

        return false;
    }

    public void keyReleased(KeyEvent e) {

    	// when WSAD are released, player stop
        int key = e.getKeyCode();
        switch (key) {
        case KeyEvent.VK_W:
        case KeyEvent.VK_S:
        case KeyEvent.VK_A:
        case KeyEvent.VK_D:
            dx = 0;
            dy = 0;
            break;
        default:
            break;
        }
    }

    public void keyPressed(KeyEvent e) {

    	// get which tile are player standing on
        int col = (absX + PLAYER_WIDTH / 2) / TILE_WIDTH;
        int row = (absY + PLAYER_HEIGHT / 2) / TILE_HEIGHT;
        int id = Map.tileSets[col][row];// use player's center to location

        int key = e.getKeyCode();
        switch (key) {
        // W, S, A, D is control to move, when talking, player cannot move
        case KeyEvent.VK_W:
            if (isTalking)
                break;
            dir = 0;	// up
            if (isOnViewUpSide)
                break;
            dy = -HUMAN_MOVE_SPEED;
            dx = 0;
            break;
        case KeyEvent.VK_S:
            if (isTalking)
                break;
            dir = 2;	// down
            if (isOnViewDownSide)
                break;
            dy = HUMAN_MOVE_SPEED;
            dx = 0;
            break;
        case KeyEvent.VK_A:
            if (isTalking)
                break;
            dir = 3;	// left
            if (isOnViewLeftSide)
                break;
            dx = -HUMAN_MOVE_SPEED;
            dy = 0;
            break;
        case KeyEvent.VK_D:
            if (isTalking)
                break;
            dir = 1;	// right
            if (isOnViewRightSide)
                break;
            dx = HUMAN_MOVE_SPEED;
            dy = 0;
            break;
        case KeyEvent.VK_P:	// plant
            if (id == SOIL) {	// check if it's the legal tile
                if (seedsSelect != -1) {	// check if there're current seeds you select
                    boolean flag = Farm.plant(seedsSelect, col * TILE_WIDTH, row * TILE_HEIGHT);
                    if (!flag) {	// plant failed
                        words = "\"I don't have this kind of seeds...\"";
                        seedsSelect = -1;
                    }
                } else
                    words = "\"I must choose a kind of seeds first...\"";	// seedsSelect = -1 means you have not selected a seed
            } else
                words = "\"Must plant on field...\"";
            break;
        case KeyEvent.VK_T:	// talk
            getTalkArea();
            boolean flag = false;	// used to check if player have found somebody to talk
            for (Role role : NPCs.npc) {
            	// check each NPC if they are in player's talk area(or if player in their talk area, no difference)
                role.getTalkArea();
                if (Math.abs(talkAreaCenterX - role.talkAreaCenterX) < Math.abs(talkAreaWidth + role.talkAreaWidth) / 2
                        && Math.abs(talkAreaCenterY - role.talkAreaCenterY) < Math
                                .abs(talkAreaHeight + role.talkAreaHeight)) {
                    flag = true;
                    isTalking = true;
                    role.talk();
                    break;
                }
            }
            if (!flag)
                words = "\"Nobody to talk...\"";
            break;
        case KeyEvent.VK_R:	// recruit(similar with talk)
            if (Player.misson < 4)
                break;

            getTalkArea();
            flag = false;
            for (Role role : NPCs.npc) {
                role.getTalkArea();
                if (Math.abs(talkAreaCenterX - role.talkAreaCenterX) < Math.abs(talkAreaWidth + role.talkAreaWidth) / 2
                        && Math.abs(talkAreaCenterY - role.talkAreaCenterY) < Math
                                .abs(talkAreaHeight + role.talkAreaHeight)) {
                    if (role.id == 0 || role.id == 1) {
                        flag = true;
                        isTalking = true;
                        role.isRecruiting = true;
                        role.talk();
                        break;
                    }
                }
            }
            if (!flag)
                words = "\"Where're the old couple...\"";
            break;
        case KeyEvent.VK_H:	// hay
            flag = false;
            
            if (id >= 0 && id <= CROPS_TOTAL_CATAGORIES - 1) {
                // locate which crops to hay
                for (int i = 0; i < Farm.cropsArrayList.size(); i++) {
                    if (Farm.cropsArrayList.get(i).absX == col * TILE_WIDTH
                            && Farm.cropsArrayList.get(i).absY == row * TILE_HEIGHT) {
                        flag = Farm.hay(Farm.cropsArrayList.get(i));
                        break;
                    }
                }
            } else {
                words = "\"Must hay the mature crops on field...\"";
                break;
            }

            if (!flag)
                words = "\"They aren't mature...\"";
            break;
        case KeyEvent.VK_C:	// dig
            if (id >= 0 && id <= 19) {
                // locate which crops to dig
                for (int i = 0; i < Farm.cropsArrayList.size(); i++) {
                    if (Farm.cropsArrayList.get(i).absX == col * TILE_WIDTH
                            && Farm.cropsArrayList.get(i).absY == row * TILE_HEIGHT) {
                        Farm.dig(Farm.cropsArrayList.get(i));
                        break;
                    }
                }
            }
            break;
        case KeyEvent.VK_ESCAPE: 
        	MenuFrame mf = new MenuFrame();
        	mf.setVisible(true);
        	break;
        case KeyEvent.VK_1:	// open seed frame
            SeedsFrame sf = new SeedsFrame();
            sf.setVisible(true);
            break;
        case KeyEvent.VK_2:	// open raw frame
            RawFrame rf = new RawFrame();
            rf.setVisible(true);
            break;
        case KeyEvent.VK_3:	// show the current mission
            words = "\"" + MISSION_NAME[misson] + "\"";
            break;
        case KeyEvent.VK_4:	// open shop frame
            ShopFrame shf = new ShopFrame();
            shf.setVisible(true);
            break;
        case KeyEvent.VK_5:	// open item frame
            ItemFrame itf = new ItemFrame();
            itf.setVisible(true);
            break;
        case KeyEvent.VK_6: // open help frame
            String message = HELP;
            JOptionPane.showMessageDialog(null, message, "help", JOptionPane.PLAIN_MESSAGE);
            break;
        default:
            break;
        }
    }

    @Override
    public void talk() {

    	// randomly talk
        int index = (int) (new Random().nextDouble() * (10.0 - 0.0));
        index = Math.max(index, 0);
        index = Math.min(index, 9);
        words = wordsList[index];
    }

    @Override
    public void move() {

        if (isTalking)	// cannot move when talking
            return;

        boolean flag = isOnViewSide(x + dx, y + dy);
        // get absolute coordinate
        absX = x - Map.mapX;
        absY = y - Map.mapY;
        if (!isCollision(absX + dx, absY + dy, id) && !flag) {	// if there's no obstacle in the way, player move
            x += dx;
            y += dy;
        }
    }

}