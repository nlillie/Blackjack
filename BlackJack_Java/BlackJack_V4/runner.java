import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.Box.*;
import java.io.IOException;
import java.io.*;
import javax.swing.JOptionPane;

public class runner extends JFrame {

 static drawContent f = new drawContent();
 static instructions i = new instructions(f);
 static drawZombieContent z = new drawZombieContent();
 static cubeGame c = new cubeGame();
 static spiralGame s = new spiralGame();

  static JPanel content = new JPanel();
 
 static JButton Hit = new JButton("Hit");
 static JButton Stand = new JButton("Stand");
 static JButton newGame = new JButton("New Game");
 static JButton playDealer = new JButton("Play Dealer");
 static JButton stopPlayDealer = new JButton("Stop Dealer");
 
 static JButton closeInstructions = new JButton("Close Instructions");
 static JButton showInstructionsButton = new JButton("Show Instructions");
 
static  JButton addZombies = new JButton("Zombie Game");
 static JButton cube = new JButton("Cube Game");
 static JButton spiral = new JButton("Spiral Game");
 static JButton closeGame = new JButton("Close Game"); 
 static JButton gamePlay = new JButton("Play");

 static boolean stand = false;
 static boolean Dealer = false;
 static boolean showInstructions = true;
 static boolean showZombiePlay = true;

public static boolean showZombieGame = false;
public static boolean showCubeGame = false;
public static boolean showSpiralGame = false;


    public runner() {
	
        f.setBackground(Color.BLACK);
		content.setBackground(Color.BLACK);

		Hit.setPreferredSize(new Dimension(100, 60));	
		Stand.setPreferredSize(new Dimension(100, 60));
		newGame.setPreferredSize(new Dimension(100, 60));
		playDealer.setPreferredSize(new Dimension(100, 60));
		stopPlayDealer.setPreferredSize(new Dimension(100, 60));
		closeInstructions.setPreferredSize(new Dimension(100, 60));
		showInstructionsButton.setPreferredSize(new Dimension(100, 60));
		addZombies.setPreferredSize(new Dimension(200, 40));
		gamePlay.setPreferredSize(new Dimension(100, 60));
		closeGame.setPreferredSize(new Dimension(100, 60));
		cube.setPreferredSize(new Dimension(200, 40));
		spiral.setPreferredSize(new Dimension(200, 40));
	
	ButtonHandler BStand = new ButtonHandler();
	Stand.addActionListener(BStand);

	ButtonHandler BHit = new ButtonHandler();
	Hit.addActionListener(BHit);
	ButtonHandler BnewGame = new ButtonHandler();
	newGame.addActionListener(BnewGame);
	ButtonHandler BplayDealer = new ButtonHandler();
	playDealer.addActionListener(BplayDealer);
	ButtonHandler BstopPlayDealer = new ButtonHandler();
	stopPlayDealer.addActionListener(BstopPlayDealer);
	ButtonHandler BcloseInstructions = new ButtonHandler();
	closeInstructions.addActionListener(BcloseInstructions);
	ButtonHandler BshowInstructionsButton = new ButtonHandler();
	showInstructionsButton.addActionListener(BshowInstructionsButton);
	ButtonHandler BaddZombies = new ButtonHandler();
	addZombies.addActionListener(BaddZombies);
	ButtonHandler BgamePlay = new ButtonHandler();
	gamePlay.addActionListener(BgamePlay);
	ButtonHandler BcloseGame = new ButtonHandler();
	closeGame.addActionListener(BcloseGame);
	ButtonHandler Bcube = new ButtonHandler();
	cube.addActionListener(Bcube);
	ButtonHandler Bspiral = new ButtonHandler();
	spiral.addActionListener(Bspiral);
	addComponents();

///////////////////////////////////////////////////////////// Set's window properties
	setBackground(Color.BLACK);
        setContentPane(content);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("BlackJack_V2");
        pack();
    }


public static void addComponents()
	{
	content.removeAll();
	f.removeAll();
	i.removeAll();
	f.setLayout(new FlowLayout());
		f.add(newGame);
		f.add(Hit);
		f.add(Stand);
		f.add(playDealer);
		f.add(stopPlayDealer);

		if(showInstructions==true)
			{i.add(closeInstructions);}
		else
			{i.add(showInstructionsButton);
		if(showZombiePlay==true)i.add(addZombies);i.add(cube);i.add(spiral);}
			content.add(f);
			content.add(i);
		if(showZombieGame==true)
			{
			content.removeAll();
			content.add(z);
			z.setLayout(new FlowLayout());
			z.add(gamePlay);
			z.add(closeGame);
			if(showZombiePlay==true&& f.getTokens()>0)
				{
					z.reset();}
			else
			{i.add(addZombies);}
			}
		else if(showCubeGame==true)
			{
			content.removeAll();
			content.add(c);
			c.setLayout(new FlowLayout());
			c.add(gamePlay);
			c.add(closeGame);
			}
		else if(showSpiralGame==true)
			{
			content.removeAll();
			content.add(s);
			s.setLayout(new FlowLayout());
			s.add(gamePlay);
			s.add(closeGame);
			}
	content.repaint();
	content.revalidate();
	}
 
