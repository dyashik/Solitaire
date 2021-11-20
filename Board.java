import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.awt.EventQueue;
import javax.swing.JFileChooser;
import java.lang.reflect.InvocationTargetException;

/**
 * Author: Yashik Dhanaraj Period: 5 Date: 12/6 Time Taken: 10hr
 * 
 * Summary: Took freaking forever. I had some bug when I was trying to split up
 * the numbers in half and was unable to translate it into code. I was able to
 * do the math right but the correct thing to do was split it up into the amount
 * that needs more and the amount that needs less. I went step by step and was
 * able to fix every single bug but what cost you may ask? 10 hours of my
 * precious life. I was able to finish the rest of the program
 * 
 **/
public class Board{
    /* *** TO BE IMPLEMENTED IN ACTIVITY 4 *** */
    // Attributes
    /** number of stacks */
    int numStacks;
    /** number of decks */
    int numDecks;
    /** stacks array */
    ArrayList<Deck> stacks;
    /** draw pile */
    Deck topDeck;
    /** number of runs completed */
    int finished;

    /**
     * Sets up the Board and fills the stacks and draw pile from a Deck consisting
     * of numDecks Decks. Here are examples:
     * 
     * # numDecks #cards in overall Deck 1 13 (all same suit) 2 26 (all same suit) 3
     * 39 (all same suit) 4 52 (all same suit)
     * 
     * Once the overall Deck is built, it is shuffled and half the cards are placed
     * as evenly as possible into the stacks. The other half of the cards remain in
     * the draw pile.
     */
    public Board(int numStacks, int numDecks) {
        this.numStacks = numStacks;
        this.numDecks = numDecks;
        finished = 0;
        Deck deck = new Deck();
        deck.fillDeck(numDecks);
        deck.shuffle();
        deck.shuffle();
        int numHalf = (numDecks * 13) / 2;
        int otherHalf = (numDecks * 13) - numHalf;
        stacks = new ArrayList<>();
        Deck cards = new Deck();
        cards.fillDeck(0, numHalf, deck);
        topDeck = deck;
        topDeck.fillDeck(numHalf, otherHalf + numHalf, deck);

        int base = numHalf / numStacks;
        // every stack has at least this number
        int max = base + 1;
        // at most this number
        int calc = numHalf - (base * numStacks);
        // number of max stacks (one more than base)
        int norm = numStacks - calc;
        // number of min stacks
        int temp = max;
        for (int i = 0; i < numStacks; i++) {
            for (int j = 0; j < calc; j++) {
                Deck d = new Deck();
                stacks.add(d.fillDeck(0, max, cards));
                if (d.deck.size() != 0) {
                    d.flipCards(true);
                }
            }
            for (int j = calc; j < norm + calc; j++) {
                Deck d = new Deck();
                stacks.add(d.fillDeck(0, base, cards));
                if (d.deck.size() != 0) {
                    d.flipCards(true);
                }
            }
        }
        trim(stacks, numStacks);
    }

