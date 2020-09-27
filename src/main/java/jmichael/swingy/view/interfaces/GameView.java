package jmichael.swingy.view.interfaces;

import jmichael.swingy.beans.Game;
import jmichael.swingy.beans.GameMap;

public interface GameView {
    String RED = "\u001B[31m";
    String GREEN = "\u001B[32m";
    String PURPLE = "\u001B[35m";
    String CYAN = "\u001B[36m";

    void render();

    void printGameMap(boolean[][] mapArray, GameMap heroPosition);

    void showPopup(String popupText);

    void updateGameData(Game game);

    void getBattleInput();

    boolean swapArtifact(String currentArtifact);

    void switchGameMode();

    void endGame();
}
