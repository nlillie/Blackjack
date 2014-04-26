import java.awt.Graphics2D;
import java.awt.Color;
import javax.swing.JFrame;
import java.awt.geom.Line2D;

public class particle 
{
	private int particleAmount = 80;
 	private int[] particleX = new int[240];
	private int[] particleY = new int[240];
	private int[] particleXdX = new int[240];
	private int[] particleYdY = new int[240];	
	private int[] particleTime = new int[240];			
	private int particleCenterX = 350;
	private int particleCenterY = 600;
	private int particleCenterXNew = 350;
	private int particleCenterYNew = 100;
	private boolean flareSet = true;
	private int show = 0;


	public particle(){resetParticle();}
	public void paintParticle(Graphics2D g2d, int particleCenterXIn, int particleCenterYIn)
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
					g2d.setColor(Color.yellow);	

					for(int b = 0;b<99;b++){
						g2d.fillOval(particleX[b],particleY[b],20-particleTime[b]*3/4,20-particleTime[b]*3/4);}
					}
		}

	public void updateFlareLocation(int particleCenterXIn, int particleCenterYIn)
		{
		particleCenterX = particleCenterXIn;
		particleCenterY = particleCenterYIn;
		}

	public void flare(Graphics2D g2d)
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