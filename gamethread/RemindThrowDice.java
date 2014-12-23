package com.woitt.batt.gamethread;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import com.woitt.batt.dispose.GameDispose;
import com.woitt.batt.ui.GamePanel;


public class RemindThrowDice extends Thread {

	private GamePanel gamePanel;
	
	private JLabel label = new JLabel();
	
	private Thread delayThread = new Thread();
	
	public RemindThrowDice(GamePanel gamePanel){
		this.gamePanel = gamePanel;
		label = new JLabel();
		int x = 120;
		int y = 610;
		label.setBounds(x, y, 150, 150);
		this.gamePanel.add(label);
	}
	@SuppressWarnings("static-access")
	@Override
	public void run(){
		int i = 1 ;
		while(true){
			try {
				this.delayThread.sleep(50);
			}
            catch (InterruptedException e) {
				break;
			}
			this.label.setIcon(new ImageIcon(GameDispose.REMIND_THROW_URL + "a00" + i + ".png"));
			i++;
			if(i==7) i=1
		}
	}
	@Override
	public void interrupt() {
		this.gamePanel.remove(this.label);
		this.gamePanel.repaint();
		super.interrupt();
	}
}
