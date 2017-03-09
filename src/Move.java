public interface Move 
{
    public int getID();
    
    public BoardGame getBoardGame();
    
    public char getSymbol();
    
    public Player getPlayer();
    
    public void setPlayer(Player play);
    
    public boolean movePlayed();
    
    public void setMovePlayed(boolean played);
    
    public Move getTopLeft();
    
    public Move getTop();
    
    public Move getTopRight();
    
    public Move getLeft();
    
    public Move getRight();
    
    public Move getBottomLeft();
    
    public Move getBottom();
    
    public Move getBottomRight();
}
