public class Card {
    private final String _FACE;
    private final String _SUIT;


    public Card(String cardFace, String cardSuit) {
        this._FACE = cardFace;
        this._SUIT = cardSuit;
    }

    // Function to return value of a card in int
    public int cardValue() {
        String[] faces = {"Ace", "Two", "Three", "Four", "Five", "Six",
                "Seven", "Eight", "Nine", "Ten", "Jack", "Queen", "King"};
        for (int i = 0; i < faces.length; i++) {
            if (_FACE.equals(faces[i]))
                return i + 1;
        }
        return 0;
    }

    //Function to return a string representation of the card in the format "FACE of SUIT"
    public String toString() {
        return _FACE + " of " + _SUIT;
    }

}