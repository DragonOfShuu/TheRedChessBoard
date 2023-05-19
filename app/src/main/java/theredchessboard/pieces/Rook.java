package theredchessboard.pieces;
import theredchessboard.Board;
import theredchessboard.Tile;

public class Rook extends AbstractPiece {
    public Rook(Board board, int x, int y) {
        super(board, x, y);
    }

    @Override
    public boolean pieceCanMove(int x, int y) {
        Tile[][] map = this.board.getBoard();
        if (this.x == x && this.y == y) return false;
        if (Math.abs(this.x) == x){
            for (int i = this.x; i < x; i++){
                 if (!map[i][x].isEmpty()) return false;
            }
            return true;
        }
        if(Math.abs(this.y) == y){
            for (int i = this.y; i < y; i++) {
                if (!map[y][i].isEmpty()) return false;
            }
            return true;
        }
        return false;
    }

    @Override
    public String pieceName() {
        return "rook";
    }
}
