import java.awt.Graphics;
import java.awt.Color;
import javax.swing.JFrame;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
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

public class drawZombieContent extends JPanel implements MouseMotionListener, MouseListener, MouseWheelListener {


////////////////////////////////////////////////////////////////////Variables

	private Image scene1 = Toolkit.getDefaultToolkit().getImage(getClass().getResource("images/zImages/scene1.jpg"));
	private Image scene2 = Toolkit.getDefaultToolkit().getImage(getClass().getResource("images/zImages/scene2.jpg"));
	private Image scene3 = Toolkit.getDefaultToolkit().getImage(getClass().getResource("images/zImages/scene3.jpg"));
	private Image crosshair = Toolkit.getDefaultToolkit().getImage(getClass().getResource("images/zImages/crosshair.png"));	
	private Image healthBar = Toolkit.getDefaultToolkit().getImage(getClass().getResource("images/zImages/healthBar.jpg"));	
	private Image loser = Toolkit.getDefaultToolkit().getImage(getClass().getResource("images/zImages/you-lose.png"));

	public int sceneX = 9000;
	public int sceneY = 1600;

	int xViewPoint = -4500;	
	int yViewPoint = -500;	
	public boolean play = false;
	public int dX = 0;
	public int dY = 0;

	public int x = getWidth()/2;
	public int y = getHeight()/2;

	public ArrayList<zombie> zombies = new ArrayList<zombie>(); 
	public int[] zombiesX = new int[10000];
	public int[] zombiesY = new int[10000];
	public int zSpeed = 2;

	public boolean fire = false;
	public int xTemp = 0;
	public int yTemp = 0;

	public gun displayGun = new gun();
	public int[] loc = new int[10000];

	public int roundCount = 0;
	public int currentRoundZombies = 25;
	public int roundNumber = 0;
	public int killed = 0;


	public int delay = 0;
	public boolean tempCounter = false;

	int eB = 0;
	int eBL = 0;

	int time = 0;
	int health = 100;
	int score = 0;
	
	Color PearCider = new Color(208,155,121);
	Color gray1 = new Color(8,8,8);
	Color green1 = new Color(40,100,10);
	GradientPaint gradient1 = new GradientPaint(0,-200,Color.BLACK,0,350,green1,true);
	Font font3 = new Font("Arial", Font.PLAIN, 20);
	Font font2 = new Font("Arial", Font.PLAIN, 30);
	Font font1 = new Font("Arial", Font.PLAIN, 40);
	Font font = new Font("Arial", Font.PLAIN, 50);



////////////////////////////////////////////////////////////////////Constructor
 
   public drawZombieContent() {
		for(int e=0;e<zombiesX.length;e++)
			loc[e] = (int)(5*Math.random());
        setPreferredSize(new Dimension(1000,900));
		addMouseMotionListener(this);
		addMouseListener(this);
		addMouseWheelListener(this);

    }
//////////////////////////////////////////////////////////////////Paint Method

