//import java.awt.Color;
//
//import javax.swing.JFrame;
//
//
//public class Main {
//
//	public static void main(String[] args) {
//		JFrame obj=new JFrame();
//		Gameplay gamePlay = new Gameplay();
//		
//		obj.setBounds(10, 10, 800, 630);
//		obj.setTitle("Tank 2D");	
//		obj.setBackground(Color.gray);
//		obj.setResizable(false);
//		
//		obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		obj.add(gamePlay);
//		obj.setVisible(true);
//
//	}
//
//}

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                JFrame frame = new JFrame("Игра в танчики");
                Gameplay gameplay = new Gameplay();
                frame.add(gameplay);
                frame.setSize(810, 640);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
            }
        });
    }
}
