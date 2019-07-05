
public interface DiningServer{

    /* Called by a philosopher when it wishes to eat */
    public void takeChopsticks(int philNumber);
    
    /* Called by a philosopher when it is finished eating */
    public void returnChopsticks(int philNumber);

}