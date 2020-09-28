package jmichael.swingy.view.console;

import jmichael.swingy.Main;
import jmichael.swingy.beans.Game;
import jmichael.swingy.view.interfaces.GameView;
import jmichael.swingy.controller.GameController;
import jmichael.swingy.beans.GameMap;
import jmichael.swingy.view.gui.GameGuiView;

import java.util.Scanner;

public class GameConsoleView implements GameView {

    private GameController controller;

    @Override
    public void render() {
        controller = new GameController(this);

        controller.renderView();
    }

    @Override
    public void updateGameData(Game game) {
        System.out.println(GREEN+"******* - Avatar Stats - *******");
        System.out.println(GREEN+ game.getHero().toString() +
                "      MAP POSITION: " + "(" + game.getHeroPosition().getX() +
                "," + game.getHeroPosition().getY() + ")");
        System.out.println(GREEN+"*******************************");
        System.out.println();
        controller.view.printGameMap(controller.game.getMap(), controller.game.getHeroPosition());
        getUserCommand();
    }

    private void getUserCommand() {
        Scanner scanner = Main.getScanner();

        System.out.println(CYAN+"**************************************************");
        System.out.println(CYAN+"***              -- DIRECTIONS: --             ***");
        System.out.println(CYAN+"***                                            ***");
        System.out.println(CYAN+"***         [W] - To move NORTH <up>           ***");
        System.out.println(CYAN+"***         [A] - To move WEST <left>          ***");
        System.out.println(CYAN+"***         [S] - To move SOUTH <down>         ***");
        System.out.println(CYAN+"***         [D] - To move EAST <right>         ***");
        System.out.println(CYAN+"***         [GUI] - To switch mode             ***");
        System.out.println(CYAN+"***                                            ***");
        System.out.println(CYAN+"**************************************************");
        System.out.println(GREEN+"** [Enter Command Below]: **");
        while (scanner.hasNext()) {
            String input = scanner.nextLine();

            if("map".equalsIgnoreCase(input)){
                controller.onRenderGameMap();
                break;
            }
            else if ("w".equalsIgnoreCase(input) ||
                    "d".equalsIgnoreCase(input) ||
                    "s".equalsIgnoreCase(input) ||
                    "a".equalsIgnoreCase(input)) {
                controller.moveAvatar(input);
                break;
            } else if ("gui".equalsIgnoreCase(input)) {
                controller.onSwitchModeCommand();
                break;
            } else {
                System.out.println("** ERROR: [Invalid Command] **");
            }
        }
    }

    @Override
    public void printGameMap(boolean[][] mapArray, GameMap heroPosition) {

        System.out.println(GREEN+"[** -- MAP -- **]");
        System.out.println();
        for (int i = 0; i < mapArray.length; i++) {
            for (int j = 0; j < mapArray[i].length; j++) {
                if (heroPosition.getX() == j && heroPosition.getY() == i)
                    System.out.print(GREEN+"[0_0] ");
                else if (mapArray[i][j])
                    System.out.print(RED+"[-.-] ");
                else
                    System.out.print(CYAN+"[   ] ");
            }
            System.out.println();
        }
        System.out.printf(GREEN+"***** %d x %d *****", mapArray.length, mapArray.length);
        System.out.println();
    }

    @Override
    public void endGame() {
        System.out.println(GREEN+"[THANKS FOR PLAYING! GOODBYE :) ]");
        Main.getFrame().dispose();
        Main.closeConnections();
    }

    @Override
    public void showPopup(String popupText) {
        System.out.println(popupText);
    }

    @Override
    public void getBattleInput() {
        Scanner scanner = Main.getScanner();

        System.out.println();
        System.out.println(RED+"-- [OH NO! a Fire Bender is in your way!] --");
        System.out.println();
        System.out.println(CYAN+"***********************************************************");
        System.out.println(CYAN+"****               --OPTIONS:--                        ****");
        System.out.println(CYAN+"****    [F] - to pay respects and FIGHT Zuko!          ****");
        System.out.println(CYAN+"****    [R] - to run away with a 50% chance to win     ****");
        System.out.println(CYAN+"***********************************************************");
        System.out.println();
        System.out.println(GREEN+"** -Enter Command Below- **");
        System.out.println();
        while (scanner.hasNext()) {
            String input = scanner.nextLine();

            if ("f".equalsIgnoreCase(input)) {
                controller.onFight();
                break;
            } else if ("r".equalsIgnoreCase(input)) {
                controller.run();
                break;
            } else {
                System.out.println("** ERROR: [Invalid Command] **");
            }
        }
    }

    @Override
    public boolean swapArtifact(String currentArtifact) {
        Scanner scanner = Main.getScanner();

        System.out.println();
        System.out.println(GREEN+"Would you like to change your artifact [" + currentArtifact + "] ?");
        System.out.println();
        System.out.println(CYAN+"****************************************************");
        System.out.println(CYAN+"****               --OPTIONS:--                 ****");
        System.out.println(CYAN+"****    [KEEP] - to keep your artifact          ****");
        System.out.println(CYAN+"****    [CHANGE] - to equip a new artifact      ****");
        System.out.println(CYAN+"****************************************************");
        System.out.println();
        System.out.println(GREEN+"** -Enter Command Below- **");
        while (scanner.hasNext()) {
            String input = scanner.nextLine();

            if ("keep".equalsIgnoreCase(input)) {
                return false;
            } else if ("change".equalsIgnoreCase(input)) {
                return true;
            } else {
                System.out.println("** ERROR: [Invalid Command] **");
            }
        }
        return false;
    }

    @Override
    public void switchGameMode() {
        new GameGuiView().render();
    }
}
