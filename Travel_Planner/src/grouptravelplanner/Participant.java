package grouptravelplanner;

public class Participant extends Person {

    private String phoneNumber;

    public Participant(String name, String email, String phoneNumber) {
        super(name, email);
        this.phoneNumber = phoneNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    @Override
    public String toString() {
        return getName() + " (" + phoneNumber + ")";
    }
}