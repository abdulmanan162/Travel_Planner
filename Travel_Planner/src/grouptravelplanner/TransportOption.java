package grouptravelplanner;

import java.io.Serializable;

public class TransportOption implements Serializable {

    private String type;
    private double pricePerPerson;

    public TransportOption(String type, double pricePerPerson) {
        this.type = type;
        this.pricePerPerson = pricePerPerson;
    }

    public String getType() {
        return type;
    }

    public double getPricePerPerson() {
        return pricePerPerson;
    }

    @Override
    public String toString() {
        return type + " (€" + pricePerPerson + ")";
    }
}