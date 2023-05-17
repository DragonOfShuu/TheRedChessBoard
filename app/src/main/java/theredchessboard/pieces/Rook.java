package theredchessboard.pieces;

import theredchessboard.Board;

public class Rook extends AbstractPiece {
    public Rook(Board board, int x, int y, String theme) {
        super(board, x, y, theme);
    }

    @Override
    public boolean pieceCanMove(int x, int y) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public String pieceName() {
        return "rook";
    }
}
