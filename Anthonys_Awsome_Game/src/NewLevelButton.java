import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JOptionPane;
//Lets you create a new level
public class NewLevelButton extends Button {

	public NewLevelButton(Game game, int posx, int posy, int width, int height) {
		super(ImageLoader.loadImage("/Sprites/Buttons/New-Button.png"), game, posx, posy, width, height);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onClick() {
		// TODO Auto-generated method stub
		String name = JOptionPane.showInputDialog("Enter Level Name: ");
		String fileName = name + ".txt";
		File file = new File("Assets/Levels/" + fileName);
		try {
			if(file.createNewFile())
			{
				creatLevel(file);
				game.levelFilePath = file.getPath();
				game.setUpLevelSelect();
				((LevelSelectState)game.levelSelectState).setCurrentFileName("");
			}
			else 
			{
				JOptionPane.showMessageDialog(null, fileName + " already exists!", fileName, JOptionPane.INFORMATION_MESSAGE);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void creatLevel(File file)
	{
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(file));
			bw.write("0 0\n");
			for(int i=0; i<20; i++)
			{
				for(int j=0; j<30; j++)
				{
					bw.write("0 ");
				}
				bw.write("\n");
			}
			bw.flush();
			bw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
			sprite = ImageLoader.loadImage("/Sprites/Buttons/New-Button-H.png");
			if(mouse.leftClick)
			{
				onClick();
				mouse.leftClick = false;
			}
		}
		else 
		{
			sprite = ImageLoader.loadImage("/Sprites/Buttons/New-Button.png");
		}
	}
	

}
