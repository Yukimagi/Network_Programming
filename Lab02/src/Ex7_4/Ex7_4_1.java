package Ex7_4;
import java.io.*;
public class Ex7_4_1 {

	public static void main(String[] args) throws IOException 
	{	
		//p1kbd2scr();
		//p2bufkbd2scr();
		//p3kbd2file();
		//p4file2scr("t.txt");		
		//p5file2writer("t.txt");		
	}	
	
	public static void DataOutS()
	{		
		try (FileOutputStream fin = new FileOutputStream("a.txt");
				DataOutputStream dataWrite = new DataOutputStream(fin))
		{
			short sh[] = {1,2,3,4,5};
			for(short i : sh)
			{
				dataWrite.writeShort(i);//�o�̪���k�n�f�t��ƫ��A�ϥ�
			}		
		} 
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	public static void DataInS()
	{
		try(FileInputStream fin = new FileInputStream("a.txt");
				DataInputStream dataWrite = new DataInputStream(fin)
		   ) 
		{			
			short sh[] = {1,2,3,4,5};
			for(short i : sh)
			{
				System.out.println(i);
			}
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	
	
		
	public static void generateCharacters(OutputStream out) throws IOException 
	{
		int firstPrintableCharacter = 33;
		int numberOfPrintableCharacters = 94;
		int numberOfCharactersPerLine = 72;
		int start = firstPrintableCharacter;
		while (true)
		{ /* infinite loop */
			for (int i = start; i < start + numberOfCharactersPerLine; i++) 
			{
				out.write(((i - firstPrintableCharacter) % numberOfPrintableCharacters) + firstPrintableCharacter);
			}
			out.write('\r'); // carriage return
			out.write('\n'); // linefeed
			start = ((start + 1) - firstPrintableCharacter) % numberOfPrintableCharacters + firstPrintableCharacter;
		}
	}
	
	public static void generateCharacters2(OutputStream out) throws IOException 
	{
		int firstPrintableCharacter = 33;
		int numberOfPrintableCharacters = 94;
		int numberOfCharactersPerLine = 72;
		int start = firstPrintableCharacter;
		byte[] line = new byte[numberOfCharactersPerLine + 2];
		// the +2 is for the carriage return and linefeed
		while (true)
		{ /* infinite loop */
			for (int i = start; i < start + numberOfCharactersPerLine; i++) 
			{
				line[i - start] = (byte) ((i - firstPrintableCharacter)	% numberOfPrintableCharacters + firstPrintableCharacter);
			}
			line[72] = (byte) '\r'; // carriage return
			line[73] = (byte) '\n'; // line feed
			out.write(line);
			start = ((start + 1) - firstPrintableCharacter)	% numberOfPrintableCharacters + firstPrintableCharacter;
		}
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

    public static void p2bufkbd2scr()
    {
        String linebuf;

        try
        {
        	BufferedReader stdin = new BufferedReader(
				      new InputStreamReader(System.in));
        	
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
        	try{
        		outfile.close();
        	} 
        	catch(Exception ex){
        		System.err.println(ex.getMessage());
        	}
       } 
       catch(Exception ex) {
			System.err.println(ex.getMessage());
       }
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
	


    public static void p5file2writer(String filename) {   	
        try (BufferedReader infile = new BufferedReader(
				      new InputStreamReader(
				      new FileInputStream(filename), "unicode"));
        	 PrintWriter outfile = new PrintWriter(
		     		      new BufferedWriter(
					new OutputStreamWriter(System.out, "big5")))
        	)
        {
        	String	linebuf;

        	while((linebuf = infile.readLine()) != null) {
        		outfile.println(linebuf);
        	}
        } 
        catch (Exception ex) {
        	System.err.println(ex.getMessage());
		}
    }

}
