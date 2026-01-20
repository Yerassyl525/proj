public class WorkoutSession {

    private Member member;
    private Trainer trainer;
    private int duration;

    public WorkoutSession(Member member, Trainer trainer, int duration) {
        if (duration <= 0) {
            throw new InvalidDataException("Duration must be positive");
        }
        this.member = member;
        this.trainer = trainer;
        this.duration = duration;
    }

    public void extend(int minutes) {
        if (minutes <= 0) {
            throw new InvalidDataException("Minutes must be positive");
        }
        duration += minutes;
    }

    public void complete() {
        System.out.println("Workout session completed. Duration: " + duration + " minutes");
    }
}
