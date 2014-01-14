package dicecardgame.board;

import java.util.ArrayList;
import java.util.Collection;

import dicecardgame.card.Card;
import dicecardgame.exceptions.InvalidBoard;
import dicecardgame.exceptions.InvalidCard;

public class Board
{
    // temp vars used to hold the string from the input file for each suit.
    private String clubs;
    private String hearts;
    private String diamonds;
    private String spades;

    // keep around for debugging. Remove before deployment.
    /**
     * @TODO: remove before deployment.
     */
    private ArrayList<String> suits = new ArrayList<String>();;

    // placer vars used to hold high/low card for suit.
    private Card lowClub;
    private Card highClub;
    private Card lowSpade;
    private Card highSpade;
    private Card lowDiamond;
    private Card highDiamond;
    private Card lowHeart;
    private Card highHeart;

    // temp cards
    private Card lowCard;
    private Card highCard;

    public Board()
    {

    }

    public Board(String[] board) throws InvalidBoard
    {
        addBoard(board);
    }

    /**
     * Takes in a string array of the suit lines in the input file.
     * 
     * @param board
     *            String[]
     * @throws InvalidBoard
     */
    public void addBoard(String[] board) throws InvalidBoard {
        if (board.length != 4)
            throw new InvalidBoard("Invalid number of lines for a valid board "
                    + board.toString());
        for (String line : board)
        {
            if (line.startsWith(Card.Suit.CLUBS.getString()))
            {
                if (checkOutBoardLine(line))
                {
                    clubs = line;
                    lowClub = lowCard;
                    highClub = highCard;
                    suits.add(clubs);
                }
            }
            else if (line.startsWith(Card.Suit.DIAMONDS.getString()))
            {
                if (checkOutBoardLine(line))
                {
                    diamonds = line;
                    lowDiamond = lowCard;
                    highDiamond = highCard;
                    suits.add(diamonds);
                }
            }
            else if (line.startsWith(Card.Suit.HEARTS.getString()))
            {
                if (checkOutBoardLine(line))
                {
                    hearts = line;
                    lowHeart = lowCard;
                    highHeart = highCard;
                    suits.add(hearts);

                }
            }
            else if (line.startsWith(Card.Suit.SPADES.getString()))
            {
                if (checkOutBoardLine(line))
                {
                    spades = line;
                    lowSpade = lowCard;
                    highSpade = highCard;
                    suits.add(spades);

                }
            }
        }
    }

    /**
     * Checks to see if a suit line is valid and has valid characters for the
     * line.
     * 
     * @param line
     * @return true if valid line detected.
     * @throws InvalidBoard
     */
    private boolean checkOutBoardLine(String line) throws InvalidBoard {
        boolean flag = false;
        try
        {
            if (line.length() == 1)
            {
                flag = true;
                lowCard = null;
                highCard = null;
            }
            else if (line.length() == 2 && line.endsWith("7"))
            {
                flag = true;
                lowCard = new Card(line.substring(1, 2) + line.substring(0, 1));
                highCard = new Card(line.substring(1, 2) + line.substring(0, 1));
            }
            else if (line.length() == 3)
            {
                String lCard = line.substring(1, 2) + line.substring(0, 1);

                lowCard = new Card(lCard);
                // other end card
                String hCard = line.substring(2, 3) + line.substring(0, 1);
                highCard = new Card(hCard);
                flag = true;
            }
            else
                throw new InvalidBoard("line is invalid: " + line);
        }
        catch (InvalidCard e)
        {
            throw new InvalidBoard("Invalid board configuration: " + line
                    + " invalid card: " + e.getMessage());
        }

        return flag;
    }

    public String getClubLine() {
        return clubs;
    }

    public String getSpadeLine() {
        return spades;
    }

    public String getHeartLine() {
        return hearts;
    }

    public String getDiamondLine() {
        return diamonds;
    }

