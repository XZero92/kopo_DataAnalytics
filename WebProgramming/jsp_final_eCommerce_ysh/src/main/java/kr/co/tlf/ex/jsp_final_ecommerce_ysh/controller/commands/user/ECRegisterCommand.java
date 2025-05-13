package kr.co.tlf.ex.jsp_final_ecommerce_ysh.controller.commands.user;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.co.tlf.ex.jsp_final_ecommerce_ysh.controller.commands.ECCommand;
import kr.co.tlf.ex.jsp_final_ecommerce_ysh.dao.UserDAO;
import kr.co.tlf.ex.jsp_final_ecommerce_ysh.dto.UserDTO;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ECRegisterCommand implements ECCommand {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            // HTTP 메서드에 따라 처리 방식 분기
            if ("GET".equalsIgnoreCase(request.getMethod())) {
                // GET 요청 처리 - 회원가입 폼 표시
                RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/user/register.jsp");
                dispatcher.forward(request, response);
            } else if ("POST".equalsIgnoreCase(request.getMethod())) {
                // POST 요청 처리 - 회원가입 처리
                registerUser(request, response);
            }
        } catch (ServletException | IOException e) {
            // 예외 로깅
            System.err.println("회원가입 처리 중 오류 발생: " + e.getMessage());
            e.printStackTrace();
            
            // 에러 정보를 request에 저장
            request.setAttribute("errorMessage", "회원가입 처리 중 오류가 발생했습니다: " + e.getMessage());
            
            // 에러 페이지로 포워딩 시도
            try {
                RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/error.jsp");
                dispatcher.forward(request, response);
            } catch (ServletException | IOException ex) {
                // 포워딩 실패 시 로그만 남김
                System.err.println("에러 페이지로 포워딩 중 추가 오류 발생: " + ex.getMessage());
                ex.printStackTrace();
            }
        }
    }

    private void registerUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 폼에서 제출된 데이터 가져오기
        String userId = request.getParameter("userId");
        String userName = request.getParameter("userName");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");
        String mobileNo = request.getParameter("mobileNo");
        
        // 서버 측 유효성 검증
        StringBuilder errorMsg = new StringBuilder();
        
        // 필수 필드 검증
        if (isEmpty(userId)) errorMsg.append("이메일을 입력해주세요.<br>");
        if (isEmpty(userName)) errorMsg.append("이름을 입력해주세요.<br>");
        if (isEmpty(password)) errorMsg.append("비밀번호를 입력해주세요.<br>");
        if (isEmpty(confirmPassword)) errorMsg.append("비밀번호 확인을 입력해주세요.<br>");
        if (isEmpty(mobileNo)) errorMsg.append("휴대폰 번호를 입력해주세요.<br>");
        
        // 이메일 형식 검증
        if (!isEmpty(userId) && !isValidEmail(userId)) {
            errorMsg.append("유효한 이메일 형식이 아닙니다.<br>");
        }
        
        // 비밀번호 일치 검증
        if (!isEmpty(password) && !isEmpty(confirmPassword) && !password.equals(confirmPassword)) {
            errorMsg.append("비밀번호와 비밀번호 확인이 일치하지 않습니다.<br>");
        }
        
        // 비밀번호 강도 검증
        if (!isEmpty(password) && !isValidPassword(password)) {
            errorMsg.append("비밀번호는 8자 이상, 영문자, 숫자, 특수문자를 포함해야 합니다.<br>");
        }
        
        // 휴대폰 번호 형식 검증
        if (!isEmpty(mobileNo) && !isValidMobileNo(mobileNo)) {
            errorMsg.append("유효한 휴대폰 번호 형식이 아닙니다. (예: 010-1234-5678)<br>");
        }
        
        // 유효성 검증 실패 시
        if (errorMsg.length() > 0) {
            // 입력 필드 값들을 request 속성으로 설정
            request.setAttribute("errorMessage", errorMsg.toString());
            request.setAttribute("userId", userId);
            request.setAttribute("userName", userName);
            request.setAttribute("mobileNo", mobileNo);
            // 보안상 비밀번호는 다시 설정하지 않음
            
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/user/register.jsp");
            dispatcher.forward(request, response);
            return;
        }
        
        // UserDTO 객체 생성 및 설정
        UserDTO user = new UserDTO();
        user.setUserId(userId);
        user.setUserName(userName);
        user.setPassword(password); // DAO에서 암호화 처리
        user.setEmail(userId); // 이메일과 userId를 동일하게 설정
        user.setMobileNo(mobileNo);
        user.setStatus(UserDTO.STATUS_INACTIVE); // 초기 상태는 비활성화(가입 대기)
        user.setUserType(UserDTO.USER_TYPE_MEMBER); // 일반 회원으로 설정
        
        // DAO를 통해 DB에 저장
        UserDAO userDAO = new UserDAO();
        boolean success = userDAO.insertUser(user);
        
        if (success) {
            // 회원가입 성공 시 로그인 페이지로 리다이렉트
            response.sendRedirect("login.do?registered=true");
        } else {
            // 회원가입 실패 시 에러 메시지와 함께 회원가입 페이지로 포워딩
            request.setAttribute("errorMessage", "회원가입 처리 중 오류가 발생했습니다. 다시 시도해주세요.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/user/register.jsp");
            dispatcher.forward(request, response);
        }
    }
    
    // 유틸리티 메서드: 빈 문자열 체크
    private boolean isEmpty(String str) {
        return str == null || str.trim().isEmpty();
    }
    
    // 유틸리티 메서드: 이메일 유효성 검증
    private boolean isValidEmail(String email) {
        String regex = "^[A-Za-z0-9+_.-]{5,15}@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    // 유틸리티 메서드: 비밀번호 유효성 검증 (5자 이상, 15자 이하, 영문자, 숫자 포함)
    private boolean isValidPassword(String password) {
        String regex = "^(?=.*[0-9])(?=.*[a-zA-Z])(?=.*[A-Z])(?=.*[a-z])[a-zA-Z0-9]{5,15}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }
    
    // 유틸리티 메서드: 휴대폰 번호 유효성 검증
    private boolean isValidMobileNo(String mobileNo) {
        String regex = "^01[0-9]-\\d{3,4}-\\d{4}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(mobileNo);
        return matcher.matches();
    }
}