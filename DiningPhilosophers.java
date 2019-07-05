import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import javax.management.monitor.Monitor;

public class DiningPhilosophers extends Monitor implements DiningServer{

    enum State { THINKING, HUNGRY, EATING };
    State[] states = new State[5];
    Condition[] self = new Condition[5];
    Lock lock = new ReentrantLock();
    
    public DiningPhilosophers(){
        for (int i = 0; i < 5; i++){
        	states[i] = State.THINKING;
        	self[i] = lock.newCondition();
        }
    }

    public void takeChopsticks(int i){
        states[i] = State.HUNGRY;
        lock.lock();
        test(i);
        if (states[i] != State.EATING)
			try {
				self[i].await();
            } catch (InterruptedException e) { }
        System.out.println("Philosopher " + i + " aquired its left and right chopsticks.");
        lock.unlock();  
    }

    public void returnChopsticks(int i){
        states[i] = State.THINKING;
        System.out.println("Philosopher " + i + " released its left and right chopsticks.");
        lock.lock();
        test((i + 4) % 5);
        test((i + 1) % 5);
        lock.unlock();
    }

    private void test(int i){
        if ((states[(i + 4) % 5] != State.EATING) &&
            (states[i] == State.HUNGRY) &&
            (states[(i + 1) % 5] != State.EATING)){
                states[i] = State.EATING;
                try{
                	self[i].signal();
                }catch(IllegalMonitorStateException e){}
        } 
    }

	@Override
	public void start() {
		
	}

	@Override
	public void stop() {

	}

}//class