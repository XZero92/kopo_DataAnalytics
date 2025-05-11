package kr.co.tlf.ex.command;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.co.tlf.ex.dao.MBDao;

public class MBDeleteCommand implements MBCommand {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        // 1. 요청에서 게시글 번호와 페이지 정보 가져오기
        String nbBoard = request.getParameter("nbBoard");
        String page = request.getParameter("page");
        
        // 페이지 번호가 없는 경우 기본값 설정
        if (page == null || page.trim().isEmpty()) {
            page = "1";
        }
        
        // 2. DB 작업을 위한 DAO 객체 생성
        MBDao dao = new MBDao();
        
        // 3. 게시글 삭제 메소드 호출
        int result = dao.deletePost(nbBoard);
        
        // 4. 삭제 결과에 따른 메시지 설정 (선택사항)
        if (result == 1) {
            // 삭제 성공 시 메시지
            request.setAttribute("message", "게시글이 성공적으로 삭제되었습니다.");
        } else {
            // 삭제 실패 시 메시지
            request.setAttribute("message", "게시글 삭제에 실패했습니다.");
        }
        
        // 5. 리스트로 돌아갈 때 사용할 페이지 번호 설정
        request.setAttribute("page", page);
        
        // 6. 삭제 후 리다이렉트할 페이지 설정 (MBFrontController에서 처리)
        // MBFrontController에서 이미 list.do로 리다이렉트하도록 설정되어 있음
    }
}