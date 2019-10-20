import org.junit.*;
import static org.junit.Assert.*;

import java.util.ArrayList;

import junit.framework.JUnit4TestAdapter;

public class JUnitTestBattleZone
{ 
    private int x = 0;
    private int y = 0;
    private String play = "Ted";
    
    
    @Test
    public void GameBoardConstructor()
    {
        GameBoard to = new GameBoard();
        assertNotNull(to);
        
    }
    
    @Test
    public void GameBoardGetGame()
    {
        GamePiece[][] go = new GamePiece[8][8];
        GameBoard to = new GameBoard();
        assertEquals( "<< GameBoard: " + "game" + " should be "
                        + go + " >>", go, to.getGame() );
    }
    
    @Test
    public void GameBoardGetLocation()
    {
        GameBoard to = new GameBoard();
        Location act = to.getLocation( 2, 5 );
        assertEquals( "<< GameBoard: " + "getLocationX" + " should be "
                        + 2 + " >>", 2, act.getX() );
        assertEquals( "<< GameBoard: " + "getLocationY" + " should be "
                        + 5 + " >>", 5, act.getY() );
    }
    
    @Test
    public void GameBoardFillGame()
    {
        GameBoard to = new GameBoard();
        
        GamePiece[][] go = new GamePiece[8][8];
        
        go[0][0] = new PieceThree(to, 0, 0, "playerTwo");
        go[0][2] = new PieceThree(to, 0, 2, "playerTwo");
        go[0][4] = new PieceThree(to, 0, 4, "playerTwo");
        go[0][6] = new PieceThree(to, 0, 6, "playerTwo");
        
        go[1][0] = new PieceTwo(to, 1, 0, "playerTwo");
        go[1][2] = new PieceTwo(to, 1, 2, "playerTwo");
        go[1][4] = new PieceTwo(to, 1, 4, "playerTwo");
        go[1][6] = new PieceTwo(to, 1, 6, "playerTwo");
        
        go[2][0] = new PieceOne(to, 2, 0, "playerTwo");
        go[2][2] = new PieceOne(to, 2, 2, "playerTwo");
        go[2][4] = new PieceOne(to, 2, 4, "playerTwo");
        go[2][6] = new PieceOne(to, 2, 6, "playerTwo");
        
        
        go[5][1] = new PieceOne(to, 5, 1, "playerOne");
        go[5][3] = new PieceOne(to, 5, 3, "playerOne");
        go[5][5] = new PieceOne(to, 5, 5, "playerOne");
        go[5][7] = new PieceOne(to, 5, 7, "playerOne");
        
        go[6][1] = new PieceTwo(to, 6, 1, "playerOne");
        go[6][3] = new PieceTwo(to, 6, 3, "playerOne");
        go[6][5] = new PieceTwo(to, 6, 5, "playerOne");
        go[6][7] = new PieceTwo(to, 6, 7, "playerOne");
        
        go[7][1] = new PieceThree(to, 7, 1, "playerOne");
        go[7][3] = new PieceThree(to, 7, 3, "playerOne");
        go[7][5] = new PieceThree(to, 7, 5, "playerOne");
        go[7][7] = new PieceThree(to, 7, 7, "playerOne");
        
        to.fillGameBoard( "playerOne", "playerTwo" );
        assertEquals("<< GameBoard: position 0 0 should be same >>", to.getGame()[0][0].getHealth() == 
        		go[0][0].getHealth(), true);
        assertEquals("<< GameBoard: position 0 2 should be same >>", to.getGame()[0][2].getHealth() == 
        		go[0][2].getHealth(), true);
        assertEquals("<< GameBoard: position 5 4 should be same >>", to.getGame()[5][1].getHealth() == 
        		go[5][1].getHealth(), true);
        assertEquals("<< GameBoard: position 7 6 should be same >>", to.getGame()[7][7].getHealth() == 
        		go[7][7].getHealth(), true);
    }
    
    @Test
    public void GameBoardGetPlayerOnePieces()
    {
        GameBoard to = new GameBoard();
        to.fillGameBoard( "playerOne", "playerTwo" );
        
        ArrayList<GamePiece> plOne = new ArrayList<GamePiece>();
        
        plOne.add( to.getGame()[7][0] );
        plOne.add( to.getGame()[7][2] );
        plOne.add( to.getGame()[7][4] );
        plOne.add( to.getGame()[7][6] );
        
        plOne.add( to.getGame()[6][0] );
        plOne.add( to.getGame()[6][2] );
        plOne.add( to.getGame()[6][4] );
        plOne.add( to.getGame()[6][6] );
        
        plOne.add( to.getGame()[5][0] );
        plOne.add( to.getGame()[5][2] );
        plOne.add( to.getGame()[5][4] );
        plOne.add( to.getGame()[5][6] );
        
        assertEquals( "<< GameBoard: " + "playerOne" + " should be "
                        + plOne + " >>", plOne, to.getPlayerOnePieces() );
        
    }
    
    @Test
    public void GameBoardRemovePlayerOnePieces()
    {
        GameBoard to = new GameBoard();
        to.fillGameBoard( "playerOne", "playerTwo" );
        
        ArrayList<GamePiece> plOne = new ArrayList<GamePiece>();
        
        plOne.add( to.getGame()[7][0] );
        plOne.add( to.getGame()[7][2] );
        plOne.add( to.getGame()[7][4] );
        plOne.add( to.getGame()[7][6] );
        
        plOne.add( to.getGame()[6][0] );
        plOne.add( to.getGame()[6][2] );
        plOne.add( to.getGame()[6][4] );
        plOne.add( to.getGame()[6][6] );
        
        plOne.add( to.getGame()[5][0] );
        plOne.add( to.getGame()[5][2] );
        plOne.add( to.getGame()[5][4] );
        plOne.add( to.getGame()[5][6] );
        
        GamePiece g = new PieceTwo(to, 6, 2, "playerOne");
        to.removePlayerOnePiece( g );
        
        assertEquals( "<< GameBoard: " + "playerOne" + " should be "
                        + plOne + " >>", plOne, to.getPlayerOnePieces() );
    }
    
