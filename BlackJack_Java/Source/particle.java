import java.awt.Graphics;
import java.awt.Color;
import javax.swing.JFrame;
import java.awt.geom.Line2D;

public class particle 
{
	private int particleAmount = 20;
 	private int[] particleX = new int[240];
	private int[] particleY = new int[240];
	private int[] particleXdX = new int[240];
	private int[] particleYdY = new int[240];	
	private int[] particleTime = new int[240];			
	private int particleCenterX = 500;
	private int particleCenterY = 600;
	private int particleCenterXNew = 350;
	private int particleCenterYNew = 100;
	private boolean flareSet = true;
	private int show = 0;

	private int bulletType = 0;
	private int slopeX = 0;
	private int slopeY = 0;
	public int timeFlight = 6;
	public int startBulletX = 0;
	public int startBulletY = 0;
	public int stopBulletX = 0;
	public int stopBulletY = 0;
	public int bulletSize = 25;
	
	particle shotGun;

	private Color C = new Color(192,255,62);

	public particle()
		{resetParticle();}

	public particle(int bulletTypeIn, int startXIn, int startYIn, int stopXIn, int stopYIn,int flightTimeIn)
		{
		timeFlight = flightTimeIn;
		bulletType = bulletTypeIn;
		startBulletX = startXIn;
		startBulletY = startYIn;
		stopBulletX = stopXIn;
		stopBulletY = stopYIn;
		slopeX = (stopXIn - startXIn)/timeFlight;
		slopeY = (stopYIn - startYIn)/timeFlight;
		if(bulletType == 5)
			shotGun = new particle();
		if(bulletTypeIn==4)
			bulletSize = 35;
		else
			bulletSize = 35;
		}

	public void paintParticle(Graphics g2d, int particleCenterXIn, int particleCenterYIn)
		{
			particleCenterX = particleCenterXIn;
			particleCenterY = particleCenterYIn;
					for(int i = 0;i<(particleAmount);i++)
					{if(particleTime[i]>30+(int)(20*Math.random()))
						{particleX[i] = (particleCenterX-20) + (int)(40*Math.random());
						particleY[i] = (particleCenterY-20) + (int)(40*Math.random());
						particleXdX[i] = -5+(int)(11*Math.random());
						particleYdY[i] = -5+(int)(11*Math.random());
						particleTime[i] = 0;}
					else{   particleX[i] = particleX[i] + particleXdX[i];
						particleY[i] = particleY[i] + particleYdY[i];
						particleTime[i]++;}
					g2d.setColor(Color.red);	

					for(int b = 0;b<99;b++){
						g2d.fillOval(particleX[b],particleY[b],15-particleTime[b]*3/4,15-particleTime[b]*3/4);}
					}
		}

	public void updateFlareLocation(int particleCenterXIn, int particleCenterYIn)
		{
		particleCenterX = particleCenterXIn;
		particleCenterY = particleCenterYIn;
		}


	public void drawBullet(Graphics g2d)
		{
bulletSize-=2;
		g2d.setColor(Color.WHITE);
		if(bulletType!=5)
			g2d.fillOval(startBulletX,startBulletY,bulletSize,bulletSize);
		else
			shotGun.flare(g2d);
		}

	public boolean checkBullet()
		{
		startBulletY +=slopeY;
		startBulletX +=slopeX;

		if((stopBulletY-100)>(startBulletY))
			return false;
		return true;
		}

	public void flare(Graphics g2d)
		{
			if(flareSet==true)
				{
					flareSet = false;
					show = 0;
				}
			if(show<70)
				{
					for(int i = 0;i<(particleAmount);i++)
					{
					  particleX[i] = particleX[i] + particleXdX[i];
						particleY[i] = particleY[i] + particleYdY[i];
						particleTime[i]++;	
					g2d.setColor(Color.orange);
					g2d.fillOval(particleX[i],particleY[i],20-particleTime[i]/4,20-	particleTime[i]/4);	
					}
				show++;
				}
			else
				{
					flareSet = true;
					particleCenterX = particleCenterXNew;
					particleCenterY = particleCenterYNew;
					show = 0;
				resetParticle();
				}

		}

	public void resetParticle()
		{
			flareSet = true;
					for(int i = 0;i<particleAmount;i++)
						{
						particleX[i] = (particleCenterX-20) + (int)(40*Math.random());
						particleY[i] = (particleCenterY-20) + (int)(40*Math.random());
						particleXdX[i] = -5+(int)(11*Math.random());
						particleYdY[i] = -5+(int)(11*Math.random());
						particleTime[i] = (int)(35*Math.random());
						}
			particleCenterYNew = (int)(400*Math.random());
			particleCenterXNew = (int)(1400*Math.random());
		}
	public void changeParticleAmout(int particleAmountIn)
		{	
		if(particleAmountIn<200)
		particleAmount = particleAmountIn;
		}

}