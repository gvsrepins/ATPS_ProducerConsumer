package atps_programacaoconcorrente;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 
 * @author gvsrepins
 */
public class Consumer extends Thread implements Runnable {

  private Buffer buffer;
  private int countConsumedItens = 0;

  public Consumer(String str) {
    super(str);
    this.buffer = Buffer.getInstance();
  }

  public void run() {
    int item;
    
    do {
      try {
        item = this.buffer.get();
      } catch (InterruptedException e) {
        return;
      }

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

      //wait producer finish
      while (this.buffer.isEmpty()) {
        try {
          System.out.print("Consumer  waiting... \n");
          Thread.sleep(100);
        } catch (InterruptedException e) {
          return;
        }
      }

      String line = "\n" + "-----------------------------------------------------" + "\n";
      String bufferUsed = "\nBuffer used: " + this.buffer.container.size();
      //get current date time and past to a String
      Date dateEnd = new Date();
      String end = dateFormat.format(dateEnd);
      String strConsumedCount = "\nConsumed: " + (++this.buffer.consumedCount);
      
      System.out.print("Consumer" + " got... "
              + (item)
              + " From: " + begin + "."
              + " To: " + end
              + bufferUsed
              + strConsumedCount
              + line);

    } while (!this.buffer.isEmpty());
  }
}