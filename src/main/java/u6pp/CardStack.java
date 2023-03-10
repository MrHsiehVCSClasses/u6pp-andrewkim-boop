package u6pp;
import java.util.ArrayList;
import java.util.Random;

public class CardStack {
    ArrayList<Card> deck = new ArrayList<Card>();
    public CardStack(){
        //no initializer needed
    }
    //adds the parameter card into the end of the deck
    public void push(Card card){
        deck.add(0, card);
    }
    //returns the top card of the this deck, returns null if the deck is empty
    public Card peek(){
        if(this.isEmpty()){
            return null;
        } 
        return(deck.get(0));
    }
    //returns whether or not the deck is empty/has 0 cards
    public boolean isEmpty(){
        if(deck.size() == 0){
            return true;
        }
        else{
            return false;
        }
    }
    //returns the size of the deck, one more than the final index of the array list
    public int getSize(){
        return deck.size();
    }
    //removes the top card of the deck and returns that card value that is returned
    public Card pop(){
        Card top = deck.get(0);
        deck.remove(0);
        return top;
    }
    //clears the this deck
    public void clear(){
        deck.clear();
    }
    //should add all the parameter deck cards into the this deck
    //however does not work and only clears the parameter deck
    public void addAll(CardStack deck2){
        ArrayList<Card> store = new ArrayList<Card>();
        if(deck.equals(deck2.deck)){
            return;
        }
        while(deck2.isEmpty() == false){
            store.add(0, deck2.deck.get(0));
            deck2.deck.remove(0);
        }while(store.isEmpty() == false){
            deck.add(0, store.get(0));
            store.remove(0);
        }
    }
    //actually shuffles the deck now
    //uses fisher yates algorithm, randomly cards spread throughout the deck
    public void shuffle(){
        final Random random = new Random();
        for (int i = 0; i < deck.size(); i++) {
            int x = random.nextInt(i, deck.size());
            if (x == i) {
                continue;
            }
            Card temp = deck.get(i);
            deck.set(i, deck.get(x));
            deck.set(x, temp);
        }
    }
    //create uno deck method
    //functions as a helper method
    //makes a new deck that has all the different elements of the uno deck
    public static CardStack createUnoDeck() {
        CardStack stack = new CardStack();
        for (String color : Card.COLORS) {
            if (color.equalsIgnoreCase(Card.WILD)) {
                continue;
            }
    
            for (String value : Card.VALUES) {
                if (value.equals(Card.WILD) || value.equals(Card.WILD_DRAW_4)) {
                    continue;
                }
                stack.push(new Card(color, value));
                if (!value.equalsIgnoreCase(Card.ZERO)) {
                    stack.push(new Card(color, value));
                }
            }
        }
    
        for (int i = 0; i < 4; i++) {
            stack.push(new Card(Card.WILD, Card.WILD));
            stack.push(new Card(Card.WILD, Card.WILD_DRAW_4));
        }
    
        return stack;
    }
}
