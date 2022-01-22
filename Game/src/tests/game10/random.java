package tests.game10;

import javax.swing.Icon;
import javax.swing.ImageIcon;

import random.Randomall;


public class random {
	public Icon[][] generateRandomIcon(int number){
		Icon[][] icons = new Icon[2][number];
		int random=0;
		Randomall randomIntegerGenerator = new Randomall();
		int totalStockOfPictures=40;
		
		int[] randomIconNumber = new int[totalStockOfPictures];
		for(int i=0; i<totalStockOfPictures; i++){
			randomIconNumber[i]=i;
		}
		
		for(int i=0; i<number; i++){
			random = randomIntegerGenerator.nextInt(totalStockOfPictures);
			
			if(randomIconNumber[random]>=0){
				icons[0][i] = new ImageIcon(getClass().
						getResource("/tests/game10/pictures/a/Symbol" + randomIconNumber[random] + ".png"));
				icons[1][i] = new ImageIcon(getClass().
						getResource("/tests/game10/pictures/b/Symbol" + randomIconNumber[random] + ".png"));
				randomIconNumber[random]=-1;
			}else{
				i--;
			}
		}
		
		return icons;
	}
}
