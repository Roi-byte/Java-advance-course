import java.util.ArrayList;

public class Polynom {
    // ArrayList to store Polynoms
    private ArrayList<PolConstructor> polynom = new ArrayList<>();
    // Constructor to initialize a polynomial from arrays of numbers and powers
    public Polynom(double[] num, int[] powers) throws Exception {
        // Ensure that the arrays for numbers and powers have the same length
        if (num.length != powers.length) {
            throw new Exception("Error! the arrays are not in the same length :(");
        }
        // Create PolConstructor objects from numbers and powers and add them to the polynom list
        for (int i = 0; i < powers.length; i++) {
            polynom.add(i, new PolConstructor(num[i], powers[i]));
        }
        // Call sort to sort the polynomial terms by their powers
        sort(polynom);
    }

    public Polynom(ArrayList<PolConstructor> pol) {
        this.polynom = new ArrayList<>(pol);
    }
    // Getter for the polynomial
    public ArrayList<PolConstructor> getPolynom() {
        return this.polynom;
    }
    // Sorts the polynomial terms by power
    public void sort(ArrayList<PolConstructor> polynom) {
        for (int i = 0; i < polynom.size(); i++) {
            for (int j = i + 1; j < polynom.size(); j++) {
                if (polynom.get(i).getPower() < polynom.get(j).getPower()) {
                    PolConstructor temp = polynom.get(i);
                    polynom.set(i, polynom.get(j));
                    polynom.set(j, temp);
                }
            }
        }

        for (int i = 0, j = 1; j < polynom.size(); i++, j++) {
            if (polynom.get(i).getPower() == polynom.get(j).getPower()) {
                polynom.set(j, new PolConstructor(polynom.get(i).getNum() + polynom.get(j).getNum(), polynom.get(j).getPower()));
                polynom.remove(i);
            }
        }
    }
    // Adds another polynomial to the current one and returns the result as a new polynomial
    public Polynom Plus(Polynom pol) {
        Polynom newPol = new Polynom(this.polynom);
        boolean found;
        for (int i = 0; i < pol.polynom.size(); i++) {
            found = false;
            int newpower = pol.polynom.get(i).getPower();
            int j = 0;
            while (j < newPol.polynom.size() && !found) {
                int currentpower = newPol.polynom.get(j).getPower();
                double newNum = pol.polynom.get(i).getNum();
                if (newpower == currentpower) {
                    newPol.polynom.set(j, new PolConstructor(newNum + newPol.polynom.get(j).getNum(), newpower));
                    found = true;
                }
                j++;
            }
            if (!found) {
                newPol.polynom.add(0, pol.polynom.get(i));
            }
        }
        newPol.sort(newPol.getPolynom());
        return newPol;
    }
    // Subtracts another polynomial from the current one and returns the result as a new polynomial
    public Polynom Minus(Polynom pol) {
        Polynom newPol = new Polynom(this.polynom);
        boolean found;
        for (int i = 0; i < pol.polynom.size(); i++) {
            found = false;
            int newpower = pol.polynom.get(i).getPower();
            double newNum = pol.polynom.get(i).getNum();
            int j = 0;
            while (j < newPol.polynom.size() && !found) {
                int currentpower = newPol.polynom.get(j).getPower();
                if (newpower == currentpower) {
                    newPol.polynom.set(j, new PolConstructor(newPol.polynom.get(j).getNum() - newNum, newpower));
                    found = true;
                }
                j++;
            }
            if (!found) {
                newPol.polynom.add(0, new PolConstructor(-newNum, newpower));
            }
        }
        newPol.sort(newPol.getPolynom());
        return newPol;
    }
    // Method to "cut" the polynom
    public Polynom Cut() {
        Polynom newPol = new Polynom(this.polynom);
        for (int i = 0; i < newPol.polynom.size(); i++) {
            int Power = newPol.polynom.get(i).getPower();
            if (Power != 0) {
                double Num = newPol.polynom.get(i).getNum();
                newPol.polynom.set(i, new PolConstructor(Power * Num, Power - 1));
            } else {
                newPol.polynom.remove(i);
            }
        }
        return newPol;
    }
    // Override the toString method to print the polynomial as a strin
    @Override
    public String toString() {
        if (this.polynom.isEmpty()) {
            return "0";
        }

        StringBuilder str = new StringBuilder();

        // Handle single term or first term
        if (this.polynom.size() == 1) {
            // Special case for a single term
            if (this.polynom.get(0).getPower() == 0) {
                // Just a constant
                return String.valueOf(this.polynom.get(0).getNum());
            } else {
                // Single term with a variable
                return this.polynom.get(0).getNum() + "X^" + this.polynom.get(0).getPower();
            }
        } else {
            // Add first term
            str.append(this.polynom.get(0).getNum()).append("X^").append(this.polynom.get(0).getPower());

            // Add middle terms
            for (int i = 1; i < this.polynom.size() - 1; i++) {
                str.append(polynom.get(i).toString());
            }

            // Add last term with special handling for constant
            int lastIndex = this.polynom.size() - 1;
            if (this.polynom.get(lastIndex).getPower() == 0) {
                // Constant term
                if (this.polynom.get(lastIndex).getNum() < 0) {
                    str.append(this.polynom.get(lastIndex).getNum());
                } else {
                    str.append("+").append(this.polynom.get(lastIndex).getNum());
                }
            } else {
                // Variable term
                str.append(polynom.get(lastIndex).toString());
            }
        }

        return str.toString();
    }
    // Method to check equality between two polynomials
    public boolean equals(Object pol) {
        if (pol instanceof Polynom) {
            // Check if the polynomials have the same size
            if (this.polynom.size() != ((Polynom) pol).polynom.size())
                return false;
            // Check if each term has the same number and power
            for (int i = 0; i < this.polynom.size(); i++) {
                if (this.polynom.get(i).getNum() != ((Polynom) pol).polynom.get(i).getNum() ||
                        this.polynom.get(i).getPower() != ((Polynom) pol).polynom.get(i).getPower())
                    return false;
            }
            return true; // Return true if all terms match
        } else {
            System.out.println("Object type error, should be Polynom");
            return false;  // Return false if not
        }
    }
}

