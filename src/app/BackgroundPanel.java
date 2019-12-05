package app;

import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.crops.Crops;
import com.farm.Farm;
import com.role.Healer;
import com.role.Mage;
import com.role.NPCs;
import com.role.OldMan;
import com.role.OldWoman;
import com.role.Player;
import com.role.Roger;
import com.role.Role;
import com.role.Warrior;
import com.warehouse.Ware;

import img.CropsImg;
import img.MapImg;
import img.RoleImg;

public class BackgroundPanel extends JPanel implements Common {

    /**
	 * all game scene will be displayed here
	 */
	private static final long serialVersionUID = 1L;
	private Image imgMap;	// game map

    private Player player;	// a player object

    public void addNPC(Role role) {
    	
    	// to add a NPC to the game
        NPCs.npc.add(role);
    }

    public BackgroundPanel() {
    	
    	// initialize the panel
        initBoard();
    }

    public void checkNPCStatus() {

        // according to the plot's development, determine which NPC show in the game
        if (Player.misson >= 1 && !NPCs.flagOldWoman) {	// old woman will show up after mission 1 complete
            addNPC(new OldWoman());
            NPCs.flagOldWoman = true;
        }
        if (Player.misson >= 2 && !NPCs.flagHealer) {	// healer will show up after mission 1 complete
            addNPC(new Healer());
            NPCs.flagHealer = true;
        }
        if (Player.misson >= 3 && !NPCs.flagMage) {	// mage will show up after mission 1 complete
            addNPC(new Mage());
            NPCs.flagMage = true;
        }
        if (NPCs.flagRoger) {	// when there are 5 rogers and 5 warriors, player win
            if (NPCs.numRoger >= 5) 
                Player.words = "\"Rogers are enough.\"";	// rogers cannot more than 5
            else {
            	// recruit a roger, and player pay
                addNPC(new Roger());	
                Player.money -= 1000;
                Ware.rawInventory[5] -= 1000;
            }
            NPCs.flagRoger = false;
        }
        if (NPCs.flagWarrior) {
            if (NPCs.numWarrior >= 5) 
                Player.words = "\"Warriors are enough.\"";	// warrior cannot more than 5
            else {
                addNPC(new Warrior());
                // recruit a warrior, and player pay
                Player.money -= 1000;
                Ware.rawInventory[10] -= 2000;
            }
            NPCs.flagWarrior = false;
        }
     
        // make sure all alive NPC's threats
        for (Role role : NPCs.npc) {
            if (!role.npcAction.isAlive())
                role.npcAction.start();
        }
    }
    
    private void checkEnd() {
		
    	// check if player complete the final goal
    	if (NPCs.numWarrior >= 5 && NPCs.numRoger >= 5) {
    		JOptionPane.showMessageDialog(null, "Bandits are afraid of you!\nYou are the hero!\nBut you can still enjoy the life here.");
    		Player.words = "\"Not over yet!\"";
    		Player.isMission4Complted = true;
    	}
	}

    private void update() {

        //check crops
        Farm.checkStatus();
        //check NPC
        checkNPCStatus();
        //check ending
        if (!Player.isMission4Complted)
        	checkEnd();
    }

    private void drawCrops(Graphics g) {

        for (int i = 0; i < Farm.cropsArrayList.size(); i++) {

            Crops c = Farm.cropsArrayList.get(i);
            // get info
            int id = c.getCropsID();
            int st = c.getStatus();
            // turn absolute coordinate
            int x = c.absX + Map.mapX;
            int y = c.absY + Map.mapY;

            g.drawImage(CropsImg.imgCrops[id][st], x, y, null);
            g.drawImage(CropsImg.imgCrops[id][st], x + CROPS_WIDTH, y, null);
            g.drawImage(CropsImg.imgCrops[id][st], x, y + CROPS_HEIGHT, null);
            g.drawImage(CropsImg.imgCrops[id][st], x + CROPS_WIDTH, y + CROPS_HEIGHT, null);
        }
    }

    private void setMapPos() {
    	
    	// calculate the left-up corner of map's absolute coordinate
        int x = Math.abs(Player.dx);
        int y = Math.abs(Player.dy);

        // this map coordinate is relative to player's position, make sure that player at the center area of the frame,
        // if map is not reach the boundary
        if (Player.isOnViewDownSide)
            Map.mapY = Math.max(Map.mapY - y, MAP_Y_MIN);
        if (Player.isOnViewUpSide)
            Map.mapY = Math.min(Map.mapY + y, 0);
        if (Player.isOnViewLeftSide)
            Map.mapX = Math.min(Map.mapX + x, 0);
        if (Player.isOnViewRightSide)
            Map.mapX = Math.max(Map.mapX - x, MAP_X_MIN);
    }

    private void doGameloop() {

        update();	// update all object in game
        repaint();	// repaint those objects whose display info have been changed
    }

    private class TAdapter extends KeyAdapter {

    	// respond to key events in class Player
        @Override
        public void keyReleased(KeyEvent e) {

            player.keyReleased(e);
        }

        @Override
        public void keyPressed(KeyEvent e) {

            player.keyPressed(e);
        }
    }

    private class Gameloop extends Thread {

