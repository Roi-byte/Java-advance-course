import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyEvent;

// CalculatorController handles the interaction with the JavaFX UI and the Calculator logic.
public class CalculatorController {

    @FXML
    private TextArea display; // The display area of the calculator (TextField)
    private Calculator calculator; // The Calculator instance to perform calculations

    // Constructor
    public CalculatorController() {
        calculator = new Calculator();
    }

    // Handles the numerical buttons (0-9) and updates the display accordingly.
    @FXML
    private void handleNum(Event event) {
        Button btn = (Button) event.getSource(); // Get the button that was clicked
        calculator.number(btn.getText()); // Send the button's text to the calculator for processing
        displayResult(); // Update the display with the current result
    }

    // Resets the calculator's memory (clears stored values) and sets the display to empty
    @FXML
    private void handleDelete() {
        calculator.reset(); // Reset the calculator's internal state
        displayResult(); // Update the display to reflect the reset state
    }

    // Handles clicks on the operation buttons (+, -, *, /) and processes the operation
    @FXML
    private void handleOperation(Event event) {
        Button btn = (Button) event.getSource(); // Get the clicked button
        calculator.operation(btn.getId()); // Pass the operation ID to the calculator
        displayResult(); // Update the display with the result of the operation
    }

    // Handles clicks on the ± button to toggle the sign of the current number
    @FXML
    private void handleChangeSign() {
        calculator.changeSign(); // Change the sign of the current number
        displayResult(); // Update the display after changing the sign
    }

    // Calculates and displays the result when the equals (=) button is clicked
    @FXML
    private void handleEquals() {
        calculator.equal(); // Perform the calculation to get the result
        displayResult(); // Display the result of the calculation
    }

    // Handles the comma button (decimal point)
    @FXML
    private void handleComma() {
        calculator.comma(); // Add a decimal point to the current number
        displayResult(); // Update the display with the new number
    }

    // Routes key presses (e.g., number and operator keys) to the corresponding function
    @FXML
    private void handleKeyPress(KeyEvent key) {
        String keyText = key.getText(); // Get the text of the key that was pressed
        switch (keyText) {
            case "0":
            case "1":
            case "2":
            case "3":
            case "4":
            case "5":
            case "6":
            case "7":
            case "8":
            case "9":
                calculator.number(keyText); // Handle number key press
                break;
            case "+":
                calculator.operation("add"); // Handle addition
                break;
            case "−":
                calculator.operation("subtract"); // Handle subtraction
                break;
            case "*":
                calculator.operation("multiply"); // Handle multiplication
                break;
            case "/":
                calculator.operation("divide"); // Handle division
                break;
            case "=":
                calculator.equal(); // Handle equals (calculate result)
                break;
            case ".":
                calculator.comma(); // Handle decimal point
                break;
            case "±":
                calculator.changeSign(); // Handle ± sign change
                break;
            default:
                break; // Default case to handle unrecognized key presses (e.g., letters or symbols)
        }
        displayResult(); // Update the display after processing the key press
    }

    // Updates the display with the current result from the calculator
    private void displayResult() {
        display.setText(calculator.getCurrentResult()); // Set the text of the display to the current result
    }

}
