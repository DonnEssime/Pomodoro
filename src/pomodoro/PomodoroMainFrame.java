package pomodoro;

import javax.swing.JFrame;

public class PomodoroMainFrame extends javax.swing.JFrame implements Runnable {
    private static javax.swing.ImageIcon icon = Util.getIconImage("/ui/icon.png");
    private PomodoroManager mgr;
    
    public PomodoroMainFrame()
    {
        super("Pomodoro Timer");
        mgr = new PomodoroManager();
        
        this.setSize(640,320);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setIconImage(icon.getImage());
        this.setResizable(false);
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
        t.start();
        try {
            t.join();
        } catch (InterruptedException ex) {
            t.interrupt();
        }
    }
}
