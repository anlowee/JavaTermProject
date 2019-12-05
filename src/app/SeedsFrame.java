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

public class SeedsFrame extends JFrame implements Common {

    /**
     * this is seed frame, including all seeds you have
     */
    private static final long serialVersionUID = 1L;

    public JPanel seedsJp;

    public JLabel[] seedsSelect = new JLabel[CROPS_TOTAL_CATAGORIES];

    private int col, row;// locate the select

    public SeedsFrame() {

        col = row = 0;
        initUI();
    }

    private void initUI() {

        setUndecorated(true);
        setResizable(false);
        setSize(SUBFRAME_WIDTH, SUBFRAME_HEIGHT * 3 / 4);

        setTitle("Seeds");
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
    }

    private void loadPanel() {

        // load seeds panel
        seedsJp = new JPanel(new GridLayout(5, 4));
        getContentPane().add(seedsJp);

        for (int i = 0; i < CROPS_TOTAL_CATAGORIES; i++) {
            ImageIcon icn = new ImageIcon(CropsImg.imgCrops[i][0]);
            seedsSelect[i] = new JLabel(icn);
            seedsSelect[i].setText(CROPS_NAME[i] + " : " + Ware.seedsInventory[i]);
            seedsSelect[i].setBorder(BorderFactory.createLineBorder(Color.BLACK));
            if (i == 0)
                seedsSelect[i].setForeground(Color.RED);// default at No.0 seeds, use up, down, left, right to select

            seedsJp.add(seedsSelect[i]);
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
                    row = Math.min(4, row + 1);
                    break;
                case KeyEvent.VK_LEFT:
                    col = Math.max(0, col - 1);
                    break;
                case KeyEvent.VK_RIGHT:
                    col = Math.min(3, col + 1);
                    break;
                case KeyEvent.VK_ENTER:
                    int id = row * 4 + col;
                    if (Ware.seedsInventory[id] <= 0) {
                        JOptionPane.showMessageDialog(null, "You don't have this kind of seeds");
                        break;
                    }
                    Player.seedsSelect = id;
                    dispose();
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