    @Test
    public void GameBoardGetPlayerTwoPieces()
    {
        GameBoard to = new GameBoard();
        to.fillGameBoard( "playerOne", "playerTwo" );
        
        ArrayList<GamePiece> plTwo = new ArrayList<GamePiece>();
        
        plTwo.add( to.getGame()[0][0] );
        plTwo.add( to.getGame()[0][2] );
        plTwo.add( to.getGame()[0][4] );
        plTwo.add( to.getGame()[0][6] );
        
        plTwo.add( to.getGame()[1][0] );
        plTwo.add( to.getGame()[1][2] );
        plTwo.add( to.getGame()[1][4] );
        plTwo.add( to.getGame()[1][6] );
        
        plTwo.add( to.getGame()[2][0] );
        plTwo.add( to.getGame()[2][2] );
        plTwo.add( to.getGame()[2][4] );
        plTwo.add( to.getGame()[2][6] );
        
        GamePiece g = new PieceOne(to, 2, 4, "playerOne");
        to.removePlayerTwoPiece( g );
        
        assertEquals( "<< GameBoard: " + "playerTwo" + " should be "
                        + plTwo + " >>", plTwo, to.getPlayerTwoPieces() );
    }
    
    @Test
    public void GameBoardRemovePlayerTwoPieces()
    {
        GameBoard to = new GameBoard();
        to.fillGameBoard( "playerOne", "playerTwo" );
        
        ArrayList<GamePiece> plTwo = new ArrayList<GamePiece>();
        
        plTwo.add( to.getGame()[0][0] );
        plTwo.add( to.getGame()[0][2] );
        plTwo.add( to.getGame()[0][4] );
        plTwo.add( to.getGame()[0][6] );
        
        plTwo.add( to.getGame()[1][0] );
        plTwo.add( to.getGame()[1][2] );
        plTwo.add( to.getGame()[1][4] );
        plTwo.add( to.getGame()[1][6] );
        
        plTwo.add( to.getGame()[2][0] );
        plTwo.add( to.getGame()[2][2] );
        plTwo.add( to.getGame()[2][4] );
        plTwo.add( to.getGame()[2][6] );
        
        assertEquals( "<< GameBoard: " + "playerTwo" + " should be "
                        + plTwo + " >>", plTwo, to.getPlayerTwoPieces() );
    }
    
    @Test
    public void GameBoardSetGameBoardSpotOccupied()
    {
        GameBoard to = new GameBoard();
        to.fillGameBoard("PlayerOne", "PlayerTwo");
        boolean x = to.setGameBoard(to.getGame()[0][0], new Location(1, 0));
        assertEquals("<< GameBoard: Set should do be false >>", false, x);
        
    }
    
    @Test
    public void GameBoardSetGameBoardSpotOpen()
    {
        GameBoard to = new GameBoard();
        to.fillGameBoard("PlayerOne", "PlayerTwo");
        boolean x = to.setGameBoard(to.getGame()[0][0], new Location(0, 1));
        assertEquals("<< GameBoard: Set should do be false >>", true, x);
        assertEquals("<< GameBoard: New Piece should be at (0,1) >>",to.getGame()[0][1] != null , true);
    }
    
    @Test
    public void GameboardTurnGameBoard()
    {
    	GameBoard to = new GameBoard();
        to.fillGameBoard("PlayerOne", "PlayerTwo");
        to.turnGameBoard();
        to.turnGameBoard();
        GameBoard db = new GameBoard();
        db.fillGameBoard("PlayerOne", "PlayerTwo");
        assertEquals( "<< GameBoard: a Board turned twice should equal a new board >>", 
        		to.getGame()[0][0].getLocation().getX() == db.getGame()[0][0].getLocation().getX(), true );
        assertEquals( "<< GameBoard: a Board turned twice should equal a new board >>", 
        		to.getGame()[0][2].getLocation().getX() == db.getGame()[0][2].getLocation().getX(), true );
        assertEquals( "<< GameBoard: a Board turned twice should equal a new board >>", 
        		to.getGame()[1][4].getLocation().getX() == db.getGame()[1][4].getLocation().getX(), true );
        assertEquals( "<< GameBoard: a Board turned twice should equal a new board >>", 
        		to.getGame()[1][0].getLocation().getX() == db.getGame()[1][0].getLocation().getX(), true );
    }
    @Test
    public void GameboardResetGameBoard()
    {
    	GameBoard to = new GameBoard();
    	to.fillGameBoard("PlayerOne", "PlayerTwo");
    	to.turnGameBoard();
    	to.resetGameBoard();
    	GameBoard db = new GameBoard();
        db.fillGameBoard("PlayerOne", "PlayerTwo");
        assertEquals( "<< GameBoard: Reset Board should be same as new Board >>", 
         		to.getGame()[0][0].getLocation().getX() == db.getGame()[0][0].getLocation().getX(), true );
        assertEquals( "<< GameBoard: Reset Board should be same as new Board >>", 
         		to.getGame()[0][2].getLocation().getX() == db.getGame()[0][2].getLocation().getX(), true );
        assertEquals( "<< GameBoard: Reset Board should be same as new Board >>", 
         		to.getGame()[1][4].getLocation().getX() == db.getGame()[1][4].getLocation().getX(), true );
        assertEquals( "<< GameBoard: Reset Board should be same as new Board >>", 
         		to.getGame()[1][0].getLocation().getX() == db.getGame()[1][0].getLocation().getX(), true );
    }
    
    @Test
    public void LocationConstructor()
    {
        Location to = new Location( x, y );
        
        assertEquals("<< Invalid Location constructed  - x >>",
            to.getX(), x );
        assertEquals("<< Invalid Location constructed - y >>",
            to.getY(), y);
    }
    
