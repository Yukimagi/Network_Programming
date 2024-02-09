package IPCharacteristics;  // 宣告 IPCharacteristics 套件

import java.net.*;  // 匯入 java.net 套件，提供網路相關的功能(集合套件)

public class IPCharacteristics {  // 定義 IPCharacteristics 類別

  public static void main(String[] args) {  
	  //ppt p.36 (我依照ppt並使用run configurations)  
  	//Try the following websites www.csie.nuk.edu.tw 
      //getByName(): ask a DNSserver to lookup the name and the numeric address
    try {
      InetAddress address = InetAddress.getByName(args[0]);  //透過參數(使用run configuration)
      														 //取得指定的主機位址
      if (address.isAnyLocalAddress()) {// 判斷是否為通配位址(wildcard address)
        System.out.println(address + " is a wildcard address."); //是則輸出  
      }
      if (address.isLoopbackAddress()) {// 判斷是否為回環位址(loopback address)
        System.out.println(address + " is loopback address.");  //是則輸出 
      }

      if (address.isLinkLocalAddress()) {// 判斷是否為連線本地位址(link-local address)
        System.out.println(address + " is a link-local address.");  //是則輸出
      } else if (address.isSiteLocalAddress()) {// 判斷是否為站點本地位址(site-local address)
        System.out.println(address + " is a site-local address.");  //是則輸出 
      } else {// 判斷是否為全球位址(global address)
        System.out.println(address + " is a global address.");  // 判斷是否為全球位址
      }

      if (address.isMulticastAddress()) {//判斷是否為多播位址
        if (address.isMCGlobal()) {// 判斷是否為全域多播位址
          System.out.println(address + " is a global multicast address.");  //是則輸出 
        } else if (address.isMCOrgLocal()) {// 判斷是否為組織範圍多播位址
          System.out.println(address + " is an organization wide multicast address.");  //是則輸出 
        } else if (address.isMCSiteLocal()) {// 判斷是否為站點範圍多播位址
          System.out.println(address + " is a site wide multicast address.");  //是則輸出
        } else if (address.isMCLinkLocal()) {// 判斷是否為子網路範圍多播位址
          System.out.println(address + " is a subnet wide multicast address.");   //是則輸出
        } else if (address.isMCNodeLocal()) {// 判斷是否為介面範圍多播位址
          System.out.println(address + " is an interface-local multicast address.");  //是則輸出
        } else {// 未知的多播位址類型
          System.out.println(address + " is an unknown multicast address type.");  // 未知則輸出
        }   
      } else {
        System.out.println(address + " is a unicast address.");  // 判斷是否為單播位址，是則輸出
      }
    } catch (UnknownHostException ex) { // 處理主機名稱解析失敗的例外
      System.err.println("Could not resolve " + args[0]);  // 印出例外
    }   
  }
}
