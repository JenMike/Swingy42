package jmichael.swingy.view.gui;

import jmichael.swingy.Main;
import jmichael.swingy.beans.Game;
import jmichael.swingy.view.console.GameConsoleView;
import jmichael.swingy.view.interfaces.GameView;
import jmichael.swingy.controller.GameController;
import jmichael.swingy.beans.GameMap;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameGuiView extends JPanel implements GameView {

    private String[] directions = {"W", "D", "S", "A"};
    private JComboBox<String> directionsComboBox = new JComboBox<>(directions);
    private JButton moveButton = new JButton("Move Avatar");
    private JButton switchButton = new JButton("Console");

    private JEditorPane gamePane = new JEditorPane();
    private JEditorPane mapPane = new JEditorPane();

    private GameController controller;

    @Override
    public void render() {
        controller = new GameController(this);

        renderUI();
        controller.renderView();
    }

    private void renderUI() {
        Main.getFrame().setTitle("Game");
        this.setLayout(new GridBagLayout());
        this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        this.setBackground(Color.BLACK);
        this.setForeground(Color.CYAN);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.fill = GridBagConstraints.VERTICAL;


        gamePane.setEditable(false);
        gamePane.setText("Select hero to see information");
        gamePane.setPreferredSize(new Dimension(720, 690));
        gamePane.setMinimumSize(new Dimension(400, 400));
        this.add(gamePane, gbc);
        gamePane.setBackground(Color.BLACK);
        gamePane.setForeground(Color.CYAN);
        gbc.insets = new Insets(5, 5, 10, 5);

        mapPane.setEditable(false);
        mapPane.setText("Map");
        JScrollPane mapScroll = new JScrollPane(mapPane);
        mapScroll.setPreferredSize(new Dimension(300, 300));
        mapScroll.setMinimumSize(new Dimension(200, 200));

        directionsComboBox.setSelectedIndex(0);
        this.add(directionsComboBox, gbc);
        this.add(moveButton, gbc);
        this.add(switchButton, gbc);

        this.setBackground(Color.BLACK);
        this.setForeground(Color.GREEN);
        this.setVisible(true);
        Main.getFrame().setContentPane(this);
        Main.getFrame().revalidate();
        Main.showFrame();

        moveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.moveAvatar((String) directionsComboBox.getSelectedItem());
            }
        });
        switchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.onSwitchModeCommand();
            }
        });
    }

    @Override
    public void printGameMap(boolean[][] mapArray, GameMap heroPosition) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(String.format("[GAME MAP SIZE - %dx%d]\n", mapArray.length, mapArray.length));
        for (int i = 0; i < mapArray.length; i++) {
            for (int j = 0; j < mapArray[i].length; j++) {
                if (heroPosition.getX() == j && heroPosition.getY() == i)
                    stringBuilder.append("[0.0\"] ");
                else if (mapArray[i][j])
                    stringBuilder.append("[ -.-] ");
                else
                    stringBuilder.append("[    ] ");
            }
            stringBuilder.append("\n");
        }
        mapPane.setText(stringBuilder.toString());

    }

    @Override
    public void updateGameData(Game game) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("            [** -- MAP -- **]\n\n     ");
        for (int i = 0; i < game.getMap().length; i++) {
            for (int j = 0; j < game.getMap()[i].length; j++) {
                if (game.getHeroPosition().getX() == j && game.getHeroPosition().getY() == i)
                    stringBuilder.append("[0.0]");
                else if (game.getMap()[i][j])
                    stringBuilder.append("[  -.-]");
                else
                    stringBuilder.append("[     ]");
            }
            stringBuilder.append("      \n     ");

            //     printMap(game.getMap(), game.getHeroCoord());
        }
        stringBuilder.append("\n");
        stringBuilder.append(String.format("         ** -%d x %d- **\n\n", game.getMap().length, game.getMap().length));
        stringBuilder.append("          ** < X -- [" + game.getHeroPosition().getX() +
                "," + game.getHeroPosition().getY() + "] -- Y > **\n");
        gamePane.setText(stringBuilder.toString() + "\n" + game.getHero().toString());
    }

    @Override
    public void endGame() {
        Main.hideFrame();
        Main.getFrame().dispose();
        Main.closeConnections();
    }

    @Override
    public void showPopup(String popupText) {
        JOptionPane.showMessageDialog(Main.getFrame(), popupText);
    }

    @Override
    public void getBattleInput() {
        Object options[] = {"Fight", "Run"};

        int result = JOptionPane.showOptionDialog(Main.getFrame(),
                "OH NO! You have encountered the fire nation!",
                "Fight or run?", JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
        if (result == JOptionPane.YES_OPTION)
            controller.onFight();
        else
            controller.run();
    }

    @Override
    public boolean swapArtifact(String currentArtifact) {
        Object options[] = {"Replace", "Leave"};

        int result = JOptionPane.showOptionDialog(Main.getFrame(),
                "Would you like to replace " + currentArtifact + "?",
                "Pick up and equip new weapon, or leave?", JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
        return result == JOptionPane.YES_OPTION;
    }

    @Override
    public void switchGameMode() {
        Main.hideFrame();
        new GameConsoleView().render();
    }
}
