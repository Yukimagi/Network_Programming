package Lab09; // 套用Lab09套件

import java.net.*; // 匯入Java的網路相關套件
import java.io.*; // 匯入Java的輸入輸出相關套件

// Chapter 5, Listing 4
public class EchoClient2 // 定義EchoClient類別
{
	// UDP port to which service is bound
	public static final int SERVICE_PORT = 7; // 定義服務的UDP端口號
	// Max size of packet
	public static final int BUFSIZE = 256; // 定義封包的最大大小

	public static void main(String args[]) // 主程式進入點
	{
		if (false) // 如果條件成立（實際上永遠不會成立）
		{
			System.err.println("Syntax - java EchoClient hostname"); // 輸出語法錯誤訊息
			return;
		}

		String hostname = "127.0.0.1"; // 指定伺服器的主機名稱

		// Get an InetAddress for the specified hostname
		InetAddress addr = null; // 宣告InetAddress物件
		try
		{
			// Resolve the hostname to an InetAddr
			addr = InetAddress.getByName(hostname); // 透過主機名稱獲得InetAddress
		}
		catch (UnknownHostException uhe)
		{
			System.err.println("Unable to resolve host"); // 處理主機名稱解析失敗的例外
			return;
		}

		try
		{
			// Bind to any free port
			DatagramSocket socket = new DatagramSocket(); // 創建DatagramSocket物件
			// Set a timeout value of two seconds
			socket.setSoTimeout(2 * 1000); // 設定超時時間為2秒

			for (int i = 1; i <= 10; i++) // 迴圈執行10次
			{
				// Copy some data to our packet
				String message = "Client 2:A1105505 Packet number " + i; // 在訊息中包含學號
				char[] cArray = message.toCharArray(); // 將字串轉換成字元陣列
				byte[] sendbuf = new byte[cArray.length]; // 創建儲存字元陣列的byte陣列

				for (int offset = 0; offset < cArray.length; offset++)
				{
					sendbuf[offset] = (byte) cArray[offset]; // 複製字元陣列到byte陣列
				}

				// Create a packet to send to the UDP Server
				DatagramPacket sendPacket = new DatagramPacket(sendbuf, cArray.length, addr, SERVICE_PORT); // 創建要發送的封包
				System.out.println("Sending packet to " + hostname); // 輸出發送封包的訊息

				// Send the packet
				socket.send(sendPacket); // 發送封包
				System.out.print("Waiting for packet.... "); // 輸出等待封包的訊息

				// Create a small packet for receiving UDP packets
				byte[] recbuf = new byte[BUFSIZE]; // 創建儲存接收封包的byte陣列
				DatagramPacket receivePacket = new DatagramPacket(recbuf, BUFSIZE); // 創建接收封包的DatagramPacket

				// Declare a timeout flag
				boolean timeout = false; // 宣告超時旗標

				// Catch any InterruptedIOException that is thrown
				// while waiting to receive a UDP packet
				try
				{
					socket.receive(receivePacket); // 接收封包
				}
				catch (InterruptedIOException ioe)
				{
					timeout = true; // 處理接收超時的例外
				}

				if (!timeout) // 如果沒有超時
				{
					System.out.println("Packet received!"); // 輸出封包已接收的訊息
					System.out.println("Details: " + receivePacket.getAddress()); // 輸出封包的詳細資訊

					// Obtain a byte input stream to read the UDP packet
					ByteArrayInputStream bin = new ByteArrayInputStream(receivePacket.getData(), 0,
							receivePacket.getLength()); // 創建ByteArrayInputStream物件
					// Connect a reader for easier access
					BufferedReader reader = new BufferedReader(new InputStreamReader(bin)); // 連接BufferedReader物件以便更容易存取
					// Loop indefinitely
					for (;;)
					{
						String line = reader.readLine(); // 讀取每一行訊息
						// Check for end of data
						if (line == null)
							break; // 如果為null則跳出迴圈
						else
							System.out.println(line); // 輸出每一行訊息
					}
				}
				else
				{
					System.out.println("Packet lost!"); // 輸出封包遺失的訊息
				}

				// Sleep for a second, to allow the user to see the packet
				try
				{
					Thread.sleep(1000); // 暫停一秒，以便使用者能看到封包
				}
				catch (InterruptedException ie)
				{
				}
			}
		}
		catch (IOException ioe)
		{
			System.err.println("Socket error " + ioe); // 處理輸出入例外
		}
	}
}
