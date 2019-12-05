package com.role;

import java.util.Random;
import java.awt.Image;
import java.awt.Color;

import app.Common;
import app.Map;

import java.awt.Font;

public abstract class Role implements Common {

    protected double health;
    protected int spdMove;
    protected double exp;
    protected int level;
    public int x;
    public int y;// coordinate
    public int absX;
    public int absY;// absolute coordinate
    public int dx;
    public int dy;// to calculate move
    public int dir;// direction 0-W, 2-S, 3-A, 1-D
    public int gif;
    public int act = 5;
    public long tGif;
    public Image img;
    public int id;
    public String name = "";
    public String words = "";
    public Color color;

    public int talkAreaX;
    public int talkAreaY;
    public int talkAreaWidth; 
    public int talkAreaHeight;
    public int talkAreaCenterX;
    public int talkAreaCenterY;

    public boolean isTalking = false;
    public boolean isRecruiting = false;

    public static Font nameFont = new Font("Times New Roman", Font.BOLD, 12);
    public static Font wordsFont = new Font("Arial", Font.BOLD, 14);

    public NPCAction npcAction;

    public boolean isLived;

    public void talk() {
    	
    	
    }

    public void getTalkArea() {

        switch (dir) {
            case 0:
                talkAreaX = absX - PLAYER_WIDTH;
                talkAreaY = absY - PLAYER_HEIGHT;
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
                talkAreaX = absX + PLAYER_WIDTH / 2;
                talkAreaY = absY + PLAYER_HEIGHT / 2;
                talkAreaWidth = 0;
                talkAreaHeight = 0;
                break;
        }

        talkAreaCenterX = talkAreaX + talkAreaWidth / 2;
        talkAreaCenterY = talkAreaY + talkAreaHeight / 2;
    }

    public Role() {

    	// initialize
        npcAction = new NPCAction(this);
        isLived = true;
    }

    public void dead() {

        isLived = false;
    }

    public static int checkWhere(int x, int y) {

    	// get which kind of tile the role is standing on
        int col = x / TILE_WIDTH;
        int row = y / TILE_HEIGHT;

        if (col < 0 || col >= 50 || row < 0 || row >= 50)
            return WATER;

        return Map.tileSets[col][row];
    }

    public boolean isCollision(int x, int y, int id) {

    	// check if absolute coordinate (x, y) is collide with other things
    	
    	int bias = 100;	// because player's method move() is a bit different from NPC's, NPC need bias to fix
    	if (id == -1)
    		bias = 0;
        // is collide with map margin
        if (Map.isMapOnSide()) {
            if (x + PLAYER_WIDTH + bias >= MAP_WIDTH) {
                return true;
            }
            if (x - bias <= 0) {
                return true;
            }
            if (y + PLAYER_HEIGHT + bias >= MAP_HEIGHT) {
                return true;
            }
            if (y - bias <= 0) {
                return true;
            }
        }

        // is collide with map object
        int leftfoot = checkWhere(x, y + PLAYER_HEIGHT);
        int rightfoot = checkWhere(x + PLAYER_WIDTH, y + PLAYER_HEIGHT);
        if ((leftfoot == HOUSE || rightfoot == HOUSE) || (leftfoot == WATER || rightfoot == WATER))
            return true;

        return false;
    }

    public void randomAction() {

    	// randomly generate a integer between 0~20. 0~3 means four directions, else means static
        act = (int) (new Random().nextDouble() * (20.0 - 0.0));
    }

    public void move() {

        switch (act) {
        // W, S, A, D is control to move
        case 0:
            dir = 0;
            dy = -NPC_MOVE_SPEED;
            dx = 0;
            break;
        case 2:
            dir = 2;
            dy = NPC_MOVE_SPEED;
            dx = 0;
            break;
        case 3:
            dir = 3;
            dx = -NPC_MOVE_SPEED;
            dy = 0;
            break;
        case 1:
            dir = 1;
            dx = NPC_MOVE_SPEED;
            dy = 0;
            break;
        default:
            dx = 0;
            dy = 0;
            break;
        }
        
        if (!isCollision(absX + dx, absY + dy, id)) {
            absX += dx;
            absY += dy;
        }
    }

    // these features are not complete...
    public void isLevelUp() {
        // TODO
    }

    public void setHealth(double d) {

        health = d;
    }

    public double getHealth() {

        return health;
    }

    public void gainEXP(double d) {

        exp += d;
    }
}