public class Member extends Person implements Manageable {

    private String membershipType;

    public Member(int id, String name, String membershipType) {
        super(id, name);
        setMembershipType(membershipType);
    }

    public void setMembershipType(String membershipType) {
        if (membershipType == null || membershipType.trim().isEmpty()) {
            throw new InvalidDataException("Membership type is required");
        }
        this.membershipType = membershipType;
    }

    public boolean isActive() {
        return !membershipType.equalsIgnoreCase("None");
    }

    @Override
    public String getRole() {
        return "Member";
    }

    @Override
    public void displayInfo() {
        System.out.println("Member: " + name + ", Membership: " + membershipType);
    }
}
