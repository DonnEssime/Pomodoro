package pomodoro;

import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;
import java.util.LinkedList;

public class PomodoroUI extends Frame implements Runnable {
    private static javax.swing.ImageIcon icon = Util.getIconImage("/ui/icon.png");
    private final PomodoroManager mgr;
    private final List<Widget> children;
    private Thread t;
    
    public PomodoroUI(PomodoroManager mgr)
    {
        super("Pomodoro Timer");
        
        this.mgr = mgr;
        this.children = new LinkedList<Widget>();
        
        this.setSize(640,320);
        this.setIconImage(icon.getImage());
        this.setResizable(false);
	addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                PomodoroUI.this.mgr.shutdown();
                System.exit(0);
            }
        });
        this.setVisible(true);
    }
        
    @Override
    public void run() {
        this.t = Thread.currentThread();
        while(!Thread.currentThread().isInterrupted())
        {
            
        }
    }
}
