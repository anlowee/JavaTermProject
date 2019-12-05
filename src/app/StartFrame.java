package app;

import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class StartFrame extends JFrame implements Common {
	
	/**
	 * this is where the entire game begin
	 */
	private static final long serialVersionUID = 1L;
	private JPanel menuJPanel;
	private JButton startButton;
	private JButton how2playButton;
	private JButton supportButton;
	private JButton quitButton;
		
	public StartFrame() {
		
		initUI();
	}
	
	private void initUI() {
		
		setUndecorated(true);
		setResizable(false);
        setSize(SUBFRAME_WIDTH / 4, SUBFRAME_HEIGHT / 4);

        setTitle("Hay Day");
        setLocationRelativeTo(null);
        
        // start menu
        startButton = new JButton("Start");
        how2playButton = new JButton("Guide");
        supportButton = new JButton("Support Me");
        quitButton = new JButton("Quit");
        
        menuJPanel = new JPanel(new GridLayout(4, 1));
        menuJPanel.add(startButton);
        menuJPanel.add(how2playButton);
        menuJPanel.add(supportButton);
        menuJPanel.add(quitButton);
        
        getContentPane().add(menuJPanel);
        
        addListener();
	}
	
	private void addListener() {
		
		startButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				// initialize main frame, do game loop
				MainFrame ex = new MainFrame();
				ex.setVisible(true);
				setVisible(false);
			}
		});
		
		how2playButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {

				JOptionPane.showMessageDialog(null, HELP);
			}
		});
		
		supportButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				JOptionPane.showMessageDialog(null, "WHU Hongyi Colledge\nWEI XIAOCHONG\nID: 2018302060342");
			}
		});
		
		quitButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				System.exit(0);
			}
		});
	}
	
    public static void main(String[] args) throws IOException {

    	// open the start frame
        new Load();
        
        EventQueue.invokeLater(() -> {
            StartFrame startFrame = new StartFrame();
            startFrame.setVisible(true);
        });
    }
}
