import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;

public class card implements cardInterface
{

   private int value;
   private String name;
   private String suit; 
   Font font = new Font("Arial", Font.PLAIN, 50);
private Image clovers = Toolkit.getDefaultToolkit().getImage(getClass().getResource("images/clovers.png"));
private Image diamonds = Toolkit.getDefaultToolkit().getImage(getClass().getResource("images/diamonds.png"));
private Image hearts = Toolkit.getDefaultToolkit().getImage(getClass().getResource("images/hearts.png"));
private Image spades = Toolkit.getDefaultToolkit().getImage(getClass().getResource("images/spades.png"));
   
   public card(int value, String name, String suit)
       {
      this.value = value;
      this.name = name;
      this.suit = suit;
	}
   
   public int getValue()
   {
      return this.value;
   }
   
   public String getName()
   {
      return this.name;
   }
  
   public String getSuit()
   {
      return this.suit;
   }
   
   public void drawMe(Graphics window, int x, int y)
   {
      window.setColor(Color.white);
      window.fillRoundRect(x,y,height,width,15,15);
      window.setColor(Color.black);
      window.drawRoundRect(x,y,height,width,15,15);
      
      window.setFont(font);
	  if(suit.equals("hearts")||suit.equals("diamonds"))
		window.setColor(Color.red);
	  else
		window.setColor(Color.black);
		
		if(suit.equals("hearts"))
			window.drawImage(hearts,x+40,y+100,60,60, null);
		else if(suit.equals("diamonds"))
			window.drawImage(diamonds,x+40,y+100,60,60, null);
		else if(suit.equals("spades"))
			window.drawImage(spades,x+40,y+100,60,60, null);
		else if(suit.equals("clovers"))
			window.drawImage(clovers,x+40,y+100,60,60, null);
	  
	  
      if( name.equals("jack") )
      		{
			window.drawString("J", x+30, y + 100);
     		}
	  else if(name.equals("queen"))
		{
			window.drawString("Q", x+30, y + 100);
		}
	  else if(name.equals("king"))
		{
			window.drawString("K", x+30, y + 100);
		}
	  else if(name.equals("ace"))
		{
			window.drawString("A", x+30, y + 100);
		}
      	else
      		{
         		window.drawString(this.value+"", x+30, y + 100);
      		}
      
   }
   
   
}
