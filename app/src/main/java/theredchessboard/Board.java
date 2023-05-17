package theredchessboard;

import javax.swing.JFrame;

import theredchessboard.pieces.AbstractPiece;
import theredchessboard.pieces.Bishop;
import theredchessboard.pieces.King;
import theredchessboard.pieces.Knight;
import theredchessboard.pieces.Pawn;
import theredchessboard.pieces.Queen;
import theredchessboard.pieces.Rook;

public class Board extends JFrame {
    public int fpLeft;
    public int spLeft;

    private String theme;

    private Tile[][] board;
    private char[][] default_board;

    private static final char[][] default_default_board = {
        {'r', 'n', 'b', 'k', 'q', 'b', 'n', 'r'},
        {'p', 'p', 'p', 'p', 'p', 'p', 'p', 'p'},
        {'o', 'o', 'o', 'o', 'o', 'o', 'o', 'o'},
        {'o', 'o', 'o', 'o', 'o', 'o', 'o', 'o'},
        {'o', 'o', 'o', 'o', 'o', 'o', 'o', 'o'},
        {'o', 'o', 'o', 'o', 'o', 'o', 'o', 'o'},
        {'p', 'p', 'p', 'p', 'p', 'p', 'p', 'p'},
        {'r', 'n', 'b', 'k', 'q', 'b', 'n', 'r'}
    };

    public Board(int x, int y, int size, int padding, char[][] default_board, String theme) {
        if (size != default_board.length) {
            throw new UnsupportedOperationException("The size of the default board must match the size");
        }
        this.default_board = default_board;
        board = new Tile[size][size];
        this.theme = theme;

        createWindows(size, size, x, y, padding);
        setBoard();
    }

    public Board(int x, int y, String theme) {
        this(x, y, 8, 5, default_default_board, theme);
    }

    public Tile[][] getBoard() {
        return board;
    }

    public void createWindows(int w, int h, int countX, int countY, int padding) {
        int paddingSpaceCountX = padding*(countX+1);
        int paddingSpaceCountY = padding*(countY+1);

        int width = (w-paddingSpaceCountX) / countX;
        int height = (h-paddingSpaceCountY) / countY;
        
        for (int y = 0; y < countY; y++) {
            int previousPaddingCountY = ((y+1)*padding);
            int previousWindowCountY = y*height;

            int locationY = previousPaddingCountY+previousWindowCountY;

            for (int x = 0; x < countX; x++) {
                int previousPaddingCountX = ((x+1)*padding);
                int previousWindowCountX = x*width;

                int locationX = previousPaddingCountX+previousWindowCountX;

                Tile manipulatable = new Tile(locationX, locationY, width, height, theme);
                this.add(manipulatable);
                board[y][x] = manipulatable;
            }
        }
    }

    private AbstractPiece solvePiece(int x, int y) {
        char pieceAbbr = default_board[y][x];

        return switch (pieceAbbr) {
            case 'r' -> new Rook(this, x, y, theme);
            case 'n' -> new Knight(this, x, y, theme);
            case 'b' -> new Bishop(this, x, y, theme);
            case 'k' -> new King(this, x, y, theme);
            case 'q' -> new Queen(this, x, y, theme);
            case 'p' -> new Pawn(this, x, y, theme);
            default -> null;
        };
    }

    public void setBoard() {
        for (int row = 0; row < board.length; row++) {
            for (int column = 0; column < board.length; column++) {
                board[row][column].setPiece( solvePiece(column, row) );
            }
        }
    }

    public String getTheme() {
        return theme;
    }
}
