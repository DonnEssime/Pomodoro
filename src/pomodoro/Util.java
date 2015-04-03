package pomodoro;

public abstract class Util {
    public static java.net.URL getResourceURL(String name)
    {
        return PomodoroMainFrame.class.getResource(name);
    }
    public static javax.swing.ImageIcon getIconImage(String name)
    {
        return new javax.swing.ImageIcon(getResourceURL(name));
    }
}
