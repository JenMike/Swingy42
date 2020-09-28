package jmichael.swingy.view.console;

import jmichael.swingy.Main;
import jmichael.swingy.view.gui.StartGameGuiView;
import jmichael.swingy.view.interfaces.StartGameView;
import jmichael.swingy.controller.StartGameController;

import java.util.Scanner;

public class StartGameConsoleView implements StartGameView {

    private StartGameController controller;

    @Override
    public void render() {
        controller = new StartGameController(this);
        System.out.println(CYAN+"**************************************************");
        System.out.println(CYAN+"*****                                        *****");
        System.out.println(CYAN+"*****"+GREEN+" 0_0 [SWINGY AVATAR - CONSOLE] 0_0   "+CYAN+"  *****");
        System.out.println(CYAN+"*****                                       *****");
        System.out.println(CYAN+"*****           -- OPTIONS: --              *****");
        System.out.println(CYAN+"*****    [CREATE | To create Avatar]        *****");
        System.out.println(CYAN+"*****    [SELECT | To load a saved Avatar]  *****");
        System.out.println(CYAN+"*****    [GUI    | To create Avatar]        *****");
        System.out.println(CYAN+"*****                                       *****");
        System.out.println(CYAN+"*************************************************");

        Scanner scanner = Main.getScanner();
        System.out.println();
        System.out.println(GREEN+"** [Enter Command Below] **");
        while (scanner.hasNext()) {
            String input = scanner.nextLine();

            if ("create".equalsIgnoreCase(input)) {
                controller.onCreateAvatar();
                break;
            } else if ("select".equalsIgnoreCase(input)) {
                controller.onSelectAvatar();
                break;
            } else if ("gui".equalsIgnoreCase(input)) {
                controller.onSwitchMode();
                break;
            } else {
                System.out.println("** ERROR: [Invalid Command] **");
            }
        }
    }

    @Override
    public void loadCreateAvatar() {
        new CreateAvatarConsoleView().render();
    }

    @Override
    public void switchGameMode() {
        new StartGameGuiView().render();
    }

    @Override
    public void loadSelectAvatar() {
        new LoadAvatarConsoleView().render();
    }
}
