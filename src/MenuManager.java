import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class MenuManager implements Menu {

    private final Scanner sc = new Scanner(System.in);
    private final PersonDAO dao = new PersonDAO();

    @Override
    public void start() {
        while (true) {
            printMenu();
            int c = readInt("Choose: ");

            try {
                switch (c) {
                    case 1 -> insert();
                    case 2 -> viewAll();
                    case 3 -> update();
                    case 4 -> delete();
                    case 5 -> searchByName();
                    case 6 -> searchByAge();
                    case 7 -> demoPolymorphism();
                    case 8 -> { return; }
                    default -> System.out.println("Wrong option.");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    private void printMenu() {
        System.out.println("""
            \n--- GYM SYSTEM ---
            1. Insert person
            2. View all
            3. Update by id
            4. Delete by id
            5. Search by name
            6. Search by age
            7. Polymorphism demo
            8. Exit
            """);
    }
    private void insert() throws SQLException, InvalidInputException {
        int id = readInt("ID: ");
        String role = read("Role (MEMBER/TRAINER): ").toUpperCase();
        String name = read("Name: ");
        int age = readInt("Age: ");
        String gender = read("Gender: ");
        if (role.equals("MEMBER")) {
            dao.insert(new Member(
                    id, name, age, gender,
                    read("Membership: "),
                    readDouble("Fee: ")
            ));
        } else {
            dao.insert(new Trainer(
                    id, name, age, gender,
                    read("Specialty: "),
                    readInt("Experience: ")
            ));
        }
    }
    private void viewAll() throws SQLException {
        for (Person p : dao.getAll()) p.displayInfo();
    }
    private void update() throws SQLException, InvalidInputException {
        int id = readInt("ID: ");
        Person p = dao.getById(id);
        if (p == null) return;
        String name = read("New name: ");
        int age = readInt("New age: ");
        String gender = read("New gender: ");
        if (p instanceof Member) {
            dao.update(new Member(
                    id, name, age, gender,
                    read("Membership: "),
                    readDouble("Fee: ")
            ));
        } else {
            dao.update(new Trainer(
                    id, name, age, gender,
                    read("Specialty: "),
                    readInt("Experience: ")
            ));
        }
    }
    private void delete() throws SQLException {
        dao.delete(readInt("ID: "));
    }
    private void searchByName() throws SQLException {
        for (Person p : dao.searchByName(read("Name part: ")))
            p.displayInfo();
    }
    private void searchByAge() throws SQLException {
        for (Person p : dao.searchByAge(readInt("Age: ")))
            p.displayInfo();
    }
    private void demoPolymorphism() throws SQLException {
        for (Person p : dao.getAll()) p.performDuty();
    }
    private int readInt(String m) {
        System.out.print(m);
        return Integer.parseInt(sc.nextLine());
    }
    private double readDouble(String m) {
        System.out.print(m);
        return Double.parseDouble(sc.nextLine());
    }
    private String read(String m) {
        System.out.print(m);
        return sc.nextLine();
    }
}
