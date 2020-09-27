package jmichael.swingy.beans.avatar;

import jmichael.swingy.beans.Armor;
import jmichael.swingy.beans.Helmet;
import jmichael.swingy.beans.Weapon;

public class AvatarBean {
    private int id;
    private String name;
    private int attack;
    private int defense;
    private int hitPoints;
    private String heroClass;
    private int level;
    private int experience;
    private Weapon weapon;
    private Armor armor;
    private Helmet helmet;

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public void setHitPoints(int hitPoints) {
        this.hitPoints = hitPoints;
    }

    public void setHeroClass(String heroClass) {
        this.heroClass = heroClass;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    public void setArmor(Armor armor) {
        this.armor = armor;
    }

    public void setHelm(Helmet helmet) {
        this.helmet = helmet;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public Hero getHero() {
        return new Hero(name, attack, defense, hitPoints, id, heroClass, level, experience, weapon, armor, helmet);
    }
}
