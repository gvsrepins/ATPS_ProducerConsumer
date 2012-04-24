package atps_programacaoconcorrente;

//import java.io.File;
//import java.io.FileOutputStream;
//import java.io.PrintStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author gvsrepins
 */
public class Consumer extends Thread {

  protected Buffer buffer;

  /**
   * 
   * @param str 
   */
  public Consumer(String str, Buffer buffer) {
    super(str);
    this.buffer = buffer;
  }

  /**
   * Executed code
   */
  public void run() {

    int[] contents = this.buffer.getContents();

    for (int i = 0; i < contents.length; i++) {

      if (contents[i] > 0) {

        //sets date format
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        //get current date time with Date()
        Date dateBegin = new Date();
        String begin = dateFormat.format(dateBegin);


        try {
          this.buffer.erase(i);
          sleep(1000); //sleep((int) (Math.random() * 1000));

          Date dateEnd = new Date();
          String end = dateFormat.format(dateEnd);

          //FileOutputStream out = new FileOutputStream(new File("output.txt", true));
          //out.write("A new line of text");
          //out.close();

          System.out.print(getName() + " erase Buffer on positon " + (i + 1) + ". ");
          System.out.print("From: " + begin + ". ");
          System.out.println("To: " + end + ". ");


        } catch (InterruptedException e) {
          System.out.println(e.toString());
        }
      }
    }
    System.out.println("DONE! " + getName());
  }
}
