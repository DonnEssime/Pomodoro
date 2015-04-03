package pomodoro;

import java.awt.Toolkit;
import java.io.IOException;
import javax.imageio.ImageIO;

public abstract class Util {
    private static Toolkit toolkit = Toolkit.getDefaultToolkit();
    public static java.net.URL getResourceURL(String name)
    {
        return PomodoroMainFrame.class.getResource(name);
    }
    public static javax.swing.ImageIcon getIconImage(String name)
    {
        return new javax.swing.ImageIcon(getResourceURL(name+".png"));
    }
    public static java.awt.image.BufferedImage getImage(String name)
    {
        try {
            return ImageIO.read(getResourceURL(name+".png"));
        } catch (IOException ex) {
            return null;
        }
    }
}
