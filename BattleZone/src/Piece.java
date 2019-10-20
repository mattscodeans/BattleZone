import java.util.ArrayList;
/**
 * 
 *  Is interface of piece containing many uninitialized methods.
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
public interface Piece
{
//    boolean hasContact();
//    void setContact(Contact c);
//    Contact getContact();
    /**
     * Return the location of the piece.
     * @return location of the piece.
     */
    Location getLocation();
    
    /**
     * Sets the location of the piece.
     * @param location of the piece.
     */
    void setLocation(Location l);
    
    /**
     * Returns the player that owns the piece.
     * @return player that owns the piece.
     */
    String getPlayer();
    
    /**
     * Returns the board piece belongs to.
     * @return board piece belongs to.
     */
    GameBoard getBoard();
    
    /**
     * Returns the current health of the piece.
     * @return health - current health of the piece.
     */
    int getHealth();
    
    /**
     * Returns the attack damage the piece causes.
     * @return attack - damage the piece causes.
     */
    int getAttack();
    
    /**
     * Sets the attack damage the piece causes to attack.
     * @param attack - new damage piece attack is set to.
     */
    void setAttack(int attack);
    
    /**
     * Return the type of piece the piece is.
     * @return pieceType the piece is.
     */
    public abstract PieceType getPieceType();
    
    /**
     * Removes the piece from the gameBoard.
     */
    void removePiece();
    
    /**
     * Reduces the health of the piece by h.
     * @param h - health piece is reduced by.
     */
    void reduceHealth(int h);
    
    /**
     * Returns the possible locations the piece can move to.
     * @return possible locations the piece can move to.
     */
    ArrayList<Location> possibleMoves();
    
    /**
     * Returns the possible locations the piece can attack.
     * @return possible locations the piece can attack.
     */
    ArrayList<Location> possibleAttacks();
}
