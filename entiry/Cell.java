package com.woitt.batt.entiry;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;


public class Cell {

	private Point location =new Point(0,0);
	private List<Plane> planeList = new ArrayList<Plane>();
	private String cellColor = null;
	private int direction;
	
	public Cell(Point point,String colorStr){
		this.location = point;
		this.cellColor = colorStr;
	}


    //return the location
	public Point getLocation() {
		return location;
	}
    
    //param location the location to set
	public void setLocation(Point location) {
		this.location = location;
	}

    //return the planeList
	public List<Plane> getPlaneList() {
		return planeList;
	}

    //param planeList the planeList to set
	public void setPlaneList(List<Plane> planeList) {
		this.planeList = planeList;
	}

    //return the cellColor
	public String getCellColor() {
		return cellColor;
	}

    //param cellColor the cellColor to set
	public void setCellColor(String cellColor) {
		this.cellColor = cellColor;
	}

    //return the direction
	public int getDirection() {
		return direction;
	}

    //param direction the direction to set
	public void setDirection(int direction) {
		this.direction = direction;
	}
}
