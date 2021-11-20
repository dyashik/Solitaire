/**
 * Author: Yashik Dhanaraj
 * Period: 5
 * Date: 12/2
 * Time Taken: 2hr
 *   
 * Summary: I did much better than I thought. I had an error but it
 * didn't turn out to be my understanding but it was some glitch with the 
 * compiler stuff. But Mr. McLeod was able to fix it. Something really neat
 * that i did was I had a printList method which printed the list with the 
 * suits next to the symbol to make it a more user friendly.
 *  
 **/  
/** 
 * Card.java
 *
 * <code>Card</code> represents a basic playing card.
 */
public class Card implements Comparable<Card> {
    /** String value that holds the symbol of the card.
    Examples: "A", "Ace", "10", "Ten", "Wild", "Pikachu"
     */
    private String symbol;

    /** int value that holds the value this card is worth */
    private int value;

    /** boolean value that determines whether this card is face up or down */
    private boolean isFaceUp;

    private String suit;

    /**
     * Creates a new <code>Card</code> instance.
     *
     * @param symbol  a <code>String</code> value representing the symbol of the card
     * @param value an <code>int</code> value containing the point value of the card
     */    
    public Card(int value, String suit) {
        String[] symbols = { "A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K" };
        this.value = value;
        this.symbol = symbols[value - 1];
        isFaceUp = true;
        this.suit = suit;
    }
    
    /**
     * Creates a new Card Instance
     * 
     * @param value the card's number value
     * @param suit the suit of the card
     * @param faceUp the state of the card (face up or face down)
     */
    public Card(int value, String suit, boolean faceUp) {
        String[] symbols = { "A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K" };
        this.value = value;
        this.symbol = symbols[value - 1];
        isFaceUp = faceUp;
        this.suit = suit;
    } 

    /**
     * Getter method to access this <code>Card</code>'s symbol.
     * 
     * @return this <code>Card</code>'s symbol.
     */
    public String getSymbol() {
        return symbol;
    }
    
    /**
     * Returns the value of the card (1 - 13).
     * @return the value of the card
     */
    public int getValue() {
        return value;
    }

    /**
     * Returns if the card is face up.
     * @return faceup(is it or not) 
     */
    public boolean isFaceUp() {
        return isFaceUp;
    }

    /**
     * Flips the card up or down
     * @param state faceUp or faceDown (true or false).
     */
    public void setFaceUp(boolean state) {
        isFaceUp = state;
    }

    /**
     * Returns the suit of the card.
     * 
     * @return the suit of the card
     */
    public String getSuit() {
        return suit;
    }

    /**
     * Returns whether or not this <code>Card</code> is equal to another
     *  
     *  @return whether or not this Card is equal to other.
     */
    public boolean equals(Card other) {
        if(getSymbol().equals(other.symbol) && getValue() == other.getValue() && getSuit().equals(other.getSuit())){
            return true;
        }else{
            return false;
        }
    }

    /**
     * Returns this card as a String.  If the card is face down, "X"
     * is returned.  Otherwise the symbol of the card is returned.
     *
     * @return a <code>String</code> containing the symbol and point
     *         value of the card.
     */
    @Override
    public String toString() {
        if(!isFaceUp()){
            return "X";
        }else{
            return getSymbol();
        }
    }

    /**
     * Returns an integer showing the difference between two cards. 
     * 
     * @return the difference between passed Card and the current Card's value
     */
    @Override
    public int compareTo(Card other) {
        return (getValue() - other.value);
    }
}