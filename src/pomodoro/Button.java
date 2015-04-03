
package pomodoro;

import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.awt.Point;

public class Button extends Widget {
    protected BufferedImage imgs[];
    protected static enum ButtonState {INACTIVE,CLICKED,ACTIVE};
    protected ButtonState state;
    
    public Button(Point loc,String type)
    {
        super(loc);
        imgs = new BufferedImage[3];
        imgs[0] = Util.getImage(type+"i");
        imgs[1] = Util.getImage(type+"c");
        imgs[2] = Util.getImage(type+"a");
        state = ButtonState.INACTIVE;
        this.sz = new Dimension(imgs[0].getWidth(),imgs[1].getHeight());
    }
    
    public void activated(){}
    public void deactivated(){}
    
    @Override
    public void draw(Graphics g)
    {
        g.drawImage(imgs[state.ordinal()],loc.x,loc.y,null);
    }
}
