package atps_programacaoconcorrente;

/**
 *
 * @author gvsrepins
 */
public class Buffer {

  private int[] contents;

  /**
   * Construct
   * @param quantity count of content to be storage on Buffer
   */
  public Buffer(int count) {
    this.contents = new int[count];
  }

  /**
   * Populate buffer
   */
  public void populateBuffer() {
    for (int i = 0; i < this.getContents().length; i++) {
      this.contents[i] = (int) Math.abs(Math.random() * i) + 1; //generate a random number
    }
  }

  /**
   * Sets item of content Buffer to 0
   * @param position 
   */
  public void erase(int position) {
    this.contents[position] = -1;
  }

  public int[] getContents() {
    return contents;
  }

  public void setContents(int[] contents) {
    this.contents = contents;
  }
}
