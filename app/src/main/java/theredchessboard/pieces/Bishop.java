package theredchessboard.pieces;
import theredchessboard.Board;

public class Bishop extends AbstractPiece { 
    
    public Bishop(Board board, int x, int y) {
        super(board, x, y);
    }

    @Override
    public boolean pieceCanMove(int x, int y){
        return checkDiagonal(x, y);
    }

    @Override
    public String pieceName() {
        return "bishop";
    }
}
