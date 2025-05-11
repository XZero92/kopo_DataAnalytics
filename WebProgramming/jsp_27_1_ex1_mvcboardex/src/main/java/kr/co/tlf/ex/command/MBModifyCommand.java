package kr.co.tlf.ex.command;

import kr.co.tlf.ex.dao.MBDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class MBModifyCommand implements MBCommand {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        String nbBoard = request.getParameter("nbBoard");
        String title = request.getParameter("title");
        String content = request.getParameter("content");
        String page = request.getParameter("page"); // 페이지 파라미터 추가

        MBDao dao = new MBDao();
        dao.modifyPost(nbBoard, title, content);

        // 수정 후 해당 게시글 보기로 리다이렉트
        request.setAttribute("viewPage", "content_view.do?nbBoard=" + nbBoard + "&page=" + page);
    }
}