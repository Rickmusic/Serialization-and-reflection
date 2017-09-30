package Sender;
import java.util.Scanner;
public class ClassC 
{
	int[] array;
	
	public ClassC()
	{
		//No arg Constructor // 
	}
	
	public ClassC(Scanner scan)
	{
		getLength(scan);
		getData(scan);
	}

	
	private void getLength(Scanner scan)
	{
		System.out.println("Please Enter an Integer to serve as the array length: ");
		String input = scan.next();
		try
		{
			int size = Integer.parseInt(input);
			array = new int[size];
		}
		catch(Exception e)
		{
			System.out.println("Invalid Input");
			getLength(scan);
		}
	}
	
	private void getData(Scanner scan) 
	{
		for(int x = 0; x < array.length; x++)
		{
			System.out.println("Please Enter an Integer: ");
			String input = scan.next();
			try
			{
				int number = Integer.parseInt(input);
				array[x] = number;
			}
			catch(Exception e)
			{
				System.out.println("Invalid Input");
				x--;
			}
		}
	}

}
