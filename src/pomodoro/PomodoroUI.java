package pomodoro;

import java.awt.Point;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class PomodoroUI extends Frame implements Runnable {
    private static javax.swing.ImageIcon icon = Util.getIconImage("/ui/icon");
    private final PomodoroManager mgr;
    private final List<Widget> children;
    
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
  
    private void init()
    {
        children.add(new Button(new Point(256,112),"/ui/startbutton"){
            @Override
            public void activated()
            {
                
            }
            @Override
            public void deactivated()
            {
                
            }
        });
    }
    
    @Override
    public void paint(Graphics g)
    {
        super.paint(g);
        for(Widget w : children)
        {
            w.paint(g);
        }
    }
    
    @Override
    public void run() {
        this.init();
        this.repaint();
        while(!Thread.currentThread().isInterrupted())
        {
            try {
                Thread.sleep(1000/PomodoroMainFrame.fps);
            } catch (InterruptedException ex) {
                
            }
        }
    }
}
