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

public class drawContent extends JPanel implements MouseMotionListener {


////////////////////////////////////////////////////////////////////Variables

	private Image imgtable = Toolkit.getDefaultToolkit().getImage(getClass().getResource("images/tableImage.jpg"));
	private Image finger = Toolkit.getDefaultToolkit().getImage(getClass().getResource("images/finger.png"));
	private Image cardBack = Toolkit.getDefaultToolkit().getImage(getClass().getResource("images/cardBack.png"));
	private Image tround = Toolkit.getDefaultToolkit().getImage(getClass().getResource("images/tround.png"));
	private Image deckImage = Toolkit.getDefaultToolkit().getImage(getClass().getResource("images/deck.png"));
	private Image dealerGirl = Toolkit.getDefaultToolkit().getImage(getClass().getResource("images/dealerGirl.png"));
	private Image girl = Toolkit.getDefaultToolkit().getImage(getClass().getResource("images/girl.png"));	
	private Image lose = Toolkit.getDefaultToolkit().getImage(getClass().getResource("images/you-lose.png"));
	private Image button1 = Toolkit.getDefaultToolkit().getImage(getClass().getResource("images/button.png"));	
	private Image button2 = Toolkit.getDefaultToolkit().getImage(getClass().getResource("images/button2.png"));	
	private Image money1 = Toolkit.getDefaultToolkit().getImage(getClass().getResource("images/money1.png"));
	private Image money2 = Toolkit.getDefaultToolkit().getImage(getClass().getResource("images/money2.png"));
	private Image money3 = Toolkit.getDefaultToolkit().getImage(getClass().getResource("images/money3.png"));
	private double gmove = 0;	

	private ArrayList<card> deck = new ArrayList<card>(); 
	private ArrayList<card> myHand = new ArrayList<card>(); 
	private ArrayList<card> dealerCards = new ArrayList<card>(); 
	private String[] suits = {"hearts","diamonds","spades","clovers"};

	private int score = 0;
	private int dealerScore = 0;	
	private boolean dealerPlay = false;
	private boolean dealerCardHide = false;
	private boolean dealerWin = true;
		private int wager = 1;
		private boolean addWager = true;  
		private boolean youLose = false;
	private int money = 100;
	private int dMoney = 0;
	public int tokens = 1;
	private boolean Payout = false;
		
	private boolean fireworks = false;
	int flareAmount = 7;
	particle FlareA[] = new particle[flareAmount];
		int particleCenterX = 0;
		int particleCenterY = 0;			
	
	Color PearCider = new Color(208,155,121);
	Color gray1 = new Color(8,8,8);
	Color green1 = new Color(40,100,10);
	GradientPaint gradient1 = new GradientPaint(0,-200,Color.BLACK,0,350,green1,true);
	Font font3 = new Font("Arial", Font.PLAIN, 20);
	Font font2 = new Font("Arial", Font.PLAIN, 30);
	Font font1 = new Font("Arial", Font.PLAIN, 40);
	Font font = new Font("Arial", Font.PLAIN, 50);

////////////////////////////////////////////////////////////////////Constructor
 
   public drawContent() {
        setPreferredSize(new Dimension(1000,900));
		for(int i=0;i<flareAmount-1;i++)
			{FlareA[i]= new particle();}	
		resetCards();	
		addMouseMotionListener(this);
    }
//////////////////////////////////////////////////////////////////Paint Method

    @Override 
public void paintComponent(Graphics window) {
        super.paintComponent(window); 

		Image bufferedImage = createImage(getWidth(),getHeight());
		Graphics2D g2d = (Graphics2D) bufferedImage.getGraphics();
		g2d.drawImage(imgtable, 0,0, getWidth()+5,getHeight(), null);
	int tsize = myHand.size();
		g2d.drawImage(tround, 200,450, 800,400, null);
	g2d.drawImage(deckImage, 750,50, 200,200, null);
													//card draw section
		 		int x = 400;
				int y = 545;
					for(card each : myHand ) //card draw
						{each.drawMe(g2d, x, y);
						x += 110;}
					x=400;
					y=245;
				
	g2d.drawImage(dealerGirl,450,40,150,200,null);
					g2d.drawImage(tround, 260,210, 600,300, null);

				if(dealerPlay==true)
					{
						for(int i=0;i<dealerCards.size();i++ ) //card draw
							{dealerCards.get(i).drawMe(g2d, x, y);
							x += 110;}
						if(dealerCardHide==true&&dealerPlay==true)
							{g2d.setColor(Color.WHITE);
							g2d.fillRect(x-110,y,100,150);
							g2d.drawImage(cardBack, x-110,y,100,150, null);}
					g2d.setColor(Color.YELLOW);
					g2d.drawImage(button1, 5,320, 280,180, null);
					g2d.setFont(font2);	
					g2d.drawString("Dealer Score: " + dealerScore,25,420);
					g2d.drawImage(button1, 5,420, 280,180, null);
					g2d.setFont(font1);
					g2d.drawString("Wager: " + wager,25,520);
					}
			
			g2d.setColor(Color.YELLOW);
			g2d.setFont(font2);
			g2d.drawImage(button1, 5,15, 300,180, null);
			g2d.drawString("BLACKJACK V2",20,115);
			g2d.drawImage(button1, 5,220, 280,180, null);
			g2d.setFont(font1);
				g2d.drawString("Money: " + money,25,320); //draws money
					if(!(dMoney ==0))
						g2d.drawString("+ "+dMoney,150,220);
			g2d.drawImage(button1, 5,120, 280,180, null);
			g2d.drawString(score+"",150,220);	
			g2d.setFont(font1); 
			g2d.drawString("Score:",25,220);



		if(fireworks == true)
			{int count = 1;
				for(int i=0;i<flareAmount-1;i++)
					{for(int n=0;n<=count;n++)FlareA[i].flare(g2d);
					count++;}
					g2d.drawImage(girl, 400+(int)(200*Math.sin(gmove)),580, 200,400, null);gmove+=.025;
					g2d.drawImage(money1, 400+(int)(200*Math.cos(gmove)),580, 200,400, null);
					g2d.drawImage(money2, 400+(int)(200*Math.tan(gmove)),580, 200,400, null);
					g2d.drawImage(money3, 400+(int)(180*Math.sin(gmove)),730, 150,300, null);
			}
		if(youLose == true && dealerPlay==true)
			{g2d.drawImage(lose, 400+(int)(300*Math.cos(gmove)), 400+(int)(400*Math.sin(gmove)),300,150,null);
			gmove+=.025;
			}

g2d.drawImage(finger,particleCenterX-25,particleCenterY-20,60,60, null); // draw mouse
		window.drawImage(bufferedImage, 0, 0, this);
updateScore();
		animate();
    }




////////////////////////////////////////////////////////////////////  BLACKJACK METHODS

