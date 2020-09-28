package jmichael.swingy.view.gui;

import jmichael.swingy.Main;
import jmichael.swingy.view.interfaces.CreateAvatarView;
import jmichael.swingy.controller.CreateAvatarController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class CreateAvatarGuiView extends JPanel implements CreateAvatarView {

    private JLabel avatarNameLabel = new JLabel(" < Avatar name > ");
    private JTextField avatarFieldBox = new JTextField(17);
    private JButton createButton = new JButton("Create Avatar");
    private String[] avatarClasses = {"Monk", "Cleric", "Wizard", "Paladin", "Druid", "Fighter"};
    private JLabel avatarClass = new JLabel(" < Class > ");
    private JComboBox<String> classesComboBox = new JComboBox<>(avatarClasses);
    private JEditorPane gamePane = new JEditorPane();

    private CreateAvatarController controller;

    @Override
    public void render() {
        controller = new CreateAvatarController(this);

        buildUI();
    }

    @Override
    public void getUserInput() {

    }

    private void buildUI() {
        Main.getFrame().setTitle("Create Avatar");
        this.setLayout(new GridBagLayout());
        this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        this.setBackground(Color.BLACK);
        avatarClass.setBackground(Color.black);
        classesComboBox.setBackground(Color.BLACK);
        classesComboBox.setForeground(Color.ORANGE);


        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        JPanel createAvatarPanel = new JPanel();
        createAvatarPanel.add(avatarNameLabel);
        createAvatarPanel.add(avatarFieldBox);
        createAvatarPanel.setBackground(Color.BLACK);
        createAvatarPanel.setVisible(true);
        this.add(createAvatarPanel, gbc);

        avatarClass.setForeground(Color.LIGHT_GRAY);
        avatarFieldBox.setBackground(Color.LIGHT_GRAY);
        avatarNameLabel.setForeground(Color.RED);
        avatarClass.setForeground(Color.RED);
        createButton.setBackground(Color.black);
        createButton.setForeground(Color.red);

        JPanel classPannel = new JPanel();
        classPannel.add(avatarClass);
        classPannel.setBackground(Color.BLACK);
        classesComboBox.setSelectedIndex(0);
        classPannel.add(classesComboBox);
        classPannel.setVisible(true);
        this.add(classPannel, gbc);

        gamePane.setEditable(false);
        gamePane.setFont(new Font("monospaced", Font.PLAIN, 14));
        gamePane.setBackground(Color.BLACK);
        gamePane.setForeground(Color.GREEN);
        gamePane.setText("\n"+"\n"+"\n"+"\n"+"\n"+"        *****************************************\n"+
                "        *****************************************\n"+
                "        ** Classes   attack   defense      hp  **\n"+
                "        -----------------------------------------\n"+
                "        ** Monk       [50]      [80]      [100]**\n" +
                "        **-------------------------------------**\n" +
                "        ** Cleric     [40]      [35]      [90] **\n" +
                "        **-------------------------------------**\n" +
                "        ** Wizard     [45]      [15]      [90] **\n" +
                "        **-------------------------------------**\n" +
                "        ** Druid      [35]      [25]      [120]**\n" +
                "        **-------------------------------------**\n" +
                "        ** Fighter    [95]      [20]      [75] **\n" +
                "        **-------------------------------------**\n" +
                "        ** Paladin    [35]      [45]      [110]**\n" +
                "        *****************************************\n"+
                "        *****************************************\n");
        gamePane.setPreferredSize(new Dimension(520, 490));
        gamePane.setMinimumSize(new Dimension(250, 250));
        this.add(gamePane, gbc);

        this.add(createButton, gbc);
        this.setVisible(true);

        Main.getFrame().setContentPane(this);
        Main.getFrame().revalidate();
        Main.showFrame();

        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.onConfirmAvatar(avatarFieldBox.getText(), (String) classesComboBox.getSelectedItem());
            }
        });
    }

    @Override
    public void showErrorPopup(String popupText) {
        JOptionPane.showMessageDialog(Main.getFrame(), popupText);
    }

    @Override
    public void loadGame() {
        this.setVisible(false);
        new GameGuiView().render();
    }
}
