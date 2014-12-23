package com.woitt.batt.entiry;

import java.io.File;

import com.woitt.batt.data.GameData;
import com.woitt.batt.util.SimpleJPlayer;


public class SoundsPlayer implements Runnable{

	protected SimpleJPlayer sjp = new SimpleJPlayer();
	protected GameData gameData;
	protected int playTimes  = 0;
	protected String soundsURL = null;
	public SoundsPlayer(GameData gameData,int playTimes,String soundsURL){
		this.gameData = gameData;
		this.playTimes = playTimes;
		this.soundsURL = soundsURL;
	}

	@Override
	public void run() {
		if(this.gameData.isPlayBGM()){
			this.playBGM();
		}
	}
	
	public void playBGM(){
		File file = new File(this.soundsURL);
		sjp.play(file,this.playTimes);
	}
	
	public void stop(){
		sjp.stop();
	}
}
