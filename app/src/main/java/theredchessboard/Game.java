package theredchessboard;

import javax.swing.JFrame;

import theredchessboard.dialoguebox.WinBox;

public class Game {
    public int fpLeft;
    public int spLeft;

    protected Board board;
    private Tile selectedTile;
    private boolean isFpTurn = true;

    public Game(int count, int size, int padding, int numOfPieces, char[][] default_board, String theme1, String theme2) {
        board = new Board(count, size, padding, numOfPieces, default_board, theme1, theme2, this);
        spLeft = fpLeft = numOfPieces/2;
    }

    public Game(String theme1, String theme2) {
        board = new Board(theme1, theme2, this);
        spLeft = fpLeft = board.getNumOfPieces() / 2;
    }

    /**
     * This reveals the dialog
     * for who won, and 
     * requests the user to
     * play again or exit.
     */
    public void beginWinSequence() {
        JFrame winBox = new JFrame();
        int x = new WinBox((isFpTurn ? 1 : 2), winBox).getResult();
        winBox.dispose();

        if (x == 1) { board.dispose(); return; }

        reset();
    }

    /**
     * Show the board 
     * and allow the
     * user to start
     */
    public void start() {
        board.start();
    }

    /**
     * Completely reset 
     * the board and 
     * the player turn
     */
    public void reset() {
        board.setBoard();
        board.repaint();
        spLeft = fpLeft = board.getNumOfPieces() / 2;
        isFpTurn = true;
    }

    /**
     * Consider the game
     * forfeited and 
     * trigger the win
     * sequence.
     */
    public void forfeit() {
        isFpTurn = !isFpTurn;
        beginWinSequence();
    }
    
    /**
     * Deselects the current 
     * tile selected. 
     * @return false if there
     * was no selected tile,
     * true otherwise
     */
    private boolean deselectCurrentTile() {
        if (selectedTile == null) return false;

        selectedTile.deselect();
        selectedTile = null;
        return true;
    }

    /**
     * Selects the given tile
     * @param tile Tile to 
     * select
     */
    private void selectTile(Tile tile) {
        deselectCurrentTile();
        selectedTile = tile;
        selectedTile.select();
    }

    /**
     * Moves the piece
     * to the given tile
     * @param moveTo The
     * tile to move to
     */
    public void movePiece(Tile moveTo) {
        moveTo.setPiece(selectedTile.getPiece());
        moveTo.getPiece().setLocation(moveTo.getLocX(), moveTo.getLocY());
        selectedTile.setPiece(null);
        isFpTurn = !isFpTurn;
    }

    /**
     * Takes the piece on
     * the tile given.
     * @param moveTo The 
     * tile the piece is 
     * being taken on.
     */
    public void takePiece(Tile moveTo) {
        if (isFpTurn) spLeft--; 
        else          fpLeft--;

        moveTo.setPiece(selectedTile.getPiece());
        moveTo.getPiece().setLocation(moveTo.getLocX(), moveTo.getLocY());
        selectedTile.setPiece(null);
        
        if (spLeft==0 || fpLeft==0) {
            beginWinSequence();
            return;
        }

        isFpTurn = !isFpTurn;
    }

    /**
     * Determines what to
     * do when a tile is 
     * clicked.
     * @param x The x location
     * of the tile clicked.
     * @param y The y location
     * of the tile clicked
     */
    public void tileClicked(int x, int y) {
        Tile tile = board.getTile(x, y);
        
        // SELECTED TILE IS NULL
        // ===

        // Null and empty
        if (selectedTile == null && tile.isEmpty()) return;
        // Null and selected tile is not our turn
        if (selectedTile == null && tile.getPiece().isFp() != isFpTurn) return;
        // Selected tile is generally null
        if (selectedTile == null) { this.selectTile(tile); return; }

        // SELECTED TILE IS NOT NULL
        // ===

        // Selected tile and tile are the same
        if (selectedTile == tile) {deselectCurrentTile(); return;}
        // Clicked tile is of our type (switch selection)
        if (!tile.isEmpty() && isFpTurn == tile.getPiece().isFp()) { selectTile(tile); return; }

        // OTHERWISE...
        // Check if the piece can move into position
        if (!selectedTile.getPiece().pieceCanMove(x, y)) return;
        
        // If it can...
        if (tile.isEmpty()) {
            // Move piece if tile is empty
            movePiece(tile);
        } else {
            // Take piece if the tile is not empty
            takePiece(tile);
        }

        // Then deselct the tile
        deselectCurrentTile();
    }
}