    /**
     * Will compute all the possiable playable cards for this board.
     * 
     * Check for null. There might not be any playable cards for this board.
     * I.E. All the cards have been played.
     * 
     * @return ArrayList<Card>
     */
    public ArrayList<Card> getPlayableCards() {
        ArrayList<Card> cards = new ArrayList<Card>();
        try
        {

            if (clubs.length() == 1)
            {
                cards.add(new Card("7C"));
            }
            if (spades.length() == 1)
            {
                cards.add(new Card("7S"));
            }
            if (hearts.length() == 1)
            {
                cards.add(new Card("7H"));
            }
            if (diamonds.length() == 1)
            {
                cards.add(new Card("7D"));
            }
            if (clubs.length() == 2)
            {
                cards.add(new Card("6C"));
                cards.add(new Card("8C"));
            }
            if (spades.length() == 2)
            {
                cards.add(new Card("6S"));
                cards.add(new Card("8S"));
            }
            if (hearts.length() == 2)
            {
                cards.add(new Card("6H"));
                cards.add(new Card("8H"));
            }
            if (diamonds.length() == 2)
            {
                cards.add(new Card("6D"));
                cards.add(new Card("8D"));
            }
            if (clubs.length() == 3)
            {
                cards.addAll(getNextCards(lowClub, highClub));
            }
            if (spades.length() == 3)
            {
                cards.addAll(getNextCards(lowSpade, highSpade));
            }
            if (hearts.length() == 3)
            {
                cards.addAll(getNextCards(lowHeart, highHeart));
            }
            if (diamonds.length() == 3)
            {
                cards.addAll(getNextCards(lowDiamond, highDiamond));
            }
        }
        catch (InvalidCard e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return cards;
    }

    /**
     * Computes the next low/high card for a given suit. returns an array of
     * Cards.
     * 
     * @param lowCard
     * @param highCard
     * @return
     */
    private Collection<? extends Card> getNextCards(Card lowCard, Card highCard) {
        ArrayList<Card> c = new ArrayList<>();

        Card temp = Card.getNextLowest(lowCard);
        if (temp != null) c.add(temp);
        temp = Card.getNextHighest(highCard);
        if (temp != null) c.add(temp);
        return c;
    }

    public Card getLowClub() {
        return lowClub;
    }

    public Card getHighClub() {
        return highClub;
    }

    public Card getLowSpade() {
        return lowSpade;
    }

    public Card getHighSpade() {
        return highSpade;
    }

    public Card getLowDiamond() {
        return lowDiamond;
    }

    public Card getHighDiamond() {
        return highDiamond;
    }

    public Card getLowHeart() {
        return lowHeart;
    }

    public Card getHighHeart() {
        return highHeart;
    }

    public ArrayList<Card> getPlayedCards() {
        ArrayList<Card> playedCards = new ArrayList<>();

        if (lowClub != null && highClub != null)
            playedCards.addAll(computeSuitPlayedCards(lowClub, highClub));
        if (lowHeart != null && highHeart != null)
            playedCards.addAll(computeSuitPlayedCards(lowHeart, highHeart));
        if (lowDiamond != null && highDiamond != null)
            playedCards.addAll(computeSuitPlayedCards(lowDiamond, highDiamond));
        if (lowSpade != null && highSpade != null)
            playedCards.addAll(computeSuitPlayedCards(lowSpade, highSpade));

        return playedCards;
    }

    private Collection<? extends Card> computeSuitPlayedCards(Card low,
            Card high) {
        ArrayList<Card> playedCards = new ArrayList<>();
        playedCards.add(low);
        // if the low card is a 7 then there isn't a high card. they are both
        // the same.
        if (low.getRank() != 7)
        {
            Card temp = Card.getNextHighest(low);
            while (!temp.toString().equals(high.toString()))
            {
                playedCards.add(temp);
                temp = Card.getNextHighest(temp);
            }
            // add the high card
            playedCards.add(high);
        }
        return playedCards;
    }
}
