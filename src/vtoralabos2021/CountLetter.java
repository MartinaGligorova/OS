package vtoralabos2021;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class CountLetter {

    /**
     * Promenlivata koja treba da go sodrzi brojot na pojavuvanja na bukvata E
     */
    int count = 0;

    /**
     * TODO: definirajte gi potrebnite elementi za sinhronizacija
     */
    public static Lock lock;

    public void init() {
        lock = new ReentrantLock();
    }

    class Counter extends Thread {
        // nishka - koga kreirame instanca

        public void count(String data) throws InterruptedException {
            // da se implementira - broenje na pojavuvanje na 'E'
            lock.lock();

            for (int i = 0; i < data.length(); i++) {
                if (data.charAt(i) == 'E' || data.charAt(i) == 'e') {
                    count++;
                }
            }
            lock.unlock();
        }

        private String data;

        public Counter(String data) {
            this.data = data;
            // konstruktor
        }

        @Override
        public void run() {
            try {
                count(data);
                // izvrshi go metodot za pojavuvanje na E vo string data i vrati rez
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
        String[] data = pom.split(" ");

        for (int i = 0; i < data.length; i++) {

            Counter c = new Counter(data[i]);
            threads.add(c);
            // povekje threads - fragmenti - strings so 'E'
            // zapocni gi site odednas i izbroj vo site zaedno kolku pati se pojavila E
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