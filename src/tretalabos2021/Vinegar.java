package tretalabos2021;

import java.util.HashSet;
import java.util.concurrent.Semaphore;

public class Vinegar {

    // def
    static Semaphore oBorn;
    static Semaphore cBorn;
    static Semaphore hBorn;

    static int brojach;
    static Semaphore lockBrojach;

    // komunikacija
    static Semaphore hIsHere;
    static Semaphore oIsHere;
    static Semaphore canBond;
    static Semaphore isDone;


    // init
    public static void init() {
        oBorn = new Semaphore(2);
        hBorn = new Semaphore(4);
        cBorn = new Semaphore(2);

        // coordinator
        lockBrojach = new Semaphore(1);
        brojach = 0;

        hIsHere = new Semaphore(0);
        oIsHere = new Semaphore(0);
        canBond = new Semaphore(0);
        isDone = new Semaphore(0);
    }

    public static void main(String[] args) throws InterruptedException {
        HashSet<Thread> threads = new HashSet<>();
        init();
        for (int i = 0; i < 20; i++) {
            threads.add(new C());
        }
        for (int j = 0; j < 40; j++) {
            threads.add(new H());
        }
        for (int s = 0; s < 20; s++) {
            threads.add(new O());
        }
        // run all threads in background
        for (Thread t : threads) {
            t.start();
        }

        // after all of them are started, wait each of them to finish for maximum 2_000 ms
        for (Thread t : threads) {
            t.join(2000);
        }

        // for each thread, terminate it if it is not finished
        for (Thread t : threads) {
            if (t.isAlive()) {
                System.out.println("Possible deadlock!");
                t.interrupt();
            }
        }
        System.out.println("Process finished.");
    }

    static class C extends Thread {

        public void execute() throws InterruptedException {
            // at most 2 atoms should print this in parallel
            cBorn.acquire();
            System.out.println("C here.");

            lockBrojach.acquire();
            brojach++;
            if (brojach == 2) {
                // vtoriot C atom e coordinator
                brojach = 0;
                // za slednoto grupiranje
                lockBrojach.release();
                hIsHere.acquire(4);
                oIsHere.acquire(2);
                canBond.release(7);
                // after all atoms are present, they should start with the bonding process together
                System.out.println("Molecule bonding.");
                Thread.sleep(100);// this represent the bonding process
                isDone.acquire(7);
                System.out.println("C done.");
                // only one atom should print the next line, representing that the molecule is created
                System.out.println("Molecule created.");
                cBorn.release(2);
                hBorn.release(4);
                oBorn.release(2);
            } else {
                lockBrojach.release();
                canBond.acquire();
                // after all atoms are present, they should start with the bonding process together
                System.out.println("Molecule bonding.");
                Thread.sleep(100);// this represent the bonding process
                System.out.println("C done.");
                isDone.release();
            }
        }

        @Override
        public void run() {
            try {
                execute();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    static class H extends Thread {

        public void execute() throws InterruptedException {
            // at most 4 atoms should print this in parallel
            hBorn.acquire();
            System.out.println("H here.");
            hIsHere.release();


            canBond.acquire();
            // after all atoms are present, they should start with the bonding process together
            System.out.println("Molecule bonding.");
            Thread.sleep(100);// this represent the bonding process


            System.out.println("H done.");
            isDone.release();

        }

        @Override
        public void run() {
            try {
                execute();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    static class O extends Thread {

        public void execute() throws InterruptedException {
            // at most 2 atoms should print this in parallel
            oBorn.acquire();
            System.out.println("O here.");
            oIsHere.release();

            canBond.acquire();
            // after all atoms are present, they should start with the bonding process together
            System.out.println("Molecule bonding.");
            Thread.sleep(100);// this represent the bonding process


            System.out.println("O done.");
            isDone.release();
        }

        @Override
        public void run() {
            try {
                execute();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}