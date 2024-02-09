package Lab09;
import java.net.*;
import java.io.*;
// Chapter 5, Listing 3
public class EchoServer
{
	// UDP port to which service is bound
	public static final int SERVICE_PORT = 7;
	// Max size of packet, large enough for almost any client
	public static final int BUFSIZE = 4096;
	// Socket used for reading and writing UDP packets
	private DatagramSocket socket;
	public EchoServer()
	{
		try//try
		{
			// Bind to the specified UDP port, to listen for incoming data packets
			socket = new DatagramSocket( SERVICE_PORT );
			//輸出訊息
			System.out.println ("Server active on port " + socket.getLocalPort());
		}
		catch (Exception e)//例外處理
		{
			System.err.println ("Unable to bind port");
		}
	}
	public void serviceClients()
	{
		// Create a buffer large enough for incoming packets
		byte[] buffer = new byte[BUFSIZE];
		for (;;)
		{
			try
			{
				// Create a DatagramPacket for reading UDP packets
				DatagramPacket packet = new DatagramPacket (buffer, BUFSIZE);
				// Receive incoming packets
				socket.receive(packet);
				//印出收到的packet資訊
				System.out.println ("Packet received from " + packet.getAddress() + ":" + packet.getPort() + " of length " + packet.getLength() );
				
				
				// 在伺服器端顯示收到的訊息，並加上學號
                ByteArrayInputStream bin = new ByteArrayInputStream(packet.getData(), 0, packet.getLength());//建立byte array
                BufferedReader reader = new BufferedReader(new InputStreamReader(bin));//建立buffer reader
                String line = reader.readLine();//讀入資訊
                System.out.println("Sent by: " + packet.getAddress().getHostAddress());//顯示packet資訊
                System.out.println("Student ID: " + line);//顯示packet內容
                System.out.println();//換行
				
             // Echo the packet back - address and port are already set for us !
				socket.send(packet);
			}
			catch (IOException ioe)//例外處理
			{
				System.err.println ("Error : " + ioe);
			}
		}
	}
	public static void main(String args[])
	{
		EchoServer server = new EchoServer();//main中執行EchoServer
		server.serviceClients();//建立
	}
}