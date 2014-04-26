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
	//private Image gun4 = Toolkit.getDefaultToolkit().getImage(getClass().getResource("images/zImages/gun5.png"));
	private Image snowball = Toolkit.getDefaultToolkit().getImage(getClass().getResource("images/zImages/snowball.png"));

	//public int gun3Ammo = 50;
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
		//gun3Ammo = 250;
		gun4Ammo = 10000;
		}
	
	public void addAmmo()
		{
		//gun3Ammo+=100;
		gun4Ammo+=200;
		}
		
	public void switchGun()
		{
		//if(gunNum==3)
			//gunNum=4;
		//else
			//gunNum = 3;
		}

	public boolean rapidFire()
		{//HAD TO REMOVE RAPID FIRE FEATURE TO MAKE "SCHOOL APPROPRIATE"
			//RE ADD FOR NORMAL GAME
		//if(gunNum==3)
			//return false;
		//else 
			return true;
		}

	public int gunType()
		{
		//if(gunNum==3)
			//return 3;
		//else
			return 4;
		}

	public void fire()
		{

		//if(gunNum==3)
			//gun3Ammo--;
		//else if(gunNum==4)
			gun4Ammo--;

		}
	
	public int checkAmmo() 
		{

		//if(gunNum==3)
			//return gun3Ammo;
		//else
			return gun4Ammo;
		//REMOVE COMMENTS FOR NON LAME VERSION

		}

	public void crossHairs(int xCrossHairIn, int yCrossHairIn)
		{
		xCrossHair = xCrossHairIn;
		yCrossHair = yCrossHairIn;
		}
	
	public void setFlightTime()
		{flightTime = 10;

		//if(gunNum==3)
			//flightTime=10;
		//else if(gunNum==4)
			flightTime=10;

		}

	public void addBullet(int xCrossHairIn, int yCrossHairIn)
		{
		setFlightTime();
		//bullets.add(new particle(gunNum,500,900,xCrossHairIn+50, yCrossHairIn+150,flightTime));
		bullets.add(new particle(4,500,900,xCrossHairIn+50, yCrossHairIn+150,flightTime));
		}
	
    	public void drawMe(Graphics window)
    		{
		Graphics2D g2d = (Graphics2D)window;
			g2d.setColor(Color.BLACK);
			g2d.fillRoundRect(800,30,200,70,10,10);
			g2d.setColor(Color.YELLOW);
			g2d.setFont(font2);
			g2d.drawString("Ammo: "+checkAmmo(),820,80);
		
			for(int c1 = 0; c1<bullets.size();c1++)
				{
				if(bullets.get(c1).checkBullet() == false)
					bullets.remove(c1);
				else
					bullets.get(c1).drawBullet(g2d);
				}

			g2d.shear((double)(	(double)(xCrossHair-450)/1000	),0);

	
			//if(gunNum==3)
				{g2d.drawImage(snowball,500,720,120,120,null);
				g2d.drawImage(gun3, 420, 720, 200,300, null);}
			//else if(gunNum==4)
				//{g2d.drawImage(snowball,430,580,160,160,null);
				//g2d.drawImage(gun4, 450, 600, 200,300, null);}
	//HAD TO REMOVE GUNS.  REMOVE COMMENTS AND VIEW OLD FILE FOR NORMAL GAME
			g2d.shear((double)(	(double)(-xCrossHair+450)/1000	),0);
			

		}//end drawMe  
}

