import java.util.ArrayList;
import java.util.List; 
import java.util.Random;  

public class Deck implements Comparable<Deck> {

    /** The full deck of cards */ 
    ArrayList<Card> deck;
    String str;
    /**
     * Initializes the deck of cards. An empty ArrayList.
     */
    public Deck() {
        deck = new ArrayList<>();
    } 
    
    /**
     * Initializes the deck of cards. An empty ArrayList.
     */
    public Deck(String str) {
        deck = new ArrayList<>();
        stringToDeck(str);
    } 
    
    /**
     * Turns a string representation of a deck and turns it into a deck object
     * @param str the string representation of a deck
     */
    public void stringToDeck(String str) {
        String[] symbols = { "A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K" };
        int[] values = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13 };
        boolean check = false;
        int num = 0;
        int value = 0;
        boolean done = false;
        boolean face = false;
        for(int i = 0; i < str.length() - 1; i++){
            if(str.charAt(i) == ' '){
                if(str.charAt(i + 1) == 'F' || str.charAt(i + 1) == 'T'){
                    check = true;
                }else if(Character.isLetter(str.charAt(i + 1))){
                    check = false;
                }else{
                    check = true;
                }
            }else if(check && Character.isLetter(str.charAt(i))){
                if(str.charAt(i) == 'F'){
                    face = false;
                }else if(str.charAt(i) == 'T'){
                    face = true;
                }
                num++;
                done = true;
            }else if(check && Character.isDigit(str.charAt(i)) && Character.isDigit(str.charAt(i + 1))){
                num++;
                String s = str.substring(i, i + 2);
                int x = Integer.parseInt(s);
                value = x;
                i++;
            }else if(check && Character.isDigit(str.charAt(i)) && !Character.isDigit(str.charAt(i + 1)) && str.charAt(i + 1) == ' '){
                String s = str.substring(i, i + 1);
                int x = Integer.parseInt(s);
                value = x;
            }else if(!check && Character.isLetter(str.charAt(i))){
                num++;
            }

            if(done){
                deck.add(new Card(value, "Spades", face));
                done = false;
            }
        }
    }

    /**
     * Fills the draw cards piles and the stacks given the full deck.
     * 
     * @param start starting index
     * @param end   ending index
     * @param d     the full deck
     * @return the full new Deck that has been created
     */
    public Deck fillDeck(int start, int end, Deck d) {
        try{
            ArrayList<Card> arr = d.deck;
            for(int i = start; i < end; i++){
                deck.add(arr.get(i));
            }
            for(int i = start; i < end; i++){
                arr.remove(i);
                i--; 
                end--;
            }
            d.deck = arr;
            return this;
            
        }catch (IndexOutOfBoundsException e){
            return this;
        }  
    }  
    /**
     * Adds a card to the deck
     * @param c card to be added
     */
    public void add(Card c){
        deck.add(c);
    }
    
    /**
     * Flips the card at the end of an
     */
    public void flipCards(boolean f){
        deck.get(deck.size() - 1).setFaceUp(f); 
    }  

    /**
     * Fills the deck with cards (in order). There will only be a multiple of 13 in the deck of cards.
     * 
     * @param numSuit the number of decks (of 13)
     * @return the "deck" aka the ArrayList with Card objects
     */  
    public void fillDeck(int numDecks) {
        String[] symbols = { "A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K" };
        int[] values = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13 };
        String[] suits = { "Spades", "Hearts", "Clovers", "Diamonds"};
        for (int j = 0; j < numDecks; j++) {
            for (int i = 12; i >= 0; i--) {
                deck.add(new Card(values[i], suits[j]));
            }
        }
    }

    /**
     * Shuffles the deck of cards randomly. 
     * 
     * @return the shuffled deck of cards (ArrayList).
     */
    public void shuffle() {
        Random rand = new Random();
        for (int i = deck.size() - 1; i > 0; i--) {
            int j = rand.nextInt(deck.size());

            Card card = deck.get(i);
            deck.set(i, deck.get(j));
            deck.set(j, card);
        }
    }

    /**
     * Returns the deck sorted from lowest to highest number (w/o suit).
     */
    public static void sort(ArrayList<Card> arr) {
		for(int j = 0; j < arr.size() - 1; j++) {
			Card max = new Card(1, "Spades");
			int index = -1;
			for(int i = 0; i < arr.size() - j; i++) {
				if(arr.get(i).getValue() >= max.getValue()) {
					max = arr.get(i);
					index = i;
				}
			}
			Card temp = arr.get(arr.size() - 1 - j);
			arr.set(arr.size() - 1 - j, arr.get(index));
			arr.set(index, temp);
		}
		System.out.println(arr);
	}
    // public void sort() {
    //     String[] symbols = { "A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K" };
    //     int[] values = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13 };
    //     ArrayList<Card> arr = new ArrayList<>();
    //     int j = 0;
    //     for (int i = 0; i < values.length; i++) {
    //         for(j = 0; j < deck.size(); j++){
    //             if(values[i] == deck.get(j).getValue()){
    //                 arr.add(deck.get(j));
    //             }
    //         }
    //     }
    //     deck = arr;
    // }   

    /**
     * Prints the card that is passed in the parameter (w/symbol and suit).
     * 
     * @param card a card object
     */
    public void format(Card card){
        if(card.getSuit().equals("Hearts")){
            System.out.print(card.toString() + "♡");
        }else if(card.getSuit().equals("Spades")){
            System.out.print(card.toString() + "♤");
        }else if(card.getSuit().equals("Clovers")){
            System.out.print(card.toString() + "♧");
        }else{
            System.out.print(card.toString() + "♢");
        }
    }

    /**
     * Prints the full deck of cards with formatting.
     */
    public void printDeck(){
        for(int i = 0; i < deck.size(); i++){
            if(i == 0 && i == deck.size() - 1){
                System.out.print("[");
                format(deck.get(i));
                System.out.print("]");
            }else if(i == 0){
                System.out.print("[");
                format(deck.get(i));
                System.out.print(", ");
            }else if(i == deck.size() - 1){
                format(deck.get(i));
                System.out.print("]");
            }else{
                format(deck.get(i));
                System.out.print(", ");
            }
        }
        System.out.println();
    }

    /**
     * Checks the parameter's deck and sees how many more or less cards it has. Prints it out.
     * 
     * @param d1 the deck you want to compare to the current deck.
     * @return the difference between them
     */
    @Override
    public int compareTo(Deck d1) {
        int diff = d1.getLength() - this.getLength();
        if(d1.getLength() - this.getLength() < 0){
            System.out.println(-diff + " less cards");
        }else{
            System.out.println(diff + " more cards");
        }
        return diff;
    }

    /** 
     * Returns the size of the current deck.
     * @return the size of the arrayList
     */ 
    public int getLength() {
        return deck.size();
    }

    /**
     * Returns a string representation of a deck
     * @return a string deck
     */
    public String stringDeck(){
        String s = "";
        for(int i = 0; i < deck.size(); i++){
            String face = deck.get(i).isFaceUp() ? "T" : "F";
            s += deck.get(i).getSymbol() + " " + deck.get(i).getValue() + " " + face + " ";
        }
        return s;
    }

    public ArrayList<Card> getCards(){
        return deck;
    }
}
