
package pomodoro.ui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;
import pomodoro.Util;

public class Label extends Widget{
    private String text;
    private int textsize;
    private BufferedImage rendered;
    
    public Label(Point p, String text)
    {
        this(p,text,12);
    }
    public Label(Point p, String text, int size)
    {
        super(p);
        this.text = text;
        this.textsize = size;
        renderText();
    }
    public final void changeText(String t)
    {
        text = t;
        renderText();
    }
    public final void changeTextSize(int sz)
    {
        textsize = sz;
        renderText();
    }
    private void renderText()
    {
        rendered = Util.renderText(text,textsize);
        this.sz = new Dimension(rendered.getWidth(),rendered.getHeight());
    }
    @Override
    public void draw(Graphics g)
    {
        g.drawImage(rendered,0,0,null);
    }
}