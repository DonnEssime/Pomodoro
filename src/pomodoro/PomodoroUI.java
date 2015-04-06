package pomodoro;

import pomodoro.ui.Widget;
import pomodoro.ui.Button;
import java.awt.Point;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;
import java.util.LinkedList;
import javax.swing.JFrame;
import javax.swing.JPanel;

public final class PomodoroUI extends JPanel implements Runnable {
    private static javax.swing.ImageIcon icon = Util.getIconImage("/ui/icon");
    private final PomodoroManager mgr;
    private final JFrame container;
    private final List<Widget> children;
    
    public PomodoroUI(PomodoroManager mgr)
    {
        super(true);
        
        container = new JFrame(){
            {
                this.setTitle("Pomodoro Timer");
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
        };
        
        this.mgr = mgr;
        this.children = new LinkedList<Widget>();
        this.setVisible(true);
        this.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseMoved(MouseEvent e)
            {
                mousemove(e.getPoint());
            }
            @Override
            public void mousePressed(MouseEvent e)
            {
                mousedown(e.getButton(),e.getPoint());
            }
            @Override
            public void mouseReleased(MouseEvent e)
            {
                mouseup(e.getButton(),e.getPoint());
            }
        });
        
        container.add(this);
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
    
    public void mousedown(int button, Point location)
    {
        for(Widget w : children)
        {
            if(w.mousedown(button, location))
                return;
        }
    }
    
    public void mouseup(int button, Point location)
    {
        for(Widget w : children)
        {
            if(w.mouseup(button, location))
                return;
        }
    }
    
    public void mousemove(Point location)
    {
        for(Widget w : children)
        {
            if(w.intersects(location))
                w.mousemove(location);
        }
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
        while(!Thread.currentThread().isInterrupted())
        {
            this.repaint();
            try {
                Thread.sleep(1000/PomodoroMainFrame.fps);
            } catch (InterruptedException ex) {}
        }
    }
}