    @Test
    public void LocationGetX()
    {
        Location to = new Location( x, y );
        assertEquals( "<< Location: " + "coorX" + " should be "
            + x + " >>", x, to.getX() );
    }
    
    @Test
    public void LocationGetY()
    {
        Location to = new Location( x, y );
        assertEquals( "<< Location: " + "coorY" + " should be "
            + y + " >>", y, to.getY() );
    }
    
    @Test
    public void LocationEquals()
    {
        Location loc = new Location( x, y );
        Location loc2 = new Location( x, y );
        assertEquals( "<< Location: " + "coorX" + " should be "
            + true + " >>", loc.getX() == loc2.getX(), true );
        assertEquals( "<< Location: " + "coorY" + " should be "
                        + true + " >>", loc.getY() == loc2.getY(), true );
    }
    
    @Test
    public void GamePieceOneConstructor()
    {
        GameBoard game = new GameBoard();
        GamePiece to = new PieceOne( game, x, y, play );
        
        assertNotNull("<< Invalid GamePiece constructed - game >>",
            to.getBoard());
        assertEquals("<< Invalid GamePiece constructed  - x >>",
            to.getLocation().getX(), x );
        assertEquals("<< Invalid GamePiece constructed - y >>",
            to.getLocation().getY(), y);
        assertEquals("<< Invalid GamePiece constructed - play >>",
            to.getPlayer(), play);
    }
    
    @Test
    public void GamePieceTwoConstructor()
    {
        GameBoard game = new GameBoard();
        GamePiece to = new PieceTwo( game, x, y, play );
        
        assertNotNull("<< Invalid GamePiece constructed - game >>",
            to.getBoard());
        assertEquals("<< Invalid GamePiece constructed  - x >>",
            to.getLocation().getX(), x );
        assertEquals("<< Invalid GamePiece constructed - y >>",
            to.getLocation().getY(), y);
        assertEquals("<< Invalid GamePiece constructed - play >>",
            to.getPlayer(), play);
    }
    
    @Test
    public void GamePieceThreeConstructor()
    {
        GameBoard game = new GameBoard();
        GamePiece to = new PieceThree( game, x, y, play );
        
        assertNotNull("<< Invalid GamePiece constructed - game >>",
            to.getBoard());
        assertEquals("<< Invalid GamePiece constructed  - x >>",
            to.getLocation().getX(), x );
        assertEquals("<< Invalid GamePiece constructed - y >>",
            to.getLocation().getY(), y);
        assertEquals("<< Invalid GamePiece constructed - play >>",
            to.getPlayer(), play);
    }
    
//    public void GamePieceHasContact()
//    {
//        
//    }
    
//    public void GamePieceSetContact()
//    {
//        
//        GameBoard game = new GameBoard();
//        GamePiece one = new PieceOne( game, x, y, "p1" );
//        GamePiece two = new PieceTwo( game, x, y, "p2" );
//        Contact c = new Contact(one, two);
//    }
    
//    public void GamePieceGetContact()
//    {
//        
//    }
    
    @Test
    public void GamePieceGetLocation()
    {
        GameBoard game = new GameBoard();
        GamePiece to = new PieceOne( game, x, y, play );
        assertEquals( "<< Location: " + "coorX" + " should be "
                        + x + " >>", x, to.getLocation().getX() );
        assertEquals( "<< Location: " + "coorY" + " should be "
                        + y + " >>", y, to.getLocation().getY() );
        
    }
    
    @Test
    public void GamePieceSetLocation()
    {
        GameBoard game = new GameBoard();
        GamePiece to = new PieceOne( game, x, y, play );
        Location l = new Location(2, 3);
        to.setLocation( l );
        
        assertEquals( "<< Location: " + "coorX" + " should be "
                        + 2 + " >>", 2, to.getLocation().getX() );
        assertEquals( "<< Location: " + "coorY" + " should be "
                        + 3 + " >>", 3, to.getLocation().getY() );
    }
    
    @Test
    public void GamePieceGetPlayer()
    {
        GameBoard game = new GameBoard();
        GamePiece to = new PieceOne( game, x, y, play );
        assertEquals( "<< Player: " + "p" + " should be "
                        + play + " >>", play, to.getPlayer() );
    }
    
    @Test
    public void GamePieceGetBoard()
    {
        GameBoard game = new GameBoard();
        GamePiece to = new PieceOne( game, x, y, play );
        assertEquals( "<< GameBoard: " + "game" + " should be "
                        + game + " >>", game, to.getBoard() );
    }
    
    @Test
    public void AttackMethod()
    {
        GameBoard game = new GameBoard();
        PieceTwo t = new PieceTwo( game, x, y, play );
        PieceOne to = new PieceOne( game, x + 1, y + 1, play );
        boolean x = t.attack( to );
        assertEquals( "<< Should be true >>", x, true );
        assertEquals( "<< Attack should return health of 5 >>", to.getHealth(), 5 );
    }


    @Test
    public void AttackNotInRangeMethod()
    {
        GameBoard game = new GameBoard();
        PieceTwo t = new PieceTwo( game, x, y, play );
        PieceOne to = new PieceOne( game, x + 5, y + 5, play );
        boolean x = t.attack( to );
        assertEquals( "<< Should be false >>", x, false );
        assertEquals( "<< Attack should return health of 8 >>", to.getHealth(), 8 );
    }


//    @Test
//    public void AttackMethodwithContact()
//    {
//        GameBoard game = new GameBoard();
//        PieceTwo tl = new PieceTwo( game, x, y, play );
//        PieceTwo t = new PieceTwo( game, x, y, play );
//        Contact c = new Contact( t, tl );
//        PieceThree to = new PieceThree( game, x + 1, y + 1, play );
//        t.attack( to );
//        assertEquals( "<< Health of PieceThree should not change >>", to.getHealth(), 2 );
//        assertEquals( "<< Health of PieceTwo should change", tl.getHealth(), 1 );
//    }

