public class Assignment2{


    public static void main(String[] args){
        
    	System.out.println("\n===============================================================================================\n" +
    					   "The following program will have 5 philosphers alternating between thinking and eating when they" + 
    					   "\nget hungry. The time between state change is 1-20 seconds." +
    					   "\nIn order to terminate the program, press Ctrl + C.\n" +
    					   "===============================================================================================\n");

        DiningPhilosophers dp = new DiningPhilosophers();
        Philosopher.setServer(dp);

        Philosopher[] phils = new Philosopher[5];
        for(int i = 0; i < 5; i++) phils[i] = new Philosopher(i);
        for(Philosopher p : phils)  p.start();
    }

}