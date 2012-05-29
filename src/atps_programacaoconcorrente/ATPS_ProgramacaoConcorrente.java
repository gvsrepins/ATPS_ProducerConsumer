package atps_programacaoconcorrente;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 
 * @author gvsrepins
 */
public class ATPS_ProgramacaoConcorrente {

  /**
   * @param args the command line arguments
   */
  public static void main(String[] args) {
    int countThreads = 1; //quantity of threads to be generate
    int bufferSize = 5000;  //size of buffer

    Buffer buffer = Buffer.getInstance();
    buffer.setBufferSize(bufferSize);

    // create new threads
    for (int i = 1; i <= countThreads; i++) {
      // starting threads
      //new Producer("Producer" + i).start();
      //new Consumer("Consumer" + i).start();

      Producer produtor = new Producer("Producer" + i);
      Consumer consumer = new Consumer("Consumer" + i);

      ExecutorService test = Executors.newFixedThreadPool(2);

      // Wait for the threads to finish
      //produtor.start();
      //consumer.start();

      try {
        test.execute(produtor);
        test.execute(consumer);
        //produtor.join();
        //consumer.join();
      } catch (Exception e) {
        return;
      }
    }
  }
}