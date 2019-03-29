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
        playerTurnPanel.setLayout(null);

        JButton coupButton, actionButton, taxButton = createButton("Tax", Color.GRAY, 20, 20 , 100, 100);
        coupButton = new JButton("Coup");
        actionButton = new JButton("Actions");
        coupButton.setBackground(Color.GRAY);
        coupButton.setOpaque(true);
        coupButton.setBorderPainted(false);
        coupButton.setBounds(400, 720, 100, 60);
        actionButton.setBounds(500, 720, 100, 60);
        playerTurnPanel.add(coupButton);
        playerTurnPanel.add(actionButton);
        playerTurnPanel.add(taxButton);
        playerTurnPanel.setBackground(Color.darkGray);


        gameFrame.add(playerTurnPanel);
        gameFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        gameFrame.setSize(1000, 800);
        gameFrame.setResizable(false);
        gameFrame.setVisible(true);
    }

    public static JButton createButton( String buttonText, Color color, int xLocation, int yLocation, int width, int height) {
        JButton button= new JButton(buttonText);
        button.setBounds(xLocation,yLocation,width,height);
        button.setBackground(color);
        button.setOpaque(true);
        button.setBorderPainted(false);
        return button;
    }


}
