import java.awt.Graphics;
import java.awt.image.BufferedImage;
//The thing that stands between you and victory 
public abstract class Obstical {
	
	protected float posx, posy;
	BufferedImage sprite;
	String name;
	GameState gameState;
	
	public Obstical(BufferedImage sprite,int posx, int posy)
	{
		this.posx = posx;
		this.posy = posy;
		this.sprite = sprite;
		
	}
	
	public abstract void update();
	public abstract void draw(Graphics g);
	
}
