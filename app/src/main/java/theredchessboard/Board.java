package theredchessboard;

import java.awt.Dimension;

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

    private int numOfPieces;

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

    public Board(int count, int size, int padding, int numOfPieces, char[][] default_board, String theme) {
        super("The Red Chessboard");
        if (count != default_board.length) {
            throw new UnsupportedOperationException("The size of the default board must match the size");
        }
        this.default_board = default_board;
        board = new Tile[count][count];
        if (!(numOfPieces % 2 == 0)) {
            throw new UnsupportedOperationException("The number of pieces must be divisible by two");
        }
        this.numOfPieces = numOfPieces;
        this.theme = theme;

        this.setLayout(null);
        this.setBounds(0, 0, size, size);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        createWindows(size, size, count, count, padding);
        setBoard();

        this.setPreferredSize(new Dimension(size+20, size+40));
        this.pack();

        this.setVisible(true);
    }

    public Board(int x, int y, String theme) {
        this(8, 500, 5, 32, default_default_board, theme);
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

                Tile manipulatable = new Tile(locationX, locationY, x, y, width, height);
                this.add(manipulatable);
                board[y][x] = manipulatable;
            }
        }
    }

    private AbstractPiece solvePiece(int x, int y) {
        char pieceAbbr = default_board[y][x];

        return switch (pieceAbbr) {
            case 'r' -> new Rook(this, x, y);
            case 'n' -> new Knight(this, x, y);
            case 'b' -> new Bishop(this, x, y);
            case 'k' -> new King(this, x, y);
            case 'q' -> new Queen(this, x, y);
            case 'p' -> new Pawn(this, x, y);
            default -> null;
        };
    }

    public void setBoard() {
        System.out.println(board.length);
        int count = 0;
        for (int row = 0; row < board.length; row++) {
            for (int column = 0; column < board.length; column++) {
                AbstractPiece newPiece = solvePiece(column, row);

                if (newPiece == null) {
                    board[row][column].setPiece( null );
                    break;
                }

                count++;
                if (count > (this.numOfPieces / 2)) {
                    newPiece.setFp(true);
                }
                board[row][column].setPiece(newPiece);
            }
        }
    }

    public String getTheme() {
        return theme;
    }
}
