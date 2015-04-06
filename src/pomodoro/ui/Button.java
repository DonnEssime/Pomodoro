
package pomodoro.ui;

import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.awt.Point;
import java.awt.event.MouseEvent;
import pomodoro.Util;

public class Button extends Widget {
    protected BufferedImage imgs[];
    private static enum ButtonState {INACTIVE,CLICKING_INACTIVE,ACTIVE,CLICKING_ACTIVE};
    private ButtonState state;
    
    public Button(Point loc,String type)
    {
        super(loc);
        imgs = new BufferedImage[4];
        imgs[0] = Util.getImage(type+"i");
        imgs[1] = Util.getImage(type+"ci");
        imgs[2] = Util.getImage(type+"a");
        imgs[3] = Util.getImage(type+"ca");
        state = ButtonState.INACTIVE;
        this.sz = new Dimension(imgs[0].getWidth(),imgs[0].getHeight());
    }
    
    public void activation_action(){}
    public final void activate(){
        this.state = ButtonState.ACTIVE;
        activation_action();
    }
    public void deactivation_action(){}
    public final void deactivate(){
        this.state = ButtonState.INACTIVE;
        deactivation_action();
    }
    
    @Override
    public void draw(Graphics g)
    {
        g.drawImage(imgs[state.ordinal()],0,0,null);
    }
    
    @Override
    public boolean mouseup(int button, Point location)
    {
        if(this.state == ButtonState.CLICKING_ACTIVE)   
        {
            deactivate();
        }
        else if(this.state == ButtonState.CLICKING_INACTIVE)   
        {
            activate();
        }
        return true;
    }
    
    @Override
    public boolean mousedown(int button, Point location)
    {
        if(this.state == ButtonState.ACTIVE)
            this.state = ButtonState.CLICKING_ACTIVE;
        else if(this.state == ButtonState.INACTIVE)
            this.state = ButtonState.CLICKING_INACTIVE;
        return true;
    }
}
