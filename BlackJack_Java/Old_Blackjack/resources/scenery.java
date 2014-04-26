import java.awt.Graphics;
import java.awt.Color;
import javax.swing.JFrame;
import java.awt.image.BufferedImage;
import java.util.Arrays;
import java.io.IOException;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.lang.Math;
import java.awt.Font;
import java.awt.event.*;


public class scenery extends JFrame implements KeyListener, MouseListener
{
    	private BufferedImage buffered;
    	private int cloudNum = 10;
    	private clouds[] myClouds = new clouds[cloudNum];
    	private int[] CloudPositionX = new int[cloudNum];
    	private int[] CloudPositionY = new int[cloudNum];
    	private int x = 400;
    	private int y = 500;
	private int[] dx = new int[30];
	private int[] dy = new int[30];
	private int displacement = -400;
   	private int time = 0;
	private int[] buildingX = new int[30];
	private int personY = 570;
	private boolean jump = false;
	private double personTheta = 0;
	private boolean Fall = false;
	private boolean drawPerson = false;
	private boolean temp = false;
	private int incriment = 5;
	private int Flytime = 0;
	private int score = 0;
	private int icicleNums = 100;
	private icicles[] icicle = new icicles[icicleNums];
	private int[] icicleX = new int[icicleNums];
	private int[] icicleY = new int[icicleNums];
	private int rockNums = 10;
	private rock[] rocks = new rock[rockNums];
	private int[][] rocksXY = new int[3][rockNums];
	private chicken ball = new chicken();
	Color C1 = new Color(255,185,15);
	Font font = new Font("Arial", Font.PLAIN, 25);
	Font font1 = new Font("Arial", Font.PLAIN, 35);
	
    public scenery()
    {
        super("My Scenery");
        addKeyListener(this);
	addMouseListener(this);
        int CloudX = 400;
        int CloudY = 150;
    
        for(int i=0; i < myClouds.length; i++)
        {
		CloudPositionX[i] = (int)(1600*Math.random());
		if(i<(cloudNum/2))CloudPositionY[i] = 80;
		if(i>=(cloudNum/2))CloudPositionY[i] = 30;
        	myClouds[i] = new clouds(CloudPositionX[i], CloudPositionY[i]);
        }
	for(int i=0;i<icicleNums;i++)
		{ 
		icicleX[i] = (int)(1600*Math.random()); icicleY[i] = 700+(int)(100*Math.random());
		icicle[i] = new icicles(icicleX[i],icicleY[i]);
		}
	for(int i=0;i<rockNums;i++)
		{ 
		rocksXY[1][i] = (int)(1600*Math.random()); rocksXY[2][i] = 680+(int)(20*Math.random());
		rocks[i] = new rock(rocksXY[1][i],rocksXY[2][i]);
		}
        setSize(1600,800);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }//end constructor
    

