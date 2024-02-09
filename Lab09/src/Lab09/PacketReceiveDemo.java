package Lab09;
import java.net.*;
import java.io.*;
// Chapter 5, Listing 1
public class PacketReceiveDemo
{
	public static void main (String args[])
	{
		try//try
		{
			//顯示目前binding
			System.out.println ("Binding to local port 2000");
			// Create a datagram socket, bound to the specific port 2000
			DatagramSocket socket = new DatagramSocket(2000);
			
			//顯示目前Binding的port
			System.out.println ("Bound to local port " + socket.getLocalPort());
			// Create a datagram packet, containing a maximum buffer of 256 bytes
			DatagramPacket packet = new DatagramPacket(new byte[256], 256);
			// Receive a packet - remember by default this is a blocking operation
			socket.receive(packet);  
			//顯示已收到
			System.out.println ("Packet received!");
			
			// Display packet information
			InetAddress remote_addr = packet.getAddress();//建立封包address
			System.out.println ("Sent by : " + remote_addr.getHostAddress() );//顯示傳來的address
			System.out.println ("Sent from: " + packet.getPort());//顯示傳來的port
			// Display packet contents, by reading from byte array
			ByteArrayInputStream bin = new ByteArrayInputStream (packet.getData());
			// Display only up to the length of the original UDP packet
			for (int i=0; i < packet.getLength(); i++)
			{
				int data = bin.read();//讀入byte array
				if (data == -1)//當無
					break;//則break
				else
					System.out.print ( (char)data) ;//否則輸出packet內容
			}
			socket.close();//關
		}
		catch (IOException ioe)//例外處理
		{
			System.err.println ("Error - " + ioe);//例外處理
		}
	}
}

/*
//Display packet contents, by reading from byte array
ByteArrayInputStream bin = new ByteArrayInputStream (packet.getData());
//Display only up to the length of the original UDP packet
for (int i=0; i < packet.getLength(); i++)
{
	int data = bin.read();
	if (data == -1)
		break;
	else
		System.out.print ( (char) data) ;
}
*/
