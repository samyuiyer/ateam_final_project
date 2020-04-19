package application;

import java.util.List;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class DisplayManager extends Application {

  private DisplayMode[] displayModes;
  private SettingsPane globalSettings;

  private List<String> args;

  private static final int WINDOW_WIDTH = 800;
  private static final int WINDOW_HEIGHT = 600;
  private static final String APP_TITLE = "hey its corona";
  
  Boolean visible = true;

  @Override
  public void start(Stage primaryStage) throws Exception {

    args = this.getParameters().getRaw();

    VBox leftPanel = new VBox();
    leftPanel.setStyle("-fx-background-color: grey;");

    String[] dispModes = {"Table Mode", "Map Mode", "Graph Mode"};

    ComboBox<String> dpMode = new ComboBox<String>(FXCollections.observableArrayList(dispModes));
    dpMode.setPromptText("Select Display Mode");
    leftPanel.getChildren().add(dpMode);

    // Slider
    Label sliderLabel = new Label("Choose Time Range:");
    Slider sliderStart = new Slider(0, 100, 10);
    Slider sliderEnd = new Slider(0, 100, 90);
    
    sliderStart.setShowTickLabels(true);
    sliderStart.setShowTickMarks(true);
    sliderStart.setBlockIncrement(10);
    sliderStart.setSnapToTicks(true);
    sliderEnd.setShowTickLabels(true);
    sliderEnd.setShowTickMarks(true);
    sliderEnd.setBlockIncrement(10);
    sliderEnd.setSnapToTicks(true);
    
    sliderStart.maxProperty().bind(sliderEnd.valueProperty());
    sliderEnd.minProperty().bind(sliderStart.valueProperty());

    sliderLabel.managedProperty().bind(sliderEnd.visibleProperty());
    sliderStart.managedProperty().bind(sliderStart.visibleProperty());
    sliderEnd.managedProperty().bind(sliderEnd.visibleProperty());

    Button time = new Button("Time Range");
    time.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        if (visible) {
          sliderLabel.setVisible(false);
          sliderStart.setVisible(false);
          sliderEnd.setVisible(false);
          visible = false;
        } else {
          sliderLabel.setVisible(true);
          sliderStart.setVisible(true);
          sliderEnd.setVisible(true);
          visible = true;
        }
      }
    });

    leftPanel.getChildren().addAll(time, sliderLabel, sliderStart, sliderEnd);

    Button loc = new Button("Locations");
    leftPanel.getChildren().add(loc);

    ColorPicker cp = new ColorPicker();
    leftPanel.getChildren().add(cp);

    // Main layout is Border Pane (top,left,center,right,bottom)
    BorderPane root = new BorderPane();
    // add to pane
    root.setLeft(leftPanel);

    // Set Scene
    Scene mainScene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);

    // Add the stuff and set the primary stage
    primaryStage.setTitle(APP_TITLE);
    primaryStage.setScene(mainScene);
    primaryStage.show();
  }

  public void displaySetting(SettingsPane setting) {

  }

  public void displayPane(DisplayMode display) {

  }

  public static void main(String[] args) {
    launch(args);
  }

}