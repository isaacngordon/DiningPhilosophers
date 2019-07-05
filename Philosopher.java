
public class Philosopher extends Thread{

	private final int MIN_SLEEP = 1000;
	private final int MAX_SLEEP = 20000;
    private static DiningPhilosophers server;
    private int philNum;

    public Philosopher(int n){
        this.philNum = n;
    }

    public void eat(){
        System.out.println("Philosopher " + philNum + " is eating.");
        try {
			Thread.sleep(randomLong(MIN_SLEEP, MAX_SLEEP));
		} catch (InterruptedException e) { }
    }

    public void think(){
        System.out.println("Philosopher " + philNum + " is thinking.");
        try {
			Thread.sleep(randomLong(MIN_SLEEP, MAX_SLEEP));
		} catch (InterruptedException e) { }
    }


    @Override
    public void run(){
        while(true){
        	think();
            server.takeChopsticks(philNum);
            eat();
            server.returnChopsticks(philNum);
        }
    }

    public static long randomLong(int min, int max){
        return (long)(Math.random() * max + min);
    }

    public static void setServer(DiningPhilosophers dp){
        server = dp; 
    }
}