    @Test
    public void MoveMethod()
    {
        GameBoard game = new GameBoard();
        PieceOne to = new PieceOne( game, x, y, play );
        Location l = new Location( x, y + 1 );
        boolean x = to.move( l );
        assertEquals( "<< Should be true >>", x, true );
        assertEquals( "<< Piece should move to  0, 1 >>", to.getLocation().getY(), 1 );
    }

    @Test
    public void MoveOutOfRangeMethod()
    {
        GameBoard game = new GameBoard();
        PieceOne to = new PieceOne( game, x, y, play );
        Location l = new Location( x, y );
        boolean x = to.move( l );
        assertEquals( "<< Should be true >>", x, false );
        assertEquals( "<< Piece should not move >>", to.getLocation().getX(), 0 );
        assertEquals( "<< Piece should not move >>", to.getLocation().getY(), 0 );
    }

//    @Test
//    public void MoveHasContactMethod()
//    {
//        GameBoard game = new GameBoard();
//        PieceOne to = new PieceOne( game, x, y, play );
//        PieceTwo tl = new PieceTwo( game, x, y, play );
//        Contact r = new Contact( to, tl );
//        Location l = new Location( x, y );
//        boolean x = to.move( l );
//        assertEquals( "<< Should be true >>", x, false );
//        assertEquals( "<< Piece should not move >>", to.getLocation().getX(), 0 );
//        assertEquals( "<< Piece should not move >>", to.getLocation().getY(), 0 );
//    }

    @Test
    public void PieceOneGetHealth()
    {
        GameBoard game = new GameBoard();
        PieceOne to = new PieceOne( game, x, y, play );
        assertEquals( "<< PieceOne: " + "health" + " should be "
                        + 8 + " >>", 8, to.getHealth() );
    }
    
    @Test
    public void PieceOneGetAttack()
    {
        GameBoard game = new GameBoard();
        PieceOne to = new PieceOne( game, x, y, play );
        assertEquals( "<< PieceOne: " + "attack" + " should be "
                        + 2 + " >>", 2, to.getAttack() );
    }
    
    @Test
    public void PieceOneSetAttack()
    {
        GameBoard game = new GameBoard();
        PieceOne to = new PieceOne( game, x, y, play );
        to.setAttack( 5 );
        
        assertEquals( "<< PieceOne: " + "attack" + " should be "
                        + 5 + " >>", 5, to.getAttack() );
    }
    
    @Test
    public void PieceOneGetPieceType()
    {
        GameBoard game = new GameBoard();
        PieceOne to = new PieceOne( game, x, y, play );
        assertEquals( "<< PieceOne: " + "PieceType" + " should be "
                        + PieceType.ONE + " >>", PieceType.ONE, 
                        to.getPieceType() );
    }
    
    @Test
    public void PieceOneRemovePiece()
    {
        GameBoard game = new GameBoard();
        game.fillGameBoard( "PlayerOne", "PlayerTwo" );
        
        GamePiece to = game.getGame()[2][2];
        to.reduceHealth( 8 );
        
        assertEquals( "<< PieceOne: " + "health" + " should be "
                        + 0 + " >>", 0, to.getHealth() );
        GamePiece toNow = game.getGame()[2][2];
        assertNull(toNow);
    }
    
    @Test
    public void PieceOneReduceHealth()
    {
        GameBoard game = new GameBoard();
        PieceOne to = new PieceOne( game, x, y, play );
        to.reduceHealth( 2 );
        
        assertEquals( "<< PieceOne: " + "health" + " should be "
                        + 6 + " >>", 6, to.getHealth() );
    }
    
    @Test
    public void PieceOnePossibleMovesRegular()
    {
        GameBoard game = new GameBoard();
        PieceOne toOne = new PieceOne( game, 2, 2, play );
        
        ArrayList<Location> exp = new ArrayList<Location>();
        exp.add( game.getLocation( 1, 1 ) );
        exp.add( game.getLocation( 1, 2 ) );
        exp.add( game.getLocation( 1, 3 ) );
        exp.add( game.getLocation( 2, 1 ) );
        exp.add( game.getLocation( 2, 3 ) );
        exp.add( game.getLocation( 3, 1 ) );
        exp.add( game.getLocation( 3, 2 ) );
        exp.add( game.getLocation( 3, 3 ) );
        
        assertEquals( "<< PieceOne: " + "possibleMoves" + " should be "
                        + exp + " >>", exp, toOne.possibleMoves() );
    }
    
    @Test
    public void PieceOnePossibleMovesZeroXBound()
    {
        GameBoard game = new GameBoard();
        PieceOne toOne = new PieceOne( game, 0, 2, play );
        
        ArrayList<Location> exp = new ArrayList<Location>();
        exp.add( game.getLocation( 0, 1 ) );
        exp.add( game.getLocation( 0, 3 ) );
        exp.add( game.getLocation( 1, 1 ) );
        exp.add( game.getLocation( 1, 2 ) );
        exp.add( game.getLocation( 1, 3 ) );
        assertEquals( "<< PieceOne: " + "possibleMoves" + " should be "
                        + exp + " >>", exp, toOne.possibleMoves() );
    }
    
    @Test
    public void PieceOnePossibleMovesZeroYBound()
    {
        GameBoard game = new GameBoard();
        PieceOne toOne = new PieceOne( game, 2, 0, play );
        
        ArrayList<Location> exp = new ArrayList<Location>();
        exp.add( game.getLocation( 1, 0 ) );
        exp.add( game.getLocation( 1, 1 ) );
        exp.add( game.getLocation( 2, 1 ) );
        exp.add( game.getLocation( 3, 0 ) );
        exp.add( game.getLocation( 3, 1 ) );
        
        assertEquals( "<< PieceOne: " + "possibleMoves" + " should be "
                        + exp + " >>", exp, toOne.possibleMoves() );
    }
    
