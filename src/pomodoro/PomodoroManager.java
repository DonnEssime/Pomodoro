package pomodoro;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.sound.sampled.Clip;

public class PomodoroManager implements Runnable {
    private PomodoroUI ui;
    private Thread t;
    private final List<Timer> timers;
    private Clip playing;
    public final int timer_length=25*60;
    
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
    
    public void playAudio(String name, final boolean loop)
    {
        if(playing != null)
            playing.close();
        
        playing = Util.getAudioClip(name);
        playing.loop(loop?Clip.LOOP_CONTINUOUSLY:0);
    }
    
    @Override
    public void run()
    {
        Thread uit = new Thread(ui);
        uit.start();
        this.t = Thread.currentThread();
        while(!Thread.currentThread().isInterrupted())
        {
            Iterator<Timer> ts = timers.iterator();
            while(ts.hasNext())
            {
                Timer t = ts.next();
                if(t.tick())
                    ts.remove();
            }
            try {
                Thread.sleep(1000/PomodoroMainFrame.mgrfps);
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
