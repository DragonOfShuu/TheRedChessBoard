package theredchessboard.pieces;
import theredchessboard.Board;

public class Rook extends AbstractPiece {
    public Rook(Board board, int x, int y) {
        super(board, x, y);
    }

    @Override
    public boolean pieceCanMove(int x, int y) {
        if (this.x == x){
            return checkVertical(y);
        }
        if(this.y == y){
            return checkHorizontal(x);
        }
        return false;
    }

    @Override
    public String pieceName() {
        return "rook";
    }
}
