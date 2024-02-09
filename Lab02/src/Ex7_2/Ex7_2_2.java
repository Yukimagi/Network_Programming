package Ex7_2;
import java.io.*;
public class Ex7_2_2 {

	public static void main(String args[])throws Exception
	   {
		/*  
	    if (args.length < 1)
	    {
	    	System.out.println("Usage: java Ex7_2_2 [fileName]");
	    	System.exit(1);
		}
		*/  
		//String fileName = args[0];
		  String fileName = "testoutFile.txt";  
		FileOutputStream fos=new FileOutputStream(fileName);

		String line = "Test for FileOutputStream �������";

	        fos.write(line.getBytes());
		fos.close();
	   }

}
