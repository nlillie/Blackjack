import java.awt.Graphics;
import java.awt.Color;
import javax.swing.JFrame;
import java.awt.image.BufferedImage;
import java.awt.event.*;
import java.awt.Font;
import javax.swing.JOptionPane;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;
import java.awt.Image;
import javax.imageio.*;
import java.io.*;
import java.util.Arrays;
import java.io.IOException;
import java.awt.Toolkit;


 

public class machine extends JFrame implements MouseListener 
{
    private BufferedImage buffered;

	Toolkit toolkit = Toolkit.getDefaultToolkit();
	Dimension dim = toolkit.getScreenSize();
	
	//private Image img1;
	private Image img1 = Toolkit.getDefaultToolkit().getImage(getClass().getResource("images/person_front.png"));
	private Image img2 = Toolkit.getDefaultToolkit().getImage(getClass().getResource("images/person_back.png"));
	private Image front1 = Toolkit.getDefaultToolkit().getImage(getClass().getResource("images/front1.png"));
	private Image back1 = Toolkit.getDefaultToolkit().getImage(getClass().getResource("images/back1.png"));
	private Image front2 = Toolkit.getDefaultToolkit().getImage(getClass().getResource("images/front2.png"));
	private Image back2 = Toolkit.getDefaultToolkit().getImage(getClass().getResource("images/back2.png"));
	private Image front3 = Toolkit.getDefaultToolkit().getImage(getClass().getResource("images/front3.png"));
	private Image back3 = Toolkit.getDefaultToolkit().getImage(getClass().getResource("images/back3.png"));
	private Image front4 = Toolkit.getDefaultToolkit().getImage(getClass().getResource("images/front4.png"));
	private Image back4 = Toolkit.getDefaultToolkit().getImage(getClass().getResource("images/back4.png"));
	private Image imgB = Toolkit.getDefaultToolkit().getImage(getClass().getResource("images/casino.jpeg"));

fileReader Read = new fileReader();


    private number num1 = new number();
    private int[] numbers = new int[5];
    private int[] numbersTest = new int[5];
    private boolean setNumbers = false;
    private int[] numXLocation = new int[5];
    private int money = 100;
    private boolean checkNumbers = false;
    private boolean gameMenu = true;
    private boolean animate = false;
    private double scale = .05;
    private int translateX = 10000;
    private int translateY = 10000;
    private boolean checkCardsA = false;
    private int resultsDraw = 0;
    private int pairs = 0;
	private String[] users = new String[20];
	private int[] usersMoney = new int[20];
	private int currentUser;

	Color LakeBlue1 = new Color(0,104,139);
	Color MidnightNavy = new Color(10,22,46);
	Color Barn = new Color(112,24,23);
	Color PearCider = new Color(208,155,121);
	Color PersimmonRed = new Color(207,119,99);
	Color ChocolateCosmos = new Color(73,41,44);
	Color Gold = new Color(238,201,0);
GradientPaint gradient1 = new GradientPaint(0,500,MidnightNavy,0,0,Barn,true);
GradientPaint gradient2 = new GradientPaint(0,400,PearCider,0,800,Color.BLACK,true);
GradientPaint gradient3 = new GradientPaint(500,0,MidnightNavy,524,0,PersimmonRed,true);
Font font = new Font("Arial", Font.PLAIN, 45);
Font font1 = new Font("Arial", Font.PLAIN, 25);
Font font2 = new Font("Arial", Font.PLAIN, 1500);
Font font3 = new Font("Arial",Font.PLAIN,1000);
 	private int[] particleX = new int[100];
	private int[] particleY = new int[100];
	private int[] particleXdX = new int[100];
	private int[] particleYdY = new int[100];
	private int particleAmount = 80;
	private int particleCenterX = 350;
	private int particleCenterY = 600;
	private boolean drawParticle = false;
	private boolean redrawParticle = false;

	private int PersonNumbers = 60;
	private boolean setPersons = true;
	private int[] personNorth = new int[PersonNumbers];
	private int[] personX = new int[PersonNumbers];
	private int[] personY = new int[PersonNumbers];
	private int[] persondX = new int[PersonNumbers];
	private int[] persondY = new int[PersonNumbers];
	private int[] personType = new int[PersonNumbers];

