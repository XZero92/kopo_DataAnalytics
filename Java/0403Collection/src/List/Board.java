package List;

import java.util.*;

public class Board {
    private String title;
    private String contents;
    private String writer;

    Board(String title, String contents, String writer) {
        this.title = title;
        this.contents = contents;
        this.writer = writer;
    }

    @Override
    public String toString() {
        String strBoard = "제목: " + title + ", 내용: " + contents + ", 작성자: " + writer;
        return strBoard;
    }

    public static void main(String[] args) {
        List<Board> list = new ArrayList<Board>();

        Board board1 = new Board("제목1", "내용1", "작성자1");
        Board board2 = new Board("제목2", "내용2", "작성자2");

        list.add(board1);
        list.add(board2);

        System.out.println("게시글: " + list.get(0));
    }
}


