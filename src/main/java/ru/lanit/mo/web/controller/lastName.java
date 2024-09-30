package ru.lanit.mo.web.controller;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class lastName extends HttpServlet
{
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
    {
        res.setContentType("text/html");

        PrintWriter out = res.getWriter();

        Cookie ck = new Cookie("firstName", req.getParameter("firstName"));
        res.addCookie(ck);

        //form
        out.println("<form action='/patronymic' method='post'> Last Name <input name='lastName' type='text'> <input type='submit' value='go'> </form>");

        out.close();
    }
}