package com.Shin.Game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class Ball {
	
	public boolean up,down,right;
	public double x,y;
	public double spd = 6.5;
	private int WIDTH,HEIGHT;
	public double dx,dy;
	public int angle;
	public boolean wrongAngle = true;
	
	public Ball(int x, int y) {
		this.x = x;
		this.y = y;
		WIDTH = 20;
		HEIGHT = 20;
		
		while(wrongAngle == true) {
			angle = new Random().nextInt(359);
			checkAngle();
		}
		
	}
	
	public void checkAngle() {
		
		if (angle >= 4 && angle <= 60 || angle >= 130 && angle <= 255) {
			dy = Math.sin(Math.toRadians(angle));
			dx = Math.cos(Math.toRadians(angle));
			wrongAngle = false;
			System.out.println(angle);
			return;
		}else if(angle >= 300 && angle < 356) {
			dy = Math.sin(Math.toRadians(angle));
			dx = Math.cos(Math.toRadians(angle));
			wrongAngle = false;
			System.out.println(angle);
			return;
		}else {
			System.out.println("errado: " + angle );
		}
	}

	public void tick() {
		
		if(y + (dy*spd) + HEIGHT > Game.HEIGHT*Game.SCALE) {
			dy*=-1;
		}else if(y+(dy*spd) < 0) {
			dy*=-1;
		}	
		
		if(x > Game.WIDTH*Game.SCALE) {
			System.out.println("ponto do player");
			new Game();
			return;
		}else if(x < 0) {
			System.out.println("ponto inimigo");
			new Game();
			return;
		}
		
		Rectangle bounds = new Rectangle((int)(x+(dx*spd)),(int)(y+(dy*spd)), WIDTH, HEIGHT);
		Rectangle boundsPlayer = new Rectangle(Game.player.x,Game.player.y,Game.player.WIDTH,Game.player.HEIGHT);
		Rectangle boundsEnemy = new Rectangle((int)Game.enemy.x,(int)Game.enemy.y,Game.enemy.WIDTH,Game.enemy.HEIGHT);
		
		if(bounds.intersects(boundsPlayer)) {
			angle = new Random().nextInt(359);
			checkAngle();
			if (dx < 0) 
				dx*=-1;
		}else if (bounds.intersects(boundsEnemy)) {
			angle = new Random().nextInt(359);
			checkAngle();
			if(dx > 0)
				dx*=-1;
		}
		
		y += dy*spd;
		x += dx*spd;
	}
	
	public void render(Graphics g) {
		g.setColor(Color.white);
		g.fillOval((int)x,(int) y, WIDTH, HEIGHT);
	}
	
}