    public machine() throws IOException
    {
        super("Number Poker");
        setSize(1000,800);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	addMouseListener(this);
       
	for(int i=0;i<5;i++)
		numXLocation[i] = (70*i) + 200;
	for(int i=0;i<20;i++)
		users[i] = "x";
	addUsers();
	
    }//end constructor
   


   public void setNum() 
	{
	if(setNumbers == true)
		{
		checkCardsA = true;
		for(int i = 0;i<5;i++)
			{
			numbers[i] = (int)(10*Math.random());
			numbersTest[i] = numbers[i];
			}
		int results = checkCards();
			if(results!=0)
				{
				drawParticle = true;
				}
			if(results == 0)
				{
					for(int i = 0;i<particleAmount;i++)
						{
						particleX[i] = (particleCenterX-20) + (int)(40*Math.random());
						particleY[i] = (particleCenterY-20) + (int)(40*Math.random());
						particleXdX[i] = -5+(int)(11*Math.random());
						particleYdY[i] = -5+(int)(11*Math.random());
						}
				System.out.println("No Match");drawParticle=false;
				}
			if(results == 1)
				{System.out.println("One Pair");money=money+1;}
			if(results == 2)
				{System.out.println("Two Pair's");money=money+2;}
			if(results == 3)
				{System.out.println("Three of a kind");money=money+3;}
			if(results == 777)
				{System.out.println("Jackpot");money=money+100;}
		}
	setNumbers = false;
	int i = 0;
	}	
public int checkCards()  
	{//checkcards open
		Arrays.sort(numbersTest);
			for(int i = 0; i<5;i++)
				System.out.print(numbersTest[i]);
		if(checkNumbers == true)	
			{///inner open
			//three of a kind check
				if((numbersTest[0] == numbersTest[2] )|| (numbersTest[1] == numbersTest[3]) ||(numbersTest[2] == numbersTest[4]))
					return 3; //3 is three of a kind
				pairs = 0;
				for(int T = 0;T<4;T++)
				{
				if(numbersTest[T] == numbersTest[T+1])
					{
					System.out.println(T);
					pairs++;
					}
					}
				if(pairs==1)
					return 1; //1 is one a pair
				if(pairs==2)
					return 2; //2 is two two pairs
				if(numbers[0]==numbers[1]&&numbers[1]==numbers[2]&&numbers[2]==numbers[3]&&numbers[3]==numbers[4])
					return 777; //777 is jackpot
			}//inner close
		checkNumbers = false;
		return 0;
	}//checkcards close	
///////////////
	public void addUsers() throws IOException
		{
		
		for(int i=0;i<20;i++)
			users[i] = Read.returnUsers("00"+i);
		for(int i=0;i<20;i++)
			usersMoney[i] = Read.returnUsersMoney("00"+i);
		}
	public void updateUsers(String UNInNumber, int newMoney) throws IOException
		{
		Read.updateUserMoney(UNInNumber, newMoney);
		for(int i=0;i<20;i++)
			users[i] = Read.returnUsers("00"+i);
		}
	public void addNewUser(String UserName) throws IOException
		{
		Read.addUser(UserName);
		}
	
