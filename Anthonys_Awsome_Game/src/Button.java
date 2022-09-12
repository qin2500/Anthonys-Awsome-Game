import java.awt.Graphics;
import java.awt.image.BufferedImage;
//The mother of all buttons
public abstract class Button {

	int posx, posy;
	int width, height;
	boolean hoverning = false;
	Mouse mouse;
	Game game;
	
	BufferedImage sprite;
	
	public Button(BufferedImage sprite, Game game,int posx,int posy,int width,int height)
	{
		this.posx = posx;
		this.posy = posy;
		this.width = width;
		this.height = height;
		this.game = game;
		this.mouse = game.mouse;
		this.sprite = sprite;
	}
	public abstract void onClick();
	public abstract void update();
	public abstract void draw(Graphics g);
	public abstract void isHovering();
}
