package sample;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception{
        mainStage(stage);
        stage.show();
    }

    private void mainStage(Stage stage){
        stage.setTitle("edge filters");
        VBox root = new VBox();
        Menu filesMenu = new Menu("File");
        MenuItem fileItem1 = new MenuItem("Open");
        MenuItem fileItem2 = new MenuItem("Close");
        Menu toolsMenu = new Menu("Tools");
        MenuItem toolsItem1 = new MenuItem("Edge Detection");
        MenuItem toolsItem2 = new MenuItem("Revert");
        filesMenu.getItems().addAll(fileItem1, fileItem2);
        toolsMenu.getItems().addAll(toolsItem1, toolsItem2);
        MenuBar menuBar = new MenuBar();
        menuBar.getMenus().add(filesMenu);
        menuBar.getMenus().add(toolsMenu);
        root.getChildren().add(menuBar);
        stage.setScene(new Scene(root, 500, 500));
    }


    public static void main(String[] args) {
        launch(args);
    }
}
