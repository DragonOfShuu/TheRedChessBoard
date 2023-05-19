package theredchessboard;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JLabel;

import theredchessboard.dialoguebox.DialogueBox;

public class App {
    public String testExample() {
        return "Hello World!";
    }

    public static void startChessBoard(String playerOneSkin, String playerTwoSkin) {
        try {
            new Game(playerOneSkin, playerTwoSkin).start();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("---------- End of Main Program Stack Trace ----------");

            JFrame errorWin = new JFrame("An Error has Occurred.");
            errorWin.setLayout(null);
            errorWin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            JLabel error = new JLabel(e.getStackTrace().toString());
            errorWin.add(error);

            errorWin.setPreferredSize(new Dimension(300, 300));
            errorWin.pack();

            errorWin.setVisible(true);
        }
    }

    public static void main(String[] args) {
        DialogueBox dialog = new DialogueBox((playerOneSkin, playerTwoSkin) -> startChessBoard(playerOneSkin, playerTwoSkin));
    }
}
