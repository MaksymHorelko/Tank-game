package main;

import javax.swing.*;

import graphics.Gameplay;

public final class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                JFrame frame = new JFrame("Tank-game");
                Gameplay gameplay = new Gameplay();
                frame.add(gameplay);
                frame.setSize(810, 640);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
            }
        });
    }
}
