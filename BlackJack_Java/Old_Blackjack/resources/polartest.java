
import java.awt.*;
import javax.swing.*;
import java.awt.geom.GeneralPath;
import java.awt.*;
import java.awt.event.*;



import javax.swing.JFrame;


public class polartest extends JPanel 
{
private static final int Size = 1600;
private int count = 0;
private double x;
private double y;
private int h;
private int w;
private double dtheta = Math.PI/180;
private static final GeneralPath path = new GeneralPath();



public Dimension getPreferredSize()
	{
	return new Dimension(Size,Size/4);
	}
public void paint(Graphics g)
	{
	super.paint(g);
	Graphics2D g2d = (Graphics2D) g;
	w = getWidth()/2;
	h = getHeight()/2;
	path.reset();
	path.moveTo(w,h*2+50);
	for(double t = 0; t<2; t+=dtheta)
		{
		x = w*t;
		y = (h*Math.sin(6*t - (double)((double)count/20)) + h)/2 + 4*h/5;
		path.lineTo(x,y);
		path.lineTo(w,h/2);
		path.lineTo(w,h*2+50);
		path.lineTo(x,y);
		}
	g2d.setColor(Color.BLACK);
	g2d.fillRect(0,0,2*w,2*h);
	g2d.setColor(Color.GREEN);
	g2d.draw(path);


	count++;
	animate();
	}

public void animate()
	{
	try{Thread.sleep(15);}catch(InterruptedException ex) {Thread.currentThread().interrupt();}
	repaint();
	}



public static void main(String args[])
	{


						JFrame window = new JFrame();
			window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			window.add(new polartest());
			window.pack();
			window.setVisible(true);
window.setLocation(0,0);

						JFrame window1 = new JFrame();
			window1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			window1.add(new wave());
			window1.pack();
			window1.setVisible(true);
			window1.setLocation(1,450);
			window1.setSize(1600,500);







			
	}
}


//http://www.javabeginner.com/java-swing/java-jmenu-class-example