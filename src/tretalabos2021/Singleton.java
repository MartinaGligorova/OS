package tretalabos2021;

import java.util.concurrent.Semaphore;

public class Singleton {

    private static volatile Singleton singleton;
    static int brojach = 0;
    static Semaphore brojachLock = new Semaphore(1);


    private Singleton() {
        System.out.println("Kreirana e edna instanca od tip Singleton");
    }

    public static Singleton getInstance() throws InterruptedException {
        // TODO: 3/29/20 Synchronize this
        brojachLock.acquire();
        while (true) {
            brojach++;
            if (brojach == 1) {
                brojachLock.release();
                singleton = new Singleton();
                // se kreira instanca
            } else {
                brojachLock.release();
                System.out.println("Ostanatite threads...");
            }
            break;
        }
        return singleton;
    }

    public static void main(String[] args) throws InterruptedException {
        // TODO: 3/29/20 Simulate the scenario when multiple threads call the method getInstance
        Thread p1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    getInstance();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        Thread p2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    getInstance();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        Thread p3 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    getInstance();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        p1.start();
        p2.start();
        p3.start();

        p1.join();
        p2.join();
        p3.join();
    }
}