    /**
     * trim the arrayList of any duplicate objects (helper method)
     * 
     * @param stacks number of cards in each stack arraylist
     * @param num    number to trim
     */
    public void trim(ArrayList<Deck> stacks, int num) {
        ArrayList<Deck> arr = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            arr.add(stacks.get(i));
        }
        this.stacks = arr;
    }

    /**
     * Moves a run of cards from src to dest (if possible) and flips the next card
     * if one is available.
     */
    public void makeMove(String symbol, int src, int dest) {
        if (!symbol.equals("X")) {
            String[] symbols = { "K", "Q", "J", "10", "9", "8", "7", "6", "5", "4", "3", "2", "A" };
            int[] values = { 13, 12, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1 };
            ArrayList<Card> arr = stacks.get(src).deck;
            int index = -1;
            for (int i = 0; i < symbols.length; i++) {
                if (symbols[i].equals(symbol)) {
                    index = i;
                }
            }
            if (index == -1) {
                System.out.println("Number not in source stack!");
                return;
            }
            int value = values[index];
            int x = 0;
            for (int i = 0; i < arr.size(); i++) {
                if (arr.get(i).getValue() == value) {
                    x = i;
                }
            }
            ArrayList<Card> array = stacks.get(dest).deck;
            if (array.size() == 0) {
                moveIt(x, arr, array);
                flip(arr);
            } else {
                if (value == 13) {
                    System.out.println("Invalid Move!");
                } else {
                    Card a = new Card(value + 1, "Spades");
                    Card actual = stacks.get(dest).deck.get(stacks.get(dest).deck.size() - 1);
                    if (isRun(value, x, arr, index) && (checkCard(a, actual) || array.size() == 0)) {
                        moveIt(x, arr, array);
                        flip(arr);
                    } else {
                        System.out.println("Invalid Move!");
                    }
                }

            }

        } else {
            System.out.println("The card you seleted is flipped over!");
        }

    }

    /**
     * Checks if a stack is empty
     * 
     * @return returns if a singular stack or multiple stacks have no cards in them.
     */
    public boolean checkStacks() {
        boolean check = false;
        for (int i = 0; i < stacks.size(); i++) {
            if (stacks.get(i).deck.size() == 0) {
                check = check || true;
            }
        }
        return !check;
    }

    /**
     * flip cards when they are moved
     * 
     * @param arr arraylist of the row that has lost an element
     */
    public void flip(ArrayList<Card> arr) {
        boolean flip = true;
        for (int i = 0; i < arr.size(); i++) {
            if (arr.get(i).getSymbol().equals("X")) {
                flip = flip && true;
            } else {
                flip = flip && false;
            }
        }
        if (!flip) {
            int value = arr.get(arr.size() - 1).getValue();
            arr.get(arr.size() - 1).setFaceUp(true);
        }
    }

    /**
     * helper method to move the end of the array element to another
     * 
     * @param index the index needed to remove att
     * @param arr   needs moving
     * @param array moving to
     */
    public void moveIt(int index, ArrayList<Card> arr, ArrayList<Card> array) {
        for (int i = index; i < arr.size(); i++) {
            array.add(arr.get(i));
            arr.remove(i);
            i--;
        }

    }

    /**
     * Checks if the cards are the same
     * 
     * @param a      sample card
     * @param actual card needs to be compared with
     */
    public boolean checkCard(Card a, Card actual) {
        if (a.getValue() == actual.getValue()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Checks if there is a run
     * 
     * @param value starting value
     * @param start starting index
     * @param arr   arraylist that needs to be checked
     * @param index index in values array
     * 
     * @return if it is a run
     */
    public boolean isRun(int value, int start, ArrayList<Card> arr, int index) {
        if (arr.size() == 1) {
            return true;
        }
        int[] values = { 13, 12, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1 };
        boolean isRun = true;
        for (int i = start; i < arr.size(); i++) {
            if (arr.get(i).getValue() == values[index]) {
                isRun = isRun && true;
            } else {
                isRun = isRun && false;
            }
            index++;
        }
        return isRun;
    }

    /**
     * Moves one card onto each stack, or as many as are available
     */
    public void drawCards() {
        int size = topDeck.deck.size() - numStacks;
        boolean draw = true;
        for (int i = 0; i < stacks.size(); i++) {
            try {
                if (checkStacks()) {
                    topDeck.deck.get(topDeck.deck.size() - 1).setFaceUp(true);
                    stacks.get(i).add(topDeck.deck.get(topDeck.deck.size() - 1));
                    topDeck.deck.remove(topDeck.deck.size() - 1);
                } else {
                    draw = false;
                }
            } catch (IndexOutOfBoundsException e) {
                return;
            }
        }
        if (!draw) {
            System.out.println("Can't draw with empty stacks!");
        }
    }

    /**
     * Returns true if all stacks and the draw pile are all empty
     */
    public boolean isEmpty() {
        boolean empty = true;
        for (int i = 0; i < stacks.size(); i++) {
            if ((stacks.get(i)).deck.size() == 0) {
                empty = empty && true;
            } else {
                empty = empty && false;
            }
        }

        if (topDeck.deck.size() != 0) {
            empty = empty && false;
        } else {
            empty = empty && true;
        }
        return empty;
    }

    /**
     * If there is a run of A through K starting at the end of sourceStack then the
     * run is removed from the game or placed into a completed stacks area.
     * 
     * If there is not a run of A through K starting at the end of sourceStack then
     * an invalid move message is displayed and the Board is not changed.
     */
    public void clear(int sourceStack) {
        if (numStacks - 1 < sourceStack) {
            System.out.println("Invalid Stack!");
        } else {
            ArrayList<Card> arr = stacks.get(sourceStack).deck;
            Card c = new Card(13, "Spades");
            Card a = new Card(13, "Spades");
            boolean clear = false;
            int index = 0;
            for (int i = 0; i < arr.size(); i++) {
                if (arr.get(i).equals(c) && arr.size() == i + 13 && arr.get(i).equals(a)) {
                    for (int j = 0; j < 13; j++) {
                        int[] values = { 13, 12, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1 };
                        if (values[j] == arr.get(i + j).getValue()
                                && stacks.get(sourceStack).deck.get(i + j).isFaceUp()) {
                            clear = clear || true;
                            index = i;
                        } else {
                            clear = clear && false;
                        }
                    }
                }
            }
            if (clear) {
                for (int i = index; i < stacks.get(sourceStack).deck.size(); i++) {
                    stacks.get(sourceStack).deck.remove(i);
                    i--;
                }
                finished++;
            } else {
                System.out.println();
                System.out.println("Not Clearable!");
            }
        }

    }

    /**
     * Prints the board to the terminal window by displaying the stacks, draw pile,
     * and done stacks (if you chose to have them)
     */
    public void printBoard() {
        System.out.println();
        System.out.print("Draw Pile: ");
        topDeck.printDeck();
        System.out.println();
        System.out.println("Stacks");
        System.out.println();
        for (int i = 0; i < stacks.size(); i++) {
            Deck d = stacks.get(i);
            System.out.print((i + 1) + ": ");
            d.printDeck();
        }
        System.out.println();
        System.out.println("Finished: " + finished + "/" + numDecks + " stacks");
    }

    /**
     * Saves the a custom file with all the decks and stacks. 
     */
    public void save(){
        try{
            JFileChooser chooser = new JFileChooser(".");
            chooser.showSaveDialog(null);
            File file = chooser.getSelectedFile();
            if (file == null) {
                throw new FileNotFoundException();
            } else {
                FileWriter fw = new FileWriter(file);
                fw.write("Draw Pile\n");
                fw.write(topDeck.stringDeck() + "\n");
                fw.write("Stacks\n");
                fw.write(stacks.size() + "\n");
                for(int i = 0; i < stacks.size(); i++){
                    fw.write(stacks.get(i).stringDeck() + "\n");
                }
                fw.close();
            }
        } catch (FileNotFoundException e){
            System.out.println("No Such File!");
        } catch (IOException e){
            System.out.println("Invalid File!");
        }
    }

    /**
     * Uses a file to create a game with the exact same piles
     */
    public void load(){
        try {
            EventQueue.invokeAndWait(new Runnable() {
                @Override
                public void run() {
                    try{
                        JFileChooser chooser = new JFileChooser(".");
                        chooser.showOpenDialog(null);
                        File file = chooser.getSelectedFile();
                        if (file == null) {
                            throw new FileNotFoundException();
                        } else {
                            Scanner s = new Scanner(file);
                            s.nextLine();
                            topDeck = new Deck(s.nextLine());
                            topDeck.printDeck();
                            s.nextLine();
                            int num = s.nextInt();
                            s.nextLine();
                            int i = 0;
                            while(s.hasNextLine()){
                                Deck deck = new Deck(s.nextLine());
                                stacks.set(i, deck);
                                i++;
                            }
                        }
                    } catch (FileNotFoundException e){
                        System.out.println("No Such File!");
                    } catch (IOException e){
                        System.out.println("Invalid File!");
                    }
                }
            });
        }
        catch (InterruptedException e) {
		    System.out.println("Error: " + e.getMessage());
        }
        catch (InvocationTargetException e) {
            System.out.println("Error: " + e.getMessage());
        }


    }
}