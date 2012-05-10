package atps_programacaoconcorrente;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 
 * @author gvsrepins
 */
public class Consumer extends Thread {

  private Buffer buffer;
  private int countConsumedItens = 0;

  public Consumer(String str) {
    super(str);
    this.buffer = Buffer.getInstance();
  }

  public void run() {
    int value;

    do {
      try {
        value = this.buffer.get();
      } catch (InterruptedException e) {
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

      countConsumedItens++;

      String line = "\n" + "-----------------------------------------------------" + "\n";
      String bufferUsed = "\nBuffer used: " + this.buffer.container.size();
      //get current date time and past to a String
      String end = dateFormat.format(dateEnd);
      System.out.print(getName() + " got... "
              + (countConsumedItens + 1)
              + " From: " + begin + "."
              + " To: " + end
              + bufferUsed
              + line);
      
      //wait producer finish
      while (this.buffer.isEmpty()) {
        try {
          Thread.sleep(1000);
        } catch (InterruptedException e) {
          return;
        }
      }

    } while (!this.buffer.isEmpty()); //|| countConsumedItens <= this.buffer.getBufferSize()
  }
}