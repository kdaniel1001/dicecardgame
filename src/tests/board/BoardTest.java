package tests.board;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import dicecardgame.board.Board;
import dicecardgame.card.Card;
import dicecardgame.exceptions.InvalidBoard;
import dicecardgame.exceptions.InvalidCard;

/**
 * @author mom The method will test the board object for completeness and
 *         functionality.
 * 
 */
public class BoardTest
{
    /**
     * First test of the board class. This will insert a valid board and verfiy
     * that the cards are valid.
     */
    @Test
    public void testBoard() {
        Board board = new Board();
        String[] b = new String[] { "C", "D7", "S59", "H2K" };
        try
        {
            board.addBoard(b);
            assertEquals(board.getClubLine(), "C");
            assertNull(board.getLowClub());
            assertNull(board.getHighClub());
            assertEquals(board.getSpadeLine(), "S59");
            assertEquals(board.getLowSpade().toString(), "5S");
            assertEquals(board.getHighSpade().toString(), "9S");

            assertEquals(board.getHeartLine(), "H2K");
            assertEquals(board.getLowHeart().toString(), "2H");
            assertEquals(board.getHighHeart().toString(), "KH");

            assertEquals(board.getDiamondLine(), "D7");
            assertEquals(board.getLowDiamond().toString(), "7D");
            assertEquals(board.getHighDiamond().toString(), "7D");
        }
        catch (InvalidBoard e)
        {
            fail(e.getMessage());
        }
        b = new String[] { "C", "D7", "S59", "H2K", "K34" };
        try
        {
            board.addBoard(b);
            fail("Should not be a valid board");
        }
        catch (InvalidBoard e)
        {
            // error is expected;
        }
    }

    /**
     * input a valid board and compute the valid cards that can be played next
     * for this board.
     */
    @Test
    public void testGetPlayableCards() {
        Board board = new Board();
        String[] b = new String[] { "C", "D7", "S59", "H2K" };
        ArrayList<Card> cards = new ArrayList<Card>();
        try
        {
            cards.add(new Card("7C"));
            cards.add(new Card("6D"));
            cards.add(new Card("8D"));
            cards.add(new Card("4S"));
            cards.add(new Card("TS"));
            cards.add(new Card("AH"));
            board.addBoard(b);
            assertEquals(board.getPlayableCards().toString(), cards.toString());
        }
        catch (InvalidCard e)
        {
            fail("Invalid card: " + e.getMessage());
        }
        catch (InvalidBoard e)
        {
            fail("Invalid Board: " + e.getMessage());
        }

        // test the other constructor
        try
        {
            board = new Board(b);
            cards = new ArrayList<Card>();
            cards.add(new Card("7C"));
            cards.add(new Card("6D"));
            cards.add(new Card("8D"));
            cards.add(new Card("4S"));
            cards.add(new Card("TS"));
            cards.add(new Card("AH"));
            board.addBoard(b);
            assertEquals(board.getPlayableCards().toString(), cards.toString());
        }
        catch (InvalidCard e)
        {
            fail("Invalid card: " + e.getMessage());
        }
        catch (InvalidBoard e)
        {
            fail("Invalid Board: " + e.getMessage());
        }

    }

    @Test
    public void testPlayedCards() {
        Board board = new Board();
        String[] b = new String[] { "C", "D7", "S59", "H2K" };
        try
        {
            board.addBoard(b);
            ArrayList<Card> playedCards = board.getPlayedCards();
            System.out.println("played cards: " + playedCards.toString());
            assertEquals(playedCards.toString(),
                    "[2H, 3H, 4H, 5H, 6H, 7H, 8H, 9H, TH, JH, QH, KH, 7D, 5S, 6S, 7S, 8S, 9S]");
        }
        catch (InvalidBoard e)
        {
            fail("Invalid board " + e.getMessage());
        }

    }
}
