package snakeConnect;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import javax.swing.JOptionPane;

public class GameLogicFunctions implements ActionListener {

    static LinkedList<Integer> adjucentIdRedLastCount = new LinkedList<Integer>();
    static LinkedList<Integer> adjucentIdRedFirstCount = new LinkedList<Integer>();
    static LinkedList<Integer> adjucentIdYellowLastCount = new LinkedList<Integer>();
    static LinkedList<Integer> adjucentIdYellowFirstCount = new LinkedList<Integer>();
    static LinkedList<Integer> redList = new LinkedList<Integer>();
    static LinkedList<Integer> yellowList = new LinkedList<Integer>();
    static LinkedList<Integer> adjucentRedFirstNodes = new LinkedList<Integer>();
    static LinkedList<Integer> adjucentRedLastNodes = new LinkedList<Integer>();
    static LinkedList<Integer> adjucentYellowFirstNodes = new LinkedList<Integer>();
    static LinkedList<Integer> adjucentYellowLastNodes = new LinkedList<Integer>();

    int poisonBox;

    @Override
    public void actionPerformed(ActionEvent arg0) {
        Object source = arg0.getSource();

        for (int x = 0; x < 20; x++) {
            for (int y = 0; y < 20; y++) {
                if ((Defining.poison1.isSelected() == true && Defining.poison1.getPoisonUsed() == false)
                        || (Defining.poison2.isSelected() == true && Defining.poison2.getPoisonUsed() == false)) {
                    if (Defining.poison1.isSelected() == true) {
                        if (source == Defining.boxes[x][y]) {
                            Defining.boxes[x][y].setBackground(Color.green);
                            Defining.poisonedBox = Defining.boxes[x][y].getButtonId();
                            poisonBox = Defining.poisonedBox;
                            poisonThrow(poisonBox);
                            Defining.poison1.setSelected(false);
                            Defining.poison1.setEnabled(false);
                            Defining.poison1.setPoisonUsed(true);
                            poisonCheckingP1();

                            Defining.turn = 2;
                            Defining.player2.setOpaque(true);
                            Defining.player2.setBackground(Color.red);
                            Defining.player1.setBackground(null);
                        }

                    } else if (Defining.poison2.isSelected() == true) {
                        if (source == Defining.boxes[x][y]) {
                            Defining.boxes[x][y].setBackground(Color.green);
                            Defining.poisonedBox = Defining.boxes[x][y].getButtonId();
                            poisonBox = Defining.poisonedBox;
                            poisonThrow(poisonBox);
                            Defining.poison2.setSelected(false);
                            Defining.poison2.setEnabled(false);
                            Defining.poison2.setPoisonUsed(true);
                            poisonCheckingP2();
                            Defining.turn = 1;
                            Defining.player1.setOpaque(true);
                            Defining.player1.setBackground(Color.yellow);
                            Defining.player2.setBackground(null);
                        }
                    }
                } else if ((Defining.poison1.getPoisonUsed() == true
                        || Defining.poison2.getPoisonUsed() == true)
                        && (Defining.boxes[x][y].getBackground() == Color.red
                        || Defining.boxes[x][y].getBackground() == Color.yellow)) {
                    poisonCheckingP1();
                    poisonCheckingP2();
                    Defining.boxes[x][y].setEnabled(false);

                } else if (source == Defining.boxes[x][y] && Defining.turn == 2) {
                    Defining.boxes[x][y].setBackground(Color.red);
                    Defining.turn = 1;
                    Defining.player1.setOpaque(true);
                    Defining.player1.setBackground(Color.yellow);
                    Defining.player2.setBackground(null);
                    storeRed();
                    countRedWinner();
                    poisonCheckingP1();
                    Defining.boxes[x][y].setEnabled(false);
                    System.out.println(Defining.turn);
                } else if (source == Defining.boxes[x][y] && Defining.turn == 1) {
                    Defining.boxes[x][y].setBackground(Color.yellow);
                    Defining.turn = 2;
                    Defining.player2.setOpaque(true);
                    Defining.player2.setBackground(Color.red);
                    Defining.player1.setBackground(null);
                    storeYellow();
                    countYellowWinner();
                    poisonCheckingP2();
                    Defining.boxes[x][y].setEnabled(false);
                    System.out.println(Defining.turn);
                }
            }
        }
    }

    LinkedList<Integer> storeRed() {
        redList.clear();
        for (int x = 0; x < 20; x++) {
            for (int y = 0; y < 20; y++) {
                if (Defining.boxes[x][y].getBackground() == Color.red) {
                    redList.add(Defining.boxes[x][y].getButtonId());
                }
            }
        }
        return redList;
    }

