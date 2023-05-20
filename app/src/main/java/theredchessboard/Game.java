package theredchessboard;

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

    public void start() {
        board.start();
    }

    private boolean deselectCurrentTile() {
        if (selectedTile == null) return false;

        selectedTile.deselect();
        selectedTile = null;
        return true;
    }

    private void selectTile(Tile tile) {
        deselectCurrentTile();
        selectedTile = tile;
        selectedTile.select();
    }

    public void movePiece(Tile moveTo) {
        moveTo.setPiece(selectedTile.getPiece());
        moveTo.getPiece().setLocation(moveTo.getLocX(), moveTo.getLocY());
        selectedTile.setPiece(null);
        isFpTurn = !isFpTurn;
    }

    public void takePiece(Tile moveTo) {
        if (isFpTurn) spLeft--; 
        else          fpLeft--;

        moveTo.setPiece(selectedTile.getPiece());
        moveTo.getPiece().setLocation(moveTo.getLocX(), moveTo.getLocY());
        selectedTile.setPiece(null);

        if (spLeft==0 || fpLeft==0) {
            System.out.println((spLeft==0 ? "First player" : "Second player") + " won the game!");
        }

        isFpTurn = !isFpTurn;
    }

    public void tileClicked(int x, int y) {
        Tile tile = board.getTile(x, y);
        
        // SELECTED TILE IS NULL
        if (selectedTile == null && tile.isEmpty()) return;
        if (selectedTile == null && tile.getPiece().isFp() != isFpTurn) return;

        if (selectedTile == null) { this.selectTile(tile); return; }

        // SELECTED TILE IS NOT NULL
        if (selectedTile == tile) {deselectCurrentTile(); return;}
        if (!tile.isEmpty() && isFpTurn == tile.getPiece().isFp()) { selectTile(tile); return; }

        // Check if the piece can move into position
        if (!selectedTile.getPiece().pieceCanMove(x, y)) return;

        if (tile.isEmpty()) {
            movePiece(tile);
        } else {
            takePiece(tile);
        }

        deselectCurrentTile();
    }
}
