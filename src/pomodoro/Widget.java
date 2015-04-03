package pomodoro;

import java.awt.Point;
import java.awt.Graphics;
import java.awt.Dimension;
import java.util.List;
import java.util.LinkedList;

public abstract class Widget {
    protected Point loc;
    protected Dimension sz;
    protected List<Widget> children;
    
    public Widget(Point loc, Dimension sz)
    {
        this.loc = loc;
        this.sz = sz;
        this.children = new LinkedList<Widget>();
    }
    public Widget(Point loc)
    {
        this(loc,new Dimension(0,0));
    }
    public void setSize(Dimension d)
    {
        this.sz = d;
    }
    public abstract void draw(Graphics g);
    public final void paint(Graphics g)
    {
        this.draw(g);
        for(Widget w : children)
        {
            Graphics lg = g.create();
            lg.translate(loc.x, loc.y);
            lg.clipRect(0,0,sz.width,sz.height);
            w.draw(lg);
        }
    }
}
