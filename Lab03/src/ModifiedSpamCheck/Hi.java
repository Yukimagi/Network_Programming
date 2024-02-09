package ModifiedSpamCheck;

import java.net.*;

public class Hi {
  
  public static final String BLACKHOLE = "sbl.spamhaus.org";

  public static void main(String[] args) throws UnknownHostException {
    for (String arg: args) {
    	
    	InetAddress[] addresses = InetAddress.getAllByName(arg);
    	for (InetAddress addr : addresses) {
    		if (isSpammer(addr)) {
    			System.out.println(addr + " is a known spammer.");
    		} else {
    			System.out.println(addr + " appears legitimate.");
    		}
    	}     
    }
  }

  private static boolean isSpammer(InetAddress arg) {
    try {
      //InetAddress address = InetAddress.getByName(arg);
      byte[] quad = arg.getAddress();
      String query = BLACKHOLE;
      for (byte octet : quad) {
        int unsignedByte = octet < 0 ? octet + 256 : octet;
        query = unsignedByte + "." + query;
      }
      InetAddress.getByName(query);
      return true;
    } catch (UnknownHostException e) {
      return false;
    }
  }
}
