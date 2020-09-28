package jmichael.swingy.controller;

import jmichael.swingy.beans.Game;
import jmichael.swingy.beans.Armor;
import jmichael.swingy.beans.Artifact;
import jmichael.swingy.beans.Helmet;
import jmichael.swingy.beans.Weapon;
import jmichael.swingy.beans.avatar.Hero;
import jmichael.swingy.beans.avatar.Villain;
import jmichael.swingy.sqlite.SqliteDB;
import jmichael.swingy.view.interfaces.GameView;
import jmichael.swingy.beans.GameMap;

import java.util.Random;


public class GameController {

    public GameView view;
    public Game game;
    private GameMap previousPosition;

    public GameController(GameView view) {
        this.view = view;
        game = Game.getInstance();
        previousPosition = new GameMap(0, 0);
    }

    public void renderView() {
        view.updateGameData(game);
    }

    public void onRenderGameMap() {
    //    view.printMap(game.getMap(), game.getHeroPosition());
        view.updateGameData(game);
    }

    public void moveAvatar(String direction) {
        int x = game.getHeroPosition().getX();
        int y = game.getHeroPosition().getY();
        previousPosition.setX(x);
        previousPosition.setY(y);

        switch (direction.toUpperCase()) {
            case "W":
                y--;
                break;
            case "D":
                x++;
                break;
            case "S":
                y++;
                break;
            case "A":
                x--;
                break;
        }

        if (x < 0 || y < 0 || x >= game.getMapSize() || y >= game.getMapSize()) {
            winGame();

            return;
        }

        game.getHeroPosition().setX(x);
        game.getHeroPosition().setY(y);

        if (game.getMap()[y][x]) {
            villainEncountered();
        }

        if (game.getHero().getHitPoints() > 0)
            view.updateGameData(game);
    }

    private void winGame() {
        view.showPopup("[VICTORY!] You won the battle against the firebenders! here's an extra " + game.getMapSize() * 100 + "XP!");
        levelUpText(game.getMapSize() * 100);
        updateDB();
        view.endGame();
    }

    private void updateDB() {
        Hero hero = game.getHero();
        SqliteDB.updateAvatarStats(hero);
    }

    private void villainEncountered() {
        view.getBattleInput();
    }

    public void run() {
        if (new Random().nextBoolean()) {
            view.showPopup("-- [PHEW! You moved to your previous position!] --");
            game.getHeroPosition().setX(previousPosition.getX());
            game.getHeroPosition().setY(previousPosition.getY());
        } else {
            view.showPopup("-- [Nice try! But you have to stay and fight!] --");
            onFight();
        }
    }

    private void setArtifact(Artifact artifact) {
        if (artifact != null) {
            if (artifact instanceof Weapon) {
                if (game.getHero().getWeapon() == null || view.swapArtifact("your weapon: " + game.getHero().getWeapon() + ", found: " + artifact)) {
                    game.getHero().equipWeapon((Weapon) artifact);
                    view.showPopup("You are now equipped with a " + artifact + "as your [weapon]! Respect the potato gun, or become FRIES!");
                }
            } else if (artifact instanceof Helmet) {
                if (game.getHero().getHelmet() == null || view.swapArtifact("your helmet: " + game.getHero().getHelmet() + ", found: " + artifact)) {
                    game.getHero().equipHelm((Helmet) artifact);
                    view.showPopup("You are now equipped with a " + artifact + "as your [helmet]! Use it wisely, young Avatar!");
                }
            } else if (artifact instanceof Armor) {
                if (game.getHero().getArmor() == null || view.swapArtifact("your armor: " + game.getHero().getArmor() + ", found: " + artifact)) {
                    game.getHero().equipArmor((Armor) artifact);
                    view.showPopup("You are now equipped with " + artifact + "as your [Armor]!");
                }
            }
        }
    }

    public void onFight() {
        Villain fireBender = game.createVillain();
        int playerXP = game.fightResult(fireBender);

        if (playerXP > 0) {
            view.showPopup("[VICTORY!] you received " + playerXP + " XP!");
            levelUpText(playerXP);

            //fixes bug of recurring villain on map after defeat.
            game.getMap()[game.getHeroPosition().getY()][game.getHeroPosition().getX()] = false;

            setArtifact(fireBender.getArtifact());
        } else {
            view.showPopup("*** [DEFEATED!] ***");
            view.endGame();
        }
    }

    private void levelUpText(int xpValue) {
        int level = game.getHero().getLevel();
        game.getHero().xpScore(xpValue);
        if (level != game.getHero().getLevel()){
            view.showPopup("*** [LEVEL UP!] ***\n" +
                    "-- Your HP, defense and attack points increased! --");
        }
    }

    public void onSwitchModeCommand() {
        view.switchGameMode();
    }
}
