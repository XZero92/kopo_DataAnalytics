package kr.co.tlf.ex.command;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.co.tlf.ex.dao.MBDao;
import kr.co.tlf.ex.dto.MBDto;

public class MBWriteCommand implements MBcommand {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        // 1. 폼에서 전송된 데이터 가져오기
        // write_view.jsp의 <input name="author">, <input name="title">, <textarea name="content"> 와 일치해야 함
        String writer = request.getParameter("author"); // 또는 "nmWriter" 등 JSP의 name 속성값
        String title = request.getParameter("title");
        String content = request.getParameter("content");

        // 2. DTO 객체 생성 및 데이터 설정
        MBDto dto = new MBDto();
        dto.setNmWriter(writer);
        dto.setNmTitle(title);
        dto.setNmContent(content);
        
        // ID_FILE은 파일 첨부 기능 구현 시 설정, 현재는 null
        dto.setIdFile(null); 
        // NB_BOARD, NB_GROUP, NB_STEP, NB_INDENT는 DAO에서 시퀀스 및 기본값으로 설정됨

        // 3. DAO 객체 생성 및 메소드 호출
        MBDao dao = new MBDao();
        int result = dao.insertBoard(dto);

        // 4. 결과에 따른 처리 (일반적으로 목록 페이지로 리다이렉트)
        if (result > 0) {
            // 성공 시 게시글 목록 페이지로 이동하도록 설정
            // FrontController에서 이 viewPage 값을 보고 list.do 등으로 리다이렉트 처리
            request.setAttribute("viewPage", "list.do"); // 또는 성공 메시지를 보여줄 페이지
        } else {
            // 실패 시 에러 페이지 또는 다시 작성 페이지로 이동하도록 설정
            request.setAttribute("viewPage", "write_view.jsp"); // 또는 에러 페이지
            request.setAttribute("errorMessage", "게시글 등록에 실패했습니다. 다시 시도해주세요.");
        }
    }
}