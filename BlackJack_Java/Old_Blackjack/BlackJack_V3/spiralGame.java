import java.awt.Graphics;
import java.awt.Color;
import javax.swing.JFrame;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.awt.event.*;
import java.util.ArrayList;
import java.awt.*;
import javax.swing.*;
import java.awt.Image;
import javax.imageio.*;
import java.io.*;
import javax.swing.JOptionPane;
import java.awt.GradientPaint;
import java.util.ArrayList;

public class spiralGame extends JPanel implements MouseMotionListener, MouseListener{

	public boolean play = false;
	public boolean gameOver = false;
	
	static int time = 0;
	int health = 100;
	int score = 0;

	static int y = 0;
	static int x = 0;
	static int yd = 0;
	static 	int xd = 0;
	
	int r;
	int tc = 15;
	int count = 0;
	double jumpTheta = .01;
	public static boolean jump = false;

	static int playerRadius = 300;

	double dtheta = Math.PI/3600;
	static  double dCenter = 0;
	static boolean sprint = false;

	ArrayList<rObject2> obstacles1 = new ArrayList<rObject2>();
	ArrayList<rObject2> obstacles = new ArrayList<rObject2>();

	Color b1 = new Color(53,79,92);
	Color b3 = new Color(0,232,241);
	Color b2 = new Color(183,200,208);
	Font font3 = new Font("Arial", Font.PLAIN, 20);
	Font font2 = new Font("Arial", Font.PLAIN, 30);
	Font font1 = new Font("Arial", Font.PLAIN, 40);
	Font font = new Font("Arial", Font.PLAIN, 50);
 
   public spiralGame() {
        setPreferredSize(new Dimension(1000,900));
		addMouseMotionListener(this);
		addMouseListener(this);
    }

public void start()
	{
	play = true;
	score=  0;
	}

    @Override 
public void paintComponent(Graphics window) {
        super.paintComponent(window); 

		Image bufferedImage = createImage(getWidth(),getHeight());
		Graphics2D g2d = (Graphics2D) bufferedImage.getGraphics();
	int w = getWidth()/2;
	int h = getHeight()/2;	
	
//g2d.drawImage(scene1, xViewPoint,yViewPoint, 9000,1600, null)
		g2d.setColor(Color.BLACK);
		g2d.setColor(b2);
		g2d.fillRect(0,0,2*w,2*h);

	for(rObject2 each: obstacles1)
		{
		each.drawObject(g2d);
		}

g2d.setColor(b1);

g2d.setColor(Color.RED);	
	for(rObject2 each: obstacles)
		{
		each.drawObject(g2d);
		}

	g2d.setColor(Color.GREEN);
	g2d.setColor(b1);
	
	
	g2d.setColor(b3);
	if(jump==true)
		g2d.setColor(Color.GREEN);
	if(play==true)
		g2d.fillOval((	w+(int)(playerRadius*Math.sin(dCenter))	),(	h+(int)(playerRadius*Math.cos(dCenter))		),20,20);
		//g2d.drawOval(210,160,600,600);


	g2d.setFont(font1);
	g2d.setColor(b3);
	g2d.drawString("Spiral Dash",40,40);
	g2d.setFont(font2);
	g2d.drawString("Health: " + health,40,90);
	g2d.drawString("Score: " + score,40,130);

	if(gameOver==true)
		{
		g2d.setFont(font1);
		g2d.drawString("GAME OVER",380,400);
		}
		window.drawImage(bufferedImage, 0, 0, this);
		animate();
    }


	public void reset()
		{
		play = false;
		time = 0;
		health = 100;
		score = 0;
		gameOver= false;
		jump =false;
		tc = 15;	
		count = 0;
		jumpTheta = 0.01;
		}

	public void animate()
		{try{Thread.sleep(10);}catch(InterruptedException ex) {Thread.currentThread().interrupt();}
			time++;
		repaint();
	if(jump==true)
		{
		playerRadius = 300+(int)(-170*Math.sin(jumpTheta));
		jumpTheta +=.08;
		if(jumpTheta > Math.PI)
			{jump = false;
			jumpTheta=0;}
		}
	System.out.println(obstacles.size());

	if(time%100==0&&tc>4)
		{
		tc--;
		}
	if(time%tc==0)
		{
		obstacles.add(new rObject2(1));	
		}

	count++;
	dCenter+=.04;


		obstacles1.add(new rObject2());obstacles1.add(new rObject2());

		for(int i=0;i<obstacles1.size();i++)
			{int res = obstacles1.get(i).keep();
			if(res==0)
				{
				obstacles1.remove(i);
				}
			}
		for(int i=0;i<obstacles.size();i++)
			{int res = obstacles.get(i).keep();
			if(res==0)
				{
				obstacles.remove(i);
				}
			}			


	if(play==true)
		{
		score++;
		if(health<10)
			{
			play = false;
			gameOver = true;
			}
		if(sprint==true)
			{
			dCenter+=.04;
			}	
		for(int i=0;i<obstacles.size();i++)
			{int res = obstacles.get(i).keep();
			if(res==0)
				{
				obstacles.remove(i);}
			else if(res==1)
				{}
			else if(res==2)
				{
				health-=20;
				System.out.println("HIT");
				obstacles.remove(i);}
		
		}

	
		}  


}


	public void mouseDragged(MouseEvent e)
			{}
	public void mouseMoved(MouseEvent e){}
	public void mouseClicked(MouseEvent e){
			if(play==true)jump=true;
			}  
	    public void mouseEntered(MouseEvent e) {}
	    public void mouseExited(MouseEvent e){}
	    public void mousePressed(MouseEvent e){if(play==true)sprint=true;}
	    public void mouseReleased(MouseEvent e) {sprint = false;}
	
}

class rObject2
{
double theta = 0;
double r = 1;
int x = 500;
int y = 450;
int w = 0;
double dtheta = Math.PI/3600;
boolean cIn = false;

	public rObject2()
	{
	theta = (2*Math.PI*Math.random());
	}
	public rObject2(int c1In)
	{
	theta = (2*Math.PI*Math.random());
	cIn=true;
	}

	public void drawObject(Graphics g2d)
		{
		x = 500+ (int)(r*Math.cos(theta));
		y = 450+ (int)(r*Math.sin(theta));
		w = (int)(r/4);	
		g2d.setColor(Color.BLACK);
		if(cIn==true)
			g2d.setColor(Color.RED);
		g2d.fillOval(x,y,w,w);
		r+=(r/15);
		if(cIn==false)
			theta+=0.04;
		else
			theta+=-.04;
		}


	public int keep()
		{
		if(r>1100)
			return 0;
		else if(checkCollision())
			return 2;
		return 1;
		}


	//500+(int)(spiralGame.playerRadius*Math.sin(spiralGame.dCenter))	),(	450+(int)(spiralGame.playerRadius*Math.cos(spiralGame.dCenter))		),20,20
	public boolean checkCollision()
		{

		if(	((	500+(int)(spiralGame.playerRadius*Math.sin(spiralGame.dCenter))	)>x) && (		500+(int)(spiralGame.playerRadius*Math.sin(spiralGame.dCenter)))<(x+w)	 && spiralGame.jump==false	)
			{
			if((	(450+(int)(spiralGame.playerRadius*Math.cos(spiralGame.dCenter)))	>(y)) && (	(450+(int)(spiralGame.playerRadius*Math.cos(spiralGame.dCenter))	)<(y+w)) 	)
				{
				return true;}
			}
		return false;
		}

}
