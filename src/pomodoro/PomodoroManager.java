package pomodoro;

public class PomodoroManager implements Runnable {
    private PomodoroUI ui;
    
    public PomodoroManager()
    {
        ui = new PomodoroUI(this);
    }
    
    @Override
    public void run()
    {
        System.out.println("Manager starting up");
        Thread t = new Thread(ui);
        t.start();
        
        while(true)
        {
            //handle management choices
            //none so far!
        }
    }
}
