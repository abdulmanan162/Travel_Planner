package grouptravelplanner;

import javafx.application.Application;
import javafx.stage.Stage;

public class MainApp extends Application {

    @Override
    public void start(Stage stage) {
        TravelPlannerUI ui = new TravelPlannerUI();
        ui.start(stage);
    }

    public static void main(String[] args) {
        launch(args);
    }
}