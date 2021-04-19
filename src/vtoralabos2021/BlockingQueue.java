package vtoralabos2021;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BlockingQueue<Object> {

    List<Object> contents = new ArrayList<>();
    int capacity;
    static Lock lock = new ReentrantLock();
    // mutex

    public BlockingQueue(int capacity) {
        this.capacity = capacity;
    }

    // Chlen dodava item vo BQ
    public void enqueue(String bookName) {
        while (true) {
            lock.lock();
            if (contents.size() < capacity) {
                contents.add((Object) bookName);
                lock.unlock();
                break;
            }
            lock.unlock();
            break;
        }
    }

    // Clen vadi item od BQ
    public String dequeue() {
        String book = "";
        while (true) {
            lock.lock();
            if (contents.size() > 0) {
                book = (String) contents.remove(0);
                lock.unlock();
                break;
            }
            // dokolku contents.size == 0...
            lock.unlock();
            break;
        }
        return book;
    }


    public static void main(String[] args) throws IOException, InterruptedException {
        BlockingQueue blockingQueue = new BlockingQueue(3);
        List<Chlen> nishki = new ArrayList<>();
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        for (int i = 0; i < 5; i++) {
            String imeNaKniga = bf.readLine();
            Chlen chlen = new Chlen(blockingQueue, imeNaKniga);
            nishki.add(chlen);
        }

        for (Chlen c : nishki) {
            c.start();
        }
        for (Chlen c : nishki) {
            c.join();
        }

        System.out.println("End of BQ");
    }
}

class Chlen extends Thread {
    BlockingQueue blockingQueue;
    String bookName;

    public Chlen(BlockingQueue blockingQueue, String bookName) {
        this.blockingQueue = blockingQueue;
        this.bookName = bookName;
    }

    @Override
    public void run() {
        for (int i = 0; i < 2; i++) {
            System.out.println("Chlen " + i + " pozajmuva kniga");
            blockingQueue.dequeue();
            // pristap do metodot
        }
        for (int i = 0; i < 2; i++) {
            System.out.println("Chlen " + i + " vrakja kniga");
            blockingQueue.enqueue(bookName);
        }
    }
}