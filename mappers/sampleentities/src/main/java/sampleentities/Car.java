package sampleentities;

import java.util.List;
import nl.fontys.sebivenlo.sebiannotations.ID;

/**
 *
 * @author "Pieter van den Hombergh {@code p.vandenhombergh@fontys.nl}"
 */
public class Car {
    @ID
    final String brand;
    final String color;
    final Engine engine;
    final Door[] doors;

    public Car( String brand, String color, Engine engine ) {
        this(brand, color,engine, new Door[]{ new Door("leftDoor", "red"),new Door("rightDoor","white")});
    }

    public Car( String brand, String color, Engine engine, Door[] doors ) {
        this.brand = brand;
        this.color = color;
        this.engine = engine;
        this.doors = doors;
    }

    
    public String getBrand() {
        return brand;
    }

    public String getColor() {
        return color;
    }

    public Engine getEngine() {
        return engine;
    }

    public Door[] getDoors() {
        return doors;
    }

    
    @Override
    public String toString() {
        return "Car{" + "brand=" + brand + ", color=" + color + ", engine=" + engine + '}';
    }
 
}
