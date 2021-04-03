package vtoralabos2021;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class TwoThreads2 {


    public static void main(String[] args) throws InterruptedException, IOException {

        List<Object> lista = new ArrayList<>();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String s = "";
        int N = 20;
        for (int i = 0; i < N; i++) {
            s = bufferedReader.readLine();
            lista.add(s);
        }
        ThreadClassLettersNumbers2 lettersNumbers = new ThreadClassLettersNumbers2(lista);
        lettersNumbers.start();
        lettersNumbers.join();
        //   System.out.println("Succesful!");
    }
}

class ThreadClassLettersNumbers2 extends Thread {
    List<Object> lettersnumbers;


    public ThreadClassLettersNumbers2(List<Object> lettersnumbers) {

        this.lettersnumbers = lettersnumbers;
    }

    @Override
    public void run() {

        for (int i = 0; i < lettersnumbers.size(); i++) {
            System.out.println(lettersnumbers.get(i));
        }
    }
}
