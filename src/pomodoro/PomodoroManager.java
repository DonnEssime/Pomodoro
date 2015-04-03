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
        
        while(!Thread.currentThread().isInterrupted())
        {
            
        }
        
        t.interrupt();
        try {
            t.join();
        } catch (InterruptedException ex) {
            System.out.println("Manager interrupted again while waiting for the UI to shut down!");
        }
        System.out.println("Manager shutting down!");
    }
}
