package PTT;
import java.io.*;
import java.net.*;
public class PTT {

	public static void main(String[] args) 
	{
		try 
		{
			Socket ptt = new Socket("ptt.cc",23);
			InputStream in = ptt.getInputStream();	
			int data = 0;
			for (int i=0;i<10;i++)
			{
				data = in.read();
				System.out.print(data + " ");
			}
			in.close();
			ptt.close();
		}
		catch(UnknownHostException e)
		{
			System.out.println("主機連線失敗");
		}
		catch(IOException e)
		{
			System.out.println("傳輸失敗");
		}		
	}		
}
