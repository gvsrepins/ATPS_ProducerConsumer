package atps_programacaoconcorrente;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author gvsrepins
 */
public class Producer extends Thread implements Runnable {

  private Buffer buffer;

  public Producer(String str) {
    super(str);
    this.buffer = Buffer.getInstance();
  }

  public void run() {
    int item = 1;
    while (!this.buffer.isFull()) {

      //sets a dateFormat
      DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
      //get current date time and past to a String
      Date dateBegin = new Date();
      String begin = dateFormat.format(dateBegin);

      try {
        Thread.sleep(5000); // sleep for a randomly chosen time
      } catch (InterruptedException e) {
        return;
      }

      try {
        //starting from 1, not 0
        this.buffer.put(item);
      } catch (Exception e) {
        return;
      }

      //wait consumer finish
      while (this.buffer.isFull()) {
        try {
          System.out.print("Producer waiting... \n");
          Thread.sleep(100);
        } catch (InterruptedException e) {
          return;
        }
      }

      String line = "\n" + "-----------------------------------------------------" + "\n";
      String bufferUsed = "\nBuffer used: " + this.buffer.container.size();
      String strProducedCount = "\nProduced: " + (++this.buffer.producedCount);

      //get current date time and past to a String
      Date dateEnd = new Date();
      String end = dateFormat.format(dateEnd);
      System.out.print("Producer" + " put... " + (item)
              + " From: " + begin
              + " To: " + end
              + bufferUsed
              + strProducedCount
              + line);


      item++;
    }
  }
}