    public void paint(Graphics window)
    {
    	//Create a buffered Image
		if(buffered==null)
 			buffered = (BufferedImage)(createImage(getWidth(),getHeight()));
	 //Create a temporary Graphics object from the buffered object
    		 Graphics windowTemp = buffered.createGraphics();
	//graphics 2d initialize  
		Graphics2D g2d = (Graphics2D)windowTemp;
		g2d.fillRect(0,0,2200,2000);
		Composite originalComposite = g2d.getComposite();
	//menu section
		if(gameMenu==true)
			{
			g2d.scale(scale,scale);
			g2d.translate(translateX, translateY);
			}
		if(animate==true)
			{		
			g2d.scale(scale,scale);
			g2d.translate(translateX, translateY);
			translateX = (int)(translateX*.9);
			translateY = (int)(translateY*.95);
			scale = scale*1.005;
			if(scale>.060)
				{
				scale= scale*Math.pow(1.2,scale);
				translateX = translateX+10;
				translateY = translateY+10;
				}
			if(scale>=5)
				animate = false;
			}

	//gradient paint and wall/floor draw
		g2d.setPaint(gradient1);
		g2d.fillRect(0,0,1000,800);
		g2d.setPaint(gradient2);
		g2d.fillRect(0,400,1000,800);
		g2d.setPaint(gradient2);
		g2d.fillRect(0,0,1000,130); 
	//first row person draw
		animatePersons();
		for(int i = 0; i<(PersonNumbers/2);i++)
			{
			if(personNorth[i] == 1)
				{
				if(personType[i] == 1)
					g2d.drawImage(img2, personX[i], personY[i], 100,100, null);
				if(personType[i] == 2)
					g2d.drawImage(back1, personX[i], personY[i], 100,100, null);
				if(personType[i] == 3)
					g2d.drawImage(back2, personX[i], personY[i], 100,100, null);
				if(personType[i] == 4)
					g2d.drawImage(back3, personX[i], personY[i], 100,100, null);
				if(personType[i] == 5)
					g2d.drawImage(back4, personX[i], personY[i], 100,100, null);
				}
			else if(personNorth[i] == 0)
				{
				if(personType[i] == 1)
					g2d.drawImage(img1, personX[i], personY[i], 100,100, null);
				if(personType[i] == 2)
					g2d.drawImage(front1, personX[i], personY[i], 100,100, null);
				if(personType[i] == 3)
					g2d.drawImage(front2, personX[i], personY[i], 100,100, null);
				if(personType[i] == 4)
					g2d.drawImage(front3, personX[i], personY[i], 100,100, null);
				if(personType[i] == 5)
					g2d.drawImage(front4, personX[i], personY[i], 100,100, null);
				}
			}
	//columns draw
		g2d.setPaint(gradient3);
		g2d.fillRect(50,95,50,360); g2d.fillOval(50,440,50,25); g2d.fillOval(50,85,50,25);
		g2d.fillRect(250,95,50,360); g2d.fillOval(250,440,50,25); g2d.fillOval(250,85,50,25);
		g2d.fillRect(450,95,50,360); g2d.fillOval(450,440,50,25); g2d.fillOval(450,85,50,25);
		g2d.fillRect(650,80,50,370); g2d.fillOval(650,440,50,25); g2d.fillOval(650,70,50,25);
		g2d.fillRect(850,95,50,360); g2d.fillOval(850,440,50,25); g2d.fillOval(850,85,50,25);
	//lower persons draw
		for(int i = (PersonNumbers/2); i<PersonNumbers-10;i++)
			{
			if(personNorth[i] == 1)
				{
				if(personType[i] == 1)
					g2d.drawImage(img2, personX[i], personY[i], 150,150, null);
				if(personType[i] == 2)
					g2d.drawImage(back1, personX[i], personY[i], 150,150, null);
				if(personType[i] == 3)
					g2d.drawImage(back2, personX[i], personY[i], 150,150, null);
				if(personType[i] == 4)
					g2d.drawImage(back3, personX[i], personY[i], 150,150, null);
				if(personType[i] == 5)
					g2d.drawImage(back4, personX[i], personY[i], 150,150, null);
				}
			else if(personNorth[i] == 0)
				{
				if(personType[i] == 1)
					g2d.drawImage(img1, personX[i], personY[i], 150,150, null);
				if(personType[i] == 2)
					g2d.drawImage(front1, personX[i], personY[i], 150,150, null);
				if(personType[i] == 3)
					g2d.drawImage(front2, personX[i], personY[i], 150,150, null);
				if(personType[i] == 4)
					g2d.drawImage(front3, personX[i], personY[i], 150,150, null);
				if(personType[i] == 5)
					g2d.drawImage(front4, personX[i], personY[i], 150,150, null);
				}
			}
	//font creation
		
		windowTemp.setFont(font);
		
	//slot machine draw      
		g2d.setPaint(Color.BLACK);
		g2d.fillRoundRect(130,660,440,200,10,10);
		g2d.setPaint(LakeBlue1);
		g2d.fillRoundRect(150,690,20,200,10,10);
		g2d.fillRoundRect(200,690,20,200,10,10);
		g2d.fillRoundRect(250,690,20,200,10,10);
		g2d.fillRoundRect(300,690,20,200,10,10);
		g2d.fillRoundRect(350,690,20,200,10,10);
		g2d.fillRoundRect(400,690,20,200,10,10);
		g2d.fillRoundRect(450,690,20,200,10,10);
		g2d.fillRoundRect(500,690,20,200,10,10);
		g2d.fillRoundRect(540,690,20,200,10,10);
		g2d.setPaint(gradient1);
		g2d.fillRoundRect(130,460,440,200,10,10);
		g2d.setPaint(Color.BLACK);
		g2d.fillRoundRect(230,360,340,100,15,15);
		g2d.setPaint(Gold);
		g2d.drawRoundRect(230,360,340,100,15,15);
		g2d.drawRoundRect(130,460,440,200,10,10);;
		g2d.drawString("SLOT MACHINE",230,410);
		windowTemp.setFont(font1);
		g2d.drawString("$1 Dollar Games",290,450);
		windowTemp.setFont(font);
		windowTemp.setColor(Color.YELLOW);
		windowTemp.fillRoundRect(150,530,400,100,30,30);
		g2d.drawImage(img2, -100, 600, 400,400, null);
	//display for reward type
	if(checkCardsA == true)
		resultsDraw = checkCards();
			checkCardsA = false;
			if(resultsDraw == 10)
				windowTemp.drawString("Full House",250,520);
			else if(resultsDraw == 3)
				windowTemp.drawString("Three of a Kind",200,520);
			else if(resultsDraw == 2)
				windowTemp.drawString("Two Pairs",250,520);
			else if(resultsDraw == 1)
				windowTemp.drawString("Pair",320,520);
			else if(resultsDraw == 777)
				windowTemp.drawString("Jackpot",250,520);
			

		if(drawParticle == true)
			{//xxxOpen		
			for(int i = 0;i<(particleAmount-1);i++)
				{
					if((particleX[i]<(particleCenterX-200))||(particleX[i]>(particleCenterX+200))||(particleY[i]<(particleCenterY-200))||(particleY[i]>(particleCenterY+200)))
						{
						particleX[i] = (particleCenterX-20) + (int)(40*Math.random());
						particleY[i] = (particleCenterY-20) + (int)(40*Math.random());
						particleXdX[i] = -5+(int)(11*Math.random());
						particleYdY[i] = -5+(int)(11*Math.random());
						}
					else
						{
						particleX[i] = particleX[i] + particleXdX[i];
						particleY[i] = particleY[i] + particleYdY[i];
						}		
					for(int b = 0;b<99;b++)
						{
						g2d.setColor(Gold);	
						windowTemp.setFont(font1);
						g2d.drawString("$",particleX[b],particleY[b]);
						}
				}
				windowTemp.setFont(font);
			}//xxxclose
	//draw buttons
		windowTemp.setColor(Color.GRAY);
		windowTemp.fillRoundRect(600,50,350,100,10,10);
		windowTemp.fillRoundRect(750,160,200,50,10,10);
		windowTemp.fillRoundRect(750,220,200,50,10,10);
		windowTemp.fillRoundRect(750,280,200,50,10,10);
	//draw buttons text
		windowTemp.setColor(PearCider);
		windowTemp.drawString("Spin",760,200);
		windowTemp.drawString("Quit",760,260);
		windowTemp.drawString("/Lag",760,320);
		windowTemp.drawString("Money: $"+money+".00",620,120);  
        //Draw number
	setNum();
	for(int i = 0;i<5;i++)
		{
       		num1.drawMe(windowTemp, numXLocation[i], 600, numbers[i]);
		}
	if(gameMenu==true||animate==true)
		{
			//casino image draw section
		g2d.drawImage(imgB, -10000, -10000, 35000,25000, null);
		windowTemp.setFont(font2);
		windowTemp.setColor(Gold);
		windowTemp.drawString("Slot Poker",-9000, -8000);
		windowTemp.setFont(font3);
		windowTemp.drawString("Select User to Play",-9000, -6700);
		windowTemp.setColor(Color.GREEN);
		windowTemp.drawString("Made by Joshua Howland",-7800, 4000);
		
		windowTemp.drawString("New User",5000,-7500);
		for(int i = 0;i<10;i++)
			{
			windowTemp.drawString(users[i],5000,-6000+ 1000*i);
			}
		}
	animate();       
        //Draw Buffered Image
        window.drawImage(buffered, 0, 0, null);   
    }//end paint

