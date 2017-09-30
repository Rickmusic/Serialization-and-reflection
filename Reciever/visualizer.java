package Reciever;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Scanner;

import org.jdom2.JDOMException;

import Sender.Inspector;

public class visualizer 
{
	static Object obj;
	static XMLReciever reciever;
	static deserializer deserializer; 
	static Scanner scan; 
	public static void main(String[] args) throws JDOMException, IOException, ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchFieldException
	{
		deserializer = new deserializer();
		scan = new Scanner(System.in);
		deserializeXML();
		options();
	}

	private static void deserializeXML() throws JDOMException, IOException, ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchFieldException
	{
		obj = deserializer.deserialize(deserializer.makeDoc());

		
	}

	private static void options() throws IllegalArgumentException, IllegalAccessException, ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException, InvocationTargetException, NoSuchFieldException, JDOMException, IOException
	{
		System.out.println("Enter 'S' to search for objects");
		System.out.println("Enter 'V' to view current objects");
		System.out.println("Enter 'Q' to quit");
		String input = scan.next();
		if(input.length()>1)
		{
			System.out.println("Invalid Input");
			options();
		}
		else if(input.equalsIgnoreCase("S")) SearchObject();
		else if(input.equalsIgnoreCase("V")) ViewObjects();
		else if(input.equalsIgnoreCase("Q")) 
		{
			System.out.println("Program terminated");
			System.exit(0);
		}
		else
		{
			System.out.println("Invalid Input");
			options();
		}
	}

	private static void ViewObjects() throws IllegalArgumentException, IllegalAccessException, ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException, InvocationTargetException, NoSuchFieldException, JDOMException, IOException
	{
		if(obj == null)
		{
			System.out.println("No object have been found yet");
		}
		else 
		{
			Inspector CIA = new Inspector();
			CIA.inspect(obj);
			
		}
		options();
	}

	private static void SearchObject() throws IllegalArgumentException, IllegalAccessException, ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException, InvocationTargetException, NoSuchFieldException, JDOMException, IOException
	{
		reciever = new XMLReciever(setHost(), 2225);
		reciever.getObject();
		deserializeXML();
		options();
		
	}

	private static String setHost()
	{
		System.out.println("Enter 'D' to use Default Location (localhost)");
		System.out.println("Enter 'C' to enter custom host");
		String input = scan.next();
		if(input.length()>1)
		{
			System.out.println("Invalid Input");
			return setHost();
		}
		else if(input.equalsIgnoreCase("D")) return "localhost"; 
		else if(input.equalsIgnoreCase("C")) return customhost();
		else
		{
			System.out.println("Invalid Input");
			return setHost();
		}
	}

	private static String customhost()
	{
		System.out.println("Enter HostName: ");
		return scan.next();
	}
}
