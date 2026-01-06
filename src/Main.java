import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        ArrayList<Vehicle> vehicles = new ArrayList<>();

        vehicles.add(new Car("BMW"));
        vehicles.add(new Bike("BMX"));

        for (Vehicle v : vehicles) {
            v.move();

            if (v instanceof Car) {
                Car c = (Car) v;
                System.out.println("This is a car: " + c.getName());
            }
        }
    }
}
