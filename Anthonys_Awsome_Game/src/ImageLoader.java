import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
//This just makes loading in images much much much easier
public class ImageLoader {
	public static BufferedImage loadImage(String path)
	{
		try {
			return ImageIO.read(Game.class.getResource(path));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.exit(1);
		}
		return null;
	}
}
