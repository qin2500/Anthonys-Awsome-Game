import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
//Button for placing coins
public class CoinButton extends Button {
	EditorState editorState;
	public CoinButton( Game game, int posx, int posy, int width, int height, EditorState e) {
		super(ImageLoader.loadImage("/Sprites/Coin.png"), game, posx, posy, width, height);
		// TODO Auto-generated constructor stub
		this.editorState = e;
	}

	@Override
	public void onClick() {
		// TODO Auto-generated method stub
		editorState.brushMode = "Coin";
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
		g.drawString("Coin", posx + 20, posy+75 );
	}

	@Override
	public void isHovering() {
		// TODO Auto-generated method stub
		if(mouse.posx >= posx && mouse.posx <= posx + width && mouse.posy >= posy && mouse.posy <= posy + height)
		{
			sprite = ImageLoader.loadImage("/Sprites/Buttons/Coin-Button-H.png");
			if(mouse.leftClick)
			{
				onClick();
				mouse.leftClick = false;
			}
		}
		else 
		{
			sprite = ImageLoader.loadImage("/Sprites/Coin.png");
		}
	}
	
	
}
