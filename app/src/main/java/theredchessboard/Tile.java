package theredchessboard;

import theredchessboard.pieces.AbstractPiece;
import theredchessboard.utils.ClickableBackgroundImage;

import java.awt.Color;
import java.awt.event.MouseEvent;

public class Tile extends ClickableBackgroundImage {
    private AbstractPiece piece = null;
    private static final String EMPTY_IMAGE = "misc/empty_image.png";

    private Game game;

    private int x;
    private int y;

    public Tile(int x, int y, int tileX, int tileY, int w, int h, Game game) {
        super(x, y, w, h, determineColor(tileX, tileY));
        this.game = game;

        this.x = tileX;
        this.y = tileY;
    }

    /**
     * Sets the piece that this
     * tile contains.
     * @param piece The piece to
     * set
     */
    public void setPiece(AbstractPiece piece) {
        this.piece = piece;
        String name = (piece == null ? EMPTY_IMAGE : this.piece.getImageName());
        setImage(name);
    }

    /**
     * @return The piece on this
     * tile if there is one, null
     * if there is no piece.
     */
    public AbstractPiece getPiece() {
        return piece;
    }

    /**
     * Determines the color of
     * this tile (makes the
     * checkerboard pattern)
     * @param x The x position of the tile
     * @param y The y position of the tile
     * @return The color it should be
     */
    private static Color determineColor(int x, int y) {
        return ((x+y) % 2) == 0 ? Color.lightGray : Color.decode("#eeeeee");
    }

    /**
     * @return true if there
     * is a piece on this
     * tile, false if there
     * is not.
     */
    public boolean isEmpty() {
        return piece==null;
    }

    /**
     * Changes the background
     * of the tile to the
     * color of selected.
     */
    public void select() {
        setBackground(Color.PINK);
    }

    /**
     * Changes the color of the background
     * back to normal.
     */
    public void deselect() {
        setBackground(determineColor(x, y));
    }

    @Override
    public void mousePressed(MouseEvent e) {
        game.tileClicked(x, y);
    }

    /**
     * @return The location of the tile on the X
     */
    public int getLocX() {
        return x;
    }

    /**
     * @return The location of the tile on the Y
     */
    public int getLocY() {
        return y;
    }
}
