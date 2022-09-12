import java.awt.Graphics;
import java.awt.image.BufferedImage;
//Brings you to the level select screen
public class LevelSelectButton extends Button {

	public LevelSelectButton(Game game, int posx, int posy, int width, int height) {
		super(ImageLoader.loadImage("/Sprites/Buttons/LevelSelect-Button.png"), game,posx, posy, width, height);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onClick() {
		// TODO Auto-generated method stub
		game.setUpLevelSelect();
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
			sprite = ImageLoader.loadImage("/Sprites/Buttons/LevelSelect-Button-H.png");
			if(mouse.leftClick)
			{
				onClick();
			}
		}
		else 
		{
			sprite = ImageLoader.loadImage("/Sprites/Buttons/LevelSelect-Button.png");
		}
	}

}
