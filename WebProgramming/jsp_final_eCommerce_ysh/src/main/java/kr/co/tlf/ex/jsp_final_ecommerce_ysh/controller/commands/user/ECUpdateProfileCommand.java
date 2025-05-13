package kr.co.tlf.ex.jsp_final_ecommerce_ysh.controller.commands.user;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import kr.co.tlf.ex.jsp_final_ecommerce_ysh.controller.commands.ECCommand;
import kr.co.tlf.ex.jsp_final_ecommerce_ysh.dao.UserDAO;
import kr.co.tlf.ex.jsp_final_ecommerce_ysh.dto.UserDTO;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ECUpdateProfileCommand implements ECCommand {
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
            
            // 폼 데이터 가져오기
            String userName = request.getParameter("userName");
            String mobileNo = request.getParameter("mobileNo");
            
            // 유효성 검사
            boolean isValid = true;
            String errorMessage = "";
            
            if (userName == null || userName.trim().isEmpty()) {
                isValid = false;
                errorMessage = "이름을 입력해주세요.";
            }
            
            if (mobileNo == null || mobileNo.trim().isEmpty()) {
                isValid = false;
                errorMessage = "휴대폰 번호를 입력해주세요.";
            } else {
                // 휴대폰 번호 형식 검증
                String regex = "^01[0-9]-\\d{3,4}-\\d{4}$";
                Pattern pattern = Pattern.compile(regex);
                Matcher matcher = pattern.matcher(mobileNo);
                if (!matcher.matches()) {
                    isValid = false;
                    errorMessage = "유효한 휴대폰 번호 형식이 아닙니다. (예: 010-1234-5678)";
                }
            }
            
            if (!isValid) {
                // 유효성 검사 실패 시
                request.setAttribute("errorMessage", errorMessage);
                request.setAttribute("user", loginUser);
                request.getRequestDispatcher("/WEB-INF/views/user/myPage.jsp").forward(request, response);
                return;
            }

            // 사용자 정보 업데이트
            UserDAO userDAO = new UserDAO();
            loginUser.setUserName(userName);
            loginUser.setMobileNo(mobileNo);
            
            boolean success = userDAO.updateUserProfile(loginUser);
            
            if (success) {
                // 성공 시 세션 정보 업데이트 및 메시지 설정
                session.setAttribute("loginUser", loginUser);
                request.setAttribute("successMessage", "회원 정보가 성공적으로 업데이트되었습니다.");
            } else {
                // 실패 시 에러 메시지 설정
                request.setAttribute("errorMessage", "회원 정보 업데이트에 실패했습니다. 다시 시도해주세요.");
            }
            
            // 마이페이지로 리다이렉트 (탭 정보 유지)
            response.sendRedirect("myPage.do?tab=info");
            
        } catch (ServletException | IOException e) {
            System.err.println("프로필 업데이트 중 오류 발생: " + e.getMessage());
            e.printStackTrace();
            
            try {
                request.setAttribute("errorMessage", "회원 정보 업데이트 중 오류가 발생했습니다.");
                request.getRequestDispatcher("/WEB-INF/views/user/myPage.jsp").forward(request, response);
            } catch (ServletException | IOException ex) {
                System.err.println("에러 페이지 포워딩 중 추가 오류 발생: " + ex.getMessage());
                ex.printStackTrace();
            }
        }
    }
}