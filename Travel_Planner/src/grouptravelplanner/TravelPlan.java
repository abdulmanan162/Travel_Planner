package grouptravelplanner;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TravelPlan implements Serializable {

    private String destination;
    private LocalDate startDate;
    private LocalDate endDate;
    private double baseCost;
    private TransportOption transport;

    // simple list for participants
    private List<Participant> participants = new ArrayList<>();

    public TravelPlan(String destination, LocalDate startDate, LocalDate endDate,
                      double baseCost, TransportOption transport) {
        this.destination = destination;
        this.startDate = startDate;
        this.endDate = endDate;
        this.baseCost = baseCost;
        this.transport = transport;
    }

    public String getDestination() {
        return destination;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public double getBaseCost() {
        return baseCost;
    }

    public TransportOption getTransport() {
        return transport;
    }

    public List<Participant> getParticipants() {
        return participants;
    }

    public void addParticipant(Participant p) {
        participants.add(p);
    }

    public int getParticipantCount() {
        return participants.size();
    }

    public double calculateTotalCost() {
        return baseCost + transport.getPricePerPerson() * participants.size();
    }

    @Override
    public String toString() {
        return destination;
    }
}