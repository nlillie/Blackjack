import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Font;

public interface C0l0rs
{
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
	Font font1 = new Font("Arial", Font.PLAIN, 45);
	Font font2 = new Font("Arial", Font.PLAIN, 25);
	Font font3 = new Font("Arial", Font.PLAIN, 1500);
	Font font4 = new Font("Arial",Font.PLAIN,1000);
}