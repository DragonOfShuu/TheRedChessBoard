package theredchessboard.pieces;

import theredchessboard.Board;
import theredchessboard.Tile;

public class Pawn extends AbstractPiece {
    private boolean hasPawnMoved = false;
    

    public Pawn(Board board, int x, int y) {
        super(board, x, y);
    }

    @Override
    public boolean pieceCanMove(int x, int y) {
        Tile[][] map = this.board.getBoard();
        if (x == this.x){
            if (isFp){
                if (!hasPawnMoved){
                    if (y == (this.y - 2)){
                        if (this.isFp != map[this.y-2][x].getPiece().isFp()){
                            hasPawnMoved = true;
                            return true;
                        }
                        else return false;
                    }
                    if (y == (this.y - 1)){
                        if (this.isFp != map[this.y-2][x].getPiece().isFp()){
                            hasPawnMoved = true;
                            return true;
                        }
                        else return false;
                    }
                    else return false;
                }
                else {
                    if (y == (this.y - 1)){
                        if (this.isFp != map[this.y-2][x].getPiece().isFp()){
                            hasPawnMoved = true;
                            return true;
                        }
                        else return false;
                    }
                    else return false;
                }
            }
            if (!isFp){
                if(!hasPawnMoved){
                    if (y == (this.y + 2)){
                        if (this.isFp != map[this.y-2][x].getPiece().isFp()){
                            hasPawnMoved = true;
                            return true;
                        }
                        else return false;
                    }
                    if (y == (this.y + 1)){
                        if (this.isFp != map[this.y-2][x].getPiece().isFp()){
                            hasPawnMoved = true;
                            return true;
                        }
                        else return false;
                    }
                    else return false;
                    
                }
                if(hasPawnMoved){
                    if (y == (this.y + 1)){
                        if (this.isFp != map[this.y-2][x].getPiece().isFp()){
                            hasPawnMoved = true;
                            return true;
                        }
                        else return false;
                    }
                    else return false;
                }
                else return false;
            }
            else return false;
        }
        else return false;
    }

    @Override
    public String pieceName() {
        return "pawn"; 
    }
}
