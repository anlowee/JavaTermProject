package com.role;

import java.util.ArrayList;

public class NPCs {

    public static ArrayList<Role> npc = new ArrayList<Role>();	// store all NPC
    
    // refer to the number of rogers and warriors, used to check if the final goal completed
    public static int numRoger = 0;
    public static int numWarrior = 0;
    
    // if the NPC should appear, for example, after mission 1 completed, old woman should show up, 
    // then set flagOldWoman = true
    public static boolean flagOldWoman = false;
    public static boolean flagHealer = false;
    public static boolean flagMage = false;
    public static boolean flagRoger = false;
    public static boolean flagWarrior = false;;
}