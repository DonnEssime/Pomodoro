package pomodoro;


public class PomodoroMainFrame implements Runnable {
    private PomodoroManager mgr;
    
    public PomodoroMainFrame()
    {
        mgr = new PomodoroManager();
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
