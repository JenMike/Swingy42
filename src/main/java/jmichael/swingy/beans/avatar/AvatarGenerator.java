package jmichael.swingy.beans.avatar;

import static jmichael.swingy.view.interfaces.GameView.RED;

public abstract class AvatarGenerator {

    public static Hero newHero(String name, String heroClass) {
        switch (heroClass.toUpperCase()) {
            case "MONK":
                return AvatarClassCreator.createMonk(name);
            case "CLERIC":
                return AvatarClassCreator.createCleric(name);
            case "WIZARD":
                return AvatarClassCreator.createWizard(name);
            case "PALADIN":
                return AvatarClassCreator.createPaladin(name);
            case "DRUID":
                return AvatarClassCreator.createDruid(name);
            case "FIGHTER":
                return AvatarClassCreator.createFighter(name);
            default:
                throw new IllegalArgumentException(RED+"[ERROR] :: you entered an invalid class name :: " + heroClass);
        }
    }
}
