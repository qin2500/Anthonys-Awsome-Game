import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
//Builds a great wall and makes no one pay for it. What a deal
public class WallButton extends Button {
	EditorState editorState;
	public WallButton(Game game, int posx, int posy, int width, int height, EditorState editorState) {
		super(ImageLoader.loadImage("Sprites/Wall-A.png"), game, posx, posy, width, height);
		// TODO Auto-generated constructor stub
		this.editorState = editorState; 
	}

	@Override
	public void onClick() {
		// TODO Auto-generated method stub
		editorState.brushMode = "Wall";
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
		g.setFont(new Font("TimesRoman",Font.PLAIN, 12));
		g.drawString("Wall", posx + 20, posy+75 );
	}

	@Override
	public void isHovering() {
		// TODO Auto-generated method stub
		if(mouse.posx >= posx && mouse.posx <= posx + width && mouse.posy >= posy && mouse.posy <= posy + height)
		{
			sprite = ImageLoader.loadImage("/Sprites/Buttons/Wall-A-Button-H.png");
			if(mouse.leftClick)
			{
				onClick();
				mouse.leftClick = false;
			}
		}
		else 
		{
			sprite = ImageLoader.loadImage("Sprites/Wall-A.png");
		}
	}

}