    public static void main(String[] args) {
        JFrame window = new runner();
        window.setVisible(true);
    }
}

	class ButtonHandler implements ActionListener
		{
		public void actionPerformed(ActionEvent e) 
			{
			if(e.getSource() == runner.Stand) 
				{
					if(runner.stand==false)
           				       runner.f.finalizeScore();
					runner.stand = true;
				}
			else if(e.getSource() == runner.Hit) 
				{
				int score = runner.f.returnScore();
				if(score<21&&runner.stand==false)
                     		runner.f.addCardToTable();
				}
			else if(e.getSource() == runner.newGame) 
				{
				runner.stand=false;
				runner.f.resetCards();
				}
			else if(e.getSource() == runner.playDealer) 
				{
				runner.f.playDealer();
				}
			else if(e.getSource() == runner.stopPlayDealer) 
				{
				runner.f.stopPlayDealer();
				}
			else if(e.getSource() == runner.closeInstructions) 
				{
				runner.i.hideInstructions();
				runner.showInstructions = false;
				runner.addComponents();
				}
			else if(e.getSource() == runner.showInstructionsButton) 
				{
				runner.i.showInstructions();
				runner.showInstructions = true;
				runner.addComponents();				
				}
			else if(e.getSource() == runner.addZombies) 
				{
				runner.showZombieGame = true;
				runner.addComponents();			
				}
			else if(e.getSource() == runner.cube) 
				{
				runner.showCubeGame = true;
				runner.addComponents();			
				}
			else if(e.getSource() == runner.spiral) 
				{
				runner.showSpiralGame = true;
				runner.addComponents();			
				}

			else if(e.getSource() == runner.closeGame) 
				{
				runner.showZombiePlay = true;
				runner.showZombieGame = false;
				runner.showCubeGame = false;
				runner.showSpiralGame=false;
				runner.c.reset();
				runner.z.reset();
				runner.s.reset();
				runner.addComponents();			
				}	

			else if(e.getSource() == runner.gamePlay) 
				{
				if(runner.f.tokens>0)
					{
					runner.f.tokens--;
					if(runner.showZombieGame==true)
							{runner.z.reset();
							runner.z.start();}
					else if(runner.showCubeGame==true)
						{runner.c.reset();
						runner.c.play=true;
						}
					else if(runner.showSpiralGame==true)
						{runner.s.reset();
						runner.s.play=true;
						}
					runner.addComponents();
					}
				else	{
					JOptionPane.showMessageDialog(null, "No Tokens.  Recieve a Score of 20 or 21 during BlackJack Gameplay for More Tokens", "Window Label",JOptionPane.ERROR_MESSAGE); }

				}		

			}
		}

