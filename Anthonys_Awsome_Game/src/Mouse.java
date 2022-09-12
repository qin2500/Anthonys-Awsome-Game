import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.event.MouseInputListener;
//This just keeps track of the mouse.
public class Mouse implements MouseInputListener, MouseMotionListener{

	int posx, posy;
	boolean leftClick = false, rightClick = false;
	public Mouse()
	{
		
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		if(e.getButton() == MouseEvent.BUTTON1) leftClick = true;
		if(e.getButton() == MouseEvent.BUTTON3) rightClick = true;
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		if(e.getButton() == MouseEvent.BUTTON1) leftClick = false;
		if(e.getButton() == MouseEvent.BUTTON3) rightClick = false;
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
		posx = arg0.getX();
		posy = arg0.getY();

	}

}
