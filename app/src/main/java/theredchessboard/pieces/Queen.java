package theredchessboard.pieces;

import theredchessboard.Board;

public class Queen extends AbstractPiece {
    public Queen(Board board, int x, int y) {
        super(board, x, y);
    }

    @Override
    public boolean pieceCanMove(int x, int y) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public String pieceName() {
        return "queen";
    }
}
