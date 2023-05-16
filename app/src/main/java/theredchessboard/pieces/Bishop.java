package theredchessboard.pieces;
import theredchessboard.Board;
import theredchessboard.Tile;

public class Bishop extends AbstractPiece { 
    
    public Bishop(Board board, int x, int y) {
        super(board, x, y);
    }

    @Override
    public boolean pieceCanMove(int x, int y){
        // TODO
        Tile[][] map = this.board.getBoard();
        if (!map[x][y].isEmpty()) return false;
        if (x == this.x){
            return false;
        }
        if (y == this.y){
            return false;
        }
        if (x < this.x){
            if (y < this.y){
                //northwest
            }
            if (y > this.y){
                //northeast
            }
        }
        if (x > this.x){
            if (y < this.y){
                //southwest
            }
            if (y > this.y){
                //southeast
            }
        }
        return false;
    }
}
