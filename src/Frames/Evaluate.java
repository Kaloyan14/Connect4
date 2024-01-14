package Frames;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static Frames.Utils.Constants.*;
import static Frames.Utils.bitscan;
import static Frames.Utils.checkWin;


public class Evaluate {
    static long nodes = 0;
    static List<Integer> validMoves(long[] node) {
        List<Integer> moves = new ArrayList<>();
        long bits = ~((node[SIDE_A] | node[SIDE_B])) & ROW[5];
        while(bits != 0) {
            moves.add(bitscan(bits) / 6);
            bits -= bits & -bits;
        }

        return moves;

    }

    static int evaluateBoard(long[] board) {
        int result = 0;
        long empty = ~(board[SIDE_A] & board[SIDE_B]);
        if(checkWin(board, SIDE_A)) return Integer.MAX_VALUE;
        if(checkWin(board, SIDE_B)) return Integer.MIN_VALUE;
        //2 row
        //0 0 1 1
        result += 3 * (Long.bitCount((empty & (empty >> 6) & (board[SIDE_A] >> 12) & (board[SIDE_A] >> 18) & ~COL[6] & ~COL[5] & ~COL[4]))
                     - Long.bitCount((empty & (empty >> 6) & (board[SIDE_B] >> 12) & (board[SIDE_B] >> 18) & ~COL[6] & ~COL[5] & ~COL[4])));
        //0 1 0 1
        result += 3 * (Long.bitCount((empty & (board[SIDE_A] >> 6) & (empty >> 12) & (board[SIDE_A] >> 18) & ~COL[6] & ~COL[5] & ~COL[4]))
                     - Long.bitCount((empty & (board[SIDE_B] >> 6) & (empty >> 12) & (board[SIDE_B] >> 18) & ~COL[6] & ~COL[5] & ~COL[4])));
        //0 1 1 0
        result += 3 * (Long.bitCount((empty & (board[SIDE_A] >> 6) & (board[SIDE_A] >> 12) & (empty >> 18) & ~COL[6] & ~COL[5] & ~COL[4]))
                     - Long.bitCount((empty & (board[SIDE_B] >> 6) & (board[SIDE_B] >> 12) & (empty >> 18) & ~COL[6] & ~COL[5] & ~COL[4])));
        //1 0 0 1
        result += 3 * (Long.bitCount((board[SIDE_A] & (empty >> 6) & (empty >> 12) & (board[SIDE_A] >> 18) & ~COL[6] & ~COL[5] & ~COL[4]))
                     - Long.bitCount((board[SIDE_B] & (empty >> 6) & (empty >> 12) & (board[SIDE_B] >> 18) & ~COL[6] & ~COL[5] & ~COL[4])));
        //1 0 1 0
        result += 3 * (Long.bitCount((board[SIDE_A] & (empty >> 6) & (board[SIDE_A] >> 12) & (empty >> 18) & ~COL[6] & ~COL[5] & ~COL[4]))
                     - Long.bitCount((board[SIDE_B] & (empty >> 6) & (board[SIDE_B] >> 12) & (empty >> 18) & ~COL[6] & ~COL[5] & ~COL[4])));
        //1 1 0 0
        result += 3 * (Long.bitCount((board[SIDE_A] & (board[SIDE_A] >> 6) & (empty >> 12) & (empty >> 18) & ~COL[6] & ~COL[5] & ~COL[4]))
                     - Long.bitCount((board[SIDE_B] & (board[SIDE_B] >> 6) & (empty >> 12) & (empty >> 18) & ~COL[6] & ~COL[5] & ~COL[4])));
        //3 row
        //0 1 1 1
        result += 5 * (Long.bitCount((empty & (board[SIDE_A] >> 6) & (board[SIDE_A] >> 12) & (board[SIDE_A] >> 18) & ~COL[6] & ~COL[5] & ~COL[4]))
                     - Long.bitCount((empty & (board[SIDE_B] >> 6) & (board[SIDE_B] >> 12) & (board[SIDE_B] >> 18) & ~COL[6] & ~COL[5] & ~COL[4])));
        //1 0 1 1
        result += 5 * (Long.bitCount((board[SIDE_A] & (empty >> 6) & (board[SIDE_A] >> 12) & (board[SIDE_A] >> 18) & ~COL[6] & ~COL[5] & ~COL[4]))
                     - Long.bitCount((board[SIDE_B] & (empty >> 6) & (board[SIDE_B] >> 12) & (board[SIDE_B] >> 18) & ~COL[6] & ~COL[5] & ~COL[4])));
        //1 1 0 1
        result += 5 * (Long.bitCount((board[SIDE_A] & (board[SIDE_A] >> 6) & (empty >> 12) & (board[SIDE_A] >> 18) & ~COL[6] & ~COL[5] & ~COL[4]))
                     - Long.bitCount((board[SIDE_B] & (board[SIDE_B] >> 6) & (empty >> 12) & (board[SIDE_B] >> 18) & ~COL[6] & ~COL[5] & ~COL[4])));
        //1 1 1 0
        result += 5 * (Long.bitCount((board[SIDE_A] & (board[SIDE_A] >> 6) & (board[SIDE_A] >> 12) & (empty >> 18) & ~COL[6] & ~COL[5] & ~COL[4]))
                     - Long.bitCount((board[SIDE_B] & (board[SIDE_B] >> 6) & (board[SIDE_B] >> 12) & (empty >> 18) & ~COL[6] & ~COL[5] & ~COL[4])));

        //2 column
        //0 0 1 1
        result += 3 * (Long.bitCount((empty & (empty << 1) & (board[SIDE_A] << 2) & (board[SIDE_A] << 3) & ~ROW[5] & ~ROW[4] & ~ROW[3]))
                     - Long.bitCount((empty & (empty << 1) & (board[SIDE_B] << 2) & (board[SIDE_B] << 3) & ~ROW[5] & ~ROW[4] & ~ROW[3])));
        //0 1 0 1
        result += 3 * (Long.bitCount((empty & (board[SIDE_A] << 1) & (empty << 2) & (board[SIDE_A] << 3) & ~ROW[5] & ~ROW[4] & ~ROW[3]))
                     - Long.bitCount((empty & (board[SIDE_B] << 1) & (empty << 2) & (board[SIDE_B] << 3) & ~ROW[5] & ~ROW[4] & ~ROW[3])));
        //0 1 1 0
        result += 3 * (Long.bitCount((empty & (board[SIDE_A] << 1) & (board[SIDE_A] << 2) & (empty << 3) & ~ROW[5] & ~ROW[4] & ~ROW[3]))
                     - Long.bitCount((empty & (board[SIDE_B] << 1) & (board[SIDE_B] << 2) & (empty << 3) & ~ROW[5] & ~ROW[4] & ~ROW[3])));
        //1 0 0 1
        result += 3 * (Long.bitCount((board[SIDE_A] & (empty << 1) & (empty << 2) & (board[SIDE_A] << 3) & ~ROW[5] & ~ROW[4] & ~ROW[3]))
                     - Long.bitCount((board[SIDE_B] & (empty << 1) & (empty << 2) & (board[SIDE_B] << 3) & ~ROW[5] & ~ROW[4] & ~ROW[3])));
        //1 0 1 0
        result += 3 * (Long.bitCount((board[SIDE_A] & (empty << 1) & (board[SIDE_A] << 2) & (empty << 3) & ~ROW[5] & ~ROW[4] & ~ROW[3]))
                     - Long.bitCount((board[SIDE_B] & (empty << 1) & (board[SIDE_B] << 2) & (empty << 3) & ~ROW[5] & ~ROW[4] & ~ROW[3])));
        //1 1 0 0
        result += 3 * (Long.bitCount((board[SIDE_A] & (board[SIDE_A] << 1) & (empty << 2) & (empty << 3) & ~ROW[5] & ~ROW[4] & ~ROW[3]))
                     - Long.bitCount((board[SIDE_B] & (board[SIDE_B] << 1) & (empty << 2) & (empty << 3) & ~ROW[5] & ~ROW[4] & ~ROW[3])));
        //3 column
        //0 1 1 1
        result += 5 * (Long.bitCount((empty & (board[SIDE_A] << 1) & (board[SIDE_A] << 2) & (board[SIDE_A] << 3) & ~ROW[5] & ~ROW[4] & ~ROW[3]))
                     - Long.bitCount((empty & (board[SIDE_B] << 1) & (board[SIDE_B] << 2) & (board[SIDE_B] << 3) & ~ROW[5] & ~ROW[4] & ~ROW[3])));
        //1 0 1 1
        result += 5 * (Long.bitCount((board[SIDE_A] & (empty << 1) & (board[SIDE_A] << 2) & (board[SIDE_A] << 3) & ~ROW[5] & ~ROW[4] & ~ROW[3]))
                     - Long.bitCount((board[SIDE_B] & (empty << 1) & (board[SIDE_B] << 2) & (board[SIDE_B] << 3) & ~ROW[5] & ~ROW[4] & ~ROW[3])));
        //1 1 0 1
        result += 5 * (Long.bitCount((board[SIDE_A] & (board[SIDE_A] << 1) & (empty << 2) & (board[SIDE_A] << 3) & ~ROW[5] & ~ROW[4] & ~ROW[3]))
                     - Long.bitCount((board[SIDE_B] & (board[SIDE_B] << 1) & (empty << 2) & (board[SIDE_B] << 3) & ~ROW[5] & ~ROW[4] & ~ROW[3])));
        //1 1 1 0
        result += 5 * (Long.bitCount((board[SIDE_A] & (board[SIDE_A] << 1) & (board[SIDE_A] << 2) & (empty << 3) & ~ROW[5] & ~ROW[4] & ~ROW[3]))
                     - Long.bitCount((board[SIDE_B] & (board[SIDE_B] << 1) & (board[SIDE_B] << 2) & (empty << 3) & ~ROW[5] & ~ROW[4] & ~ROW[3])));

        //2 down
        //0 0 1 1
        result += 3 * (Long.bitCount((empty & (empty << 7) & (board[SIDE_A] << 14) & (board[SIDE_A] << 21) & ~ROW[5] & ~ROW[4] & ~ROW[3] & ~COL[2] & ~COL[1] & ~COL[0]))
                     - Long.bitCount((empty & (empty << 7) & (board[SIDE_B] << 14) & (board[SIDE_B] << 21) & ~ROW[5] & ~ROW[4] & ~ROW[3] & ~COL[2] & ~COL[1] & ~COL[0])));
        //0 1 0 1
        result += 3 * (Long.bitCount((empty & (board[SIDE_A] << 7) & (empty << 14) & (board[SIDE_A] << 21) & ~ROW[5] & ~ROW[4] & ~ROW[3] & ~COL[2] & ~COL[1] & ~COL[0]))
                     - Long.bitCount((empty & (board[SIDE_B] << 7) & (empty << 14) & (board[SIDE_B] << 21) & ~ROW[5] & ~ROW[4] & ~ROW[3] & ~COL[2] & ~COL[1] & ~COL[0])));
        //0 1 1 0
        result += 3 * (Long.bitCount((empty & (board[SIDE_A] << 7) & (board[SIDE_A] << 14) & (empty << 21) & ~ROW[5] & ~ROW[4] & ~ROW[3] & ~COL[2] & ~COL[1] & ~COL[0]))
                     - Long.bitCount((empty & (board[SIDE_B] << 7) & (board[SIDE_B] << 14) & (empty << 21) & ~ROW[5] & ~ROW[4] & ~ROW[3] & ~COL[2] & ~COL[1] & ~COL[0])));
        //1 0 0 1
        result += 3 * (Long.bitCount((board[SIDE_A] & (empty << 7) & (empty << 14) & (board[SIDE_A] << 21) & ~ROW[5] & ~ROW[4] & ~ROW[3] & ~COL[2] & ~COL[1] & ~COL[0]))
                     - Long.bitCount((board[SIDE_B] & (empty << 7) & (empty << 14) & (board[SIDE_B] << 21) & ~ROW[5] & ~ROW[4] & ~ROW[3] & ~COL[2] & ~COL[1] & ~COL[0])));
        //1 0 1 0
        result += 3 * (Long.bitCount((board[SIDE_A] & (empty << 7) & (board[SIDE_A] << 14) & (empty << 21) & ~ROW[5] & ~ROW[4] & ~ROW[3] & ~COL[2] & ~COL[1] & ~COL[0]))
                     - Long.bitCount((board[SIDE_B] & (empty << 7) & (board[SIDE_B] << 14) & (empty << 21) & ~ROW[5] & ~ROW[4] & ~ROW[3] & ~COL[2] & ~COL[1] & ~COL[0])));
        //1 1 0 0
        result += 3 * (Long.bitCount((board[SIDE_A] & (board[SIDE_A] << 7) & (empty << 14) & (empty << 21) & ~ROW[5] & ~ROW[4] & ~ROW[3] & ~COL[2] & ~COL[1] & ~COL[0]))
                     - Long.bitCount((board[SIDE_B] & (board[SIDE_B] << 7) & (empty << 14) & (empty << 21) & ~ROW[5] & ~ROW[4] & ~ROW[3] & ~COL[2] & ~COL[1] & ~COL[0])));
        //3 down
        //0 1 1 1
        result += 5 * (Long.bitCount((empty & (board[SIDE_A] << 7) & (board[SIDE_A] << 14) & (board[SIDE_A] << 21) & ~ROW[5] & ~ROW[4] & ~ROW[3] & ~COL[2] & ~COL[1] & ~COL[0]))
                     - Long.bitCount((empty & (board[SIDE_B] << 7) & (board[SIDE_B] << 14) & (board[SIDE_B] << 21) & ~ROW[5] & ~ROW[4] & ~ROW[3] & ~COL[2] & ~COL[1] & ~COL[0])));
        //1 0 1 1
        result += 5 * (Long.bitCount((board[SIDE_A] & (empty << 7) & (board[SIDE_A] << 14) & (board[SIDE_A] << 21) & ~ROW[5] & ~ROW[4] & ~ROW[3] & ~COL[2] & ~COL[1] & ~COL[0]))
                     - Long.bitCount((board[SIDE_B] & (empty << 7) & (board[SIDE_B] << 14) & (board[SIDE_B] << 21) & ~ROW[5] & ~ROW[4] & ~ROW[3] & ~COL[2] & ~COL[1] & ~COL[0])));
        //1 1 0 1
        result += 5 * (Long.bitCount((board[SIDE_A] & (board[SIDE_A] << 7) & (empty << 14) & (board[SIDE_A] << 21) & ~ROW[5] & ~ROW[4] & ~ROW[3] & ~COL[2] & ~COL[1] & ~COL[0]))
                     - Long.bitCount((board[SIDE_B] & (board[SIDE_B] << 7) & (empty << 14) & (board[SIDE_B] << 21) & ~ROW[5] & ~ROW[4] & ~ROW[3] & ~COL[2] & ~COL[1] & ~COL[0])));
        //1 1 1 0
        result += 5 * (Long.bitCount((board[SIDE_A] & (board[SIDE_A] << 7) & (board[SIDE_A] << 14) & (empty << 21) & ~ROW[5] & ~ROW[4] & ~ROW[3] & ~COL[2] & ~COL[1] & ~COL[0]))
                     - Long.bitCount((board[SIDE_B] & (board[SIDE_B] << 7) & (board[SIDE_B] << 14) & (empty << 21) & ~ROW[5] & ~ROW[4] & ~ROW[3] & ~COL[2] & ~COL[1] & ~COL[0])));

        //2 up
        //0 0 1 1
        result += 3 * (Long.bitCount((empty & (empty >> 5) & (board[SIDE_A] >> 10) & (board[SIDE_A] >> 15) & ~ROW[5] & ~ROW[4] & ~ROW[3] & ~COL[6] & ~COL[5] & ~COL[4]))
                     - Long.bitCount((empty & (empty >> 5) & (board[SIDE_B] >> 10) & (board[SIDE_B] >> 15) & ~ROW[5] & ~ROW[4] & ~ROW[3] & ~COL[6] & ~COL[5] & ~COL[4])));
        //0 1 0 1
        result += 3 * (Long.bitCount((empty & (board[SIDE_A] >> 5) & (empty >> 10) & (board[SIDE_A] >> 15) & ~ROW[5] & ~ROW[4] & ~ROW[3] & ~COL[6] & ~COL[5] & ~COL[4]))
                     - Long.bitCount((empty & (board[SIDE_B] >> 5) & (empty >> 10) & (board[SIDE_B] >> 15) & ~ROW[5] & ~ROW[4] & ~ROW[3] & ~COL[6] & ~COL[5] & ~COL[4])));
        //0 1 1 0
        result += 3 * (Long.bitCount((empty & (board[SIDE_A] >> 5) & (board[SIDE_A] >> 10) & (empty >> 15) & ~ROW[5] & ~ROW[4] & ~ROW[3] & ~COL[6] & ~COL[5] & ~COL[4]))
                     - Long.bitCount((empty & (board[SIDE_B] >> 5) & (board[SIDE_B] >> 10) & (empty >> 15) & ~ROW[5] & ~ROW[4] & ~ROW[3] & ~COL[6] & ~COL[5] & ~COL[4])));
        //1 0 0 1
        result += 3 * (Long.bitCount((board[SIDE_A] & (empty >> 5) & (empty >> 10) & (board[SIDE_A] >> 15) & ~ROW[5] & ~ROW[4] & ~ROW[3] & ~COL[6] & ~COL[5] & ~COL[4]))
                     - Long.bitCount((board[SIDE_B] & (empty >> 5) & (empty >> 10) & (board[SIDE_B] >> 15) & ~ROW[5] & ~ROW[4] & ~ROW[3] & ~COL[6] & ~COL[5] & ~COL[4])));
        //1 0 1 0
        result += 3 * (Long.bitCount((board[SIDE_A] & (empty >> 5) & (board[SIDE_A] >> 10) & (empty >> 15) & ~ROW[5] & ~ROW[4] & ~ROW[3] & ~COL[6] & ~COL[5] & ~COL[4]))
                     - Long.bitCount((board[SIDE_B] & (empty >> 5) & (board[SIDE_B] >> 10) & (empty >> 15) & ~ROW[5] & ~ROW[4] & ~ROW[3] & ~COL[6] & ~COL[5] & ~COL[4])));
        //1 1 0 0
        result += 3 * (Long.bitCount((board[SIDE_A] & (board[SIDE_A] >> 5) & (empty >> 10) & (empty >> 15) & ~ROW[5] & ~ROW[4] & ~ROW[3] & ~COL[6] & ~COL[5] & ~COL[4]))
                     - Long.bitCount((board[SIDE_B] & (board[SIDE_B] >> 5) & (empty >> 10) & (empty >> 15) & ~ROW[5] & ~ROW[4] & ~ROW[3] & ~COL[6] & ~COL[5] & ~COL[4])));
        //3 up
        //0 1 1 1
        result += 5 * (Long.bitCount((empty & (board[SIDE_A] >> 5) & (board[SIDE_A] >> 10) & (board[SIDE_A] >> 15) & ~ROW[5] & ~ROW[4] & ~ROW[3] & ~COL[6] & ~COL[5] & ~COL[4]))
                     - Long.bitCount((empty & (board[SIDE_B] >> 5) & (board[SIDE_B] >> 10) & (board[SIDE_B] >> 15) & ~ROW[5] & ~ROW[4] & ~ROW[3] & ~COL[6] & ~COL[5] & ~COL[4])));
        //1 0 1 1
        result += 5 * (Long.bitCount((board[SIDE_A] & (empty >> 5) & (board[SIDE_A] >> 10) & (board[SIDE_A] >> 15) & ~ROW[5] & ~ROW[4] & ~ROW[3] & ~COL[6] & ~COL[5] & ~COL[4]))
                     - Long.bitCount((board[SIDE_B] & (empty >> 5) & (board[SIDE_B] >> 10) & (board[SIDE_B] >> 15) & ~ROW[5] & ~ROW[4] & ~ROW[3] & ~COL[6] & ~COL[5] & ~COL[4])));
        //1 1 0 1
        result += 5 * (Long.bitCount((board[SIDE_A] & (board[SIDE_A] >> 5) & (empty >> 10) & (board[SIDE_A] >> 15) & ~ROW[5] & ~ROW[4] & ~ROW[3] & ~COL[6] & ~COL[5] & ~COL[4]))
                     - Long.bitCount((board[SIDE_B] & (board[SIDE_B] >> 5) & (empty >> 10) & (board[SIDE_B] >> 15) & ~ROW[5] & ~ROW[4] & ~ROW[3] & ~COL[6] & ~COL[5] & ~COL[4])));
        //1 1 1 0
        result += 5 * (Long.bitCount((board[SIDE_A] & (board[SIDE_A] >> 5) & (board[SIDE_A] >> 10) & (empty >> 15) & ~ROW[5] & ~ROW[4] & ~ROW[3] & ~COL[6] & ~COL[5] & ~COL[4]))
                     - Long.bitCount((board[SIDE_B] & (board[SIDE_B] >> 5) & (board[SIDE_B] >> 10) & (empty >> 15) & ~ROW[5] & ~ROW[4] & ~ROW[3] & ~COL[6] & ~COL[5] & ~COL[4])));

        return result;
    }

