package Sender;
import java.util.Scanner;


public class ClassB 
{
	ClassA A;
	ClassC C;
	public ClassB()
	{
		//No arg Constructor // 
	}
	public ClassB(Scanner scan)
	{
		A = new ClassA(scan);
		C = new ClassC(scan);
	}

}
