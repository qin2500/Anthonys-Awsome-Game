import java.awt.Graphics;
import java.awt.image.BufferedImage;
//this buttons is used to return to the previous screen
public class BackButton extends Button{
	State prevState;
	public BackButton(Game game, int posx, int posy, int width, int height,State prevState) {
		super(ImageLoader.loadImage("/Sprites/Buttons/Back-Button.png"), game, posx, posy, width, height);
		// TODO Auto-generated constructor stub
		this.prevState = prevState;
	}

	@Override
	public void onClick() {
		// TODO Auto-generated method stub
		game.currentState = prevState;
		//game.setUp();
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		isHovering();
	}

	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		g.drawImage(sprite,posx, posy,width,height,null);
	}

	@Override
	public void isHovering() {
		// TODO Auto-generated method stub
		if(mouse.posx >= posx && mouse.posx <= posx + width && mouse.posy >= posy && mouse.posy <= posy + height)
		{
			sprite = ImageLoader.loadImage("/Sprites/Buttons/Back-Button-H.png");
			if(mouse.leftClick)
			{
				onClick();
			}
		}
		else 
		{
			sprite = ImageLoader.loadImage("/Sprites/Buttons/Back-Button.png");
		}
	}

}
