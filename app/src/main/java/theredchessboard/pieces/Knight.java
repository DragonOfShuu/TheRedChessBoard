package theredchessboard.pieces;

import theredchessboard.Board;
import theredchessboard.Tile;

public class Knight extends AbstractPiece {
    public Knight(Board board, int x, int y) {
        super(board, x, y);
    }

    @Override
    public boolean pieceCanMove(int x, int y) {
        Tile[][] map = this.board.getBoard();

        if (Math.abs(this.x-x)!=2 && Math.abs(this.y-y)!=1) return false;
        if (Math.abs(this.x-x)!=1 && Math.abs(this.y-y)!=2) return false;
        if (map[y][x].getPiece() == null) return true;
        if (this.isFp != map[y][x].getPiece().isFp()) return true;
        return false;
    }

    @Override
    public String pieceName() {
        return "knight";
    }
}