    @Override 
public void paintComponent(Graphics window) {
        super.paintComponent(window); 
		Image bufferedImage = createImage(getWidth(),getHeight());
		Graphics2D g2d = (Graphics2D) bufferedImage.getGraphics();
		try{
			if(roundNumber<2)
				g2d.drawImage(scene1, xViewPoint,yViewPoint, sceneX,sceneY, null);	
			else if(roundNumber<4)
				g2d.drawImage(scene2, xViewPoint,yViewPoint, sceneX,sceneY, null);	
			else if(roundNumber<6)
				{
				try{
					g2d.drawImage(scene3, xViewPoint,yViewPoint, sceneX,sceneY, null);
					}
				catch(Exception e)
					{g2d.drawImage(scene1, xViewPoint,yViewPoint, sceneX,sceneY, null);}
				}
			else
				g2d.drawImage(scene1, xViewPoint,yViewPoint, sceneX,sceneY, null);	
			}catch(Exception e){}
	
		for(int i=0;i<zombies.size();i++)
			{			
				if(((roundNumber==2)||(roundNumber==3))&&(zombiesY[i]<900))
					{zombies.get(i).drawParachute(true);}
				else
					zombies.get(i).drawParachute(false);
				zombies.get(i).updateLocation(xViewPoint+zombiesX[i],yViewPoint+zombiesY[i],zombiesY[i]);
				zombies.get(i).drawMe(g2d);
			}
				//add the x displacement you want from upper left of corner
				//add the y displacement you wanat from upper left o f corner
		g2d.setColor(Color.GRAY);

		g2d.drawImage(crosshair, x-50,y-50, 100,100, null);

		g2d.fillRect(x-30,y-5,60,10);
		g2d.fillRect(x-5,y-30,10,60);
		displayGun.crossHairs(x-50,y-50);
		displayGun.drawMe(g2d);

		g2d.drawImage(healthBar,200,800,600-6*Math.abs(100-health),80,null);
		g2d.setColor(Color.BLACK);
		if( displayGun.gunType() == 4)
			g2d.drawString("Snow Cannon",250,850);
		else 
			g2d.drawString("SnowBall Shooter",250,850);

		g2d.fillRoundRect(800,110,200,70,10,10);
		g2d.setColor(Color.YELLOW);
		g2d.setFont(font2);
		g2d.drawString("Score: "+score,820,160);

			if(play==false && health>50)
				{}
			else if(play==false && health<1)
				{g2d.drawImage(loser,300,300,400,200,null);}

		if(delay>1)
			{
			g2d.setColor(Color.YELLOW);
			g2d.setFont(font1);
			g2d.drawString("Round " + (roundNumber+1),500,500);
			delay--;
			play = true;
			}

		window.drawImage(bufferedImage, 0, 0, this);

		animate();
    }


////////////////////////////////////////////////////////////////////  GAME METHODS
	
	public void start()
		{
		play = true;
		delay = 100;
		}

	public void moveZombies()
		{
			for(int i=0;i<zombies.size();i++)
				{if((time%(4+loc[i])==0)&& (!(zombies.get(i).checkHit()))&& (play==true))
					zombiesY[i] +=(int)(zSpeed*Math.random());
				if(zombiesY[i] >1200)
					{zombiesY[i] = -999999;
					loc[i] = (int)(3*Math.random());
					health-=10;
					killed+=1;}
				}
		}




	public void checkHit()
		{
		for(int i=0;i<zombies.size();i++)
			{
			setEB(zombiesY[i]);
			if(	((x-xViewPoint)>(zombiesX[i]-eBL)) && ((x-xViewPoint)<(zombiesX[i]+eB)) 
			 	&& ((y-yViewPoint)>(zombiesY[i]- (2*eBL))) && ((y-yViewPoint)<zombiesY[i]+(2*eB)
				&& (zombies.get(i).checkHit()==false)) 	
				)
				{
				zombies.get(i).hitPoint();
					score+=100;
					killed +=1;
				}
			}
		}

	public void setEB(int zIn)
		{
		int gunNum = displayGun.gunType();
		eB = (int)(zIn/20);
		eBL = 10;
		if(gunNum==3)	
			{eB = (int)(zIn/10);
			eBL = 30;}	
		else if(gunNum==4)
			{eB = (int)(zIn/20);
			eBL = 10;}
		}
		

	public void addZombie()
		{
			if(delay<2)
			{
			if(roundNumber == 1)
				{zombies.add(new zombie(100,100));
					zombiesX[zombies.size()] = 4750+(int)(1200 - (2400*Math.random()));
					zombiesY[zombies.size()] = 750;
				roundCount++;	}
			else if(roundNumber>3&&roundNumber<6)
				{zombies.add(new zombie(100,100));
					zombiesX[zombies.size()] = 2000+(int)(3000 - (3000*Math.random()));
					zombiesY[zombies.size()] = 700;
				roundCount++;	}
			else
				{zombies.add(new zombie(100,100));
					zombiesX[zombies.size()] = 4750+(int)(1200 - (2400*Math.random()));
					zombiesY[zombies.size()] = 750;
				roundCount++;	}
			}
	System.gc();

		}
	public void reset()
		{
		time = 0;
		xViewPoint = -4500;	
		yViewPoint = -500;	
		dX = 0;
		dY = 0;
		play = false;
		health = 100;
		score = 0;
		roundCount = 0;
		zSpeed = 2;
		zombies.clear();
		displayGun.resetAmmo();
			for(int one:zombiesY)
				one = 0;
		}
		
