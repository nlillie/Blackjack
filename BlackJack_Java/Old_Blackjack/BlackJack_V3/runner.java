import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.Box.*;
import java.io.IOException;

public class runner extends JFrame {

private drawContent f = new drawContent();
private instructions i = new instructions(f);
private drawZombieContent z = new drawZombieContent();
private cubeGame c = new cubeGame();
private spiralGame s = new spiralGame();

 JPanel content = new JPanel();
 
 JButton Hit = new JButton("Hit");
 JButton Stand = new JButton("Stand");
 JButton newGame = new JButton("New Game");
 JButton playDealer = new JButton("Play Dealer");
 JButton stopPlayDealer = new JButton("Stop Dealer");
 
 JButton closeInstructions = new JButton("Close Instructions");
 JButton showInstructionsButton = new JButton("Show Instructions");
 
 JButton addZombies = new JButton("Zombie Game");
 JButton cube = new JButton("Cube Game");
 JButton spiral = new JButton("Spiral Game");
 JButton closeGame = new JButton("Close Game"); 
 JButton gamePlay = new JButton("Play");

private boolean stand = false;
private boolean Dealer = false;
private boolean showInstructions = true;
private boolean showZombiePlay = true;

public boolean showZombieGame = false;
public boolean showCubeGame = false;
public boolean showSpiralGame = false;

fileReader fileRead;

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

		try{fileRead = new fileReader();}catch(Exception e){}
		try{f.updateMoney(fileRead.returnMoney());}
			catch(Exception e){System.out.println("XXX");} //RETURN MONEY NOT WORKING???
			
///////////////////////////////////////////////////////////// Button Listener's Section

        Stand.addActionListener(new ActionListener() {   	//Stand
                public void actionPerformed(ActionEvent e) {
		if(stand==false)
                   f.finalizeScore();
					stand = true;
						try{fileRead.updateFile(f.returnMoney());}
							catch(Exception e1){}
                }
            });
  Hit.addActionListener(new ActionListener() { 			 //Hit
                public void actionPerformed(ActionEvent e) {
int score = f.returnScore();
		if(score<21&&stand==false)
                     f.addCardToTable();
						try{fileRead.updateFile(f.returnMoney());}
							catch(Exception e2){}
                }
            });
	newGame.addActionListener(new ActionListener() {	  //New Game
                public void actionPerformed(ActionEvent e) {
				stand=false;
				f.resetCards();
						try{;fileRead.updateFile(f.returnMoney());}
							catch(Exception e3){}
                }
            });
	playDealer.addActionListener(new ActionListener() {  	//Play Dealer
                public void actionPerformed(ActionEvent e) {
					f.playDealer();
						try{fileRead.updateFile(f.returnMoney());}
							catch(Exception e4){}
                }
            });
	stopPlayDealer.addActionListener(new ActionListener() {  	//Stop Playing Dealer
                public void actionPerformed(ActionEvent e) {
				f.stopPlayDealer();
					try{fileRead.updateFile(f.returnMoney());}
							catch(Exception e5){}
                }
            });
			
	closeInstructions.addActionListener(new ActionListener() {  	//Close instructions
                public void actionPerformed(ActionEvent e) {
				i.hideInstructions();
				showInstructions = false;
				addComponents();
                }
            });
		
	showInstructionsButton.addActionListener(new ActionListener() {  	//Show instructions
                public void actionPerformed(ActionEvent e) {
				i.showInstructions();
				showInstructions = true;
				addComponents();
                }
            });

	addZombies.addActionListener(new ActionListener() {  	//add zombie game
                public void actionPerformed(ActionEvent e) {
				showZombieGame = true;
				addComponents();
                }
            });

	gamePlay.addActionListener(new ActionListener() {  	//zombie play
                public void actionPerformed(ActionEvent e) {
				if(f.tokens>0)
					{
					f.tokens--;
					if(showZombieGame==true)
							{z.reset();
							z.start();}
					else if(showCubeGame==true)
						{c.reset();
						c.play=true;
						}
					else if(showSpiralGame==true)
						{s.reset();
						s.play=true;
						}
					addComponents();}
                }
            });

	closeGame.addActionListener(new ActionListener() {  	// game close
                public void actionPerformed(ActionEvent e) {
				showZombiePlay = true;
				showZombieGame = false;
				showCubeGame = false;
				showSpiralGame=false;
				c.reset();
				z.reset();
				s.reset();
				addComponents();
                }
            });
	cube.addActionListener(new ActionListener() {  	//cube button
                public void actionPerformed(ActionEvent e) {
				showCubeGame = true;	
				addComponents();
                }
            });
	spiral.addActionListener(new ActionListener() {  	//spiral button
                public void actionPerformed(ActionEvent e) {
				showSpiralGame = true;	
				addComponents();
                }
            });
	addComponents();

///////////////////////////////////////////////////////////// Set's window properties
	setBackground(Color.BLACK);
        setContentPane(content);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("BlackJack_V2");
        pack();
    }

public void addComponents()
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