import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.*;
//This is where you select what you want to do. Weither thats making a level, deleting one, playing one, or editing one 
public class LevelSelectState extends State implements KeyListener {
	BufferedImage background,title;
	File folder = new File("Assets/Levels");
	File[] levels;
	String currentFileName;
	
	LevelButton[] buttons;
	PlayButton playButton;
	EditButton editButton;
	NewLevelButton newLevelButton;
	DeleteButton deleteButton;
	BackButton backButton;
	public LevelSelectState(Game game) {
		super(game);
		levels = folder.listFiles();
		buttons = new LevelButton[levels.length];
		background = ImageLoader.loadImage("/Sprites/LevelSelectBackground.png");
		title = ImageLoader.loadImage("/Sprites/LevelSelect.png");
		for(int i=0; i<levels.length; i++)
		{
			buttons[i] = new LevelButton(game,150,150+i*75,600,50,levels[i].getPath(),levels[i].getName());
		}
		game.levelFilePath = "";
		playButton = new PlayButton(game, 1000,140,275,147);
		editButton = new EditButton(game, 1000, 300, 275, 147);
		newLevelButton = new NewLevelButton(game, 1000, 460, 275, 147);
		deleteButton = new DeleteButton(game, 1000, 620, 275, 147);
		backButton = new BackButton(game, 20, 20, 150,80,game.mainMenuState);
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		playButton.update();
		editButton.update();
		newLevelButton.update();
		deleteButton.update();
		backButton.update();
		for(LevelButton i : buttons)
		{
			i.update();
		}
		
	}

	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		g.drawImage(background, 0,0,1500,900,null);
		g.drawImage(title,450,10,600,120,null);
		newLevelButton.draw(g);
		playButton.draw(g);
		editButton.draw(g);
		deleteButton.draw(g);
		backButton.draw(g);
		g.setFont(new Font("TimesRoman",Font.PLAIN, 20));
		g.drawString("Selected Level: " + currentFileName, 1000, 100);
		for(LevelButton i : buttons)
		{
			i.draw(g);
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public void setCurrentFileName(String penis)
	{
		currentFileName = penis;
}

}
