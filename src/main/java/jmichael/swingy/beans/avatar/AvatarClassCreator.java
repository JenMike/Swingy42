package jmichael.swingy.beans.avatar;


public class AvatarClassCreator {

    private static AvatarBean buildNew(String name) {
        AvatarBean builder = new AvatarBean();
        builder.setName(name);
        builder.setLevel(1);
        builder.setExperience(0);
        return builder;
    }

    public static Hero createMonk(String name) {
        AvatarBean builder = buildNew(name);
        builder.setAttack(40);
        builder.setDefense(20);
        builder.setHitPoints(100);
        builder.setHeroClass("Monk");
        return builder.getHero();
    }

    public static Hero createCleric(String name) {
        AvatarBean builder = buildNew(name);
        builder.setAttack(30);
        builder.setDefense(15);
        builder.setHitPoints(90);
        builder.setHeroClass("Cleric");
        return builder.getHero();
    }

    public static Hero createWizard(String name) {
        AvatarBean builder = buildNew(name);
        builder.setAttack(25);
        builder.setDefense(25);
        builder.setHitPoints(90);
        builder.setHeroClass("Wizard");
        return builder.getHero();
    }

    public static Hero createPaladin(String name) {
        AvatarBean builder = buildNew(name);
        builder.setAttack(40);
        builder.setDefense(30);
        builder.setHitPoints(120);
        builder.setHeroClass("Paladin");
        return builder.getHero();
    }

    public static Hero createDruid(String name) {
        AvatarBean builder = buildNew(name);
        builder.setAttack(45);
        builder.setDefense(10);
        builder.setHitPoints(80);
        builder.setHeroClass("Druid");
        return builder.getHero();
    }

    public static Hero createFighter(String name) {
        AvatarBean builder = buildNew(name);
        builder.setAttack(25);
        builder.setDefense(20);
        builder.setHitPoints(110);
        builder.setHeroClass("Fighter");
        return builder.getHero();
    }
}
