package PooledWeblog;// 宣告 PooledWeblog 套件

import java.net.*; // 匯入 java.net 套件，提供網路相關的功能
import java.util.concurrent.Callable; // 匯入 java.util.concurrent 中的 Callable 介面

public class LookupTask implements Callable<String> {// 定義 LookupTask 類別實作 Callable

  private String line;// 宣告私有字串變數 line
  
  public LookupTask(String line) {//$***Type要一致(// LookupTask 建構子)
    this.line = line;//賦值
  }
  
  @Override
  public String call() {//$***Type要一致，// 實作 Callable 的call()
    try {
      // separate out the IP address(// 分離 IP 位址)
      int index = line.indexOf(' ');//$回傳第一個空白的index(位置)
      String address = line.substring(0, index);//$抓出從0到index-1的位置的字串，也就是ip address
      String theRest = line.substring(index);//$抓出index之後的字串<取得除 IP 位址外的其餘部分>
      String hostname = InetAddress.getByName(address).getHostName();//// 透過 IP 位址取得主機名稱
      return hostname + " " + theRest;// 回傳主機名稱 + 其餘部分
    } catch (Exception ex) {//catch例外情況
      return line;// 回傳原始字串
    }
  }
}
