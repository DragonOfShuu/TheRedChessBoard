package theredchessboard.pieces;

import theredchessboard.Board;
import theredchessboard.Tile;

public class Queen extends AbstractPiece {
    public Queen(Board board, int x, int y) {
        super(board, x, y);
    }

    @Override
    public boolean pieceCanMove(int x, int y) {
        Tile[][] map = this.board.getBoard();
        if (!map[x][y].isEmpty()) return false;
        if (this.x == x && this.y == y) return false;
        //horizontal or vertical
        if (Math.abs(this.x) == x){
            for (int i = this.x; i < x; i++){
                 if (!map[x][i].isEmpty()) return false;
            }
            return true;
        }
        if(Math.abs(this.y) == y){
            for (int i = this.y; i < y; i++) {
                if (!map[i][y].isEmpty()) return false;
            }
            return true;
        }
        //diagonal
        if (Math.abs(x-this.x) == (Math.abs(y-this.y))){
            if (y < this.y){
                if (x > this.x){
                    //northeast
                    for (int i = this.x; i < x; i--){
                        for (int j = this.y; j < y; j--){
                            if (!map[x][y].isEmpty()) return false;
                        }
                    }
                    return true;
                }
                if (x < this.x){
                    //northwest
                    for (int i = this.x; i < x; i++){
                        for (int j = this.y; j < y; j--){
                            if (!map[x][y].isEmpty()) return false;
                        }
                    }
                    return true;
                }
            }
            if (y > this.y){
                if (x > this.x){
                    //southeast
                    for (int i = this.x; i < x; i++){
                        for (int j = this.y; j < y; j++){
                            if (!map[x][y].isEmpty()) return false;
                        }
                    }
                    return true;
                }
                if (x < this.x){
                    //southwest
                    for (int i = this.x; i < x; i--){
                        for (int j = this.y; j < y; j--){
                            if (!map[x][y].isEmpty()) return false;
                        }
                    }
                    return true;
                }
            }  
            } 
        return false;
    }

    @Override
    public String pieceName() {
        return "queen";
    }
}
