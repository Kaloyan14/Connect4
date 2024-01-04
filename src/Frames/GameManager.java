package Frames;

import static Frames.Utils.Constants.*;
import static Frames.Utils.*;

public class GameManager {
    /*
    0  6 12 18 24 30 36
    1  7 13 19 25 31 37
    2  8 14 20 26 32 38
    3  9 15 21 27 33 39
    4 10 16 22 28 34 40
    5 11 17 23 29 35 41
     */
    public long[] board;

    public GameManager() {
        board = new long[3];
    }

    public int makeMove(int move) {
        int pos = bitscan((board[SIDE_A] | board[SIDE_B]) & COL[move]);
        //printBoard(new long[]{board[SIDE_A] | board[SIDE_B], 0, 0});
        if(pos % 6 != 0) {
            //System.out.println("Pos: " + pos);
            if(pos == 63) pos = (1 + move) * 6;
            board[(int)board[SIDE]] ^= 1L << (pos - 1);
            board[SIDE] = 1 - board[SIDE];
            return pos - 1;
        }
        return -1;
    }



}
