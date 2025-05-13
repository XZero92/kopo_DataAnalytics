package kr.co.tlf.ex.jsp_final_ecommerce_ysh.controller.commands.user;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import kr.co.tlf.ex.jsp_final_ecommerce_ysh.controller.commands.ECCommand;
import kr.co.tlf.ex.jsp_final_ecommerce_ysh.dao.OrderDAO;
import kr.co.tlf.ex.jsp_final_ecommerce_ysh.dto.OrderDTO;
import kr.co.tlf.ex.jsp_final_ecommerce_ysh.dto.UserDTO;

import java.io.IOException;
import java.util.List;

public class ECMyPageCommand implements ECCommand {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            // 세션에서 로그인 정보 확인
            HttpSession session = request.getSession();
            UserDTO loginUser = (UserDTO) session.getAttribute("loginUser");
            
            // 로그인되지 않은 경우 로그인 페이지로 리다이렉트
            if (loginUser == null) {
                response.sendRedirect("login.do");
                return;
            }
            
            /*// 주문 내역 가져오기 (최근 5개)
            OrderDAO orderDAO = new OrderDAO();
            List<OrderDTO> recentOrders = orderDAO.getRecentOrders(loginUser.getUserId(), 5);*/
            
            // 요청 속성 설정
            request.setAttribute("user", loginUser);
            //request.setAttribute("recentOrders", recentOrders);
            
            // 마이페이지로 포워딩
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/user/myPage.jsp");
            dispatcher.forward(request, response);
            
        } catch (ServletException | IOException e) {
            System.err.println("마이페이지 처리 중 오류 발생: " + e.getMessage());
            e.printStackTrace();
        }
    }
}