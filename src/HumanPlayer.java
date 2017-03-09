import java.util.Scanner;


public class HumanPlayer implements Player
{
    private char color;
    private int order;

    public HumanPlayer(char playerColor)
    {
        color = playerColor;
    }

    @Override
    public void makeBestMove(BoardGame game, int level, double alpha, double beta, int opponents)
    {
        System.out.println("Select a move.");
        int choice = getInput();
        while(!game.validMove(choice))
        {
            System.out.println("Not a valid move, select again.");
            choice = getInput();
        }
        game.makeMove(choice);
    }
    private int getInput()
    { 
        @SuppressWarnings("resource")
        Scanner scan = new Scanner(System.in);
        int result;
        while(!scan.hasNextInt())
        {
            System.out.println("Not an integer, select again.");
            scan.next();
        }
        result = scan.nextInt();
        return result;
    }
    @Override
    public double maxValue(BoardGame game, int level, double alpha, double beta, int opponents)
    {
        // Unnecessary for human input
        return 0;
    }
    @Override
    public double minValue(BoardGame game, int level, double alpha, double beta, int opponents)
    {
        // Unnecessary for human input
        return 0;
    }
    @Override
    public boolean checkWinningMove(BoardGame game, Move move)
    {
        boolean won = false;

        if(move != null)
        {
            boolean movingLeft = true;
            boolean movingUp = true;
            boolean movingUpLeft = true;
            boolean movingDownLeft = true;

            int countLeftRight = 1;
            int countUpDown = 1;
            int countDiagUpLeft = 1;
            int countDiagDownLeft = 1;

            Move tempMoveLeftRight = move;
            Move tempMoveUpDown = move;
            Move tempMoveDiagUpLeft = move;
            Move tempMoveDiagDownLeft = move;

            for(int i = 0; i < 2 * game.getNumToWin(); i++)
            {
                if(movingLeft)
                {
                    if(tempMoveLeftRight.getLeft() != null && tempMoveLeftRight.getLeft().getPlayer() == this)
                    {
                        tempMoveLeftRight = tempMoveLeftRight.getLeft();
                        countLeftRight++;
                    }
                    else
                    {
                        movingLeft = false;
                        tempMoveLeftRight = move;
                    }
                }
                else
                {
                    if(tempMoveLeftRight.getRight() != null && tempMoveLeftRight.getRight().getPlayer() == this)
                    {
                        tempMoveLeftRight = tempMoveLeftRight.getRight();
                        countLeftRight++;
                    }
                    else
                    {
                        /*
                         * Wait for other directions to be checked
                         */
                    }
                }

                if(movingUp)
                {
                    if(tempMoveUpDown.getTop() != null && tempMoveUpDown.getTop().getPlayer() == this)
                    {
                        tempMoveUpDown = tempMoveUpDown.getTop();
                        countUpDown++;
                    }
                    else
                    {
                        movingUp = false;
                        tempMoveUpDown = move;
                    }
                }
                else
                {
                    if(tempMoveUpDown.getBottom() != null && tempMoveUpDown.getBottom().getPlayer() == this)
                    {
                        tempMoveUpDown = tempMoveUpDown.getBottom();
                        countUpDown++;
                    }
                    else
                    {
                        /*
                         * Wait for other directions to be checked
                         */
                    }
                }

                if(movingUpLeft)
                {
                    if(tempMoveDiagUpLeft.getTopLeft() != null && tempMoveDiagUpLeft.getTopLeft().getPlayer() == this)
                    {
                        tempMoveDiagUpLeft = tempMoveDiagUpLeft.getTopLeft();
                        countDiagUpLeft++;
                    }
                    else
                    {
                        movingUpLeft = false;
                        tempMoveDiagUpLeft = move;
                    }
                }
                else
                {
                    if(tempMoveDiagUpLeft.getBottomRight() != null && tempMoveDiagUpLeft.getBottomRight().getPlayer() == this)
                    {
                        tempMoveDiagUpLeft = tempMoveDiagUpLeft.getBottomRight();
                        countDiagUpLeft++;
                    }
                    else
                    {
                        /*
                         * Wait for other directions to be checked
                         */
                    }
                }

                if(movingDownLeft)
                {
                    if(tempMoveDiagDownLeft.getBottomLeft() != null && tempMoveDiagDownLeft.getBottomLeft().getPlayer() == this)
                    {
                        tempMoveDiagDownLeft = tempMoveDiagDownLeft.getBottomLeft();
                        countDiagDownLeft++;
                    }
                    else
                    {
                        movingDownLeft = false;
                        tempMoveDiagDownLeft = move;
                    }
                }
                else
                {
                    if(tempMoveDiagDownLeft.getTopRight() != null && tempMoveDiagDownLeft.getTopRight().getPlayer() == this)
                    {
                        tempMoveDiagDownLeft = tempMoveDiagDownLeft.getTopRight();
                        countDiagDownLeft++;
                    }
                    else
                    {
                        /*
                         * Wait for other directions to be checked
                         */
                    }
                }

                if(countLeftRight >= game.getNumToWin() || countUpDown >= game.getNumToWin() 
                || countDiagUpLeft >= game.getNumToWin() || countDiagDownLeft >= game.getNumToWin())
                {
                    won = true;
                    break;
                }
            }
        }
        return won;
    }
    @Override
    public Player getNextPlayer(BoardGame game)
    {
        if(order == game.getPlayerList().length - 1)
        {
            return game.getPlayerList()[0];
        }
        else 
        {
            return game.getPlayerList()[order + 1];
        }
    }
    @Override
    public Player getLastPlayer(BoardGame game)
    {
        if(order == 0)
        {
            return game.getPlayerList()[game.getPlayerList().length - 1];
        }
        else 
        {
            return game.getPlayerList()[order - 1];
        }
    }
    @Override
    public void setOrder(int position)
    {
        order = position;
    }
    @Override
    public char getColor()
    {
        return color;
    }
    @Override
    public String toString()
    {
        return Character.toString(color);
    }
}
