package jmichael.swingy.beans.avatar;

import jmichael.swingy.beans.Artifact;

public class Villain extends Avatar {

    private final Artifact artifact;

    public Villain(String name, int attack, int defense, int hitPoints, Artifact artifact) {
        super(name, attack, defense, hitPoints);
        this.artifact = artifact;
    }

    public Artifact getArtifact() {
        return artifact;
    }
}
