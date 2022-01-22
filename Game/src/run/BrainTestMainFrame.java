
package run;


import help.about.About;
import help.instruction.Instruction;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import tests.game10.game10;
import tests.game5.game5;
import tests.game6.game6;
import tests.game1.MixedNumbers;


@SuppressWarnings("serial")
public class BrainTestMainFrame extends JFrame implements Runnable{
	protected JLabel jLabelMain;
	
	protected JButton jButtonControl;
	protected JPanel[] jPanelProgress;
	
	//menu bar
	private JMenuBar jMenuBarMain;
	private JMenu jMenuTest, jMenuHelp;
	private JMenuItem[] jMITests;
	private JMenuItem jMIInstruction, jMIDeveloper, jMIAbout;

	protected String instruction;
	private int numberOfTests;
	
	//main thread
	protected Thread thread;
	
	//operational variables
	protected int gameState;

	protected final int IS_NOT_STARTED=0;	//before the game starts
	protected final int IS_STARTED=1;		//after the game is started
	protected final int IS_SHOWING=2;		//field show
	protected final int IS_WAITING=3;		//take input from user 
	protected final int IS_PROCESSING=4;	//stop taking input from user & process result
	protected final int IS_FINISHED=5;		//game is finished
	
	protected final int WEIGTH = 700;
	protected final int HEIGHT = 600;
	public BrainTestMainFrame() {
		instruction = new String();
		numberOfTests=4;
		
		initialComponent();
	}

