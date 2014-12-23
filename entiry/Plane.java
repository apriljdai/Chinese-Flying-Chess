package com.woitt.batt.entiry;

import java.awt.Cursor;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import com.woitt.batt.data.GameData;
import com.woitt.batt.dispose.GameDispose;
import com.woitt.batt.res.GameRes;


public class Plane extends JLabel implements MouseListener{

	private static final long serialVersionUID = 1L;
	private final String planeType;
	private int planeDirection;
	private boolean isPlayer;
	private boolean isAtHome = true;
	private int loc = -2;
	private int index = -1 ;

	public static final String TYPE_RED = "r";
	public static final String TYPE_BLUE = "b";
	public static final String TYPE_GREEN = "g";
	public static final String TYPE_YELLOW = "y";
	
	public static final int UP = 4;
	public static final int DOWN = 1;
	public static final int LEFT = 2;
	public static final int RIGHT = 3;
	
	private GameData data;
	
	
	public Plane(String type ,int direction,boolean isPlayer,int index,GameData data){
		this.setData(data);
		this.setIndex(index);
		this.setSize(GameDispose.PLANE_SIZE, GameDispose.PLANE_SIZE);
		this.planeType = type;
		ChangeDireciont(planeType,direction);
		this.setPlayer(isPlayer);
	}
	

	public void ChangeDireciont(String type,int direction){
		if(type.equals(Plane.TYPE_RED)){
			ImageIcon icon = GameRes.redPlaneImgArray[direction-1];
			this.setIcon(icon);
			return;
		}
		if(type.equals(Plane.TYPE_GREEN)){
			ImageIcon icon = GameRes.greenPlaneImgArray[direction - 1];
			this.setIcon(icon);
			return;
		}
		
		if(type.equals(Plane.TYPE_BLUE)){
			ImageIcon icon = GameRes.bluePlaneImgArray[direction - 1];
			this.setIcon(icon);
			return;
		}
		if(type.equals(Plane.TYPE_YELLOW)){
			ImageIcon icon = GameRes.yellowPlaneImgArray[direction - 1];
			this.setIcon(icon);
			return;
		}
	}


	public void addMouseListener(){
		this.addMouseListener(this);
	}
	

	public void removeMouseListener(){
		this.removeMouseListener(this);
	}

	public int getPlaneDirection() {
		return planeDirection;
	}


	public void setPlaneDirection(int planeDirection) {
		this.planeDirection = planeDirection;
	}


	public int getLoc() {
		return loc;
	}


	public void setLoc(int loc) {
		this.loc = loc;
	}


	public String getPlaneType() {
		return planeType;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		this.data.setNumberOfCurrentPlane(this.getIndex()) ;
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		this.setCursor(new Cursor(Cursor.HAND_CURSOR)) ;
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

    
	public boolean isPlayer() {
		return isPlayer;
	}


	public void setPlayer(boolean isPlayer) {
		this.isPlayer = isPlayer;
	}


	public boolean isAtHome() {
		return isAtHome;
	}


	public void setAtHome(boolean isAtHome) {
		this.isAtHome = isAtHome;
	}


	public int getIndex() {
		return index;
	}


	public void setIndex(int index) {
		this.index = index;
	}

    
	public GameData getData() {
		return data;
	}


	public void setData(GameData data) {
		this.data = data;
	}
	
}
