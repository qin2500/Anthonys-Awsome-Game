import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
//Just the main menu, nother speical 
public class MainMenuState extends State implements KeyListener{
	Button levelSelect;
	BufferedImage background,title;
	public MainMenuState(Game game) {
		super(game);
		background = ImageLoader.loadImage("/Sprites/Background.png");
		title = ImageLoader.loadImage("/Sprites/Title.png");
		levelSelect = new LevelSelectButton(game, 750-130, 400, 260, 100);
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		levelSelect.update();
	}

	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		g.drawImage(background,0,0,1500,900,null);
		g.drawImage(title,350,100,800,186,null);
		levelSelect.draw(g);
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

}
