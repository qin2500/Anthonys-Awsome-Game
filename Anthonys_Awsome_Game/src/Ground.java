import java.awt.Graphics;
import java.awt.image.BufferedImage;
//Its just what u step on. The object really does nothing other than draw a green square to the screen
public class Ground extends Tile {

	public Ground(int id) {
		super(ImageLoader.loadImage("/Sprites/Ground.png"), id);
		
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void draw(Graphics g, int posx, int posy) {
		// TODO Auto-generated method stub
		g.drawImage(sprite, posx, posy, null);
		this.posx = posx;
		this.posy = posy;
	}

}
