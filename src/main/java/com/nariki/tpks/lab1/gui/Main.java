package com.nariki.tpks.lab1.gui;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex) {
            // do nothing, standard look and feel will be loaded
        }
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createTheGUIComponents();
            }
        });
    }

    private static void createTheGUIComponents() {
        new MainFrame().setVisible(true);
    }
}
