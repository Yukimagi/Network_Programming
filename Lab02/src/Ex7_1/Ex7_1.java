package Ex7_1;
import java.io.File;
import java.util.Date;
public class Ex7_1 {

	public static void main(String args[]) throws java.io.IOException
    { 
     /*if (args.length < 1)
	  {
	   System.out.println("Usage: java Ex7_1 [fileName]");
	   System.exit(1);
	  }*/
	  
     //String fileName = args[0];
     String fileName = "testFile.txt";
     File file = new File(fileName); 
     
     System.out.println("The File you point:");
     System.out.println("  Exists: " + file.exists());
     System.out.println("  File Name: " + file.getName());
     System.out.println("  Canonical File Name: " + file.getCanonicalFile());
     System.out.println("  canWritable: " + file.canWrite());
     System.out.println("  Length: " + file.length());
     System.out.println("  Hidden: " + file.isHidden());
     System.out.println("  isFile: " + file.isFile());
     System.out.println("  isDirectory: " + file.isDirectory());
     System.out.println("  LastModified: " + new Date(file.lastModified()));
    }

}
