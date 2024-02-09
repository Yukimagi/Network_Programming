package FILE16;
import java.io.*;
import java.net.*;

public class Speaker16 {
  int i;
  Socket socket;
  static String servername;
  static int port;
  DataOutputStream  outstream;
  
  public Speaker16() {
     try{
         socket=new Socket(InetAddress.getByName(servername),port);
         outstream = new DataOutputStream(socket.getOutputStream());
 
         InputStreamReader isr = new InputStreamReader(System.in);
         BufferedReader br = new BufferedReader(isr);
         System.out.println("Input data from keyboard...");

         while(true) {
               i = br.read();
               outstream.writeInt(i);
         }
      }

      catch(IOException e){
         System.out.println("IOException when connecting Server!");
      }
  }

  public static void main(String args[]) {
      //if (args.length < 2){
      //   System.out.println("USAGE: java Speaker16 [servername] [port]");
      //   System.exit(1);
      //}

      //servername= args[0];
      //port=Integer.parseInt(args[1]);
      servername= "localhost";
	  port = 222;
      Speaker16 ClientStart=new Speaker16();
  }
}
