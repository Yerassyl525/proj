public class Trainer extends Person implements Manageable {

    private int experienceYears;

    public Trainer(int id, String name, int experienceYears) {
        super(id, name);
        setExperienceYears(experienceYears);
    }

    public void setExperienceYears(int experienceYears) {
        if (experienceYears < 0) {
            throw new InvalidDataException("Experience cannot be negative");
        }
        this.experienceYears = experienceYears;
    }

    public boolean isExperienced() {
        return experienceYears > 3;
    }

    @Override
    public String getRole() {
        return "Trainer";
    }

    @Override
    public void displayInfo() {
        System.out.println("Trainer: " + name + ", Experience: " + experienceYears);
    }
}
