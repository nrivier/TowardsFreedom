package towardsfreedom;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BoardMove {
    BoardShape boardShape;
    MoveDirection moveDirection;
}
