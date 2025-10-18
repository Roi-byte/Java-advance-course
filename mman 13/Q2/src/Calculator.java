// Methods for all of the calculator core function
public class Calculator {

    private String operator; // Stores the current operation (e.g., add, subtract)
    private double prevNum, curNum; // Stores the previous and current numbers for calculations
    private boolean opFlag; // True if the last action was an operation (e.g., add, subtract)
    private String text; // Holds the current text (number or result) on the display

    // Constructor initializes the calculator's internal state.
    public Calculator() {
        text = ""; // No initial text
        operator = ""; // No initial operator
        prevNum = 0; // No previous number
        curNum = 0; // No current number
        opFlag = false; // No operator flag set initially
    }

    // Returns the current result as a string, so it can be displayed on the screen.
    public String getCurrentResult() {
        return text; // Returns the current text (result)
    }

    // Resets the calculator's internal memory and clears the display.
    public void reset() {
        text = ""; // Clear display text
        prevNum = 0; // Reset previous number
        curNum = 0; // Reset current number
        operator = ""; // Reset operator
        opFlag = false; // Reset operator flag
    }

    // Changes the sign of the current number (i.e., makes it positive or negative).
    public void changeSign() {
        double number = parseDouble(text); // Parse the current text into a number
        text = fixedNumber(-number); // Change the sign and format the number
    }

    // Calculates the result and stores it in the display.
    public void equal() {
        curNum = parseDouble(text); // Parse the current text into the current number
        text = calcResult(); // Perform the calculation and store the result as text
        prevNum = parseDouble(text); // Update the previous number with the result
        operator = ""; // Reset the operator after calculation
        opFlag = false; // Reset the operator flag
    }

    // Adds a comma (decimal point) to the current text unless one already exists.
    public void comma() {
        String curText = text; // Store the current text
        if (opFlag || curText.length() == 0) {
            text = "0."; // Start with 0 if the text is empty or after an operator
        } else if (!curText.contains(".")) {
            text = curText + "."; // Add decimal if not already present
        }
        opFlag = false; // Reset the operator flag
    }

    // Sets the operator (e.g., add, subtract) and performs the operation if necessary.
    public void operation(String op) {
        if (opFlag) {
            operator = op; // If the last action was an operator, just change the operator
        } else {//relevant only for the first num
            opFlag = true; // Set the operator flag to true
            curNum = parseDouble(text); // Parse the current text into the current number
            text = calcResult(); // Perform the calculation and store the result
            prevNum = parseDouble(text); // Update the previous number with the result
            operator = op; // Set the new operator
        }
    }

    // Takes a number as a string and adds it to the currently displayed number.
    public void number(String num) {
        if (opFlag) {
            text = num; // If the last action was an operation, replace the current text with the new number
        } else {
            text += num; // Otherwise, append the number to the current text
        }
        opFlag = false; // Reset the operator flag
    }

    // parseDouble is a wrapper around Double.parseDouble, ensuring that the text isn't empty and returns 0 on failure.
    private double parseDouble(String text) {
        if (text != null && text.length() > 0) {
            try {
                return Double.parseDouble(text); // Try to parse the string into a double
            } catch (NumberFormatException e) {
                // If parsing fails, return 0
                return 0;
            }
        }
        return 0; // Return 0 if the text is null or empty
    }

    // calcResult computes the result based on the current operator.
    private String calcResult() {
        switch (operator) {
            case "add":
                return fixedNumber(prevNum + curNum); // Perform addition
            case "divide":
                return fixedNumber(prevNum / curNum); // Perform division
            case "subtract":
                return fixedNumber(prevNum - curNum); // Perform subtraction
            case "multiply":
                return fixedNumber(prevNum * curNum); // Perform multiplication
            default:
                return fixedNumber(curNum); // If no operation, return the current number
        }
    }

    // fixedNumber formats the number to have a maximum of 5 digits after the decimal point,
    // And removes any unnecessary trailing zeros.
    private String fixedNumber(double num) {
        if (num == (long) num)
            return String.format("%d", (long) num);
        return String.format("%.5f", num).replaceAll("\\.?0*$", ""); // Format as a decimal with up to 5 digits
    }

}
