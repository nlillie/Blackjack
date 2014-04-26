
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


public class game
{
    	static BufferedImage buffered;
    	
    	static int x = 400;
    	static int y = 500;
	static int[] dx = new int[30];
	static int[] dy = new int[30];
	static int displacement = -400;
   	static int time = 0;
	static int[] buildingX = new int[30];
	static int personY = 570;
	static boolean jump = false;
	static double personTheta = 0;
	static boolean Fall = false;
	static boolean drawPerson = false;
	static boolean temp = false;
	static int incriment = 5;
	static int score = 0;
	static Color C1 = new Color(255,185,15);
	static Font font = new Font("Arial", Font.PLAIN, 25);
	static Font font1 = new Font("Arial", Font.PLAIN, 35);
	
    

    public static void paintGame(Graphics windowTemp)
    {
	animateGame();
    	//Draw background
    	windowTemp.setColor(Color.BLACK);
    	windowTemp.fillRect(0,0,1600,800);
  
	for(int i = 0;i<18;i++)
		{paintBuildingsFront(windowTemp, 600,(-1200+ displacement + 400*i));buildingX[i]=-1200 + displacement + 400*i;}

	if(displacement>1200||displacement<-490)
		displacement = -400;
	if(drawPerson==true)
		{windowTemp.setColor(C1);
			//ball.drawMe(windowTemp,780,personY-50);
			time++;}
	windowTemp.drawString("GAME PLACEHOLDER",200,200);
      
    }//end paint

public static void animateGame()
	{
			displacement=displacement+incriment;	
	}


    public static void paintBuildingsFront(Graphics window, int xIn, int yIn)
	{
	x = xIn; y = yIn;
	for(int i=0;i<30;i++)dx[i] = i*(700-x)/(140);
	for(int i = 0;i<30;i++)dy[i] = i;x++;	
	window.setColor(Color.WHITE);for(int i = 0;i<30;i++){window.drawRect((x+dx[i]),(y-dy[i]),200,100);}
	window.setColor(Color.GRAY);
	window.fillRect((x+dx[0]),(y-dy[0]),200,100);
	}
}













