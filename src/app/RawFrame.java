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

public class RawFrame extends JFrame implements Common {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public JPanel rawJp;

    public static JLabel[] rawSelect = new JLabel[CROPS_TOTAL_CATAGORIES];

    private int col, row;// locate the select

    public RawFrame() {

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
            rawSelect[i].setBorder(BorderFactory.createLineBorder(Color.BLACK));
            rawSelect[i].setForeground(Color.BLACK);
            if (i == row * 4 + col)
                rawSelect[i].setForeground(Color.RED);// default at No.0 raw, use up, down, left, right to select
        }
    }

    private void updateInventory() {

        for (int i = 0; i < CROPS_TOTAL_CATAGORIES; i++) {
            rawSelect[i].setText(CROPS_NAME[i] + " : " + Ware.rawInventory[i]);
        }
    }

    private void loadPanel() {

        // load raw panel
        rawJp = new JPanel(new GridLayout(5, 4));
        getContentPane().add(rawJp);

        for (int i = 0; i < CROPS_TOTAL_CATAGORIES; i++) {
            ImageIcon icn = new ImageIcon(CropsImg.imgCrops[i][5]);
            rawSelect[i] = new JLabel(icn);
            rawSelect[i].setText(CROPS_NAME[i] + " : " + Ware.rawInventory[i]);
            rawSelect[i].setBorder(BorderFactory.createLineBorder(Color.BLACK));
            if (i == 0)
                rawSelect[i].setForeground(Color.RED);// default at No.0 raw, use up, down, left, right to select

            rawJp.add(rawSelect[i]);
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
                    int inv = Ware.rawInventory[id];
                    if (inv <= 0) {
                        JOptionPane.showMessageDialog(null, "You don't have this kind of raw!");
                        break;
                    }

                    String inputValue = JOptionPane.showInputDialog(null, "How many to sell? 0~" + inv);
                    int num = Integer.valueOf(inputValue);
                    if (num < 0 || num > inv) {
                        JOptionPane.showMessageDialog(null, "Not correct number!", "Wrong Input!", JOptionPane.ERROR_MESSAGE);
                        break;
                    }
                    
                    int earn = Ware.sell(num, id);
                    JOptionPane.showMessageDialog(null, num + " " + CROPS_NAME[id] + " sold!\n$" + earn + " get!");
                    setTitle("$" + Player.money);
                    updateInventory();
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