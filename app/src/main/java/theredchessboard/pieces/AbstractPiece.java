package theredchessboard.pieces;

import theredchessboard.Board;

public abstract class AbstractPiece {
    protected boolean isFp;
    protected Board board;
    protected String theme1;
    protected String theme2;

    protected int x;
    protected int y;

    public AbstractPiece(Board board, int x, int y) {
        this.board = board;
        this.theme1 = board.getTheme1();
        this.theme2 = board.getTheme2();
        this.x = x;
        this.y = y;
    }

    /**
     * @return The url to the 
     * image
     */
    public String getImageName() {
        String theme = (isFp ? theme1 : theme2);
        return "themes/" + theme + "/" + this.pieceName() + ".png";
    }

    /**
     * Set if this piece
     * is first player.
     * @param isFp True if this piece is 
     * first player
     */
    public void setFp(boolean isFp) {
        this.isFp = isFp;
    }

    /**
     * @return If this piece is first
     * player
     */
    public boolean isFp() {
        return isFp;
    }

    /**
     * The location of this piece
     * @param x
     * @param y
     */
    public void setLocation(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    /**
     * Check all pieces from us
     * to the x (excludes the 
     * piece at x)
     * @param x the x of the tile to check up to
     * @return true if possible
     */
    protected boolean checkHorizontal(int x) {
        int directionFactor = (this.x - x > 0 ? 1 : -1);
        int diff = Math.abs(this.x - x);

        for (int i = 1; i < diff; i++) {
            if (!board.getTile(x+(i*directionFactor), this.y).isEmpty()) return false;
        }

        return true;
    }

    /**
     * Check all pieces from us
     * to the y (excludes the
     * piece at y)
     * @param y the y of the tile to check up to
     * @return true if possible
     */
    protected boolean checkVertical(int y) {
        int directionFactor = (this.y - y > 0 ? 1 : -1);
        int diff = Math.abs(this.y - y);

        for (int i = 1; i < diff; i++) {
            if (!board.getTile(this.x, y+(i*directionFactor)).isEmpty()) return false;
        }

        return true;
    }

    protected boolean checkDiagonal(int x, int y) {
        if (Math.abs(this.x - x) != Math.abs(this.y - y)) return false;

        // // I chose these arbitary 
        // // operations to make it like 
        // // math and quadrants.
        int relativeX = x - this.x;
        int relativeY = this.y - y;

        int dirFactorX = (relativeX > 0 ? 1 : -1);
        int dirFactorY = (relativeY > 0 ? 1 : -1);

        int diff = Math.abs(this.y - y);

        for (int i = 1; i < diff; i++) {
            if (
                !board.getTile(
                    this.x+(i*dirFactorX), 
                    // Inverted here because down is positive for y
                    this.y-(i*dirFactorY)
                ).isEmpty()
            ) {
                return false;
            }
        }

        return true;
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
