package com.example.demo.servlet;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/basic")
public class BasicServlet extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws IOException {
        res.getWriter().write("GET OK");
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws IOException {
        res.getWriter().write("POST OK");
    }
}
