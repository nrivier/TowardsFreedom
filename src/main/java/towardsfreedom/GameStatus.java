package towardsfreedom;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class GameStatus {

    private List<BoardMove> moves;
    private BoardState boardState;


    public GameStatus(GameStatus that) {
        this(that.boardState, that.moves);
    }

    public GameStatus(BoardState boardState, List<BoardMove> moves) {
        this.moves = new ArrayList<> (moves.size() + 1);
        this.moves.addAll(moves);
        this.boardState = new BoardState(boardState);
    }

    public boolean canDoMove(BoardMove boardMove) {
        return boardState.canMoveShape(boardMove.getBoardShape(),boardMove.getMoveDirection());
    }

    public GameStatus moveShape(BoardMove boardMove) {
        GameStatus gameStatus = new GameStatus(this);
        gameStatus.boardState.moveShape(boardMove.getBoardShape(), boardMove.getMoveDirection());
        gameStatus.moves.add(boardMove);
        return gameStatus;
    }

    public boolean isFinal() {
        return  boardState.isFinal();
    }

}
