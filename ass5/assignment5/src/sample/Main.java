package sample;
//package com.bham.pij.assignments.edgedetector;

import com.sun.javafx.geom.Edge;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class Main extends Application {

    private ImageView imgView;
    private File file;

    @Override
    public void start(Stage stage) throws Exception {
        mainStage(stage);
        stage.show();
    }

    private void mainStage(Stage stage) {
        stage.setTitle("Image filters");
        VBox root = new VBox();
        //
        //
        Menu filesMenu = new Menu("File");
        MenuItem openItem = new MenuItem("Open");
        MenuItem closeItem = new MenuItem("Close");
        Menu toolsMenu = new Menu("Tools");
        MenuItem filterItem = new MenuItem("Edge Detection");
        MenuItem revertItem = new MenuItem("Revert");
        filesMenu.getItems().addAll(openItem, closeItem);
        toolsMenu.getItems().addAll(filterItem, revertItem);
        MenuBar menuBar = new MenuBar();
        menuBar.getMenus().add(filesMenu);
        menuBar.getMenus().add(toolsMenu);
        root.getChildren().add(menuBar);
        //
        //
        revertItem.setOnAction(actionEvent -> {
            showImage(root, img);
        });
        //
        //
        stage.setScene(new Scene(root, 500, 500));
        //
        //
        closeItem.setOnAction(actionEvent -> {
            removeImage(root);
        });
        //
        //
        filterItem.setOnAction(actionEvent -> {
            EdgeDetector det = new EdgeDetector();
            filteredImage = det.filterImage(img);
            showImage(root, filteredImage);
        });
        //
        //
        openItem.setOnAction(actionEvent -> {
            FileChooser fileOpener = new FileChooser();
            fileOpener.setTitle("Open a file");
            file = fileOpener.showOpenDialog(stage);
            if (file != null) {
                removeImage(root);
                loadFileImage(root);
            }
        });
    }

    private Image img;
    private Image filteredImage;

    private void loadFileImage(VBox root) {
        img = new Image("file:" + file.getPath());
        imgView = new ImageView(img);
        root.getChildren().add(imgView);
    }

    private void showImage(VBox root, Image img) {
        removeImage(root);
        imgView = new ImageView(img);
        root.getChildren().add(imgView);
    }

    private void removeImage(VBox root) {
        root.getChildren().remove(imgView);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
