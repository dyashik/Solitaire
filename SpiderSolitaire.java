import java.util.InputMismatchException;
import java.util.Scanner;
/** 
 * Author: Yashik Dhanaraj
 * Period: 5
 * Date: 12/12
 * Time Taken: 3hr
 * 
 * Summary: For me, the hardest part was converting the deck to a string
 * It wasn't really the actual conversion that threw me off but it was the
 * instructions given in the video. Because it said that there would be a token
 * instead of the full line but in the directions it said to use the full line. 
 * I was able to clear my doubt and did it. It was very similar to codingBat problems
 * so it was relatively straightforward. Then, I had to use the mac try clatch clause
 * for it to work and that took some time because it didn't allow me to use the dialog 
 * box twice in a row
 * 
 * **/
public class SpiderSolitaire {
    /** Number of stacks on the board **/
    public final int NUM_STACKS = 10;

    /** Number of complete decks used in this game.  A 1-suit deck, which is the
     *  default for this lab, consists of 13 cards (Ace through King).
     */
    public final int NUM_DECKS = 4;

    /** A Board contains stacks and a draw pile **/
    private Board board;

    /** Used for keyboard input **/
    private Scanner input;

    public SpiderSolitaire() {
        // Start a new game with NUM_STACKS stacks and NUM_DECKS of cards
        board = new Board(NUM_STACKS, NUM_DECKS);
        input = new Scanner(System.in);
    }

    /** Main game loop that plays games until user wins or quits **/
    public void play() {
        board.printBoard();
        boolean gameOver = false;
        boolean error = false;
        while(!gameOver) {
            System.out.println("\nCommands:");
            System.out.println("   move [card] [source_stack] [destination_stack]");
            System.out.println("   draw");
            System.out.println("   clear [source_stack]");
            System.out.println("   restart");
            System.out.println("   save");
            System.out.println("   load");
            System.out.println("   quit");
            System.out.print("> ");
            String command = input.next().toLowerCase();
            if (command.equals("move")) {
                try {
                    String symbol = input.next();
                    int sourceStack = input.nextInt();
                    if(sourceStack <= 0 || sourceStack > NUM_STACKS){
                        throw new InputMismatchException();
                    }
                    int destinationStack = input.nextInt();
                    if(destinationStack <= 0 || destinationStack > NUM_STACKS){
                        throw new InputMismatchException();
                    }
                    board.makeMove(symbol, sourceStack - 1, destinationStack - 1);
                } catch (InputMismatchException e) {
                    System.out.println("Invalid Move. Try Again");
                    error = true;
                } 
            }
            else if (command.equals("draw")) {
                board.drawCards();
            }
            else if (command.equals("clear")) {
                try {
                    int sourceStack = input.nextInt();
                    if(sourceStack < 0 || sourceStack > NUM_STACKS){
                        throw new InputMismatchException();
                    }
                    board.clear(sourceStack - 1);
                } catch (InputMismatchException e) {
                    System.out.println("Invalid clear command");
                    error = true;
                }
                
                
            }
            else if (command.equals("restart")) {
                board = new Board(NUM_STACKS, NUM_DECKS);
            }else if(command.equals("save")){
                board.save();
            }else if(command.equals("load")){
                board.load();
            }
            else if (command.equals("quit")) {
                System.out.println("Goodbye!");
                System.exit(0);
            }else{
                if(!error){
                    System.out.println("Invalid Command");
                }else{
                    error = false;
                }
            }
            board.printBoard();
            // If all stacks and the draw pile are clear, you win!
            if (board.isEmpty()) {
                gameOver = true;
                break;
            }
        }
        System.out.println("Congratulations!  You win!");
    }
}
