package snakeConnect;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;


public class GameButtons extends JButton {

    protected int buttonId;
    protected boolean buttonCheck;

    public void setButtonId(int buttonId) {
        this.buttonId = buttonId;
    }

    public int getButtonId() {
        return buttonId;
    }

    public void setButtonCheck(boolean buttonCheck) {
        this.buttonCheck = buttonCheck;
    }

    public boolean getButtonCheck() {
        return buttonCheck;
    }

    GameButtons() {
        this.setSize(100, 40);
        this.setVisible(true);
    }

    void play(final GameFrames currentFrames, final GameFrames nextFrame) {
        this.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                currentFrames.dispose();
                nextFrame.setVisible(true);
                for (int x = 0; x < 20; x++) {
                    for (int y = 0; y < 20; y++) {
                        Defining.boxes[x][y].setEnabled(true);
                    }
                }
            }
        });
    }

    void restart(final GameFrames currentFrames, final GameFrames nextFrame) {
        if (Defining.turn == 2) {
            Defining.turn = 1;
        }
        Defining.poison1.setEnabled(true);
        Defining.poison2.setEnabled(false);
        Defining.poison1.setPoisonUsed(false);
        Defining.poison2.setPoisonUsed(false);
        Defining.player1.setOpaque(true);
        Defining.player1.setBackground(Color.yellow);
        Defining.player2.setBackground(null);
        currentFrames.dispose();
        nextFrame.setVisible(true);
        for (int x = 0; x < 20; x++) {
            for (int y = 0; y < 20; y++) {
                Defining.boxes[x][y].setEnabled(true);
                Defining.boxes[x][y].setBackground(null);
            }
        }
    }

    void close(final GameFrames currentFrames, final GameFrames nextFrame) {
        this.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                currentFrames.dispose();
                nextFrame.dispose();
            }
        });
    }
}
