package pomodoro;

public class PomodoroUI implements Runnable {
    private final PomodoroManager mgr;
    private boolean running = false;
    
    public PomodoroUI(PomodoroManager mgr)
    {
        this.mgr = mgr;
    }
        
    @Override
    public void run() {
        running = true;
        System.out.println("UI starting up");
        while(!Thread.currentThread().isInterrupted())
        {
            
        }
        System.out.println("UI stopping up");
    }
    
}
