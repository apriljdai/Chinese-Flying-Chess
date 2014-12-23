package com.woitt.batt.gamethread;

import com.woitt.batt.control.GameControl;
import com.woitt.batt.data.GameData;

public class Circle extends Thread {

	private GameData data;

	private GameControl gameContorl;

	@SuppressWarnings("unused")
	private Thread delayThread = new Thread();

	public Circle(GameData data, GameControl gameControl) {
		this.data = data;
		this.setGameContorl(gameControl);
	}

	@Override
	public void run() {

		int flag = data.getCurrentRole();
		while (data.isStart()) {
			if (flag == 0) {
				RemindThrowDice rtd = new RemindThrowDice(
						this.gameContorl.getGameServiceImpl().getGameData()
								.getPlayers()[0].getGamePanel());
				rtd.start();
				while (true) {
					if (this.data.isClick()) {
						rtd.interrupt();
						data.getDices()[flag].enableClick(false);
						this.gameContorl.getGameServiceImpl().throwDice(
								data.getDices()[flag]);
						
						this.data.setClick(false);
						this.gameContorl.getGameServiceImpl().judgePlayerThrowResult(
								flag, data.getDices()[flag].getNumber());
						break;
					}
				}
			}
            else {
				this.gameContorl.getGameServiceImpl().throwDice(
						data.getDices()[flag]);
				this.gameContorl.getGameServiceImpl().judgeCompThrowResult(flag, data.getDices()[flag].getNumber()) ;
				
			}
			flag = this.data.getCurrentRole() % 4;
			flag++;
			if (flag == 4) {
				flag = 0;
				data.getDices()[flag].enableClick(true);
			}
			data.setCurrentRole(flag);
		}
	}


	public GameControl getGameContorl() {
		return gameContorl;
	}


	public void setGameContorl(GameControl gameContorl) {
		this.gameContorl = gameContorl;
	}
}
