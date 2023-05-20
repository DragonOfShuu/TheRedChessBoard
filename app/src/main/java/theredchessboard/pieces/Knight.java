package theredchessboard.pieces;

import theredchessboard.Board;

public class Knight extends AbstractPiece {
    public Knight(Board board, int x, int y) {
        super(board, x, y);
    }

    @Override
    public boolean pieceCanMove(int x, int y) {
        if ((Math.abs(this.x-x)==2 && Math.abs(this.y-y)==1)
            || (Math.abs(this.x-x)==1 && Math.abs(this.y-y)==2)) return true;
        
        return false;
    }

    @Override
    public String pieceName() {
        return "knight";
    }
}
