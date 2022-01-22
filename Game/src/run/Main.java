/************************************************************************************************************
* Developer: Minhas Kamal(BSSE-0509, IIT, DU)																*
* Date: 13-Oct-2014																							*
* Comment: 	*									
*************************************************************************************************************/

package run;

import run.BrainTestMainFrame;

public class Main {
	/********* Main Method *********/
	public static void main(String args[]) {
		/*// Set the NIMBUS look and feel //*/
		try {
			javax.swing.UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Exception ex) {
			// do nothing if operation is unsuccessful
		}

		/* Create and display the form */
		BrainTestMainFrame gui = new BrainTestMainFrame();
		gui.setVisible(true);
	}
}
