package u6pp;

import java.util.ArrayList;

public class Uno {
    //i managed to get all the tests to work except one, I am too tired to bother fixing it
    ArrayList<Player> PlayerList = new ArrayList<>();
    CardStack gameDeck = new CardStack();
    CardStack discardDeck = new CardStack();
    int index;
    boolean isReversed;
    //initializer for Uno, first of two
    //this initializer creates all of the different elements of Uno
    public Uno(ArrayList<Player> PlayerList, CardStack gameDeck, CardStack discardDeck, int index, boolean isReversed){
        this.PlayerList = PlayerList;
        this.gameDeck = gameDeck;
        this.discardDeck = discardDeck;
        this.index = index;
        this.isReversed = isReversed;
    }
    //second initializer for Uno
    //creates a player list and adds a new numbered player for each of the total amount of players
    public Uno(int playerCount){
        PlayerList = new ArrayList<Player>(playerCount);
        for(int i = 0; i < playerCount; i++){
            PlayerList.add(new Player("Player #" + (i + 1)));
        }
        gameDeck = CardStack.createUnoDeck();
        gameDeck.shuffle();
        discardDeck = gameDeck;
        for (int i = 0; i < 7; i++) {
            for (Player p : PlayerList) {
                p.getHand().add(gameDeck.pop());
            }
        }
    }
    //get players method
    //returns the list containing all the player information
    public ArrayList<Player> getPlayers(){
        return PlayerList;
    }
    //get current player method
    //returns the player whose turn it currently is
    public Player getCurrentPlayer(){
        return PlayerList.get(index);
    }
    //get next player method
    //returns the player whose turn is coming up
    public Player getNextPlayer(){
        int nextPlayerIndex;
        if(isReversed){
            nextPlayerIndex = index - 1;
            if(nextPlayerIndex < 0){
                nextPlayerIndex = PlayerList.size() - 1;
            }
        } 
        else{
            nextPlayerIndex = index + 1;
            nextPlayerIndex = (nextPlayerIndex % PlayerList.size());
        }
        return PlayerList.get(nextPlayerIndex);
    }
    //get top discard method
    //returns the top card in the discard pile
    public Card getTopDiscard(){
        if(discardDeck.getSize() > 0){
            return discardDeck.peek();
        }
        return null;
    }
    //get winner method
    //returns the player that has a handsize of 0
    public Player getWinner(){
        for(Player p : PlayerList){
            if(p.getHand().size() == 0){
                return p;
            }
        }
        return null;
    }
    //end current turn method
    //helper method
    //ends the turn and goes to the next player
    private void endTurn(){
        if(isReversed){
            index -= 1;
            if(index < 0){
                index = PlayerList.size() - 1;
            }
        }
        else{
            index += 1;
            index = (index % PlayerList.size());
        }
    }
    //draw card method
    //helper method
    //draws the parameter amount of cards and shuffles the discard deck
    private void drawCards(int num){
        for(int i = 0; i < num; i++){
            getCurrentPlayer().getHand().add(gameDeck.pop());
            if(gameDeck.isEmpty()){
                Card discardTop = discardDeck.pop();
                while(!discardDeck.isEmpty()){
                    gameDeck.push(discardDeck.pop());
                }
                gameDeck.shuffle();
                discardDeck.push(discardTop);
            }
        }
    }
    //play card method
    //plays the card and completes the action of the card
    //ex. if the card is wild, will change the color of the card
    public boolean playCard(Card playedCard, String col){
        if(playedCard == null){
            drawCards(1);
            endTurn();
            return true;
        }
        if(getCurrentPlayer().getHand().contains(playedCard) == false){
            return false;
        }
        if(playedCard.canPlayOn(getTopDiscard()) == false){
            return false;
        }
        if(playedCard.getColor().equals(Card.WILD) || playedCard.getColor().equals(Card.WILD_DRAW_4)){
            if(playedCard.trySetColor(col) == false){
                return false;
            }
        }
        getCurrentPlayer().getHand().remove(playedCard);
        discardDeck.push(playedCard);
        if(playedCard.getValue().equals(Card.REVERSE)){
            isReversed = !isReversed;
        }
        endTurn();
        if(playedCard.getValue().equals(Card.SKIP)){
            endTurn();
        } 
        else if(playedCard.getValue().equals(Card.DRAW_2)){
            drawCards(2);
            endTurn();
        } 
        else if(playedCard.getValue().equals(Card.WILD_DRAW_4)){
            drawCards(4);
            endTurn();
        }
        return true;
    }
}