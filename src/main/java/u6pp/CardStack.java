package u6pp;
import java.util.ArrayList;

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
        if(deck2.isEmpty()){
            return;
        }
        for(int i = 0; i < deck2.getSize() - 1; i++) {
            deck.add(deck2.pop());
        }
        deck2.clear();
    }
    //should shuffle the deck, however i did not bother 
    public void shuffle(){

    }
}
