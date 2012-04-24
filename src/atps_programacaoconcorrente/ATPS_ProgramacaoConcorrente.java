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
    // TODO code application logic here
    int countThreads = 50;

    Buffer buffer = new Buffer(5000);
    buffer.populateBuffer();

    //execute Threads Consumer
    for (int i = 1; i <= countThreads; i++) {
      new Consumer("Thread " + i, buffer).start();
    }

  }
}
