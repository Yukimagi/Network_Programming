package Ex7_2;
import java.io.*;
public class Ex7_2_1 {

	public static void main(String args[])throws Exception{
		int i;
		/*
		if (args.length < 1)
		{
		System.out.println("Usage: java Ex7_2_1 [fileName]");
		System.exit(1);
		}
		*/	  
		//String fileName = args[0];
		String fileName = "testFile.txt";
		FileInputStream fis=new FileInputStream(fileName);
		while((i=fis.read()) !=-1){
			System.out.print((char)i);
		}
	}

}
