package com.woitt.batt.data;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import com.woitt.batt.dispose.GameDispose;
import com.woitt.batt.entiry.Cell;
import com.woitt.batt.entiry.Dice;
import com.woitt.batt.entiry.Plane;
import com.woitt.batt.entiry.Role;


public class GameData {

    //initialize the data of game
	private boolean isStart = true;
	private boolean isPlayBGM = false;
    
	private Plane[] redP = new Plane[4];
	private Plane[] blueP = new Plane[4];
	private Plane[] greenP = new Plane[4];
	private Plane[] yellowP = new Plane[4];
    
	private int luckyNumber = -1 ;
	private int chance = 3;
    
	private List<Cell> pathList = new ArrayList<Cell>(
			GameDispose.LIST_PATN_CAPACITANCE);
	private List<Cell> crossList = new ArrayList<Cell>(
			GameDispose.CROSS_PATH_CAPACITANCE);
	private List<Cell> redList = new ArrayList<Cell>(
			GameDispose.PATH_CAPACITANCE);
	private List<Cell> greenList = new ArrayList<Cell>(
			GameDispose.PATH_CAPACITANCE);
	private List<Cell> blueList = new ArrayList<Cell>(
			GameDispose.PATH_CAPACITANCE);
	private List<Cell> yellowList = new ArrayList<Cell>(
			GameDispose.PATH_CAPACITANCE);

