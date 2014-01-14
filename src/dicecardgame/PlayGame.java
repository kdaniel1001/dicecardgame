/**
 * 
 */
package dicecardgame;

import dicecardgame.ai.ComputeCardAB;
import dicecardgame.ai.ComputeCardFirstFound;
import dicecardgame.board.Board;
import dicecardgame.exceptions.InvalidBoard;
import dicecardgame.exceptions.InvalidCard;
import dicecardgame.fileIO.InputFile;
import dicecardgame.hand.Hand;

/**
 * @author mom
 * 
 *         Will play the sevens game based on a input.txt file that will display
 *         current hand and the four suits of already played cards. Will poll
 *         the directory to see if a new input.txt file has been inserted, read
 *         the file, compute a card to play and write output.txt file to the
 *         same directory.
 * 
 */
public class PlayGame
{
    /**
     * @param args
     */
    public static void main(String[] args) {
        // no args used so far.
        InputFile inFile = new InputFile("input.txt");

        try
        {
            Hand hand = new Hand(inFile.getHandString());
            Board board = new Board();
            board.addBoard(inFile.getSuits());
            ComputeCardAB cc = new ComputeCardFirstFound(hand, board);
            
        }
        catch (InvalidCard e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        catch (InvalidBoard e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

}
