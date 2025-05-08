package kr.co.tlf.ex.frontcontroller;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.ServletException;
import kr.co.tlf.ex.command.*;

import java.io.IOException;
import java.util.HashMap;

@WebServlet("*.do")
public class MBFrontController extends HttpServlet {
    
    /*private final HashMap<String, MBcommand> commandMap = new HashMap<>();
    
    @Override
    public void init() throws ServletException {
        
    }*/
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String viewPage = null;
        MBcommand cmdObj = null;

        String uri = request.getRequestURI();
        String contextPath = request.getContextPath();
        String command = uri.substring(contextPath.length());

        if (command.equals("/list.do")) {
            viewPage = "list.jsp";
        } else if (command.equals("/write_view.do")) {
            viewPage = "write_view.jsp";
        } else if (command.equals("/write.do")) {
            
        } else if (command.equals("/modify.do")) {
            
        } else if (command.equals("/content_view.do")) {
            viewPage = "content_view.jsp";
        } else if (command.equals("/reply.do")) {

        } else if () {
            
        }


        request.getRequestDispatcher(viewPage).forward(request, response);
    }
}