package jmichael.swingy.view.gui;

import jmichael.swingy.Main;
import jmichael.swingy.view.console.StartGameConsoleView;
import jmichael.swingy.view.interfaces.StartGameView;
import jmichael.swingy.controller.StartGameController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class StartGameGuiView extends JPanel implements StartGameView {

    private JButton createButton = new JButton("CREATE");
    private JButton selectButton = new JButton("SELECT");
    private JButton switchModeButton = new JButton("CONSOLE");

    private StartGameController controller;

    @Override
    public void render() {
        controller = new StartGameController(this);

        renderUI();
    }

    private void renderUI() {
        Main.getFrame().setTitle("Start");
        this.setLayout(new GridBagLayout());
        this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        this.setBackground(Color.BLACK);
        this.setForeground(Color.GREEN);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        this.add(createButton, gbc);
        this.add(selectButton, gbc);
        this.add(switchModeButton, gbc);

        createButton.setBackground(Color.BLACK);
        selectButton.setBackground(Color.BLACK);
        switchModeButton.setBackground(Color.BLACK);
        createButton.setForeground(Color.GREEN);
        selectButton.setForeground(Color.GREEN);
        switchModeButton.setForeground(Color.GREEN);

        this.setVisible(true);
        Main.getFrame().setContentPane(this);
        Main.getFrame().revalidate();
        Main.showFrame();

        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.onCreateAvatar();
            }
        });
        selectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.onSelectAvatar();
            }
        });
        switchModeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.onSwitchMode();
            }
        });
    }

    @Override
    public void loadCreateAvatar() {
        this.setVisible(false);
        new CreateAvatarGuiView().render();
    }

    @Override
    public void switchGameMode() {
        Main.hideFrame();
        new StartGameConsoleView().render();
    }

    @Override
    public void loadSelectAvatar() {
        this.setVisible(false);
        new LoadAvatarGuiView().render();
    }
}
