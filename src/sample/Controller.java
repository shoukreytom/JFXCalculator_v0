package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.math.BigDecimal;
import java.math.MathContext;

public class Controller {
    @FXML
    private TextField screen;
    @FXML
    private Label screen2;
    private BigDecimal value1; //cause: it can handle both double and int and also deals with large numbers like: 9983298384 , 33219834823,.. etc.
    private BigDecimal value2;
    private BigDecimal result;
    private String op;
    private StringBuilder sb = new StringBuilder();
    private String sw = "+"; //provided for making number positive or negative, check hadleActions() {switch() case "-/+"}

    public void initialize() {
    }

    /*for handling key types from keyboard
     *(0 - 9 , add , minus , multiplication , divide , decimal , back-space)
     */
    @FXML
    public void handleKey(KeyEvent event) {
        switch(event.getCode()) {
            case NUMPAD0: case NUMPAD1: case NUMPAD2: case NUMPAD3:
            case NUMPAD4: case NUMPAD5: case NUMPAD6: case NUMPAD7:
            case NUMPAD8: case NUMPAD9: case DECIMAL:
                screen.appendText(event.getText());
                break;
            case ADD:
                if(!screen.getText().isEmpty()) {
                    value1 = new BigDecimal(screen.getText());
                    op = "+";
                    sb.append(value1);
                    sb.append(op);
                    screen2.setText(sb.toString());
                    screen.setText("");
                    sb.delete(0, sb.length());
                }
                break;
            case MINUS:
                if(!screen.getText().isEmpty()) {
                    value1 = new BigDecimal(screen.getText());
                    op = "-";
                    sb.append(value1);
                    sb.append(op);
                    screen2.setText(sb.toString());
                    screen.setText("");
                    sb.delete(0, sb.length());
                }
                break;
            case MULTIPLY:
                if(!screen.getText().isEmpty()) {
                    value1 = new BigDecimal(screen.getText());
                    op = "*";
                    sb.append(value1);
                    sb.append(op);
                    screen2.setText(sb.toString());
                    screen.setText("");
                    sb.delete(0, sb.length());
                }
                break;
            case DIVIDE:
                if(!screen.getText().isEmpty()) {
                    value1 = new BigDecimal(screen.getText());
                    op = "/";
                    sb.append(value1);
                    sb.append(op);
                    screen2.setText(sb.toString());
                    screen.setText("");
                    sb.delete(0, sb.length());
                }
                break;
            case EQUALS:
                equal();
                break;
            case DELETE:
                clear1();
                break;
            case BACK_SPACE:
                delete();
                break;
        }
    }

    //for handling Buttons Action
    @FXML
    public void handleActions(ActionEvent e) {
        String text = ((Labeled) e.getSource()).getText();
        switch (text) {
            case "+":
                if(!screen.getText().isEmpty()) {
                    value1 = new BigDecimal(screen.getText());
                    op = "+";
                    sb.append(value1);
                    sb.append(op);
                    screen2.setText(sb.toString());
                    screen.setText("");
                    sb.delete(0, sb.length());
                }
                break;
            case "-":
                if(!screen.getText().isEmpty()) {
                    value1 = new BigDecimal(screen.getText());
                    op = "-";
                    sb.append(value1);
                    sb.append(op);
                    screen2.setText(sb.toString());
                    screen.setText("");
                    sb.delete(0, sb.length());
                }
                break;
            case "*":
                if(!screen.getText().isEmpty()) {
                    value1 = new BigDecimal(screen.getText());
                    op = "*";
                    sb.append(value1);
                    sb.append(op);
                    screen2.setText(sb.toString());
                    screen.setText("");
                }
                break;
            case "/":
                if(!screen.getText().isEmpty()) {
                    value1 = new BigDecimal(screen.getText());
                    op = "/";
                    sb.append(value1);
                    sb.append(op);
                    screen2.setText(sb.toString());
                    screen.setText("");
                    sb.delete(0, sb.length());
                }
                break;
            case "+/-":
                if(sw.equals("-")) {
                    sw = "";
                    screen.setText("");
                }
                else {
                    sw = "-";
                    screen.setText("");
                }
                screen.appendText(sw);
                break;
                default:
                    screen.appendText(text);
                    break;
        }

    }

    //deletes one character backwards from text-field
    @FXML
    public void delete() {
        if (!screen.getText().isEmpty())
            screen.deleteText(screen.getText().length() - 1, screen.getText().length());
    }

    //cleans text-field
    @FXML
    public void clear1() {
        screen.clear();
    }

    //cleans label
    @FXML
    public void clear2() {
        screen2.setText("");
    }

    @FXML
    public void equal() {
        if(!screen.getText().isEmpty()) {
            value2 = new BigDecimal(screen.getText());
        }
        if(value1 != null && value2 != null) {
            switch (op) {
                case "+":
                    result = value1.add(value2);
                    break;
                case "-":
                    result = value1.subtract(value2);
                    break;
                case "*":
                    result = value1.multiply(value2);
                    break;
                case "/":
                    result = value1.divide(value2, MathContext.DECIMAL32);
                    result = result.stripTrailingZeros(); //removes all unnecessary zeroes form result
                    break;
            }
            if((value2.intValue() < 0 || value2.doubleValue() < 0.0) && op.equals("+")) {
                screen2.setText(value1 + "-" + value2.abs() + "=" + result);
                screen.setText(result + "");
            }else if((value2.intValue() < 0 || value2.doubleValue() < 0.0) && op.equals("-")) {
                screen2.setText(value1 + "+" + value2.abs() + "=" + result);
                screen.setText(result + "");
            }else {
                screen2.setText(value1 + op + value2 + "=" + result);
                screen.setText(result + "");
            }
            sb.delete(0, sb.length());
            value2 = null; //cause: by default value2's value is last number entered
        }
    }
}