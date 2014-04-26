import java.awt.Graphics;

public interface cardInterface
{
   //height and width of card
   static final int height =100;
   static final int width =150;
   
   //Get value of the card
   public int getValue();
   
   //Get name of the card
   public String getName();
   
   //Get suit of the the card
   public String getSuit(); 
   
   //draw card
   public void drawMe(Graphics window, int x, int y);
   
}
