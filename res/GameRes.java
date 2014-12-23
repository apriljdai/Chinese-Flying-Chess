package com.woitt.batt.res;

import java.awt.Image;
import java.io.File;

import javax.swing.ImageIcon;

import com.woitt.batt.dispose.GameDispose;


public class GameRes {

	public static final Image GAME_MAP;
	public static final ImageIcon[] redPlaneImgArray;
	public static final ImageIcon[] bluePlaneImgArray;
	public static final ImageIcon[] greenPlaneImgArray;
	public static final ImageIcon[] yellowPlaneImgArray;
	public static final ImageIcon[] DICE_ICON;
	public static final ImageIcon RED_CROWN;
	public static final ImageIcon YELLOW_CROWN;
	public static final ImageIcon GREEN_CROWN;
	public static final ImageIcon BLUE_CROWN;
	public static final ImageIcon SOURCE_ICON;
	static{
		
		SOURCE_ICON = new ImageIcon(GameDispose.SOURCE_INCO_URL);
        
		RED_CROWN = new ImageIcon(GameDispose.RED_CROWN);
		YELLOW_CROWN = new ImageIcon(GameDispose.YELLOW_CROWN);
		GREEN_CROWN = new ImageIcon(GameDispose.GREEN_CROWN);
		BLUE_CROWN = new ImageIcon(GameDispose.BLUE_CROWN);
		
		int planeNum = GameDispose.PLANE_NUMBER;
		GAME_MAP = new ImageIcon(GameDispose.GAME_MAP_URL).getImage();
		
		redPlaneImgArray = new ImageIcon[4];
		bluePlaneImgArray = new ImageIcon[4];
		greenPlaneImgArray = new ImageIcon[4];
		yellowPlaneImgArray = new ImageIcon[4];
		for(int i = 0 ;i < planeNum ;i++){
			redPlaneImgArray[i] = new ImageIcon(GameDispose.PLANE_IMG_URL+"r" + (i+1) + ".PNG");
			bluePlaneImgArray[i] = new ImageIcon(GameDispose.PLANE_IMG_URL+"b" + (i+1) + ".PNG");
			greenPlaneImgArray[i] = new ImageIcon(GameDispose.PLANE_IMG_URL+"g" + (i+1) + ".PNG");
			yellowPlaneImgArray[i] = new ImageIcon(GameDispose.PLANE_IMG_URL+"y" + (i+1) + ".PNG");
		}
		
		File file = new File(GameDispose.DICE_ICON_URL);
		File[] fields = file.listFiles();
		DICE_ICON =new ImageIcon[fields.length];
		for(int i = 0 ;i<fields.length;i++){
			DICE_ICON[i] = new ImageIcon(fields[i].getAbsolutePath());
		}
	}
}
