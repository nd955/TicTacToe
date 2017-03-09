import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;


public class GameMain
{

    public static void main(String[] args) throws IOException
    {
        //TODO: allow the user to input numPlayers, which types of players/char, numRows, numCols, numRuns,
        //numTurnsAhead, show board
        //Scanner scan = new Scanner(System.in);
        AlphaBeta aPlayer = new AlphaBeta('a');
        AlphaBeta bPlayer = new AlphaBeta('b');
        //ReinforcementLearner rPlayer = new ReinforcementLearner('r');
        HumanPlayer hPlayer = new HumanPlayer('h');
        Player[] players = {hPlayer, aPlayer};

        TicTacToe ttt = new TicTacToe(3, 3, 3, players);
        
        File weightData = new File("src/Weights.data");
        int turnsAhead = 4;
        int depth = turnsAhead * players.length;
        int numRuns = 1;
        boolean showingGame = true;
        
        //rPlayer.updateFeatures(dots);
       // rPlayer.initializeWeights(weightData);

        for(int i = 0; i < numRuns; i++)
        {
            if(showingGame)
                System.out.println(ttt);
            ttt.startNewGame();
            while(!ttt.gameOver())
            {
                for(Player player : ttt.getPlayerList())
                {
                    player.makeBestMove(ttt, depth, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY, players.length - 1);
                    if(showingGame)
                        System.out.println(ttt);
                }
            }
            //rPlayer.writeWeights(weightData);   
        }
    }
    
}
