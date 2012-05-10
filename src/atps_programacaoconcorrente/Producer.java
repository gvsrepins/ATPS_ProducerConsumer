package atps_programacaoconcorrente;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author gvsrepins
 */
public class Producer extends Thread {

  private Buffer buffer;

  public Producer(String str) {
    super(str);
    this.buffer = Buffer.getInstance();
  }

  public void run() {
    int i = 0;
    while (!this.buffer.isFull()) {
      try {
        //starting from 1, not 0
        this.buffer.put(i + 1);
      } catch (Exception e) {
        return;
      }

      //sets a dateFormat
      DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
      //get current date time and past to a String
      Date dateBegin = new Date();
      Date dateEnd = new Date();
      String begin = dateFormat.format(dateBegin);

      try {
        //sleep 500 miliseconds
        Thread.sleep(5000);
      } catch (InterruptedException e) {
        return;
      }

      String line = "\n" + "-----------------------------------------------------" + "\n";
      String bufferUsed = "\nBuffer used: " + this.buffer.container.size();

      //get current date time and past to a String
      String end = dateFormat.format(dateEnd);
      System.out.print(getName() + " put... " + (i + 1)
              + " From: " + begin
              + " To: " + end
              + bufferUsed
              + line);

      i++;
    }
  }
}