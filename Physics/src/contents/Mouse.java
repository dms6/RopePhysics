package contents;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


public class Mouse implements MouseListener, KeyListener{

	private int mouseXi = -1; 
	private int mouseYi = -1;
	private int mouseXf = -1; 
	private int mouseYf = -1;
	
	@Override
	public void mouseClicked(MouseEvent e) {

		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		mouseXi = e.getX();
		mouseYi = e.getY();
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		mouseXf = e.getX();
		mouseYf = e.getY();
		if(mouseXi==mouseXf && mouseYi==mouseYf) {
			//CHECK IF POINT EXISTS
			Point tempPoint = Simulate.findNearest(mouseXi, mouseYi);
			
			if(tempPoint!=null && Simulate.dist(tempPoint.x- mouseXi, tempPoint.y-mouseYi) <= Simulate.radius){
				tempPoint.locked = !tempPoint.locked;
				//System.out.println("Point Locked");
			}
			else {
				Simulate.points.add(new Point(e.getX(), e.getY(),false));
				//System.out.println("Point Created");
			}
		}
		else {
			//CHECK IF LINE CAN BE FORMED
			Point tempInitial = Simulate.findNearest(mouseXi, mouseYi);
			Point tempFinal = Simulate.findNearest(mouseXf,mouseYf);
			if(tempInitial != tempFinal) {
				Simulate.sticks.add(new Stick(tempInitial, tempFinal));
				
			}
			//else Same Point! Nothing happens
		}
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
	public void keyTyped(KeyEvent e) {
		if(e.getKeyChar()==' ')
			display.start = !display.start;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}
