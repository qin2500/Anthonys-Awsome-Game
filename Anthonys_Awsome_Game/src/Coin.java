import java.awt.Graphics;
import java.awt.image.BufferedImage;
//MONEY MONEY MONEY!! The player must collect all of these before he can complete the level. Although some levels may not have any coins at all
public class Coin extends Obstical {
	int radius = 11;
	int centerX, centerY;
	boolean collected = false;
	public Coin(int posx, int posy, GameState gameState) {
		super(ImageLoader.loadImage("/Sprites/Coin.png"), posx, posy);
		// TODO Auto-generated constructor stub
		this.gameState = gameState;
		centerX = posx + 23;
		centerY = posy +23;
	}

	public void update(GameState g) {
		// TODO Auto-generated method stub
		if(!collected)
		{
			if(isIntersecting(g))
			{
				g.level.numOfCoin -= 1;
				collected = true;
			}
			
		}
		
	}

	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		if(!collected)g.drawImage(sprite,(int) posx, (int) posy, 45, 45, null);
	}

	//Check if the player is intersecting with the coin (same as the code from the ball object)
	public boolean isIntersecting(GameState g)
	{
			Player player = g.getPlayer();
			int circleDistX = Math.abs(centerX - player.centerX);
			int circleDistY = Math.abs(centerY - player.centerY);
			
			//check square
			if(circleDistX > (player.getWidth()/2 + radius)) return false;
			if(circleDistY > (player.getHeight()/2 + radius)) return false;
			
			//Check corners
			if(circleDistX <= player.getWidth()/2) return true;
			if(circleDistY <= player.getHeight()/2) return true;
			
			
			
			int cornetDist = (int) ((int) Math.pow((circleDistX - player.getWidth()/2),2)
							+ Math.pow((circleDistY - player.getHeight()/2), 2));
			
			return (cornetDist <= Math.pow(radius, 2));	

	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}
}
