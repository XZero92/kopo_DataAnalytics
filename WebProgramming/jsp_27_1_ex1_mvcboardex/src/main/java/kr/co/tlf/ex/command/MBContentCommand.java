package kr.co.tlf.ex.command;

import kr.co.tlf.ex.dto.MBDto;
import kr.co.tlf.ex.dao.MBDao;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class MBContentCommand implements MBCommand {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        // 요청 파라미터 이름을 제대로 확인
        String nbBoard = request.getParameter("nbBoard");
        
        // 페이지 정보 유지를 위해 현재 페이지 번호 받기
        String page = request.getParameter("page");
        if (page == null || page.trim().isEmpty()) {
            page = "1";
        }
        
        MBDao dao = new MBDao();
        
        // 1. 조회수 증가
        dao.updateHit(nbBoard);
        
        // 2. 게시글 정보 조회
        MBDto dto = dao.getContent(nbBoard);
        
        // 3. request에 데이터 저장
        request.setAttribute("content_view", dto);
        request.setAttribute("page", page); // 목록으로 돌아갈 때 페이지 정보 유지
    }
}