package Exercise_1;/* infinite loop (用於需要不斷執行的網路連結，直到client close connection*/
import java.io.*;
public class Exercise_1 {

	public static void main(String[] args) throws IOException 
	{
		FileOutputStream outfile = new FileOutputStream("number2.txt");
		Exercise_1.generateCharacters(outfile);		
		Exercise_1.generateCharacters2(outfile);		
		outfile.close();		
	}	
		
	public static void generateCharacters(OutputStream out) throws IOException //byte-by-byte
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
	
	public static void generateCharacters2(OutputStream out) throws IOException //line-by-line
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

}
