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
public class CardTester {
    public static void main(String[] args) {
        Card card = new Card(7, "Hearts");
        Card card2 = new Card(10, "Spades");
        System.out.println(card.getValue());
        System.out.println(card.getSymbol());
        card.setFaceUp(true);
        System.out.println(card.equals(card2));
        System.out.println(card.toString());
        System.out.println(card.compareTo(card2));
    }
}