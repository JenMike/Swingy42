package jmichael.swingy.view.interfaces;


public interface StartGameView {
    String GREEN = "\u001B[32m";
    String CYAN = "\u001B[36m";

    void render();

    void switchGameMode();

    void loadCreateAvatar();

    void loadSelectAvatar();
}