    public void paint(Graphics window)
    {
	animate();
	if(buffered==null)
 		buffered = (BufferedImage)(createImage(getWidth(),getHeight()));
    	Graphics windowTemp = buffered.createGraphics();
    	
    	//Draw background
    	windowTemp.setColor(Color.BLACK);
    	windowTemp.fillRect(0,0,1600,800);
    	
	for(int i = 0; i<myClouds.length;i++)
		myClouds[i].drawMe(windowTemp);

	for(int i = 0;i<18;i++)
		{paintBuildingsFront(windowTemp, (-1200+ displacement + 400*i),600);buildingX[i]=-1200 + displacement + 400*i;}
	for(int i = 0;i<2;i++)
		paintPlane(windowTemp, (-600+ (displacement*2) + 400*i),300);
	if(displacement>1200||displacement<-490)
		displacement = -400;
	if(drawPerson==true)
		{windowTemp.setColor(C1);ball.drawMe(windowTemp,780,personY-50);time++;}
	for(int i=0;i<icicleNums;i++)
		icicle[i].drawMe(windowTemp);
	for(int i=0;i<rockNums;i++)
		rocks[i].drawMe(windowTemp);
	windowTemp.setColor(Color.DARK_GRAY);
		windowTemp.fillRoundRect(1295,245,70,70,10,10);
		windowTemp.fillRoundRect(1375,165,70,70,10,10);
		windowTemp.fillRoundRect(1455,245,70,70,10,10);
		windowTemp.fillRoundRect(1255,45,305,105,10,10);
		windowTemp.fillRoundRect(1255,345,300,70,10,10);
		windowTemp.fillRoundRect(1255,445,300,70,10,10);
	windowTemp.setColor(Color.GRAY);
		windowTemp.fillRoundRect(1300,250,70,70,10,10);
		windowTemp.fillRoundRect(1380,170,70,70,10,10);
		windowTemp.fillRoundRect(1460,250,70,70,10,10);
		windowTemp.fillRoundRect(1260,50,310,110,10,10);
		windowTemp.fillRoundRect(1260,350,305,75,10,10);
		windowTemp.fillRoundRect(1260,450,305,75,10,10);
	windowTemp.setColor(C1);
	windowTemp.setFont(font);
		windowTemp.drawString("Fast",1305,280);
		windowTemp.drawString("Jump",1385,200);
		windowTemp.drawString("Slow",1465,280);
		windowTemp.drawString("Use Arrow Keys to Play",1280,120);
		windowTemp.drawString("By Joshua Howland",1280,150);
		windowTemp.setFont(font1);
		windowTemp.drawString("Chicken Runner",1270,80);
		windowTemp.drawString("Click Here To Play",1270,400);
			if(!(time<10))score = time;
		windowTemp.drawString("Score: "+score,1270,500);
    	window.drawImage(buffered, 0, 0, null);       
    }//end paint

public void animate()
	{
		if(jump==true)
			{
				personY = personY - (int)(15*Math.sin(personTheta+3.14/2));
				personTheta+=.08;
				if(personTheta>7)
					personY+=3;
				if(personY>560)
					{jump=false;personTheta=0;personY =570;}
			}
		if(jump==false)
			{
				for(int i=0;i<30;i++){
					if(buildingX[i]<840&&(buildingX[i]+200)>770)
						{temp = true;}
					}	
					if(temp == false&&time>100)
						{Fall=true;} 
					temp = false;
			}
		if(Fall==true)
			{
			personY=personY+10;incriment=5;time=0;
			}
		for(int i = 0; i<myClouds.length;i++)
			{
				if(CloudPositionX[i]>1700)
					CloudPositionX[i] = -200 + (int)(100*Math.random());
			CloudPositionX[i]++;
			myClouds[i].changePosition(CloudPositionX[i],CloudPositionY[i]);
			}
			displacement=displacement+incriment;
			if(time>100&&drawPerson==true&&time%150==0)	
				incriment++;
			if(time%1500==0)
				Flytime=0;
		for(int i=0;i<icicleNums;i++)
			{
			icicleX[i]+=(incriment+1);
			icicle[i].updateLocation(icicleX[i],icicleY[i]);
			if(icicleX[i]>1600)
				{icicleX[i] = -200 + (int)(100*Math.random());icicleY[i] = 680+(int)(100*Math.random());}
			}
		for(int i=0;i<rockNums;i++)
			{
			rocksXY[1][i]+=(incriment+1);
			rocks[i].updateLocation(rocksXY[1][i],rocksXY[2][i]);
			if(rocksXY[1][i]>1600)
				{rocksXY[1][i] = -200 + (int)(100*Math.random());rocksXY[2][i] = 680+(int)(20*Math.random());}
			}
	try{Thread.sleep(25);}catch(InterruptedException ex) {Thread.currentThread().interrupt();}
	repaint();
	}


    public void paintBuildingsFront(Graphics window, int xIn, int yIn)
	{
	x = xIn; y = yIn;
	for(int i=0;i<30;i++)dx[i] = i*(700-x)/(140);
	for(int i = 0;i<30;i++)dy[i] = i;x++;
	window.setColor(Color.WHITE);for(int i = 0;i<30;i++){window.drawRect((x+dx[i]),(y-dy[i]),200,100);}
	window.setColor(Color.GRAY);
	window.fillRect((x+dx[0]),(y-dy[0]),200,100);
	}
    public void paintPlane(Graphics window, int xIn, int yIn)
	{
	int z =100;int y = 100;x = xIn; y = yIn;
	for(int i=0;i<30;i++)dx[i] = i*(800-x)/(140);
	for(int i = 0;i<30;i++)dy[i] = i;
	x++;window.setColor(Color.WHITE);
	for(int i = 0;i<30;i++){y+=10;z+=10;window.drawOval((x+dx[i]),(y-dy[i]),z,y);}
	}
 public void keyTyped(KeyEvent e) {}

    public void keyPressed(KeyEvent e) {
			if (e.getKeyCode() == KeyEvent.VK_RIGHT)
				{displacement-=10;}
			if (e.getKeyCode() == KeyEvent.VK_LEFT)
				{displacement+=10;}
			if (e.getKeyCode() == KeyEvent.VK_UP) 
				{if(Fall==false)jump = true;if(personY>250)if(Flytime<100)personTheta-=.2;Flytime++;System.out.println(Flytime);}
			if (e.getKeyCode() == KeyEvent.VK_DOWN)
				{}
			 }
    public void keyReleased(KeyEvent e) {}       

	public void mouseClicked(MouseEvent e)
        {
        	int x = e.getX();int y = e.getY();
		if(x>1260&&x<1560&&y>350&&y<425)
			{drawPerson = true;Fall=false;personY=570;time=0;incriment=5;Flytime=0;}
	}  
	    public void mouseEntered(MouseEvent e) {}
	    public void mouseExited(MouseEvent e){}
	    public void mousePressed(MouseEvent e){}
	    public void mouseReleased(MouseEvent e) {}
}