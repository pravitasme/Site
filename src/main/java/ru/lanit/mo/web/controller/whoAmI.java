package ru.lanit.mo.web.controller;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class whoAmI  extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        res.setContentType("text/html");

        PrintWriter out = res.getWriter();

        Cookie[] cookies = req.getCookies();

        out.println("You are");

        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("lastName"))
            {
                out.println(cookie.getValue());
            }
            else if (cookie.getName().equals("firstName"))
            {
                out.println(cookie.getValue());
            }
            else if (cookie.getName().equals("patronymic"))
            {
                out.println(cookie.getValue());
            }
        }
    }
}
