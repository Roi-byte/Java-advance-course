public class PolConstructor {
    private int power;
    private double num;

    // Constructor to initialize the polynomial term with a number (coefficient) and power
    public PolConstructor(double num, int power) {
        this.power = power;
        this.num = num;
    }
    // Getter for the power of the polynomial term
    public int getPower() {
        return this.power;
    }
    // Getter for the number (coefficient) of the polynomial term
    public double getNum() {
        return this.num;
    }

    // Override toString method to provide a string representation in polynomial term
    @Override
    public String toString() {
        if (this.num == 0) return "";

        StringBuilder str = new StringBuilder();

        if (this.num > 0) {
            str.append("+").append(this.num);
        } else {
            str.append(this.num);
        }

        str.append("x");

        if (this.power > 1) {
            str.append("^").append(this.power);
        }

        return str.toString();
    }
}
