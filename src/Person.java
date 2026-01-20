public abstract class Person {
    protected int id;
    protected String name;

    public Person(int id, String name) {
        setId(id);
        setName(name);
    }

    public void setId(int id) {
        if (id <= 0) {
            throw new InvalidDataException("ID must be positive");
        }
        this.id = id;
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new InvalidDataException("Name cannot be empty");
        }
        this.name = name;
    }

    public abstract String getRole();
}
