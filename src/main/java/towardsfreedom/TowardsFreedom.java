package towardsfreedom;

import towardsfreedom.layouts.InitialLayout_0;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedList;
import java.util.Queue;

public class TowardsFreedom {
    private final Queue<GameStatus> gameStatusQueue = new LinkedList<>();
    private GameStatus  finalGameStatus = null;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

    public static void main(String[] args) {

        TowardsFreedom towardsFreedom = new TowardsFreedom();
        towardsFreedom.run();

    }

    public static BoardState getInitialState() {
        return new InitialLayout_0().setup();
    }

    public void run() {
        BoardState boardState = getInitialState();
        GameStatus initialGameStatus = new GameStatus(boardState, Collections.emptyList());
        gameStatusQueue.add(initialGameStatus);

        boolean foundSolution = false;

        long lastTime = System.currentTimeMillis();

        while (!foundSolution && gameStatusQueue.size() > 0) {

            long currentTime = System.currentTimeMillis();
            if (currentTime - lastTime >= 5*60*1000) {
                System.out.println("At "+ dateFormat.format(new Date(currentTime)) + " queue size is " + gameStatusQueue.size());
                lastTime = currentTime;
            }
            GameStatus gameStatus = gameStatusQueue.remove();

            for (BoardShape shape: gameStatus.getBoardState().getShapes()) {
                for (MoveDirection moveDirection: MoveDirection.values()) {
                    BoardMove boardMove = new BoardMove(shape, moveDirection);
                    if (gameStatus.canDoMove(boardMove)) {
                        GameStatus newGameStatus = gameStatus.moveShape(boardMove);
                        if (!newGameStatus.hasLoop()) {
                            foundSolution = newGameStatus.isFinal();
                            if (foundSolution) {
                                finalGameStatus = newGameStatus;
                                break;
                            } else {
                                gameStatusQueue.add(newGameStatus);
                            }
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
        if (!foundSolution) {
            System.out.println("Did not find a solution! :-(");
        }

    }
}
