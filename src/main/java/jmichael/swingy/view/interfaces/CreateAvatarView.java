package jmichael.swingy.view.interfaces;

public interface CreateAvatarView {
    String RED = "\u001B[31m";
    String GREEN = "\u001B[32m";
    String CYAN = "\u001B[36m";

    void render();

    void getUserInput();

    void showErrorPopup(String popupText);

    void loadGame();
}
