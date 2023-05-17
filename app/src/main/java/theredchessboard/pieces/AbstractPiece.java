package theredchessboard.pieces;
import theredchessboard.Board;

public abstract class AbstractPiece {
    public boolean isFp;
    protected Board board;
    protected String theme;

    protected int x;
    protected int y;

    public AbstractPiece(Board board, int x, int y, String theme) {
        this.board = board;
        this.theme = theme;
        this.x = x;
        this.y = y;
    }

    public String getImageName() {
        String playerName = (isFp ? "first_player" : "second_player");
        return "resources/themes/" + theme + playerName + this.pieceName();
    }

    /**
     * Kills the current piece and
     * relays to Board. Assume that
     * the tile will now be empty
     */
    public void kill() {

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

    /**
     * @return The name of the piece. ANSWER SHOULD BE SNAKE CASE
     */
    public abstract String pieceName();
}
