package tretalabos2021;

import java.util.Random;
import java.util.concurrent.Semaphore;

class Demo {

    public static void main(String args[]) throws InterruptedException {
        DiningPhilosophers.runTest();
    }
}

class DiningPhilosophers {

    private static Random random = new Random(System.currentTimeMillis());
    private Semaphore[] forks = new Semaphore[6];

    private Semaphore accessTable;

    public DiningPhilosophers() {
        forks[0] = new Semaphore(1);
        forks[1] = new Semaphore(1);
        forks[2] = new Semaphore(1);
        forks[3] = new Semaphore(1);
        forks[4] = new Semaphore(1);
        forks[5] = new Semaphore(1);

        accessTable = new Semaphore(1);

    }

    public void lifecycleOfPhilosopher(int id) throws InterruptedException {

        while (true) {
            think();
            // Na filozof mu se prijaduva...
            accessTable.acquire();
            // Sto treba da e ispolneto za da jade? - Levoto i Desnoto stapche
            // Neefikasno reshenie implementirano - efikasno - koga dva filozofi mozhat istovremeno da jadat...
            if (id == 0) {
                forks[0].acquire();
                forks[1].acquire();
                eat(id);
                System.out.println("Filozof so id " + id + " jade");
            } else if (id == 1) {
                forks[1].acquire();
                forks[2].acquire();
                eat(id);
                System.out.println("Filozof so id " + id + " jade");
            } else if (id == 2) {
                forks[2].acquire();
                forks[3].acquire();
                eat(id);
                System.out.println("Filozof so id " + id + " jade");
            } else if (id == 3) {
                forks[3].acquire();
                forks[4].acquire();
                eat(id);
                System.out.println("Filozof so id " + id + " jade");
            } else if (id == 4) {
                forks[4].acquire();
                forks[5].acquire();
                eat(id);
                System.out.println("Filozof so id " + id + " jade");
            } else if (id == 5) {
                forks[5].acquire();
                forks[0].acquire();
                eat(id);
                System.out.println("Filozof so id " + id + " jade");
            }
           // break;
        }
    }

    void think() throws InterruptedException {
        Thread.sleep(random.nextInt(50));
    }

    void eat(int id) throws InterruptedException {
        // TODO: synchronize
        if (id == 0) {
            forks[0].release();
            forks[1].release();
            System.out.println("Filozof so id " + id + " razmisluva");
        } else if (id == 1) {
            forks[1].release();
            forks[2].release();
            System.out.println("Filozof so id " + id + " razmisluva");
        } else if (id == 2) {
            forks[2].release();
            forks[3].release();
            System.out.println("Filozof so id " + id + " razmisluva");
        } else if (id == 3) {
            forks[3].release();
            forks[4].release();
            System.out.println("Filozof so id " + id + " razmisluva");
        } else if (id == 4) {
            forks[4].release();
            forks[5].release();
            System.out.println("Filozof so id " + id + " razmisluva");
        } else if (id == 5) {
            forks[5].release();
            forks[0].release();
            System.out.println("Filozof so id " + id + " razmisluva");
        }
        accessTable.release();
    }

    static void runPhilosopher(DiningPhilosophers dp, int id) {
        try {
            dp.lifecycleOfPhilosopher(id);
            // site nishki so objekt od tip DiningPhilosophers go povikuvaat ovoj metod^
        } catch (InterruptedException ie) {

        }
    }

    public static void runTest() throws InterruptedException {
        final DiningPhilosophers dp = new DiningPhilosophers();

        Thread p1 = new Thread(new Runnable() {

            public void run() {
                runPhilosopher(dp, 0);
            }
        });

        Thread p2 = new Thread(new Runnable() {

            public void run() {
                runPhilosopher(dp, 1);
            }
        });

        Thread p3 = new Thread(new Runnable() {

            public void run() {
                runPhilosopher(dp, 2);
            }
        });

        Thread p4 = new Thread(new Runnable() {

            public void run() {
                runPhilosopher(dp, 3);
            }
        });

        Thread p5 = new Thread(new Runnable() {

            public void run() {
                runPhilosopher(dp, 4);
            }
        });

        Thread p6 = new Thread(new Runnable() {

            public void run() {
                runPhilosopher(dp, 5);
            }
        });

        p1.start();
        p2.start();
        p3.start();
        p4.start();
        p5.start();
        p6.start();

        p1.join(5000);
        p2.join(5000);
        p3.join(5000);
        p4.join(5000);
        p5.join(5000);
        p6.join(5000);

        // dodadeno
        p1.interrupt();
        p2.interrupt();
        p3.interrupt();
        p4.interrupt();
        p5.interrupt();
        p6.interrupt();
    }
}