import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class pongFrame extends JFrame{

    pongPanel panel;

    pongFrame(){
        panel = new pongPanel();
        this.add(panel);
        this.setTitle("Pong Game");
        this.setResizable(false);
        this.setBackground(Color.BLACK);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }
}
