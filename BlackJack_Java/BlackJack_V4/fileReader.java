import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.FileWriter;
import java.awt.Graphics;

public class fileReader
{

private Scanner file;
private String[] gameData = new String[55];
private int userNumbers = 0;
int UserMoney = 0;


public fileReader() throws IOException
	{
	file = new Scanner(new File("gameData.dat"));
	int i = 0;
	
	while(file.hasNextLine())
		{
		gameData[i] = file.nextLine();
		System.out.println(gameData[i]);
		i++;
		}
	UserMoney = Integer.parseInt(gameData[0]);
	}
	
public void updateFile(int score) throws IOException
	{
	UserMoney = score;
	PrintWriter fileOut = new PrintWriter(new FileWriter("gameData.dat")); 
	fileOut.println(UserMoney);
		//for(int i=0; i<50; i++)
			//{
				 //fileOut.println(gameData[i]);	
		//	}
			fileOut.close();
	}
public int returnMoney()
	{
	return UserMoney;
	}
public void resetScore() throws IOException
	{
	updateFile(100);
	}
	
	/*
public String returnUsers(String UNInNumber) throws IOException //UNI tag is #1/#2/#3 for usernum
	{
	for(int i = 0;i<50;i++)
		{
		if(UNInNumber.equals(gameData[i]))
			{return gameData[i+1];}
		}
	return "Not Found";
	}
public int returnUsersMoney(String UNInNumber) throws IOException
	{
	for(int i = 0;i<50;i++)
		{
		if(UNInNumber.equals(gameData[i]))
			{return Integer.parseInt(gameData[i+2]) ;}
		}
	return 999999;
	}
public void updateUserMoney(String UserName, int newMoney) throws IOException
	{
	for(int i = 0;i<50;i++)
		{
		if(UserName.equals(gameData[i]))
			{gameData[i+1] = Integer.toString(newMoney);}////////HERE IS THE PROBLEM I THINK
		}
	updateFile();
	}
public void addUser(String UserName) throws IOException
	{
	for(int i = 0;i<50;i++)
		{if("Empty".equals(gameData[i]))
		{gameData[i] = UserName; i=60;
		}
		}
	updateFile();
	}
public void displayUsers(Graphics window)
	{
	window.setColor(Color.BLACK);
	window.fillRoundRect(100,100,600,400,20,20);
	for(
	}
	*/
}