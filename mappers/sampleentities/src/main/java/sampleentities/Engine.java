package sampleentities;

import nl.fontys.sebivenlo.sebiannotations.ID;

/**
 *
 * @author "Pieter van den Hombergh {@code pieter.van.den.hombergh@gmail.com}"
 */
public class Engine {

    @ID
    final String type;
    final int cilinders;
    final double hp;

    public Engine( String type, int cilinders, double hp ) {
        this.type = type;
        this.cilinders = cilinders;
        this.hp = hp;
    }

    public String getType() {
        return type;
    }

    public int getCilinders() {
        return cilinders;
    }

    public double getHp() {
        return hp;
    }

    @Override
    public String toString() {
        return "Engine{" + "type=" + type + ", cilinders=" + cilinders + ", hp="
                + hp + '}';
    }

}
