package Exercise_3;
import java.io.*;
public class Exercise_3 {

	public static void main(String[] args) throws IOException 
	{
		p3kbd2file();		
	}

	public static void p3kbd2file() 
    {
        byte[]	linebuf = new byte[1024];        
        int	n;        
        try(FileOutputStream outfile = new FileOutputStream("out.txt")) 
        {
        	try{	    	
        		while((n = System.in.read(linebuf)) >= 0){
        			outfile.write(linebuf, 0, n);
        		}
        	} 
        	catch(Exception ex){
        		System.err.println(ex.getMessage());
        	}
        	if(outfile != null){
        		try{
        			outfile.close();
        		}	 
        		catch(Exception ex){
        			System.err.println(ex.getMessage());
        		}
        	}
       } 
       catch(Exception ex) {
			System.err.println(ex.getMessage());
       }
    }   

}
