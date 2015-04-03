package pomodoro;

public class PomodoroUI implements Runnable {
    private final PomodoroManager mgr;
    
    public PomodoroUI(PomodoroManager mgr)
    {
        this.mgr = mgr;
    }
    
    @Override
    public void run() {
        System.out.println("UI starting up");
    }
    
}
