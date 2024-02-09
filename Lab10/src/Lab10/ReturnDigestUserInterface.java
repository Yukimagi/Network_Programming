package Lab10;
//import javax.xml.bind.*; // for DatatypeConverter

public class ReturnDigestUserInterface {
  
  public static void main(String[] args) {
    for (String filename : args) {
      // Calculate the digest
      ReturnDigest dr = new ReturnDigest(filename);
      dr.start();
      
      // Now print the result
      StringBuilder result = new StringBuilder(filename);
      result.append(": ");
      byte[] digest = dr.getDigest();
      String s=new String(digest);//改這行
      //result.append(DatatypeConverter.printHexBinary(digest));
      result.append(digest);//改這行
      System.out.println(result); 
    }
  }
}