package jmichael.swingy.beans.avatar;

import jmichael.swingy.beans.Armor;
import jmichael.swingy.beans.Helmet;
import jmichael.swingy.beans.Weapon;
import jmichael.swingy.handler.ValidationException;

import javax.validation.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Set;
import java.util.logging.Level;

public class Hero extends Avatar {

    private Weapon weapon;
    private Armor armor;
    private Helmet helmet;

    @Min(value = 1, message = "Level cannot be less than 1")
    private int level;

    @Min(value = 0, message = "Experience cannot be less than 0")
    private int experience;

    @NotNull(message = "Hero class cannot be null")
    private String heroClass;

    private int id;

    public Hero(String name, int attack, int defense, int hitPoints, int id, String heroClass,
                int level, int experience, Weapon weapon, Armor armor, Helmet helmet) {
        super(name, attack, defense, hitPoints);
        this.id = id;
        this.weapon = weapon;
        this.armor = armor;
        this.helmet = helmet;
        this.level = level;
        this.experience = experience;
        this.heroClass = heroClass;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void validateHero() throws ValidationException {
        java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.OFF);
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        Set<ConstraintViolation<Hero>> constraintViolations = validator.validate(this);
        if (constraintViolations.size() != 0) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Hero validation error(s): ");
            stringBuilder.append(constraintViolations.size());
            stringBuilder.append("\n");
            for (ConstraintViolation<Hero> cv : constraintViolations) {
                stringBuilder.append("property: [");
                stringBuilder.append(cv.getPropertyPath());
                stringBuilder.append("], value: [");
                stringBuilder.append(cv.getInvalidValue());
                stringBuilder.append("], message: [");
                stringBuilder.append(cv.getMessage());
                stringBuilder.append("]\n");
            }
            throw new ValidationException(stringBuilder.toString());
        }
    }

    public void equipWeapon(Weapon weapon) {
        if (this.weapon != null) {
            this.attack -= this.weapon.getPoints();
        }
        this.attack += weapon.getPoints();
        this.weapon = weapon;
    }

    public void equipHelm(Helmet helmet) {
        if (this.helmet != null) {
            this.hitPoints -= this.helmet.getPoints();
            if (this.hitPoints + helmet.getPoints() <= 0) {
                this.hitPoints += this.helmet.getPoints();
                return;
            }
        }
        this.hitPoints += helmet.getPoints();
        this.helmet = helmet;
    }

    public void equipArmor(Armor armor) {
        if (this.armor != null) {
            this.defense -= this.armor.getPoints();
        }
        this.defense += armor.getPoints();
        this.armor = armor;
    }

    public void addExperience(int addXP) {
        int nextLevel = (level + 1) * 1000 + level * level * 450;

        if (experience + addXP >= nextLevel)
            levelUp();
        experience += addXP;
    }

    private void levelUp() {
        level++;
        hitPoints += 50 + level * 10;
        attack += level * 3;
        defense += level * 2;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    public Armor getArmor() {
        return armor;
    }

    public void setArmor(Armor armor) {
        this.armor = armor;
    }

    public Helmet getHelmet() {
        return helmet;
    }

    public void setHelm(Helmet helmet) {
        this.helmet = helmet;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public String getHeroClass() {
        return heroClass;
    }

    public void setHeroClass(String heroClass) {
        this.heroClass = heroClass;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("       [Name]    :: ").append(name).append("\n");
        sb.append("       [Class]   :: ").append(heroClass).append("\n");
        sb.append("       [Level]   :: ").append(level).append("\n");
        sb.append("       [XP]      :: ").append(experience).append("\n");
        sb.append("       [Attack]  :: ").append(attack).append("\n");
        sb.append("       [Defense] :: ").append(defense).append("\n");
        sb.append("       [HP]      :: ").append(hitPoints).append("\n");

        sb.append("       [Weapon]  :: ");
        if (weapon != null)
            sb.append(weapon.getName()).append(" (attack +").append(weapon.getPoints()).append(")\n");
        else
            sb.append(" no weapon\n");

        sb.append("       [Helm]    :: ");
        if (helmet != null)
            sb.append(helmet.getName()).append(" (hp +").append(helmet.getPoints()).append(")\n");
        else
            sb.append(" no helmet\n");

        sb.append("       [Armor]   :: ");
        if (armor != null)
            sb.append(armor.getName()).append(" (defense +").append(armor.getPoints()).append(")\n");
        else
            sb.append(" no armor\n");
        return sb.toString();
    }
}
