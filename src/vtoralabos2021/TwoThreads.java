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


        ThreadClassLettersNumbers thread1 = new ThreadClassLettersNumbers(bukvi);
        ThreadClassLettersNumbers thread2= new ThreadClassLettersNumbers(brojki);
        Thread threadA = new Thread(thread1);
        Thread threadB = new Thread(thread2);
        threadA.start();
        threadA.join();
        threadB.start();
        threadB.join();
    }
}

class ThreadClassLettersNumbers implements Runnable {

    List<Object> bukvibrojki;

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

