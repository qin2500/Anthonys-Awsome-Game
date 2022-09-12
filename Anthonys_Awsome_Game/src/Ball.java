import java.awt.Graphics;
import java.awt.image.BufferedImage;
//This is the ball obstacle, it moves around the level and kills the player
public class Ball extends Obstical {
	public int radius,centerX,centerY;
	int pointNum;
	int[][] points;
	float velocityX, velocityY, velocity;
	int currentPoint = 0;
	boolean isLooping = false;
	int direction = 1;
	public Ball(int posx, int posy, GameState gameState, int radius, int velocity, int pointNum) {
		super(ImageLoader.loadImage("/Sprites/Ball.png"),posx, posy);
		// TODO Auto-generated constructor stub
		points = new int[pointNum][2];
		this.gameState = gameState;
		this.radius = radius;
		this.centerX = posx + radius;
		this.centerY = posy + radius;
		this.velocity = velocity;
		this.pointNum = pointNum;
		this.name = "Ball";
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		this.centerX = (int) (posx + radius);
		this.centerY = (int) (posy + radius);
		if(isIntersecting())
		{
			gameState.playerDie();
		}
		if(Math.abs(posx-points[currentPoint][0])<=velocity && Math.abs(posy-points[currentPoint][1])<=velocity && pointNum >1)
		{
			if(currentPoint >= points.length-1 && isLooping)
			{
					currentPoint = 0;
			}
			else if(currentPoint >= points.length-1 && !isLooping) 
			{
				direction = -1;
				currentPoint += direction;
			}
			else if(currentPoint == 0 && direction == -1)
			{
				direction = 1;
				currentPoint += direction;
			}
			else currentPoint += direction;
			calcVelocity();
		}
		posx += velocityX;
		posy += velocityY;
	}
	
	//Calculates the x and y components of the the velocity vector using the current position and the target position.
	public void calcVelocity()
	{
		try
		{
			if(points[currentPoint][0] == posx && points[currentPoint][1] > posy)
			{
				velocityX = 0;
				velocityY = velocity;
			}
			else if(points[currentPoint][0] == posx && points[currentPoint][1] > posy)
			{
				velocityX = 0;
				velocityY = velocity*-1;
			}
			else if(points[currentPoint][1] == posy && points[currentPoint][0] > posx)
			{
				velocityX = velocity;
				velocityY = 0;
			}
			else if(points[currentPoint][1] == posy && points[currentPoint][0] < posx)
			{
				velocityX = velocity*-1;
				velocityY = 0;
			}
			else
			{

				float angle = (float) Math.atan2(-1*((points[currentPoint][1] - posy)),((points[currentPoint][0] - posx)));
				velocityX = (float) ((Math.cos(angle)*velocity));
				velocityY = (float) ((Math.sin(angle)*velocity)*-1);
			}
			
		}
		catch (ArithmeticException e)
		{
			velocityX = 0;
			velocityY = 0;
		}
		
	}
	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		g.drawImage(sprite, (int)posx, (int)posy,2*radius,2*radius, null);
	}
	
	//Check if the player is intersecting with the ball
	public boolean isIntersecting()
	{
			Player player = gameState.getPlayer();
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
	
	
	
	
	//getters and setters
	public int[][] getPoints()
	{
		return points;
	}

	public int getRadius() {
		return radius;
	}

	public void setRadius(int radius) {
		this.radius = radius;
	}

	public int getPointNum() {
		return pointNum;
	}

	public void setPointNum(int pointNum) {
		this.pointNum = pointNum;
	}

	public float getVelocity() {
		return velocity;
	}

	public void setVelocity(int velocity) {
		this.velocity = velocity;
	}

	public boolean isLooping() {
		return isLooping;
	}

	public void setLooping(boolean isLooping) {
		this.isLooping = isLooping;
	}

	public void setPoints(int[][] points) {
		this.points = points;
	}
	public void setGameState(GameState e)
	{
		this.gameState = e;
	}

}
