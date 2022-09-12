import java.awt.Graphics;
import java.io.*;
import java.util.Arrays;
//Stores all the information about a level as you either edit it or play it
public class Level {

	private int[][] tiles = new int[20][30];
	private String file;
	public int startXpos, startYpos, finishXpos, finishYpos = -1;
	public ObsticalManager obsticalManager;
	public GameState gameState;
	public int numOfCoin;
	
	public Level(String file)
	{
		this.file = file;
		obsticalManager = new ObsticalManager();
		numOfCoin = 0;
		loadLevel();
		
	}
	
	//Goes and gets me a tile from the tiles array
	public Tile getTile(int i, int j)
	{
		if(i >= tiles.length || j >= tiles[0].length || i<0 || j<0) return Tile.wall;
		Tile temp = Tile.tiles[tiles[i][j]];
		if(temp != null) return temp;
		else return Tile.ground;
	}
	public void update()
	{
		
	}
	public void draw(Graphics g)
	{
		//Draw all tiles
		for(int i=0; i<tiles.length; i++)
		{
			for(int j=0; j<tiles[0].length; j++)
			{
				getTile(i,j).draw(g, j*45, i*45);
			}
		}
		
		//Draw the start and finish tiles
		if(startXpos >= 0 && startYpos >= 0)g.drawImage(ImageLoader.loadImage("/Sprites/Start.png"),startXpos,startYpos,45,45,null);
		if(finishXpos >= 0 && finishYpos >= 0)g.drawImage(ImageLoader.loadImage("/Sprites/Finish.png"),finishXpos,finishYpos,45,45,null);
		
		//Draw all obstacles
		for(Obstical i : obsticalManager.obsticals)
		{
			i.draw(g);
		}
	}
	public ObsticalManager getObsticalManager() {
		return obsticalManager;
	}
	
	//Loads in the level based off of the provided .txt file
	public void loadLevel()
	{
		obsticalManager.obsticals.clear();
		for(Coin[] i : obsticalManager.coins)
		{
			Arrays.fill(i, null);
		}
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			
			//Set up start and finish
			String[] posses = br.readLine().split(" ");
			startXpos = Integer.parseInt(posses[0]);
			startYpos = Integer.parseInt(posses[1]);
			String[] pin = br.readLine().split(" ");
			finishXpos = Integer.parseInt(pin[0]);
			finishYpos = Integer.parseInt(pin[1]);
			
			//set up tiles
			for(int i =0; i<tiles.length; i++)
			{
				
				String[] input = br.readLine().split(" ");
				for(int j=0; j<input.length; j++)
				{
					tiles[i][j] = Integer.parseInt(input[j]);;
				}
				
			}
			
			//Set up obstacles
			String beans = br.readLine();
			
			if(beans != null && !beans.equals(""))
			{
				int numOfObsticals = Integer.parseInt(beans);
				for(int i=0; i<numOfObsticals; i++)
				{
					String[] input = br.readLine().split(" ");
					if(input[0].equals("Ball"))
					{
						Ball ball = new Ball(0, 0, gameState , Integer.parseInt(input[4]), (int)Float.parseFloat(input[2]), Integer.parseInt(input[3]));
						
						if(input[1].equals("true")) ball.isLooping = true;
						else ball.isLooping = false;
						
						int index = 0;
						for(int j=5; j<input.length ; j+=2 )
						{
							ball.points[index][0] = Integer.parseInt(input[j]);
							ball.points[index][1] = Integer.parseInt(input[j+1]);
							index++;
						}
						ball.posx = (float)ball.points[0][0];
						ball.posy = (float)ball.points[0][1];
						obsticalManager.obsticalIndexes[(int) (ball.posy/45)][(int) (ball.posx/45)] = ball;
						obsticalManager.obsticals.add(ball);
					}
				}
			}
			
			//set coins
			int numOfCoins = Integer.parseInt(br.readLine());
			numOfCoin = numOfCoins;
			for(int i=0; i<numOfCoins; i++)
			{
				String[] temp = br.readLine().split(" ");
				Coin coin = new Coin(Integer.parseInt(temp[0]), Integer.parseInt(temp[1]), gameState);
				ObsticalManager.coins[(int) (coin.posy/45)][(int) (coin.posx/45)] = coin;
			}
			
			br.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e)
		{
			e.printStackTrace();
		} catch(NullPointerException e)
		{
			finishXpos = 2000;
			finishYpos = 2000;
		}
	}
	
	public int[][] getTilesArray()
	{
		return tiles;
	}
	
	public void setTilesArray(int i, int j, int input)
	{
		tiles[i][j] = input;
	}
	public void setGameState(GameState s)
	{
		this.gameState = s;
	}
}
