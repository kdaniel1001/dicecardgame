package dicecardgame.fileIO;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.StringTokenizer;

import dicecardgame.board.Board;
import dicecardgame.card.Card;
import dicecardgame.exceptions.InvalidBoard;
import dicecardgame.exceptions.InvalidCard;
import dicecardgame.hand.Hand;

/**
 * @author mom
 * 
 *         Input file sent from server. This file should only have 5 lines.
 *         First line is our hand, the next for lines have the suits and cards
 *         played for each suit.
 */
public class InputFile
{

    Path inputFile;
    String handString;
    String[] suitString;

    /**
     * InputFile will crate a java Path for reading the file.
     * @param string
     */
    public InputFile(String string)
    {
        inputFile = Paths.get(string);
    }

    public boolean exists() {
        return Files.exists(inputFile);
    }

    /**
     * Checks the input file is valid and has only 4 suits and  the cards in your hand are valid cards.
     * @return
     * @throws IOException
     * @throws InvalidCard 
     * @throws InvalidBoard 
     */
    public boolean isValid() throws IOException, InvalidCard, InvalidBoard {
        Boolean flag = Files.isReadable(inputFile);
        if (flag) flag = Files.isDirectory(inputFile);
        if (!flag)
        {
            byte[] temp = Files.readAllBytes(inputFile);
            String record = new String(temp, "ISO-8859-1");
            StringTokenizer st = new StringTokenizer(record,
                    System.lineSeparator());
            if (st.countTokens() == 5)
            {
                flag = true;
                handString = st.nextToken().trim();

                int lines = st.countTokens();
                suitString = new String[lines];
                for (int i = 0; i < lines; i++)
                    suitString[i] = st.nextToken().trim();
                Hand hand = new Hand(handString);
                Board board = new Board(suitString);
                
                try
                {
                    ArrayList<Card> playableCards = board.getPlayedCards();
                    ArrayList<Card> handCards = hand.getCards();
                    for (Card handCard : handCards)
                    {
                        for (Card playCard : playableCards)
                        {
                            if (playCard.toString().equals(handCard.toString()))
                            {
                                flag = false;
                                break;
                            }
                        }
                    }
                }
                catch (Exception e)
                {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
        return flag;
    }

    public String getHandString() {
        return handString;
    }

    public String[] getSuits() {
        return suitString;
    }
}
