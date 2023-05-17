package theredchessboard.pieces;

import theredchessboard.Board;

import theredchessboard.Tile;

public class Pawn extends AbstractPiece {
    private boolean hasPawnMoved = false;
    

    public Pawn(Board board, int x, int y, String theme) {
        super(board, x, y, theme);
    }

    @Override
    public boolean pieceCanMove(int x, int y) {
        Tile[][] map = this.board.getBoard();
        if (x == this.x){
            if (isFp){
                if (!hasPawnMoved){
                    if (y == (this.y - 2)){
                        if (map[x][this.y-2].isEmpty()){
                            hasPawnMoved = true;
                            return true;
                        }
                        else return false;
                    }
                    if (y == (this.y - 1)){
                        if (map[x][this.y-1].isEmpty()){
                            hasPawnMoved = true;
                            return true;
                        }
                        else return false;
                    }
                    else return false;
                }
                if (hasPawnMoved){
                    if (y == (this.y - 1)){
                        if (map[x][this.y-1].isEmpty()){
                            hasPawnMoved = true;
                            return true;
                        }
                        else return false;
                    }
                    else return false;
                }
                else return false;
            }
            if (!isFp){
                if(!hasPawnMoved){
                    if (y == (this.y + 2)){
                        if (map[x][this.y+2].isEmpty()){
                            hasPawnMoved = true;
                            return true;
                        }
                        else return false;
                    }
                    if (y == (this.y + 1)){
                        if (map[x][this.y+1].isEmpty()){
                            hasPawnMoved = true;
                            return true;
                        }
                        else return false;
                    }
                    else return false;
                    
                }
                if(hasPawnMoved){
                    if (y == (this.y + 1)){
                        if (map[x][this.y+1].isEmpty()){
                            hasPawnMoved = true;
                            return true;
                        }
                        else return false;
                    }
                    else return false;
                }
            }
        }
        else return false;
    }

    @Override
    public String pieceName() {
        return "pawn"; 
    }
}
