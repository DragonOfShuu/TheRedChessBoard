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
        if (x == this.x){return false;}
        if (y == this.y){return false;}
        if (Math.abs(x-this.x) == (Math.abs(y-this.y))){
            if (y < this.y){
                if (x > this.x){
                    //northeast
                    for (int i = this.x; i < x; i--){
                        for (int j = this.y; j < y; j--){
                            if (map[x][y].isEmpty()) return true;
                        }
                    }
                }
                if (x < this.x){
                    //northwest
                    for (int i = this.x; i < x; i++){
                        for (int j = this.y; j < y; j--){
                            if (map[x][y].isEmpty()) return true;
                        }
                    }
                }
            }
            if (y > this.y){
                if (x > this.x){
                    //southeast
                    for (int i = this.x; i < x; i++){
                        for (int j = this.y; j < y; j++){
                            if (map[x][y].isEmpty()) return true;
                        }
                    }
                }
                if (x < this.x){
                    //southwest
                    for (int i = this.x; i < x; i--){
                        for (int j = this.y; j < y; j--){
                            if (map[x][y].isEmpty()) return true;
                        }
                    }
                }
            }  
            } 
            else return false;
        return false;
    }

    @Override
    public String pieceName() {
        return "bishop";
    }
}