    LinkedList<Integer> storeYellow() {
        yellowList.clear();
        for (int x = 0; x < 20; x++) {
            for (int y = 0; y < 20; y++) {
                Defining.boxes[x][y].getButtonId();
                if (Defining.boxes[x][y].getBackground() == Color.yellow) {
                    yellowList.add(Defining.boxes[x][y].getButtonId());
                }
            }
        }
        return yellowList;
    }

    LinkedList<Integer> findAdjacentRedLast(int curentLastRedId) throws NoSuchElementException {
        storeRed();
        if (redList.size() > 1) {

            for (int i = 0; i < redList.size(); i++) {
                adjucentRedLastNodes.clear();
                for (int j = 0; j < redList.size(); j++) {
                    if ((curentLastRedId - redList.get(j) == 21
                            || curentLastRedId - redList.get(j) == 20
                            || curentLastRedId - redList.get(j) == 19
                            || curentLastRedId - redList.get(j) == 1
                            || curentLastRedId - redList.get(j) == -21
                            || curentLastRedId - redList.get(j) == -20
                            || curentLastRedId - redList.get(j) == -19
                            || curentLastRedId - redList.get(j) == -1)
                            && !adjucentIdRedLastCount.contains(redList.get(j))) {
                        adjucentRedLastNodes.add(redList.get(j));
                        adjucentIdRedLastCount.add(redList.get(j));
                    }
                }
                if (adjucentRedLastNodes.size() == 1) {
                    curentLastRedId = adjucentRedLastNodes.getFirst();
                } else if (adjucentRedLastNodes.size() > 1) {
                    curentLastRedId = adjucentRedLastNodes.getLast();
                }

            }

        }
        return adjucentIdRedLastCount;
    }

    LinkedList<Integer> findAdjacentRedFirst(int curentFirstRedId) throws NoSuchElementException {
        storeRed();
        if (redList.size() > 1) {
            for (int i = 0; i < redList.size(); i++) {
                adjucentRedFirstNodes.clear();
                for (int j = 0; j < redList.size(); j++) {
                    if ((curentFirstRedId - redList.get(j) == 21
                            || curentFirstRedId - redList.get(j) == 20
                            || curentFirstRedId - redList.get(j) == 19
                            || curentFirstRedId - redList.get(j) == 1
                            || curentFirstRedId - redList.get(j) == -21
                            || curentFirstRedId - redList.get(j) == -20
                            || curentFirstRedId - redList.get(j) == -19
                            || curentFirstRedId - redList.get(j) == -1)
                            && !adjucentIdRedFirstCount.contains(redList.get(j))) {
                        adjucentRedFirstNodes.add(redList.get(j));
                        adjucentIdRedFirstCount.add(redList.get(j));
                    }
                }
                if (adjucentRedFirstNodes.size() > 0) {
                    curentFirstRedId = adjucentRedFirstNodes.getFirst();
                }

            }
        }
        return adjucentIdRedFirstCount;
    }

    LinkedList<Integer> findAdjacentYellowLast(int curentYellowLastId) throws NoSuchElementException {
        storeYellow();
        if (yellowList.size() > 1) {
            for (int i = 0; i < yellowList.size(); i++) {
                adjucentYellowLastNodes.clear();
                for (int j = 0; j < yellowList.size(); j++) {
                    if ((curentYellowLastId - yellowList.get(j) == 21
                            || curentYellowLastId - yellowList.get(j) == 20
                            || curentYellowLastId - yellowList.get(j) == 19
                            || curentYellowLastId - yellowList.get(j) == 1
                            || curentYellowLastId - yellowList.get(j) == -21
                            || curentYellowLastId - yellowList.get(j) == -20
                            || curentYellowLastId - yellowList.get(j) == -19
                            || curentYellowLastId - yellowList.get(j) == -1)
                            && !adjucentIdYellowLastCount.contains(yellowList.get(j))) {
                        adjucentYellowLastNodes.add(yellowList.get(j));
                        adjucentIdYellowLastCount.add(yellowList.get(j));
                    }
                }
                if (adjucentYellowLastNodes.size() == 1) {
                    curentYellowLastId = adjucentYellowLastNodes.getFirst();
                } else if (adjucentYellowLastNodes.size() > 1) {
                    curentYellowLastId = adjucentYellowLastNodes.getLast();
                }
            }
        }
        return adjucentIdYellowLastCount;
    }

