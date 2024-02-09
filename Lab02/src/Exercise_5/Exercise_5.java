package Exercise_5;
import java.io.*;
public class Exercise_5 {

	public static void main(String[] args)
	{
		Exercise_5.p1kbd2scr();	
	}	
		
	public static void p1kbd2scr() 
	{
        byte[]	linebuf = new byte[1024]; 
        int	n;

        try 
        {       	
			System.out.println("Please input a string");
            while((n = System.in.read(linebuf)) >= 0) 
            {
            	System.out.write(linebuf, 0, n);
            }
        } 
        catch(Exception ex) 
        {
        	System.err.println(ex.getMessage());
        }
    }

}
