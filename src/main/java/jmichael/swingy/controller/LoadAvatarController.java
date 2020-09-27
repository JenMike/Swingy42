package jmichael.swingy.controller;

import jmichael.swingy.beans.Game;
import jmichael.swingy.beans.avatar.Hero;
import jmichael.swingy.sqlite.SqliteDB;
import jmichael.swingy.handler.ValidationException;
import jmichael.swingy.view.interfaces.LoadAvatarView;
import jmichael.swingy.sqlite.*;

import java.util.ArrayList;

public class LoadAvatarController {

    private LoadAvatarView view;
    private Game game;

    public LoadAvatarController(LoadAvatarView view) {
        this.view = view;
        game = Game.getInstance();
    }

    public void onListElementSelected(int idx) {
        Hero hero = SqliteDB.selectHeroById(idx + 1);
        view.updatePaneText(hero.toString());
    }

    public String[] getListData() {
        ArrayList<String> list = SqliteDB.selectAll();
        String[] listArr = new String[list.size()];
        listArr = list.toArray(listArr);
        return listArr;
    }

    public void onSelectAvatar(int idx) {
        Hero hero;
        try {
            hero = SqliteDB.selectHeroById(idx + 1);
            hero.validateHero();
        } catch (ValidationException e) {
            view.showErrorPopup(e.getMessage());
            return;
        }

        game.initGame(hero);
        view.loadGame();
    }

    public void onCreateAvatar() {
        view.loadCreateAvatar();
    }
}
