package pomodoro;

public class PomodoroManager implements Runnable {
    private PomodoroUI ui;
    private Thread t;
    
    public PomodoroManager()
    {
        ui = new PomodoroUI(this);
    }
    
    public void shutdown()
    {
        t.interrupt();
    }
    
    @Override
    public void run()
    {
        Thread uit = new Thread(ui);
        uit.start();
        this.t = Thread.currentThread();
        while(!Thread.currentThread().isInterrupted())
        {
            try {
                Thread.sleep(1000/PomodoroMainFrame.fps);
            } catch (InterruptedException ex) {
                
            }
        }
        
        uit.interrupt();
        try {
            uit.join();
        } catch (InterruptedException ex) {
        }
    }
}
