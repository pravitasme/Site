package ru.lanit.mo.web.controller;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class saveItAll extends HttpServlet
{
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
    {
        res.setContentType("text/html");

        PrintWriter out = res.getWriter();

        Cookie ck = new Cookie("patronymic", req.getParameter("patronymic"));
        res.addCookie(ck);

        //next button
        out.println("<form action='/youAre' method='post'> <input type='submit' value='So who am I?'> </form>");
    }
}
