package multithread.chapter04;

public class UnsafeCounter {

    volatile int counter = 0;

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }

    public void increment() {
        counter = counter + 1;
    }
}
