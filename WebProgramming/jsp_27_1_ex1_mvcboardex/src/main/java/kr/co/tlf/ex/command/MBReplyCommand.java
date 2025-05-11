package kr.co.tlf.ex.command;

import kr.co.tlf.ex.dao.MBDao;
import kr.co.tlf.ex.dto.MBDto;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class MBReplyCommand implements MBCommand {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        // 원본 게시글 번호 가져오기
        String nbBoard = request.getParameter("nbBoard");
        String page = request.getParameter("page");
        
        // 폼에서 전달된 답글 정보 가져오기
        String title = request.getParameter("title");
        String content = request.getParameter("content");
        String author = request.getParameter("author");
        
        // 원본 게시글 정보 가져오기 (그룹, 스텝, 인덴트 정보 필요)
        MBDao dao = new MBDao();
        MBDto original = dao.getContent(nbBoard);
        
        // 답글 정보 설정
        MBDto replyDto = new MBDto();
        replyDto.setNmTitle(title);
        replyDto.setNmContent(content);
        replyDto.setCbContent(content); // CLOB 필드에도 동일한 내용 설정
        replyDto.setNmWriter(author);
        replyDto.setNbGroup(original.getNbGroup());   // 원본 게시글과 같은 그룹
        replyDto.setNbStep(original.getNbStep()); // 원본과 같은 스텝
        replyDto.setNbIndent(original.getNbIndent()); // 원본과 같은 인덴트

        dao.insertReply(replyDto);
        
        // 목록 페이지로 리다이렉트 설정
        request.setAttribute("viewPage", "list.do?page=" + page);
    }
}