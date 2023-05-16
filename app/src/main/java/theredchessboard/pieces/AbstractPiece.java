package theredchessboard.pieces;
import theredchessboard.Board;

public abstract class AbstractPiece {
    public boolean isFp;
    protected Board board;

    protected int x;
    protected int y;

    public AbstractPiece(Board board, int x, int y) {
        this.board = board;
        this.x = x;
        this.y = y;
    }

    /**
     * This determines if the piece 
     * can move to the designated 
     * location
     * @param x the x location
     * @param y the y location
     * @return true if the piece can move there
     */
    public abstract boolean pieceCanMove(int x, int y);
}
