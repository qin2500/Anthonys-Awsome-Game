import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.*;
import java.util.*;
import java.util.concurrent.TimeUnit;

import javax.swing.JOptionPane;
//Welcome to the editor state/screen !!! This is where levels are made!
public class EditorState extends State implements KeyListener {

	static Level level;
	static String filePath;
	String brushMode = "";
	static int coinNum = 0;
	ArrayList<Button> buttons = new ArrayList<>();
	public EditorState(Game game)
	{
		super(game);
		level = new Level(game.levelFilePath);
		filePath = game.levelFilePath;
		
		buttons.add(new SaveButton(game, 1360, 750, 100, 53, this));
		buttons.add(new BackButton(game, 1360, 830, 100, 53, game.levelSelectState));
		buttons.add(new WallButton(game,1360,100,60,60,this));
		buttons.add(new BallButton(game, 1430,100 , 60, 60, this));
		buttons.add(new SetStartButton(game, 1360, 640, 60, 60, this));
		buttons.add(new SetFinishButton(game,1430 , 640, 60, 60, this));
		buttons.add(new CoinButton(game, 1360, 180, 60, 60, this));
	}
	
	//This here lets you save the levels you make as .txt files.
	public static void saveLevel()
	{
		if(level.finishXpos < 2000  && level.finishYpos < 2000)
		{
			try {
				
				BufferedWriter bw = new BufferedWriter(new FileWriter(new File(filePath),false));
				bw.write((level.startXpos) + " " + (level.startYpos ) + "\n");
				bw.write(level.finishXpos + " " + level.finishYpos + "\n");
				String temp = "";
				//Save Tiles
				for(int i=0; i<level.getTilesArray().length; i++)
				{
					for(int j=0; j<level.getTilesArray()[0].length; j++)
					{
						temp += Integer.toString(level.getTilesArray()[i][j]) + " ";
					}
					temp += "\n";
					bw.write(temp);
					temp = "";
				}
				
				
				//Save Obsticals
				bw.write(level.getObsticalManager().obsticals.size() + "\n");
				for(Obstical o : level.getObsticalManager().obsticals)
				{
					Ball ball = (Ball)o;
					if(o.name.equals("Ball"))
					{
						temp = "Ball ";
						temp += ball.isLooping()  + " ";
						temp += (int)ball.getVelocity() + " ";
						temp += ball.getPointNum() + " ";
						temp += ball.getRadius() + " ";
						for(int[] i : ball.getPoints())
						{
							String posx = Integer.toString(i[0]);
							String posy = Integer.toString(i[1]);
							temp += posx + " " + posy + " "; 
						}

					}
					temp += "\n";
					bw.write(temp);
					temp = "";
				}
				
				//save coins
				bw.write(level.numOfCoin + "\n");
				
				for(Coin[] e : ObsticalManager.coins)
				{
					for(Coin i : e)
					{
						if(i != null)
						{
							bw.write((int)i.posx + " " + (int)i.posy + "\n");
						}
					}
				}
				
				bw.flush();
				bw.close();
				
				JOptionPane.showMessageDialog(null, "Level Saved!");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		else
		{
			JOptionPane.showMessageDialog(null, "Set a finish point before saving.", null, JOptionPane.WARNING_MESSAGE, null);
			game.mouse.leftClick = false;
		}
		
		
	}
	
	public void placeWall()
	{
		int i = Math.floorDiv(game.mouse.posx, 45);
		int j = Math.floorDiv(game.mouse.posy, 45);
		if(j < level.getTilesArray().length && j>=0 && i < level.getTilesArray()[1].length && i>= 0)
		{
			level.setTilesArray(j, i, 1);
		}	
	}
	
	public void placeBall()
	{
		int numOfPoints = 0;
		int velocity = 0;
		//Enter number of points
		while(true)
		{
			String temp = JOptionPane.showInputDialog("Imput how many points you would like.\n Max:10, Min:1");
			game.mouse.leftClick = false;
			if(temp == null)break;
			
			try
			{
				numOfPoints = Integer.parseInt(temp);
				if(numOfPoints > 10 || numOfPoints < 1) throw new NumberFormatException() ;
				break;
			}catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(null, "Enter an appropriate NUMBER >:C", temp, velocity);
				game.mouse.leftClick = false;
			}
		}
		if(numOfPoints > 0)
		{
			//Enter velocity
			while(true)
			{
				String temp = JOptionPane.showInputDialog("Imput the velocity.\n Unit: pixels per frame, Max: 10, Min: 1");
				game.mouse.leftClick = false;
				if(temp == null)break;
				try
				{
					velocity = Integer.parseInt(temp);
					if(velocity > 10 || velocity < 1) throw new NumberFormatException() ;
					break;
				}catch (NumberFormatException e) {
					JOptionPane.showMessageDialog(null, "Enter an appropriate NUMBER >:C",temp, JOptionPane.WARNING_MESSAGE, null);
					game.mouse.leftClick = false;
				}
			}
			if(velocity > 0)
			{
				Ball ball = new Ball(0,0,null,15,velocity,numOfPoints);
				JOptionPane.showMessageDialog(null, "Select your " + numOfPoints + " point in order.(Click on the grid tiles)");
				game.mouse.leftClick = false;
				
				//Select the points the ball will travel too
				int i = 0;
				while(true)
				{
					try {
						TimeUnit.MILLISECONDS.sleep(50);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					if(i>=numOfPoints) break;
					if(game.mouse.leftClick)
					{
						game.mouse.leftClick = false;
						int x = Math.floorDiv(game.mouse.posx, 45);
						int y = Math.floorDiv(game.mouse.posy, 45);
						if(y < level.getTilesArray().length && x>=0 && x < level.getTilesArray()[1].length && i>= 0)
						{
							ball.points[i][0] = x*45+8;
							ball.points[i][1] = y*45+7;
						}
						else continue;
						i++;
					}
				}
				if(JOptionPane.showConfirmDialog(null, "Would you like the ball to loop through the points. (otherwise, it will go back and forth.)","message",JOptionPane.YES_NO_OPTION)
						== JOptionPane.YES_OPTION)
				{
					ball.isLooping = true;
				}
				else ball.isLooping = false;
				
				ball.posx = ball.points[0][0];
				ball.posy = ball.points[0][1];
				
				level.obsticalManager.obsticals.add(ball);
				System.out.println(ball.posx/45 + " " + ball.posy/45);
				level.obsticalManager.obsticalIndexes[(int) (ball.posy/45)][(int) (ball.posx/45)] = ball;
				
				
				brushMode = "";
			}
			
		}
	}
	public void placeCoin()
	{
		int x = Math.floorDiv(game.mouse.posx, 45);
		int y = Math.floorDiv(game.mouse.posy, 45);
		Coin coin = new Coin(x*45, y*45, null);
		if(y < level.getTilesArray().length && y>=0 && x < level.getTilesArray()[1].length && x>= 0)
		{
			if(ObsticalManager.coins[y][x] == null)
			{
				ObsticalManager.coins[y][x] = coin;
				level.numOfCoin += 1;
			}
		}
		
	}
	@Override
	public void update() {
		// TODO Auto-generated method stub
		
		//update buttons
		for(Button i : buttons)
		{
			i.update();
		}
		//place stuff
		if(game.mouse.leftClick == true)
		{
			game.mouse.leftClick = false;
			if(brushMode.equals("Wall")) placeWall();
			if(brushMode.equals("Coin")) placeCoin();
				
		}
		
		//erase stuff
		if(game.mouse.rightClick == true)
		{
			game.mouse.rightClick = false;
			int i = Math.floorDiv(game.mouse.posx, 45);
			int j = Math.floorDiv(game.mouse.posy, 45);
			if(j < level.getTilesArray().length && j>=0 && i < level.getTilesArray()[1].length && i>= 0)
			{
				level.setTilesArray(j, i, 0);
				if(level.obsticalManager.obsticalIndexes[j][i] != null)
					{
						level.obsticalManager.obsticals.remove(level.obsticalManager.obsticals.indexOf(level.obsticalManager.obsticalIndexes[j][i]));
						level.obsticalManager.obsticalIndexes[j][i] = null;
					}
				if(ObsticalManager.coins[j][i] != null)
				{
					ObsticalManager.coins[j][i] = null;
					level.numOfCoin -= 1;
				}
			}
		}
		
		
	}

	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub

		//draw level
		level.draw(g);
		
		//Draw lable
		g.drawImage(ImageLoader.loadImage("/Sprites/Obsticals.png"), 1355,25,null);
		
		//write selected obstical
		g.setFont(new Font("TimesRoman",Font.PLAIN, 15));
		g.drawString("Selected: " + brushMode, 1360, 740);
		
		//draw all coins
		for(Coin[] e : ObsticalManager.coins)
		{
			for(Coin i : e)
			{
				if(i != null)i.draw(g);
			}
		}
		//draw all buttons
		for(Button i : buttons)
		{
			i.draw(g);
		}
	}
	
	//set the starting position of the player in the level
	public void setStart()
	{
		JOptionPane.showMessageDialog(null, "Please select the tile you would like the player to start on.");
		game.mouse.leftClick = false;
		while(true)
		{
			try {
				TimeUnit.MILLISECONDS.sleep(50);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(game.mouse.leftClick)
			{
				Mouse mouse = game.mouse;
				game.mouse.leftClick = false;
				int posx = Math.floorDiv(mouse.posx, 45) * 45;
				int posy = Math.floorDiv(mouse.posy, 45)* 45;
				if(posx <= 1350 || posx >= 0 || posy <= 900 || posy >= 0)
				{
					level.startXpos = posx;
					level.startYpos = posy;
				}
				break;
			}
		}
		
	}

	//set where the player must go to beat the level
	public void setFinish()
	{
		JOptionPane.showMessageDialog(null, "Please select the finish tile of the level.");
		game.mouse.leftClick = false;
		while(true)
		{
			try {
				TimeUnit.MILLISECONDS.sleep(50);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(game.mouse.leftClick)
			{
				Mouse mouse = game.mouse;
				game.mouse.leftClick = false;
				int posx = Math.floorDiv(mouse.posx, 45) * 45;
				int posy = Math.floorDiv(mouse.posy, 45)* 45;
				if(posx <= 1350 || posx >= 0 || posy <= 900 || posy >= 0)
				{
					level.finishXpos = posx;
					level.finishYpos = posy;
				}
				break;
			}
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
	

}
