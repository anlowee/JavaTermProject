package app;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.role.Player;
import com.warehouse.Ware;

import img.CropsImg;
import img.ItemImg;

public class ShopFrame extends JFrame implements Common {

    /**
     * this is shop frame, you can buy seeds and items here
     */
    private static final long serialVersionUID = 1L;

    public JPanel itemJp;

    public JLabel[] seedsSelect = new JLabel[CROPS_TOTAL_CATAGORIES];
    public JLabel[] itemsSelect = new JLabel[ITEM_TOTAL_CATAGORIES];

    private int col, row;// locate the select

    public ShopFrame() {

        col = row = 0;
        initUI();
    }

    private void initUI() {

        setResizable(false);
        setSize(SUBFRAME_WIDTH, SUBFRAME_HEIGHT);

        setTitle("$" + Player.money);
        setLocationRelativeTo(null);

        // show goods
        loadPanel();
    }

    private void updateSelect() {

        for (int i = 0; i < CROPS_TOTAL_CATAGORIES; i++) {
            seedsSelect[i].setBorder(BorderFactory.createLineBorder(Color.BLACK));
            seedsSelect[i].setForeground(Color.BLACK);
            if (i == row * 4 + col)
                seedsSelect[i].setForeground(Color.RED);// default at No.0 seeds, use up, down, left, right to select
        }

        for (int i = 0; i < ITEM_TOTAL_CATAGORIES; i++) {
            itemsSelect[i].setBorder(BorderFactory.createLineBorder(Color.BLACK));
            itemsSelect[i].setForeground(Color.BLACK);
            if (i == row * 4 + col - 20)
                itemsSelect[i].setForeground(Color.RED);
        }
    }

    private void loadPanel() {

        // load seeds panel
        itemJp = new JPanel(new GridLayout(8, 4));
        getContentPane().add(itemJp);

        for (int i = 0; i < CROPS_TOTAL_CATAGORIES; i++) {
            ImageIcon icn = new ImageIcon(CropsImg.imgCrops[i][0]);
            seedsSelect[i] = new JLabel(icn);
            seedsSelect[i].setText(CROPS_NAME[i] + " : $" + SEEDS_PRICE[i]);
            seedsSelect[i].setBorder(BorderFactory.createLineBorder(Color.BLACK));
            if (i == 0)
                seedsSelect[i].setForeground(Color.RED);// default at No.0 seeds, use up, down, left, right to select

            itemJp.add(seedsSelect[i]);
        }
        for (int i = 0; i < ITEM_TOTAL_CATAGORIES; i++) {
        	ImageIcon icn = new ImageIcon(ItemImg.buggBufferedImages[i]);
            itemsSelect[i] = new JLabel(icn);
            itemsSelect[i].setText(ITEM_NAME[i] + " : $" + ITEM_PRICE[i]);
            itemsSelect[i].setBorder(BorderFactory.createLineBorder(Color.BLACK));

            itemJp.add(itemsSelect[i]);
        }

        addKeyListener(new KeyAdapter() {

            @Override
            public void keyPressed(KeyEvent e) {
                int key = e.getKeyCode();
                switch (key) {
                case KeyEvent.VK_UP:
                    row = Math.max(0, row - 1);
                    break;
                case KeyEvent.VK_DOWN:
                    row = Math.min(7, row + 1);
                    break;
                case KeyEvent.VK_LEFT:
                    col = Math.max(0, col - 1);
                    break;
                case KeyEvent.VK_RIGHT:
                    col = Math.min(3, col + 1);
                    break;
                case KeyEvent.VK_ENTER:
                    int id = row * 4 + col;
                    int max;
                    if (id < 20) {	// if id < 20, player selected a seed
                        max = Player.money / SEEDS_PRICE[id];	// maximum seeds player can buy
                        if (max == 0) {	// player's money cannot afford even a single seed
                            JOptionPane.showMessageDialog(null, "You are too poor!");
                            break;
                        }
                        // input how many seeds to buy, must an integer
                        String inputValue = JOptionPane.showInputDialog(null, "How many to buy? 0~" + max);
                        if (inputValue == null || inputValue.equals(""))
                            inputValue = new String("0");
                        int num = Integer.valueOf(inputValue);
                        if (num < 0 || num > max)
                            JOptionPane.showMessageDialog(null, "Not correct number!", "Wrong Input",
                                    JOptionPane.ERROR_MESSAGE);

                        int cost = num * SEEDS_PRICE[id];
                        Player.money -= cost;
                        Ware.seedsInventory[id] += num;
                        JOptionPane.showMessageDialog(null,
                                num + " " + CROPS_NAME[id] + " seeds get!\n" + "Cost $" + cost);
                        setTitle("$" + Player.money);
                    } else {	// player selected an item
                    	// similar with above
                        max = Player.money / ITEM_PRICE[id - CROPS_TOTAL_CATAGORIES];
                        if (max == 0) {
                            JOptionPane.showMessageDialog(null, "You are too poor!");
                            break;
                        }
                        String inputValue = JOptionPane.showInputDialog(null, "How many to buy? 0~" + max);
                        if (inputValue == null || inputValue.equals(""))
                            inputValue = new String("0");
                        int num = Integer.valueOf(inputValue);
                        if (num < 0 || num > max)
                            JOptionPane.showMessageDialog(null, "Not correct number!", "Wrong Input",
                                    JOptionPane.ERROR_MESSAGE);

                        int cost = num * ITEM_PRICE[id - CROPS_TOTAL_CATAGORIES];
                        Player.money -= cost;
                        Ware.itemInventory[id - CROPS_TOTAL_CATAGORIES] = num;
                        JOptionPane.showMessageDialog(null,
                                num + " " + ITEM_NAME[id - CROPS_TOTAL_CATAGORIES] + " get!\n" + "Cost $" + cost);
                        setTitle("$" + Player.money);
                    }
                    break;
                case KeyEvent.VK_ESCAPE:
                    dispose();
                    break;
                }
                updateSelect();
            }
        });
    }
}