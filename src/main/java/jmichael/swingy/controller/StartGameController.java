package jmichael.swingy.controller;

import jmichael.swingy.view.interfaces.StartGameView;

public class StartGameController {

    private StartGameView view;

    public StartGameController(StartGameView view) {
        this.view = view;
    }

    public void onCreateAvatar() {
        view.loadCreateAvatar();
    }

    public void onSwitchMode() {
        view.switchGameMode();
    }

    public void onSelectAvatar() {
        view.loadSelectAvatar();
    }
}
