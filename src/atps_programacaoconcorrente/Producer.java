package atps_programacaoconcorrente;

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
    int i = 1;
    while (!this.buffer.isFull()) {


      try {
        Thread.sleep(500); // sleep for a randomly chosen time
      } catch (InterruptedException e) {
        return;
      }

      try {
        //starting from 1, not 0
        this.buffer.put(i);
      } catch (Exception e) {
        return;
      }
      i++;


      //wait consumer finish
      while (this.buffer.isFull()) {
        try {
          System.out.print("Producer waiting... \n");
          Thread.sleep(100);
        } catch (InterruptedException e) {
          return;
        }
      }

    }
  }
}