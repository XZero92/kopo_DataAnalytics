package List;

import java.util.List;

public class ArrayListEx {
    public static void main(String[] args) {
        BoardDao dao = new BoardDao();
        List<Board> list = dao.getBoardList();

        for(Board board : list) {
            System.out.println(board.toString());
        }
    }
}
