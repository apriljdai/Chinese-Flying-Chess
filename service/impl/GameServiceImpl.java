package com.woitt.batt.service.impl;

import java.util.List;
import java.util.Random;

import com.woitt.batt.data.GameData;
import com.woitt.batt.dispose.GameDispose;
import com.woitt.batt.entiry.Cell;
import com.woitt.batt.entiry.Dice;
import com.woitt.batt.entiry.DiceRing;
import com.woitt.batt.entiry.Plane;
import com.woitt.batt.entiry.SoundsPlayer;
import com.woitt.batt.gamethread.Circle;
import com.woitt.batt.gamethread.ThrowSix;
import com.woitt.batt.res.GameRes;
import com.woitt.batt.service.GameService;
import com.woitt.batt.ui.GameFrame;


public class GameServiceImpl implements GameService {

	private GameData gameData;

	private Circle circle;

	private Thread delayThread;

	public GameServiceImpl(GameData data) {
		this.gameData = data;
	}

	@Override
	public void initGame(GameFrame frame) {
		frame.getGamePanel().initPanel();
	}

	@Override
	public void startGame(GameFrame frame) {
		this.setCircle(new Circle(gameData, frame.getGamePanel()
				.getGameConrol()));
		this.circle.start();
	}


	public GameData getGameData() {
		return gameData;
	}


	public Circle getCircle() {
		return circle;
	}


	public void setCircle(Circle circle) {
		this.circle = circle;
	}

	@SuppressWarnings("static-access")
	@Override
	public void throwDice(Dice dice) {
		Thread diceRingTh = new Thread(new DiceRing(this.getGameData(), 1,
				GameDispose.DICE_RING_URL));
		diceRingTh.start();

		int temp;
		Random random = new Random();

		for (int i = 0; i < 13; i++) {
			try {
				delayThread.sleep(100);
			}
            catch (InterruptedException e1) {
				return;
			}
			temp = random.nextInt(4);
			dice.setIcon(GameRes.DICE_ICON[temp + 6]);
			dice.repaint();
		}

        
		if (dice.isPlayerDice()&&(this.getGameData().getLuckyNumber() != -1))
			temp = this.getGameData().getLuckyNumber();
		else
			temp = random.nextInt(6);
		dice.setNumber(temp + 1);
		
		this.getGameData().setLuckyNumber(-1) ;
		
		dice.setIcon(GameRes.DICE_ICON[temp]);
		dice.repaint();
		diceRingTh.interrupt();
		try {
			delayThread.sleep(300);
		}
        catch (InterruptedException e) {
			return;
		}

	}


	@Override
	public void judgePlayerThrowResult(int flag, int diceNumber) {
		if (diceNumber == 6) {
			ThrowSix throwSix = new ThrowSix(
					this.gameData.getPlayers()[flag].getGamePanel());
			throwSix.start();

			Plane[] planes = this.getGameData().getPlayers()[flag].getPlanes();
			for (Plane p : planes)
				p.addMouseListener();
			while (true) {
				if (this.gameData.getNumberOfCurrentPlane() != -1) {

					throwSix.interrupt();
					this.getGameData().getPlayers()[flag].movePlane(this
							.getGameData().getNumberOfCurrentPlane(),
							diceNumber);
					this.gameData.setNumberOfCurrentPlane(-1);
					for (Plane p : planes)
						p.removeMouseListener();
					break;
				}
			}
			this.gameData.setCurrentRole(flag + 3);
		}
        else {
			int tempNum = 0;

			Plane[] planes = this.getGameData().getPlayers()[flag].getPlanes();
			for (Plane p : planes) {
				if (p.getLoc() != -2 && p.getLoc() != -3)
					p.addMouseListener();
				else
					tempNum++;
			}
			if (tempNum == 4)
				return;
			while (true) {
				if (this.gameData.getNumberOfCurrentPlane() != -1) {
					this.getGameData().getPlayers()[flag].movePlane(this
							.getGameData().getNumberOfCurrentPlane(),
							diceNumber);
					this.gameData.setNumberOfCurrentPlane(-1);
					for (Plane p : planes) {
						if (p.getLoc() != -2)
							p.removeMouseListener();
					}
					break;
				}
			}
		}
	}