    @Test
    public void PieceOnePossibleMovesMaxXBound()
    {
        GameBoard game = new GameBoard();
        PieceOne toOne = new PieceOne( game, game.getGame()[0].length - 1,
            2, play );
        
        ArrayList<Location> exp = new ArrayList<Location>();
        exp.add( game.getLocation( 6, 1 ) );
        exp.add( game.getLocation( 6, 2 ) );
        exp.add( game.getLocation( 6, 3 ) );
        exp.add( game.getLocation( 7, 1 ) );
        exp.add( game.getLocation( 7, 3 ) );
        assertEquals( "<< PieceOne: " + "possibleMoves" + " should be "
                        + exp + " >>", exp, toOne.possibleMoves() );
    }
    
    @Test
    public void PieceOnePossibleMovesMaxYBound()
    {
        GameBoard game = new GameBoard();
        PieceOne toOne = new PieceOne( game, 2, game.getGame().length - 1,
            play );
        
        ArrayList<Location> exp = new ArrayList<Location>();
        exp.add( game.getLocation( 1, 6 ) );
        exp.add( game.getLocation( 1, 7 ) );
        exp.add( game.getLocation( 2, 6 ) );
        exp.add( game.getLocation( 3, 6 ) );
        exp.add( game.getLocation( 3, 7 ) );
        
        assertEquals( "<< PieceOne: " + "possibleMoves" + " should be "
                        + exp + " >>", exp, toOne.possibleMoves() );
    }
    
    @Test
    public void PieceOnePossibleAttacksRegular()
    {
        GameBoard game = new GameBoard();
        PieceOne toOne = new PieceOne( game, 2, 2, play );
        
        ArrayList<Location> exp = new ArrayList<Location>();
        exp.add( game.getLocation( 1, 1 ) );
        exp.add( game.getLocation( 1, 2 ) );
        exp.add( game.getLocation( 1, 3 ) );
        exp.add( game.getLocation( 2, 1 ) );
        exp.add( game.getLocation( 2, 3 ) );
        exp.add( game.getLocation( 3, 1 ) );
        exp.add( game.getLocation( 3, 2 ) );
        exp.add( game.getLocation( 3, 3 ) );
        
        assertEquals( "<< PieceOne: " + "possibleAttacks" + " should be "
                        + exp + " >>", exp, toOne.possibleAttacks() );
    }
    
    @Test
    public void PieceOnePossibleAttacksZeroXBound()
    {
        GameBoard game = new GameBoard();
        PieceOne toOne = new PieceOne( game, 0, 2, play );
        
        ArrayList<Location> exp = new ArrayList<Location>();
        exp.add( game.getLocation( 0, 1 ) );
        exp.add( game.getLocation( 0, 3 ) );
        exp.add( game.getLocation( 1, 1 ) );
        exp.add( game.getLocation( 1, 2 ) );
        exp.add( game.getLocation( 1, 3 ) );
        assertEquals( "<< PieceOne: " + "possibleAttacks" + " should be "
                        + exp + " >>", exp, toOne.possibleAttacks() );
    }
    
    @Test
    public void PieceOnePossibleAttacksZeroYBound()
    {
        GameBoard game = new GameBoard();
        PieceOne toOne = new PieceOne( game, 2, 0, play );
        
        ArrayList<Location> exp = new ArrayList<Location>();
        exp.add( game.getLocation( 1, 0 ) );
        exp.add( game.getLocation( 1, 1 ) );
        exp.add( game.getLocation( 2, 1 ) );
        exp.add( game.getLocation( 3, 0 ) );
        exp.add( game.getLocation( 3, 1 ) );
        
        assertEquals( "<< PieceOne: " + "possibleAttacks" + " should be "
                        + exp + " >>", exp, toOne.possibleAttacks() );
    }
    
    @Test
    public void PieceOnePossibleAttacksMaxXBound()
    {
        GameBoard game = new GameBoard();
        PieceOne toOne = new PieceOne( game, game.getGame()[0].length - 1,
            2, play );
        
        ArrayList<Location> exp = new ArrayList<Location>();
        exp.add( game.getLocation( 6, 1 ) );
        exp.add( game.getLocation( 6, 2 ) );
        exp.add( game.getLocation( 6, 3 ) );
        exp.add( game.getLocation( 7, 1 ) );
        exp.add( game.getLocation( 7, 3 ) );
        assertEquals( "<< PieceOne: " + "possibleAttacks" + " should be "
                        + exp + " >>", exp, toOne.possibleAttacks() );
    }
    
    @Test
    public void PieceOnePossibleAttacksMaxYBound()
    {
        GameBoard game = new GameBoard();
        PieceOne toOne = new PieceOne( game, 2, game.getGame().length - 1,
            play );
        
        ArrayList<Location> exp = new ArrayList<Location>();
        exp.add( game.getLocation( 1, 6 ) );
        exp.add( game.getLocation( 1, 7 ) );
        exp.add( game.getLocation( 2, 6 ) );
        exp.add( game.getLocation( 3, 6 ) );
        exp.add( game.getLocation( 3, 7 ) );
        
        assertEquals( "<< PieceOne: " + "possibleAttacks" + " should be "
                        + exp + " >>", exp, toOne.possibleAttacks() );
    }
    
    @Test
    public void PieceOneToString()
    {
        GameBoard game = new GameBoard();
        PieceOne to = new PieceOne( game, x, y, play );
        assertEquals( "<< PieceOne: " + "string" + " should be "
                        + 6 + " >>", "PieceOne", to.toString() );
    }
    
    @Test
    public void PieceTwoGetHealth()
    {
        GameBoard game = new GameBoard();
        PieceTwo to = new PieceTwo( game, x, y, play );
        assertEquals( "<< PieceTwo: " + "health" + " should be "
                        + 4 + " >>", 4, to.getHealth() );
    }
    
    @Test
    public void PieceTwoGetAttack()
    {
        GameBoard game = new GameBoard();
        PieceTwo to = new PieceTwo( game, x, y, play );
        assertEquals( "<< PieceTwo: " + "attack" + " should be "
                        + 3 + " >>", 3, to.getAttack() );
    }
    
