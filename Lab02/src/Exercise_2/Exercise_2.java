package Exercise_2;
import java.io.*;

public class Exercise_2 {

	public static void main(String[] args) throws IOException 
	{
		Exercise_2.p2bufkbd2scr();	
	}	
		
	public static void p2bufkbd2scr()
    {
        String linebuf;

        try
        {
        	BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
        	
            while((linebuf = stdin.readLine()) != null)
            {
                System.out.println(linebuf);
            }
        } 
        catch(Exception ex)
        {
        	System.err.println(ex.getMessage());
        }
    }    

}
