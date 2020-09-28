package jmichael.swingy.controller;

import jmichael.swingy.beans.Game;
import jmichael.swingy.beans.avatar.Hero;
import jmichael.swingy.beans.avatar.AvatarGenerator;
import jmichael.swingy.handler.ValidationException;
import jmichael.swingy.view.interfaces.CreateAvatarView;
import jmichael.swingy.sqlite.SqliteDB;

public class CreateAvatarController {

    private CreateAvatarView view;
    private Game game;

    public CreateAvatarController(CreateAvatarView view) {
        this.view = view;
        game = Game.getInstance();
    }

    public void onConfirmAvatar(String name, String heroClass) {
        Hero hero;
        try {
            hero = AvatarGenerator.newHero(name, heroClass);
            hero.validateHero();
        } catch (IllegalArgumentException | ValidationException e) {
            view.showErrorPopup(e.getMessage());
            view.getUserInput();
            return;
        }

        int id = SqliteDB.insert(hero.getName(), hero.getHeroClass(), hero.getLevel(), hero.getExperience(), hero.getAttack(), hero.getDefense(), hero.getHitPoints());
        hero.setId(id);
        game.initGame(hero);
        view.loadGame();
    }
}