	@Override
	public void judgeCompThrowResult(int flag, int diceNumber) {

		Plane[] planes = this.getGameData().getPlayers()[flag].getPlanes();
		if (diceNumber == 6) {
			if (this.getOutPlane(flag) == 0) {
				int temp = 0;
				for (int i = 0; i < 4; i++)
					if (planes[i].getLoc() == -2) {
						temp = i;
						break;
					}

				this.gameData.getPlayers()[flag].launchPlane(temp);
			}
            else {

				int index = this.isGetDes(planes, diceNumber);
				if (index != -1)
					this.getGameData().getPlayers()[flag].movePlane(index,
							diceNumber);
				else if (this.isDoubleMoving(planes, diceNumber) != -1)
					this.getGameData().getPlayers()[flag]
							.movePlane(this.isDoubleMoving(planes, diceNumber),
									diceNumber);
				else if (this.getPlaneIndexAtHome(planes) != -1)
					this.getGameData().getPlayers()[flag].movePlane(
							this.getPlaneIndexAtHome(planes), diceNumber);
				else
					this.getGameData().getPlayers()[flag].movePlane(
							this.getNearDes(planes), diceNumber);

			}
			this.gameData.setCurrentRole(flag + 3);
		}
        else {
			if (this.getOutPlane(flag) != 0) {
				int index = this.isGetDes(planes, diceNumber);
				if (index != -1)
					this.getGameData().getPlayers()[flag].movePlane(index,
							diceNumber);
				else if (this.isDoubleMoving(planes, diceNumber) != -1)
					this.getGameData().getPlayers()[flag]
							.movePlane(this.isDoubleMoving(planes, diceNumber),
									diceNumber);
				else
					this.getGameData().getPlayers()[flag].movePlane(
							this.getNearDes(planes), diceNumber);
			}
		}
	}


	private int getOutPlane(int flag) {
		int temp = 0;

		Plane[] planes = this.getGameData().getPlayers()[flag].getPlanes();
		for (Plane p : planes) {
			if (p.getLoc() > -2)
				temp++;
		}
		return temp;
	}


	private int isGetDes(Plane[] planes, int diceNumber) {
		for (int i = 0; i < 4; i++)
			if (planes[i].getLoc() + diceNumber == 56)
				return i;
		return -1;
	}


	private int isDoubleMoving(Plane[] planes, int diceNumber) {

		List<Cell> path = this.getGameData().getLine(planes[0].getPlaneType());
		for (int i = 0; i < 4; i++)
			if (planes[i].getLoc() > -2
					&& (planes[i].getLoc() + diceNumber) <= 56)
				if (planes[i].getPlaneType().equals(
						path.get(planes[i].getLoc() + diceNumber)
								.getCellColor()))
					return i;
		return -1;
	}


	private int getNearDes(Plane[] planes) {
		int temp = 0;
		Random random = new Random();
		while (true) {
			temp = random.nextInt(4);
			if (planes[temp].getLoc() > -2)
				return temp;
		}
	}


	private int getPlaneIndexAtHome(Plane[] planes) {
		for (int i = 0; i < 4; i++)
			if (planes[i].getLoc() == -2)
				return i;
		return -1;
	}

	@Override
	public int judgeIsWin(int flag) {

		Plane[] planes = this.getGameData().getPlayers()[flag].getPlanes();

		int temp = 0;
		for (int i = 0; i < 4; i++)
			if (planes[i].getLoc() == -3)
				temp++;
		if (temp == 4)
			return flag;
		return -1;
	}


	public void judgeIsNearWin() {
		Plane[] planes = this.getGameData().getPlayers()[0].getPlanes();
		int temp = 0;
		for (Plane p : planes) {
			if (p.getLoc() == -3)
				temp++;
		}
		if (temp == 3 && !this.getGameData().isChangeBGM()) {

			this.getGameData().setChangeBGM(true);
			this.getGameData().getPlayers()[0].getGamePanel().getBgmThread()
					.interrupt();
			this.getGameData().getPlayers()[0].getGamePanel().getBgmPlayer()
					.stop();
        
			Thread nearWinThread = new Thread(new SoundsPlayer(
					this.getGameData(), Integer.MAX_VALUE,
					GameDispose.NEAR_WIN_RING_URL));
			nearWinThread.start();
		}

	}

	@Override
	public void restartGame(GameFrame gameFrame) {

	}

	@Override
	public void setLuckyNumber(char justAChar) {
		int luckyNumber = justAChar - '1';
		if (luckyNumber >= 0 && luckyNumber <= 5&&this.getGameData().getChance() >0){
			this.getGameData().getChance();
			this.getGameData().setLuckyNumber(luckyNumber);
			this.getGameData().setChance(this.getGameData().getChance()-1);
		}
	}
}
