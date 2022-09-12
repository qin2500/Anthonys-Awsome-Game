import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
//Lets you set where the player starts in a level
public class SetStartButton extends Button {
	EditorState editorState;
	public SetStartButton(Game game, int posx, int posy, int width, int height, EditorState editorState) {
		super(ImageLoader.loadImage("/Sprites/Start.png"), game, posx, posy, width, height);
		// TODO Auto-generated constructor stub
		this.editorState = editorState;
	}

	@Override
	public void onClick() {
		// TODO Auto-generated method stub
		editorState.setStart();
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
		g.setFont(new Font("TimesRoman",Font.PLAIN, 15));
		g.drawString("Set Start", posx+3, posy+70);
	}

	@Override
	public void isHovering() {
		// TODO Auto-generated method stub
		if(mouse.posx >= posx && mouse.posx <= posx + width && mouse.posy >= posy && mouse.posy <= posy + height)
		{
			sprite = ImageLoader.loadImage("/Sprites/Buttons/Start-Button-H.png");
			if(mouse.leftClick)
			{
				onClick();
				mouse.leftClick = false;
			}
		}
		else 
		{
			sprite = ImageLoader.loadImage("/Sprites/Start.png");
		}
	}

}
