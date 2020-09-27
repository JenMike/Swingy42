package jmichael.swingy.view.interfaces;

public interface LoadAvatarView {
    String RED = "\u001B[31m";
    String GREEN = "\u001B[32m";
    String CYAN = "\u001B[36m";

    void render();

    void updatePaneText(String text);

    void loadGame();

    void showErrorPopup(String errorText);

    void loadCreateAvatar();
}
