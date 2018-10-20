package snakeConnect;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Defining {

    static int poisonedBox;
    public static int turn = 1;
    static public GameButtons boxes[][] = new GameButtons[20][20];

    static GameCheckBox poison1;
    static GameCheckBox poison2;
    static GameLabel player1;
    static GameLabel player2;

    Defining() {

        final GameFrames startPage = new GameFrames();
        startPage.setLayout(new GridLayout(1, 2));

        GamePanel homeLayoutPanelLeft = new GamePanel();
        startPage.add(homeLayoutPanelLeft);
        homeLayoutPanelLeft.setLayout(new GridLayout(3, 1));

        GamePanel homeLayoutPanelRight = new GamePanel();
        startPage.add(homeLayoutPanelRight);
        homeLayoutPanelRight.setLayout(new GridLayout(3, 1));

        GameLabel space = new GameLabel();
        space.setText("      VS       ");

        player1 = new GameLabel();
        player1.setText("Player 1");
        player1.setOpaque(true);
        player1.setBackground(Color.yellow);

        player2 = new GameLabel();
        player2.setText("Player 2");

        final GameFrames gamePlayFrame = new GameFrames();
        gamePlayFrame.setLayout(new BorderLayout());

        GamePanel gameFace = new GamePanel();
        GamePanel gameUserFace = new GamePanel();
        GamePanel gamePoisonFace = new GamePanel();

        gameFace.setPreferredSize(new Dimension((int) (gamePlayFrame.getWidth() * 9) / 10, (int) (gamePlayFrame.getHeight() * 9) / 10));
        gamePlayFrame.add(gameUserFace, BorderLayout.WEST);
        gamePlayFrame.add(gamePoisonFace, BorderLayout.EAST);
        gamePlayFrame.add(gameFace, BorderLayout.AFTER_LAST_LINE);

        gameUserFace.add(player1, BorderLayout.LINE_START);
        gameUserFace.add(space, BorderLayout.LINE_END);
        gameUserFace.add(player2, BorderLayout.LINE_START);

        gameFace.setLayout(new GridLayout(20, 20));
        int i = 0;
        for (int x = 0; x < 20; x++) {
            for (int y = 0; y < 20; y++) {
                boxes[x][y] = new GameButtons();
                boxes[x][y].setButtonId(i);
                boxes[x][y].setButtonCheck(false);

//				System.out.println(boxes[x][y].getButtonId());
                boxes[x][y].addActionListener((ActionListener) new GameLogicFunctions());
                gameFace.add(boxes[x][y]);
                i++;
            }
        }

        poison1 = new GameCheckBox();
        poison1.setText("Poison 1");
        gamePoisonFace.add(poison1);
        poison1.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if (poison1.isSelected() == true) {
                    for (int x = 0; x < 20; x++) {
                        for (int y = 0; y < 20; y++) {
                            if (Defining.boxes[x][y].getBackground() == Color.black || Defining.boxes[x][y].getBackground() == Color.green) {
                                Defining.boxes[x][y].setEnabled(false);
                            } else if (Defining.poison1.getPoisonUsed() == true && (Defining.boxes[x][y].getBackground() == Color.red
                                    || Defining.boxes[x][y].getBackground() == Color.yellow)) {
                                Defining.boxes[x][y].setEnabled(false);
                                Defining.poison1.setEnabled(false);
                                Defining.poison2.setEnabled(true);
                            } else {
                                Defining.boxes[x][y].setEnabled(true);
                            }
                        }
                    }
                }
            }
        });

        poison2 = new GameCheckBox();
        poison2.setText("Poison 2");
        gamePoisonFace.add(poison2);
        poison2.setEnabled(false);
        poison2.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if (poison2.isSelected() == true) {
                    for (int x = 0; x < 20; x++) {
                        for (int y = 0; y < 20; y++) {
                            if (Defining.boxes[x][y].getBackground() == Color.black || Defining.boxes[x][y].getBackground() == Color.green) {
                                Defining.boxes[x][y].setEnabled(false);
                            } else {
                                Defining.boxes[x][y].setEnabled(true);
                            }
                        }
                    }
                }
            }
        });

        GameButtons playGame = new GameButtons();
        playGame.setText("Play");
        homeLayoutPanelLeft.add(playGame);
        playGame.play(startPage, gamePlayFrame);

        GameButtons closeGame = new GameButtons();
        closeGame.setText("Close");
        startPage.add(closeGame);
        closeGame.close(startPage, gamePlayFrame);

        final GameButtons restartGame = new GameButtons();
        restartGame.setText("Restart");
        gamePoisonFace.add(restartGame);
        restartGame.play(gamePlayFrame, startPage);
        restartGame.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                restartGame.restart(gamePlayFrame, startPage);
            }
        });

        startPage.setVisible(true);
    }
}
