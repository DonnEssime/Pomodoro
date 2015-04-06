package pomodoro;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.Clip;

public class PomodoroManager implements Runnable {
    private PomodoroUI ui;
    private Thread t;
    private final List<Timer> timers;
    private Clip playing;
    public final int timer_length=5;
    
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
        final Clip c = Util.getAudioClip(name);
        if(playing == null)
        {
            c.loop(loop?Clip.LOOP_CONTINUOUSLY:1);
            playing = c;
        }
        else
        {
            playing.close();
            (new Thread(){
                @Override
                public void run() {
                    while(playing != null)
                        try {
                            Thread.sleep((int) (1000/PomodoroMainFrame.mgrfps));
                        } catch (InterruptedException ex) {}
                    
                    c.loop(loop?Clip.LOOP_CONTINUOUSLY:0);
                    playing = c;
                }
            }).start();
        }
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
            if(playing != null && !playing.isOpen())
            {
                playing = null;
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