	private void initialComponent() {
	
		jLabelMain = new JLabel();
		
		jButtonControl = new JButton();
		jPanelProgress = new JPanel[2];

		jMenuBarMain = new JMenuBar();
		jMenuTest = new JMenu(); jMenuHelp = new JMenu();
		jMITests = new JMenuItem[numberOfTests];
		jMIInstruction = new JMenuItem(); jMIDeveloper = new JMenuItem(); jMIAbout = new JMenuItem();
		
		gameState = IS_NOT_STARTED;
		jLabelMain.setBounds(0, 0, WEIGTH, HEIGHT);
		jLabelMain.setLayout(null);
		jLabelMain.setIcon(new ImageIcon(getClass().getResource("/run/pictures/Background.png")));
		
		
		jButtonControl.setBounds(WEIGTH/2-100, 15, 200, 40);
		jButtonControl.setText("Start");
		jButtonControl.setLayout(null);
		jButtonControl.setBackground(new Color(230, 255, 242));
		jButtonControl.setFont(new java.awt.Font("Arial", 0, 20));
		jButtonControl.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
			.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "Enter_pressed");
		jButtonControl.getActionMap().put("Enter_pressed", new AbstractAction() {
			public void actionPerformed(ActionEvent evt) {
				JButton jButton = (JButton) evt.getSource();
				jButton.doClick();
			}
		});
		jButtonControl.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				jButtonControlActionPerformed();
			}
		});
		
		jPanelProgress[0] = new JPanel();
		jPanelProgress[0].setBounds(3, 3, 0, 5);
		jPanelProgress[0].setBackground(new Color(250, 0, 0, 120));
		
		jPanelProgress[1] = new JPanel();
		jPanelProgress[1].setBounds(3, 30, 0, 5);
		jPanelProgress[1].setBackground(new Color(250, 0, 0, 120));
		
		
		//menu
		jMenuTest.setText("Test      ");
		jMenuTest.setFont(new Font("Lucida Bright", 1, 13));
		jMenuTest.setIcon(new ImageIcon(getClass().getResource("/run/pictures/IconTest.png")));
		jMenuHelp.setText("Help      ");
		jMenuHelp.setFont(new Font("Lucida Bright", 1, 13));
		jMenuHelp.setIcon(new ImageIcon(getClass().getResource("/run/pictures/IconHelp.png")));
		
		
		for(int i=0; i<numberOfTests; i++){
			jMITests[i] = new JMenuItem();
			jMITests[i].setIcon(new ImageIcon(getClass().getResource("/run/pictures/IconTests.png")));
			jMITests[i].setFont(new Font("Lucida Bright", 2, 15));
			jMITests[i].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent evt) {
        			jMITestsActionPerformed(evt);
				}
			});
		}
		jMITests[0].setText("Mixed Numbers");
		jMITests[1].setText("Lost Symbols");
		jMITests[2].setText("Lost Twin");
		jMITests[3].setText("Hidden Blocks");
		
		
		jMIInstruction.setText("Instruction"); 
		jMIInstruction.setIcon(new ImageIcon(getClass().getResource("/run/pictures/IconInstruction.png")));
		jMIInstruction.setFont(new Font("Lucida Bright", 2, 13));
    	jMIInstruction.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent evt) {
        		jMIInstructionActionPerformed(evt);
        	}
        });
		jMIAbout.setText("About");
		jMIAbout.setIcon(new ImageIcon(getClass().getResource("/run/pictures/IconAbout.png")));
		jMIAbout.setFont(new Font("Lucida Bright", 2, 13));
    	jMIAbout.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent evt) {
        		jMIAboutActionPerformed(evt);
        	}
        });
		
    	jLabelMain.add(jButtonControl);
    	jButtonControl.add(jPanelProgress[0]);
    	jButtonControl.add(jPanelProgress[1]);
    	
    	jMenuBarMain.add(jMenuTest); jMenuBarMain.add(jMenuHelp);
		
		for(int i=0; i<numberOfTests; i++){
			jMenuTest.add(jMITests[i]);
		}
		jMenuHelp.add(jMIInstruction); jMenuHelp.add(jMIDeveloper); jMenuHelp.add(jMIAbout);
		setIconImage(new ImageIcon(getClass().getResource("/run/pictures/Icon.png")).getImage());
		setBounds(150, 50, WEIGTH, HEIGHT);
		setTitle("Brain Test");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(null);
		setResizable(false);
		add(jLabelMain);
		setJMenuBar(jMenuBarMain);
	}

	private void jButtonControlActionPerformed(){
		if(gameState==IS_NOT_STARTED){
			jButtonControl.setText("Stop");
			thread = new Thread(this);
			thread.start();
			
		}else if(gameState==IS_STARTED || gameState==IS_SHOWING || gameState==IS_WAITING){
			gameState=IS_FINISHED;
			
		}else if(gameState==IS_FINISHED){
			gameState=IS_NOT_STARTED;
			
			jButtonControl.setText("Start");
			gameInitialize();
		}
	}
	
	
	private void jMITestsActionPerformed(ActionEvent evt){
		
		if(evt.getActionCommand() == "Mixed Numbers"){
			new MixedNumbers().setVisible(true);
		}else if(evt.getActionCommand() == "Lost Symbols"){
			new game6().setVisible(true);
		}else if(evt.getActionCommand() == "Lost Twin"){
			new game10().setVisible(true);
		}else if(evt.getActionCommand() == "Hidden Blocks"){
			new game5().setVisible(true);
		}else{
			new MixedNumbers().setVisible(true);
		}
		
		gameState = IS_FINISHED;
      	dispose();
	}
	
	private void jMIInstructionActionPerformed(ActionEvent evt){
		new Instruction(instruction).setVisible(true);
	}
	
	private void jMIAboutActionPerformed(ActionEvent evt){
		new About().setVisible(true);
	}
	@Override
	public void run() {
		try{
			gameState=IS_STARTED;
			gameState = IS_SHOWING;
			gameState = IS_WAITING;
			
			int totalShowTime=500, passedTime=0;
			while(passedTime<totalShowTime){
				if(gameState == IS_FINISHED){
					stopInput();
							
					return ;
				}
				jButtonControl.setText((int)passedTime/100 + "." + passedTime%100);
				progressPanelAdvance(195*passedTime/totalShowTime);
				
				Thread.sleep(10);
				passedTime++;
			}
			
			gameState = IS_PROCESSING;
			jButtonControl.setText("Time's up!");
			stopInput();
			
			Thread.sleep(1000);
			showResult();
			
			
		}catch (Exception e) {
			//do noting
			
		}finally{
			gameState = IS_FINISHED;
			jButtonControl.setText("Restart");
			progressPanelReinitialize();
		}
	}
	public static void main(String args[]) {
		try {
			javax.swing.UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Exception ex) {
		}
		BrainTestMainFrame gui = new BrainTestMainFrame();
		gui.setVisible(true);
	}

	protected void progressPanelReinitialize(){
		jPanelProgress[0].setSize(0, 3);
		jPanelProgress[1].setSize(0, 3);
	}
	
	protected void progressPanelAdvance(int i){
		jPanelProgress[0].setSize(i, 3);
		jPanelProgress[1].setSize(i, 3);
	}
	protected void gameInitialize(){
		//should be overridden in properly
	}
	
	protected void stopInput(){
		//should be overridden in properly
	}
	
	protected void showResult(){
		//should be overridden in properly
	}
	// End of Overridden Methods 																			#________OM_______#

}
