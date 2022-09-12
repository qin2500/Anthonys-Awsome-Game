import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
//These are the buttons that represent the different levels 
public class LevelButton extends Button {
	public String levelPath,levelName;
	public LevelButton(Game game, int posx, int posy, int width, int height, String levelPath, String levelName) {
		super(ImageLoader.loadImage("/Sprites/Buttons/Level-Button.png"), game, posx, posy, width, height);
		this.levelPath = levelPath;
		this.levelName = levelName;
		
	}

	@Override
	public void onClick() {
		// TODO Auto-generated method stub
		game.levelFilePath = levelPath;
		((LevelSelectState)game.levelSelectState).setCurrentFileName(new File(levelPath).getName());//this is the stupidest line of code i've ever written 
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		isHovering();
	}

	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		g.drawImage(sprite, posx,posy,width,height,null);
		g.setFont(new Font("TimesRoman",Font.PLAIN, 20));
		g.drawString(levelName, posx+10, posy+(height/2)+5);
	}

	@Override
	public void isHovering() {
		// TODO Auto-generated method stub
		if(mouse.posx >= posx && mouse.posx <= posx + width && mouse.posy >= posy && mouse.posy <= posy + height)
		{
			sprite = ImageLoader.loadImage("/Sprites/Buttons/Level-Button-H.png");
			if(mouse.leftClick)
			{
				onClick();
			}
		}
		else 
		{
			sprite = ImageLoader.loadImage("/Sprites/Buttons/Level-Button.png");
		}
	}

}
