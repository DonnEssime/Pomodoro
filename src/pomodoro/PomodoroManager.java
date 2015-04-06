package pomodoro;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class PomodoroManager implements Runnable {
    private PomodoroUI ui;
    private Thread t;
    private final List<Timer> timers;
    
    public PomodoroManager()
    {
        ui = new PomodoroUI(this);
        timers = new ArrayList<Timer>();
    }
    
    public void shutdown()
    {
        t.interrupt();
    }
    
    public void addTimer(Timer t)
    {
        timers.add(t);
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
                Iterator<Timer> ts = timers.iterator();
                while(ts.hasNext())
                {
                    Timer t = ts.next();
                    if(t.tick())
                        ts.remove();
                }
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
