import java.util.Scanner;
/**
 * The BattleZone class the main class. It tells the program to create a 
 * new GameGUI and consists of some methods to help with debugging.
 * @author User
 *
 */
public class Main
{
    private static GameBoard game;
    int[][] sqrBoard = new int[8][8];
    /** 
     * Creates a GameBoard and calls fill GameBoard to fill the 2D array with the default pieces
     * and default locations.
     * Tranverses the 2D array, printing 1 for pieceOne, 2 for PieceTwo, and 3 for PieceThree
     * (Used for Debugging) 
     */
    public Main()
    {
        game = new GameBoard();
        game.fillGameBoard( "PlayerOne", "PlayerTwo" );
        for (int i = 0; i < game.getGame().length; i++)
        {
            for (int j = 0; j < game.getGame()[0].length; j++)
            {
                if (game.getGame()[i][j] instanceof PieceOne)
                {
                    System.out.print( "1 " );
                }
                else if (game.getGame()[i][j] instanceof PieceTwo)
                {
                    System.out.print( "2 " );
                }
                else if (game.getGame()[i][j] instanceof PieceThree)
                {
                    System.out.print( "3 " );
                }
                else
                {
                    System.out.print( "0 " );
                }
            }
            System.out.println();
        }
        System.out.println("-------------");
    }
    /**
     * Accessor method for game, 2D array inside GameBoard
     * @return game
     */
    public GameBoard boardGetter()
    {
        return game;
    }
    /**
     * Tranverses the 2D array in game, printing 1 for pieceOne, 2 for PieceTwo, and 3 for PieceThree
     * (Used for Debugging) 
     * @param game GameBoard object
     */
    public static void printBoard(GameBoard game)
    {
    	for (int i = 0; i < game.getGame().length; i++)
        {
            for (int j = 0; j < game.getGame()[0].length; j++)
            {
                if (game.getGame()[i][j] instanceof PieceOne)
                {
                    System.out.print( "1 " );
                }
                else if (game.getGame()[i][j] instanceof PieceTwo)
                {
                    System.out.print( "2 " );
                }
                else if (game.getGame()[i][j] instanceof PieceThree)
                {
                    System.out.print( "3 " );
                }
                else
                {
                    System.out.print( "0 " );
                }
            }
            System.out.println();
        }
    	System.out.println("-------------");
    }
    /**
     * main method that runs the program. Tells the program to create a new 
     * GameGUI object, opening and displaying GameGUI
     * @param args
     */
    public static void main( String[] args )
    {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new GameGUI();
            }
        });
    }
}

