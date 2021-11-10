package towardsfreedom.layouts;

import towardsfreedom.BoardState;
import towardsfreedom.BoardShape;

public class InitialLayout_0 implements Layout {

    @Override
    public BoardState setup() {

        BoardState boardState = new BoardState(Layout.BOARD_HEIGHT, Layout.BOARD_WIDTH);
        boardState.addShape(new BoardShape(2,1,0,0));
        boardState.addShape(new BoardShape(2,2,0,1));
        boardState.addShape(new BoardShape(2,1,0,3));
        boardState.addShape(new BoardShape(1,2,2,1));
        boardState.addShape(new BoardShape(2,1,3,0));
        boardState.addShape(new BoardShape(1,1,3,1));
        boardState.addShape(new BoardShape(1,1,3,2));
        boardState.addShape(new BoardShape(1,1,4,1));
        boardState.addShape(new BoardShape(1,1,4,2));
        boardState.addShape(new BoardShape(2,1,3,3));
        return boardState;
    }
}
