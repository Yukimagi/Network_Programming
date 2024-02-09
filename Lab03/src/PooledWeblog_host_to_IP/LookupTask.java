package PooledWeblog_host_to_IP;

import java.net.*; // 匯入 java.net 套件，提供網路相關的功能
import java.util.concurrent.Callable; // 匯入 java.util.concurrent 中的 Callable 介面

public class LookupTask implements Callable<String> {// 定義 LookupTask 類別實作 Callable

  private String host;// 宣告私有字串變數 host
  
  public LookupTask(String host) {//$***Type要一致(// LookupTask 建構子)
    this.host = host;//賦值
  }
  //part2主要改以下程式
  @Override
  public String call() {//$***Type要一致，// 實作 Callable 的call()
    try {//try catch事件
    	InetAddress address = InetAddress.getByName(host);//取得指定的主機位址
        return host + " " + address.getHostAddress();//輸出host 透過主機獲得ip address
    } catch (Exception ex) {//catch例外情況
    	return host + " Unknown Host";//輸出例外情況

    }
  }
}
