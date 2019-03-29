package GUI;

import java.awt.Graphics;
import java.awt.GraphicsConfiguration;
import java.awt.Color;
import javax.swing.*;

public class Main extends JPanel{
    static GraphicsConfiguration gc;
    public void paint(Graphics g){

    }
    public static void main(String[] args) {
        JFrame gameFrame = new JFrame();
        JPanel playerTurnPanel = new JPanel();

        JButton coupButton, actionButton;
        coupButton = new JButton("Coup");
        actionButton = new JButton("Actions");
        playerTurnPanel.setLayout(null)     ;
        coupButton.setBounds(250, 720, 100, 60);
        actionButton.setBounds(350, 720, 100, 60);
        playerTurnPanel.add(coupButton);
        playerTurnPanel.add(actionButton);
        playerTurnPanel.setBackground(Color.darkGray);

        gameFrame.add(playerTurnPanel);
        gameFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        gameFrame.setSize(800, 800);
        gameFrame.setResizable(false);
        gameFrame.setVisible(true);

    }


}
