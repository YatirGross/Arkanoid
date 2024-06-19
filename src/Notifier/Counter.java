// 207083395 Yatir Zeev Gross
package Notifier;

/**
 * The Counter class represents a simple counter that can be incremented or decremented.
 */
public class Counter {
    private int counter;
    /**
     * Constructs a Counter object with an initial value.
     *
     * @param number the initial value of the counter
     */
    public Counter(int number) {
        this.counter = number;
    }
    /**
     * Increases the counter by a given value.
     *
     * @param number the value to increase the counter by
     */
    public void increase(int number) {
        this.counter += number;
    }
    /**
     * Decreases the counter by a given value.
     *
     * @param number the value to decrease the counter by
     */
    public void decrease(int number) {
        this.counter -= number;
    }
    /**
     * Return the current value of the counter.
     *
     * @return the current value of the counter
     */
    public int getValue() {
        return this.counter;
    }
}
