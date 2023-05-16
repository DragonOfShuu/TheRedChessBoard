package theredchessboard;

import theredchessboard.pieces.AbstractPiece;
import theredchessboard.utils.JLComp;

public class Tile extends JLComp.ClickableImage {
    private AbstractPiece piece;

    public Tile(AbstractPiece piece) {
        super();
        this.piece = piece;
    }

    public boolean isEmpty() {
        return piece==null;
    }
}
