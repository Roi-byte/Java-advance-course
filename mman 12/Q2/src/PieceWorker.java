public class PieceWorker extends Employee {
    // Instance variables to store the number of items produced and the price per item
    private int items;
    private final int ITEM_PRICE = 17000;  // Price per item, fixed at 17000
    // Constructor for the PieceWorker
    public PieceWorker(String firstname, String lastName, String socialSecurityNumber, BirthDate date, int items) {
        super(firstname, lastName, socialSecurityNumber, date);
        // Validate the number of items is non-negative
        if (items < 0){
            throw new IllegalArgumentException("items must be >= 0");
        }
        this.items = items;
    }
    // Getter method to retrieve the number of items produced by the PieceWorker
    public int getItems()
    {
        return this.items;
    }

    @Override
    public double earnings() {
        return getItems() * ITEM_PRICE + GetBirthdayBonus(); // The earnings are calculated as the number of items produced multiplied by the price per item plus Bonus if relevant
    }

    public String toString() {
        return String.format("piece employee: %s%n%s: %s%s; %s: %s",super.toString(), "created", getItems(), " items", "item's price", ITEM_PRICE);
    }
}
