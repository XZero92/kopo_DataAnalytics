package kr.co.tlf.ex.command;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class MBModifyCommand implements MBcommand {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        String nbMvcBoard = request.getParameter("nbMvcBoard");
        String title = request.getParameter("title");
        String content = request.getParameter("content");

        /*MBDao dao = new MBDao();
        dao.modifyPost(nbMvcBoard, title, content);*/

        request.setAttribute("viewPage", "list.do"); // 수정 후 리스트로 이동
    }
}
