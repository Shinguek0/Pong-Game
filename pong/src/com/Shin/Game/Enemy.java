package com.Shin.Game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class Enemy {
	
	public boolean up,down,right;
	public double x,y;
	public int spd = 4;
	public int WIDTH,HEIGHT;
	
	public Enemy(int x, int y) {
		this.x = x;
		this.y = y;
		WIDTH = 10;
		HEIGHT = 70;
	}

	public void tick() {
		
		if (this.y + HEIGHT >= Game.HEIGHT*Game.SCALE){
			this.y = Game.HEIGHT*Game.SCALE - HEIGHT;
		}else if(this.y <= 0) {
			this.y = 0;
		}
		
		y+= (Game.ball.y - (y - 10))*0.2;
		
		
	}
	
	public void render(Graphics g) {
		g.setColor(Color.white);
		g.fillRect((int)x,(int) y, WIDTH, HEIGHT);
	}
	
}
