
import java.awt.*;
import javax.swing.*;
import java.awt.geom.GeneralPath;
import java.awt.*;
import java.awt.event.*;
import java.awt.Color;


import javax.swing.JFrame;


public class wave extends JPanel 
{
private static final int Size = 1600;
private int count = 0;
private double x;
private double y;
private int h;
private int w;
private int color1 = 0;
private boolean backdown = false;
private double dtheta = Math.PI/180;
private static final GeneralPath path = new GeneralPath();



public Dimension getPreferredSize()
	{
	return new Dimension(Size,Size*3/7);
	}
public void paint(Graphics g)
	{
	super.paint(g);
	Graphics2D g2d = (Graphics2D) g;
	w = getWidth()/2;
	h = getHeight()/2;
	path.reset();
	path.moveTo(w,h*2+50);
	for(double t = -3; t<6; t+=dtheta)
		{
		x = w*t;
		y = (h*Math.sin(6*t - (double)((double)count/20)) + h)/2 + 2*h;
		path.lineTo(x,y);
		path.lineTo(w,h/2);
		}
	g2d.setColor(Color.BLACK);
	//g2d.fillRect(0,0,2*w,2*h);


	//Color tester = new Color((int)(250*Math.random()),(int)(250*Math.random()),(int)(250*Math.random()));
	Color tester = new Color(0,color1/3,color1);
	g2d.setColor(tester);
	g2d.draw(path);
	count++;
	animate();
	}

public void animate()
	{
	if(color1<250&&backdown==false)
		color1+=2;
	else
		backdown=true;
	if(backdown==true)
		{color1-=2;if(color1<2)backdown=false;}

	try{Thread.sleep(10);}catch(InterruptedException ex) {Thread.currentThread().interrupt();}
	repaint();
	}



public static void main(String args[])
	{


						JFrame window = new JFrame();
			window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			window.add(new wave());
			window.pack();
			window.setVisible(true);

	

	}
}


//http://www.javabeginner.com/java-swing/java-jmenu-class-example