import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.swing.JOptionPane;
//Used for deleteing a selected level
public class DeleteButton extends Button {

	public DeleteButton(Game game, int posx, int posy, int width, int height) {
		super(ImageLoader.loadImage("/Sprites/Buttons/Delete-Button.png"), game, posx, posy, width, height);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onClick() {
		// TODO Auto-generated method stub
		if(game.levelFilePath != "")
		{
			File file = new File(game.levelFilePath);
			file.delete();
			game.setUpLevelSelect();
			game.levelFilePath = "";
			((LevelSelectState)(game.levelSelectState)).currentFileName = "";
		}
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
			sprite = ImageLoader.loadImage("/Sprites/Buttons/Delete-Button-H.png");
			if(mouse.leftClick)
			{
				onClick();
				mouse.leftClick = false;
			}
		}
		else 
		{
			sprite = ImageLoader.loadImage("/Sprites/Buttons/Delete-Button.png");
		}
	}

}
