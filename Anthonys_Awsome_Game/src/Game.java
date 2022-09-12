import java.applet.AudioClip;
import java.io.*;
import java.applet.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.*;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.TargetDataLine;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
//This is the center of the game. Think of it like a central station where all the code comes together to unite as one.
public class Game implements Runnable, KeyListener{
	
	public JFrame frame;
	public Canvas canvas;
	private int height, width;
	private boolean running = false;
	private Thread thread;
	private Player player;
	public static Mouse mouse;
	public String levelFilePath = "";
	public static Clip clip;
	
	private BufferStrategy buffer;
	private Graphics g;
	
	public State currentState;
	public State gameState;
	public State editorState;
	public State mainMenuState;
	public State levelSelectState;

	
	static BufferStrategy bufferS;
	
	public Game(int height , int width)
	{
		this.height = height;
		this.width = width;
		
		frame = new JFrame("Game");
		frame.setSize(new Dimension(height, width));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		
		
		canvas = new Canvas();
		canvas.setPreferredSize(new Dimension(width,height));
		canvas.setMaximumSize(new Dimension(width,height));
		canvas.setMinimumSize(new Dimension(width,height));
		
		frame.setFocusable(true);
		frame.requestFocus();
		frame.add(canvas);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
		mouse = new Mouse();
	}
	//Honestly this should be called setUpMainMenu cuz thats really all it does
	public void setUp()
	{
		playerMusic("Assets/Music/Wii sports.wav");
			
		mainMenuState = new MainMenuState(this);
		
		frame.addKeyListener((KeyListener) currentState);
		canvas.addKeyListener((KeyListener) currentState);
		frame.addMouseListener(mouse);
		frame.addMouseMotionListener(mouse);
		canvas.addMouseListener(mouse);
		canvas.addMouseMotionListener(mouse);
	
	}
	//sets up the level select menu
	public void setUpLevelSelect()
	{
		System.out.println("Set UP level select");
		levelSelectState = new LevelSelectState(this);
		currentState = levelSelectState;
		frame.addKeyListener((KeyListener) currentState);
		canvas.addKeyListener((KeyListener) currentState);
		frame.addMouseListener(mouse);
		frame.addMouseMotionListener(mouse);
		canvas.addMouseListener(mouse);
		canvas.addMouseMotionListener(mouse);
	}
	//sets up the game
	public void setUpGame()
	{
		playerMusic("Assets/Music/Wii Shop.wav");
		gameState = new GameState(this);
		currentState = gameState;
		frame.addKeyListener((KeyListener) currentState);
		canvas.addKeyListener((KeyListener) currentState);
		frame.addMouseListener(mouse);
		frame.addMouseMotionListener(mouse);
		canvas.addMouseListener(mouse);
		canvas.addMouseMotionListener(mouse);
	}
	//sets up the level editor
	public void setUpEditor()
	{
		playerMusic("Assets/Music/Mii.wav");
		editorState = new EditorState(this);
		currentState = editorState;
		frame.addKeyListener((KeyListener) currentState);
		canvas.addKeyListener((KeyListener) currentState);
		frame.addMouseListener(mouse);
		frame.addMouseMotionListener(mouse);
		canvas.addMouseListener(mouse);
		canvas.addMouseMotionListener(mouse);
	}
	public synchronized void start()
	{
		setUp();
		thread = new Thread(this);
		thread.start();
		running = true;
	}
	
	public synchronized void stop()
	{
		if(!running) return;
		running = false;
		try
		{
			thread.join();
		}
		catch(InterruptedException e)
		{
			e.printStackTrace();
		}
		System.exit(0);
	}
	
	//Updates everything on screen such as moving the player
	public void update()
	{
		if(currentState != null)currentState.update();
	}
	
	//Makes things appear on screen
	public void draw()
	{
		buffer = canvas.getBufferStrategy();
		
		if(buffer == null)
		{
			canvas.createBufferStrategy(3);
			return;
		}
		g = buffer.getDrawGraphics();
		
		
		//Clear everything on screen
		g.clearRect(0, 0, width, height);
		
		//draw stuff to a buffer
		if(currentState != null)currentState.draw(g);
		
		//show the buffer
		bufferS = buffer;
		buffer.show();
		g.dispose();
	}
	
	
	public void run()
	{
		currentState = mainMenuState;
		while(running)
		{
			try {
				thread.sleep(16);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			update();
			draw();
		}
	}
	


	public Player getPlayer()
	{
		return player;
	}
	
	public static void showBuffer()
	{
		bufferS.show();
	}
	
	//Plays music
	public static void playerMusic(String file)
	{
		File musicPath = new File(file);
		
		if(musicPath.exists())
		{
			if(clip != null)clip.stop();
			try {
				AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicPath);
				clip = AudioSystem.getClip();
				clip.open(audioInput);
				
				clip.start();
				clip.loop(Clip.LOOP_CONTINUOUSLY);
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
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
	
	


