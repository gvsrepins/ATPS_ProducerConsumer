package atps_programacaoconcorrente;

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
    int bufferSize = 3;  //size of buffer

    Buffer buffer = Buffer.getInstance();
    buffer.setBufferSize(bufferSize);

    // create new threads
    for (int i = 1; i <= countThreads; i++) {

      Thread produtor = new Producer("Producer" + i);
      Thread consumer = new Consumer("Consumer" + i);


      // starting threads
      produtor.start();
      consumer.start();

      // Wait for the threads to finish
      try {
        produtor.join();
        consumer.join();
      } catch (InterruptedException e) {
        return;
      }
    }
  }
}