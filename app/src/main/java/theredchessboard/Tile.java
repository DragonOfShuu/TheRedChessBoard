package theredchessboard;

import theredchessboard.pieces.AbstractPiece;
import theredchessboard.utils.ClickableBackgroundImage;

import java.awt.Color;

public class Tile extends ClickableBackgroundImage {
    private AbstractPiece piece = null;

    public Tile(int x, int y, int w, int h, String theme) {
        super(x, y, w, h, determineColor(x, y));
    }

    public void setPiece(AbstractPiece piece) {
        this.piece = piece;
        setImage(this.piece.getImageName());
    }

    private static Color determineColor(int x, int y) {
        return ((x+y) % 2) == 0 ? Color.lightGray : Color.WHITE;
    }

    public boolean isEmpty() {
        return piece==null;
    }
}
