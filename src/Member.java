public class Member extends Person {

    private String membershipType;
    private double monthlyFee;

    public Member(int id, String name, int age, String gender,
                  String membershipType, double monthlyFee)
            throws InvalidInputException {
        super(id, name, age, gender);
        setMembershipType(membershipType);
        setMonthlyFee(monthlyFee);
    }
    public String getMembershipType() {
        return membershipType;
    }
    public double getMonthlyFee() {
        return monthlyFee;
    }
    public void setMembershipType(String membershipType) {
        this.membershipType = membershipType;
    }
    public void setMonthlyFee(double monthlyFee) throws InvalidInputException {
        if (monthlyFee < 0)
            throw new InvalidInputException("Fee cannot be negative.");
        this.monthlyFee = monthlyFee;
    }
    @Override
    public void performDuty() {
        System.out.println(name + " is attending a workout session.");
    }
    @Override
    public String getDescription() {
        return "Gym Member with " + membershipType + " plan.";
    }
    @Override
    public void displayInfo() {
        System.out.println(
                "[Member] " + toString() +
                        " | Type: " + membershipType +
                        " | Fee: $" + monthlyFee
        );
    }
}
