package FILE15;
import java.io.*;
import java.net.*;

public class Client15 {
  int i, flag, messagein;
  Socket socket;
  static String servername;
  static int port;
  DataOutputStream  outstream;
  DataInputStream  instream;
  
  public Client15() {
     try{
         socket=new Socket(InetAddress.getByName(servername),port);
         outstream = new DataOutputStream(socket.getOutputStream());
         instream = new DataInputStream(socket.getInputStream());
 
         InputStreamReader isr = new InputStreamReader(System.in);
         BufferedReader br = new BufferedReader(isr);
         System.out.println("Input data from keyboard...");

         while(true) {
             i = br.read();
             outstream.writeInt(i);
             if (i == 64) {
                flag = 1;
             }

             while (flag == 1) {
               messagein = instream.readInt();
               System.out.print((char)messagein);
               if (messagein == 64) {
                  flag = 0;
               System.out.println(" ");
               }
             }
         }
      }

      catch(IOException e){
         System.out.println("IOException when connecting Server!");
      }
  }

  public static void main(String args[]) {
      //if (args.length < 2){
      //   System.out.println("USAGE: java Client15 [servername] [port]");
      //   System.exit(1);
      //}

      //servername= args[0];
	  servername = "localhost";
	  port = 222;
      //port=Integer.parseInt(args[1]);
      Client15 ClientStart=new Client15();
  }
}
