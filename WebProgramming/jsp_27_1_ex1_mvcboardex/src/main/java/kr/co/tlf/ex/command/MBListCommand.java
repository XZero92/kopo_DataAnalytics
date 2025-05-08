package kr.co.tlf.ex.command;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import kr.co.tlf.ex.dao.MBDao;
import kr.co.tlf.ex.dto.MBDto;

import java.util.List;

public class MBListCommand implements MBcommand {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        MBDao dao = new MBDao();
        List<MBDto> boardList = dao.getAllPosts();
        request.setAttribute("boardList", boardList);
        request.setAttribute("viewPage", "list.jsp");

    }
}
