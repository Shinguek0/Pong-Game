package com.Shin.Game;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

public class Game extends Canvas implements Runnable, KeyListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static int WIDTH = 240, HEIGHT = 120, SCALE = 3;
	public static Player player;
	public static Enemy enemy;
	public static Ball ball;
	private boolean isRunning = true;
	private BufferedImage layer = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_ARGB_PRE);

	public Game() {
		this.setPreferredSize(new Dimension(WIDTH *SCALE,HEIGHT* SCALE));
		this.addKeyListener(this);
		
		player = new Player(50 , 145);
		enemy = new Enemy(Game.WIDTH*SCALE - 50, 145);
		ball = new Ball(360,180);
	}
	
	public static void main(String[] args) {
		Game game = new Game();
		JFrame frame = new JFrame("pong  'O'");
		frame.setResizable(true);  //resizeble
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(game);
		frame.pack();
		
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
		new Thread(game).start();
	}
	
	public void tick() {
		player.tick();
		enemy.tick();
		ball.tick();
	}
	
	public void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		Graphics g = layer.getGraphics();
		g = bs.getDrawGraphics();
		g.drawImage(layer, 0, 0, WIDTH*SCALE, HEIGHT*SCALE, null);
		g.setColor(Color.black);
		g.fillRect(0, 0, WIDTH*SCALE, HEIGHT*SCALE);
		
		enemy.render(g);
		player.render(g);
		ball.render(g);
		
		bs.show();
	}
	
	public void run() {
		while(isRunning) {
			tick();
			render();
			try {
				Thread.sleep(1000/60);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		
		if (e.getKeyCode() == KeyEvent.VK_UP) {
			player.up = true;
		}else if(e.getKeyCode() == KeyEvent.VK_DOWN) {
			player.down = true;
		}
		
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			player.right = true;
		}
		
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
		if (e.getKeyCode() == KeyEvent.VK_UP) {
			player.up = false;
		}else if(e.getKeyCode() == KeyEvent.VK_DOWN) {
			player.down = false;
		}
		
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			player.right = false;
		}
		
	}
	
	

}
