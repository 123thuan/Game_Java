
package tests.game6;


import java.awt.*;

import javax.swing.*;
import javax.swing.border.*;

import random.Randomall;

import message.Message;

import run.BrainTestMainFrame;
import tests.game6.random;

@SuppressWarnings("serial")
public class game6 extends BrainTestMainFrame{
	private JLabel[] jLabelSymbolView;
	private JLabel[] jLabelNumberView;
	private JTextField[] jTextFieldAnswers;
	private int numberOfSymbols;
	private int rightAnswer;
	public game6() {
		numberOfSymbols = 15;
		initialComponent();
	}

	private void initialComponent() {
		jLabelSymbolView = new JLabel[numberOfSymbols];
		jLabelNumberView = new JLabel[numberOfSymbols];
		jTextFieldAnswers = new JTextField[numberOfSymbols];
		for(int i=0, x, y; i<numberOfSymbols; i++){
			x=(i%5)*100 + 100;
			y=(i/5)*155+85;
			
			jLabelSymbolView[i] = new JLabel();
			jLabelSymbolView[i].setBounds(x, y, 65, 65);
			jLabelSymbolView[i].setHorizontalAlignment(0);
			jLabelSymbolView[i].setVerticalAlignment(0);		
			jLabelSymbolView[i].setBorder((new LineBorder(new Color(105, 30, 30), 3)));
			
			jLabelNumberView[i] = new JLabel();
			jLabelNumberView[i].setSize(65, 35);
			jLabelNumberView[i].setLocation(x, y+63);
			jLabelNumberView[i].setFont(new java.awt.Font("Consolas", 0, 25));
			jLabelNumberView[i].setHorizontalAlignment(0);
			jLabelNumberView[i].setVerticalAlignment(0);		
			jLabelNumberView[i].setBorder((new LineBorder(new Color(105, 30, 30), 3)));
			
			jTextFieldAnswers[i] = new JTextField();
			jTextFieldAnswers[i].setSize(65, 35);
			jTextFieldAnswers[i].setLocation(900, 0);
			jTextFieldAnswers[i].setFont(new java.awt.Font("Consolas", 0, 25));
			jTextFieldAnswers[i].setHorizontalAlignment(0);
			jTextFieldAnswers[i].setBackground(new Color(230, 255, 242));
			jTextFieldAnswers[i].setBorder((new LineBorder(new Color(105, 30, 30), 3)));
		}		
		for(int i=0; i<numberOfSymbols; i++){
			jLabelMain.add(jLabelSymbolView[i]);
			jLabelMain.add(jLabelNumberView[i]);
			jLabelMain.add(jTextFieldAnswers[i]);
		}
		setTitle("Lost Symbols");
	}
	@Override
	public void run() {
		try{
			gameState=IS_STARTED;
			
			char[] answer = new char[numberOfSymbols];
			Icon[] symbols = new random().generateRandomIcon(numberOfSymbols);
			for(int i=0; i<numberOfSymbols; i++){
				answer[i] = (char)(97+i);
				jLabelNumberView[i].setText(answer[i]+"");
				jLabelSymbolView[i].setIcon(symbols[i]);
				Thread.sleep(30);
			}
		
			gameState = IS_SHOWING;
			
			int totalShowTime=3600, passedTime=0;
			while(passedTime<totalShowTime){
				if(gameState==IS_FINISHED){
			
					return ;
				}
				
				jButtonControl.setText((int)passedTime/100 + "." + passedTime%100);
				progressPanelAdvance(195*passedTime/totalShowTime);
				
				Thread.sleep(10);
				passedTime++;
			}
			gameState = IS_WAITING;
			for(int i=0; i<numberOfSymbols; i++){
				jTextFieldAnswers[i].setLocation(jLabelNumberView[i].getLocation());
				jLabelNumberView[i].setLocation(900, 0);
				Thread.sleep(15);
			}
			Thread.sleep(500);
			shuffle(answer, symbols);
			for(int i=numberOfSymbols-1; i>=0; i--){
				jLabelSymbolView[i].setIcon(symbols[i]);
				Thread.sleep(15);
			}
			Thread.sleep(500);
			jButtonControl.setText("Submit");
			progressPanelReinitialize();
			while(gameState==IS_WAITING){
				Thread.sleep(500);
			}
			gameState = IS_PROCESSING;
			jButtonControl.setText("Time's up!");
			stopInput();

			for(int i=0; i<numberOfSymbols; i++){
				jLabelNumberView[i].setLocation((i%5)*100 + 100, (i/5)*155 + 181);
				jLabelNumberView[i].setText(answer[i]+"");
				
				if(jTextFieldAnswers[i].getText().equalsIgnoreCase(answer[i]+"")){			//right
					rightAnswer++;
					jTextFieldAnswers[i].setBackground(new java.awt.Color(140, 255, 80));
				}else if(!jTextFieldAnswers[i].getText().equals("")){						//wrong
					jTextFieldAnswers[i].setBackground(new java.awt.Color(255, 50, 50));
				}
				
				Thread.sleep(15);
			}
			
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
		game6 gui = new game6();
		gui.setVisible(true);
	}	
	private void shuffle(char[] answer, Icon[] symbols){
		int length = answer.length;
		
		char charTemp;
		Icon iconTemp;
		
		Randomall randomIntegerGenerator = new Randomall();
		int randomNumber1=0, randomNumber2=0;
		for(int i=0; i<50; i++){
			randomNumber1 = randomIntegerGenerator.nextInt(length);
			randomNumber2 = randomIntegerGenerator.nextInt(length);
			
			charTemp = answer[randomNumber1];
			answer[randomNumber1] = answer[randomNumber2];
			answer[randomNumber2] = charTemp;
			
			iconTemp = symbols[randomNumber1];
			symbols[randomNumber1] = symbols[randomNumber2];
			symbols[randomNumber2] = iconTemp;
		}
	}
	@Override
	protected void gameInitialize(){
		rightAnswer=0;
		
		for(int i=0, x, y; i<numberOfSymbols; i++){
			x=(i%5)*100 + 100;
			y=(i/5)*155+85+63;
			
			jLabelNumberView[i].setLocation(x, y);
			jLabelNumberView[i].setText("");
			
			jTextFieldAnswers[i].setLocation(900, 0);
			jTextFieldAnswers[i].setText("");
			jTextFieldAnswers[i].setBackground(new java.awt.Color(230, 255, 242));
			jTextFieldAnswers[i].setEditable(true);
			
			jLabelSymbolView[i].setIcon(null);
		}
	}
	
	@Override
	protected void stopInput(){
		for (int i=0; i<numberOfSymbols; i++) {
			jTextFieldAnswers[i].setEditable(false);
		}
	}
	
	@Override
	protected void showResult(){
		if(rightAnswer<=3){
			new Message("số điểm của bạn là " + rightAnswer + " right. \n" +
					"bộ nhớ của bạn dưới mức trung bình.", 1);
		}else if(rightAnswer<=5){
			new Message("số điểm của bạn là " + rightAnswer + " right. \n" +
					"Bạn có một bộ nhớ trung bình", 1);
		}else if(rightAnswer<=8){
			new Message("số điểm của bạn là " + rightAnswer + " right \n" +
					"Bộ nhớ của bạn là trên trung bình.", 1);
		}else if(rightAnswer<=10){
			new Message("số điểm của bạn là " + rightAnswer + " right \n" +
					"Bạn có một trí nhớ làm việc rất cao.", 1);
		}else if(rightAnswer<=12){
			new Message("số điểm của bạn là " + rightAnswer + " right \n" +
					"Bạn có một trí nhớ làm việc tuyệt vời.", 1);
		}else{
			new Message("số điểm của bạn là " + rightAnswer + " right \n" +
					"Bạn có một bộ nhớ thiên tài ! WOW!!!", 1);
		}
	}
	// End of Overridden Methods 																			#________OM_______#
}


