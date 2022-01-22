
package tests.game10;


import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;

import random.Randomall;

import tests.game10.random;
import message.Message;
import run.BrainTestMainFrame;

@SuppressWarnings("serial")
public class game10 extends BrainTestMainFrame{
	//**
	// Variable Declaration 																				#*******D*******#
	//**
	private JLabel jLabelBoxContainer;
	private JLabel[] jLabelEmptyBox;
	private JLabel[] jLabelSymbol;
	
	
	//operational variables
	private int rightAnswer;
	private int numberOfSymbols;
	
	private int selectedEmptyBoxNo;
	private int selectedSymbolNo;
	// End of Variable Declaration 																			#_______D_______#

	/***##Constructor##***/
	public game10() {
		numberOfSymbols=25;
		selectedEmptyBoxNo=-1;
		selectedSymbolNo=-1;
		
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
		jLabelBoxContainer = new JLabel();
		jLabelEmptyBox = new JLabel[numberOfSymbols];
		jLabelSymbol = new JLabel[numberOfSymbols];
		// End of Initialization																			#_______I_______#

		//**
		// Setting Bounds and Attributes of the Elements 													#*******S*******#
		//**
		jLabelBoxContainer.setBounds(220, 120, 252, 252);
		jLabelBoxContainer.setBorder((new javax.swing.border.LineBorder(new java.awt.Color(105, 30, 30), 2)));
		
		for(int i=0; i<numberOfSymbols; i++){
			jLabelEmptyBox[i] = new JLabel();
			jLabelEmptyBox[i].setSize(50, 50);
			jLabelEmptyBox[i].setLocation((i/5)*50+1, (i%5)*50+1);
			jLabelEmptyBox[i].setHorizontalAlignment(0);
			jLabelEmptyBox[i].setVerticalAlignment(0);		
			jLabelEmptyBox[i].setBorder((new javax.swing.border.LineBorder(new java.awt.Color(105, 30, 30), 2)));
			jLabelEmptyBox[i].setFocusable(false);
			jLabelEmptyBox[i].addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent arg0) {
					jLabelEmptyBoxMouseClicked(arg0);
	            }
	        });
		}
		arrangeAsSquareGrid(jLabelEmptyBox, 1, 1, 0, 0);
		
		for(int i=0; i<numberOfSymbols; i++){
			jLabelSymbol[i] = new JLabel();
			jLabelSymbol[i].setSize(50, 50);
			/*if(i<16){
				jLabelSymbol[i].setLocation((i%2)*605+20, (i/2)*55+90);
			}else{
				jLabelSymbol[i].setLocation((i-15)*55+47, 475);
			}*/
			jLabelSymbol[i].setHorizontalAlignment(0);
			jLabelSymbol[i].setVerticalAlignment(0);	
			jLabelSymbol[i].setBorder((new javax.swing.border.LineBorder(new java.awt.Color(105, 30, 30), 1)));
			jLabelSymbol[i].setFocusable(false);
			//jLabelSymbol[i].setToolTipText(i+"");
			jLabelSymbol[i].addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent arg0) {
					jLabelSymbolMouseClicked(arg0);
	            }
	        });
		}
		jLabelSymbol[0].setLocation(35, 215);
		jLabelSymbol[1].setLocation(615, 215);
		jLabelSymbol[2].setLocation(90, 240);
		jLabelSymbol[3].setLocation(560, 240);
		
		jLabelSymbol[4].setLocation(10, 290);
		jLabelSymbol[5].setLocation(635, 290);
		jLabelSymbol[6].setLocation(110, 295);
		jLabelSymbol[7].setLocation(540, 295);
		jLabelSymbol[8].setLocation(40, 345);
		jLabelSymbol[9].setLocation(610, 345);
		
		jLabelSymbol[10].setLocation(140, 350);
		jLabelSymbol[11].setLocation(510, 350);
		jLabelSymbol[12].setLocation(70, 400);
		jLabelSymbol[13].setLocation(580, 400);
		jLabelSymbol[14].setLocation(160, 405);
		jLabelSymbol[15].setLocation(490, 405);
		
		jLabelSymbol[16].setLocation(215, 430);
		jLabelSymbol[17].setLocation(435, 430);
		jLabelSymbol[18].setLocation(90, 455);
		jLabelSymbol[19].setLocation(560, 455);
		
		jLabelSymbol[20].setLocation(270, 450);
		jLabelSymbol[21].setLocation(380, 450);
		jLabelSymbol[22].setLocation(145, 475);
		jLabelSymbol[23].setLocation(505, 475);
		
		jLabelSymbol[24].setLocation(325, 460);
		// End of Setting Bounds and Attributes 															#_______S_______#

		//**
		// Adding Components 																				#*******A*******#
		//**
		jLabelMain.add(jLabelBoxContainer);
		
		for(int i=0; i<numberOfSymbols; i++){
			jLabelBoxContainer.add(jLabelEmptyBox[i]);
		}
		
		for(int i=0; i<numberOfSymbols; i++){
			jLabelMain.add(jLabelSymbol[i]);
		}
		
		// End of Adding Components 																		#_______A_______#

		//**Setting Criterion of the Frame**//
		setTitle("Lost Twin");
	}

	//**
	// Action Events 																						#********AE*******#
	//**
	private void jLabelEmptyBoxMouseClicked(MouseEvent arg){
		
		for(int i=0; i<numberOfSymbols; i++){					//check all the labels
			if(arg.getSource().equals(jLabelEmptyBox[i]) && jLabelEmptyBox[i].isFocusable()){	//when found clicked label
				if(selectedSymbolNo>=0){						//if a symbol is already selected
					
					String EmptyBoxIconName=jLabelEmptyBox[i].getIcon().toString();
					EmptyBoxIconName=EmptyBoxIconName.substring(EmptyBoxIconName.lastIndexOf('/'));
					
					String SymbolIconName=jLabelSymbol[selectedSymbolNo].getIcon().toString();
					SymbolIconName=SymbolIconName.substring(SymbolIconName.lastIndexOf('/'));
					
					if(EmptyBoxIconName.equals(SymbolIconName)){//compare the symbols of two labels
						
						rightAnswerFound(jLabelEmptyBox[i], jLabelSymbol[selectedSymbolNo]);
						
						selectedEmptyBoxNo=-1;
						selectedSymbolNo=-1;
					}
				}else{											//if no symbol is selected yet
					jLabelEmptyBox[i].setBorder((new javax.swing.border.LineBorder
							(new java.awt.Color(255, 50, 50), 3)));
					selectedEmptyBoxNo=i;
				}
			}else{												//when found other than clicked label
				jLabelEmptyBox[i].setBorder((new javax.swing.border.LineBorder(new java.awt.Color(105, 30, 30), 2)));
			}
		}
		
	}
	
	private void jLabelSymbolMouseClicked(MouseEvent arg){
		
			for(int i=0; i<numberOfSymbols; i++){
				if(arg.getSource().equals(jLabelSymbol[i]) && jLabelSymbol[i].isFocusable()){
					if(selectedEmptyBoxNo>=0){
						String SymbolIconName=jLabelSymbol[i].getIcon().toString();
						SymbolIconName=SymbolIconName.substring(SymbolIconName.lastIndexOf('/'));
						
						String EmptyBoxIconName=jLabelEmptyBox[selectedEmptyBoxNo].getIcon().toString();
						EmptyBoxIconName=EmptyBoxIconName.substring(EmptyBoxIconName.lastIndexOf('/'));
						
						if(SymbolIconName.equals(EmptyBoxIconName)){
	
							rightAnswerFound(jLabelEmptyBox[selectedEmptyBoxNo], jLabelSymbol[i]);
							
							selectedEmptyBoxNo=-1;
							selectedSymbolNo=-1;
						}
					}else{
						jLabelSymbol[i].setBorder((new javax.swing.border.LineBorder
							(new java.awt.Color(255, 50, 50), 2)));
						selectedSymbolNo=i;
					}
				}else{
					jLabelSymbol[i].setBorder((new javax.swing.border.LineBorder(new java.awt.Color(105, 30, 30), 1)));
				}
			}
		
	}
	// End of Action Events 																				#________AE_______#
	
	//**
	// Run Method																							#********R********#
	//**
	public void run() {

		try{
			//****Field Setup****////////////////////////////////////////////////////////////////////////////////////////
			gameState=IS_STARTED;
			
			Icon[][] icons = new random().generateRandomIcon(numberOfSymbols);
			
			shuffle(icons[0]);
			shuffle(icons[1]);
		
			for(int i=0; i<numberOfSymbols; i++){
				jLabelEmptyBox[i].setFocusable(true);
				jLabelEmptyBox[i].setIcon(icons[0][i]);
				Thread.sleep(15);
			}
			
			for(int i=0; i<numberOfSymbols; i++){
				jLabelSymbol[i].setFocusable(true);
				jLabelSymbol[i].setIcon(icons[1][i]);
				Thread.sleep(15);
			}
			
			//****Field Show****/////////////////////////////////////////////////////////////////////////////////////////
			gameState = IS_SHOWING;
			
			//****Take Input****/////////////////////////////////////////////////////////////////////////////////////////
			gameState = IS_WAITING;
			
			int totalShowTime=6000, passedTime=0;
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
			
			//****Result Processing****///////////////////////////////////////////////////////////////////////////////////
			gameState = IS_PROCESSING;
			jButtonControl.setText("Time's up!");
			stopInput();
			
			Thread.sleep(1000);
			
			//****Result Show****/////////////////////////////////////////////////////////////////////////////////////////
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
	//End of Run Method																						#________R________#

	
	/********* Main Method *********/
	public static void main(String args[]) {
		/*// Set the NIMBUS look and feel //*/
		try {
			javax.swing.UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Exception ex) {
			// do nothing if operation is unsuccessful
		}

		/* Create and display the form */
		game10 gui = new game10();
		gui.setVisible(true);
	}

	private void shuffle(Icon[] symbols){
		int length = symbols.length;
		int noOfShuffle = length*3;
		
		Icon iconTemp;
		
		Randomall randomIntegerGenerator = new Randomall();
		int randomNumber1=0, randomNumber2=0;
		for(int i=0; i<noOfShuffle; i++){
			randomNumber1 = randomIntegerGenerator.nextInt(length);
			randomNumber2 = randomIntegerGenerator.nextInt(length);
			
			iconTemp = symbols[randomNumber1];
			symbols[randomNumber1] = symbols[randomNumber2];
			symbols[randomNumber2] = iconTemp;
		}
	}
	
	/**
	 * 
	 * @param label1
	 * @param label2
	 */
	private void rightAnswerFound(JLabel emptyBox, JLabel symbol){
		emptyBox.setIcon(symbol.getIcon());
		symbol.setIcon(null);
		
		emptyBox.setFocusable(false);
		emptyBox.setBorder((new javax.swing.border.LineBorder(new java.awt.Color(105, 30, 30), 2)));
		
		symbol.setFocusable(false);
		symbol.setBorder((new javax.swing.border.LineBorder(new java.awt.Color(105, 30, 30), 1)));
		
		rightAnswer++;
	}
	
	
	/**
	 * 
	 * @param jComponents
	 * @param distanceX
	 * @param distanceY
	 * @param gapX
	 * @param gapY
	 */
	private void arrangeAsSquareGrid(JComponent[] jComponents, int distanceX, int distanceY, int gapX, int gapY){
		int noOfComponents = jComponents.length;
		int column = (int) Math.sqrt(noOfComponents);
		int height = jComponents[0].getHeight();
		int width = jComponents[0].getWidth();
		

		for(int i=0, j=0, k=0, l=0; i<noOfComponents; i++){
			
			if(j>=column || k>=column){
				i--;
			}else{
				jComponents[i].setLocation( j*(width+gapX) + distanceX, k*(height+gapY) + distanceY );
			}
			
			if(j==0){
				l++;
				if(l>2*column){
					break;
				}
				
				j=l;
				k=0;
				
			}else{
				j--;
				k++;
			}
		}
	}
	// End of Auxiliary Methods 																			#________AM_______#
	
	//**
	// Overridden Methods 																					#********OM*******#
	//**
	@Override
	protected void gameInitialize(){
		try{
			for(int i=numberOfSymbols-1; i>=0; i--){
				jLabelSymbol[i].setIcon(null);
			}
			for(int i=numberOfSymbols-1; i>=0; i--){
				jLabelEmptyBox[i].setIcon(null);
			}
		}catch (Exception e) {
			//handle exception
		}
	}
	
	@Override
	protected void stopInput(){
		for(int i=0; i<numberOfSymbols; i++){
			jLabelEmptyBox[i].setFocusable(false);
			jLabelEmptyBox[i].setBorder((new javax.swing.border.LineBorder(new java.awt.Color(105, 30, 30), 2)));
			jLabelSymbol[i].setFocusable(false);
			jLabelSymbol[i].setBorder((new javax.swing.border.LineBorder(new java.awt.Color(105, 30, 30), 1)));
		}
	}
	
	@Override
	protected void showResult(){
		if(rightAnswer<=4){
			new Message("You got " + rightAnswer + " right. \n" +
					"Did you really try?", 420);
		}else if(rightAnswer<=10){
			new Message("You got " + rightAnswer + " right. \n" +
					"Your perfection is below average.", 210);
		}else if(rightAnswer<=16){
			new Message("You got " + rightAnswer + " right. \n" +
					"You have an average perfection.", 0);
		}else if(rightAnswer<=20){
			new Message("You got " + rightAnswer + " right \n" +
					"Your perfection is above average.", 1);
		}else if(rightAnswer<=23){
			new Message("You got " + rightAnswer + " right \n" +
					"You have a high perfection. \n" +
					"Almost double from average.", 1);
		}else{
			new Message("You got " + rightAnswer + " right \n" +
					"You are not only a perfectionist but also \n" +
					"can work geat under pressure!", 1);
		}
	}
	// End of Overridden Methods 																			#________OM_______#
}


