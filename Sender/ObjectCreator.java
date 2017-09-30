package Sender;
import java.util.Scanner; 

public class ObjectCreator {
	static Scanner scan; 
	static Object item;
	static Inspector ninja;
	static Serializer serial; 
	static boolean serialized; 
	
	public static void main(String[] args) throws IllegalArgumentException, IllegalAccessException
	{	
		scan = new Scanner(System.in);
		ninja = new Inspector();
		serial = new Serializer(); 
		mainLoop();
	}
	
	public static void mainLoop() throws IllegalArgumentException, IllegalAccessException
	{
		mainCreator();
		nextOptions();
	}
	
	private static void nextOptions() throws IllegalArgumentException, IllegalAccessException
	{
		System.out.println("\nEnter 'V' to view your object");
		System.out.println("Enter 'S' to serialize your object your object");
		System.out.println("Enter 'Z' to send Object");
		System.out.println("Enter 'R' to restart");
		String input = scan.next();
		if(input.length()>1)
		{
			System.out.println("Invalid Input");
			nextOptions();
		}
		else if(input.equalsIgnoreCase("V")) ViewObject();
		else if(input.equalsIgnoreCase("S")) serializeObject();
		else if(input.equalsIgnoreCase("R")) mainLoop();
		else if(input.equalsIgnoreCase("Z")) sendObject();
		else
		{
			System.out.println("Invalid Input");
			nextOptions();
		}
	}
	
	private static void sendObject() throws IllegalArgumentException, IllegalAccessException 
	{
		if(serialized == false)
		{
			System.out.println("Object needs to be serialized first");
			nextOptions();
		}
		else
		{
			XMLServer server = new XMLServer();
			server.startConnection();
			mainLoop();
		}
		
		
	}

	private static void serializeObject() throws IllegalArgumentException, IllegalAccessException
	{
		
		serial.serialize(item);
		System.out.println("Object Serialized");
		serialized = true; 
		nextOptions();
	}

	private static void ViewObject() throws IllegalArgumentException, IllegalAccessException 
	{
		System.out.println("View Object");
		try
		{
			ninja.inspect(item);
		} 
		catch (Exception e)
		{
			System.out.println("Something went wrong... Restarting");
			mainLoop();
		}
		nextOptions();
	}

	public static void mainCreator ()
	{
		serialized = false; 
		System.out.println("\nClass A: Object with only primitives");
		System.out.println("Class B: Object with a reference to a Class A and a Class C Object");
		System.out.println("Class C: Object contains an array of primitives");
		System.out.println("Class D: Object contains an array of 3 Class A Objects");
		System.out.println("Class E: Object contains a LinkedList of 3 Class A Objects");
		System.out.println("Enter Q to quit program\n");
		initialHandler();
	}
	
	public static void initialHandler()
	{		
		System.out.println("Please enter the character name (A, B, C, D or E) of the class you wish to create: ");
		String input = scan.next();
		if(input.length()>1)
		{
			System.out.println("Invalid Input");
			initialHandler();
		}
		else if(input.equalsIgnoreCase("A"))
		{
			item = new ClassA(scan);
		}
		else if(input.equalsIgnoreCase("B"))
		{
			item = new ClassB(scan);
			
		}
		else if(input.equalsIgnoreCase("C"))
		{
			item = new ClassC(scan);
			
		}
		else if(input.equalsIgnoreCase("D"))
		{
			item = new ClassD(scan);
		}
		else if(input.equalsIgnoreCase("E"))
		{
			item = new ClassE(scan);
		}
		else if (input.equalsIgnoreCase("Q"))
		{
			System.out.println("Program terminated");
			System.exit(0);
		}
		else
		{
			System.out.println("Invalid Input");
			initialHandler();	
		}
		
	}
	
}
