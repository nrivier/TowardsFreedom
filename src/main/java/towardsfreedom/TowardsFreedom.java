package towardsfreedom;

import towardsfreedom.layouts.InitialLayout_0;

import java.util.Collections;
import java.util.Stack;

public class TowardsFreedom {
    private Stack<GameStatus> gameStatusStack = new Stack<>();
    private GameStatus  finalGameStatus = null;


    public static void main(String[] args) {

        TowardsFreedom towardsFreedom = new TowardsFreedom();
        towardsFreedom.run();

    }

    public void run() {
        BoardState boardState = new InitialLayout_0().setup();
        GameStatus initialGameStatus = new GameStatus(boardState, Collections.emptyList());
        gameStatusStack.push(initialGameStatus);

        boolean foundSolution = false;


        while (!foundSolution && gameStatusStack.size() > 0) {

            GameStatus gameStatus = gameStatusStack.pop();

            for (BoardShape shape: gameStatus.getBoardState().getShapes()) {
                for (MoveDirection moveDirection: MoveDirection.values()) {
                    BoardMove boardMove = new BoardMove(shape, moveDirection);
                    if (gameStatus.canDoMove(boardMove)) {
                        GameStatus newGameStatus = gameStatus.moveShape(boardMove);
                        foundSolution = newGameStatus.isFinal();
                        if (foundSolution) {
                            finalGameStatus = newGameStatus;
                            break;
                        } else {
                            gameStatusStack.push(newGameStatus);
                        }
                    }
                }
            }

            if (foundSolution) {
                finalGameStatus.getMoves().forEach(
                        move -> System.out.println("Move " + move.toString())
                );
            }

        }
        System.out.println("Did not find a solution! :-(");
    }
}
