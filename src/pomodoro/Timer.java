
package pomodoro;

public abstract class Timer {
    protected long start;
    protected long length;
    protected long togo;
    public Timer(long length)
    {
        start = System.currentTimeMillis();
        this.length = length;
        this.togo = length;
    }
    public final boolean tick()
    {
        togo = start + length - System.currentTimeMillis();
        if(togo<0)
        {
            stop_action();
            return true;
        }
        else
        {
            tick_action();
            return false;
        }
    }
    public void tick_action()
    {
        
    }
    public void stop_action()
    {
        
    }
}
