package vtoralabos2021;

public class Exercise {

    public static void main(String[] args) throws InterruptedException {
        ThreadingExample.example();
        // vnatre vo example se kreira nova nishka
    }
}

class ThreadingExample {

    static public void example() throws InterruptedException {

        final Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println("Block thread for an hour");
                    // Thread.sleep(1000 * 60 * 60);
                    Thread.sleep(15);
                } catch (InterruptedException e) {
                    System.out.println("Thread is interrupted : " + Thread.currentThread().isInterrupted());
                    Thread.currentThread().interrupt();
                    System.out.println("The thread is up");
                }
            }
        });

        t1.start();
        // togas zapocnuva run() - logikata na thread
        t1.join(10);
        t1.interrupt();

    }

}
