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

public class instructions extends drawContent {

private boolean showInstructions = true;
public drawContent tempObject;

	public instructions(drawContent tempObjectIn)
		{
		tempObject = tempObjectIn;
		setPreferredSize(new Dimension(300,900));
		}
		
   @Override 
public void paintComponent(Graphics window) {
        super.paintComponent(window); 

	Image bufferedImage = createImage(getWidth(),getHeight());
		Graphics2D g2d = (Graphics2D) bufferedImage.getGraphics();
		
			g2d.setPaint(gradient1);
			g2d.fillRect(0,0,300,1000);
		
		if(showInstructions==true)
			{
				g2d.setColor(Color.YELLOW);
				g2d.setFont(font1);
				g2d.drawString("Instructions:",10,150);
				g2d.setFont(font3);
				g2d.drawString("To Play, Click New Game",10,200);
				g2d.drawString("Each game costs $1",10,230);
				g2d.drawString("Hit adds a card to your hand",10,260);
				g2d.drawString("Stand stops the game and",10,290);
				g2d.drawString("calculates each persons score",10,320);
				g2d.drawString("To play dealer, click Play Dealer",10,350);
				g2d.drawString("Enter your desired wager",10,380);
				g2d.drawString("Every time you score 20 or 21",10,470);
				g2d.drawString("you get one arcade token",10,500);
				g2d.drawString("Arcade Tokens can be redeemed",10,530);
				g2d.drawString("By closing instructions and clicking",10,560);
				g2d.drawString("Play Arcade Game",10,590);
			}
		else 
			{
				g2d.setColor(Color.YELLOW);
				g2d.setFont(font1);
				g2d.drawString("Arcade Game:",10,250);
				g2d.setFont(font3);
				g2d.drawString("Select the game you would like",10,320);
				g2d.drawString("Click Play to Play.",10,350);
				g2d.drawString("Hold and Drag the Mouse",10,380);
				g2d.drawString("to move in Cube Runner",10,410);
				g2d.drawString("Press the mouse to sprint",10,440);
				g2d.drawString("in Spiral Game, and click",10,500);
				g2d.drawString("to Jump",10,530);
				g2d.drawString("Each game costs one token",10,560);
				g2d.drawString("",10,590);
			}
	g2d.setColor(Color.YELLOW);
	g2d.setFont(font1);
	g2d.drawString("Tokens: " + tempObject.tokens,10,700);
	window.drawImage(bufferedImage, 0, 0, this);
}

public void hideInstructions()
	{
	showInstructions = false;
	repaint();
	}
public void showInstructions()
	{
	showInstructions = true;
	repaint();
	}

}