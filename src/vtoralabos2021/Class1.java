package vtoralabos2021;

public class Class1 extends Class2 implements Runnable {


    @Override
    public void run() {
        System.out.println("Hehe");
    }

    public static void main(String[] args) {
        Thread A = new Thread(new Class1());
        A.start();
    }
}
