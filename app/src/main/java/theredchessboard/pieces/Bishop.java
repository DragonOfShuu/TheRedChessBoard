package theredchessboard.pieces;
import theredchessboard.Board;
import theredchessboard.Tile;

public class Bishop extends AbstractPiece { 
    
    public Bishop(Board board, int x, int y) {
        super(board, x, y);
    }

    @Override
    public boolean pieceCanMove(int x, int y){
        Tile[][] map = this.board.getBoard();
        if (!map[y][x].isEmpty()) return false;
        if (this.x == x && this.y == y) return false;
        if (x == this.x || y == this.y) return false;

        if (Math.abs(x-this.x) == (Math.abs(y-this.y))){
            if (y < this.y){
                if (x > this.x){
                    //northeast
                    for (int i = this.x; i < x; i--){
                        for (int j = this.y; j < y; j--){
                            if (!map[y][x].isEmpty()) return false;
                        }
                    }
                    if (map[y][x] == null || this.isFp != map[y][x].getPiece().isFp()) return true;
                }
                if (x < this.x){
                    //northwest
                    for (int i = this.x; i < x; i++){
                        for (int j = this.y; j < y; j--){
                            if (!map[y][x].isEmpty()) return false;
                        }
                    }
                    if (map[y][x] == null || this.isFp != map[y][x].getPiece().isFp()) return true;
                }
            }
            if (y > this.y){
                if (x > this.x){
                    //southeast
                    for (int i = this.x; i < x; i++){
                        for (int j = this.y; j < y; j++){
                            if (!map[y][x].isEmpty()) return false;
                        }
                    }
                    if (map[y][x] == null || this.isFp != map[y][x].getPiece().isFp()) return true;
                }
                if (x < this.x){
                    //southwest
                    for (int i = this.x; i < x; i--){
                        for (int j = this.y; j < y; j--){
                            if (!map[y][x].isEmpty()) return false;
                        }
                    }
                    if (map[y][x] == null || this.isFp != map[y][x].getPiece().isFp()) return true;
                }
            }  
            } 
        return false;
    }

    @Override
    public String pieceName() {
        return "bishop";
    }
}
