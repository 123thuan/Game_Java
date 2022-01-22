package tests.game6;

import javax.swing.Icon;
import javax.swing.ImageIcon;

import random.Randomall;

public class random {
	public Icon[] generateRandomIcon(int number){
		Icon[] icons = new Icon[number];
		int random=0;
		int totalStockOfIcons = 80;
		Randomall randomIntegerGenerator = new Randomall();
		
		int[] randomIconNumber = new int[totalStockOfIcons];
		for(int i=0; i<totalStockOfIcons; i++){
			randomIconNumber[i]=i;
		}
		
		for(int i=0; i<number; i++){
			random = randomIntegerGenerator.nextInt(totalStockOfIcons);
			
			if(randomIconNumber[random]>=0){
				icons[i] = new ImageIcon(getClass().
						getResource("/tests/game6/pictures/Symbol" + randomIconNumber[random] + ".png"));
				randomIconNumber[random]=-1;
			}else{
				i--;
			}
		}
		
		return icons;
	}
}
