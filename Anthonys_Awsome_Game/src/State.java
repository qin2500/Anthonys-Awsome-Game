import java.awt.Graphics;
//The UNITED STATES!
//A state is really just a screen in the game. Such as the game state or the main menu state. 
public abstract class State {

	public static State currentState = null;
	protected static Game game;
	public State(Game game)
	{
		game = game;
	}
	public static State getState()
	{
		return currentState;
	}
	
	public static void setState(State state)
	{
		currentState = state;
	}
	
	public abstract void update();
	public abstract void draw(Graphics g);
	
	
}
