package atps_programacaoconcorrente;

import java.util.ArrayList;
import java.util.concurrent.ArrayBlockingQueue;

/**
 *
 * @author gvsrepins
 */
class Buffer {

  //ArrayList<Integer> container = new ArrayList<Integer>();
  ArrayBlockingQueue<Integer> container;
  private int contents, bufferSize;
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
    //wait till the buffer becomes not full
    while (this.isFull()) {
      try {
        wait();
      } catch (InterruptedException e) {
        throw e;
      }
    }

    //add item on bufferContainer
    this.container.add(item);
    //System.out.println("Producer: put..." + item);
    notify();
  }

  /**
   * Get items of Buffer
   * @return value
   * @throws InterruptedException 
   **/
  public synchronized int get() throws InterruptedException {
    while (this.isEmpty()) {	//wait until something appears in the buffer
      try {
        wait();
      } catch (InterruptedException e) {
        throw e;
      }
    }

    //remove first element of buffer
    int item = (int) this.container.take();
    //System.out.println("Consumer: got..." + item);
    notify();
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