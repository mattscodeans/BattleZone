import java.lang.reflect.Field;
import java.util.ArrayList;

/**
 * 
 *  Is crossbow with attack of 3 and health of 4. It moves only diagonal 1 step 
 *  and its attack range is 2.
 *  Contains methods such as move, attack, health, and removal methods. 
 *  Contains additional methods such as PieceType and PieceTwoToString.
 *
 *  @author  Ajeet, Matthew
 *  @version May 28, 2019
 *  @author  Period: 4th
 *  @author  Assignment: APCSFinalProject
 *
 *  @author  Sources: None
 */
public class PieceTwo extends GamePiece
{
    private int health = 4;

    private int attack = 3;

    /**
     * Constructs a pieceTwo piece
     * @param game - GameBaord piece is placed on
     * @param x - x-coordinate of the piece
     * @param y - y-coordinate of the piece
     * @param play - player piece belongs to
     */
    public PieceTwo( GameBoard game, int x, int y, String play )
    {
        super(game, x, y, play);
    }
    
    /**
     * Returns the max health of the piece.
     * @return health - max health of the piece.
     */
    public int getMaxHealth()
    {
        return 4;
    }
    
    /**
     * Returns the current health of the piece.
     * @return health - current health of the piece.
     */
    public int getHealth()
    {
        return health;
    }

    /**
     * Returns the attack damage the piece causes.
     * @return attack - damage the piece causes.
     */
    public int getAttack()
    {
        return attack;
    }

    /**
     * Sets the attack damage the piece causes to attack.
     * @param attack - new damage piece attack is set to.
     */
    public void setAttack( int attack )
    {
        this.attack = attack;
    }

    /**
     * Return the type of piece the piece is.
     * @return pieceType the piece is.
     */
    @Override
    public PieceType getPieceType() {
        return PieceType.TWO;
    }

    /**
     * Removes the piece from the gameBoard.
     */
    public void removePiece()
    {
        if ( health <= 0 )
        {
            if (getPlayer().equals( "PlayerOne" ))
            {
                getBoard().removePlayerOnePiece( this );
            }
            else if (getPlayer().equals( "PlayerOne" ))
            {
                getBoard().removePlayerOnePiece( this );
            }
            
            getBoard().getGame()[super.getLocation().getX()]
                            [super.getLocation().getY()] = null;
            
            
        }
    }

    /**
     * Reduces the health of the piece by h.
     * @param h - health piece is reduced by.
     */
    public void reduceHealth( int h )
    {
        health = health - h;
        removePiece();
    }

    /**
     * Returns the possible locations the piece can move to.
     * @return possible locations the piece can move to.
     */
    public ArrayList<Location> possibleMoves()
    {
        ArrayList<Location> a = new ArrayList<Location>();
        for ( int x = super.getLocation().getX() - 1;
                        x <= super.getLocation().getX() + 1; x++ )
        {
            for ( int y = super.getLocation().getY() - 1;
                            y <= super.getLocation().getY() + 1; y++ )
            {
                if ( (x >= 0 && x < this.getBoard().getGame()[0].length) && 
                                (y >= 0 && y < this.getBoard().getGame().length))
                {
                    if (x != super.getLocation().getX() 
                                    && y != super.getLocation().getY() )
                    {
                        a.add( this.getBoard().getLocation( x, y ) );
                    }
                }
            }
        }
        
        return a;
    }

    /**
     * Returns the possible locations the piece can attack.
     * @return possible locations the piece can attack.
     */
    public ArrayList<Location> possibleAttacks()
    {
        ArrayList<Location> a = new ArrayList<Location>();
        for ( int x = super.getLocation().getX() - 2;
                        x <= super.getLocation().getX() + 2; x++ )
        {
            for ( int y = super.getLocation().getY() - 2;
                            y <= super.getLocation().getY() + 2; y++ )
            {
                if ( (x >= 0 && x < this.getBoard().getGame()[0].length) && 
                                (y >= 0 && y < this.getBoard().getGame().length))
                {
                    if (x != super.getLocation().getX() || 
                                    y != super.getLocation().getY())
                    a.add( this.getBoard().getLocation( x, y ) );
                }
            }
        }
        return a;
    }
    
    /**
     * Returns a string of the piece type name.
     * @return piece type name of the piece.
     */
    public String toString()
    {
        /*String str = this.getClass().getName() + "[";
        String separator = "";

        Field[] fields = this.getClass().getDeclaredFields();

        for ( Field field : fields )
        {
            try
            {
                str += separator + field.getType().getName() + " "
                    + field.getName() + ":" + field.get( this );
            }
            catch ( IllegalAccessException ex )
            {
                System.out.println( ex );
            }

            separator = ", ";
        }
        
        return str + "]";
        */
        return "PieceTwo";
    }
}
