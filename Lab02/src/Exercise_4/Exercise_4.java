package Exercise_4;
import java.io.*;
public class Exercise_4 {

	public static void main(String[] args) throws IOException 
	{
		Exercise_4.p4file2scr("t.txt");	
	}	
		
	public static void p4file2scr(String filename)
	{
        try(FileInputStream infile = new FileInputStream(filename))
        {
        	try 
        	{
        		int b, n = 0;

                while((b = infile.read()) >= 0) 
                {
                	System.out.write(b);
                	n++;
                }
                System.out.println("Total: " + n + " Bytes");
        	} 
        	catch(Exception ex) 
        	{
        		System.err.println(ex.getMessage());
        	}
	   
        	try 
        	{
        		infile.close();
        	} 
        	catch(Exception ex) 
        	{
        		System.err.println(ex.getMessage());
        	}
        } 
        catch(Exception ex) 
        {
        	System.err.println(ex.getMessage());
        } 
    }

}
