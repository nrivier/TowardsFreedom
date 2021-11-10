package towardsfreedom.layouts;

import towardsfreedom.BoardState;

public interface Layout {
     public int BOARD_HEIGHT = 5;
     public int BOARD_WIDTH = 4;
     BoardState setup();
}
