public class Vehicle {

    protected String name;

    public Vehicle(String name) {
        setName(name);
    }

    public void setName(String name) {
        if (name == null || name.isEmpty()) {
            this.name = "No name";
        } else {
            this.name = name;
        }
    }

    public String getName() {
        return name;
    }

    public void move() {
        System.out.println("Vehicle moves");
    }
}
