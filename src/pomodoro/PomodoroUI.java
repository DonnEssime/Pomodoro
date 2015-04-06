package pomodoro;

import pomodoro.ui.Button;
import java.awt.Point;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import pomodoro.ui.Label;
import pomodoro.ui.RootWidget;

public final class PomodoroUI extends JPanel implements Runnable {
    private static javax.swing.ImageIcon icon = Util.getIconImage("ui/icon");
    private final PomodoroManager mgr;
    private final JFrame container;
    private final RootWidget root;
    
    private Button sbtn;
    private Label  tlbl;
    
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
        this.root = new RootWidget();
        this.setVisible(true);
        this.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseMoved(MouseEvent e)
            {
                root.mousemove(e.getPoint());
            }
            @Override
            public void mousePressed(MouseEvent e)
            {
                root.mousedown(e.getButton(),e.getPoint());
            }
            @Override
            public void mouseReleased(MouseEvent e)
            {
                root.mouseup(e.getButton(),e.getPoint());
            }
        });
        
        container.add(this);
    }
  
    private void init()
    {
        root.addChild(tlbl=new Label(new Point(256,40),Util.getTimeString(mgr.timer_length),48));
        root.addChild(sbtn=new Button(new Point(256,112),"ui/startbutton"){
            @Override
            public void activation_action()
            {
                mgr.addTimer(new Timer(mgr.timer_length*1000){
                    @Override
                    public void tick_action() {
                        tlbl.changeText(Util.getTimeString((int) (togo/1000)));
                    }
                    @Override
                    public void stop_action() {
                        sbtn.deactivate();
                    }
                });
                mgr.playAudio("sfx/tick",true);
            }
            @Override
            public void deactivation_action()
            {
                tlbl.resetText();
                mgr.playAudio("sfx/alarm",false);
            }
        });
    }
    
    @Override
    public void paint(Graphics g)
    {
        super.paint(g);
        root.paint(g);
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
