package com.woitt.batt.service;

import com.woitt.batt.entiry.Dice;
import com.woitt.batt.ui.GameFrame;


public interface GameService {

	public void initGame(GameFrame frame);
	public void startGame(GameFrame frame);
	public void throwDice(Dice dice);
	public void judgePlayerThrowResult(int flag , int diceNumber);
	public void judgeCompThrowResult(int flag ,int diceNumber);
	public int  judgeIsWin(int flag);
	public void restartGame(GameFrame gameFrame);
	public void setLuckyNumber(char justAChar);
}
