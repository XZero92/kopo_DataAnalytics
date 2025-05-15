package kr.co.tlf.ex.jsp_final_ecommerce_ysh.controller.commands.user;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import kr.co.tlf.ex.jsp_final_ecommerce_ysh.controller.commands.ECCommand;
import kr.co.tlf.ex.jsp_final_ecommerce_ysh.dao.UserDAO;
import kr.co.tlf.ex.jsp_final_ecommerce_ysh.dto.UserDTO;

import java.io.IOException;

public class ECLoginCommand implements ECCommand {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            // GET 요청 처리 - 로그인 폼 표시
            if ("GET".equalsIgnoreCase(request.getMethod())) {
                RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/user/login.jsp");
                dispatcher.forward(request, response);
                return;
            }
            
            // POST 요청 처리 - 로그인 처리
            String userId = request.getParameter("userId");
            String password = request.getParameter("password");
            
            // 기본적인 입력 유효성 검사
            if (userId == null || userId.trim().isEmpty() || password == null || password.trim().isEmpty()) {
                request.setAttribute("errorMessage", "아이디와 비밀번호를 모두 입력해주세요.");
                RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/user/login.jsp");
                dispatcher.forward(request, response);
                return;
            }
            
            // UserDAO를 통한 인증 처리
            UserDAO userDAO = new UserDAO();
            UserDTO user = userDAO.loginUser(userId, password);
            
            // 로그인 성공 시
            if (user != null) {
                if (UserDTO.STATUS_ACTIVE.equals(user.getStatus())) {
                    // 정상 로그인
                    HttpSession session = request.getSession();
                    session.setAttribute("loginUser", user);
                    session.setMaxInactiveInterval(30 * 60);

                    String referer = request.getHeader("Referer");
                    if (referer != null && !referer.contains("login.do") && !referer.contains("register.do")) {
                        response.sendRedirect(referer);
                    } else {
                        response.sendRedirect("main.do");
                    }
                } else {
                    // 상태에 따른 메시지 설정
                    String message = "";
                    if (UserDTO.STATUS_INACTIVE.equals(user.getStatus())) {
                        message = "회원가입 승인 대기중입니다. 관리자 승인 후 이용할 수 있습니다.";
                    } else if (UserDTO.STATUS_DELETED.equals(user.getStatus())) {
                        message = "탈퇴한 회원입니다. 로그인 할 수 없습니다.";
                    } else if (UserDTO.STATUS_SUSPENDED.equals(user.getStatus())) {
                        message = "일시정지된 회원입니다. 관리자에게 문의하세요.";
                    }
                    request.setAttribute("errorMessage", message);
                    RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/user/login.jsp");
                    dispatcher.forward(request, response);
                }
            } 
            // 로그인 실패 시
            else {
                request.setAttribute("errorMessage", "아이디 또는 비밀번호가 일치하지 않습니다.");
                RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/user/login.jsp");
                dispatcher.forward(request, response);
            }
        } catch (ServletException | IOException e) {
            // 예외 로깅
            System.err.println("로그인 처리 중 오류 발생: " + e.getMessage());
            e.printStackTrace();
            
            try {
                // 에러 메시지 설정 및 로그인 페이지로 포워딩
                request.setAttribute("errorMessage", "로그인 처리 중 오류가 발생했습니다. 나중에 다시 시도해주세요.");
                RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/user/login.jsp");
                dispatcher.forward(request, response);
            } catch (ServletException | IOException ex) {
                System.err.println("에러 페이지 포워딩 중 추가 오류 발생: " + ex.getMessage());
                ex.printStackTrace();
            }
        }
    }
}