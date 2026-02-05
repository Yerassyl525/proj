public class Trainer extends Person {

    private String specialty;
    private int yearsOfExperience;

    public Trainer(int id, String name, int age, String gender,
                   String specialty, int yearsOfExperience)
            throws InvalidInputException {

        super(id, name, age, gender);
        setSpecialty(specialty);
        setYearsOfExperience(yearsOfExperience);
    }


    public String getSpecialty() {
        return specialty;
    }
    public int getYearsOfExperience() {
        return yearsOfExperience;
    }
    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }
    public void setYearsOfExperience(int yearsOfExperience)
            throws InvalidInputException {
        if (yearsOfExperience < 0)
            throw new InvalidInputException("Experience cannot be negative.");
        this.yearsOfExperience = yearsOfExperience;
    }
    @Override
    public void performDuty() {
        System.out.println("Trainer " + name + " is coaching a client.");
    }
    @Override
    public String getDescription() {
        return "Professional Trainer specialized in " + specialty;
    }
    @Override
    public void displayInfo() {
        System.out.println(
                "[Trainer] " + toString() +
                        " | Specialty: " + specialty +
                        " | Experience: " + yearsOfExperience + " years"
        );
    }
}