	public void newRound()
		{
		zombies.clear();
		play = true;
			for(int one:zombiesY)
				one = 0;
		zSpeed++;
		}
	
	public void animate()
		{try{Thread.sleep(10);}catch(InterruptedException ex) {Thread.currentThread().interrupt();}
				time++;
	
		if(((xViewPoint<0) && (xViewPoint>-8000)) && ((yViewPoint<0)&&(yViewPoint>-700)) )
			{xViewPoint+=dX;yViewPoint+=dY;}
		else
			{
			if(xViewPoint<1000 && dX<0&&xViewPoint>-1000)
				{xViewPoint+=dX;}
			else if(xViewPoint<-8000&&dX>0)
				{xViewPoint+=dX;}

			else if(yViewPoint<0 && dY>0)
				yViewPoint+=dY;
			else if(yViewPoint>-700&&dY<0)
				{yViewPoint+=dY;}
	
			else if(yViewPoint<0&&dY<0&&	(xViewPoint<0) && (xViewPoint>-8000)    )
				xViewPoint+=dX;

			else if(yViewPoint>-8000&&dY>0&&	(xViewPoint<0) && (xViewPoint>-8000)    )
				xViewPoint+=dX;
	
			}
		if(fire==true)
			checkHit();

		moveZombies();
		fire=false;
		if((time%20==0) && (roundCount<currentRoundZombies) && (play==true))
			addZombie();

		if(health<1)
			play=false;
			
		if(time%20==0)
			tempCounter = true;

		if(killed>currentRoundZombies-2)
			{
				roundCount = 0;
				currentRoundZombies +=10;
				delay = 100;
				roundNumber++;
				displayGun.addAmmo();
				killed = 0;
				newRound();
			}



		repaint();
		}  




	public void mouseDragged(MouseEvent e)
			{
			x = e.getX();
			y = e.getY();
			dX = -(int)(	 (x- (getWidth()/2)  )/20	);
			if(play==true)
				dY = -(int)(	 (y- (getHeight()/2)  )/20	);	
			if ((e.getButton() == MouseEvent.BUTTON3)||(e.getButton() == MouseEvent.BUTTON2)) 
				displayGun.switchGun();
			else if(play==true&&!(displayGun.checkAmmo()==0) && displayGun.rapidFire())
				{fire=true;displayGun.fire();
				displayGun.addBullet(x-50,y-50);}
			else if(tempCounter == true&&!(displayGun.checkAmmo()==0))
				{fire=true;displayGun.fire();
				displayGun.addBullet(x-50,y-50);tempCounter = false;
				}
			}
	public void mouseMoved(MouseEvent e)
		{
			x = e.getX();
			y = e.getY();
			dX = -(int)(	 (x- (getWidth()/2)  )/20	);
			if(play==true)
				dY = -(int)(	 (y- (getHeight()/2)  )/20	);		
		}


	public void mouseClicked(MouseEvent e)
       		{
		if ((e.getButton() == MouseEvent.BUTTON3)||(e.getButton() == MouseEvent.BUTTON2)) 
				displayGun.switchGun();
		else if(play==true&&!(displayGun.checkAmmo()==0))
				{fire=true;displayGun.fire();
				displayGun.addBullet(x-50,y-50);}
		xTemp = e.getX();
		yTemp = e.getY();
		}  
	    public void mouseEntered(MouseEvent e) {}
	    public void mouseExited(MouseEvent e){}
	    public void mousePressed(MouseEvent e){}
	    public void mouseReleased(MouseEvent e) {}

  public void mouseWheelMoved(MouseWheelEvent e) {
			int notches = e.getWheelRotation();
     	  		displayGun.switchGun();	

    }









}