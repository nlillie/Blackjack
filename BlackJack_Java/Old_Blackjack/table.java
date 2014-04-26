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

public class table extends JFrame implements MouseListener, MouseMotionListener
	{
	Toolkit toolkit = Toolkit.getDefaultToolkit();
	Dimension dim = toolkit.getScreenSize();//check to verify nescesary
	private Image imgtable = Toolkit.getDefaultToolkit().getImage(getClass().getResource("images/tableImage.png"));
	private Image finger = Toolkit.getDefaultToolkit().getImage(getClass().getResource("images/finger.png"));
	private Image cardBack = Toolkit.getDefaultToolkit().getImage(getClass().getResource("images/cardBack.jpg"));
	
	private ArrayList<card> deck = new ArrayList<card>(); 
	private ArrayList<card> myHand = new ArrayList<card>(); 
	private ArrayList<card> dealerCards = new ArrayList<card>(); 
	private String[] suits = {"hearts","diamonds","spades","clovers"};
	private boolean addCardsInitial = true;

	private int score = 0;
	private int dealerScore = 0;
		private boolean dealerPlay = false;
		private boolean setDealerInitial = false;
		private boolean dealerHideCard = true;
		private boolean startDealerPlay = false;
		private boolean dealerWin = false;
		private boolean dealerPlayDone = false;
		private int wager = 1;
		private boolean addWager = true;  
	private int money = 100;
	private int dMoney = 0;
	private boolean incrimentMoney = false;
	private boolean Payout = false;
	boolean showStandPlay = true;

	boolean showDeck = false;
	
	fileReader file;
		
	private boolean fireworks = false;
	int flareAmount = 7;
	particle FlareA[] = new particle[flareAmount];
	particle Particle = new particle();
		int particleCenterX = 0;
		int particleCenterY = 0;			
		int width = 1200;
		int height = 800;
	private int time = 0;
	
	Color PearCider = new Color(208,155,121);
	Font font2 = new Font("Arial", Font.PLAIN, 24);
	Font font1 = new Font("Arial", Font.PLAIN, 40);
	Font font = new Font("Arial", Font.PLAIN, 50);


	JMenuBar menuBar = new JMenuBar();

	public table()
	{
		super("BlackJack");
			addMouseListener(this);
			addMouseMotionListener(this);
		resetCards();setSize(1400,800);
		setVisible(true);setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		for(int i=0;i<flareAmount-1;i++)
			{FlareA[i]= new particle();}	
		shuffle();		
		
		try{file = new fileReader();money = file.returnMoney();}
			catch(Exception e){}
		
	}
	public void paint(Graphics window)
	{
		Image bufferedImage = createImage(getWidth(),getHeight());
		Graphics2D g2d = (Graphics2D) bufferedImage.getGraphics();

	
		g2d.setColor(Color.black);

		g2d.fillRect(0,0,getWidth(),getHeight());
		g2d.drawImage(imgtable, 0,0, getWidth()+5,getHeight(), null);
		
		if(fireworks == true)
			{int count = 1;
				for(int i=0;i<flareAmount-1;i++)
					{for(int n=0;n<=count;n++)FlareA[i].flare(g2d);
					count++;}
			}

				Particle.paintParticle(g2d,particleCenterX,particleCenterY);
		 			int x = (int)(getWidth()/2.5);
					int y = (int)(getHeight()*1.9/3);
			for(card each : myHand ) //card draw
				{each.drawMe(g2d, x, y);
				x += 110;}


			//dealer button draw section
				g2d.setColor(Color.BLACK);
				g2d.fillRoundRect((int)(getWidth()/3.162),0,(int)(getWidth()/2.7),getHeight()/8,20,20);
					g2d.setColor(PearCider);
					if(dealerPlay == false)
						g2d.drawString("Play Dealer",(int)(getWidth()/3.1),(int)(getHeight()/10));
					else
	
						g2d.drawString("Stop Playing Dealer",(int)(getWidth()/3.1),(int)(getHeight()/10));
				//wager button section draw
					if(dealerPlay==true)
						{g2d.setColor(Color.BLACK);
						g2d.fillRoundRect(0,getHeight()*7/8,getWidth()/5,200,30,30);
						g2d.setColor(PearCider);
						g2d.drawString("Set Wager",getHeight()/100,getHeight()*13/14);
							g2d.setFont(font2);
						for(int i=0;i<12;i+=2)
							g2d.drawString("$"+i,getHeight()*6*(i)/200,getHeight()*48/49);}
			//dealer card draw section

			if(dealerPlay == true)
				{
				if(fireworks == true) 
					g2d.drawString("You Won", getWidth()/3,getHeight()/2);

					g2d.setColor(PearCider);
					g2d.setFont(font1);
					g2d.drawString("Dealer Score: " + dealerScore,5,(int)(getHeight()/5));
					g2d.drawString("Current Wager: " + wager,5,(int)(getHeight()/3.6));

					if(setDealerInitial == true)
						{addDealerCardToTable();
						addDealerCardToTable();
						setDealerInitial = false;}	
				int x1 = (int)(getWidth()/2.5);
				int y1 = (int)(getHeight()/5);
				for(card each : dealerCards ) //card draw
					{each.drawMe(g2d, x1, y1);
					if(x1>(int)(getWidth()/2.5)&&(dealerHideCard==true))
						g2d.drawImage(cardBack,x1,y1,100,150, null);
					x1 += 110;
				 	}
				if(startDealerPlay == true ||score>20)
					{
					dealerHideCard = false;updateScore(); 
					if(dealerScore<17/*&&(time%20 == 0)*/)
						{addDealerCardToTable();updateScore();}
					else
						{dealerPlayDone = true;finalizeScore();updateScore();}
					}


				//g2d.setColor(Color.YELLOW);	
				if(dealerPlayDone == true)
					{
					g2d.setColor(Color.BLACK);
					g2d.fillRoundRect((int)(getWidth()/3.3),(int)(getHeight()/2.3),350,100,20,20);
					g2d.setColor(Color.YELLOW);
					if(dealerWin == true && dealerPlayDone == true)
							{g2d.drawString("Dealer Won", getWidth()/3,(int)(getHeight()/2));}
					else 
							g2d.drawString("You Won", getWidth()/3,getHeight()/2);
					}
				}


			if(addCardsInitial == true)
				{addCardToTable();addCardToTable();//adds cards intitially
				addCardsInitial=false;}
			g2d.setColor(PearCider);
			g2d.setFont(font);
				g2d.drawString("$ " + money, getWidth()*8/9,getHeight()/3+getHeight()/10); //draws money
					if(!(dMoney ==0))
						g2d.drawString("+ "+dMoney,getWidth()*8/9,getHeight()/3+getHeight()/5);
			g2d.drawString("Play Again",(int)(getWidth()/2),getHeight()*23/24+10);
						g2d.shear(0.3,0);
						g2d.drawString(score+"",100,90);	
						g2d.shear(-.3,0);
			if(showStandPlay==true)
				{  //stand button draw
				g2d.setColor(Color.black);
					g2d.fillRoundRect((int)(getWidth()/1.44),getHeight()*9/10,200,200,40,40);
					g2d.setColor(PearCider);
						g2d.drawString("Stand",(int)(getWidth()/1.4),getHeight()*23/24+10);
				g2d.drawString("Hit",(int)(getWidth()/2.85),getHeight()*23/24+10);
				}
			
			g2d.setFont(font1);
			g2d.drawString("Score:",5,90);
			
		if(showDeck == true)
			{int x3=0;
			int y3=0;
			for(card oe: deck)
				{oe.drawMe(g2d,x3,y3);
				x3+=80;
				if(x3>1200)
					{
					x3=0;
					y3+=160;
					}
				}
			}
		g2d.drawImage(finger,particleCenterX-25,particleCenterY-20,60,60, null); // draw mouse
		window.drawImage(bufferedImage, 0, 0, this);
		animate();
		}


	public void resetCards()
		{	
score=0;addCardsInitial=true;money--;Payout=false;fireworks = false;dMoney=0;showStandPlay = true;incrimentMoney = false;
deck.clear();
			for(int i=0;i<4;i++)
				{deck.add(new card(2, "two", suits[i]));deck.add(new card(3, "three", suits[i]));
				deck.add(new card(4, "four", suits[i]));deck.add(new card(5, "five", suits[i]));
				deck.add(new card(6, "six", suits[i]));deck.add(new card(7, "seven", suits[i]));
				deck.add(new card(8, "eight", suits[i]));deck.add(new card(9, "nine", suits[i]));
				deck.add(new card(10, "ten", suits[i]));deck.add(new card(10, "jack", suits[i]));
				deck.add(new card(10, "queen", suits[i]));deck.add(new card(10, "king", suits[i]));
				deck.add(new card(11, "ace", suits[i]));}
			myHand.clear();
			dealerCards.clear();}
	public void shuffle()
		{
		for(int i=0;i<100;i++)
			{int t1 = (int)(deck.size()*Math.random());
			int t2 = (int)(deck.size()*Math.random());
			card temp1 = deck.get(t1);
			card temp2 = deck.get(t2);
			deck.remove(t1);
			deck.add(t1,temp2);
			deck.remove(t2);
			deck.add(t2,temp1);}
		}

	public void addDealerCardToTable()
		{
		dealerCards.add(deck.get(0));
		deck.remove(0);
		updateScore();
		}

	public void addCardToTable()
		{
		myHand.add(deck.get(0));
		deck.remove(0);
		updateScore();}
	
	public void finalizeScore()
		{
			if(dealerPlay == false)
					{dealMoney();}
			else 	
				{
				if(score>21)
					{dealerWin = true; fireworks = false; 										
						if(addWager == true){money= money-wager;addWager=false;}}
				else if(score<22&&(score>dealerScore||dealerScore>21))
					{dealerWin = false; fireworks = true;
						if(addWager == true)
							{money+=wager;addWager=false;}}
				else	
					{dealerWin = true; fireworks = false;}
				}
		}	

	public void dealMoney()
		{
			Payout=true;
				if(score<19&&score>15&&incrimentMoney==false){money++;fireworks = true;dMoney=1;incrimentMoney = true;}
				else if(score==19&&incrimentMoney==false){money+=2;fireworks = true;dMoney=2;incrimentMoney = true;}
				else if(score==20&&incrimentMoney==false){money+=3;fireworks = true;dMoney=3;incrimentMoney = true;}
				else if(score==21&&incrimentMoney==false){money+=5;fireworks = true;dMoney=5;incrimentMoney = true;}
		}

	public void updateScore()
		{score = 0;
		for(card each: myHand)
			score += each.getValue();
		if(dealerPlay == true)
			{dealerScore = 0;
			if(dealerHideCard==true)
				dealerScore = dealerCards.get(0).getValue();
			else
				for(card each: dealerCards)
					dealerScore += each.getValue();}
		if(score>21)
				showStandPlay = false;
				
			try{file.updateFile(money);}
			catch(Exception e){}
			
		}
	
	public void animate()
		{time++;try{Thread.sleep(10);}catch(InterruptedException ex) {Thread.currentThread().interrupt();}
			repaint();}     

	public void mouseClicked(MouseEvent e)
        {
        	int x = e.getX();int y = e.getY();

			if(x>(int)(getWidth()/3.2) && x<(int)(getWidth()/2)&&(y>getHeight()*9/10)&&score<21&&(showStandPlay==true)) //hit
				addCardToTable();
			else if(x>(int)(getWidth()/2) && x<(int)(getWidth()/1.5)&&(y>getHeight()*9/10)) 			//play again
				{resetCards();
				if(dealerPlay == true)
					setDealerInitial = true;
					dealerHideCard = true;
					startDealerPlay = false;
					dealerPlayDone = false;
					addWager=true;
					shuffle();
					}
			else if(x>(int)(getWidth()/1.45) && x<(int)(getWidth()/1.18)&&(y>getHeight()*9/10)&&(showStandPlay==true)) //stand
				{ showStandPlay = false;
				if((dealerPlay == true)&&(startDealerPlay == false))
					{startDealerPlay = true;}
				else
					finalizeScore();
				}
			else if(x>(int)(getWidth()/3.162)&&x<(int)(getWidth()/3.162 + getWidth()/2.7)&&y>(0)&&y<(getHeight()/8)&&dealerPlay==false) //dealer play
				{resetCards();
				dealerPlay = true;
				setDealerInitial=true;
				}
			else if(x>(int)(getWidth()/3.162)&&x<(int)(getWidth()/3.162 + getWidth()/2.7)&&y>(0)&&y<(getHeight()/8)&&dealerPlay==true) //dealer away
				{dealerPlay = false;
				setDealerInitial=false;}
			else if(x>0&&x<getWidth()/5&&y>getHeight()*7/8&&y<(getHeight()*7/8+getHeight()/5)&&dealerPlay==true) //wager area
				{
				wager=(10*x/(getWidth()/5)+1);}


			if(getWidth()>1600&&(x>1200)&&(y>700))
				{showDeck = true;money=100;}
			else
				showDeck = false;		
	
			}
				
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e){}
	public void mousePressed(MouseEvent e){}
	public void mouseReleased(MouseEvent e) {}
	public void mouseDragged(MouseEvent e){}
	public void mouseMoved(MouseEvent e)
		{particleCenterX = e.getX();
		particleCenterY = e.getY();}

}
