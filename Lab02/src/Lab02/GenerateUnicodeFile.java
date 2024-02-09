package Lab02;
import java.io.*;

public class GenerateUnicodeFile {
	
	public static void main(String[] args) {
        String str = "A1105505 產生一個 Unicode  file"; // 要寫入的字串

        // 指定要寫入的檔案路徑
        String filePath = "lab02unicode.txt";

        // 寫入字串到檔案
        writeStringToUnicodeFile(str, filePath);

        System.out.println("字串已成功寫入檔案: " + filePath);
    }
	
	
	public static void writeStringToUnicodeFile(String str, String filePath) {
        try (FileOutputStream fos = new FileOutputStream(filePath);
             OutputStreamWriter osw = new OutputStreamWriter(fos, "unicode")) {

            // 寫入Unicode編碼的BOM (Byte Order Mark)
            // BOM用來指示字節順序 (little-endian or big-endian)
            fos.write(new byte[]{(byte) 0xFE, (byte) 0xFF});

            // 寫入字串
            osw.write(str);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	/*String strText = "A1105505產生一個Unicode文件";
    String strFilePath = "lab02unicode.txt";
	//strFilePath：文件名，strText：要是写入的内容
	public static boolean writefile(String strFilePath,String strText)
	{
	FileOutputStream fos = null;
	BufferedOutputStream osw = null;
	File file =new File(strFilePath);
	try
	{
	fos = new FileOutputStream(file);
	osw = new BufferedOutputStream(fos);
	byte[] bom={-1, -2};  
	osw.write(bom);  
	osw.write(strText.getBytes("UTF-16LE"));
	osw.flush();
	fos.close();
	osw.close();
	}
	catch(Exception e) {
		e.printStackTrace();
	}
	return true;
	}*/
	
}

