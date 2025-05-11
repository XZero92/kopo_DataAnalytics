package kr.co.tlf.ex.command;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.co.tlf.ex.dao.MBDao;
import kr.co.tlf.ex.dto.MBDto;

public class MBWriteCommand implements MBCommand {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        // 1. 폼에서 전송된 데이터 가져오기
        String writer = request.getParameter("author");
        String title = request.getParameter("title");
        String content = request.getParameter("content");

        // 2. DTO 객체 생성 및 데이터 설정
        MBDto dto = new MBDto();
        dto.setNmWriter(writer);
        dto.setNmTitle(title);
        dto.setNmContent(content);
        dto.setCbContent(content); // CLOB 필드에도 동일한 내용 설정
        
        // ID_FILE은 파일 첨부 기능 구현 시 설정, 현재는 null
        dto.setIdFile(null); 

        // 3. DAO 객체 생성 및 메소드 호출
        MBDao dao = new MBDao();
        int result = dao.insertPost(dto);

        // 4. 결과에 따른 처리
        if (result > 0) {
            request.setAttribute("viewPage", "list.do");
        } else {
            request.setAttribute("viewPage", "write_view.jsp");
            request.setAttribute("errorMessage", "게시글 등록에 실패했습니다. 다시 시도해주세요.");
        }
    }
}