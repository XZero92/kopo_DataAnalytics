package List;

import java.util.ArrayList;
import java.util.List;

public class BoardDao {
    private ArrayList<Board> boardList;

    BoardDao() {
        boardList = new ArrayList<Board>();
        boardList.add(new Board("SampleT", "SampleC", "SampleW"));
        boardList.add(new Board("TestT", "TestC", "TestW"));
    }

    public List<Board> getBoardList() {
        return boardList;
    }
}
