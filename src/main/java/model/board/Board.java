package model.board;

import model.livings.Putable;

public class Board {
    private int X;
    private int Y;

    private final Putable[][] board;

    public Board(int x, int y) {
        X = x;
        Y = y;
        board = new Putable[X][Y];
    }

    public int getX() {
        return X;
    }

    public void setX(int x) {
        X = x;
    }

    public int getY() {
        return Y;
    }

    public void setY(int y) {
        Y = y;
    }

    public Putable get(int x, int y) {
        return board[x][y];
    }

    public void put(int x, int y, Putable object) {
        board[x][y] = object;
    }


}
