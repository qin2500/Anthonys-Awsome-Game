import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
//The building blocks of the game
public abstract class Tile {
	
	//The tiles work off of an id system. Each unique tile is given an id so that it is easier to expand and add more types of tiles if i had time
	public static Tile[] tiles = new Tile[10];
	public static Tile ground = new Ground(0);
	public static Tile wall = new Wall(1);
	
	protected int posx, posy;
	protected BufferedImage sprite;

	
	protected final int id;
	
	public Tile( BufferedImage sprite, int id )
	{
		this.sprite = sprite;
		this.id = id;
		
		tiles[id] = this;
	}
	
	public boolean isSolid()
	{
		return false;
	}
	
	public int getPosx() {
		return posx;
	}

	public void setPosx(int posx) {
		this.posx = posx;
	}

	public int getPosy() {
		return posy;
	}

	public void setPosy(int posy) {
		this.posy = posy;
	}
	
	public BufferedImage getSprite() {
		return sprite;
	}
	public void setSprite(BufferedImage sprite) {
		this.sprite = sprite;
	}
	
	
	public abstract void update();
	public abstract void draw(Graphics g, int posx, int posy);
}
