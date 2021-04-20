package tretalabos2021;

import java.util.HashSet;
import java.util.concurrent.Semaphore;

public class BasketballTournament {

    // def
    static Semaphore accessSala;
    static Semaphore accessKabina;

    static Semaphore brojachLock;
    static int brojIgrachi;

    // komunikacija
    static Semaphore playerDone;


    public void init() {
        accessSala = new Semaphore(20);
        accessKabina = new Semaphore(10);

        brojachLock = new Semaphore(1);
        brojIgrachi = 0;

        playerDone = new Semaphore(0);
    }

    static class Player extends Thread {
        // Nested Thread

        @Override
        public void run() {
            try {
                execute();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        public void execute() throws InterruptedException {
            // at most 20 players should print this in parallel
            accessSala.acquire();
            System.out.println("Player inside.");


            // at most 10 players may enter in the dressing room in parallel
            accessKabina.acquire();
            System.out.println("In dressing room.");
            accessKabina.release();
            Thread.sleep(10);// this represent the dressing time
            // after all players are ready, they should start with the game together

            brojachLock.acquire();
            brojIgrachi++;
            if (brojIgrachi == 20) {
                brojIgrachi = 0;
                // za narednata grupa da broi povtorno...
                brojachLock.release();
                // sekoj igrach pechati deka natprevarot zapocnal...
                System.out.println("Game started.");
                Thread.sleep(100);// this represent the game duration
                playerDone.acquire(19);
                // only one player should print the next line, representing that the game has finished
                System.out.println("Game finished.");
                accessSala.release(20);
            } else {
                brojachLock.release();
                // Sekoj igrach pechati deka natprevarot zapocnal...
                System.out.println("Game started.");
                Thread.sleep(100);// this represent the game duration
                System.out.println("Player done.");
                playerDone.release();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        HashSet<Player> threads = new HashSet<>();
        BasketballTournament basketballTournament = new BasketballTournament();
        basketballTournament.init();
        for (int i = 0; i < 100; i++) {
            Player p = new Player();
            threads.add(p);
        }
        // run all threads in background
        for (Player p : threads) {
            p.start();
        }

        // after all of them are started, wait each of them to finish for maximum 5_000 ms
        for (Player p : threads) {
            p.join(5000);
        }

        // for each thread, terminate it if it is not finished
        for (Player p : threads) {
            if (p.isAlive()) {
                System.out.println("Possible deadlock!");
                p.interrupt();
            } else {
                System.out.println("Tournament finished.");
            }
        }
    }
}