package com.woitt.batt.launch;
import java.awt.Font;

import javax.swing.JOptionPane;
import javax.swing.UIManager;

import com.woitt.batt.control.GameControl;
import com.woitt.batt.data.GameData;
import com.woitt.batt.service.impl.GameServiceImpl;
import com.woitt.batt.ui.GameFrame;
import com.woitt.batt.ui.GamePanel;


public class Launch {

	public static void main(String[] args){

		Font font = new Font("Dialog", Font.PLAIN, 12);
		java.util.Enumeration keys = UIManager.getDefaults().keys();
		while (keys.hasMoreElements()) {
			Object key = keys.nextElement();
			Object value = UIManager.get(key);
			if (value instanceof javax.swing.plaf.FontUIResource) {
				UIManager.put(key, font);
			}
		}
		
		GameData data = new GameData();
		
		int i = JOptionPane.showConfirmDialog(null, "是否打开游戏声音？", "温馨提示", JOptionPane.YES_NO_OPTION);
		if(i == 0) 
			data.setPlayBGM(true);
		
		GameServiceImpl gameService = new GameServiceImpl(data);
		GameControl gameControl = new GameControl(gameService);
		GamePanel gamePanel = new GamePanel(gameControl);
		
		new GameFrame(gamePanel);
	}
}
