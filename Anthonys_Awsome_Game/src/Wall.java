import java.awt.Graphics;
import java.awt.image.BufferedImage;
//Stops you from going places
public class Wall extends Tile {

	public Wall( int id) {
		super(ImageLoader.loadImage("/Sprites/Wall-A.png"), id);
	}

	public boolean isSolid()
	{
		return true;
	}
	
	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void draw(Graphics g,int posx, int posy) {
		// TODO Auto-generated method stub
		g.drawImage(sprite,posx, posy,null);
		this.posx = posx;
		this.posy = posy;
	}

}
