package Lab10;
//import javax.xml.bind.DatatypeConverter;

public class JoinDigestUserInterface {
  
  public static void main(String[] args) {

    ReturnDigest[] digestThreads = new ReturnDigest[args.length];
  
    for (int i = 0; i < args.length; i++) {
      // Calculate the digest
      digestThreads[i] = new ReturnDigest(args[i]);
      digestThreads[i].start();
    }
  
    for (int i = 0; i < args.length; i++) {
      try {      
        digestThreads[i].join();
        // Now print the result
        StringBuffer result = new StringBuffer(args[i]);
        result.append(": ");
        byte[] digest = digestThreads[i].getDigest();
        String s=new String(digest);//改這行
        //result.append(DatatypeConverter.printHexBinary(digest)); 
        result.append(digest);//改這行
        System.out.println(result);
      } catch (InterruptedException ex) {
        System.err.println("Thread Interrupted before completion");
      }  
    }     
  }
}