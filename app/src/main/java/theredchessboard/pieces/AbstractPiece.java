package theredchessboard.pieces;
import theredchessboard.Board;

public abstract class AbstractPiece {
    protected boolean isFp;
    protected Board board;
    protected String theme;

    protected int x;
    protected int y;

    public AbstractPiece(Board board, int x, int y) {
        this.board = board;
        this.theme = board.getTheme();
        this.x = x;
        this.y = y;
    }

    public String getImageName() {
        String playerName = (isFp ? "first_player" : "second_player");
        return "themes/" + theme + "/" + playerName + "/" + this.pieceName() + ".png";
    }

    public void setFp(boolean isFp) {
        this.isFp = isFp;
    }

    public boolean isFp() {
        return isFp;
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
