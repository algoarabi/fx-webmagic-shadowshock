package com.leong;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * @author phoenix
 * @since 2016/12/8
 */
public class FxHelloWorld extends Application {
    private int counter = 0;

    public static void main(String[] args) {
        launch();
    }

    public void start(Stage stage) throws Exception {
        stage.setTitle("Hello World");

        GridPane gridPane = new GridPane();
        gridPane.setHgap(0);
        gridPane.setVgap(0);

        Rectangle r1 = new Rectangle();
        r1.setFill(Color.DARKKHAKI);
        r1.setHeight(125);
        r1.setWidth(125);

        Rectangle r2 = new Rectangle();
        r2.setFill(Color.rgb(189, 40, 40));
        r2.setHeight(125);
        r2.setWidth(125);

        Rectangle r3 = new Rectangle();
        r3.setFill(Color.hsb(235, 0.52, 0.36));
        r3.setHeight(125);
        r3.setWidth(125);

        Rectangle r4 = new Rectangle();
        r4.setFill(Color.web("b894cc"));
        r4.setHeight(125);
        r4.setWidth(125);

        gridPane.add(r1, 0, 0);
        gridPane.add(r2, 0, 1);
        gridPane.add(r3, 1, 0);
        gridPane.add(r4, 1, 1);

        final Label label = new Label();
        label.setFont(new Font("Calibri", 15));
        label.setTextFill(Color.BLACK);

        Button button = new Button();
        button.setFont(new Font("Calibri", 15));
        button.setText("Say 'Hello World'");

        button.setOnAction(event -> {
            System.out.println("button click:" + counter);
            label.setText("You said Hello to the world:" + (++counter) + ((counter == 1) ? " time" : " times"));
        });

        BorderPane bp = new BorderPane();
        bp.setBottom(label);
        bp.setAlignment(label, Pos.CENTER);
        bp.setCenter(button);

        StackPane root = new StackPane();
        root.getChildren().add(gridPane);
        root.getChildren().add(bp);
        stage.setScene(new Scene(root, 250,250));
        stage.show();

    }

}
