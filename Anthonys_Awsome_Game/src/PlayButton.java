import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JOptionPane;
//Lets you play the game
public class PlayButton extends Button {

	public PlayButton(Game game, int posx, int posy, int width, int height) {
		super(ImageLoader.loadImage("/Sprites/Buttons/Play-Button.png"), game, posx, posy, width, height);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onClick() {
		// TODO Auto-generated method stub
		if(game.levelFilePath != "")game.setUpGame();
		else JOptionPane.showMessageDialog(game.frame, "No Level Selected!");
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		isHovering();
	}

	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		g.drawImage(sprite, posx, posy, width, height, null);
	}

	@Override
	public void isHovering() {
		// TODO Auto-generated method stub
		if(mouse.posx >= posx && mouse.posx <= posx + width && mouse.posy >= posy && mouse.posy <= posy + height)
		{
			sprite = ImageLoader.loadImage("/Sprites/Buttons/Play-Button-H.png");
			if(mouse.leftClick)
			{
				onClick();
				mouse.leftClick = false;
			}
		}
		else 
		{
			sprite = ImageLoader.loadImage("/Sprites/Buttons/Play-Button.png");
		}
	}

}
