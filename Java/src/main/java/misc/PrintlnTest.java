package misc;

public class PrintlnTest {
  public static void main(String[] args) {
    System.out.print("Start[          ]");
    System.out.flush(); // the flush method prints it to the screen

    // 11 '\b' chars: 1 for the ']', the rest are for the spaces
    System.out.print("\b\b\b\b\b\b\b\b\b\b\b");
    System.out.flush();
    try {
      Thread.sleep(500); // just to make it easy to see the changes
      for (int i = 0; i < 10; i++) {
        System.out.print("."); // overwrites a space
        System.out.flush();
        Thread.sleep(250);
      }
      System.out.print("] Done\n"); // overwrites the ']' + adds chars
      System.out.flush();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}
