package theredchessboard;

public class Game {
    public int fpLeft;
    public int spLeft;

    protected Board board;
    private Tile selectedTile;
    private boolean isFpTurn = true;

    public Game(int count, int size, int padding, int numOfPieces, char[][] default_board, String theme) {
        board = new Board(count, size, padding, numOfPieces, default_board, theme, this);
        spLeft = fpLeft = numOfPieces/2;
    }

    public Game(String theme) {
        board = new Board(theme, this);
        spLeft = fpLeft = board.getNumOfPieces() / 2;
    }

    public void start() {
        board.start();
    }

    public boolean deselectCurrentTile() {
        if (selectedTile == null) return false;

        selectedTile.deselect();
        selectedTile = null;
        return true;
    }

    public void movePiece(Tile moveTo) {
        moveTo.setPiece(selectedTile.getPiece());
        selectedTile.setPiece(null);
        isFpTurn = !isFpTurn;
    }

    public void takePiece(Tile moveTo) {
        System.out.println("Take piece attempted");
    }

    public void tileClicked(int x, int y) {
        Tile tile = board.getTile(x, y);
        
        // SELECTED TILE IS NULL
        if (selectedTile == null && tile.isEmpty()) return;
        if (selectedTile == null && tile.getPiece().isFp() != isFpTurn) return;

        if (selectedTile == null) {
            selectedTile = tile;
            selectedTile.select();
            return;
        }

        // SELECTED TILE IS NOT NULL
        if (selectedTile == tile) {deselectCurrentTile(); return;}
        if (!tile.isEmpty() && isFpTurn == tile.getPiece().isFp()) return;

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
