package u6pp;
import java.util.ArrayList;

public class Player {
    String myName;
    ArrayList<Card> myHand = new ArrayList<Card>();
    //initializer for the player, only name is needed
    public Player(String name){
        myName = name;
    }
    //basic getter and setter for name
    public void setName(String name){
        myName = name;
    }
    public String getName(){
        return myName;
    }
    //getter for the hand, not initialized earlier as all hands start off the same(empty)
    public ArrayList<Card> getHand(){
        return myHand;
    }
}
