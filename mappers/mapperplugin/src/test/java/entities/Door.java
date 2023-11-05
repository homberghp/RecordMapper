package entities;

import nl.fontys.sebivenlo.sebiannotations.ID;

/**
 *
 * @author Pieter van den Hombergh {@code pieter.van.den.hombergh@gmail.com}
 */
public class Door {

    @ID
    String doorName;
    String color;

    public Door( String doorName, String color ) {
        this.doorName = doorName;
        this.color = color;
    }

    public String getDoorName() {
        return doorName;
    }

    public String getColor() {
        return color;
    }

    @Override
    public String toString() {
        return "Door{" + "doorName=" + doorName + ", color=" + color + '}';
    }

}
