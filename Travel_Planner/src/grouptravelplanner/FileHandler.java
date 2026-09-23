package grouptravelplanner;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileHandler {

    private static final String FILE_NAME = "travelplans.dat";

    // SAVE DATA
    public static void savePlans(List<TravelPlan> plans) {
        try {
            ObjectOutputStream out = new ObjectOutputStream(
                    new FileOutputStream(FILE_NAME)
            );
            out.writeObject(plans);
            out.close();

        } catch (Exception e) {
            System.out.println("Error Savedata");
        }
    }

    // LOAD DATA
    @SuppressWarnings("unchecked")
    public static List<TravelPlan> loadPlans() {
        try {
            ObjectInputStream in = new ObjectInputStream(
                    new FileInputStream(FILE_NAME)
            );

            List<TravelPlan> plans = (List<TravelPlan>) in.readObject();
            in.close();

            return plans;

        } catch (Exception e) {
            return new ArrayList<>();
        }
    }
}