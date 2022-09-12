import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.concurrent.TimeUnit;
import java.io.*;
//This is where the "game" part of the game takes place. If you know what i mean
public class GameState extends State implements KeyListener{
	
	boolean[] keys = new boolean[500];
	BufferedImage playerSprite;
	Player player;
	Level level;
	BackButton backButton;
	boolean win = false;
	public GameState(Game game)
	{
		super(game);
		playerSprite = ImageLoader.loadImage("/Sprites/Player.png");
		level = new Level(game.levelFilePath);
		level.setGameState(this);
		player = new Player(level.startXpos+7, level.startYpos+7, 5, playerSprite, level);
		player.gameState = this;
		
		backButton = new BackButton(game, 1360, 830, 100, 53, game.levelSelectState);
	}
	
	
	public void update() {
		if(!win)
		{
			//move player
			if(keys[KeyEvent.VK_W] || keys[KeyEvent.VK_UP]) player.moveUp();
			if(keys[KeyEvent.VK_A] || keys[KeyEvent.VK_LEFT]) player.moveLeft();
			if(keys[KeyEvent.VK_S] || keys[KeyEvent.VK_DOWN]) player.moveDown();
			if(keys[KeyEvent.VK_D] || keys[KeyEvent.VK_RIGHT]) player.moveRight();
			
			//update all obstacles
			for(Obstical i : level.obsticalManager.obsticals)
			{
				i.gameState = this;
				i.update();
			}
			
			//update coins
			for(Coin[] e : ObsticalManager.coins)
			{
				for(Coin i : e)
				{
					if(i != null)i.update(this);
				}
			}
			
			//check if the player is at the finish flag
			if(Math.floorDiv(player.getPosx(), 45) == Math.floorDiv(level.finishXpos, 45) && Math.floorDiv(player.getPosy(), 45) == Math.floorDiv(level.finishYpos, 45) ||
					Math.floorDiv(player.getPosx() + 30, 45) == Math.floorDiv(level.finishXpos, 45) && Math.floorDiv(player.getPosy(), 45) == Math.floorDiv(level.finishYpos, 45) ||
					Math.floorDiv(player.getPosx(), 45) == Math.floorDiv(level.finishXpos, 45) && Math.floorDiv(player.getPosy()+30, 45) == Math.floorDiv(level.finishYpos, 45) ||
					Math.floorDiv(player.getPosx() + 30, 45) == Math.floorDiv(level.finishXpos, 45) && Math.floorDiv(player.getPosy() + 30, 45) == Math.floorDiv(level.finishYpos, 45))
			{
				if(level.numOfCoin==0)win = true;
			}
			
			//update buttons
			backButton.update();
		}
		
	}

	
	public void draw(Graphics g) {
		//draw level and player
		level.draw(g);
		player.draw(g);
		
		
		//draw all coins
		for(Coin[] e : ObsticalManager.coins)
		{
			for(Coin i : e)
			{
				if(i != null)i.draw(g);
			}
		}
		
		//Draw all obsitcals
		for(Obstical i : level.obsticalManager.obsticals)
		{
			i.draw(g);
		}
		
		//draw the buttons
		backButton.draw(g);
		
		//Win screen
		if(win)
		{
			g.drawImage(ImageLoader.loadImage("/Sprites/Win.png"), 0, 0, null);
			Game.bufferS.show();
			while(true)
			{
				try {
					TimeUnit.MILLISECONDS.sleep(50);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if(game.mouse.rightClick)
				{
					win = false;
					game.mouse.rightClick = false;
					backButton.onClick();	
					break;
				}
			}
		}
	}
	
	//This is what happens when you die.
	public void playerDie()
	{
		player.setPosx( level.startXpos + 7);
		player.setPosy( level.startYpos + 7);
		for(Coin[] e :  level.obsticalManager.coins)
		{
			for(Coin i : e)
			{
				if(i!= null && i.collected == true)
				{
					i.collected = false;
					level.numOfCoin += 1;
				}
			}
		}
	}
	
	public void keyPressed(KeyEvent k) {
		// TODO Auto-generated method stub
		keys[k.getKeyCode()] = true;
		
	}
	public void keyReleased(KeyEvent k) {
		// TODO Auto-generated method stub
		keys[k.getKeyCode()] = false;
	}


	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public Player getPlayer()
	{
		return this.player;
	}

}
