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

public class cubeGame extends JPanel implements MouseMotionListener, MouseListener{


////////////////////////////////////////////////////////////////////Variables

	//private Image scene1 = Toolkit.getDefaultToolkit().getImage(getClass().getResource("images/zImages/scene1.jpg"));

	public boolean play = false;
	public boolean gameOver = false;
	
	static int time = 0;
	int health = 100;
	int score = 0;
	
	int objectNumbers = 1;
	static int xd = 480;
	static int yd = 850;

	int dA = 20;

	int x = 480;
	static int y = 0;

	ArrayList<rObject> obstacles = new ArrayList<rObject>();
	ArrayList<tree> trees = new ArrayList<tree>();


	Color PearCider = new Color(208,155,121);
	Color gray1 = new Color(8,8,8);
	Color green1 = new Color(40,100,10);
	GradientPaint gradient1 = new GradientPaint(0,-200,Color.BLACK,0,350,green1,true);
	Font font3 = new Font("Arial", Font.PLAIN, 20);
	Font font2 = new Font("Arial", Font.PLAIN, 30);
	Font font1 = new Font("Arial", Font.PLAIN, 40);
	Font font = new Font("Arial", Font.PLAIN, 50);



////////////////////////////////////////////////////////////////////Constructor
 
   public cubeGame() {
        setPreferredSize(new Dimension(1000,900));
		addMouseMotionListener(this);
		addMouseListener(this);
    }
//////////////////////////////////////////////////////////////////Paint Method

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
	int w = getWidth();
	int h = getHeight();	

	
//g2d.drawImage(scene1, xViewPoint,yViewPoint, 9000,1600, null)
		g2d.setColor(Color.BLACK);
		g2d.fillRect(0,0,w,h);
	g2d.setColor(Color.GREEN);
		g2d.drawLine(0,h,w/2,h/4);
		g2d.drawLine(w,h,w/2,h/4);
	for(rObject each: obstacles)
		{each.drawObject(g2d);}
	for(tree each: trees)
		{each.drawTree(g2d);}
	g2d.fillOval(xd,yd,20,20);
	g2d.setFont(font1);
	g2d.drawString("Cube Runner",40,40);
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
		objectNumbers = 1;
		xd = 480;
		yd = 850;
		dA = 20;
		x = 480;
		y = 0;
		obstacles.clear();
		gameOver= false;
		}

////////////////////////////////////////////////////////////////////  GAME METHODS

	public void animate()
		{try{Thread.sleep(10);}catch(InterruptedException ex) {Thread.currentThread().interrupt();}
			time++;
		trees.add(new tree());

		if(time%dA==0)
			{
			int numAdd = (int)(3*Math.random());
				for(int zf=0;zf<numAdd;zf++)
					{obstacles.add(new rObject());
					}
					}
		for(int i=0;i<trees.size();i++)
			{
			if(trees.get(i).keep()==false)
				trees.remove(i);
			}
		if((xd>x) &&(x>50)&&(x<950))
			xd-= (int)(xd-x)/4;
		if((xd<x)&&(x>50)&&(x<950))
			xd+= (int)(x-xd	)/4;
		repaint();
	
	if(play==true)
		{
		score++;
		if(health<10)
			{
			play = false;
			gameOver = true;
			obstacles.clear();
			}
			for(int i=0;i<obstacles.size();i++)
				{if(obstacles.get(i).checkCollision())
					{health-=20;
					System.out.println("CRASH" + health);
					obstacles.remove(i);
					}
				else if(obstacles.get(i).keep(xd,yd)==0)
					{obstacles.remove(i);}
				}
			if(time%100==0)
				{
				if(dA>5)
					dA--;}
				}
		}  




	public void mouseDragged(MouseEvent e)
			{
			x = e.getX();
			y = e.getY();
			}
	public void mouseMoved(MouseEvent e){}
	public void mouseClicked(MouseEvent e){}  
	    public void mouseEntered(MouseEvent e) {}
	    public void mouseExited(MouseEvent e){}
	    public void mousePressed(MouseEvent e){}
	    public void mouseReleased(MouseEvent e) {}

}

class rObject
{
double x = 0;
double y = 225;
double dy = 1;
int time = 0;
double over = 0;
int a =0;
int b = 0;
int op=100;
double rad = 0.93324753;
	public rObject()
	{
	over = 1 - 2*Math.random();
	x = 500;
	y = 225;
	}

	public void drawObject(Graphics g2d)
		{
		a = (int)( (y-225)/Math.tan(rad)/4	);
		b = a;
		x = 500 - (y-225)/Math.tan(rad)*over;
		g2d.setColor(Color.GREEN);
		g2d.fillRect((int)(x),(int)(y),a,b);
		dy = (y-225)/30;
		if(dy==0)
			{if(time%10==0)
				dy = 1;
			else
				dy = 0;}
		y+=dy;	
		time++;
		}
	public int keep(int x9,int 	y9)
		{
		if(y>1100)
			return 0;
		else if(checkCollision())
			return 2;
		return 1;
		}

	public boolean checkCollision()
		{
		if(	((cubeGame.xd-10)>x) && ((cubeGame.xd-10)<(x+a)))
			{
			if((cubeGame.yd>(y)) && (cubeGame.yd<(y+a))	)
				{
				return true;}
			}
		return false;
		}
}

class tree
{
	private double treeX = 0;
	private double treeY = 0;
	private double treedX = 0;
	private double treedY = 0;
	private int[] treeSizingX = new int[3];
	private int[] treeSizingY = new int[3];
	private double scale = 0;
	double rad = 0.93324753;
	double over = 0;
	int side = 0;

public tree()
	{
		treeX = (int)(1000*Math.random());
		while(treeX>460&&treeX<540)
			treeX = (int)(1000*Math.random());
	
		if(treeX>600)
			treedX = 1.349;
		treeY = 200;
	}

public void drawTree(Graphics windowTemp)
	{
		treedY = Math.abs((treeY-190)/30);
		if(treeX>470)
			treedX = 1.149*treedY;
		else
			treedX = -1.149*treedY;
		treeX = treeX + treedX;
		treeY = treeY + treedY;		

		scale= Math.abs((treeY-160)/30);
				treeSizingX[0] = (int)(treeX);
				treeSizingY[0] = (int)(treeY);
				treeSizingX[1] = (int)(treeX) - (int)(3*scale);
				treeSizingY[1] = (int)(treeY) + (int)(15*scale);
				treeSizingX[2] = (int)(treeX) + (int)(3*scale);
				treeSizingY[2] = (int)(treeY) + (int)(15*scale);	
		windowTemp.setColor(Color.GREEN);
		windowTemp.fillPolygon(treeSizingX, treeSizingY, 3); 
	}
	public boolean keep()
		{
		if(treeY>1100)
			return false;
		else
			return true;
		}

}