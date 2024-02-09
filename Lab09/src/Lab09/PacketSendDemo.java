package Lab09;
import java.net.*;
import java.io.*;
// Chapter 5, Listing 2
public class PacketSendDemo
{
	public static void main (String args[])
	{
		int argc = args.length;//長度建立
		// Check for valid number of parameters
		/*if (argc != 1)
		{
			System.out.println ("Syntax :");
			System.out.println ("java PacketSendDemo hostname");
			return;
		}
		*/
		String hostname = "127.0.0.1";//ip位址
		try
		{
			System.out.println ("Binding to a local port");//顯示連線
			// Create a datagram socket, bound to any available local port
			//Unlike the receiver demonstration, it doesn't matter which local port is being used. In fact,
			//any free port is a candidate, and you may find that running the application several times will result
			//in different port numbers. After binding to a port, the port number is displayed to demonstrate this
			DatagramSocket socket = new DatagramSocket();//建立udp socket
			System.out.println ("Bound to local port " + socket.getLocalPort());//連線到的port顯示
			// Create a message to send using a UDP packet
			//Before sending any data, we need to create a DatagramPacket. First, a
			//ByteArrayOutputStream is used to create a sequence of bytes. Once this is complete, the
			//array of bytes is passed to the DatagramPacket constructor.
			ByteArrayOutputStream bout = new ByteArrayOutputStream();//byte array輸出
			PrintStream pout = new PrintStream (bout);//網路傳的PrintStream建立
			pout.print ("Greetings!");//傳PrintStream
			// Get the contents of our message as an array of bytes
			byte[] barray = bout.toByteArray();//byte array
			// Create a datagram packet, containing our byte array
			DatagramPacket packet = new DatagramPacket(barray, barray.length);//udp
			System.out.println ("Looking up hostname " + hostname );//顯示hostname
			// Lookup the specified hostname, and get an InetAddress
			InetAddress remote_addr = InetAddress.getByName(hostname);//建立address
			System.out.println ("Hostname resolved as " + remote_addr.getHostAddress());//顯示address
			// Address packet to sender
			packet.setAddress(remote_addr);//設定傳遞的addreas
			// Set port number to 2000
			packet.setPort(2000);//傳遞的port設定			
			// Send the packet - remember no guarantee of delivery
			//The final step, after all this work, is to send the packet. This is the easiest step of all�Xsimply
			//invoke the send method of DatagramSocket. Again, remember: there is no guarantee of
			//delivery, so it is possible for a packet to become lost in transit. A more robust application would
			//try to read an acknowledgment and resend the message if it had become lost.
			socket.send(packet);//傳封包
			System.out.println ("Packet sent!");//已傳送
		}
		catch (UnknownHostException uhe)//例外處理
		{
			System.err.println ("Can't find host " + hostname);
		}
		catch (IOException ioe)//例外處理
		{
			System.err.println ("Error - " + ioe);
		}
	}
}