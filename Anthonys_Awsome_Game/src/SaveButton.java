import java.awt.Graphics;
import java.awt.image.BufferedImage;
//lets you save your hard work
public class SaveButton extends Button {
	EditorState editorState;
	public SaveButton(Game game, int posx, int posy, int width, int height, EditorState editorState) {
		super(ImageLoader.loadImage("/Sprites/Buttons/Save-Button.png"), game, posx, posy, width, height);
		// TODO Auto-generated constructor stub
		this.editorState = editorState;
	}

	@Override
	public void onClick() {
		// TODO Auto-generated method stub
		editorState.saveLevel(); 
		System.out.println("Clicked");
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
	}

	@Override
	public void isHovering() {
		// TODO Auto-generated method stub
		if(mouse.posx >= posx && mouse.posx <= posx + width && mouse.posy >= posy && mouse.posy <= posy + height)
		{
			sprite = ImageLoader.loadImage("/Sprites/Buttons/Save-Button-H.png");
			if(mouse.leftClick)
			{
				onClick();
				mouse.leftClick = false;
			}
		}
		else 
		{
			sprite = ImageLoader.loadImage("/Sprites/Buttons/Save-Button.png");
		}
	}

}
