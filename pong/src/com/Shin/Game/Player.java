package com.Shin.Game;

import java.awt.Color;
import java.awt.Graphics;

public class Player {
	
	public boolean up,down,right;
	public int x,y;
	public int spd = 4;
	public int WIDTH,HEIGHT;
	
	public Player(int x, int y) {
		this.x = x;
		this.y = y;
		WIDTH = 10;
		HEIGHT = 70;
	}

	public void tick() {
		
		if (y + HEIGHT >= Game.HEIGHT*Game.SCALE){
			y = Game.HEIGHT*Game.SCALE - HEIGHT;
		}else if(y <= 0) {
			y = 0;
		}
		
		if (up) {
			y-=spd;
		}else if(down) {
			y+=spd;
		}
		
		if (right) {
			x++;
		}
		
		
	}
	
	public void render(Graphics g) {
		g.setColor(Color.white);
		g.fillRect(x, y, WIDTH, HEIGHT);
	}
	
}
