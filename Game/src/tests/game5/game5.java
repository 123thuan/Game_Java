/************************************************************************************************************
* Developer: Minhas Kamal(BSSE-0509, IIT, DU)																*
* Date: 19-Jul-2014, Modified: 4-Sep-2014																	*
* Comment: 	*									
*************************************************************************************************************/

package tests.game5;


import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;
import tests.game5.random;
import message.Message;
import run.BrainTestMainFrame;


@SuppressWarnings("serial")
public class game5 extends BrainTestMainFrame{
	//**
	// Variable Declaration 																				#*******D*******#
	//**
	private JPanel jLabelBlockContainer;
	private Block[] blocks;

	//operational variables
	private int[] answers;
	private int noOfClicks;
	private int levelOfDifficulty, boundaryOfDifficulty;
	private boolean isMistaken;
	private int noOfConsicutiveMistakes;
	
	private int numberOfBlocks;
	// End of Variable Declaration 																			#_______D_______#

	/***##Constructor##***/
	public game5() {
		numberOfBlocks = 80;
		
		
		initialComponent();
	}

	
	/**
	 * Method for Initializing all the GUI variables, placing them all to specific space on the frame and adding action
	 * listener to them. Also specifies criteria of the main frame.
	 */
	private void initialComponent() {
		//**
		// Initialization 																					#*******I*******#
		//**
		jLabelBlockContainer = new JPanel();
		blocks = new Block[numberOfBlocks];
		
		levelOfDifficulty = 0;
		boundaryOfDifficulty = 18;
		isMistaken = false;
		noOfConsicutiveMistakes = 0;
		// End of Initialization																			#_______I_______#

		//**
		// Setting Bounds and Attributes of the Elements 													#*******S*******#
		//**
		jLabelBlockContainer.setBounds(85, 85, 516, 416);
		jLabelBlockContainer.setBackground(new Color(130, 90, 25));
		jLabelBlockContainer.setLayout(null);
		
		for(int i=0; i<numberOfBlocks; i++){
			blocks[i] = new Block();
			blocks[i].setName(i+"");
			blocks[i].setLocation((i%10)*50+13, (i/10)*50+13);
			blocks[i].setFocusable(false);
			blocks[i].addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent arg0) {
					jPanelColorBlocksMouseClicked(arg0);
	            }
				public void mouseEntered(MouseEvent arg0) {
					jPanelColorBlocksMouseEntered(arg0);
			    }
			    public void mouseExited(MouseEvent arg0) {
			    	jPanelColorBlocksMouseExited(arg0);
			    }
	        });
		}
		
		// End of Setting Bounds and Attributes 															#_______S_______#

		//**
		// Adding Components 																				#*******A*******#
		//**
		jLabelMain.add(jLabelBlockContainer);
		
		for(int i=0; i<numberOfBlocks; i++){
			jLabelBlockContainer.add(blocks[i]);
		}
		// End of Adding Components 																		#_______A_______#

		//**Setting Criterion of the Frame**//
		setTitle("Hidden Blocks");
	}

	//**
	// Action Events 																						#********AE*******#
	//**
	private void jPanelColorBlocksMouseClicked(MouseEvent arg0){
		Block jPanel = (Block) arg0.getSource();
		if(jPanel.isFocusable()){
			String panelNo = jPanel.getName();
			int answersLength = answers.length;
			
			int i;
			for(i=0; i<answersLength; i++){
				if(panelNo.equals(answers[i]+"")){
					jPanel.setBackgroundColor(170, 255, 170);
					break;
				}
			}
			
			
			if(i==answersLength){
				jPanel.setBackgroundColor(250, 150, 150);
				isMistaken=true;
			}
			
			
			jPanel.setFocusable(false);
			jPanel.isDown=false;

			noOfClicks++;
			
			if(noOfClicks==answersLength){
				if(!isMistaken){
					jPanel.addOutcome();
				}
				gameState=IS_STARTED;
			}
		}
	}
	
	
	private void jPanelColorBlocksMouseEntered(MouseEvent arg0){
		JPanel jPanel = (JPanel) arg0.getSource();
		if(jPanel.isFocusable()){
			jPanel.setBackground(new Color(155, 125, 125));
		}
	}
	
	private void jPanelColorBlocksMouseExited(MouseEvent arg0){
		JPanel jPanel = (JPanel) arg0.getSource();
		if(jPanel.isFocusable()){
			jPanel.setBackground(new Color(185, 175, 175));
		}
	}
	// End of Action Events 																				#________AE_______#
	
	//**
	// Run Method																							#*******R*******#
	//**
	@Override
	public void run() {

		try{
			//****Field Setup****////////////////////////////////////////////////////////////////////////////////////////
			gameState=IS_STARTED;
			
			for(int i=4; i<numberOfBlocks; i+=9){
				blocks[i].setBackgroundColor(185, 175, 175);
				blocks[i].isDown=true;
				Thread.sleep(80); i+=1;
				blocks[i].setBackgroundColor(185, 175, 175);
				blocks[i].isDown=true;
				Thread.sleep(80);
			}for(int i=3; i<numberOfBlocks; i+=7){
				blocks[i].setBackgroundColor(185, 175, 175);
				blocks[i].isDown=true;
				Thread.sleep(70); i+=3;
				blocks[i].setBackgroundColor(185, 175, 175);
				blocks[i].isDown=true;
				Thread.sleep(70);
			}for(int i=2; i<numberOfBlocks; i+=5){
				blocks[i].setBackgroundColor(185, 175, 175);
				blocks[i].isDown=true;
				Thread.sleep(60); i+=5;
				blocks[i].setBackgroundColor(185, 175, 175);
				blocks[i].isDown=true;
				Thread.sleep(60);
			}for(int i=1; i<numberOfBlocks; i+=3){
				blocks[i].setBackgroundColor(185, 175, 175);
				blocks[i].isDown=true;
				Thread.sleep(50); i+=7;
				blocks[i].setBackgroundColor(185, 175, 175);
				blocks[i].isDown=true;
				Thread.sleep(50);
			}for(int i=0; i<numberOfBlocks; i+=1){
				blocks[i].setBackgroundColor(185, 175, 175);
				blocks[i].isDown=true;
				Thread.sleep(40); i+=9;
				blocks[i].setBackgroundColor(185, 175, 175);
				blocks[i].isDown=true;
				Thread.sleep(40);
			}
			
			while(noOfConsicutiveMistakes<4 && levelOfDifficulty<=boundaryOfDifficulty){
				answers = new random().selectRandomBlocks(levelOfDifficulty, numberOfBlocks);
				noOfClicks=0;
				isMistaken=false;
				
				progressPanelAdvance(195*(levelOfDifficulty+1)/boundaryOfDifficulty);
				
				Thread.sleep(1000);
				if(gameState == IS_FINISHED){	//stop when the user wants
					stopInput();
					return ;
				}
	
				gameState = IS_SHOWING;
				
				for(int i=0; i<answers.length; i++){
					blocks[answers[i]].setBackgroundColor(170, 255, 170);
					blocks[answers[i]].isDown=false;
					Thread.sleep(30);
				}
				
				
				Thread.sleep(1500);
				if(gameState == IS_FINISHED){	//stop when the user wants
					stopInput();
					return ;
				}
				Thread.sleep(1500);
				if(gameState == IS_FINISHED){	//stop when the user wants
					stopInput();
					return ;
				}
				
				for(int i=0; i<numberOfBlocks; i++){
					if(!blocks[i].isDown){
						blocks[i].setBackgroundColor(185, 175, 175);
						blocks[i].isDown=true;
					}
				}
				
				Thread.sleep(800);
				
				for(int i=0; i<numberOfBlocks; i++){
					blocks[i].setFocusable(true);
				}
				
				//****Take Input****/////////////////////////////////////////////////////////////////////////////////////
				gameState = IS_WAITING;
				
				while(gameState==IS_WAITING){
					Thread.sleep(100);
				}
				
				if(gameState == IS_FINISHED){	//stop when the user wants
					stopInput();
					return ;
				}
			
				//****Result Processing****////////////////////////////////////////////////////////////////////////////////
				gameState = IS_PROCESSING;
				stopInput();
				
				if(!isMistaken){
					levelOfDifficulty++;
					noOfConsicutiveMistakes = 0;
				}else{
					noOfConsicutiveMistakes++;
					
					//**Show Answer**//
					Thread.sleep(1000);
					
					for(int i=0; i<answers.length; i++){
						if(blocks[answers[i]].isDown){
							blocks[answers[i]].setBackgroundColor(170, 255, 170);
							blocks[answers[i]].isDown=false;
						}
					}
				}
				
				//**Reinitialize**//
				gameState = IS_STARTED;
				
				Thread.sleep(2000);
				
				for(int i=0; i<numberOfBlocks; i++){
					if(!blocks[i].isDown){
						blocks[i].removeOutcome();
						blocks[i].setBackgroundColor(185, 175, 175);
						blocks[i].isDown=true;
						Thread.sleep(10);
					}
				}
				
			}
			
			//****Result Show****/////////////////////////////////////////////////////////////////////////////////////////
			Thread.sleep(500);
			showResult();
			
		}catch (Exception e) {
			//do noting
			
		}finally{
			//****Game Finish****/////////////////////////////////////////////////////////////////////////////////////////
			gameState = IS_FINISHED;
			
			jButtonControl.setText("Restart");
			progressPanelReinitialize();
		}
	}
	//End of Run Method																						#_______R_______#

	
	/********* Main Method *********/
	public static void main(String args[]) {
		/*// Set the NIMBUS look and feel //*/
		try {
			javax.swing.UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Exception ex) {
			// do nothing if operation is unsuccessful
		}

		/* Create and display the form */
		game5 gui = new game5();
		gui.setVisible(true);
	}

	//**
	// Auxiliary Methods 																					#********AM*******#
	//**
	
	// End of Auxiliary Methods 																			#________AM_______#

	//**
	// Overridden Methods 																					#********OM*******#
	//**
	@Override
	protected void gameInitialize(){
		noOfConsicutiveMistakes=0;
		
		if(levelOfDifficulty>3){
			levelOfDifficulty -= 3;
		}else{
			levelOfDifficulty=0;
		}
		
		for(int i=numberOfBlocks-1; i>=0; i--){
			blocks[i].setFocusable(false);
			blocks[i].removeOutcome();
			blocks[i].setBackgroundColor(255, 240, 206);
			blocks[i].isDown = false;
		}
	}
	
	@Override
	protected void stopInput(){
		for(int i=numberOfBlocks-1; i>=0; i--){
			blocks[i].setFocusable(false);
		}
	}
	
	@Override
	protected void showResult(){
		int i=boundaryOfDifficulty, j=levelOfDifficulty, point=0;
		for(j=i-j; i>j; i--){
			point=point+(i/6);
		}
		point+=4;
		//System.out.println(i+", "+j+", "+k);
		
		new Message("You got " + (levelOfDifficulty/2 + 3) + " boxes right. \nYour point is: " + point + ".", 1);
	}
	// End of Overridden Methods 																			#________OM_______#

}