    @Test
    public void PieceTwoSetAttack()
    {
        GameBoard game = new GameBoard();
        PieceTwo to = new PieceTwo( game, x, y, play );
        to.setAttack( 5 );
        
        assertEquals( "<< PieceTwo: " + "attack" + " should be "
                        + 5 + " >>", 5, to.getAttack() );
    }
    
    @Test
    public void PieceTwoGetPieceType()
    {
        GameBoard game = new GameBoard();
        PieceTwo to = new PieceTwo( game, x, y, play );
        assertEquals( "<< PieceTwo: " + "PieceType" + " should be "
                        + PieceType.TWO + " >>", PieceType.TWO, 
                        to.getPieceType() );
    }
    
    @Test
    public void PieceTwoRemovePiece()
    {
        GameBoard game = new GameBoard();
        game.fillGameBoard( "PlayerOne", "PlayerTwo" );
        
        GamePiece to = game.getGame()[1][2];
        to.reduceHealth( 5 );
        
        assertEquals( "<< PieceTwo: " + "health" + " should be "
                        + -1 + " >>", -1, to.getHealth() );
        GamePiece toNow = game.getGame()[1][2];
        assertNull(toNow);
    }
    
    @Test
    public void PieceTwoReduceHealth()
    {
        GameBoard game = new GameBoard();
        PieceTwo to = new PieceTwo( game, x, y, play );
        to.reduceHealth( 2 );
        
        assertEquals( "<< PieceTwo: " + "health" + " should be "
                        + 2 + " >>", 2, to.getHealth() );
    }
    
    @Test
    public void PieceTwoPossibleMovesRegular()
    {
        GameBoard game = new GameBoard();
        PieceTwo toTwo = new PieceTwo( game, 2, 2, play );
        
        ArrayList<Location> exp = new ArrayList<Location>();
        exp.add( game.getLocation( 1, 1 ) );
        exp.add( game.getLocation( 1, 3 ) );
        exp.add( game.getLocation( 3, 1 ) );
        exp.add( game.getLocation( 3, 3 ) );
        
        assertEquals( "<< PieceTwo: " + "possibleMoves" + " should be "
                        + exp + " >>", exp, toTwo.possibleMoves() );
    }
    
    @Test
    public void PieceTwoPossibleMovesZeroXBound()
    {
        GameBoard game = new GameBoard();
        PieceTwo toTwo = new PieceTwo( game, 0, 2, play );
        
        ArrayList<Location> exp = new ArrayList<Location>();
        exp.add( game.getLocation( 1, 1 ) );
        exp.add( game.getLocation( 1, 3 ) );
        assertEquals( "<< PieceTwo: " + "possibleMoves" + " should be "
                        + exp + " >>", exp, toTwo.possibleMoves() );
    }
    
    @Test
    public void PieceTwoPossibleMovesZeroYBound()
    {
        GameBoard game = new GameBoard();
        PieceTwo toTwo = new PieceTwo( game, 2, 0, play );
        
        ArrayList<Location> exp = new ArrayList<Location>();
        exp.add( game.getLocation( 1, 1 ) );
        exp.add( game.getLocation( 3, 1 ) );
        
        assertEquals( "<< PieceTwo: " + "possibleMoves" + " should be "
                        + exp + " >>", exp, toTwo.possibleMoves() );
    }
    
    @Test
    public void PieceTwoPossibleMovesMaxXBound()
    {
        GameBoard game = new GameBoard();
        PieceTwo toTwo = new PieceTwo( game, game.getGame()[0].length - 1,
            2, play );
        
        ArrayList<Location> exp = new ArrayList<Location>();
        exp.add( game.getLocation( 6, 1 ) );
        exp.add( game.getLocation( 6, 3 ) );

        assertEquals( "<< PieceTwo: " + "possibleMoves" + " should be "
                        + exp + " >>", exp, toTwo.possibleMoves() );
    }
    
    @Test
    public void PieceTwoPossibleMovesMaxYBound()
    {
        GameBoard game = new GameBoard();
        PieceTwo toTwo = new PieceTwo( game, 2, game.getGame().length - 1,
            play );
        
        ArrayList<Location> exp = new ArrayList<Location>();
        exp.add( game.getLocation( 1, 6 ) );
        exp.add( game.getLocation( 3, 6 ) );
        
        assertEquals( "<< PieceTwo: " + "possibleMoves" + " should be "
                        + exp + " >>", exp, toTwo.possibleMoves() );
    }
    
    @Test
    public void PieceTwoPossibleAttacksRegular()
    {
        GameBoard game = new GameBoard();
        PieceTwo toTwo = new PieceTwo( game, 2, 2, play );
        
        ArrayList<Location> exp = new ArrayList<Location>();
        for (int i = 0; i <= 4; i++)
        {
            for (int j = 0; j <= 4; j++)
            {
                if (i != 2 || j != 2)
                {
                    exp.add( game.getLocation( i, j ) );
                }
            }
        }
        
        assertEquals( "<< PieceTwo: " + "possibleAttacks" + " should be "
                        + exp + " >>", exp, toTwo.possibleAttacks() );
    }
    
    @Test
    public void PieceTwoPossibleAttacksZeroXBound()
    {
        GameBoard game = new GameBoard();
        PieceTwo toTwo = new PieceTwo( game, 0, 2, play );
        
        ArrayList<Location> exp = new ArrayList<Location>();
        for (int i = 0; i <= 2; i++)
        {
            for (int j = 0; j <= 4; j++)
            {
                if (i != 0 || j != 2)
                {
                    exp.add( game.getLocation( i, j ) );
                }
            }
        }
        assertEquals( "<< PieceTwo: " + "possibleAttacks" + " should be "
                        + exp + " >>", exp, toTwo.possibleAttacks() );
    }
    