    	// all game events develop here
        @Override
        public void run() {

            while (true) {
                doGameloop();
                try {
                    Thread.sleep(DELAY);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void initBoard() {

        addKeyListener(new TAdapter());
        setFocusable(true);

		this.imgMap = MapImg.imgMap;
		//Player.init();
        player = new Player();

        // old man is the first NPC in game, so make sure he has shown in the map
        boolean flag = false;	// use to check if old man has been added to NPC list
        for (Role role: NPCs.npc) {
        	if (role.id == 0) {
        		flag = true;
        		break;
        	}
        }
        if (!flag)
        	addNPC(new OldMan());

        new playerGif().start();	// start to display player's GIF
    }

    @Override
    public void addNotify() {
        super.addNotify();

        Gameloop gl = new Gameloop();	// when this panel be set, game loop start
        gl.start();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        // draw map components here
        drawMap(g);	
        drawCrops(g);
        drawNPC(g);
    }

    public void drawMap(Graphics g) {

        g.drawImage(imgMap, Map.mapX, Map.mapY, null);
        Toolkit.getDefaultToolkit().sync();
    }

    public void drawPlayer(Graphics g) {

        g.drawImage(Player.img, Player.x, Player.y, null);

        // set font
        g.setColor(player.color); // font color
        g.setFont(Role.nameFont); // name font
        FontMetrics fm = g.getFontMetrics(Role.nameFont); // get middle
        int height = fm.getHeight();
        int width = fm.stringWidth(Player.name);

        // draw name
        g.drawString(Player.name, Player.x + PLAYER_WIDTH / 2 - width / 2, Player.y);

        // draw words
        g.setFont(Role.wordsFont);
        fm = g.getFontMetrics(Role.wordsFont);
        height = fm.getHeight();
        width = fm.stringWidth(Player.words);
        g.drawString(Player.words, Player.x + PLAYER_WIDTH / 2 - width / 2, Player.y - height);

        Toolkit.getDefaultToolkit().sync();
    }

    public void drawNPC(Graphics g) {

    	// sort all NPC including player by there absolute Y coordinate, small Y is behind big Y, if they are equal, small X
    	// is behind big X. Thus, it make player see all characters from forward to back, make it more real
    	Collections.sort(NPCs.npc, new Comparator<Role>() {
    		
    		@Override
    		public int compare(Role r1, Role r2) {
    			
    			if (r1.absY > r2.absY) 
    				return 1;
    			else if (r1.absY < r2.absY)
    				return -1;
    			else if (r1.absX > r2.absX)
    				return 1;
    			else return -1;
    		}
		});
    	
    	boolean flag = false;	// use flag to locate which one in list is player
        for (int i = 0; i < NPCs.npc.size(); i++) {
        	Role role = NPCs.npc.get(i);
        	if (i == 0 && Player.absY <= role.absY && !flag) {
        		drawPlayer(g);
        		flag = true;
        	}
        	
        	// code below is similar to method drawPlayer(), they draw all other NPC except player
            role.x = role.absX + Map.mapX;
            role.y = role.absY + Map.mapY;
            g.drawImage(role.img, role.x, role.y, null);

            g.setColor(role.color); // font color
            g.setFont(Role.nameFont); // font
            FontMetrics fm = g.getFontMetrics(Role.nameFont); // get middle
            int height = fm.getHeight();
            int width = fm.stringWidth(role.name);

            // draw name
            g.drawString(role.name, role.x + PLAYER_WIDTH / 2 - width / 2, role.y);

            // draw words
            g.setFont(Role.wordsFont);
            fm = g.getFontMetrics(Role.wordsFont);
            height = fm.getHeight();
            width = fm.stringWidth(role.words);
            g.drawString(role.words, role.x + PLAYER_WIDTH / 2 - width / 2, role.y - height);
            
            if (!flag && Player.absY >= role.absY && (i + 1) < NPCs.npc.size() && Player.absY < NPCs.npc.get(i + 1).absY) {
            	drawPlayer(g);
        		flag = true;
            }
            
            if (!flag)
            	drawPlayer(g);;
        }

        Toolkit.getDefaultToolkit().sync();
    }

    private class playerGif extends Thread {

    	// this method not only draw player's GIF but also make player randomly talk
        private int cnt = 1; // orgnize the talk

        @Override
        public void run() {

            while (player.isLived) {

                if (Player.isTalking) {
                    Player.words = "";
                } else {
                    cnt++;
                    if (cnt % 60 == 0) {
                        if (Player.words.equals("")) {
                            double mood = new Random().nextDouble();
                            if (mood >= 0.60) // is mood good enough to talk
                                player.talk();
                        } else
                            Player.words = "";
                        cnt = 0;
                    }
                }

                // draw GIF by a custom timer
                if (Player.dx != 0 || Player.dy != 0) {
                    Player.tGif++;
                    if (Player.tGif >= 10) {
                        Player.gif++;
                        Player.tGif = 0;
                    }
                    if (Player.gif > 1)
                        Player.gif = 0;
                } else {
                    Player.gif = 2;
                }

                player.move();	// move the player, in other word, update player's coordinate
                setMapPos();	// according to player's coordinate, update map's.
                Player.img = RoleImg.imgPlayer[Player.dir][Player.gif];	// change player's current image, to realize GIF affection

                try {
                    Thread.sleep(DELAY);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}