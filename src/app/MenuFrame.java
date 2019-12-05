package app;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MenuFrame extends JFrame implements Common {
	
	/**
	 * menu frame
	 */
	private static final long serialVersionUID = 1L;

	private JPanel selectJPanel;
	
	private JButton resumeButton;
	private JButton quiButton;

	private void initUI() {
		
		setUndecorated(true);
        setResizable(false);
        setSize(SUBFRAME_WIDTH / 4, SUBFRAME_HEIGHT / 8);

        setLocationRelativeTo(null);
        
        resumeButton = new JButton("Resume");
        quiButton = new JButton("Quit&Save");
        
        selectJPanel = new JPanel(new GridLayout(2, 1));
        selectJPanel.add(resumeButton);
        selectJPanel.add(quiButton);
        
        getContentPane().add(selectJPanel);
        addListener();
	}
	
	private void addListener() {
		
		resumeButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				dispose();	// nothing happened
			}
		});
		
		quiButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				try {
					new Save();	// automatically save
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				System.exit(0);	// quit the game, close entire program
			}
		});
	}
	
	public MenuFrame() {
		
		initUI();
	}
}
