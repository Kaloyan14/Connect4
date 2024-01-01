package Frames;

import java.awt.*;

public class Utils {

    public static class Constants {
        public final static long[] ROW = new long[]{0b1000001000001000001000001000001000001L, 0b10000010000010000010000010000010000010L, 0b100000100000100000100000100000100000100L, 0b1000001000001000001000001000001000001000L, 0b10000010000010000010000010000010000010000L, 0b100000100000100000100000100000100000100000L};
        public final static long[] COL = new long[]{0b111111L, 0b111111000000L, 0b111111000000000000L, 0b111111000000000000000000L, 0b111111000000000000000000000000L, 0b111111000000000000000000000000000000L, 0b111111000000000000000000000000000000000000L};
        public final static int[] INDEX64 = new int[]{
            63,  0, 58,  1, 59, 47, 53,  2,
            60, 39, 48, 27, 54, 33, 42,  3,
            61, 51, 37, 40, 49, 18, 28, 20,
            55, 30, 34, 11, 43, 14, 22,  4,
            62, 57, 46, 52, 38, 26, 32, 41,
            50, 36, 17, 19, 29, 10, 13, 21,
            56, 45, 25, 31, 35, 16,  9, 12,
            44, 24, 15,  8, 23,  7,  6,  5
        };
        public final static int SIDE_A = 0;
        public final static int SIDE_B = 1;
        public final static int SIDE = 2;
        public static final Color[] COLOR = new Color[]{Color.RED, Color.YELLOW};

    }

    public static void printBoard(long[] board) {
        long sideA = board[0], sideB = board[1];
        String[] result = new String[]{"", "", "", "", "", ""};
        for(int i = 0; i < 42; i++) {
            if(sideA % 2 == 1) result[i % 6] += "1 ";
            else if(sideB % 2 == 1) result[i % 6] += "2 ";
            else result[i % 6] += ". ";

            sideA /= 2;
            sideB /= 2;
        }

        for(int i = 0; i < 6; i++) {
            System.out.println(result[i]);
        }
        System.out.println(board[2] + "'s turn");
    }

    public static int bitscan(long n) {
        final long debruijn64 = 0x07EDD5E59A4E28C2L;
        return Constants.INDEX64[(int)(((n & -n) * debruijn64) >>> 58L)];
    }
}
