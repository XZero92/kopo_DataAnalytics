package kr.co.tlf.ex.command;

import kr.co.tlf.ex.dao.MBDao;
import kr.co.tlf.ex.dto.MBDto;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class MBModifyViewCommand implements MBCommand {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        String nbBoard = request.getParameter("nbBoard");
        String page = request.getParameter("page");
        
        MBDao dao = new MBDao();
        MBDto dto = dao.getContent(nbBoard);
        
        // content_view에서 사용하는 이름과 동일하게 설정
        request.setAttribute("content_view", dto);
        request.setAttribute("page", page);
        
        // 수정 모드 설정
        request.setAttribute("modify", true);
        
        // write_view.jsp로 포워딩하도록 설정
        request.setAttribute("viewPage", "write_view.jsp");
    }
}