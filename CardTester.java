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
