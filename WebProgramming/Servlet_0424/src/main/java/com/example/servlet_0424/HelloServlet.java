package com.example.servlet_0424;

import java.io.*;

import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;


@WebServlet(name = "helloServlet", value = "/hello-servlet", initParams={@WebInitParam(name="dbName", value="testDB"), @WebInitParam(name="dbPaswd", value="1234"), @WebInitParam(name="path", value="/WEB-INF/")})
public class HelloServlet extends HttpServlet {
    private String message;



    public void init() {
        message = "Hello Servlet!";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        String dbnm = getInitParameter("dbName");
        String dbpaswd = getInitParameter("dbPaswd");
        String path = getInitParameter("path");

        String dbid = getServletContext().getInitParameter("dbId");
        String dbpwd = getServletContext().getInitParameter("dbPwd");
        String dbsid = getServletContext().getInitParameter("dbSid");

        // Hello
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>" + message + "</h1>");
        out.println("<p>" + dbnm + "</p>");
        out.println("<p>" + dbpaswd + "</p>");
        out.println("<p>" + path + "</p>");
        out.println("<p>" + dbid + "</p>");
        out.println("<p>" + dbpwd + "</p>");
        out.println("<p>" + dbsid + "</p>");
        out.println("</body></html>");
    }

    public void destroy() {
    }
}