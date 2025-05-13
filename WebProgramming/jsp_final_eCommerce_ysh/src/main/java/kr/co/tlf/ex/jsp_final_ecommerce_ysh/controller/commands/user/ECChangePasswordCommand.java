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

public class ECChangePasswordCommand implements ECCommand {
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
            String currentPassword = request.getParameter("currentPassword");
            String newPassword = request.getParameter("newPassword");
            String confirmPassword = request.getParameter("confirmPassword");
            
            // 유효성 검사
            boolean isValid = true;
            String errorMessage = "";
            
            if (currentPassword == null || currentPassword.trim().isEmpty()) {
                isValid = false;
                errorMessage = "현재 비밀번호를 입력해주세요.";
            }
            
            if (newPassword == null || newPassword.trim().isEmpty()) {
                isValid = false;
                errorMessage = "새 비밀번호를 입력해주세요.";
            }
            
            if (confirmPassword == null || confirmPassword.trim().isEmpty()) {
                isValid = false;
                errorMessage = "새 비밀번호 확인을 입력해주세요.";
            }
            
            if (isValid && !newPassword.equals(confirmPassword)) {
                isValid = false;
                errorMessage = "새 비밀번호와 확인 비밀번호가 일치하지 않습니다.";
            }
            
            // 비밀번호 강도 검증
            if (isValid) {
                String regex = "^(?=.*[0-9])(?=.*[a-zA-Z])(?=.*[!@#$%^&*])[a-zA-Z0-9!@#$%^&*]{8,}$";
                Pattern pattern = Pattern.compile(regex);
                Matcher matcher = pattern.matcher(newPassword);
                if (!matcher.matches()) {
                    isValid = false;
                    errorMessage = "비밀번호는 8자 이상, 영문자, 숫자, 특수문자를 포함해야 합니다.";
                }
            }
            
            UserDAO userDAO = new UserDAO();
            
            // 현재 비밀번호 확인
            if (isValid) {
                boolean isCurrentPasswordValid = userDAO.validatePassword(loginUser.getUserId(), currentPassword);
                if (!isCurrentPasswordValid) {
                    isValid = false;
                    errorMessage = "현재 비밀번호가 일치하지 않습니다.";
                }
            }
            
            if (!isValid) {
                // 유효성 검사 실패 시
                request.setAttribute("errorMessage", errorMessage);
                request.setAttribute("user", loginUser);
                request.getRequestDispatcher("/WEB-INF/views/user/myPage.jsp?tab=password").forward(request, response);
                return;
            }
            
            // 비밀번호 변경
            boolean success = userDAO.changePassword(loginUser.getUserId(), newPassword);
            
            if (success) {
                // 성공 시 메시지 설정
                request.setAttribute("successMessage", "비밀번호가 성공적으로 변경되었습니다.");
            } else {
                // 실패 시 에러 메시지 설정
                request.setAttribute("errorMessage", "비밀번호 변경에 실패했습니다. 다시 시도해주세요.");
            }
            
            // 마이페이지로 리다이렉트 (탭 정보 유지)
            response.sendRedirect("myPage.do?tab=password");
            
        } catch (ServletException | IOException e) {
            System.err.println("비밀번호 변경 중 오류 발생: " + e.getMessage());
            e.printStackTrace();
            
            try {
                request.setAttribute("errorMessage", "비밀번호 변경 중 오류가 발생했습니다.");
                request.getRequestDispatcher("/WEB-INF/views/user/myPage.jsp?tab=password").forward(request, response);
            } catch (ServletException | IOException ex) {
                System.err.println("에러 페이지 포워딩 중 추가 오류 발생: " + ex.getMessage());
                ex.printStackTrace();
            }
        }
    }
}