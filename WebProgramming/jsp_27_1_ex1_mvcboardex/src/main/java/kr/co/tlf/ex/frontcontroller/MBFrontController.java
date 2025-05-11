package kr.co.tlf.ex.frontcontroller;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.ServletException;
import kr.co.tlf.ex.command.*;

import java.io.IOException;

@WebServlet("*.do")
public class MBFrontController extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String viewPage = null;
        MBCommand cmdObj = null;
        boolean isRedirect = false;

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
            cmdObj.execute(request, response);
            
            // viewPage 속성을 확인하여 리다이렉트 여부 결정
            String nextPage = (String) request.getAttribute("viewPage");
            if (nextPage != null && nextPage.endsWith(".do")) {
                viewPage = nextPage;
                isRedirect = true;
            } else {
                viewPage = "/WEB-INF/views/list.jsp"; // 기본값
            }
        } else if (command.equals("/content_view.do")) {
            // 글 상세보기
            cmdObj = new MBContentCommand();
            cmdObj.execute(request, response);
            viewPage = "/WEB-INF/views/content_view.jsp";
        } else if (command.equals("/modify_view.do")) {
            cmdObj = new MBModifyViewCommand();
            cmdObj.execute(request, response);

            // 수정 화면으로 이동
            viewPage = "/WEB-INF/views/write_view.jsp";
        } else if (command.equals("/modify.do")) {
            cmdObj = new MBModifyCommand();
            cmdObj.execute(request, response);

            // 속성에서 viewPage를 가져와 리다이렉트
            String nextPage = (String) request.getAttribute("viewPage");
            if (nextPage != null) {
                viewPage = nextPage;
                isRedirect = true;
            } else {
                viewPage = "list.do";
                isRedirect = true;
            }
        } else if (command.equals("/reply_view.do")) {
            cmdObj = new MBReplyViewCommand();
            cmdObj.execute(request, response);
            viewPage = "/WEB-INF/views/write_view.jsp";
        } else if (command.equals("/reply.do")) {
            cmdObj = new MBReplyCommand();
            cmdObj.execute(request, response);
            
            // 속성에서 viewPage를 가져와 리다이렉트
            String nextPage = (String) request.getAttribute("viewPage");
            if (nextPage != null) {
                viewPage = nextPage;
                isRedirect = true;
            } else {
                viewPage = "list.do";
                isRedirect = true;
            }
        } else if (command.equals("/delete.do")) {
            cmdObj = new MBDeleteCommand();
            cmdObj.execute(request, response);
            
            // 삭제 후 목록 페이지로 리다이렉트
            viewPage = "list.do";
            isRedirect = true;
        } else {
            // 기본 페이지 (오류 또는 홈)
            viewPage = "/WEB-INF/views/error.jsp";
        }

        // 최종 페이지 이동 처리
        if (isRedirect) {
            response.sendRedirect(viewPage);
        } else {
            request.getRequestDispatcher(viewPage).forward(request, response);
        }
    }
}