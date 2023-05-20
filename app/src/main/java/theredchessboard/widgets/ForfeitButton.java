package theredchessboard.widgets;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;

import theredchessboard.Game;

public class ForfeitButton extends JButton implements MouseListener {
    private Game game;

    public ForfeitButton(Game game) {
        super("Forfeit");
        this.game = game;
        this.addMouseListener(this);
    }

    @Override
    public void mouseClicked(MouseEvent e) {}

    @Override
    public void mousePressed(MouseEvent e) {
        game.forfeit();
    }

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}
}
