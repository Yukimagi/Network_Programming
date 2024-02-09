package Ex7_3;
import java.io.*;
import java.util.Date;
public class Ex7_3_2 {

	public static void main(String args[])throws Exception
	{
		/*
		if (args.length == 0)
		{
			System.out.println("Usage: java Ex7_3_2 [fileName]");
			System.exit(1);
		}
		*/	  
		//String fileName = args[0];
		String fileName = "testoutFile.txt";
		FileOutputStream fos=new FileOutputStream(fileName);
		DataOutputStream dos = new DataOutputStream(fos);

		String line= "Test for DataOutputStream  �������";
		dos.write(line.getBytes());
		dos.close();
	}

}
