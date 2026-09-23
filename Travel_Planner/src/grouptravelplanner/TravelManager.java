package grouptravelplanner;

import java.util.List;

public class TravelManager {

    private List<TravelPlan> plans;

    public TravelManager() {
        plans = FileHandler.loadPlans();
    }

    public void addPlan(TravelPlan plan) {
        plans.add(0, plan);
    }

    public List<TravelPlan> getPlans() {
        return plans;
    }

    public void save() {
        FileHandler.savePlans(plans);
    }

    public TravelPlan getCheapestPlan() {
        if (plans.isEmpty()) return null;

        TravelPlan cheapest = plans.get(0);

        for (TravelPlan p : plans) {
            if (p.calculateTotalCost() < cheapest.calculateTotalCost()) {
                cheapest = p;
            }
        }

        return cheapest;
    }
}