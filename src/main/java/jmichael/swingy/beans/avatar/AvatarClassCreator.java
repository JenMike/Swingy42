package jmichael.swingy.beans.avatar;


public class AvatarClassCreator {

    private static AvatarBean generateNew(String name) {
        AvatarBean generator = new AvatarBean();
        generator.setName(name);
        generator.setLevel(0);
        generator.setExperience(0);
        return generator;
    }

    public static Hero createMonk(String name) {
        AvatarBean builder = generateNew(name);
        builder.setAttack(50);
        builder.setDefense(80);
        builder.setHitPoints(100);
        builder.setHeroClass("Monk");
        return builder.getHero();
    }

    public static Hero createCleric(String name) {
        AvatarBean builder = generateNew(name);
        builder.setAttack(40);
        builder.setDefense(35);
        builder.setHitPoints(95);
        builder.setHeroClass("Cleric");
        return builder.getHero();
    }

    public static Hero createWizard(String name) {
        AvatarBean builder = generateNew(name);
        builder.setAttack(45);
        builder.setDefense(15);
        builder.setHitPoints(90);
        builder.setHeroClass("Wizard");
        return builder.getHero();
    }

    public static Hero createPaladin(String name) {
        AvatarBean builder = generateNew(name);
        builder.setAttack(35);
        builder.setDefense(45);
        builder.setHitPoints(110);
        builder.setHeroClass("Paladin");
        return builder.getHero();
    }

    public static Hero createDruid(String name) {
        AvatarBean builder = generateNew(name);
        builder.setAttack(35);
        builder.setDefense(45);
        builder.setHitPoints(120);
        builder.setHeroClass("Druid");
        return builder.getHero();
    }

    public static Hero createFighter(String name) {
        AvatarBean builder = generateNew(name);
        builder.setAttack(95);
        builder.setDefense(20);
        builder.setHitPoints(75);
        builder.setHeroClass("Fighter");
        return builder.getHero();
    }
}
