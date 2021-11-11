package towardsfreedom;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;


@RequiredArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class BoardState {

    private int height;
    private int width;

    private final List<BoardShape> shapes = new ArrayList<>();
    private boolean[][] boardMap;

    public BoardState(int height, int width) {
        this.height = height;
        this.width = width;
        this.boardMap = prepareBoardMap();
    }

    public BoardState(BoardState that) {
        this(that.height, that.width);
        that.shapes.forEach(shape -> addShape(new BoardShape(shape)));
    }

    public boolean isLocationInsideBoard(int x, int y){
        return x >=0 && x<height && y >= 0 && y < width;
    }

    public void addShape(BoardShape boardShape){
        if (canAddShape(boardShape)) {
            shapes.add(boardShape);
            updateBoardMap(boardShape);
        }

    }

    private boolean[][] prepareBoardMap() {
        boolean[][] boardMap = new boolean[height][width];
        for (int i = 0; i < height; i++) {
            for ( int j=0 ; j < width; j++) {
                boardMap[i][j] = false;
            }
        }

        for (BoardShape boardShape: shapes) {
            for (int i = 0; i < boardShape.getHeight(); i++) {
                for ( int j=0 ; j < boardShape.getWidth(); j++) {
                    boardMap[i + boardShape.getBoardLocationX()][j + boardShape.getBoardLocationY()] = true;
                }
            }
        }

        return boardMap;
    }

    private boolean canAddShape(BoardShape boardShape) {

        boolean answer = true;
        for (int i = 0; i < boardShape.getHeight() && answer; i++) {
            for ( int j=0 ; j < boardShape.getWidth() && answer; j++) {
                answer = answer && !boardMap[i + boardShape.getBoardLocationX()][j + boardShape.getBoardLocationY()] ;
            }
        }

        return answer;

    }

    private void updateBoardMap(BoardShape boardShape) {
        for (int i = 0; i < boardShape.getHeight(); i++) {
            for ( int j=0 ; j < boardShape.getWidth() ; j++) {
                boardMap[i + boardShape.getBoardLocationX()][j + boardShape.getBoardLocationY()] = true;
            }
        }
    }

    public boolean canMoveShape(BoardShape boardShape, MoveDirection moveDirection) {
        boolean answer = shapes.contains(boardShape);
        switch (moveDirection) {
            case UP:
                for (int j = 0; j < boardShape.getWidth() && answer; j++) {
                    answer = answer && isLocationInsideBoard(boardShape.getBoardLocationX() - 1, boardShape.getBoardLocationY() + j) &&
                              !boardMap[boardShape.getBoardLocationX() - 1 ][boardShape.getBoardLocationY() + j];
                }
                break;
            case DOWN:
                for (int j = 0; j < boardShape.getWidth() && answer; j++) {
                    answer = answer && isLocationInsideBoard(boardShape.getBoardLocationX() + boardShape.getHeight(), boardShape.getBoardLocationY() + j) &&
                              !boardMap[boardShape.getBoardLocationX() + boardShape.getHeight()][boardShape.getBoardLocationY() + j];
                }
                break;
            case LEFT:
                for (int i = 0; i < boardShape.getHeight() && answer; i++) {
                    answer = answer && isLocationInsideBoard(boardShape.getBoardLocationX() + i, boardShape.getBoardLocationY() - 1) &&
                            !boardMap[boardShape.getBoardLocationX() + i][boardShape.getBoardLocationY() - 1];
                }
                break;
            case RIGHT:
                for (int i = 0; i < boardShape.getHeight() && answer; i++) {
                    answer = answer && isLocationInsideBoard(boardShape.getBoardLocationX() + i, boardShape.getBoardLocationY() + boardShape.getWidth()) &&
                            !boardMap[boardShape.getBoardLocationX() + i][boardShape.getBoardLocationY() + boardShape.getWidth()];
                }
                break;

        }
        return answer;
    }

    public void moveShape(BoardShape boardShape, MoveDirection moveDirection) {
        BoardShape shape = shapes.stream().filter( s -> s.equals(boardShape)).findFirst().get();
        switch (moveDirection) {
            case UP:
                shape.setBoardLocationX(boardShape.getBoardLocationX() - 1);
                break;
            case DOWN:
                shape.setBoardLocationX(boardShape.getBoardLocationX() + 1);
                break;
            case LEFT:
                shape.setBoardLocationY(boardShape.getBoardLocationY() - 1);
                break;
            case RIGHT:
                shape.setBoardLocationY(boardShape.getBoardLocationY() + 1);
                break;
        }
        boardMap = prepareBoardMap();
    }

    public boolean isFinal() {
        return shapes.stream().anyMatch(
                shape ->
                        shape.getHeight() == 2 &&
                        shape.getWidth() == 2 &&
                        shape.getBoardLocationX() == height - 2
                ) ;
    }

}