    LinkedList<Integer> findAdjacentYellowFirst(int curentYellowFirstId) throws NoSuchElementException {
        storeYellow();
        if (yellowList.size() > 1) {
            for (int i = 0; i < yellowList.size(); i++) {
                adjucentYellowFirstNodes.clear();
                for (int j = 0; j < yellowList.size(); j++) {
                    if ((curentYellowFirstId - yellowList.get(j) == 21
                            || curentYellowFirstId - yellowList.get(j) == 20
                            || curentYellowFirstId - yellowList.get(j) == 19
                            || curentYellowFirstId - yellowList.get(j) == 1
                            || curentYellowFirstId - yellowList.get(j) == -21
                            || curentYellowFirstId - yellowList.get(j) == -20
                            || curentYellowFirstId - yellowList.get(j) == -19
                            || curentYellowFirstId - yellowList.get(j) == -1)
                            && !adjucentIdYellowFirstCount.contains(yellowList.get(j))) {
                        adjucentYellowFirstNodes.add(yellowList.get(j));
                        adjucentIdYellowFirstCount.add(yellowList.get(j));
                    }
                }
                if (adjucentYellowFirstNodes.size() > 0) {
                    curentYellowFirstId = adjucentYellowFirstNodes.getFirst();
                }
            }
        }
        return adjucentIdYellowFirstCount;
    }

    void poisonThrow(int poisonNode) {
        if (poisonBox % 20 == 0) {
            findBoxById(poisonBox - 20).setBackground(Color.black);
            findBoxById(poisonBox - 20).setEnabled(false);
            findBoxById(poisonBox - 19).setBackground(Color.black);
            findBoxById(poisonBox - 19).setEnabled(false);
            findBoxById(poisonBox + 21).setBackground(Color.black);
            findBoxById(poisonBox + 21).setEnabled(false);
            findBoxById(poisonBox + 20).setBackground(Color.black);
            findBoxById(poisonBox + 20).setEnabled(false);
            findBoxById(poisonBox + 1).setBackground(Color.black);
            findBoxById(poisonBox + 1).setEnabled(false);
        } else if ((poisonBox + 1) % 20 == 0) {
            findBoxById(poisonBox - 21).setBackground(Color.black);
            findBoxById(poisonBox - 21).setEnabled(false);
            findBoxById(poisonBox - 20).setBackground(Color.black);
            findBoxById(poisonBox - 20).setEnabled(false);
            findBoxById(poisonBox - 1).setBackground(Color.black);
            findBoxById(poisonBox - 1).setEnabled(false);
            findBoxById(poisonBox + 20).setBackground(Color.black);
            findBoxById(poisonBox + 20).setEnabled(false);
            findBoxById(poisonBox + 19).setBackground(Color.black);
            findBoxById(poisonBox + 19).setEnabled(false);
        } else if (poisonBox < 20) {
            findBoxById(poisonBox - 1).setBackground(Color.black);
            findBoxById(poisonBox - 1).setEnabled(false);
            findBoxById(poisonBox + 21).setBackground(Color.black);
            findBoxById(poisonBox + 21).setEnabled(false);
            findBoxById(poisonBox + 20).setBackground(Color.black);
            findBoxById(poisonBox + 20).setEnabled(false);
            findBoxById(poisonBox + 19).setBackground(Color.black);
            findBoxById(poisonBox + 19).setEnabled(false);
            findBoxById(poisonBox + 1).setBackground(Color.black);
            findBoxById(poisonBox + 1).setEnabled(false);
        } else if (poisonBox > 379) {
            findBoxById(poisonBox - 21).setBackground(Color.black);
            findBoxById(poisonBox - 21).setEnabled(false);
            findBoxById(poisonBox - 20).setBackground(Color.black);
            findBoxById(poisonBox - 20).setEnabled(false);
            findBoxById(poisonBox - 19).setBackground(Color.black);
            findBoxById(poisonBox - 19).setEnabled(false);
            findBoxById(poisonBox - 1).setBackground(Color.black);
            findBoxById(poisonBox - 1).setEnabled(false);
            findBoxById(poisonBox + 1).setBackground(Color.black);
            findBoxById(poisonBox + 1).setEnabled(false);
        } else {
            findBoxById(poisonBox - 21).setBackground(Color.black);
            findBoxById(poisonBox - 21).setEnabled(false);
            findBoxById(poisonBox - 20).setBackground(Color.black);
            findBoxById(poisonBox - 20).setEnabled(false);
            findBoxById(poisonBox - 19).setBackground(Color.black);
            findBoxById(poisonBox - 19).setEnabled(false);
            findBoxById(poisonBox - 1).setBackground(Color.black);
            findBoxById(poisonBox - 1).setEnabled(false);
            findBoxById(poisonBox + 21).setBackground(Color.black);
            findBoxById(poisonBox + 21).setEnabled(false);
            findBoxById(poisonBox + 20).setBackground(Color.black);
            findBoxById(poisonBox + 20).setEnabled(false);
            findBoxById(poisonBox + 19).setBackground(Color.black);
            findBoxById(poisonBox + 19).setEnabled(false);
            findBoxById(poisonBox + 1).setBackground(Color.black);
            findBoxById(poisonBox + 1).setEnabled(false);
        }
    }

