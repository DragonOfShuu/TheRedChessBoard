package theredchessboard.pieces;
import theredchessboard.Board;
import theredchessboard.Tile;

public class King extends AbstractPiece {
    public King(Board board, int x, int y) {
        super(board, x, y);
    }

    @Override
    public boolean pieceCanMove(int x, int y) {
        Tile[][] map = this.board.getBoard();
        if (this.x == x && this.y == y) return false;
        if(Math.abs(this.x-x)==1 || Math.abs(this.y-y)==1){
            if (map[y][x].isEmpty()) return true;
            else return false;
        }
        if(Math.abs(this.x-x)==1 && Math.abs(this.y-y)==1){
            if (map[y][x].isEmpty()) return true;
            else return false;
        }
        else{
            return false;
        }
    }

    @Override
    public String pieceName() {
        return "king";
    }

}
