package atps_programacaoconcorrente;

//import java.util.ArrayList;
import java.util.concurrent.ArrayBlockingQueue;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author gvsrepins
 */
class Buffer {

  //ArrayList<Integer> container = new ArrayList<Integer>();
  public static ArrayBlockingQueue<Integer> container;
  private int bufferSize;
  public static int producedCount = 0, consumedCount = 0;
  private static Buffer instance = new Buffer();

  /**
   * Implements a Singleton Pattern
   * Construct Private
   * @param bufferSize 
   */
  private Buffer() {
  }

  /**
   * Implements a Singleton Pattern - getInstance()
   * Is not necessary implements a Singleton pattern, but I have some troubles
   * and because this I supose to be because the threads are not sharing de same buffer, 
   * now I know that is not because this, however, to this study looks good for me 
   * to do with that way. :)
   * 
   * @return Buffer Instance
   */
  public static Buffer getInstance() {
    if (instance == null) {
      instance = new Buffer();
    }
    return instance;
  }

  /**
   * Put items on Buffer
   * @param int item
   * @throws InterruptedException 
   */
  public synchronized void put(int item) throws InterruptedException {

//    //sets a dateFormat
//    DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
//    //get current date time and past to a String
//    Date dateBegin = new Date();
//    String begin = dateFormat.format(dateBegin);

    //wait till the buffer becomes not full
    while (this.isFull()) {
      try {
        //Thread.sleep(100);
        wait();
      } catch (InterruptedException e) {
        throw e;
      }
    }

    //add item on bufferContainer
    this.container.add(item);

//    try {
//      Thread.sleep(5000); // sleep for a randomly chosen time
//    } catch (InterruptedException e) {
//      throw e;
//    }

//    String line = "\n" + "-----------------------------------------------------" + "\n";
//    String bufferUsed = "\nBuffer used: " + this.container.size();
//    String strProducedCount = "\nProduced: " + (++this.producedCount);
//
//    //get current date time and past to a String
//    Date dateEnd = new Date();
//    String end = dateFormat.format(dateEnd);
//    System.out.print("Producer" + " put... " + (item)
//            + " From: " + begin
//            + " To: " + end
//            + bufferUsed
//            + strProducedCount
//            + line);


    // Notify producer that status has changed.
    //notify();
    notifyAll();
  }

  /**
   * Get items from Buffer
   * @return value
   * @throws InterruptedException 
   **/
  public synchronized int get() throws InterruptedException {

//    //sets a dateFormat
//    DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
//    //get current date time and past to a String
//    Date dateBegin = new Date();
//    String begin = dateFormat.format(dateBegin);

    while (this.isEmpty()) {	//wait until something appears in the buffer
      try {
        //Thread.sleep(100);
        wait();
      } catch (InterruptedException e) {
        throw e;
      }
    }

    //remove first element of buffer
    int item = (int) this.container.take();
    //int item = (int) this.container.remove(0);

//    try {
//      Thread.sleep(5000); // sleep for a randomly chosen time
//    } catch (InterruptedException e) {
//      throw e;
//    }

    // Notify Consumer that status has changed.
    //notify();
    notifyAll();

//    String line = "\n" + "-----------------------------------------------------" + "\n";
//    String bufferUsed = "\nBuffer used: " + this.container.size();
//    String strConsumedCount = "\nConsumed: " + (++this.consumedCount);
//    //get current date time and past to a String
//    Date dateEnd = new Date();
//    String end = dateFormat.format(dateEnd);
//    System.out.print("Consumer" + " got... "
//            + (item)
//            + " From: " + begin + "."
//            + " To: " + end
//            + bufferUsed
//            + strConsumedCount
//            + line);

    return item;
  }

  /**
   * getBufferSize
   * @param bufferSize 
   */
  public int getBufferSize() {
    return this.bufferSize;
  }

  /**
   * 
   * @param bufferSize 
   */
  public void setBufferSize(int bufferSize) {
    this.bufferSize = bufferSize;
    this.container = new ArrayBlockingQueue<Integer>(bufferSize);
  }

  /**
   * Verify if container is empty
   * @return boolean
   */
  public boolean isFull() {
    return this.container.size() == this.bufferSize;
  }

  /**
   * Verify if container is empty
   * @return boolean
   */
  public boolean isEmpty() {
    return this.container.isEmpty();
  }
}