package com.woitt.batt.gamethread;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import com.woitt.batt.dispose.GameDispose;
import com.woitt.batt.ui.GamePanel;


public class ThrowSix extends Thread {

	private GamePanel gamePanel;
	private JLabel label = new JLabel();
	private Thread delayThread = new Thread();

	public ThrowSix(GamePanel gamePanel){
		this.gamePanel = gamePanel;
		label = new JLabel();
		int x = 120;
		int y = 600;
		label.setBounds(x, y, 150, 150);
		this.gamePanel.add(label);
	}
	
	@SuppressWarnings("static-access")
	@Override
	public void run(){
		int i = 1;
		while(true){
			try {
				this.delayThread.sleep(80);
			} catch (InterruptedException e) {
				break;
			}
			if(i<10)
			this.label.setIcon(new ImageIcon(GameDispose.THROW_SIX + "b00" + i + ".png"));
			else 
				this.label.setIcon(new ImageIcon(GameDispose.THROW_SIX + "b0" + i + ".png"));
			i++;
			if(i==24) i=1;
		}
	}
	@Override
	public void interrupt() {
		this.gamePanel.remove(this.label);
		this.gamePanel.repaint();
		super.interrupt();
	}
}
