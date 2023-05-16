package theredchessboard;

import javax.swing.JFrame;

public class Board extends JFrame {
    public int fpLeft;
    public int spLeft;

    private Tile[][] board = new Tile[8][8];

    public Tile[][] getBoard() {
        return board;
    }
}
