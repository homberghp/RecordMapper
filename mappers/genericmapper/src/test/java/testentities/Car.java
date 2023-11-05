package testentities;

/**
 * Test array
 *
 * @author hom
 */
public class Car {

    final int id;
    final String[] doors;

    public Car(int id, String[] doors) {
        this.id = id;
        this.doors = doors;
    }

    public String[] getDoors() {
        return doors;
    }

}
