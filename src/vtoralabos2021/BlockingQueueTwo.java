package vtoralabos2021;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BlockingQueueTwo<T> {
    // TODO: realiziraj sync. so Mutex. - BlockingQueue - similar to Library.
    List<String> elementsInQueue = new ArrayList<>();
    int capacity;

    // Mutex
    public static Lock lock = new ReentrantLock();

    public BlockingQueueTwo(int capacity) {
        this.capacity = capacity;
        // od vlez se naznachuva max capacity na redicata.
    }

    // clen postavuva vo BlockingQueue
    public void enqueue(T item) {
        while (true) {
            // Uslov 1: Sto se sluchuva ako clen postavuva vo polna BlockingQueue?
            lock.lock();
            // zakluci dodeka proveruvash capacity
            if (elementsInQueue.size() < capacity) {
                elementsInQueue.add((String) item);
                lock.unlock();
                break;
            }
            lock.unlock();
            break;
            // oslobodi kluc za - vadenje od queue dokolku e prepoln kapacitetot
        }
    }


    // clen vadi od BlockingQueue(od vrv)
    public T dequeue() {
        String item = "";
        while (true) {
            // Uslov 2: Sto se sluchuva ako clen saka da izvadi, a queue e prazna?
            lock.lock();
            if (elementsInQueue.size() > 0) {
                item = elementsInQueue.remove(0);
                lock.unlock();
                break;
            }
            lock.unlock();
            break;
        }
        // dokolku size == 0 oslobodi kluch.
        return (T) item;
    }

    public static void main(String[] args) throws InterruptedException {
        List<MemberTwo> lista = new ArrayList<>();
        BlockingQueueTwo blockingQueue = new BlockingQueueTwo(5);
        for (int i = 0; i < 10; i++) {
            MemberTwo memberTwo = new MemberTwo("M", blockingQueue);
            // nishki ->Born -> 10
            lista.add(memberTwo);
        }

        for (MemberTwo member : lista) {
            member.start();
        }

        for (MemberTwo member : lista) {
            member.join(1000);
            if (member.isAlive()) {
                member.interrupt();
            }
        }
    }
}

class MemberTwo extends Thread {
    // nishki
    private String name;
    private BlockingQueueTwo blockingQueue;
    // sekoja thread ima svoja redica


    public MemberTwo(String name, BlockingQueueTwo blockingQueue) {
        this.name = name;
        this.blockingQueue = blockingQueue;
    }


    @Override
    public void run() {
        // main logic na thread - dodava/vadi elementi od BlockingQueue
        for (int i = 0; i < 3; i++) {
            System.out.println("Thread PUTS item " + i + " into BlockingQueue!");
            blockingQueue.enqueue("Item: " + i);
        }
        for (int i = 0; i < 2; i++) {
            System.out.println("Thread REMOVES item " + i + " from BlockingQueue!");
            blockingQueue.dequeue();
        }
    }
}
