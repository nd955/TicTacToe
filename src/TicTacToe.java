import java.util.Random;


public class TicTacToe implements BoardGame
{

    private final int NUM_ROWS;
    private final int NUM_COLS;
    final int NUM_TO_WIN;
    private Space[] spacePositions;
    private Player[] players;
    private char neutral;
    private Player currPlayer;
    private boolean gameEnded;
    private Player winner;
    
    public TicTacToe(int rows, int cols, int numToWin, Player[] allPlayers)
    {
        NUM_ROWS = rows;
        NUM_COLS = cols;
        if(numToWin <= Math.max(NUM_ROWS, NUM_COLS))
            NUM_TO_WIN = numToWin;
        else
            NUM_TO_WIN = Math.max(NUM_ROWS, NUM_COLS);
        players = allPlayers;
        players[0].setOrder(0);
        players[1].setOrder(1);
        neutral = ' ';
        gameEnded = false;
        winner = null;
        setup();
    }
    
    @Override
    public void setup()
    {
        spacePositions = new Space[NUM_ROWS * NUM_COLS];
        for(int i = 0; i < NUM_ROWS; i++)
        {
            for(int j = 0; j < NUM_COLS; j++)
            {
                spacePositions[(i * NUM_COLS) + j] = new Space((i * NUM_COLS) + j, this);
            }
        }
    }
    @Override
    public void startNewGame()
    {
        resetGame();
        currPlayer = players[0];
    }
    @Override
    public void resetGame()
    {
        for(int i = 0; i < NUM_ROWS; i++)
        {
            for(int j = 0; j < NUM_COLS; j++)
            {
                spacePositions[(i * NUM_COLS) + j].setMovePlayed(false);
                spacePositions[(i * NUM_COLS) + j].setPlayer(null);
            }
        }
    }
    @Override
    public Player[] randomizeOrder(Player[] unshuffledPlayers)
    {
        Random rand = new Random();
        Player[] shuffledPlayers = new Player[unshuffledPlayers.length];
        
        for(int i = 0; i < unshuffledPlayers.length; i++)
        {
            int randInt = rand.nextInt(unshuffledPlayers.length);
            while(shuffledPlayers[randInt] != null)
            {
                randInt = rand.nextInt(unshuffledPlayers.length);
            }
            shuffledPlayers[randInt] = unshuffledPlayers[i];
        }
        
        return shuffledPlayers;
    }
    @Override
    public boolean makeMove(int position)
    {
        if(this.validMove(position))
        {
            //Makes move
            Space space = spacePositions[position];
            space.setPlayer(currPlayer);
            space.setMovePlayed(true);
            if(currPlayer.checkWinningMove(this, space))
            {
                winner = currPlayer;
                gameEnded = true;
            }
                
            //Changes the player's move
            currPlayer = currPlayer.getNextPlayer(this);

            return space.movePlayed();
        }
        else
        {
            //Returns false if move is invalid
            return false;
        }
    }
    @Override
    public void undoMove(int position)
    {
        //Undo the move
        Space space = spacePositions[position];
        if(currPlayer.checkWinningMove(this, space))
        {
            winner = null;
            gameEnded = false;
        }
        space.setPlayer(null);
        space.setMovePlayed(false);
        
        //Changes the player's move
        currPlayer = currPlayer.getLastPlayer(this);
    }
    @Override
    public Player whoseMove()
    {
        return currPlayer;
    }
    @Override
    public int getNumToWin()
    {
        return NUM_TO_WIN;
    }
    @Override
    public boolean gameOver()
    {
        return gameEnded;
    }
    @Override
    public Player getWinner()
    {
        return winner;
    }
    @Override
    public double getBoardValue(Player player)
    {
        if(!gameOver())
            return 0;
        if(player == getWinner())
            return 1;
        else
            return -1;
    }
    @Override
    public int getNumRows()
    {
        return NUM_ROWS;
    }
    @Override
    public int getNumCols()
    {
        return NUM_COLS;
    }
    @Override
    public boolean validMove(int position)
    {
        boolean result = true;
        if(position >= NUM_ROWS * NUM_COLS || position < 0)
            result = false;
        if(spacePositions[position].movePlayed())
            result = false;
        return result;
    }
    @Override
    public Player[] getPlayerList()
    {
        return players;
    }
    @Override
    public Move[] getMoveList()
    {
        return spacePositions;
    }
    @Override
    public String toString()
    {
        String result = "";
        for(int i = 0; i < NUM_ROWS; i++)
        {
            for(int j = 0; j < NUM_COLS; j++)
            {
                if(j != NUM_COLS - 1)
                    result += (spacePositions[(i * NUM_COLS) + j] + "\t|\t");
                else
                    result += (spacePositions[(i * NUM_COLS) + j]);
            }
            result += "\r";
            for(int j = 0; j < NUM_COLS; j++)
            {
                if(i != NUM_ROWS - 1)
                    result += "_\t\t";
                else
                    break;
            }
            result += "\r";
        }
        return result;
    }

}