	public void resetCards()
		{	
score=0;money--;Payout=false;fireworks = false;dMoney=0;youLose=false;
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
			dealerCards.clear();
			shuffle();
		addCardToTable();
		addCardToTable();
		if(dealerPlay==true)
			{addDealerCardToTable();addDealerCardToTable();dealerCardHide=true;}
		}

	public void shuffle()  //note  -program should be random, but oftentimes is not.  Adding shuffle as a back up
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
		int rc= (int)(deck.size()*Math.random());
		dealerCards.add(deck.get(rc));
		deck.remove(rc);
		updateScore();
		}
		
	public int getTokens()
		{
		return tokens;
		}

	public void addCardToTable()
		{
		int rc= (int)(deck.size()*Math.random());
		myHand.add(deck.get(rc));
		deck.remove(rc);
		}
	
	public void finalizeScore()
		{ 	
			if(dealerPlay == false)
					{
					Payout=true;
					if(score<19&&score>15){money++;fireworks = true;dMoney=1;}
					else if(score==19){money+=2;fireworks = true;dMoney=2;}
					else if(score==20){money+=3;fireworks = true;dMoney=3;tokens++;}
					else if(score==21){money+=5;fireworks = true;dMoney=5;tokens++;}
					}
			else 	
				{	
				dealerCardHide=false;
				updateScore();
					if(dealerScore<17)
						{addDealerCardToTable();}
					if(dealerScore<17)
						{addDealerCardToTable();}
				if(score>21)
					{dealerWin = true; fireworks = false; youLose=true;					
						//if(addWager == true){
						money= money-wager;addWager=false;
						//}
					}
				else if(score<22&&(score>dealerScore||dealerScore>21))
					{dealerWin = false; fireworks = true;youLose=false;tokens++;
						//if(addWager == true)
							{money+=wager;addWager=false;}}
				else	
					{dealerWin = true; fireworks = false;youLose=true;}
				} 
		}	
		
	public void finalizeScore(int cone)
		{ 	
			if(dealerPlay == false)
					{
					Payout=true;
					if(score<19&&score>15){money++;fireworks = true;dMoney=1;}
					else if(score==19){money+=2;fireworks = true;dMoney=2;}
					else if(score==20){money+=3;fireworks = true;dMoney=3;}
					else if(score==21){money+=5;fireworks = true;dMoney=5;}
					}
			else 	
				{	
				dealerCardHide=false;
				updateScore();
					if(dealerScore<17)
						{addDealerCardToTable();}
					if(dealerScore<17)
						{addDealerCardToTable();}
				if(score>21)
					{dealerWin = true; fireworks = false; youLose=true;					
					}
				else if(score<22&&(score>dealerScore||dealerScore>21))
					{dealerWin = false; fireworks = true;youLose=false;
							{}}
				else	
					{dealerWin = true; fireworks = false;youLose=true;}
				} 
		}	



	public void updateScore()
		{score = 0;
		for(card each: myHand)
			{
			if(each.getValue()==11 && score>10)
				score += 1;
			else 
				score+= each.getValue();
			}
		if(dealerPlay==true)
			{if(dealerCardHide==true)
				dealerScore = dealerCards.get(0).getValue();
			else{
				dealerScore = 0;
				for(card each: dealerCards)
					{dealerScore += each.getValue();}}
			}
		
		}

	public int returnScore()
		{return score;}
	
	public int returnMoney()
		{return money;}

	public void updateMoney(int moneyIn)
		{money = moneyIn;}
	
	public void animate()
		{try{Thread.sleep(10);}catch(InterruptedException ex) {Thread.currentThread().interrupt();}
			repaint();}  

	public void playDealer()
		{
		if(dealerPlay == false)
			dealerPlay = true;
		dealerCardHide= true;
		resetCards();
			try{
			wager = Integer.parseInt(JOptionPane.showInputDialog("Enter Your Wager Value.  Must be between 1 and 20"));}
		catch(Exception e){System.out.println("YOU DID NOT ENTER A INTEGER");}

		}

	public void stopPlayDealer()
		{
		dealerPlay = false;
		resetCards();
		}


	public void mouseDragged(MouseEvent e){}
	public void mouseMoved(MouseEvent e)
		{particleCenterX = e.getX();
		particleCenterY = e.getY();}
}