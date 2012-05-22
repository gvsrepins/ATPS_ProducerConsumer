package atps_programacaoconcorrente;

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

      try {
        Thread.sleep((int) Math.random() * 100); // sleep for a randomly chosen time
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

    } while (!this.buffer.isEmpty());
  }
}