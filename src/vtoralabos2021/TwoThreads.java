package vtoralabos2021;

import java.util.Collections;
import java.util.List;

public class TwoThreads {


    public static void main(String[] args) throws InterruptedException {
       /* ThreadClassLetters letters = new ThreadClassLetters();
        ThreadClassNumbers numbers = new ThreadClassNumbers();
        letters.start();
        letters.join();
        numbers.start();
        numbers.join(); */

       // Experiments with Runnable - kreiranje na threads

       List<String> bukvi = Collections.singletonList("A\nB\nC\nD\nE\nF\nG\nH\nI\nJ");
       List<String> brojki = Collections.singletonList("0\n1\n2\n3\n4\n5\n6\n7\n8\n9");
       Runnable nishka1 = new ThreadClassLettersNumbers(bukvi);
       Runnable nishka2 = new ThreadClassLettersNumbers(brojki);
       Thread t1 = new Thread(nishka1);
       Thread t2 = new Thread(nishka2);

       t1.start();
       t1.join();
       t2.start();
       t2.join();



    }


}

class ThreadClassLettersNumbers implements Runnable {

    private List lista;


    public ThreadClassLettersNumbers(List lista) {
        this.lista = lista;
    }

    @Override
    public void run() {
        for (int i = 0; i < lista.size(); i++) {
            System.out.println(lista.toString().replace("[", "").replace("]", ""));

        }
    }
}
