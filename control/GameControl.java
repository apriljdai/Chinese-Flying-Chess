package com.woitt.batt.control;

import com.woitt.batt.service.impl.GameServiceImpl;
import com.woitt.batt.ui.GameFrame;


public class GameControl {
	
	private GameServiceImpl gameServiceImpl ;


	public GameControl(GameServiceImpl gameService){
		this.gameServiceImpl = gameService ;
	}


	public void initGame(GameFrame frame ){	
		gameServiceImpl.initGame(frame) ;
		
	}
	

	public void startGame(GameFrame frame){
		gameServiceImpl.startGame(frame) ;
	}

    
	public GameServiceImpl getGameServiceImpl() {
		return gameServiceImpl;
	}
	

	public void setGameServiceImpl(GameServiceImpl gameServiceImpl) {
		this.gameServiceImpl = gameServiceImpl;
	}
	

	public void restartGame(GameFrame gameFrame){
		this.gameServiceImpl.restartGame(gameFrame) ;
	}
	

	public void setLuckyNumber(char justAChar){
		this.gameServiceImpl.setLuckyNumber(justAChar) ;
	}

}
