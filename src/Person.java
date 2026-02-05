public abstract class Person implements GymAction {
    protected int id;
    protected String name;
    protected int age;
    protected String gender;
    public Person(int id, String name, int age, String gender) throws InvalidInputException {
        setId(id);
        setName(name);
        setAge(age);
        setGender(gender);
    }
    public int getId() { return id; }
    public void setId(int id) throws InvalidInputException {
        if (id <= 0) throw new InvalidInputException("ID must be positive.");
        this.id = id;
    }
    public String getName() { return name; }
    public void setName(String name) throws InvalidInputException {
        if (name == null || name.trim().isEmpty()) throw new InvalidInputException("Name cannot be empty.");
        this.name = name;
    }
    public int getAge() { return age; }
    public void setAge(int age) throws InvalidInputException {
        if (age < 14 || age > 100) throw new InvalidInputException("Age must be between 14 and 100.");
        this.age = age;
    }
    public String getGender() { return gender; }
    public void setGender(String gender) {
        this.gender = gender;
    }
    public abstract void displayInfo();
    @Override
    public String toString() {
        return "ID: " + id + " | Name: " + name + " | Age: " + age;
    }
}