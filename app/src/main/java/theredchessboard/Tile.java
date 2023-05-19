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

    public void setPiece(AbstractPiece piece) {
        this.piece = piece;
        String name = (piece == null ? EMPTY_IMAGE : this.piece.getImageName());
        setImage(name);
    }

    public AbstractPiece getPiece() {
        return piece;
    }

    private static Color determineColor(int x, int y) {
        return ((x+y) % 2) == 0 ? Color.lightGray : Color.white;
    }

    public boolean isEmpty() {
        return piece==null;
    }

    public void select() {
        setBackground(Color.PINK);
    }

    public void deselect() {
        setBackground(determineColor(x, y));
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        game.tileClicked(x, y);
    }
}
