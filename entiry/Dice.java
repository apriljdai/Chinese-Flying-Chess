package com.woitt.batt.entiry;

import java.awt.Cursor;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JLabel;
import com.woitt.batt.control.GameControl;
import com.woitt.batt.res.GameRes;


public class Dice extends JLabel implements MouseListener{


	private static final long serialVersionUID = 1L;
	private boolean isPlayerDice = false;
	private int number = 6;
	private GameControl gameControl;
	
	public Dice(boolean isPlayerDice,GameControl gameControl){
		this.setGameControl(gameControl);
		this.setPlayerDice(isPlayerDice);
		this.setSize(100, 100);
		this.setIcon(GameRes.DICE_ICON[this.number-1]);
		this.enableClick(isPlayerDice);
	}
	
	public void enableClick(boolean isEnable){
		if(isEnable) this.addMouseListener(this);
		else this.removeMouseListener(this);
	}
    
    
    //return the isPlayerDice
	public boolean isPlayerDice() {
		return isPlayerDice;
	}
    
    //param isPlayerDice the isPlayerDice to set
	public void setPlayerDice(boolean isPlayerDice) {
		this.isPlayerDice = isPlayerDice;
	}

    //return the number
	public int getNumber() {
		return number;
	}
    
    //param number the number to set
	public void setNumber(int number) {
		this.number = number;
	}

    @Override
	public void mouseClicked(MouseEvent e) {
		this.getGameControl().getGameServiceImpl().getGameData().setClick(true) ;
		
	}
    
    @Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

    @Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
    
	@Override
	public void mouseEntered(MouseEvent e) {
		this.setCursor(new Cursor(Cursor.HAND_CURSOR));
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

    //return the gameControl
	public GameControl getGameControl() {
		return gameControl;
	}
    
    //param gameControl the gameControl to set
	public void setGameControl(GameControl gameControl) {
		this.gameControl = gameControl;
	}

	
}
