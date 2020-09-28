package jmichael.swingy.view.console;

import jmichael.swingy.Main;
import jmichael.swingy.view.interfaces.CreateAvatarView;
import jmichael.swingy.controller.CreateAvatarController;

import java.util.Scanner;

public class CreateAvatarConsoleView implements CreateAvatarView {

    private CreateAvatarController controller;

    @Override
    public void render() {
        controller = new CreateAvatarController(this);

        getUserInput();
    }

    @Override
    public void getUserInput() {
        Scanner scanner = Main.getScanner();

        System.out.println(GREEN+"** [Enter New Avatar Name and Class] **");
        System.out.println(CYAN+"** [ENTER NAME] **");
        String name = scanner.nextLine();
        System.out.println(CYAN+"*****************************************\n"+
                "*****************************************\n"+GREEN+
                "** Classes   attack   defense      hp  **\n"+CYAN+
                "-----------------------------------------\n"+
                "** Monk       [50]      [80]      [100]**\n" +
                "** Cleric     [40]      [35]      [95] **\n" +
                "** Wizard     [45]      [15]      [90] **\n" +
                "** Druid      [35]      [25]      [120]**\n" +
                "** Fighter    [95]      [20]      [75] **\n" +
                "** Paladin    [35]      [45]      [110]**\n" +
                "*****************************************\n"+
                "*****************************************\n");
        System.out.println(GREEN+"** [ENTER CLASS] **");
        String heroClass = scanner.nextLine();
        System.out.println(GREEN+"** Confirm? (y to continue | ctrl+c to quit) **");
        while (scanner.hasNext()) {
            String input = scanner.nextLine();

            if ("y".equalsIgnoreCase(input)) {
                controller.onConfirmAvatar(name, heroClass);
                break;
            } else {
                System.out.println("** ERROR :: [Invalid Command] **");
            }
        }
    }

    @Override
    public void showErrorPopup(String popupText) {
        System.out.println(RED+"** [Error] :: " + popupText + "**");
    }

    @Override
    public void loadGame() {
        new GameConsoleView().render();
    }
}
