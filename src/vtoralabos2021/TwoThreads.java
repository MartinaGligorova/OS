package vtoralabos2021;

import java.util.ArrayList;
import java.util.List;

public class TwoThreads {


    public static void main(String[] args) throws InterruptedException{
        List<Object> brojki = new ArrayList<>();
        List<Object> bukvi = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            brojki.add(i);
            bukvi.add((char) (i + 65));
        }


        ThreadClassLettersNumbers threadClassLettersNumbers = new ThreadClassLettersNumbers(bukvi);
        ThreadClassLettersNumbers threadClassLettersNumbers1 = new ThreadClassLettersNumbers(brojki);
        Thread thread = new Thread(threadClassLettersNumbers);
        Thread thread1 = new Thread(threadClassLettersNumbers1);
        thread.start();
        thread.join();
        thread1.start();
        thread1.join();
    }
}

class ThreadClassLettersNumbers implements Runnable {

    List<Object> bukvibrojki = new ArrayList<>();

    public ThreadClassLettersNumbers(List<Object> bukvibrojki) {
        this.bukvibrojki = bukvibrojki;
    }

    @Override
    public void run() {
        // main logic na thread
        for (int i = 0; i < bukvibrojki.size(); i++) {
            System.out.println(bukvibrojki.get(i));
        }
    }
}