    private Role[] players = new Role[4];
	private Cell[] redHome = new Cell[4];
	private Cell[] yellowHome = new Cell[4];
	private Cell[] greenHome = new Cell[4];
	private Cell[] blueHome = new Cell[4];
	private int currentRole = 0;
	private Dice[] dices = new Dice[4];
	private boolean isClick = false;
	private int NumberOfCurrentPlane = -1;
	private boolean isChangeBGM = false ;
	{
		for (int i = 0; i < GameDispose.PLANE_NUMBER; i++) {
			this.getPlane(Plane.TYPE_BLUE)[i] = new Plane(Plane.TYPE_BLUE,
					Plane.UP, false,i,this);
			this.getPlane(Plane.TYPE_RED)[i] = new Plane(Plane.TYPE_RED,
					Plane.RIGHT, true,i,this);
			this.getPlane(Plane.TYPE_GREEN)[i] = new Plane(Plane.TYPE_GREEN,
					Plane.LEFT, false,i,this);
			this.getPlane(Plane.TYPE_YELLOW)[i] = new Plane(Plane.TYPE_YELLOW,
					Plane.DOWN, false,i,this);
		}

		this.setCurrentRole(0);

		for (int i = 0; i < GameDispose.LIST_PATN_CAPACITANCE; i++) {
			if (i < 1) {
				Cell cell = new Cell(getPiontByRC(14, 3), null);
				this.pathList.add(cell);
			}
            else if (i < 5) {
				Cell cell = new Cell(getPiontByRC(15 - i, 4),
						GameDispose.COLORARRAY[(i - 1) % 4]);
				cell.setDirection(Plane.UP);
				this.pathList.add(cell);

			}
            else if (i < 8) {
				Cell cell = new Cell(getPiontByRC(10, 8 - i),
						GameDispose.COLORARRAY[(i - 1) % 4]);
				cell.setDirection(Plane.LEFT);
				this.pathList.add(cell);
			}
            else if (i < 14) {
				Cell cell = new Cell(getPiontByRC(18 - i, 0),
						GameDispose.COLORARRAY[(i - 1) % 4]);
				cell.setDirection(Plane.UP);
				this.pathList.add(cell);
			}
            else if (i < 18) {
				Cell cell = new Cell(getPiontByRC(4, i - 14),
						GameDispose.COLORARRAY[(i - 1) % 4]);
				cell.setDirection(Plane.RIGHT);
				this.pathList.add(cell);
			}
            else if (i < 21) {
				Cell cell = new Cell(getPiontByRC(21 - i, 4),
						GameDispose.COLORARRAY[(i - 1) % 4]);
				cell.setDirection(Plane.UP);
				this.pathList.add(cell);
			}
            else if (i < 27) {
				Cell cell = new Cell(getPiontByRC(0, i - 17),
						GameDispose.COLORARRAY[(i - 1) % 4]);
				cell.setDirection(Plane.RIGHT);
				this.pathList.add(cell);
			}
            else if (i < 31) {
				Cell cell = new Cell(getPiontByRC(i - 27, 10),
						GameDispose.COLORARRAY[(i - 1) % 4]);
				cell.setDirection(Plane.DOWN);
				this.pathList.add(cell);
			}
            else if (i < 34) {
				Cell cell = new Cell(getPiontByRC(4, i - 20),
						GameDispose.COLORARRAY[(i - 1) % 4]);
				cell.setDirection(Plane.RIGHT);
				this.pathList.add(cell);
			}
            else if (i < 40) {
				Cell cell = new Cell(getPiontByRC(i - 30, 14),
						GameDispose.COLORARRAY[(i - 1) % 4]);
				cell.setDirection(Plane.DOWN);
				this.pathList.add(cell);
			}
            else if (i < 44) {
				Cell cell = new Cell(getPiontByRC(10, 54 - i),
						GameDispose.COLORARRAY[(i - 1) % 4]);
				cell.setDirection(Plane.LEFT);
				this.pathList.add(cell);
			}
            else if (i < 47) {
				Cell cell = new Cell(getPiontByRC(i - 33, 10),
						GameDispose.COLORARRAY[(i - 1) % 4]);
				cell.setDirection(Plane.DOWN);
				this.pathList.add(cell);
			}
            else if (i < 53) {
				Cell cell = new Cell(getPiontByRC(14, 57 - i),
						GameDispose.COLORARRAY[(i - 1) % 4]);
				cell.setDirection(Plane.LEFT);
				this.pathList.add(cell);
			}
		}

		for (int i = 0; i < GameDispose.CROSS_PATH_CAPACITANCE; i++) {
			if (i < 6) {
				Cell cell = new Cell(getPiontByRC(13 - i, 7),
						GameDispose.COLORARRAY[1]);
				cell.setDirection(Plane.UP);
				this.crossList.add(cell);
			}
            else if (i < 12) {
				Cell cell = new Cell(getPiontByRC(7, i - 5),
						GameDispose.COLORARRAY[2]);
				cell.setDirection(Plane.RIGHT);
				this.crossList.add(cell);
			}
            else if (i < 18) {
				Cell cell = new Cell(getPiontByRC(i - 11, 7),
						GameDispose.COLORARRAY[3]);
				cell.setDirection(Plane.DOWN);
				this.crossList.add(cell);
			}
            else if (i < 24) {
				Cell cell = new Cell(getPiontByRC(7, 31 - i),
						GameDispose.COLORARRAY[0]);
				cell.setDirection(Plane.LEFT);
				this.crossList.add(cell);
			}
		}

		this.redList.addAll(this.pathList.subList(1, 51));
		this.redList.addAll(this.crossList.subList(0, 6));

		this.yellowList.addAll(this.pathList.subList(14, 53));
		this.yellowList.addAll(this.pathList.subList(1, 12));
		this.yellowList.addAll(this.crossList.subList(6, 12));

		this.greenList.addAll(this.pathList.subList(27, 53));
		this.greenList.addAll(this.pathList.subList(1, 25));
		this.greenList.addAll(this.crossList.subList(12, 18));

		this.blueList.addAll(this.pathList.subList(40, 53));
		this.blueList.addAll(this.pathList.subList(1, 38));
		this.blueList.addAll(this.crossList.subList(18, 24));

		for (int i = 0; i < 4; i++) {
			this.redHome[i] = new Cell(getLoc(GameDispose.RED_FIRST_LOC, i),
					Plane.TYPE_RED);
			redHome[i].setDirection(Plane.RIGHT ) ;
			
			this.yellowHome[i] = new Cell(getLoc(GameDispose.YELLOW_FIRST_LOC,
					i), Plane.TYPE_YELLOW);
			yellowHome[i].setDirection(Plane.DOWN) ;
			
			this.greenHome[i] = new Cell(
					getLoc(GameDispose.GREEN_FIRST_LOC, i), Plane.TYPE_GREEN);
			greenHome[i].setDirection(Plane.LEFT) ;
			
			
			this.blueHome[i] = new Cell(getLoc(GameDispose.BLUE_FIRST_LOC, i),
					Plane.TYPE_BLUE);
			blueHome[i].setDirection(Plane.UP) ;
		}

	}