    private static long[] makeMove(long[] board, int move) {
        int pos = bitscan((board[SIDE_A] | board[SIDE_B]) & COL[move]);
        //printBoard(new long[]{board[SIDE_A] | board[SIDE_B], 0, 0});
        if(pos % 6 != 0) {
            //System.out.println("Pos: " + pos);
            if(pos == 63) pos = (1 + move) * 6;
            board[(int)board[SIDE]] ^= 1L << (pos - 1);
            board[SIDE] = 1 - board[SIDE];
        }
        return board;
    }

    static Pair<Integer, Integer> alphaBetaSoft(long[] node, int depth, int alpha, int beta, boolean maximizingPlayer) {
        nodes++;
        List<Integer> moves = validMoves(node);
        //terminal cases
        if(depth == 0 || checkWin(node, SIDE_A) || checkWin(node, SIDE_B) || moves.isEmpty()) {
            //A won
            if(checkWin(node, SIDE_A)) {
                return new Pair<>(Integer.MAX_VALUE, -1);
            }
            //B won
            if(checkWin(node, SIDE_B)) {
                return new Pair<>(Integer.MIN_VALUE, -1);
            }
            //draw
            if(depth != 0) {
                return new Pair<>(0, -1);
            }
            //depth is 0
            return new Pair<>(evaluateBoard(node), -1);
        }
        //Side A
        if(maximizingPlayer) {
            int val = Integer.MIN_VALUE;
            int col = moves.get(0);
            for(int i : moves) {
                long[] copy = makeMove(Arrays.copyOf(node, node.length), i);
                int buff = alphaBetaSoft(copy, depth - 1, alpha, beta, false).first;
                if(buff > val) {
                    val = buff;
                    col = i;
                }
                alpha = Math.max(val, alpha);
                if(val >= beta) {
                    break;
                }
            }
            return new Pair<>(val, col);
        }
        //Side B
        int val = Integer.MAX_VALUE;
        int col = moves.get(0);
        for(int i : moves) {
            long[] copy = makeMove(Arrays.copyOf(node, node.length), i);
            int buff = alphaBetaSoft(copy, depth - 1, alpha, beta, true).first;
            if(buff < val) {
                val = buff;
                col = i;
            }
            beta = Math.min(val, beta);
            if(val <= alpha) {
                break;
            }
        }
        return new Pair<>(val, col);
    }
}