    @Test
    public void PieceTwoPossibleAttacksZeroYBound()
    {
        GameBoard game = new GameBoard();
        PieceTwo toTwo = new PieceTwo( game, 2, 0, play );
        
        ArrayList<Location> exp = new ArrayList<Location>();
        for (int i = 0; i <= 4; i++)
        {
            for (int j = 0; j <= 2; j++)
            {
                if (i != 2 || j != 0)
                {
                    exp.add( game.getLocation( i, j ) );
                }
            }
        }
        
        assertEquals( "<< PieceTwo: " + "possibleAttacks" + " should be "
                        + exp + " >>", exp, toTwo.possibleAttacks() );
    }
    
    @Test
    public void PieceTwoPossibleAttacksMaxXBound()
    {
        GameBoard game = new GameBoard();
        PieceTwo toTwo = new PieceTwo( game, game.getGame()[0].length - 1,
            2, play );
        
        ArrayList<Location> exp = new ArrayList<Location>();
        for (int i = game.getGame()[0].length - 3; 
                        i <= game.getGame()[0].length - 1; i++)
        {
            for (int j = 0; j <= 4; j++)
            {
                if (i != game.getGame()[0].length - 1 || j != 2)
                {
                    exp.add( game.getLocation( i, j ) );
                }
            }
        }
        assertEquals( "<< PieceTwo: " + "possibleAttacks" + " should be "
                        + exp + " >>", exp, toTwo.possibleAttacks() );
    }
    
    @Test
    public void PieceTwoPossibleAttacksMaxYBound()
    {
        GameBoard game = new GameBoard();
        PieceTwo toTwo = new PieceTwo( game, 2, game.getGame().length - 1,
            play );
        
        ArrayList<Location> exp = new ArrayList<Location>();
        for (int i = 0; i <= 4; i++)
        {
            for (int j = game.getGame().length - 3; 
                            j <= game.getGame().length - 1; j++)
            {
                if (i != 2 || j != game.getGame().length - 1)
                {
                    exp.add( game.getLocation( i, j ) );
                }
            }
        }
        
        assertEquals( "<< PieceTwo: " + "possibleAttacks" + " should be "
                        + exp + " >>", exp, toTwo.possibleAttacks() );
    }
    
    @Test
    public void PieceTwoToString()
    {
        GameBoard game = new GameBoard();
        PieceTwo to = new PieceTwo( game, x, y, play );
        assertEquals( "<< PieceTwo: " + "string" + " should be "
                        + 6 + " >>", "PieceTwo", to.toString() );
    }
    
    @Test
    public void PieceThreeGetHealth()
    {
        GameBoard game = new GameBoard();
        PieceThree to = new PieceThree( game, x, y, play );
        assertEquals( "<< PieceThree: " + "health" + " should be "
                        + 2 + " >>", 2, to.getHealth() );
    }
    
    @Test
    public void PieceThreeGetAttack()
    {
        GameBoard game = new GameBoard();
        PieceThree to = new PieceThree( game, x, y, play );
        assertEquals( "<< PieceThree: " + "attack" + " should be "
                        + 1 + " >>", 1, to.getAttack() );
    }
    
    @Test
    public void PieceThreeSetAttack()
    {
        GameBoard game = new GameBoard();
        PieceThree to = new PieceThree( game, x, y, play );
        to.setAttack( 5 );
        
        assertEquals( "<< PieceThree: " + "attack" + " should be "
                        + 5 + " >>", 5, to.getAttack() );
    }
    
    @Test
    public void PieceThreeGetPieceType()
    {
        GameBoard game = new GameBoard();
        PieceThree to = new PieceThree( game, x, y, play );
        assertEquals( "<< PieceThree: " + "PieceType" + " should be "
                        + PieceType.THREE + " >>", PieceType.THREE, 
                        to.getPieceType() );
    }
    
    @Test
    public void PieceThreeRemovePiece()
    {
        GameBoard game = new GameBoard();
        game.fillGameBoard( "PlayerOne", "PlayerTwo" );
        
        GamePiece to = game.getGame()[0][2];
        to.reduceHealth( 5 );
        
        assertEquals( "<< PieceThree: " + "health" + " should be "
                        + -3 + " >>", -3, to.getHealth() );
        GamePiece toNow = game.getGame()[0][2];
        assertNull(toNow);
    }
    
    @Test
    public void PieceThreeReduceHealth()
    {
        GameBoard game = new GameBoard();
        PieceThree to = new PieceThree( game, x, y, play );
        to.reduceHealth( 2 );
        
        assertEquals( "<< PieceThree: " + "health" + " should be "
                        + 0 + " >>", 0, to.getHealth() );
    }
    
    @Test
    public void PieceThreePossibleMovesRegular()
    {
        GameBoard game = new GameBoard();
        PieceThree toThree = new PieceThree( game, 2, 2, play );
        
        ArrayList<Location> exp = new ArrayList<Location>();
        exp.add( game.getLocation( 1, 2 ) );
        exp.add( game.getLocation( 2, 1 ) );
        exp.add( game.getLocation( 2, 3 ) );
        exp.add( game.getLocation( 3, 2 ) );
        
        assertEquals( "<< PieceThree: " + "possibleMoves" + " should be "
                        + exp + " >>", exp, toThree.possibleMoves() );
    }
    
    @Test
    public void PieceThreePossibleMovesZeroXBound()
    {
        GameBoard game = new GameBoard();
        PieceThree toThree = new PieceThree( game, 0, 2, play );
        
        ArrayList<Location> exp = new ArrayList<Location>();
        exp.add( game.getLocation( 0, 1 ) );
        exp.add( game.getLocation( 0, 3 ) );
        exp.add( game.getLocation( 1, 2 ) );
        assertEquals( "<< PieceThree: " + "possibleMoves" + " should be "
                        + exp + " >>", exp, toThree.possibleMoves() );
    }
    
    @Test
    public void PieceThreePossibleMovesZeroYBound()
    {
        GameBoard game = new GameBoard();
        PieceThree toThree = new PieceThree( game, 2, 0, play );
        
        ArrayList<Location> exp = new ArrayList<Location>();
        exp.add( game.getLocation( 1, 0 ) );
        exp.add( game.getLocation( 2, 1 ) );
        exp.add( game.getLocation( 3, 0 ) );
        
        assertEquals( "<< PieceThree: " + "possibleMoves" + " should be "
                        + exp + " >>", exp, toThree.possibleMoves() );
    }
    
