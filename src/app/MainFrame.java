package app;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JToolBar;

import img.CropsImg;
import img.ItemImg;

public class MainFrame extends JFrame {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    
    private static JToolBar toolBar;
    private static JLabel seedsJl;
    private static JLabel rawJl;
    private static JLabel proJl;
    private static JLabel shopJl;
    private static JLabel itemJl;
    private static JLabel helpJl;

    public MainFrame() {
    	
        initUI();
    }

    private void initUI() {

        add(new BackgroundPanel());

        setUndecorated(true);
        setResizable(false);
        setSize(960, 960);
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // initial tool bar
        toolBar = new JToolBar();
        toolBar.setLayout(new GridLayout(1, 6));
        toolBar.setFloatable(false);
        getContentPane().add(toolBar, BorderLayout.SOUTH);

        seedsJl = new JLabel("SEEDS-1", new ImageIcon(CropsImg.imgCrops[0][0]), JLabel.CENTER);
        rawJl = new JLabel("RAW-2", new ImageIcon(CropsImg.imgCrops[4][5]), JLabel.CENTER);
        proJl = new JLabel("MISSION-3", new ImageIcon(CropsImg.imgCrops[9][5]), JLabel.CENTER);
        shopJl = new JLabel("SHOP-4", new ImageIcon(ItemImg.coinImg), JLabel.CENTER);
        itemJl = new JLabel("ITEM-5", new ImageIcon(ItemImg.swordImg), JLabel.CENTER);
        helpJl = new JLabel("HELP-6", new ImageIcon(ItemImg.helpImg), JLabel.CENTER);

        toolBar.add(seedsJl);
        toolBar.add(rawJl);
        toolBar.add(proJl);
        toolBar.add(shopJl);
        toolBar.add(itemJl);
        toolBar.add(helpJl);
    }    
}