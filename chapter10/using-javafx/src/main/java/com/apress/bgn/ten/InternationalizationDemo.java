/*
Freeware License, some rights reserved

Copyright (c) 2024 Iuliana Cosmina

Permission is hereby granted, free of charge, to anyone obtaining a copy 
of this software and associated documentation files (the "Software"), 
to work with the Software within the limits of freeware distribution and fair use. 
This includes the rights to use, copy, and modify the Software for personal use. 
Users are also allowed and encouraged to submit corrections and modifications 
to the Software for the benefit of other users.

It is not allowed to reuse,  modify, or redistribute the Software for 
commercial use in any way, or for a user's educational materials such as books 
or blog articles without prior permission from the copyright holder. 

The above copyright notice and this permission notice need to be included 
in all copies or substantial portions of the software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS OR APRESS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
*/
package com.apress.bgn.ten;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * @author Iuliana Cosmina
 * since 1.0
 */
public class InternationalizationDemo extends Application {

    private static final String BUNDLE_LOCATION = "chapter10/using-javafx/src/main/resources";

    private static ResourceBundle resourceBundle = null;
    private static Locale locale = Locale.of("en", "GB");
    private static int selectedLang = 0;

    void main() {
        launch();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        loadLocale(locale);
        primaryStage.setTitle(resourceBundle.getString("WindowTitle"));

        String[] data = {resourceBundle.getString("Cat"),
                resourceBundle.getString("Dog"),
                resourceBundle.getString("Parrot"),
                resourceBundle.getString("Mouse"),
                resourceBundle.getString("Cow"),
                resourceBundle.getString("Pig")};

        var borderPane = new BorderPane();

        //Top
        final ComboBox<String> comboBox = new ComboBox<>();
        comboBox.getItems().addAll(data);


        final ComboBox<String> langList = new ComboBox<>();
        String[] languages = {
                resourceBundle.getString("English"),
                resourceBundle.getString("French"),
                resourceBundle.getString("Italian"),
                resourceBundle.getString("Gaelic")};

        langList.getItems().addAll(languages);
        langList.getSelectionModel().select(selectedLang);

        var gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);

        var labelLang = new Label(resourceBundle.getString("ChooseLanguage"));
        gridPane.add(labelLang, 0, 0);
        gridPane.add(langList, 1, 0);

        var labelPet = new Label(resourceBundle.getString("ChoosePet"));
        gridPane.add(labelPet, 0, 1);
        gridPane.add(comboBox, 1, 1);

        borderPane.setTop(gridPane);

        //Center
        final TextArea textArea = new TextArea();
        textArea.setEditable(false);
        borderPane.setCenter(textArea);

        comboBox.valueProperty().addListener((_, _, newValue)
                -> textArea.appendText(newValue + "\n"));

        langList.valueProperty().addListener((_, _, _)
                -> {
            int idx = langList.getSelectionModel().getSelectedIndex();
            selectedLang = idx;
            locale = switch (idx) {
                case 1 -> Locale.of("fr", "FR");
                case 2 -> Locale.of("it", "IT");
                case 3 -> Locale.of("gh", "GA"); // Scottish Gaelic
                default -> Locale.of("en", "GB");
            };

            primaryStage.close();
            Platform.runLater(() -> {
                try {
                    new InternationalizationDemo().start(new Stage());
                } catch (Exception e) {
                    System.err.println("Could not reload application!");
                }
            });
        });

        var box = new HBox();
        box.setPadding(new Insets(10, 12, 10, 12));
        box.setSpacing(10);
        box.setAlignment(Pos.BASELINE_RIGHT);
        box.setStyle("-fx-background-color: #85929e;");
        Button exitButton = new Button();
        exitButton.setText(resourceBundle.getString("Byebye"));
        exitButton.setOnAction(_ -> Platform.exit());
        box.getChildren().add(exitButton);
        borderPane.setBottom(box);

        //Bottom
        var root = new StackPane();
        root.getChildren().add(borderPane);
        primaryStage.setScene(new Scene(root, 500, 500));
        primaryStage.show();
    }

    private void loadLocale(Locale locale) throws Exception {
        var file = new File(BUNDLE_LOCATION);
        URL[] url = {file.toURI().toURL()};
        var loader = new URLClassLoader(url);

        resourceBundle = ResourceBundle.getBundle("global", locale, loader);
    }
}
