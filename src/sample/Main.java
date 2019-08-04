/**
 * Simple mini calculator deals with addition,subtraction,multiplication,division.
 * Created by: Shoukrey Tom
 * it's advantages:-
 *  1- it deals with any number even millions or billions.
 *  2- it deals with negative and positive numbers.
 *  3- it's not mixing between double and int for example:
 *          if you done operation on integer the result will be integer but division depends on result.
 *  4- it provides numpad keys for inserting numbers and making calculations.
 *  5- it provides types of clear screen tools:-
 *          1\ "X" this delete one number backwards
 *          2\ "C1" this cleans main-screen(largest screen)
 *          3\ "C2" this cleans the up screen(smallest screen)
 *  6- nearly there is no bug in it that leads to unexpected result
 */
package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Tooltip;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application {

    public static Stage stage;

    @Override
    public void start(Stage window) throws Exception{
        stage = window;
        Parent root = FXMLLoader.load(getClass().getResource("CalculatorView.fxml"));
        window.setTitle("mini-calc");
        window.setScene(new Scene(root, 386, 552));
        window.initStyle(StageStyle.UNIFIED);
        window.setResizable(false);
        window.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
