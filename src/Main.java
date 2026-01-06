import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ArrayList<Vehicle> vehicles = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        int choice;

        do {
            System.out.println("\n=== VEHICLE SYSTEM ===");
            System.out.println("1. Add Car");
            System.out.println("2. Add Bike");
            System.out.println("3. View All Vehicles");
            System.out.println("0. Exit");
            System.out.print("Enter choice: ");

            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter car name: ");
                    String carName = scanner.nextLine();
                    vehicles.add(new Car(carName));
                    System.out.println("Car added!");
                    break;

                case 2:
                    System.out.print("Enter bike name: ");
                    String bikeName = scanner.nextLine();
                    vehicles.add(new Bike(bikeName));
                    System.out.println("Bike added!");
                    break;

                case 3:
                    System.out.println("\nAll Vehicles:");
                    for (Vehicle v : vehicles) {
                        System.out.print("- " + v.getName() + " | ");
                        v.move();
                    }
                    break;

                case 0:
                    System.out.println("Exiting...");
                    break;

                default:
                    System.out.println("Invalid choice! Try again.");
            }

        } while (choice != 0);

        scanner.close();
    }
}
