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
import theredchessboard.widgets.ForfeitButton;

public class Board extends JFrame {
    private int numOfPieces;

    private String theme1;
    private String theme2;
    private Game game;

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

    public Board(int count, 
                 int size, 
                 int padding, 
                 int numOfPieces, 
                 char[][] default_board, 
                 String theme1,
                 String theme2,
                 Game game) {
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
        this.theme1 = theme1;
        this.theme2 = theme2;
        this.game = game;

        this.setLayout(null);
        this.setBounds(0, 0, size, size);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        createWindows(size, size, count, count, padding);
        setBoard();

        this.setPreferredSize(new Dimension(size+10, size+65));
        this.pack();
    }

    public void start() {
        this.setVisible(true);
    }

    public Board(String theme1, String theme2, Game game) {
        this(8, 500, 5, 32, default_default_board, theme1, theme2, game);
    }

    public Tile[][] getBoard() {
        return board;
    }

    public int getNumOfPieces() {
        return numOfPieces;
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

                Tile manipulatable = new Tile(locationX, locationY, x, y, width, height, game);
                this.add(manipulatable);
                board[y][x] = manipulatable;
            }
        }

        ForfeitButton fButton = new ForfeitButton(this.game);
        fButton.setBounds(10, (countY*height)+(countY*padding) + 10, 80, 20);
        this.add(fButton);
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
        int count = 0;
        for (int row = 0; row < board.length; row++) {
            for (int column = 0; column < board[row].length; column++) {
                AbstractPiece newPiece = solvePiece(column, row);

                if (newPiece == null) {
                    board[row][column].setPiece( null );
                    continue;
                }

                count++;
                if (count > (this.numOfPieces / 2)) {
                    newPiece.setFp(true);
                }
                board[row][column].setPiece(newPiece);
            }
        }
    }

    public String getTheme1() {
        return theme1;
    }

    public String getTheme2() {
        return theme2;
    }

    public Tile getTile(int x, int y) {
        return board[y][x];
    }
}
