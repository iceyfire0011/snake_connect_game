package snakeConnect;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

public class GameFrames extends JFrame{
	Dimension frameSize= Toolkit.getDefaultToolkit().getScreenSize();
	
	public GameFrames() {

		this.setSize((int)frameSize.getWidth(),(int)frameSize.getHeight()*9/10);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}

}
