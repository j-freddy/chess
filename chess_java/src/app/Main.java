package app;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Main extends Application {

  private final int boardWidth = 600;
  private final int boardHeight = 600;

  @Override
  public void start(Stage primaryStage) throws Exception {
    Pane root = new Pane();
    Scene scene = new Scene(root, boardWidth, boardHeight);
    primaryStage.setTitle("Hello World");
    primaryStage.setScene(scene);
    primaryStage.show();
  }


  public static void main(String[] args) {
    launch(args);
  }
}
