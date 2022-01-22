/****************************************************************************************************************************
* Developer: Minhas Kamal(BSSE-0509, IIT, DU)																				*
* Date: 04-Sep-2014																							*
* Comment: 	*									
*****************************************************************************************************************************/


package tests.game5;


import javax.swing.*;


@SuppressWarnings("serial")
public class Block extends JPanel implements Runnable{
	//**
	// Variable Declaration 																				#*******D*******#
	//**
	
	 JLabel jLabelOutcome;
	final private int X = 40;
	final private int Y = 40;
	
	private int R;
	private int G;
	private int B;
	
	public boolean isDown;
	private boolean hasOutcome;
	// End of Variable Declaration 																			#_______D_______#

	/***##Constructor##***/
	public Block(int i) {
		
		initialComponent(i);
	}
	
	public Block() {
		
		initialComponent(1);
	}

	
	/**
	 * Method for Initializing all the GUI variables, placing them all to specific space on the frame and adding action
	 * listener to them. Also specifies criteria of the main frame.
	 */
	private void initialComponent(int i) {
		//**
		// Initialization 																					#*******I*******#
		//**
		jLabelOutcome = new JLabel();

		R = 255;
		G = 240;
		B = 206;
		
		isDown = false;
		hasOutcome = false;
		// End of Initialization																			#_______I_______#
		
		//**
		// Components
		//**
		jLabelOutcome.setHorizontalAlignment(0);
		jLabelOutcome.setVerticalAlignment(0);
		jLabelOutcome.setSize(40, 40);
		jLabelOutcome.setLocation(0, 0);
		jLabelOutcome.setIcon(new ImageIcon(getClass().getResource("/tests/game5/pictures/Right.png")));
		
		//**
		// Setting Bounds and Attributes of the Panel	 													#*******S*******#
		//**
		setSize(X, Y);
		setBackground(new java.awt.Color(R, G, B));
		// End of Setting Bounds and Attributes 															#_______S_______#
	}

	//**
	// Action Events 																						#********AE*******#
	//**
	
	// End of Action Events 																				#________AE_______#
	
	//**
	// Run Method																							#*******R*******#
	//**
	@Override
	public void run() {
		try {
			int x = getX();
			
			for(int i=Y/2-1; i>0; i--){
				setSize(X, getHeight()-2);
				setLocation(x, getY()+1);
				Thread.sleep(10);
			}
			
			setBackground(new java.awt.Color(R, G, B));
			
			for(int i=Y/2-1; i>0; i--){
				setSize(X, getHeight()+2);
				setLocation(x, getY()-1);
				Thread.sleep(10);
			}
			
			
			for(int i=0; i<4; i++){
				setSize(X, getHeight()-2);
				setLocation(x, getY()+1);
				Thread.sleep(12);
			}
			
			if(hasOutcome){
				add(jLabelOutcome);
			}
			
			for(int i=0; i<4; i++){
				setSize(X, getHeight()+2);
				setLocation(x, getY()-1);
				Thread.sleep(12);
			}
			
			
			for(int i=0; i<2; i++){
				setSize(X, getHeight()-2);
				setLocation(x, getY()+1);
				Thread.sleep(9);
			}
			
			for(int i=0; i<2; i++){
				setSize(X, getHeight()+2);
				setLocation(x, getY()-1);
				Thread.sleep(9);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//End of Run Method																						#_______R_______#

	
	//**
	// Auxiliary Methods 																					#********AM*******#
	//**
	public void setBackgroundColor(int R, int G, int B){
		this.R=R;
		this.G=G;
		this.B=B;
		
		Thread thread = new Thread(this);
		thread.start();
	}
	
	public void addOutcome(){
		hasOutcome = true;
	}
	
	public void removeOutcome(){
		remove(jLabelOutcome);
		hasOutcome = false;
	}
	// End of Auxiliary Methods 																			#________AM_______#
}
