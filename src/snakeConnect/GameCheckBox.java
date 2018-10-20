package snakeConnect;

import javax.swing.JCheckBox;

public class GameCheckBox extends JCheckBox{
	private boolean poisonUsed=false;
	GameCheckBox(){
		//this.setEnabled(false);
		
	}
	void setPoisonUsed(boolean poisonTrowed){
		this.poisonUsed=poisonTrowed;
	}
	
	boolean getPoisonUsed(){
		return poisonUsed;
	}
	
}
