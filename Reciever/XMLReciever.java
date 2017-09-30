package Reciever;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class XMLReciever
{
	String domain;
	int portNumber;
	InputStream inToo;
	
	public XMLReciever(String domain, int portNumber)
	{
		this.portNumber = portNumber;
		this.domain = domain; 
	}
	
	public void getObject()
	{
		Socket socket = null;
		// Attempts to open a connection to the domain path and portnumber specified // 
		try
		{
			//Initializing Socket and print writer //  
			socket = new Socket(domain, portNumber);
			System.out.println("Connected to XMLserver");
			// Used as buffer for incoming data streams // 
			byte[] data = new byte[10 * 1024];
			System.out.println("Saving file deserializer.xml");
			FileOutputStream fileOut = new FileOutputStream("deserializer.xml");
			inToo = socket.getInputStream();
			
			// Writes data to file // 
			fileOut.write(data, 0, inToo.read(data));
			
			// Closing Connections // 
			fileOut.close();
			closeConnection(socket);
		}
		catch (Exception e)
		{
			System.out.println("Error Connecting to output check server is running");
			e.printStackTrace();
		}
	}
	public void closeConnection(Socket socket)
	{
		try 
		{
			socket.shutdownInput();
			socket.close();
			System.out.println("Connection closed");
		} 
		catch (IOException e)
		{
			System.out.println("Error Closing Socket Connection");
			e.printStackTrace();
		}	
	}		
}