	private Point getPiontByRC(int row, int col) {
		return new Point(-2 + 32 * col, 121 + 32 * row);
	}

    
	public Plane[] getPlane(String type) {
		if (Plane.TYPE_BLUE.equals(type))
			return this.blueP;
		else if (Plane.TYPE_GREEN.equals(type))
			return this.greenP;
		else if (Plane.TYPE_RED.equals(type))
			return this.redP;
		else if (Plane.TYPE_YELLOW.equals(type))
			return this.yellowP;
		else
			return null;
	}


	public List<Cell> getLine(String type) {
		if (Plane.TYPE_BLUE.equals(type))
			return this.blueList;
		else if (Plane.TYPE_GREEN.equals(type))
			return this.greenList;
		else if (Plane.TYPE_RED.equals(type))
			return this.redList;
		else if (Plane.TYPE_YELLOW.equals(type))
			return this.yellowList;
		else
			return null;
	}

    
	public Point getLaunchPointByTyep(String type){
		if (Plane.TYPE_BLUE.equals(type))
			return GameDispose.BLUE_LAUNCH ;
		else if (Plane.TYPE_GREEN.equals(type))
			return GameDispose.GREEN_LAUNCH ;
		else if (Plane.TYPE_RED.equals(type))
			return GameDispose.RED_LAUNCH;
		else if (Plane.TYPE_YELLOW.equals(type))
			return GameDispose.YELLOW_LAUNCH;
		else
			return null;
	}
	
    
	private static Point getLoc(Point point, int index) {
		if (index < 2)
			return new Point(point.x + index * 42, point.y);
		else if (index < 4)
			return new Point(point.x + (index - 2) * 42, point.y + 42);
		else
			return null;
	}


	public boolean isStart() {
		return isStart;
	}


	public void setStart(boolean isStart) {
		this.isStart = isStart;
	}


	public boolean isPlayBGM() {
		return isPlayBGM;
	}


	public void setPlayBGM(boolean isPlayBGM) {
		this.isPlayBGM = isPlayBGM;
	}

    
	public Cell[] getRedHome() {
		return redHome;
	}


	public void setRedHome(Cell[] redHome) {
		this.redHome = redHome;
	}


	public Cell[] getYellowHome() {
		return yellowHome;
	}


	public void setYellowHome(Cell[] yellowHome) {
		this.yellowHome = yellowHome;
	}


	public Cell[] getGreenHome() {
		return greenHome;
	}


	public void setGreenHome(Cell[] greenHome) {
		this.greenHome = greenHome;
	}


	public Cell[] getBlueHome() {
		return blueHome;
	}


	public void setBlueHome(Cell[] blueHome) {
		this.blueHome = blueHome;
	}


	public int getCurrentRole() {
		return currentRole;
	}


	public void setCurrentRole(int currentRole) {
		this.currentRole = currentRole;
	}


	public Dice[] getDices() {
		return dices;
	}

    
	public void setDices(Dice[] dices) {
		this.dices = dices;
	}

	
	public boolean isClick() {
		return isClick;
	}

	
	public void setClick(boolean isClick) {
		this.isClick = isClick;
	}

	
	public Role[] getPlayers() {
		return players;
	}

	
	public void setPlayers(Role[] players) {
		this.players = players;
	}

	
	public int getNumberOfCurrentPlane() {
		return NumberOfCurrentPlane;
	}

	
	public void setNumberOfCurrentPlane(int numberOfCurrentPlane) {
		NumberOfCurrentPlane = numberOfCurrentPlane;
	}

	
	public boolean isChangeBGM() {
		return isChangeBGM;
	}


	public void setChangeBGM(boolean isChangeBGM) {
		this.isChangeBGM = isChangeBGM;
	}


	public int getLuckyNumber() {
		return luckyNumber;
	}


	public void setLuckyNumber(int luckyNumber) {
		this.luckyNumber = luckyNumber;
	}


	public int getChance() {
		return chance;
	}

	
	public void setChance(int chance) {
		this.chance = chance;
	}
}
