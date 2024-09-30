package listeners;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class listener2 implements HttpSessionListener
{
    @Override
    public void sessionCreated(HttpSessionEvent se)
    {
        System.out.println("sessionCreated");
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent)
    {
        System.out.println("sessionDestroyed");
    }
}
