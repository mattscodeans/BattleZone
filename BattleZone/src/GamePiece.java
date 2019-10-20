import java.util.ArrayList;

/**
 * 
 *  Is general piece with location, gameboard, and player string.
 *  Contains methods such as location, gameboard, and player methods. 
 *  Contains abstract methods such as move, attack, health, and removal methods.
 *
 *  @author  Ajeet, Matthew
 *  @version May 28, 2019
 *  @author  Period: 4th
 *  @author  Assignment: APCSFinalProject
 *
 *  @author  Sources: None
 */
public abstract class GamePiece implements Piece
{
    private Location location;

    private GameBoard myGame;

    private String p;

    //private Contact c = null;
    
    /**
     * Constructs a general game piece
     * @param game - GameBaord piece is placed on
     * @param x - x-coordinate of the piece
     * @param y - y-coordinate of the piece
     * @param play - player piece belongs to
     */
    public GamePiece(GameBoard game, int x, int y, String play )
    {
        location = new Location( x, y );
        myGame = game;
        p = play;
        game.getGame()[x][y] = this;
    }
    
    //public boolean hasContact()
    //{
        //return !(c == null);
    //}
    
    //public void setContact( Contact c )
    //{
        //this.c = c;
    //}
    
    //public Contact getContact()
    //{
        //return c;
    //}
    
    /**
     * Returns the max health of the piece.
     * @return health - max health of the piece.
     */
    public abstract int getMaxHealth();
    
    /**
     * Return the location of the piece.
     * @return location of the piece.
     */
    public Location getLocation()
    {
        return location;
    }
    
    /**
     * Sets the location of the piece.
     * @param location of the piece.
     */
    public void setLocation( Location l )
    {
        location = l;
    }

    /**
     * Returns the player that owns the piece.
     * @return player that owns the piece.
     */
    public String getPlayer()
    {
        return p;
    }

    /**
     * Returns the board piece belongs to.
     * @return board piece belongs to.
     */
    public GameBoard getBoard()
    {
        return myGame;
    }

    /**
     * Returns the current health of the piece.
     * @return health - current health of the piece.
     */
    public abstract int getHealth();

    /**
     * Returns the attack damage the piece causes.
     * @return attack - damage the piece causes.
     */
    public abstract int getAttack();

    /**
     * Sets the attack damage the piece causes to attack.
     * @param attack - new damage piece attack is set to.
     */
    public abstract void setAttack( int attack );

    /**
     * Removes the piece from the gameBoard.
     */
    public abstract void removePiece();

    /**
     * Reduces the health of the piece by h.
     * @param h - health piece is reduced by.
     */
    public abstract void reduceHealth( int h );

    /**
     * Returns the possible locations the piece can move to.
     * @return possible locations the piece can move to.
     */
    public abstract ArrayList<Location> possibleMoves();

    /**
     * Returns the possible locations the piece can attack.
     * @return possible locations the piece can attack.
     */
    public abstract ArrayList<Location> possibleAttacks();
    
    /**
     * Returns whether piece can attack or not
     * @param attacked - piece that is attacked
     * @return whether piece can attack or not
     */
    public boolean attack( Piece attacked )
    {
        /*
         * if (this.hasContact()) { this.getContact().contactAttack(); return
         * true; }
         */
        ArrayList<Location> a = this.possibleAttacks();
        for ( int i = 0; i < a.size(); i++ )
        {
            if ( a.get( i ).getX() == attacked.getLocation().getX()
                && a.get( i ).getY() == attacked.getLocation().getY() )
            {
                attacked.reduceHealth( this.getAttack() );
                return true;
            }
        }
        return false;
    }

    /**
     * 
     * Determines whether piece can move to new Location.
     * @param newL - new Location piece moves to.
     * @return whether piece can move to new Location.
     */
    public boolean move( Location newL )
    {
        /*
         * if (this.hasContact()) { return false; }
         */
        ArrayList<Location> a = this.possibleMoves();
        for ( int i = 0; i < a.size(); i++ )
        {
            if ( a.get( i ).getX() == newL.getX() && a.get( i ).getY() == newL.getY() )
            {
                boolean x = myGame.setGameBoard( this, newL );
                this.setLocation( newL );
                if ( !x )
                {
                    return false;
                }
                return true;
            }
        }
        return false;
    }

}