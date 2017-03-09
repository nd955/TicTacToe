
public class Space implements Move
{
    
    private boolean movePlayed;
    private Player owner;
    private int id;
    private BoardGame board;
    private boolean rewardEarned;
    private char symbol;
    
    private Space topLeft;
    private Space top;
    private Space topRight;
    private Space left;
    private Space right;
    private Space bottomLeft;
    private Space bottom;
    private Space bottomRight;

    public Space(int numID, BoardGame game)
    {
        movePlayed = false;
        owner = null;
        symbol = ' ';
        id = numID;
        board = game;
        rewardEarned = false;
        
        topLeft = null;
        top = null;
        topRight = null;
        left = null;
        right = null;
        bottomLeft = null;
        bottom = null;
        bottomRight = null;
    }
    
    @Override
    public int getID()
    {
        return id;
    }
    @Override
    public BoardGame getBoardGame()
    {
        return board;
    }
    @Override
    public Player getPlayer()
    {
        return owner;
    }
    @Override
    public void setPlayer(Player play)
    {
        owner = play;
        if(play != null)
            symbol = owner.getColor();
        else
            symbol = ' ';
    }
    @Override
    public boolean movePlayed()
    {
       return movePlayed;
    }
    @Override
    public void setMovePlayed(boolean played)
    {
        movePlayed = played;
    }
    @Override
    public char getSymbol()
    {
        return symbol;
    }
    @Override
    public Space getTopLeft()
    {
        return topLeft;
    }
    @Override
    public Space getTop()
    {
        return top;
    }
    @Override
    public Space getTopRight()
    {
        return topRight;
    }
    @Override
    public Space getLeft()
    {
        return left;
    }
    @Override
    public Space getRight()
    {
        return right;
    }
    @Override
    public Space getBottomLeft()
    {
        return bottomLeft;
    }
    @Override
    public Space getBottom()
    {
        return bottom;
    }
    @Override
    public Space getBottomRight()
    {
        return bottomRight;
    }
    @Override
    public String toString()
    {
        if(movePlayed)
            return Character.toString(symbol);
        else
            return Integer.toString(id);
    }

}
