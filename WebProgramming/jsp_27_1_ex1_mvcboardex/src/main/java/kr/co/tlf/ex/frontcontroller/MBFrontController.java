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

        // 뷰 페이지 매핑
        if (command.equals("/list.do")) {
            cmdObj = new MBListCommand();
            cmdObj.execute(request, response);
            viewPage = "/WEB-INF/views/list.jsp";
        } else if (command.equals("/write_view.do")) {
            viewPage = "/WEB-INF/views/write_view.jsp";
        } else if (command.equals("/write.do")) {
            // 글 작성 내용을 DB에 저장하는 요청 (POST 방식)
            cmdObj = new MBWriteCommand();
            cmdObj.execute(request, response); // MBWriteCommand는 "viewPage" 속성을 "list.do" 또는 "write_view.jsp"로 설정합니다.
            
        } else if (command.equals("/content_view.do")) {
            // 글 상세보기
            cmdObj = new MBContentCommand();
            cmdObj.execute(request, response);
            viewPage = "/WEB-INF/views/content_view.jsp";
        } else if (command.equals("/modify.do")) {

        } else if (command.equals("/reply.do")) {

        } else {
            // 기본 페이지 (오류 또는 홈)
            viewPage = "/WEB-INF/views/error.jsp";
        }
            request.getRequestDispatcher(viewPage).forward(request, response);
        }
    }