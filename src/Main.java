import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        ArrayList<Member> members = new ArrayList<>();
        ArrayList<Trainer> trainers = new ArrayList<>();

        while (true) {

            System.out.println("\n=== GYM MANAGEMENT SYSTEM ===");
            System.out.println("1. Add Member");
            System.out.println("2. View Members");
            System.out.println("3. Add Trainer");
            System.out.println("4. View Trainers");
            System.out.println("0. Exit");
            System.out.print("Choose option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {

                case 1:
                    try {
                        System.out.print("Enter member ID: ");
                        int id = scanner.nextInt();
                        scanner.nextLine();

                        System.out.print("Enter member name: ");
                        String name = scanner.nextLine();

                        System.out.print("Enter membership type: ");
                        String membership = scanner.nextLine();

                        Member member = new Member(id, name, membership);
                        members.add(member);

                        System.out.println("Member added.");
                    } catch (InvalidDataException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;

                case 2:
                    if (members.isEmpty()) {
                        System.out.println("No members found.");
                    } else {
                        for (Member m : members) {
                            m.displayInfo();
                        }
                    }
                    break;

                case 3:
                    try {
                        System.out.print("Enter trainer ID: ");
                        int id = scanner.nextInt();
                        scanner.nextLine();

                        System.out.print("Enter trainer name: ");
                        String name = scanner.nextLine();

                        System.out.print("Enter experience years: ");
                        int exp = scanner.nextInt();
                        scanner.nextLine();

                        Trainer trainer = new Trainer(id, name, exp);
                        trainers.add(trainer);

                        System.out.println("Trainer added.");
                    } catch (InvalidDataException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;

                case 4:
                    if (trainers.isEmpty()) {
                        System.out.println("No trainers found.");
                    } else {
                        for (Trainer t : trainers) {
                            t.displayInfo();
                        }
                    }
                    break;

                case 0:
                    System.out.println("Program finished.");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid option.");
            }
        }
    }
}
