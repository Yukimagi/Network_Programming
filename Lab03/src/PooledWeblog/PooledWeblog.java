package PooledWeblog;// 宣告 PooledWeblog 套件

import java.io.*;// 匯入 java.io 套件，提供 I/O 功能
import java.util.*;// 匯入 java.util 套件，提供常用的工具類別
import java.util.concurrent.*;// 匯入 java.util.concurrent 套件，提供多執行緒支援

// Requires Java 7 for try-with-resources and multi-catch
public class PooledWeblog {// 定義 PooledWeblog 類別
  /*p.52~58(ppt)
   * Run PooledWeblog.java p.59(for part1) 
   * Input: lab03log.txt 
   * You should add class LookupTaskinto your project */
  
	/*part 2:lab03host.txt*/
  private final static int NUM_THREADS = 4;// 宣告常數，指定執行緒數量
  
  public static void main(String[] args) throws IOException {// 如有例外，拋出 IOException 例外
    ExecutorService executor = Executors.newFixedThreadPool(NUM_THREADS);// 建立固定大小的執行緒池
    Queue<LogEntry> results = new LinkedList<LogEntry>();//$Class Queue的型態為classLogEntry
    													 // 建立 Queue 來儲存 LogEntry 物件
    try (BufferedReader in = new BufferedReader(//try catch並建立in BufferedReader
      new InputStreamReader(new FileInputStream(args[0]), "UTF-8"));) {// 建立 BufferedReader 讀取檔案
      for (String entry = in.readLine(); entry != null; entry = in.readLine()) {// 逐行讀取檔案
        //$ 1.Class Queue的型態為classLogEntry(下兩行)
    	LookupTask task = new LookupTask(entry);// 建立 LookupTask 物件
        Future<String> future = executor.submit(task);// 提交執行緒至執行緒池，取得 Future 物件
        //$ 2. Add callback to Queue(下兩行)
        LogEntry result = new LogEntry(entry, future);// 建立LogEntry 物件
        results.add(result);//將 LogEntry 物件加入 Queue
        /*•Future提供了get()方法
         * 讓我們可以等待Callable結束並獲取它的執行結果。
         * get()方法用來獲取執行結果，
         * 這個方法會產生阻塞，
         * 會㇐直等到任務執行完畢才返回；*/
      }
    }
    //(開始輸出結果，這會阻塞，直到結果準備好為止)
    // Start printing the results. This blocks each time a result isn't ready.
    for (LogEntry result : results) {//for迴圈// 迭代處理 Queue 中的 LogEntry 物件
      try {//try catch事件
        System.out.println(result.future.get());//$ Wait results in order(// 取得執行結果並輸出)
      } catch (InterruptedException | ExecutionException ex) {//catch例外事件
        System.out.println(result.original);//輸出例外事件
      }
    }

    executor.shutdown();// 關閉執行緒池
  }
  
  private static class LogEntry {// 定義 LogEntry 巢狀類別
    String original;//$ 存string
    Future<String> future;//$用來讀取該thread處理完的結果
    
    LogEntry(String original, Future<String> future) {// LogEntry 建構子
     this.original = original;//賦值
     this.future = future;//賦值
    }
  }
}
