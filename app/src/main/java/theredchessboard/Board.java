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

    /**
     * The very default of all of chess
     */
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
        // Initiate this board
        super("The Red Chessboard");
        // Verify the default board and the count are the same
        if (count != default_board.length) {
            throw new UnsupportedOperationException("The size of the default board must match the size");
        }
        
        this.default_board = default_board;
        board = new Tile[count][count];
        // Verify that the number of pieces
        // can be cleanly divided by two
        if (!(numOfPieces % 2 == 0)) {
            throw new UnsupportedOperationException("The number of pieces must be divisible by two");
        }
        this.numOfPieces = numOfPieces;
        this.theme1 = theme1;
        this.theme2 = theme2;
        this.game = game;

        // Set up the board window
        this.setLayout(null);
        this.setBounds(0, 0, size, size);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create the tiles and prepare the board
        createWindows(size, size, count, count, padding);
        setBoard();

        // Finish window sizing
        this.setPreferredSize(new Dimension(size+10, size+65));
        this.pack();
    }
    
    public Board(String theme1, String theme2, Game game) {
        this(8, 500, 5, 32, default_default_board, theme1, theme2, game);
    }

    // Allow the board to be used
    public void start() {
        this.setVisible(true);
    }

    // Get the two dimensional array
    // of tiles
    public Tile[][] getBoard() {
        return board;
    }

    /**
     * @return The total *ORIGINAL*
     * number of pieces
     */
    public int getNumOfPieces() {
        return numOfPieces;
    }

    /**
     * Format the layout of the tiles
     * @param w The width of each tile
     * @param h The height of each tile
     * @param countX The number of tiles along the x
     * @param countY The number of tiles along the y
     * @param padding The padding between each tile
     * 
     * @author Dragon of Shuu
     */
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

    /**
     * Convert character into an
     * actual piece
     * @param x The x of the tile the piece will be placed
     * @param y The y of the tile the piece will be placed
     * @return The new actual tile
     */
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

    /**
     * Set up the board with all
     * of the pieces.
     */
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

    /**
     * @return The name of the theme
     * for the first player
     */
    public String getTheme1() {
        return theme1;
    }

    /**
     * @return The name of the theme
     * for the second player
     */
    public String getTheme2() {
        return theme2;
    }

    /**
     * Get the tile at the
     * given location
     * @param x The x of the tile
     * @param y The y of the tile
     * @return The tile at the location given
     */
    public Tile getTile(int x, int y) {
        return board[y][x];
    }
}
