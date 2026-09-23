package grouptravelplanner;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class TravelPlannerUI {

    private TravelManager manager = new TravelManager();
    private ObservableList<TravelPlan> planList = FXCollections.observableArrayList();

    public void start(Stage stage) {

        ListView<TravelPlan> listView = new ListView<>(planList);

        TextField destinationField = new TextField();
        destinationField.setPromptText("Destination");

        DatePicker startDatePicker = new DatePicker();
        DatePicker endDatePicker = new DatePicker();

        ComboBox<String> transportBox = new ComboBox<>();
        transportBox.getItems().addAll("Bus", "Train", "Flight");

        TextField transportPriceField = new TextField();
        transportPriceField.setPromptText("Transport price");

        TextField baseCostField = new TextField();
        baseCostField.setPromptText("Base cost");

        TextField nameField = new TextField();
        nameField.setPromptText("Participant name");

        TextField emailField = new TextField();
        emailField.setPromptText("Email");

        TextField phoneField = new TextField();
        phoneField.setPromptText("Phone");

        TextArea detailsArea = new TextArea();
        detailsArea.setEditable(false);

        Button addPlanBtn = new Button("Add Plan");
        Button addParticipantBtn = new Button("Add Participant");
        Button saveBtn = new Button("Save");
        Button loadBtn = new Button("Load");
        Button compareBtn = new Button("Cheapest Plan");
        Button deleteBtn = new Button("Delete Plan");

        // Plan
        addPlanBtn.setOnAction(e -> {
            try {
                TransportOption t = new TransportOption(
                        transportBox.getValue(),
                        Double.parseDouble(transportPriceField.getText())
                );

                TravelPlan plan = new TravelPlan(
                        destinationField.getText(),
                        startDatePicker.getValue(),
                        endDatePicker.getValue(),
                        Double.parseDouble(baseCostField.getText()),
                        t
                );

                manager.addPlan(plan);
                planList.setAll(manager.getPlans());
                showDetails(plan, detailsArea);

            } catch (Exception ex) {
                detailsArea.setText("Please fill all fields correctly.");
            }
        });

        // Participant
        addParticipantBtn.setOnAction(e -> {
            TravelPlan selected = listView.getSelectionModel().getSelectedItem();

            if (selected == null) {
                detailsArea.setText("Please select a plan first.");
                return;
            }

            Participant p = new Participant(
                    nameField.getText(),
                    emailField.getText(),
                    phoneField.getText()
            );

            selected.addParticipant(p);

            listView.refresh();
            showDetails(selected, detailsArea);
        });

        // Save
        saveBtn.setOnAction(e -> {
            manager.save();
            detailsArea.setText("Plans saved successfully.");
        });

        // Delete
        deleteBtn.setOnAction(e -> {
            TravelPlan selected = listView.getSelectionModel().getSelectedItem();

            if (selected != null) {
                manager.getPlans().remove(selected);
                planList.setAll(manager.getPlans());
                detailsArea.clear();
            }
        });

        // Load
        loadBtn.setOnAction(e -> {
            manager = new TravelManager();
            planList.setAll(manager.getPlans());
            detailsArea.setText("Plans loaded successfully.");
        });

        // Compare
        compareBtn.setOnAction(e -> {
            TravelPlan cheapest = manager.getCheapestPlan();

            if (cheapest != null) {
                showDetails(cheapest, detailsArea);
            }
        });

        // Select
        listView.getSelectionModel().selectedItemProperty().addListener(
                (obs, oldVal, newVal) -> {
                    if (newVal != null) {
                        showDetails(newVal, detailsArea);
                    }
                });

        Label title1 = new Label("Trip Details");
        title1.getStyleClass().add("title-label");

        Label title2 = new Label("Participants");
        title2.getStyleClass().add("title-label");

        // Layout
        VBox left = new VBox(10, new Label("Travel Plans"), listView);

        VBox right = new VBox(10,
                title1,
                destinationField,
                startDatePicker,
                endDatePicker,
                transportBox,
                transportPriceField,
                baseCostField,
                addPlanBtn,
                deleteBtn,
                title2,
                nameField,
                emailField,
                phoneField,
                addParticipantBtn,
                saveBtn,
                loadBtn,
                compareBtn
        );

        left.setPadding(new Insets(10));
        right.setPadding(new Insets(10));

        BorderPane root = new BorderPane();
        root.setLeft(left);
        root.setCenter(right);
        root.setBottom(detailsArea);

        Scene scene = new Scene(root, 900, 600);
        scene.getStylesheets().add(getClass().getResource("/style.css").toExternalForm());

        stage.setTitle("Group Travel Planner");
        stage.setScene(scene);
        stage.show();

        planList.setAll(manager.getPlans());
    }

    private void showDetails(TravelPlan plan, TextArea area) {

        StringBuilder sb = new StringBuilder();

        sb.append("Destination: ").append(plan.getDestination()).append("\n");
        sb.append("Participants: ").append(plan.getParticipantCount()).append("\n");
        sb.append("Total Cost: €").append(plan.calculateTotalCost()).append("\n\n");

        for (Participant p : plan.getParticipants()) {
            sb.append("- ").append(p.getName()).append("\n");
        }

        area.setText(sb.toString());
    }
}