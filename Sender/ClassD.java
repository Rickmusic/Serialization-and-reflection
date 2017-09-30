package Sender;
import java.util.Scanner;


public class ClassD
{
	ClassA[] array;
	public ClassD()
	{
		//No arg Constructor // 
	}
	public ClassD(Scanner scan)
	{
		System.out.println("This class requires you to create 3 class A objects");
		array = new ClassA[3];
		array[0] = new ClassA(scan);
		array[1] = new ClassA(scan);
		array[2] = new ClassA(scan); 
	}
	
	

}