   public void animate()
        {
           		try { Thread.sleep(25);}
			catch(InterruptedException ex) {
			Thread.currentThread().interrupt();}
                	repaint();
	}        
	public void animatePersons()
		{
			if(setPersons == true)
				{
				setPersons = false;
				for(int i = 0; i<(PersonNumbers/2);i++)
					{
					personX[i] = (int)(800*Math.random());
					personY[i] = 320 + (int)(i/2);
					persondX[i] = -2 + (int)(5*Math.random());
					personType[i] = (int)(6*Math.random());
					if(i%2 == 0)
						personType[i] = 1;
					}
				for(int i = (PersonNumbers/2); i<PersonNumbers;i++)
					{
					personX[i] = (int)(800*Math.random());
					personY[i] = 370 + (int)(i*2 );
					persondX[i] = -4 + (int)(8*Math.random());
					personType[i] = (int)(6*Math.random());
					if(i%2 == 0)
						personType[i] = 1;
					}
				}	
			for(int i = 0; i<PersonNumbers;i++)
				{
					if(persondX[i]==0)
						persondX[i] = 1;
					if(persondX[i]<0)
						personNorth[i] = 1;
					else
						personNorth[i] = 0;
				}
			for(int i = 0; i<(PersonNumbers/2);i++)
				{
				personX[i] = personX[i] - persondX[i];
				if(personX[i] <-100)
					{	
					personX[i] = 1000 + (int)(50*Math.random());
					persondX[i] = -2 + (int)(5*Math.random());
					}
				if(personX[i] > 1050)
					{	
					personX[i] = -50 - (int)(50*Math.random());
					persondX[i] = -2 + (int)(5*Math.random());
					}
				}
			for(int i = (PersonNumbers/2); i<PersonNumbers;i++)
				{
				personX[i] = personX[i] - persondX[i];
				if(personX[i] <-100)
					{	
					personX[i] = 1000 + (int)(50*Math.random());
					persondX[i] = -4 + (int)(8*Math.random());
					}
				if(personX[i] > 1050)
					{	
					personX[i] = -50 - (int)(50*Math.random());
					persondX[i] = -2 + (int)(5*Math.random());
					}
				}
		}
	public void mouseClicked(MouseEvent e)
        {
        	int x = e.getX();
        	int y = e.getY();
		System.out.println(x);
		if(gameMenu == true)
			{
			if(x>740&&x<1000&&y>150&&y<650&&(gameMenu==true)) 
				{
					currentUser = (y-150)/50;
					money = usersMoney[currentUser];
System.out.println(currentUser);System.out.println(currentUser);System.out.println(currentUser);System.out.println(currentUser);
					if(users[currentUser].equals("Josh"))
						{
						String pass = JOptionPane.showInputDialog("Admin Password");
						if(pass.equals("How"))
							{money = 999999999;
							gameMenu=false;
							animate = false;}//change animate to true for admin animation
						}
					else if(false==(users[currentUser].equals("Empty")))
						{
						money = usersMoney[currentUser];
						gameMenu=false;
						animate = true;
						}
					}
			if(x>740&&x<1000&&y>90&&y<150) //add new user
				{
				currentUser = (y-150)/50;
				String newUser = JOptionPane.showInputDialog("Enter Your Name");
				for(int i=0;i<10;i++)
					{if("Empty".equals(users[i]))
						{users[i] = newUser;money=20; usersMoney[currentUser] = 20; i=11;}}
				try{addNewUser(newUser);}
					catch(Exception g){}	
				//gameMenu = false;
				//animate = true;
				}
			}
		if(x>750&&x<950&&y>160&&y<210&&(gameMenu==false)) //spin button
			{
			if(money>0)
				{
				checkNumbers = true;
				setNumbers = true;
				money--;
				}
			if(money<=0)
				JOptionPane.showMessageDialog(null, "Current account balance = $0.00", "Out of Money", JOptionPane.ERROR_MESSAGE);	
			}
		if(x>750&&x<950&&y>220&&y<270&&(gameMenu==false)) //menu button
			{
			gameMenu = true;
			animate = false;
 			scale = .05;
  			translateX = 10000;
 			translateY = 10000;
			usersMoney[currentUser] = money;
			try{updateUsers(users[currentUser],usersMoney[currentUser]);}	catch(Exception f){}
			}	
		if(x>750&&x<950&&y>280&&y<330) //empty button
			{
			for(int i = 0; i<PersonNumbers;i++)
				persondX[i] = -persondX[i];	
			}
        }
	    public void mouseEntered(MouseEvent e) {}
	    public void mouseExited(MouseEvent e){}
	    public void mousePressed(MouseEvent e){}
	    public void mouseReleased(MouseEvent e) {}
}