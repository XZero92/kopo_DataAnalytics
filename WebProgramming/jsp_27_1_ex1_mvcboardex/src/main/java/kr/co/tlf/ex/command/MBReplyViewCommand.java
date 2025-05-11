package kr.co.tlf.ex.command;

import kr.co.tlf.ex.dao.MBDao;
import kr.co.tlf.ex.dto.MBDto;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class MBReplyViewCommand implements MBCommand {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        // 원본 게시글 번호와 페이지 정보 가져오기
        String nbBoard = request.getParameter("nbBoard");
        String page = request.getParameter("page");
        
        MBDao dao = new MBDao();
        MBDto dto = dao.getContent(nbBoard);
        
        // 답글 작성을 위한 원본 게시글 정보 설정
        request.setAttribute("content_view", dto);
        request.setAttribute("page", page);
        
        // 답글 모드 설정 (write_view.jsp에서 사용)
        request.setAttribute("reply", true);
    }
}