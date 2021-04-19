package vtoralabos2021;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.concurrent.Semaphore;

public class CountLetter {

    /**
     * Promenlivata koja treba da go sodrzi brojot na pojavuvanja na bukvata A
     */
    static int count = 0;
    /**
     * TODO: definirajte gi potrebnite elementi za sinhronizacija
     */

    static Semaphore coordinator;
    static Semaphore mutex;
    // ^ samo 1 nitka moze da ja modificira count vo odreden vremenski moment

    public void init() {
        coordinator = new Semaphore(100);
        mutex = new Semaphore(1);
    }

    class Counter extends Thread {
        // Nested Thread

        public void count(String data) throws InterruptedException {
            // da se implementira
            coordinator.acquire();
            // 100 nitki od 1000 chekaat ovde

            mutex.acquire();
            // edna po edna dobivaat pristap do count..
            if (data.charAt(0) == 'E' || data.charAt(0) == 'e') {
                count++;
                mutex.release();
                // oslobodi mutex ako char e E
            }
            mutex.release();
            coordinator.release();

        }

        private String data;

        public Counter(String data) {

            this.data = data;
        }

        @Override
        public void run() {
            try {
                count(data);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        try {
            CountLetter environment = new CountLetter();
            environment.start();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void start() throws Exception {

        init();

        HashSet<Thread> threads = new HashSet<Thread>();
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        String pom = bf.readLine();
        String[] data = pom.split("");

        for (int i = 0; i < data.length; i++) {
            // kolku sto ima characters - tolku nitki se kreiraat - so toj single character

            Counter c = new Counter(data[i]);
            threads.add(c);
        }


        for (Thread t : threads) {
            t.start();
        }

        for (Thread t : threads) {
            t.join();
        }
        System.out.println(count);


    }
}


