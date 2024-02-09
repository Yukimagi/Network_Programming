package FILE18_1;
import java.awt.*;

class Ex18_1 extends Frame {
  
  public Ex18_1() {
     super("Server/Client");

     add(new TextField("ChatRecord"), BorderLayout.CENTER);
     add(new TextField("ChatInput"), BorderLayout.SOUTH);

     setSize(500, 350);
     show();
  }

  public static void main(String args[]) {
     Ex18_1 ProgramStart=new Ex18_1();
  }
}

