package theredchessboard.pieces;

import theredchessboard.Board;
import theredchessboard.Tile;

public class Pawn extends AbstractPiece {
    private boolean hasPawnMoved = false;
    

    public Pawn(Board board, int x, int y) {
        super(board, x, y);
    }

    private boolean pieceMovement(int x, int y) {
        int playerFactor = (isFp ? -1 : 1);
        Tile toTile = board.getTile(x, y);

        // If player attempts to take a piece
        // or more precisely, if there is a 
        // piece on the tile
        if (!toTile.isEmpty()) {
            // Verify the location is a feesible take
            if ((x == this.x-1 
                || x == this.x+1)
                && y == this.y+playerFactor) return true;
            return false;
        }
        
        // Verify the player isn't attempting to go diagonal
        if (x != this.x) return false;
        int differenceY = toTile.getLocY()-this.y;

        // If player attempts to move two spaces
        if (Math.abs(differenceY)==2 
            && !this.hasPawnMoved
            // Verify the next space is empty
            && board.getTile(this.x, this.y+playerFactor).isEmpty()) {

            if ((differenceY > 0 && !isFp) 
                || (differenceY < 0 && isFp)) return true;
            else                              return false;
        }
        
        if (toTile.getLocY() == this.y+playerFactor) return true;

        return false;
    }

    @Override
    public boolean pieceCanMove(int x, int y) {
        boolean result = pieceMovement(x, y);
        
        // Adjust pawn has moved
        if (result && !hasPawnMoved) hasPawnMoved = true;
        return result;

        // if (x == this.x){
        //     if (isFp){
        //         if (!hasPawnMoved){
        //             if (y == (this.y - 2)){
        //                 if (map[this.y-2][x].getPiece() == null){
        //                     hasPawnMoved = true;
        //                     return true;
        //                 }
        //                 if (this.isFp != map[this.y-2][x].getPiece().isFp()){
        //                     hasPawnMoved = true;
        //                     return true;
        //                 }
        //                 else return false;
        //             }
        //             if (y == (this.y - 1)){
        //                 if (map[this.y-1][x].getPiece() == null){
        //                     hasPawnMoved = true;
        //                     return true;
        //                 }
        //                 if (this.isFp != map[this.y-1][x].getPiece().isFp()){
        //                     hasPawnMoved = true;
        //                     return true;
        //                 }
        //                 else return false;
        //             }
        //             else return false;
        //         }
        //         else {
        //             if (y == (this.y - 1)){
        //                 if (map[this.y-1][x].getPiece() == null){
        //                     hasPawnMoved = true;
        //                     return true;
        //                 }
        //                 if (this.isFp != map[this.y-1][x].getPiece().isFp()){
        //                     hasPawnMoved = true;
        //                     return true;
        //                 }
        //                 else return false;
        //             }
        //             else return false;
        //         }
        //     }
        //     if (!isFp){
        //         if(!hasPawnMoved){
        //             if (y == (this.y + 2)){
        //                 if (map[this.y+2][x].getPiece() == null){
        //                     hasPawnMoved = true;
        //                     return true;
        //                 }
        //                 if (this.isFp != map[this.y+2][x].getPiece().isFp()){
        //                     hasPawnMoved = true;
        //                     return true;
        //                 }
        //                 else return false;
        //             }
        //             if (y == (this.y + 1)){
        //                 if (map[this.y+1][x].getPiece() == null){
        //                     hasPawnMoved = true;
        //                     return true;
        //                 }
        //                 if (this.isFp != map[this.y+1][x].getPiece().isFp()){
        //                     hasPawnMoved = true;
        //                     return true;
        //                 }
        //                 else return false;
        //             }
        //             else return false;
                    
        //         }
        //         if(hasPawnMoved){
        //             if (y == (this.y + 1)){
        //                 if (map[this.y+1][x].getPiece() == null){
        //                     hasPawnMoved = true;
        //                     return true;
        //                 }
        //                 if (this.isFp != map[this.y+1][x].getPiece().isFp()){
        //                     hasPawnMoved = true;
        //                     return true;
        //                 }
        //                 else return false;
        //             }
        //             else return false;
        //         }
        //         else return false;
        //     }
        //     else return false;
        // }
        // else return false;
    }

    @Override
    public String pieceName() {
        return "pawn"; 
    }
}
