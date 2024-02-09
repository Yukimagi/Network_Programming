package InterfaceLister;// 宣告 InterfaceLister 套件
import java.net.*;// 匯入 java.net 套件，提供網路相關的功能(總集合套件)
import java.util.*;// 匯入 java.util 套件，提供常用的工具類別(總集合套件)
public class InterfaceLister {// 定義 InterfaceLister 類別

	//main function，如無法執行會拋出 SocketException 例外
	public static void main(String[] args) throws SocketException {
		// 取得所有網路介面的列舉
	    Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
	    //while迴圈，檢查是否還有下一個元素
	    while (interfaces.hasMoreElements()) {
	      // 取得下一個網路介面
	      NetworkInterface ni = interfaces.nextElement();
	      // 輸出上述取得之網路介面的資訊
	      System.out.println(ni);               
	    }  
	  }

}
