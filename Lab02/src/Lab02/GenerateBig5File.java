package Lab02;

import java.io.*;
import java.nio.charset.Charset;

public class GenerateBig5File {

	public static void main(String[] args) {
        // 源字串
		String str = "A1105505 產生一個 big5  file";
        // 目標文件名稱
		String filePath =  "lab02big5.txt";
		// 寫入字串到檔案
        writeStringToBig5File(str, filePath);

        System.out.println("字串已成功寫入檔案: " + filePath);
    }

	public static void writeStringToBig5File(String str, String filePath) {
        try (FileOutputStream fos = new FileOutputStream(filePath);
             OutputStreamWriter osw = new OutputStreamWriter(fos, Charset.forName("Big5"))) {

            // 寫入字串
            osw.write(str);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
