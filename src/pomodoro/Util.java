package pomodoro;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
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
    
    private static Font default_font;
    static {
        default_font = new Font("Trebuchet MS", Font.PLAIN, 12);
    }
    public static Font getApplicationFont(int size)
    {
        return default_font.deriveFont(Font.BOLD, size);
    }
    public static BufferedImage renderText(String text, int size)
    {
        Font f = Util.getApplicationFont(size);
        //stupid java trick to get fontmetrics without first getting a graphics2d
        FontMetrics fm = (new Canvas()).getFontMetrics(f);
        int height = fm.getHeight();
        int width = fm.stringWidth(text);
        BufferedImage ret = new BufferedImage(width,height,BufferedImage.TYPE_4BYTE_ABGR);
        Graphics2D g = ret.createGraphics();
        g.setColor(Color.DARK_GRAY);
        g.setFont(f);
        g.setRenderingHint(
            RenderingHints.KEY_TEXT_ANTIALIASING,
            RenderingHints.VALUE_TEXT_ANTIALIAS_GASP);
        g.drawString(text, 1, ret.getHeight()-1);
        g.dispose();
        return ret;
    }
}
