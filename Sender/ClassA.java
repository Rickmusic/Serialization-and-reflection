package Sender;
import java.util.Scanner;

public class ClassA
{
	public int number;
	private boolean bool; 

	public ClassA()
	{
		//No arg Constructor // 
	}
	public ClassA(Scanner scan)
	{
		System.out.println("In order to make this Class you must instiate two varibles");
		makeInt(scan);
		makeBool(scan);		
	}
	
	public void makeInt(Scanner scan)
	{
		System.out.println("Varible Number 1 is an integer");
		System.out.println("Please Enter an Integer: ");
		String input = scan.next();
		try
		{
			number = Integer.parseInt(input);
		}
		catch(Exception e)
		{
			System.out.println("Invalid Input");
			makeInt(scan);
		}
		
	}
	
	public void makeBool(Scanner scan)
	{
		System.out.println("Varible number 2 is  Boolean");
		System.out.println("Please enter 'T' for true and 'F' for false: ");
		String input = scan.next();
		if(input.length()>1)
		{
			System.out.println("Invalid Input");
			makeBool(scan);
		}
		else if(input.equalsIgnoreCase("T"))
		{
			bool = true;
		}
		else if(input.equalsIgnoreCase("F"))
		{
			bool = false;
		}
		else
		{
			System.out.println("Invalid Input");
			makeBool(scan);
		}
	}
	

}
