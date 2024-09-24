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

public class servlet2 extends HttpServlet {
    @Override
    protected void doPost(@NotNull HttpServletRequest req, @NotNull HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");

        PrintWriter out = resp.getWriter();

        Cookie[] cookies = req.getCookies();

        out.printf("Hello %s", cookies[0].getValue());

        out.close();
    }
}
