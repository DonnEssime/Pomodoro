package pomodoro;

import java.awt.*;
import java.awt.event.*;

public class PomodoroMainFrame extends Frame implements Runnable {
    private static javax.swing.ImageIcon icon = Util.getIconImage("/ui/icon.png");
    private PomodoroManager mgr;
    private Thread t;
    
    public PomodoroMainFrame()
    {
        super("Pomodoro Timer");
        mgr = new PomodoroManager();
        
        this.setSize(640,320);
        this.setIconImage(icon.getImage());
        this.setResizable(false);
	addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                t.interrupt();
                try {
                    t.join();
                } catch (InterruptedException ex) {
                }
                System.exit(0);
            }
        });
        this.setVisible(true);
    }
    
    static {
	try {
	    javax.swing.UIManager.setLookAndFeel(javax.swing.UIManager.getSystemLookAndFeelClassName());
	} catch(Exception e) {}
    }
    
    public static void main(String[] args)
    {
        PomodoroMainFrame mainframe = new PomodoroMainFrame();
        mainframe.run();
    }
    
    @Override
    public void run()
    {
        System.out.println("Mainframe starting up");
        ThreadGroup tg = new ThreadGroup("Pomodoro main group");
        Thread t = new Thread(tg, mgr);
        this.t = Thread.currentThread();
        t.start();
        try {
            t.join();
        } catch (InterruptedException ex) {
            t.interrupt();
        }
        System.out.println("MainFrame stopping.");
    }
}