    @Test
    public void PieceThreePossibleMovesMaxXBound()
    {
        GameBoard game = new GameBoard();
        PieceThree toThree = new PieceThree( game, game.getGame()[0].length - 1,
            2, play );
        
        ArrayList<Location> exp = new ArrayList<Location>();
        exp.add( game.getLocation( 6, 2 ) );
        exp.add( game.getLocation( 7, 1 ) );
        exp.add( game.getLocation( 7, 3 ) );
        assertEquals( "<< PieceThree: " + "possibleMoves" + " should be "
                        + exp + " >>", exp, toThree.possibleMoves() );
    }
    
    @Test
    public void PieceThreePossibleMovesMaxYBound()
    {
        GameBoard game = new GameBoard();
        PieceThree toThree = new PieceThree( game, 2, game.getGame().length - 1,
            play );
        
        ArrayList<Location> exp = new ArrayList<Location>();
        exp.add( game.getLocation( 1, 7 ) );
        exp.add( game.getLocation( 2, 6 ) );
        exp.add( game.getLocation( 3, 7 ) );
        
        assertEquals( "<< PieceThree: " + "possibleMoves" + " should be "
                        + exp + " >>", exp, toThree.possibleMoves() );
    }
    
    @Test
    public void PieceThreePossibleAttacksUpperRight()
    {
        GameBoard game = new GameBoard();
        PieceThree toThree = new PieceThree( game, 4, 4, play );
        
        ArrayList<Location> exp = new ArrayList<Location>();
        for (int i = 0; i <= 7; i++)
        {
            for (int j = 0; j <= 7; j++)
            {
                if (i != 4 || j != 4)
                {
                    exp.add( game.getLocation( i, j ) );
                }
            }
        }
        
        assertEquals( "<< PieceThree: " + "possibleAttacks" + " should be "
                        + exp + " >>", exp, toThree.possibleAttacks() );
    }
    
    @Test
    public void PieceThreePossibleAttacksBottomLeft()
    {
        GameBoard game = new GameBoard();
        PieceThree toThree = new PieceThree( game, 3, 3, play );
        
        ArrayList<Location> exp = new ArrayList<Location>();
        for (int i = 0; i <= 7; i++)
        {
            for (int j = 0; j <= 7; j++)
            {
                if (i != 3 || j != 3)
                {
                    exp.add( game.getLocation( i, j ) );
                }
            }
        }
        assertEquals( "<< PieceThree: " + "possibleAttacks" + " should be "
                        + exp + " >>", exp, toThree.possibleAttacks() );
    }
    
    @Test
    public void PieceThreeToString()
    {
        GameBoard game = new GameBoard();
        PieceThree to = new PieceThree( game, x, y, play );
        assertEquals( "<< PieceThree: " + "string" + " should be "
                        + 6 + " >>", "PieceThree", to.toString() );
    }
    
//    @Test
//    public void ContactConstructor()
//    {
//        GameBoard game = new GameBoard();
//        PieceOne to = new PieceOne( game, x, y, play );
//        PieceTwo tl = new PieceTwo( game, x, y, play );
//        Contact c = new Contact( to, tl );
//        assertNotNull( "<< Invalid PieceThree constructed - contact >>", c );
//    }
//
//    @Test
//    public void ContactGetPieceOneConstructor()
//    {
//        GameBoard game = new GameBoard();
//        PieceOne to = new PieceOne( game, x, y, play );
//        PieceTwo tl = new PieceTwo( game, x, y, play );
//        Contact c = new Contact( to, tl );
//        assertEquals( "<< Contact - Should return PieceOne >>", c.getFirstPiece().getHealth(), 8 );
//    }
//
//    @Test
//    public void ContactGetPieceTwoConstructor()
//    {
//        GameBoard game = new GameBoard();
//        PieceOne to = new PieceOne( game, x, y, play );
//        PieceTwo tl = new PieceTwo( game, x, y, play );
//        Contact c = new Contact( to, tl );
//        assertEquals( "<< Contact - Should return PieceTwo >>", c.getSecondPiece().getHealth(), 4 );
//    }
//
//    @Test
//    public void ContactGetLocationConstructor()
//    {
//        GameBoard game = new GameBoard();
//        PieceOne to = new PieceOne( game, x, y, play );
//        PieceTwo tl = new PieceTwo( game, x, y, play );
//        Contact c = new Contact( to, tl );
//        assertEquals( "<< Contact - Should return Location at 0,0 >>", c.getLocation().getX(), 0 );
//        assertEquals( "<< Contact - Should return Location at 0,0 >>", c.getLocation().getY(), 0 );
//    }
//
//    @Test
//    public void ContactContactDamageConstructor()
//    {
//        GameBoard game = new GameBoard();
//        PieceOne to = new PieceOne( game, x, y, play );
//        PieceTwo tl = new PieceTwo( game, x, y, play );
//        Contact c = new Contact( to, tl );
//        c.contactDamage();
//        assertEquals( "<< Contact -PieceOne should have health of 7 >>", to.getHealth(), 7 );
//        assertEquals( "<< Contact -PieceTwo should have health of 2 >>", tl.getHealth(), 2 );
//    }
//
//    @Test
//    public void ContactContactAttackConstructor()
//    {
//        GameBoard game = new GameBoard();
//        PieceOne to = new PieceOne( game, x, y, play );
//        PieceOne tl = new PieceOne( game, x, y, play );
//        Contact c = new Contact( to, tl );
//        c.contactDamage();
//        c.contactAttack();
//        assertEquals( "<< Contact -PieceOne should have health of 4 >>", to.getHealth(), 4 );
//        assertEquals( "<< Contact -PieceTwo should have health of 4 >>", tl.getHealth(), 4 );
//    }
}

