package kr.co.tlf.ex.command;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import kr.co.tlf.ex.dao.MBDao;

public class MBWriteCommand implements MBcommand {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        String title = request.getParameter("title");
        String content = request.getParameter("content");
        String name = request.getParameter("name");

        MBDao dao = new MBDao();
        dao.writePost(name, title, content);

        request.setAttribute("viewPage", "list.do"); // 작성 후 리스트로 이동

    }
}
