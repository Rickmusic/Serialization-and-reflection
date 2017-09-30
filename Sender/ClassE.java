package Sender;
import java.util.LinkedList;
import java.util.Scanner;


public class ClassE
{
	LinkedList<ClassA> list = new LinkedList<ClassA>();;
	public ClassE()
	{
		//No arg Constructor // 
	}
	public ClassE(Scanner scan)
	{
		System.out.println("This class requires you to create 3 class A objects");
		list.add(new ClassA(scan));
		list.add(new ClassA(scan));
		list.add(new ClassA(scan));
	}

}
