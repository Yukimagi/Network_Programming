package Lab02;
import java.io.*;
public class Lab02 {

	public static void main(String[] args) throws IOException 
	{	
		//p1kbd2scr();
		//p2bufkbd2scr();
		//p3kbd2file();
		//p4file2scr("lab02unicode.txt");		
		//p4file2scr("lab02big5.txt");
		//p5file2writer("lab02unicode.txt");		
		p5file2writer("lab02big5.txt");	
	}	
	
	public static void DataOutS()
	{		
		try (FileOutputStream fin = new FileOutputStream("a.txt");
				DataOutputStream dataWrite = new DataOutputStream(fin))
		{
			short sh[] = {1,2,3,4,5};
			for(short i : sh)
			{
				dataWrite.writeShort(i);//這裡的方法要搭配資料型態使用
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
        byte[]	linebuf = new byte[1024]; // 定義一個長度1024的位元組陣列，用來存儲輸人的資料
        int	n;//變數n 

        try //try catch中判定
        {        	
        	System.out.println("Please input a string");//輸人字串 
            while((n = System.in.read(linebuf)) >= 0) //輸人流中讀取資料到linebuf中，並返回讀取的位元組數量n 
            {
            	 // 將讀取的資料寫出到標準輸出流 
            	System.out.write(linebuf, 0, n);
            }
        } 
        catch(Exception ex) //抓到例外情況
        {
        	// 顯示任何例外情況
        	System.err.println(ex.getMessage());
        }
    }

    public static void p2bufkbd2scr()
    {
        String linebuf;// 定義字串變數用來儲存每次輸人的字串 

        try// try catch判斷
        {
        	// 建立一個BufferedReader物件，用於讀取．系統標準輸人 
        	BufferedReader stdin = new BufferedReader(
				      new InputStreamReader(System.in));
        	
        	 // while讀取迥圈，從標準輸人讀取一行字串，直到輸人為null（即按下Enter)為止 
            while((linebuf = stdin.readLine()) != null)
            {
            	// 將讀取的字串輸出到標準輸出 
                System.out.println(linebuf);
            }
        } 
        catch(Exception ex)// 例外情況
        {
        	// 捕捉並顯示任何例外異常消息
        	System.err.println(ex.getMessage());
        }
    }

    public static void p3kbd2file() 
    {
        byte[]	linebuf = new byte[1024];// 定義一個長度為1024的位元組陣列，用來存儲輸人的資料        
        int	n;//變數n 
        
      //try catch中判定（並建立一個FileOutputStream物件，用於寫人到out - txt檔案） 
        try(FileOutputStream outfile = new FileOutputStream("out.txt"))
        {
        	try{//try catch中判定 
        		
        		//while輸人流中讀取資料到linebuf中，並返回讀取的位元組數量n
        		while((n = System.in.read(linebuf)) >= 0){
        			outfile.write(linebuf, 0, n);// 將讀取的資料寫人到檔案
        		}
        	} 
        	catch(Exception ex){//catch例外
        		System.err.println(ex.getMessage());// 捕捉並顯示任何例外異常消息
        	}
        	try{//try catch中判定 
        		outfile.close();//關檔
        	} 
        	catch(Exception ex){//catch例外
        		System.err.println(ex.getMessage());// 捕捉並顯示任何例外異常消息
        	}
       } 
       catch(Exception ex) {//catch��嚙賣�哨蕭
			System.err.println(ex.getMessage());// 捕捉並顯示任何例外異常消息
       }
    }    
	
	public static void p4file2scr(String filename)
	{// 檔案內容以Big5編碼輸出到螢幕
        try(FileInputStream infile = new FileInputStream(filename))
        {//try catch建立新的FileInputStream
        	try //try catch事件
        	{
        		int b, n = 0;//變數定義
        		// 逐字節讀取檔案內容並以Big5編碼輸出到螢幕
                while((b = infile.read()) >= 0) 
                {
                	System.out.write(b);//以Big5編碼輸出到螢幕 
                	n++;//不斷更新有幾字
                }
                System.out.println("Total: " + n + " Bytes");//輸出有幾字
        	} 
        	catch(Exception ex) //捕捉例外情況
        	{
        		System.err.println(ex.getMessage());//// 捕捉並顯示任何例外異常消息
        	}
	   
        	try //try catch是鍵
        	{
        		infile.close();// 關閉檔案輸人流 
        	} 
        	catch(Exception ex) //捕捉例外情況 
        	{
        		// 捕捉並顯示關閉檔案輸人流時的例外異常消息 
        		System.err.println(ex.getMessage());
        	}
        } 
        catch(Exception ex)//捕捉例外情況 
        {
        	// 捕捉並顯示建立FileInputStream時的例外異常消息
        	System.err.println(ex.getMessage());
        } 
    }
	


	public static void p5file2writer(String filename) {   
	    // 寫人Big5編碼的檔案 

	    try (BufferedReader infile = new BufferedReader(//try catch事件 
	            new InputStreamReader(
	                    new FileInputStream(filename), "unicode")); // 讀取Unicode編碼的檔案 
	         PrintWriter outfile = new PrintWriter(
	                 new BufferedWriter(
	                         new OutputStreamWriter(System.out, "big5"))); // 寫人Big5編碼的檔案 
	    )
	    {
	        String linebuf;//定義變數 

	        // 逐行讀取檔案並寫人到Big5編碼的檔案
	        while((linebuf = infile.readLine()) != null) {
	            outfile.println(linebuf);//輸出 
	        }
	    } 
	    catch (Exception ex) {//有例外情況 
	        // 捕捉並顯示任何例外異常消息
	        System.err.println(ex.getMessage());
	    }
	}
}
