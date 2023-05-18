package theredchessboard.pieces;
import theredchessboard.Board;

public class King extends AbstractPiece {
    public King(Board board, int x, int y) {
        super(board, x, y);
    }

    @Override
    public boolean pieceCanMove(int x, int y) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public String pieceName() {
        return "king";
    }
}
