package jmichael.swingy.view.gui;

import jmichael.swingy.Main;
import jmichael.swingy.controller.LoadAvatarController;
import jmichael.swingy.view.interfaces.LoadAvatarView;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class LoadAvatarGuiView extends JPanel implements LoadAvatarView {

    private JEditorPane textPane = new JEditorPane();
    private JButton selectButton = new JButton("Select");
    private JButton createButton = new JButton("Create");

    private LoadAvatarController controller;
    private int lastSelectedIdx;

    @Override
    public void render() {
        controller = new LoadAvatarController(this);

        buildUI();
    }

    private void buildUI() {
        Main.getFrame().setTitle("Load Saved Hero");
        this.setLayout(new GridBagLayout());
        this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        this.setBackground(Color.BLACK);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        String[] data = controller.getListData();
        final JList list = new JList(data);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setLayoutOrientation(JList.VERTICAL);
        list.setVisibleRowCount(-1);
        JScrollPane listScroll = new JScrollPane(list);
        listScroll.setPreferredSize(new Dimension(200, 200));
        listScroll.setMinimumSize(new Dimension(150, 150));
        this.add(listScroll);

        textPane.setEditable(false);
        textPane.setText("Select hero to see stats");
        if (data.length == 0)
            textPane.setText("No avatars were previously saved.");
        JScrollPane infoScroll = new JScrollPane(textPane);
        infoScroll.setPreferredSize(new Dimension(200, 200));
        infoScroll.setMinimumSize(new Dimension(150, 150));
        this.add(infoScroll, gbc);

        this.add(selectButton, gbc);
        this.add(createButton, gbc);
        selectButton.setBackground(Color.BLACK);
        createButton.setBackground(Color.BLACK);
        selectButton.setForeground(Color.GREEN);
        createButton.setForeground(Color.GREEN);

        //BRO
        selectButton.setEnabled(false);
        textPane.setPreferredSize(new Dimension(720, 690));
        textPane.setMinimumSize(new Dimension(400, 400));

        this.setVisible(true);

        Main.getFrame().setContentPane(this);
        Main.getFrame().revalidate();
        Main.showFrame();

        list.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    if (list.getSelectedIndex() != -1) {
                        controller.onListElementSelected(list.getSelectedIndex());
                        selectButton.setEnabled(true);
                        lastSelectedIdx = list.getSelectedIndex();
                    } else
                        selectButton.setEnabled(false);
                }
            }
        });
        selectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.onSelectAvatar(lastSelectedIdx);
            }
        });
        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.onCreateAvatar();
            }
        });
    }

    @Override
    public void updatePaneText(String text) {
        textPane.setText(text);
    }

    @Override
    public void showErrorPopup(String errorText) {
        JOptionPane.showMessageDialog(Main.getFrame(), errorText);
    }

    @Override
    public void loadGame() {
        this.setVisible(false);
        new GameGuiView().render();
    }

    @Override
    public void loadCreateAvatar() {
        this.setVisible(false);
        new CreateAvatarGuiView().render();
    }
}
