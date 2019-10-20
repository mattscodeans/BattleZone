import java.util.ArrayList;

public class GameBoard
{
    GamePiece[][] game;
    ArrayList<GamePiece> playerOne;
    ArrayList<GamePiece> playerTwo;
    ArrayList<Location> locs = new ArrayList<Location>();
    
    public GameBoard() 
    {
        game = new GamePiece[8][8];
        playerOne = new ArrayList<GamePiece>();
        playerTwo = new ArrayList<GamePiece>();
        for (int i = 0; i < game.length; i++)
        {
            for (int j = 0; j < game[0].length; j++)
            {
                locs.add( new Location(i, j) );
            }
        }
    }
    
    public GamePiece[][] getGame() 
    {
        return game;
    }
    
    public Location getLocation(int x, int y)
    {
        return locs.get( x * 8 + y );
    }
    public void fillGameBoard(String p1, String p2)
    {
        //make GameBoard 8 by 8.
        //Piece arrangement is similar
        for (int i = 0; i < 3; i++)
        {
            for (int j = 0; j < game.length; j+=2)
            {
                if (i == 0)
                {
                    GamePiece newPiece = new PieceThree(this, i, j, p2);
                    game[i][j] = newPiece;
                    playerTwo.add( newPiece );
                }
                else if (i == 1)
                {
                    GamePiece newPiece = new PieceTwo(this, i, j, p2);
                    game[i][j] = newPiece;
                    playerTwo.add( newPiece );
                }
                else if (i == 2)
                {
                    GamePiece newPiece = new PieceOne(this, i, j, p2);
                    game[i][j] = newPiece;
                    playerTwo.add( newPiece );
                }
            }
        }
        
        for (int i = game.length - 1; i > game.length - 4; i--)
        {
            for (int j = 1; j < game.length; j+=2)
            {
                if (i == game.length - 1)
                {
                    GamePiece newPiece = new PieceThree(this, i, j, p1);
                    game[i][j] = newPiece;
                    playerOne.add( newPiece );
                }
                else if (i == game.length - 2)
                {
                    GamePiece newPiece = new PieceTwo(this, i, j, p1);
                    game[i][j] = newPiece;
                    playerOne.add( newPiece );
                }
                else if (i == game.length - 3)
                {
                    GamePiece newPiece = new PieceOne(this, i, j, p1);
                    game[i][j] = newPiece;
                    playerOne.add( newPiece );
                }
            }
        }
    }
    
    public ArrayList<GamePiece> getPlayerOnePieces()
    {
        return playerOne;
    }
    
    public void removePlayerOnePiece(GamePiece target)
    {
        playerOne.remove( target );
    }
    
    public ArrayList<GamePiece> getPlayerTwoPieces()
    {
        return playerTwo;
    }
    
    public void removePlayerTwoPiece(GamePiece target)
    {
        playerTwo.remove( target );
    }
    public boolean setGameBoard(GamePiece piece, Location newL)
    {
          GamePiece spot = game[newL.getX()][newL.getY()]; 
          if (spot != null)
          {
              return false;
          }
          game[newL.getX()][newL.getY()] = piece;
          game[piece.getLocation().getX()][piece.getLocation().getY()] = null;
          return true;
    }
    
    public void turnGameBoard()
    {
        GamePiece[][] newGame = new GamePiece[8][8];
        for (int i = 0; i < game.length; i++)
        {
            for (int j = 0; j < game[0].length; j++)
            {
                newGame[i][j] = game[game.length - 1 - i][game[0].length - 1 - j];
                if (game[game.length - 1 - i][game[0].length - 1 - j] != null)
                {
                    game[game.length - 1 - i][game[0].length - 1 - j].setLocation(new Location(i,j));
                }
            }
        }
        game = newGame;
    }
  public void resetGameBoard()
    {
    	game = new GamePiece[8][8];
    	this.fillGameBoard("PlayerOne" , "PlayerTwo");
    }
    
    
//    public String playerToString()
//    {
//        return "playerOne";
//    }
    
}
