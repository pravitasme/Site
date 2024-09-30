package filters;

import javax.servlet.*;
import java.io.IOException;
import java.io.PrintWriter;

public class filter implements Filter
{
    private FilterConfig config = null;
    private boolean active = false;

    @Override
    public void init(FilterConfig config) throws ServletException
    {
        this.config = config;
        String act = config.getInitParameter("active");
        if (act != null)
        {
            active = (act.equalsIgnoreCase("TRUE"));
        }
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException
    {
        PrintWriter out = servletResponse.getWriter();

        System.out.println("got request");
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy()
    {
        config = null;
    }
}