package Sender;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.*;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;

public class XMLServer extends Thread
{
	ServerSocket server; 
	Socket connect; 
	OutputStream output; 
	
	public void startConnection()
	{
		try
		{
			server = new ServerSocket(2225);
			System.out.println("Server Started on socket 2225");
			System.out.println("Waiting for Connection");
			connect = server.accept();
			System.out.println("Client Connected");
			output = connect.getOutputStream();
			sendXML();
			closeConnection();
		}
		catch (Exception e)
		{
			System.out.println("Error with Server");
			
		}
	}
	
	public void sendXML()
	{
		Path path = FileSystems.getDefault().getPath("serialized.xml");
        try {
			Files.copy(path,output);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("Sending XML");
	}
	
	public void closeConnection()
	{
		if(server != null)
		{
			try 
			{
				server.close();
				System.out.println("Server Closed");
			}
			catch (IOException e) 
			{
				System.out.println("Problem Closing Server. May already be closed");
			}
			
		}
		
	}

}
