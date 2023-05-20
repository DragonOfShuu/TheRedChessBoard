package theredchessboard.dialoguebox;
import javax.swing.JOptionPane;
import javax.swing.JFrame;


public class WinBox {
    private int result;

    public WinBox(int playerWinner, JFrame frame){
        // super(frame);
        // // super declaration


        Object[] customButtons = {"Play Again?", "Exit"};   // titles for buttons
        int n = JOptionPane.showOptionDialog(
            frame,                                          // JFrame parent input
            "Player " + playerWinner + " Won!",             // window title
            "Continue?",                                    // window text
            JOptionPane.YES_NO_OPTION,                      // determines how many buttons appear (I think) and how buttons return 
            JOptionPane.INFORMATION_MESSAGE,                // changes the icon
            null,                                           // custom icon
            customButtons,                                  // button text input  
            customButtons[0]);                              // honestly not sure what this does, probably declares what title goes first?

        result = n;
    }

    public int getResult() {
        return result;
    }
}