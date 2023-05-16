package theredchessboard.pieces;

import theredchessboard.Board;

public class Pawn extends AbstractPiece {
    public Pawn(Board board, int x, int y) {
        super(board, x, y);
    }

    @Override
    public boolean pieceCanMove(int x, int y) {
        // TODO Auto-generated method stub
        return false;
    }
}
