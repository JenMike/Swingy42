package jmichael.swingy.view.console;

import jmichael.swingy.Main;
import jmichael.swingy.controller.LoadAvatarController;
import jmichael.swingy.view.interfaces.LoadAvatarView;

import java.util.Scanner;


public class LoadAvatarConsoleView implements LoadAvatarView {

    private LoadAvatarController controller;
    private int lastIdx = -1;

    @Override
    public void render() {
        controller = new LoadAvatarController(this);

        getInput();
    }

    private void getInput() {
        Scanner scanner = Main.getScanner();

        System.out.println(CYAN+"** [Available heroes] **");
        printHeroes(controller.getListData());

        System.out.println();
        System.out.println(CYAN+"******************************************************");
        System.out.println(CYAN+"***            -- OPTIONS: --                      ***");
        System.out.println(CYAN+"*** [CREATE] - To create Avatar                    ***");
        System.out.println(CYAN+"*** [INT] - [int] To load data of chosen Avatar    ***");
        System.out.println(CYAN+"*** [SELECT] - enter after index                   ***");
        System.out.println(CYAN+"***                                                ***");
        System.out.println(CYAN+"******************************************************");
        System.out.println(GREEN+"** - Enter a command - **");
        while (scanner.hasNext()) {
            String input = scanner.nextLine();

            if ("create".equalsIgnoreCase(input)) {
                controller.onCreateAvatar();
                break;
            } else if (isValidNumericString(input, controller.getListData().length)) {
                lastIdx = Integer.parseInt(input) - 1;
                controller.onListElementSelected(lastIdx);
            } else if ("select".equalsIgnoreCase(input) && lastIdx != -1) {
                controller.onSelectAvatar(lastIdx);
                break;
            } else {
                System.out.println("** ERROR: [Invalid Command] **");
            }
        }
    }

    private boolean isValidNumericString(String str, int max) {
        try {
            int n = Integer.parseInt(str);
            if (n <= 0 || n > max)
                return false;
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    private void printHeroes(String[] heroes) {
        if (heroes.length == 0)
            System.out.println(RED+ "[No previously saved heroes]");
        for (String hero : heroes) {
            System.out.println(GREEN+hero);
        }
    }

    @Override
    public void updatePaneText(String info) {
        System.out.println(info);
    }

    @Override
    public void showErrorPopup(String errorText) {
        System.out.println(RED+"** [Error] :: " + errorText +"**");
        getInput();
    }

    @Override
    public void loadGame() {
        new GameConsoleView().render();
    }

    @Override
    public void loadCreateAvatar() {
        new CreateAvatarConsoleView().render();
    }
}
