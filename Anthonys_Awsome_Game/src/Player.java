import java.awt.*;
import java.awt.image.BufferedImage;

import javax.swing.*;
//What you play as
public class Player {

	private int posx, posy, width, height; 
	public int centerX, centerY;
	private float velocity;
	private BufferedImage sprite;
	private Level level;
	public GameState gameState;
	
	public Player(int x, int y, float velocity, BufferedImage sprite, Level level)
	{
		this.level = level;
		this.width = 30;
		this.height = 30;
		this.sprite = sprite;
		this.posx = x;
		this.posy = y;
		this.velocity = velocity;
	}
	public void draw(Graphics g)
	{
		centerX = posx + (width/2);
		centerY = posy + (height/2);
		g.drawImage(sprite,posx, posy,null);
	}

	//moue up
	public void moveUp()
	{
		int tempY = Math.floorDiv(posy-(int)velocity, 45); 
		if(!level.getTile(tempY, Math.floorDiv(posx, 45)).isSolid() && !level.getTile(tempY, Math.floorDiv(posx+width, 45)).isSolid()) 		
			this.posy -= velocity;		
		else posy = tempY * 45+45;
	}
	//move down
	public void moveDown()
	{
		int tempY = Math.floorDiv(posy+height+(int)velocity, 45); 
		if(!level.getTile(tempY, Math.floorDiv(posx, 45)).isSolid() && !level.getTile(tempY, Math.floorDiv(posx+width, 45)).isSolid()) 
			this.posy += velocity;
		else posy = tempY * 45 - 30 - 1;
	}
	//move right
	public void moveRight()
	{
		int tempX = Math.floorDiv(posx + width + (int)velocity, 45);
		if(!level.getTile(Math.floorDiv(posy, 45),tempX).isSolid() && !level.getTile(Math.floorDiv(posy+30, 45), tempX).isSolid()) 
			this.posx += velocity;
		else posx = tempX * 45-30 -1;
	}
	//move left
	public void moveLeft()
	{
		int tempX = Math.floorDiv(posx - (int)velocity, 45);
		if(!level.getTile(Math.floorDiv(posy, 45),tempX).isSolid() && !level.getTile(Math.floorDiv(posy+30, 45), tempX).isSolid()) 
			this.posx -= velocity;
		else posx = tempX * 45+45;
	}
	
	
	
	
	
	public float getVelocity()
	{
		return velocity;
	}
	public int getPosx()
	{
		return posx;
	}
	public int getPosy()
	{
		return posy;
	}
	public int getWidth()
	{
		return this.width;
	}
	public int getHeight()
	{
		return this.height;
	}
	public void setPosx(int posx)
	{
		this.posx = posx;
	}
	public void setPosy(int posy)
	{
		this.posy = posy;
	}

}
