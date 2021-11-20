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
public class DeckTester{ 
    public static void main(String[] args) {
        Deck deck = new Deck();
        Deck deck1 = new Deck();
        System.out.println();
        System.out.println("Deck 1: ");
        deck1.fillDeck(2);
        deck1.shuffle();
        deck1.printDeck();
        deck1.sort(deck1.getCards());
        //System.out.println(deck1.stringDeck());
        // deck1.printDeck();
        // System.out.println();
        // System.out.println("Deck 2: ");
        // deck.printDeck();
        // deck.fillDeck(1);
        // deck.printDeck();
        // deck.shuffle();
        // deck.printDeck();
        // deck.sort();
        // System.out.println();
        // deck1.compareTo(deck);
        // System.out.println();
    }
}