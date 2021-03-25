package vtoralabos2021;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BlockingQueue<T> {

    //T[] contents;
    List<String> contents = new ArrayList<>();
    int capacity;
    public static Lock lock = new ReentrantLock();

    public BlockingQueue(int capacity) {
        //  contents = (T[]) new Object[capacity];
        this.capacity = capacity;
    }

    public void enqueue(T item) {
        // dodadi item
        // uslov - ako nizata e polna ne mozes da dodades item
        while (true) {
            // infinite loop
            lock.lock();
            // pred da ja proverish sostojbata na contents zakluchi - da ne ja promeni dr. thread vo megjuvreme
            if (contents.size() < capacity) {
                // ima mesto

                contents.add((String) item);

                lock.unlock();
                // otkako sme postavile item otkluci
                break;
                // izlezi od loop da se dodeli kluch na nareden thread
            }
            lock.unlock();
            // dokolku contents.length == capacity - prepolna e nizata ne moze da dodavash otkluchi nekoj da izvadi item
        }
    }

    public T dequeue() {
        // izvadi item od niza
        // uslov - ako nizata e prazna ne mozes da izvadish item
        T element;
        int i = 0;
        while (true) {
            lock.lock();
            // zakluci za proverka na capacity
            if (contents.size() > 0) {
                // ako ima elementi vo nizata
                element = (T) contents.remove(0);

                // remove from head?
                lock.unlock();
                break;
            }
            lock.unlock();
            // dokolku contents.size<0..prazna e otkluci
        }
        return element;
    }
}