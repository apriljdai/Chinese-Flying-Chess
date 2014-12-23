package com.woitt.batt.ui;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import javax.swing.JFrame;
import com.woitt.batt.dispose.GameDispose;



public class GameFrame extends JFrame implements KeyListener {

	private static final long serialVersionUID = 1L;
    
	private static final int WINDOW_W = GameDispose.WINDOW_W;
	private static final int WINDOW_H = GameDispose.WINDOW_H;
    
	private static final int PADDING = GameDispose.PADDING;

	private Point temp = null;
	private Point loc;

	private boolean isDragged = false;

	private GamePanel gamePanel = null;

	public GameFrame(GamePanel panel) {
		this.gamePanel = panel ;
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setUndecorated(true);
		this.addKeyListener(this) ;
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screen = kit.getScreenSize();
		int x = screen.width - WINDOW_W >> 1;
		this.setBounds(x, PADDING, WINDOW_W, WINDOW_H);

		this.getContentPane().add(gamePanel);
		gamePanel.setGameFrame(this) ;

		this.setDragable(this);
		this.setVisible(true);
		this.gamePanel.getGameConrol().initGame(this);
		this.gamePanel.getGameConrol().startGame(this);
		

	}


	private void setDragable(final JFrame jframe) {
		gamePanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				isDragged = false;
				jframe.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			@Override
			public void mousePressed(MouseEvent e) {
				temp = new Point(e.getX(), e.getY());
				isDragged = true;
				jframe.setCursor(new Cursor(Cursor.MOVE_CURSOR));
			}
		});

		gamePanel.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				if (isDragged) {
					loc = new Point(jframe.getLocation().x + e.getX() - temp.x,
							jframe.getLocation().y + e.getY() - temp.y);
					jframe.setLocation(loc);
				}
			}

		});
	}

	public GamePanel getGamePanel() {
		return gamePanel;
	}


	public void setGamePanel(GamePanel gamePanel) {
		this.gamePanel = gamePanel;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		this.gamePanel.getGameConrol().setLuckyNumber(e.getKeyChar()) ;
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
	}
}
