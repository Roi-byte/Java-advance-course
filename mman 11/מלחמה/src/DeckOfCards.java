import java.security.SecureRandom;
import java.util.ArrayList;
// this is a class for deck of cards it include all of the operation of deck
public class DeckOfCards {
    private ArrayList<Card> _deck;
    private static final int _NUMBER_OF_CARDS =52;
    private static final SecureRandom _RANDOM_NUMBER = new SecureRandom();

    // create deck with Card objects
    public DeckOfCards() {
        _deck = new ArrayList<Card>();
        String[] faces = { "Ace", "Two", "Three", "Four", "Five", "Six",
                "Seven", "Eight", "Nine", "Ten", "Jack", "Queen", "King" };
        String[] suits = { "Hearts", "Diamonds", "Clubs", "Spades" };
        for (int i = 0; i < _NUMBER_OF_CARDS; i++)
            //make sure we got a legal deck
            _deck.add( new Card(faces[i % 13], suits[i / 13]));
    }
    //clear deck
    public void clearDeck(){
        _deck.clear();
    }

    // shuffle deck of Cards
    public void shuffle() {
        for(int first = 0; first < _deck.size(); first++) {
            // select a random number between 0 and 51
            int second = _RANDOM_NUMBER.nextInt(_NUMBER_OF_CARDS);
            // swap current Card with randomly selected Card
            Card temp = _deck.get(first);
            _deck.set(first,_deck.get(second));
            _deck.set(second,temp);
        }
    }

    // deal one Card
    public Card dealCard() {
        // determine whether Cards remain to be dealt
        if (!_deck.isEmpty())
            return _deck.remove(0); //return card at top of deck
        return null; // return null to indicate that all Cards were dealt
    }

    public int SizeOfDeck () {
        return _deck.size();
    }

    public boolean isEmpty () {
        return _deck.size()==0;
    }

    // add card to the deck
    public void addCard(Card card) {
        _deck.add(card);
    }
    // add entire deck other to the deck
    public void addDeck(DeckOfCards  other) {
        while (!other.isEmpty())
            _deck.add(other.dealCard());
    }
} // End of class


