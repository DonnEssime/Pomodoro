package pomodoro.ui;

import java.awt.Point;
import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
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
            lg.translate(w.loc.x, w.loc.y);
            lg.clipRect(0,0,w.sz.width,w.sz.height);
            w.draw(lg);
        }
    }
    public boolean mousedown(int button, Point location)
    {
        for(Widget w : children)
        {
            if(w.intersects(location) && w.mousedown(button,new Point(location.x-w.loc.x,location.y-w.loc.y)))
                return true;
        }
        return false;
    };
    public boolean mouseup(int button, Point location)
    {
        for(Widget w : children)
        {
            if(w.intersects(location) && w.mouseup(button,new Point(location.x-w.loc.x,location.y-w.loc.y)))
                return true;
        }
        return false;
    };
    public void mousemove(Point location)
    {
        for(Widget w : children)
        {
            if(w.intersects(location))
                w.mousemove(new Point(location.x-w.loc.x,location.y-w.loc.y));
        }
    };
    public final boolean intersects(Point p)
    {
        boolean ix = p.x >= loc.x && p.x <= loc.x+sz.width;
        boolean iy = p.y >= loc.y && p.y <= loc.y+sz.height;
        return ix && iy;
    };
    public void addChild(Widget w)
    {
        children.add(w);
    }
}
