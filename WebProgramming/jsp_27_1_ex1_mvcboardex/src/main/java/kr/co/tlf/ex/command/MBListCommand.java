package kr.co.tlf.ex.command;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import kr.co.tlf.ex.dao.MBDao;
import kr.co.tlf.ex.dto.MBDto;

import java.util.List;

public class MBListCommand implements MBCommand {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        MBDao dao = new MBDao();
        
        // 페이징 처리를 위한 변수들
        int pageSize = 10; // 한 페이지에 표시할 게시글 수
        int pageBlock = 5; // 페이지 번호 블록 단위
        int totalCount = dao.getTotalCount(); // 전체 게시글 수
        
        // 현재 페이지 설정 (요청 파라미터에서 가져옴, 기본값은 1)
        String pageNum = request.getParameter("page");
        if (pageNum == null || pageNum.isEmpty()) {
            pageNum = "1";
        }
        int currentPage = Integer.parseInt(pageNum);
        
        // 페이징 계산
        int totalPage = (totalCount + pageSize - 1) / pageSize; // 전체 페이지 수
        int startRow = (currentPage - 1) * pageSize + 1; // 시작 행
        int endRow = currentPage * pageSize; // 끝 행
        
        // 페이지 블록 계산
        int startPage = ((currentPage - 1) / pageBlock) * pageBlock + 1; // 시작 페이지 번호
        int endPage = startPage + pageBlock - 1; // 끝 페이지 번호
        if (endPage > totalPage) {
            endPage = totalPage;
        }
        
        // 게시글 목록 조회
        List<MBDto> boardList = dao.getBoardList(startRow, endRow);
        
        // 요청 속성 설정
        request.setAttribute("boardList", boardList);
        request.setAttribute("totalCount", totalCount);
        request.setAttribute("pageSize", pageSize);
        request.setAttribute("totalPage", totalPage);
        request.setAttribute("currentPage", currentPage);
        request.setAttribute("startPage", startPage);
        request.setAttribute("endPage", endPage);
        request.setAttribute("pageBlock", pageBlock);
    }
}