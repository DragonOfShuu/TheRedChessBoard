package theredchessboard;

import theredchessboard.pieces.AbstractPiece;
import theredchessboard.utils.ClickableBackgroundImage;

import java.awt.Color;

public class Tile extends ClickableBackgroundImage {
    private AbstractPiece piece = null;
    private static final String EMPTY_IMAGE = "misc/empty_image.png";

    public Tile(int x, int y, int tileX, int tileY, int w, int h) {
        super(x, y, w, h, determineColor(tileX, tileY));
    }

    public void setPiece(AbstractPiece piece) {
        this.piece = piece;
        String name = (piece == null ? EMPTY_IMAGE : this.piece.getImageName());
        setImage(name);
    }

    private static Color determineColor(int x, int y) {
        return ((x+y) % 2) == 0 ? Color.lightGray : Color.white;
    }

    public boolean isEmpty() {
        return piece==null;
    }
}
