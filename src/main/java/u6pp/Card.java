package u6pp;

public class Card {

    public static String RED = "RED";
    public static String GREEN = "GREEN";
    public static String BLUE = "BLUE";
    public static String YELLOW = "YELLOW";

    public static String ZERO = "0";
    public static String ONE = "1";
    public static String TWO = "2";
    public static String THREE = "3";
    public static String FOUR = "4";
    public static String FIVE = "5";
    public static String SIX = "6";
    public static String SEVEN = "7";
    public static String EIGHT = "8";
    public static String NINE = "9";

    public static String DRAW_2 = "DRAW_2";
    public static String REVERSE = "REVERSE";
    public static String SKIP = "SKIP";
    public static String WILD = "WILD";
    public static String WILD_DRAW_4 = "WILD_DRAW_4";

    // Wild color is the default color for wilds, before they are played. 
    public static String[] COLORS = {RED, GREEN, BLUE, YELLOW, WILD}; 
    public static String[] VALUES = {ZERO, ONE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, 
        DRAW_2, REVERSE, SKIP, WILD, WILD_DRAW_4};

    // start you code here
    String myColor;
    String myValue;
    //initializer for card
    public Card(String color, String value){
        myColor = color;
        myValue = value;
    }
    //basic getters and setters for card color and value
    public void setColor(String color){
        myColor = color;
    }
    public void setValue(String value){
        myValue = value;
    }
    public String getColor(){
        return myColor;
    }
    public String getValue(){
        return myValue;
    }
    //returns true if the card is wild and the parameter is one of the colors: red, green, blue, yellow
    public boolean trySetColor(String color){
        if(color == null){
            return false;
        }
        if(this.getColor().equals(WILD)){
            if(color.equals(RED) || color.equals(YELLOW) || color.equals(GREEN) || color.equals(BLUE)){
                myColor = color;
                return true;
            }
        }
        return false;
    }
    //returns true if the parameter is a card that can be played on
    //if the card is the same color or value as the parameter or a wild
    //also returns false if null
    public boolean canPlayOn(Card card){
        if(card == null){
            return false;
        }
        if(this.getColor() == WILD){
            return true;
        }
        if(this.getColor() == card.getColor() || this.getValue() == card.getValue()){
            return true;
        }
        else{
            return false;
        }
    }
    
}