    GameButtons findBoxById(int boxId) {
        GameButtons box = new GameButtons();
        for (int x = 0; x < 20; x++) {
            for (int y = 0; y < 20; y++) {
                if (Defining.boxes[x][y].getButtonId() == boxId) {
                    box = Defining.boxes[x][y];
                }
            }
        }

        return box;
    }

    void poisonCheckingP1() {
        if (Defining.poison1.getPoisonUsed() == false) {
            Defining.poison1.setEnabled(true);
            Defining.poison2.setEnabled(false);
        } else if (Defining.poison1.getPoisonUsed() == true && Defining.poison2.getPoisonUsed() == false) {
            Defining.poison1.setEnabled(false);
            Defining.poison2.setEnabled(true);
        } else {
            Defining.poison1.setEnabled(false);
            Defining.poison2.setEnabled(false);
        }

    }

    void poisonCheckingP2() {

        if (Defining.poison2.getPoisonUsed() == false) {
            Defining.poison2.setEnabled(true);
            Defining.poison1.setEnabled(false);
        } else if (Defining.poison2.getPoisonUsed() == true && Defining.poison1.getPoisonUsed() == false) {
            Defining.poison1.setEnabled(true);
            Defining.poison2.setEnabled(false);

        } else {
            Defining.poison1.setEnabled(false);
            Defining.poison2.setEnabled(false);
        }
    }

    void countRedWinner() {
        for (int k = 0; k < redList.size(); k++) {
            adjucentIdRedLastCount.clear();
            adjucentIdRedFirstCount.clear();
            try {
                adjucentIdRedLastCount = findAdjacentRedLast(redList.get(k));
                adjucentIdRedFirstCount = findAdjacentRedFirst(redList.get(k));

//                System.out.println("red " + adjucentIdRedLastCount);
//                System.out.println("red ///" + adjucentIdRedFirstCount);
                if (adjucentIdRedLastCount.size() > 8 || adjucentIdRedFirstCount.size() > 8) {
                    Defining.poison1.setEnabled(false);
                    Defining.poison2.setEnabled(false);
                    Defining.poison1.setPoisonUsed(true);
                    Defining.poison2.setPoisonUsed(true);
                    for (int i = 0; i < 20; i++) {
                        for (int j = 0; j < 20; j++) {
                            Defining.boxes[i][j].setEnabled(false);
                        }
                    }
                    JOptionPane.showMessageDialog(null, "Game Over. Player-2 is the Winner!!!");

                    break;
                }
            } catch (NoSuchElementException ne) {
            }
        }
    }

    void countYellowWinner() {
        for (int k = 0; k < yellowList.size(); k++) {
            adjucentIdYellowFirstCount.clear();
            adjucentIdYellowLastCount.clear();
            try {
                findAdjacentYellowFirst(yellowList.getFirst());
                findAdjacentYellowLast(yellowList.getFirst());
//                System.out.println("yellow " + adjucentIdYellowFirstCount);
//                System.out.println("yellow " + adjucentIdYellowLastCount);
                if (adjucentIdYellowLastCount.size() > 8 || adjucentIdYellowFirstCount.size() > 8) {
                    Defining.poison1.setEnabled(false);
                    Defining.poison2.setEnabled(false);
                    Defining.poison1.setPoisonUsed(true);
                    Defining.poison2.setPoisonUsed(true);
                    for (int i = 0; i < 20; i++) {
                        for (int j = 0; j < 20; j++) {
                            Defining.boxes[i][j].setEnabled(false);
                        }
                    }
                    JOptionPane.showMessageDialog(null, "Game Over. Player-1 is the Winner!!!");

                    break;
                }
            } catch (NoSuchElementException ne) {
            }
        }
    }
}
