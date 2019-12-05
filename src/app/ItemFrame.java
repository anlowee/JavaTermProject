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

import com.crops.Crops;
import com.farm.Farm;
import com.warehouse.Ware;

import img.ItemImg;

public class ItemFrame extends JFrame implements Common {

    /**
     * this frame is item frame
     */
    private static final long serialVersionUID = 1L;

    public JPanel itemJp;

    public JLabel[] itemsSelect = new JLabel[CROPS_TOTAL_CATAGORIES];

    private int col, row;	// locate the select

    public ItemFrame() {

        col = row = 0;
        initUI();
    }

    private void initUI() {

        setUndecorated(true);
        setResizable(false);
        setSize(SUBFRAME_WIDTH, SUBFRAME_HEIGHT * 3 / 5);

        setLocationRelativeTo(null);

        // show goods
        loadPanel();
    }

    private void updateSelect() {

        for (int i = 0; i < ITEM_TOTAL_CATAGORIES; i++) {
            itemsSelect[i].setText(ITEM_NAME[i] + " : " + Ware.itemInventory[i]);
            itemsSelect[i].setBorder(BorderFactory.createLineBorder(Color.BLACK));
            itemsSelect[i].setForeground(Color.BLACK);

            if (i == row * 4 + col)
                itemsSelect[i].setForeground(Color.RED);
        }
    }

    private void updateInventory() {

        for (int i = 0; i < ITEM_TOTAL_CATAGORIES; i++) {
            itemsSelect[i].setText(ITEM_NAME[i] + " : " + Ware.itemInventory[i]);
        }
    }

    private void loadPanel() {

        // load seeds panel
        itemJp = new JPanel(new GridLayout(3, 4));
        getContentPane().add(itemJp);

        for (int i = 0; i < ITEM_TOTAL_CATAGORIES; i++) {
        	ImageIcon icn = new ImageIcon(ItemImg.buggBufferedImages[i]);
            itemsSelect[i] = new JLabel(icn);
            itemsSelect[i].setText(ITEM_NAME[i] + " : " + Ware.itemInventory[i]);
            itemsSelect[i].setBorder(BorderFactory.createLineBorder(Color.BLACK));
            if (i == 0)
                itemsSelect[0].setForeground(Color.RED);// default at No.0 seeds, use up, down, left, right to select

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
                    row = Math.min(2, row + 1);
                    break;
                case KeyEvent.VK_LEFT:
                    col = Math.max(0, col - 1);
                    break;
                case KeyEvent.VK_RIGHT:
                    col = Math.min(3, col + 1);
                    break;
                case KeyEvent.VK_ENTER:
                    int id = row * 4 + col;
                    if (Ware.itemInventory[id] <= 0) {	// make sure player has this kind of item
                        JOptionPane.showMessageDialog(null, "You don't have this kind of item");
                        break;
                    }

                    Farm.bf.setBuff(row, col + 1);
                    switch (row) {
                    case 0:
                        if (Crops.growBuff > 0) {	// player cannot use two grow buff at same time
                            JOptionPane.showMessageDialog(null, ITEM_NAME[Crops.growBuff - 1] + " already exists!");
                            break;
                        }
                        // use it
                        JOptionPane.showMessageDialog(null, ITEM_NAME[id] + " now!");
                        Crops.growBuff = (short) (col + 1);
                        Ware.itemInventory[id]--;
                        Farm.bf.start();
                        updateInventory();
                        break;
                    case 1:
                        if (Crops.fruitBuff > 0) {
                            JOptionPane.showMessageDialog(null,
                                    ITEM_NAME[4 + Crops.fruitBuff - 1] + " already exists!");
                            break;
                        }
                        JOptionPane.showMessageDialog(null, ITEM_NAME[id] + " now!");
                        Crops.fruitBuff = (short) (col + 1);
                        Ware.itemInventory[id]--;
                        Farm.bf.start();
                        updateInventory();
                        break;
                    case 2:
                        if (Crops.growBuff > 0) {
                            JOptionPane.showMessageDialog(null, ITEM_NAME[8 + Crops.valueBuff - 1] + " already exists!");
                            break;
                        }
                        JOptionPane.showMessageDialog(null, ITEM_NAME[id] + " now!");
                        Crops.valueBuff = (short) (col + 1);
                        Ware.itemInventory[id]--;
                        Farm.bf.start();
                        updateInventory();
                        break;
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