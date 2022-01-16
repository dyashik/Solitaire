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
