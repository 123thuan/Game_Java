

package tests.game1;


import java.awt.event.ActionEvent;
import javax.swing.*;

import tests.game1.random;

import message.Message;
import run.BrainTestMainFrame;

@SuppressWarnings("serial")
public class MixedNumbers extends BrainTestMainFrame{
	
	private JLabel jLabelCharView;
	private JLabel[] jLabelAnswer;
	private JLabel[] jLabelOutcome;
	private JLabel jLabelResult;
	private JButton[] jButtonNumbers;
	
	private int result=0;
	private int noOfChances = 15;
	public MixedNumbers() {

		initialComponent();
	}
	private void initialComponent() {
		jLabelCharView = new JLabel();
		jLabelAnswer = new JLabel[2];
		jLabelOutcome = new JLabel[2];
		jLabelResult = new JLabel();
		jButtonNumbers = new JButton[10];
		jLabelCharView.setBounds(WEIGTH/2-100, 170, 200, 200);
		jLabelCharView.setBorder((new javax.swing.border.LineBorder(new java.awt.Color(105, 30, 30), 2)));
		jLabelCharView.setHorizontalAlignment(0);
		jLabelCharView.setVerticalAlignment(0);		
		jLabelCharView.setFont(new java.awt.Font("Consolas", 0, 160));
		
		for(int i=0; i<2; i++){
			jLabelAnswer[i] = new JLabel();
			jLabelAnswer[i].setBounds(i*99 + (WEIGTH/2-100), 390, 101, 60);
			jLabelAnswer[i].setFont(new java.awt.Font("Consolas", 0, 50));
			jLabelAnswer[i].setHorizontalAlignment(0);
			jLabelAnswer[i].setVerticalAlignment(0);		
			jLabelAnswer[i].setBorder((new javax.swing.border.LineBorder(new java.awt.Color(105, 30, 30), 2)));
		}
		for(int i=0; i<2; i++){
			jLabelOutcome[i] = new JLabel();
			jLabelOutcome[i].setBounds(0, 0, 100, 60);
			jLabelOutcome[i].setHorizontalAlignment(0);
			jLabelOutcome[i].setVerticalAlignment(0);		
		}
		
		jLabelResult.setBounds(WEIGTH/2-100, 90, 200, 60);
		jLabelResult.setBorder((new javax.swing.border.LineBorder(new java.awt.Color(105, 30, 30), 2)));
		jLabelResult.setHorizontalAlignment(0);
		jLabelResult.setVerticalAlignment(0);		
		jLabelResult.setFont(new java.awt.Font("Consolas", 0, 50));
		
		for(int i=0; i<10; i++){
			jButtonNumbers[i] = new JButton();
			jButtonNumbers[i].setBounds(i*50+100, HEIGHT-115, 50, 45);
			jButtonNumbers[i].setText(""+i);
			jButtonNumbers[i].setBackground(new java.awt.Color(230, 255, 242));
			jButtonNumbers[i].setHorizontalAlignment(0);
			jButtonNumbers[i].setVerticalAlignment(0);		
			jButtonNumbers[i].setFont(new java.awt.Font("Arial", 0, 30));
			jButtonNumbers[i].getInputMap(javax.swing.JComponent.WHEN_IN_FOCUSED_WINDOW)
				.put(javax.swing.KeyStroke.getKeyStroke(48+i, 0), "NumberButton"+i+"_pressed");
			jButtonNumbers[i].getActionMap().put("NumberButton"+i+"_pressed", new AbstractAction() {
				public void actionPerformed(java.awt.event.ActionEvent evt) {
					//jButtonNumbersActionPerformed(evt);
					JButton jButton = (JButton) evt.getSource();
					jButton.doClick();
				}
			});
			jButtonNumbers[i].addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent evt) {
					jButtonNumbersActionPerformed(evt);
				}
			});
			jButtonNumbers[i].setFocusable(false);
                }
		jLabelMain.add(jLabelCharView);
		
		for(int i=0; i<2; i++){
			jLabelMain.add(jLabelAnswer[i]);
			jLabelAnswer[i].add(jLabelOutcome[i]);
		}
		jLabelMain.add(jLabelResult);
		
		for(int i=0; i<10; i++){
			jLabelMain.add(jButtonNumbers[i]);
		}
		setTitle("Mixed Numbers");
	}
	private void jButtonNumbersActionPerformed(ActionEvent evt){
		if(jButtonNumbers[0].isFocusable()){
			if(jLabelAnswer[0].getText()==""){
				jLabelAnswer[0].setText(evt.getActionCommand());
			}else{
				jLabelAnswer[1].setText(evt.getActionCommand());
				gameState=IS_STARTED;
			}
			
//			System.out.println("OK");
		}
	}
	@Override
	public void run() {

		try{
			gameState=IS_STARTED;
			
			random randomCharacterMaker= new random();
			int noOfChars = 14;
			char[] charSet = new char[noOfChars];
			char[] ch = new char[2];
			boolean[] outcome = new boolean[2];
			
			int timeGap = 300;
			int chance = 0;
			
			jLabelResult.setText(0+"/"+0);
			
			Thread.sleep(1000);
			while(chance<noOfChances){
				gameState = IS_SHOWING;
				
				progressPanelAdvance(195*(chance+1)/noOfChances);
				charSet=randomCharacterMaker.creatRandomNumeralInChar(noOfChars);
				Thread.sleep(500);
				
				for(int i=0; i<noOfChars; i++){		//show numerals
					jLabelCharView.setText(""+charSet[i]);
					Thread.sleep(timeGap);
					
					if(gameState==IS_FINISHED){	//stop the game if the user wants
						jLabelCharView.setText("");
						
						return;
					}
				}
				jLabelCharView.setText("");
			        gameState = IS_WAITING;
				
				for(int i=0; i<10; i++){	//enabling the buttons for input
					jButtonNumbers[i].setFocusable(true);
				}
				gameState=IS_WAITING;	//wait for user input
				
				while(gameState==IS_WAITING){
					Thread.sleep(10);
				}
				
				for(int i=0; i<10; i++){	//unable the buttons
					jButtonNumbers[i].setFocusable(false);
				}
			
				if(gameState==IS_FINISHED){	//stop the game if the user wants
					return;
				}
			
				gameState = IS_PROCESSING;
				
				ch[0]=jLabelAnswer[0].getText().charAt(0);
				ch[1]=jLabelAnswer[1].getText().charAt(0);
				
				outcome = examine(charSet, ch);
				
				Thread.sleep(200);
				
				for(int i=0; i<2; i++){
					if(outcome[i]){
						jLabelOutcome[i].setIcon(new javax.swing.ImageIcon(getClass().
								getResource("/tests/game1/pictures/Right.png")));
						result++;
					}else{
						jLabelOutcome[i].setIcon(new javax.swing.ImageIcon(getClass().
								getResource("/tests/game1/pictures/Wrong.png")));
					}
				}
				
				Thread.sleep(500);
				
				jLabelResult.setText(result + "/" + (chance*2+2));
				
				//**Update**//
				if(outcome[0] && outcome[1]){
					if(timeGap>400){
						timeGap = timeGap-50;
					}else if(timeGap>300){
						timeGap = timeGap-40;
					}else if(timeGap>100){
						timeGap = timeGap-30;
					}else if(timeGap>50){
						timeGap = timeGap-10;
					}
				}
				
				Thread.sleep(1500);
				
				if(gameState==IS_FINISHED){	//stop the game if the user wants
					return;
				}
				
				//**initialization**//
				for(int i=0; i<2; i++){
					jLabelAnswer[i].setText("");
					jLabelOutcome[i].setIcon(null);
				}
				
				chance++;
			}
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
	public static void main(String args[]) {
		try {
			javax.swing.UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Exception ex){}
		MixedNumbers gui = new MixedNumbers();
		gui.setVisible(true);
	}
	private boolean[] examine(char[] charSet, char[] chars){
		int length = charSet.length;
		boolean[] outcome = {false, false};
		
		for(int i=0; i<length; i++){
			if(charSet[i]==chars[0]){
				charSet[i]='A';
				outcome[0]=true;
				break;
			}
		}
		for(int i=0; i<length; i++){
			if(charSet[i]==chars[1]){
				outcome[1]=true;
				break;
			}
		}
		
		return outcome;
	}
	@Override
	protected void gameInitialize(){
		result = 0;
		
		jLabelResult.setText("");
		jLabelCharView.setText("");
		
		for(int i=0; i<2; i++){
			jLabelAnswer[i].setText("");
			jLabelOutcome[i].setIcon(null);
		}
	}
	
	@Override
	protected void stopInput(){
		for(int i=0; i<10; i++){	//enabling the buttons for input
			jButtonNumbers[i].setFocusable(false);
		}
	}
	
	@Override
	protected void showResult(){

		float score = (float)((double)result/2.00/(double)noOfChances*100.00);
		
		String comment="";
		
		if(score>95){
			comment="Your score is excellent!!! \n How did you do that?";
		}else if(score>90){
			comment="Your score is excellent!!!";
		}else if(score>80){
			comment="Your score is very very high!!";
		}else if(score>70){
			comment="Your score is high!";
		}else if(score>60){
			comment="Your score is good.";
		}else if(score>50){
			comment="Your score is not bad.";
		}else if(score>40){
			comment="Your score is low.";
		}else if(score>30){
			comment="Your score is very low.";
		}else{
			comment="Are you joking? \nYou did not concentrate at all.";
		}
		
		new Message("You got " + result + " right in "+ (2*noOfChances) +".\nYou score is: "+ score +"%. \n" + comment, 0);
	
	}
	// End of Overridden Methods 																			#________OM_______#

}


