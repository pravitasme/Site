package ru.lanit.mo.web.controller;

import org.jetbrains.annotations.NotNull;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

//@WebServlet("/hello")
public class servlet extends HttpServlet {
    @Override
    protected void doPost(@NotNull HttpServletRequest req, @NotNull HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");

        PrintWriter out = resp.getWriter();

        String name = req.getParameter("name");

        Cookie cookies = new Cookie("userName", name);
        resp.addCookie(cookies);

        //submit button
        out.println("<html> <body> <form action='/servlet2'> <input type='submit' value='go'> </form> </body> </html>");

        out.close();
    }
}
