import java.awt.Graphics2D;
import java.awt.Color;
import javax.swing.*;
import java.awt.Image;
import javax.imageio.*;
import java.io.*;
import java.awt.event.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.Image;
import javax.imageio.*;
import java.util.ArrayList;

public class gun
{
	private  int xCrossHair = 0;
	private  int yCrossHair = 0;
	private  int gunNum = 3;
	public int flightTime = 0;

	public ArrayList<particle> bullets = new ArrayList<particle>();
	
	private Image gun3 = Toolkit.getDefaultToolkit().getImage(getClass().getResource("images/zImages/gun4.png"));
	private Image snowball = Toolkit.getDefaultToolkit().getImage(getClass().getResource("images/zImages/snowball.png"));

	public int gun4Ammo = 10000;

	Color PearCider = new Color(208,155,121);
	Color gray1 = new Color(8,8,8);
	Color green1 = new Color(40,100,10);
	GradientPaint gradient1 = new GradientPaint(0,-200,Color.BLACK,0,350,green1,true);
	Font font3 = new Font("Arial", Font.PLAIN, 20);
	Font font2 = new Font("Arial", Font.PLAIN, 30);
	Font font1 = new Font("Arial", Font.PLAIN, 40);
	Font font = new Font("Arial", Font.PLAIN, 50);


	
	public gun()
		{}

	public void resetAmmo()
		{
		gun4Ammo = 5000;
		}
	
	public void addAmmo()
		{
		gun4Ammo+=2000;
		}
		
	public void switchGun()
		{
		}

	public boolean rapidFire()
		{
			return true;
		}

	public int gunType()
		{
			return 4;
		}

	public void fire()
		{
			gun4Ammo--;
		}
	
	public int checkAmmo() 
		{
			return gun4Ammo;
		}

	public void crossHairs(int xCrossHairIn, int yCrossHairIn)
		{
		xCrossHair = xCrossHairIn;
		yCrossHair = yCrossHairIn;
		}
	
	public void setFlightTime()
		{
			flightTime=10;
		}

	public void addBullet(int xCrossHairIn, int yCrossHairIn)
		{
		setFlightTime();
		bullets.add(new particle(4,runner.z.getWidth()/2,900,xCrossHairIn+50, yCrossHairIn+150,flightTime));
		}
	
    	public void drawMe(Graphics window)
    		{
		Graphics2D g2d = (Graphics2D)window;
			g2d.setColor(Color.BLACK);
			g2d.fillRoundRect(1100,30,200,70,10,10);
			g2d.setColor(Color.YELLOW);
			g2d.setFont(font2);
			g2d.drawString("Ammo: "+checkAmmo(),1120,80);
		
			for(int c1 = 0; c1<bullets.size();c1++)
				{
				if(bullets.get(c1).checkBullet() == false)
					bullets.remove(c1);
				else
					bullets.get(c1).drawBullet(g2d);
				}

			g2d.shear((double)(	(double)(xCrossHair-450)/1000	),0);

	
			g2d.drawImage(snowball,500,720,120,120,null);
				g2d.drawImage(gun3, 420, 720, 200,300, null);
			g2d.shear((double)(	(double)(-xCrossHair+450)/1000	),0);
			

		}//end drawMe  
}

