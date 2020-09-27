package jmichael.swingy;

import jmichael.swingy.sqlite.SqliteDB;
import jmichael.swingy.view.console.StartGameConsoleView;
import jmichael.swingy.view.gui.StartGameGuiView;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Scanner;

public class Main {

    private static JFrame frame;
    private static Scanner scanner;

    public static void main(String[] args) {
        if (args.length != 1 || (!args[0].equals("console") && !args[0].equals("gui"))) {
            JOptionPane.showMessageDialog(null, "Please enter console or gui as argument","Error",JOptionPane.WARNING_MESSAGE);
            System.exit(1);
        }

        SqliteDB.connect();

        if (args[0].equals("console"))
            new StartGameConsoleView().render();
        else if (args[0].equals("gui"))
            new StartGameGuiView().render();
    }

    public static JFrame getFrame() {
        if (frame == null) {
            frame = new JFrame();
            frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            frame.setSize(500, 400);
            frameListener();
        }
        return frame;
    }

    public static void showFrame() {
        if (frame != null)
            frame.setVisible(true);
    }

    public static void hideFrame() {
        if (frame != null)
            frame.setVisible(false);
    }

    public static Scanner getScanner() {
        if (scanner == null)
            scanner = new Scanner(System.in);
        return scanner;
    }

    public static void closeConnections() {
        SqliteDB.close();
        if (scanner != null)
            scanner.close();
    }


    private static void frameListener() {
        getFrame().addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                closeConnections();
                super.windowClosing(e);
            }
        });
    }
}
