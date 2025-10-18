import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        startWarGame();
    }

    // Game of war function
    public static void startWarGame() {
        DeckOfCards deckA = new DeckOfCards();
        DeckOfCards deckB = new DeckOfCards();
        DeckOfCards temp = new DeckOfCards();
        deckA.clearDeck();
        deckB.clearDeck();
        temp.shuffle();
        divide(deckA, deckB, temp);
        temp.clearDeck();

        String output;

        while (!deckA.isEmpty() && !deckB.isEmpty()) {
            Card cardA = deckA.dealCard();
            Card cardB = deckB.dealCard();
            output = "Player A Card is: " + cardA.toString() +
                    "\nPlayer B Card is: " + cardB.toString();

            if (cardA.cardValue() > cardB.cardValue()) {
                Addtodeck(cardA, cardB, deckA, temp);
                output += "\nPlayer A won!";
                if (showMessage(output)) return; // check for X
            } else if (cardA.cardValue() < cardB.cardValue()) {
                Addtodeck(cardB, cardA, deckB, temp);
                output += "\nPlayer B won!";
                if (showMessage(output)) return;
            }
            // War mode
            else {
                output += "\nIt's a war!";
                if (showMessage(output)) return;
                // Add 2 cards from each player to the temp deck
                for (int i = 0; i < 2; i++) {
                    cardA = deckA.dealCard();
                    cardB = deckB.dealCard();
                    temp.addCard(cardA);
                    temp.addCard(cardB);
                }
            }
        } // End of while
        winner(deckA, deckB);
    }

    // Method to show message and exit on X
    public static boolean showMessage(String message) {
        int result = JOptionPane.showConfirmDialog(null, message, "War Game", JOptionPane.DEFAULT_OPTION);
        if (result == JOptionPane.CLOSED_OPTION) {
            System.exit(0); // Exit when user presses X or closes dialog
            return true;
        }
        return false;
    }

    // Deal the cards to the decks
    public static void divide(DeckOfCards deckA, DeckOfCards deckB, DeckOfCards MainDeck) {
        for (int i = 0; i < 52; i++) {
            if (i % 2 == 0)
                deckA.addCard(MainDeck.dealCard());
            else
                deckB.addCard(MainDeck.dealCard());
        }
    }

    // Add to the winning player’s deck
    public static void Addtodeck(Card cardA, Card cardB, DeckOfCards deck, DeckOfCards temp) {
        temp.addCard(cardA);
        temp.addCard(cardB);
        deck.addDeck(temp);
        temp.clearDeck();
    }
    //check winner and print winner
    public static  void winner (DeckOfCards A, DeckOfCards B) {
        if(A.isEmpty())
            JOptionPane.showMessageDialog(null,  "Player B won the game!");
        else
            JOptionPane.showMessageDialog(null, "Player A won the game!");
    }
}// end of Main
