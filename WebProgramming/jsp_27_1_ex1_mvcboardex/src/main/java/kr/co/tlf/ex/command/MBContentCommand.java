package kr.co.tlf.ex.command;

import kr.co.tlf.ex.dto.MBDto;
import kr.co.tlf.ex.dao.MBDao;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class MBContentCommand implements MBcommand {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        String nbMvcBoard = request.getParameter("nbMvcBoard");
        MBDao dao = new MBDao();
        MBDto dto = dao.getContentView(nbMvcBoard);

        request.setAttribute("content_view", dto);
    }
}
