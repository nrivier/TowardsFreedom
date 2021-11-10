package towardsfreedom;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BoardShape {

    private int height;
    private int width;
    private int boardLocationX;
    private int boardLocationY;

    public BoardShape(BoardShape that) {
        this(that.height, that.width, that.boardLocationX, that.boardLocationY);
    }

    public void moveTo(int x, int y) {
        setBoardLocationX(x);
        setBoardLocationY(y);
    }


}
