import java.awt.Graphics;
import java.awt.Color;
import javax.swing.*;
import java.awt.Image;
import javax.imageio.*;
import java.io.*;
import java.awt.event.*;
import java.awt.*;


public class zombie
{
	private int x;
	private int y;

	public int sizeX = 50;
	public int sizeY = 100;

	public boolean show =true;

	public int hits = 0;
	public boolean bleed = false;
	public boolean showBleed = false;
	public boolean showParachute = false;

	public int bloodTime =0;
	public int sizeYC = 0;
	public int sizeConstant = 0;
	public int zombieType = 0;

	private Image zombieD = Toolkit.getDefaultToolkit().getImage(getClass().getResource("images/zImages/zombie.png"));
private Image parachute = Toolkit.getDefaultToolkit().getImage(getClass().getResource("images/zImages/parachute.png"));

	
	public zombie(int xI, int yI)
		{
		x = xI;
		y = yI;
		}

	public zombie(int xI, int yI, int SCI)
		{
		x = xI;
		y = yI;
		sizeConstant = SCI;
		}

	public void reset()
		{
		x = 0;
		y = 450;
		hits = 0;
		bleed = false;
		showBleed = false;
		bloodTime = 0;
		sizeYC = 0;
		sizeConstant = 0;
		}
		
	public void drawParachute(boolean parachuteIn)
		{
		showParachute = parachuteIn;
		}

	public void hitPoint()	
		{
		hits++;
		bleed = true;
		showBleed = true;
		}

	public void putOnRoad()
		{
		sizeConstant+=20;
		}

	public void updateLocation(int xi, int yi, int sizeYCIn)
		{
		x = xi;
		y = yi;
		sizeYC = sizeYCIn;
		}

	public boolean checkHit()
		{
		return bleed;
		}

	public boolean checkShow()
		{
		return show;
		}
	
    	public void drawMe(Graphics window)
    		{
		sizeX = (int)(	(sizeYC-600)/(7)	);
		if(bleed==false)
		sizeY = sizeX*2;
		if(showParachute == true && show ==true)
			window.drawImage(parachute, x-8,y-50,sizeX+20, sizeY+30,null);

		if(show==true)
			{
			if(zombieType==0||zombieType>4)
				window.drawImage(zombieD, x,y, sizeX  + sizeConstant,sizeY + sizeConstant, null);
			}
		if(showBleed == true)
			{	
			sizeY-=4;
			bloodTime++;
			if(bloodTime>25)
				{bloodTime=0;
				showBleed = false;
				show=false;
				}
			}
		}
}

