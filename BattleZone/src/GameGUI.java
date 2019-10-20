
import java.awt.*;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;

import javax.imageio.ImageIO;
import javax.swing.*;
/**
 * GameGUI contains the main GUI of BattleZone. At the center of the 
 * GUI is the gameboard and to the right of it is a panel that helps
 * display what turn it is and characteristics of the pieces.
 * 
 * Also includes a Menu Item Bar which allows the user to reset or 
 * exit the game, toggle whether to show attack range when hovering 
 * the mouse over a piece, and skipping a turn.
 * 
 * 	@author  Matthew Wang and Ajeet Kotturu
 * 	@version May 28, 2019
 *  @author  Period: 4th
 *  @author  Assignment: APCSFinalProject
 *
 *  @author  Sources: Software & Architecture Design (Youtube User)
 *
 */
public class GameGUI
{
    private JFrame GameFrame;
    private GameBoard game = new GameBoard(); 
    private BoardDisplay boardDisplay;
    private String filePath = "src/img/"; //image/pieceOnePlayerOne.png
    private static Dimension MAX_FRAME = new Dimension(900, 800);
    private static Dimension TILE_FRAME = new Dimension(10, 10);
    private static Dimension BOARD_FRAME = new Dimension(600, 600);
    private Color white = Color.decode("#ffffff");
    private Color brown = Color.decode("#823d01");
    private Color green = Color.decode("#00FF00");
    private Color orange = Color.decode("#ffc04c");
    private GamePiece selectedPiece;
    private GamePiece moveToPiece;
    private GamePiece ghostPiece;
    private TurnDecider turnDecider;
    private MovesPanel movesPanel;
    private boolean ghost = true;
    /**
     * Creates the GameGUI from the parts created in the methods and private classes. The GUI
     * utilizes a BorderLayout from JFrame. Also creates, sets up, and displays
     * the Menu Bar. Calls fillGameBoard() from GameBoard to initialize the 
     * 2D array. Adds BoardDisplay to the middle of the BorderLayout and puts
     * the movesPanel on the East side. Creates a new TurnDecider, starting at 
     * the first turn.
     */
    public GameGUI()
    {
        this.GameFrame = new JFrame("BattleZone");
        this.GameFrame.setLayout(new BorderLayout());
        this.turnDecider = new TurnDecider();
        game.fillGameBoard("PlayerOne", "PlayerTwo");
        JMenuBar guiMenuBar = new JMenuBar();
        guiMenuBar.add(fileMenu());
        guiMenuBar.add(skipMenu());
        guiMenuBar.add(toolsMenu());
        this.GameFrame.setJMenuBar(guiMenuBar);
        this.GameFrame.setSize(MAX_FRAME);
        this.boardDisplay = new BoardDisplay();
        this.GameFrame.add(boardDisplay, BorderLayout.CENTER);
        this.movesPanel = new MovesPanel(turnDecider);
        this.GameFrame.add(movesPanel, BorderLayout.EAST);
        this.GameFrame.setVisible(true);
    }
    /**
     * Calls resetGameBoard() from GameBoard to clear the board, 
     * and creates a new turnDecider, resetting it to the first turn.
     */
    private void reset()
    {
    	game.resetGameBoard();
    	turnDecider = new TurnDecider();
    }
    /**
     * Creates a new JMenu called Game and adds two Items to it:
     * Reset Game - when pressed, calls reset()
     * Exit Game - when pressed, exits the game removes the board display
     * @return JMenu called Game with Reset Game and Exit Game
     * Functions
     */
    private JMenu fileMenu()
    {
        JMenu fileMenu = new JMenu("Game");
        JMenuItem reset = new JMenuItem("Reset Game");
        reset.addActionListener(new ActionListener() {
        	/**
        	 * tells this GUI to reset the board through reset()
        	 */
            @Override
            public void actionPerformed(ActionEvent arg0) {
                reset();
            }
        });
        fileMenu.add(reset);
        JMenuItem exit = new JMenuItem("Exit Game");
        exit.addActionListener(new ActionListener() {
        	/**
        	 * tells the System to close the GUI and removes the boardDisplay from
        	 * the JFrame
        	 */
            @Override
            public void actionPerformed(ActionEvent arg0) {
                System.exit(0);
                GameFrame.remove(boardDisplay);
            }
        });
        fileMenu.add(exit);
        return fileMenu;
    }
    /**
     * Creates a new JMenu called Skip and adds one Item to it:
     * Skip Turn - tells turnDecider to advanceTurn() and move 
     * to next turn
     * @return JMenu called Skip with Skip Turn Function
     */
    private JMenu skipMenu()
    {
        JMenu skipMenu = new JMenu("Skip");
        JMenuItem skip = new JMenuItem("Skip Turn");
        skip.addActionListener(new ActionListener() {
        	/**
        	 * calls the turnDecider to advance a turn through advanceTurn()
        	 */
            @Override
            public void actionPerformed(ActionEvent arg0) {
                turnDecider.advanceTurn(); 
            }
        });
        skipMenu.add(skip);
        return skipMenu;
    }
    /**
     * Creates a new JMenu called Tools and adds one Item to it:
     * Toggle Displaying Range on Hover - Changes ghost to true or 
     * false depending on its value, a boolean
     * Ghost determines if ranges show while mouse is hovered 
     * over the piece
     * @return JMenu called Skip with Skip Turn Function
     */
    private JMenu toolsMenu()
    {
        JMenu toolsMenu = new JMenu("Tools");
        JMenuItem range = new JMenuItem("Toggle Displaying Range on Hover");
        range.addActionListener(new ActionListener() {
        	/**
        	 * if ghost is true, set to false and vice versa. Updates the movePanel
        	 * by adding/removing message stating to hover a piece to see its attack
        	 * range.
        	 */
            @Override
            public void actionPerformed(ActionEvent arg0) {
                if (ghost)
                {
                	ghost = false;
                	movesPanel.updateToggle();
                }
                else if (!ghost)
                {
                	ghost = true;
                	movesPanel.updateToggle();
                }
            }
        });
        toolsMenu.add(range);
        return toolsMenu;
    }
    /**
     * Constructs the overall infrastructure of the game board. 
     * Creates the 8 x 8 grid that the player will player on.
     * Each square on the grid is constructed in the Square class.
     * 
     * Contains a drawBoard() method that repaints the entire grid,
     * and a checkWin() method that checks if PlayerOne or PlayerTwo is out of pieces,
     * ending the game by showing a pop up.
     * 
     *  @author Matthew Wang and Ajeet Kotturu
     *  @version May 28, 2019
     *  @author  Period: 4th
     *  @author  Assignment: APCSFinalProject
     *
     *  @author  Sources: Software & Architecture Design (Youtube User)
     *
     *
     */
    private class BoardDisplay extends JPanel
    {
        ArrayList<Square> boardSquares = new ArrayList<Square>();
        /**
         * Creates the BoardDisplay JPanel using a GridLayout (8 x 8).
         * Creates Square objects for each square in the grid, and assigns
         * it an location according to the layout of the 2D array in
         * GameBoard, and sizes it according to desired dimension.
         */
        BoardDisplay()
        {
            super(new GridLayout(8, 8));
            for (int i = 0; i < game.getGame().length; i++)
            {
                for (int j = 0; j < game.getGame()[0].length; j++)
                {
                    Square sq = new Square(this, new Location(i,j));
                    this.boardSquares.add(sq);
                    add(sq);
                }
            }
            setPreferredSize(BOARD_FRAME);
            validate();
        }
        /**
         * Removes all Squares and calls drawSquare() for each square on
         * the board and adds it onto the board. Then calls the JPanel
         * to repaint and checking win condition.
         * @param board GameBoard object
         */
        public void drawBoard(GameBoard board)
        {
        	this.removeAll();
        	for (Square sq : boardSquares)
        	{
        		sq.drawSquare(board);
        		add(sq);
        	}
        	validate();
        	repaint();
        	checkWin();
        }
        /**
         * Checks the condition to win the game. Gets the size of the the ArrayList
         * containing PlayerOne pieces and the ArrayList containing PlayerTwo pieces.
         * If they have a size of 0, opens a pop up, displaying the player without the empty
         * ArrayList as the winner.
         */
        public void checkWin()
        {
        	if (game.getPlayerTwoPieces().size() == 0)
        	{
        		JOptionPane.showMessageDialog(GameFrame,
        			    		"Blue wins the match!",
        			    	    "Blue Wins",
        			    	    JOptionPane.PLAIN_MESSAGE);
        		
        	}
        	else if (game.getPlayerOnePieces().size() == 0)
        	{
        		JOptionPane.showMessageDialog(GameFrame, "Red wins the match!" , 
        				"Red Wins", 
        				JOptionPane.PLAIN_MESSAGE);
        	}
        }
    }
    /**
     * Square objects corresponds to a square on the gridLayout in BoardDisplay.
     * Each Square has a MouseListener that makes a selection and moves/attacks
     * with left click, removes selected pieces with right click, and 
     * displays the attack range when the mouse enters the Square (if ghost
     * is true) and removes it when the mouse exits it.
     * 
     * Has a color() method to properly assign the color of the Square, various
     * highlight methods to display possible moves and attack range, and has 
     * drawSquare() method that repaints the Square.
     * 
     * @author Matthew Wang and Ajeet Kotturu
     * 	@version May 28, 2019
     *  @author  Period: 4th
     *  @author  Assignment: APCSFinalProject
     *
     *  @author  Sources: Software & Architecture Design (Youtube User)
     *
     */
    private class Square extends JPanel
    {
        private Location loc;
        /**
         * Creates a Square JPanel using the GridBagLayout(), or 
         * one square within the 8 x 8 gird in BoardDisplay. Sets the
         * dimensions of the square, sets the correct color for the square
         * as well as the piece and health bar for the pieces on the board.
         * 
         * Also adds a MouseListener for the Square. 
         * 
         * @param bd Corresponding Board Display
         * @param l Location of Square on 2D Array
         */
        Square (BoardDisplay bd, Location l)
        {
            super(new GridBagLayout());
            this.loc = l;
            setPreferredSize(TILE_FRAME);
            color();
            setBoard(game);
            setBorder(BorderFactory.createLineBorder(Color.BLACK));
            addMouseListener(new MouseListener() {
            	/**
            	 * MouseListener looks for right clicks and left clicks. 
            	 * Right click - if a player has selected a piece to move/attack,
            	 * right click will set selectedPiece to null and cancel the play.
            	 * Left click - if a player has not selected a piece, the selected 
            	 * Square will become selectedPiece if the Square has a piece in the 
            	 * corresponding Game Board. Checks if selectedPiece is one of the
            	 * pieces of the player making the move. If not, does nothing.
            	 * If a player has already selected a piece, depending on whether TurnDecider
            	 * contains move or attack, the gameboard will highlight possible moves/attack
            	 * range. If the move/attack is successful and returns true, advances the turn
            	 * and makes the play. If not, the play is canceled and the turn stays the same.
            	 */
				@Override
				public void mouseClicked(MouseEvent e) {
					if (SwingUtilities.isRightMouseButton(e))
					{
						selectedPiece = null;
						moveToPiece = null;
					}
					else if (SwingUtilities.isLeftMouseButton(e))
					{
						if (turnDecider.contains("Move"))
						{
							if (selectedPiece == null)
							{
								if(game.getGame()[loc.getX()][loc.getY()] != null && 
										turnDecider.contains(game.getGame()
												[loc.getX()][loc.getY()].getPlayer()))
								{
									selectedPiece = game.getGame()[loc.getX()][loc.getY()];
									if (selectedPiece == null)
									{
										selectedPiece = null;
									}
								}
							}
							else
							{
								moveToPiece = game.getGame()[loc.getX()][loc.getY()];
								if (moveToPiece == null)
								{
									boolean x = selectedPiece.move(loc);
									if (x)
									{
										turnDecider.advanceTurn();
									}
								}
								selectedPiece = null;
								moveToPiece = null;
							}
						}
						else if (turnDecider.contains("Attack"))
						{
							if (selectedPiece == null)
							{
								if(game.getGame()[loc.getX()][loc.getY()] != null && 
										turnDecider.contains(game.getGame()
												[loc.getX()][loc.getY()].getPlayer()))
								{
									selectedPiece = game.getGame()[loc.getX()][loc.getY()];
									if (selectedPiece == null)
									{
										selectedPiece = null;
									}
								}
							}
							else
							{
								moveToPiece = game.getGame()[loc.getX()][loc.getY()];
								if (moveToPiece != null && !moveToPiece.getPlayer()
										.equals(selectedPiece.getPlayer()))
								{
									boolean x = selectedPiece.attack(moveToPiece);
									if (x)
									{
										turnDecider.advanceTurn();
									}
								}
								selectedPiece = null;
								moveToPiece = null;
							}
						}							
										
					}
					SwingUtilities.invokeLater(new Runnable() {
						/**
						 * tells the BoardDisplay to redraw the Board through
						 * drawBoard()
						 */
						@Override
						public void run() {
							bd.drawBoard(game);
						}
					});
				}
				/**
				 * When the mouse hovers over this Square, if the Square has a piece and
				 * the boolean ghost is true, assigns ghostPiece to the piece if selectedPiece
				 * is still null. Updates movePanel with statistics of the piece.
				 * 
				 * This is used to highlight the attack range when mouse is hovered over this square.
				 */
				@Override
				public void mouseEntered(MouseEvent arg0) {
					
					if (game.getGame()[loc.getX()][loc.getY()] != null)
					{
						int hp = game.getGame()[loc.getX()][loc.getY()].getHealth();
						movesPanel.updateHealth(game.getGame()[loc.getX()][loc.getY()], hp);
						if (ghost)
						{
							if(selectedPiece == null)
							{
								ghostPiece = game.getGame()[loc.getX()][loc.getY()];
							}
						}
					}				
					SwingUtilities.invokeLater(new Runnable() {
						/**
						 * tells the BoardDisplay to redraw the Board through
						 * drawBoard()
						 */
						@Override
						public void run() {
							bd.drawBoard(game);
						}
					});
				}
				/**
				 * When the mouse stops hovering the Square, undoes everything from when the 
				 * mouse is hovered over the Square.
				 */
				@Override
				public void mouseExited(MouseEvent arg0) {
					movesPanel.updateHealth(game.getGame()[loc.getX()][loc.getY()], 0);
					ghostPiece = null;
					SwingUtilities.invokeLater(new Runnable() {
						/**
						 * tells the BoardDisplay to redraw the Board through
						 * drawBoard()
						 */
						@Override
						public void run() {
							bd.drawBoard(game);
						}
					});
				}

				@Override
				public void mousePressed(MouseEvent arg0) {	
				}

				@Override
				public void mouseReleased(MouseEvent arg0) {	
				}
            });
            validate();
        }
        /**
         * Draws the Square, assigning it its color, calling 
         * setBoard() to assign the proper image, and whether
         * it is a move or attack turn, highlights the corresponding 
         * attack range or possible moves. Also, if the Square fits the 
         * criteria of ghostAttacks(), the background color of the Square
         * is changed accordingly.
         * @param board GameBoard object
         */
        public void drawSquare(GameBoard board) 
        {
			color();
			setBoard(board);
			if (turnDecider.contains("Move"))
			{
				highlightMoves(board);
			}
			else if (turnDecider.contains("Attack"))
			{
				highlightAttacks(board);
			}
			ghostAttacks(board);
			validate();
			repaint();
		}
        /**
         * setBoard() gets the piece located at this Square's location. 
         * If it exists, calls the image (.png) file through toStrings from the Piece's 
         * methods and filePath, scaling the image and assigns it to the Square. Also creates a rectangle
         * that signifies the piece's health (health bar), and adds it to the image, matching
         * the color of the piece.
         * @param board GameBoard object
         */
		private void setBoard(GameBoard board)
        {
            this.removeAll();
            GamePiece piece = board.getGame()[loc.getX()][loc.getY()];
            if (piece != null)
            {
                try 
                {
                    BufferedImage image = ImageIO.read(new File(filePath + piece.toString() + 
                            piece.getPlayer() + ".png"));
                    int wid = image.getWidth();
                    wid /= piece.getMaxHealth();
                    Graphics2D gr = image.createGraphics();
                    int health = piece.getHealth();
                    if("PlayerOne" == piece.getPlayer())
                        gr.setColor( Color.BLUE );
                    else
                        gr.setColor( Color.RED );
                    for(int i = 0; i < health; i++)
                        gr.fillRect( wid * i, 0, wid - 10, 60 );
                    Image newImage = image.getScaledInstance(80, 80, Image.SCALE_DEFAULT);
                    
                    add(new JLabel(new ImageIcon(newImage)));
                } catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
        }
		/**
		 * Checks if there is a selectedPiece. If there is, gets the Location ArrayList
		 * from possibleMoves() of the selectedPiece. If this Square is in the ArrayList and
		 * the Location of the Square does not have a piece, sets the Square's background to
		 * green. Helps the player to know which Squares the Piece can be moved.
		 * @param game GameBoard Object
		 */
		private void highlightMoves(GameBoard game) 
		{
			GamePiece p = game.getGame()[loc.getX()][loc.getY()];
			if (selectedPiece != null)
			{
				for (Location l : selectedPiece.possibleMoves())
				{
					if(l.getX() == loc.getX() && l.getY() == loc.getY() 
							&& game.getGame()[l.getX()][l.getY()] == null)
					{
						setBackground(green);
					}
				}
			}
		}
		/**
		 * Checks if selectedPiece contains a piece. If it does, gets the Location 
		 * ArrayList possibleAttacks() from the piece. If this Square is in the list
		 * it will set the background color to orange.
		 * Helps the Player to know the attack range of the piece while making an Attack.
		 * @param game GameBoard Object
		 */
		private void highlightAttacks(GameBoard game) 
		{
			GamePiece p = game.getGame()[loc.getX()][loc.getY()];
			if (selectedPiece != null)
			{
				for (Location l : selectedPiece.possibleAttacks())
				{
					if(l.getX() == loc.getX() && l.getY() == loc.getY())
					{
						setBackground(orange);
					}
				}
			}
		}
		/**
		 * Checks if ghostPiece contains a piece and selectedPiece does not . If
		 * the conditions are met, the method then gets the Location 
		 * ArrayList possibleAttacks() from the piece. If this Square is in the list
		 * it will set the background color to gray.
		 * Helps the player to see the attack range of an object when the mouse is hovered
		 * over a square with a piece. Does not work when ghost is false.
		 * @param game GameBoard Object
		 */
		private void ghostAttacks(GameBoard game)
		{
			GamePiece p = game.getGame()[loc.getX()][loc.getY()];
			if (ghostPiece != null && selectedPiece == null)
			{
				for (Location l : ghostPiece.possibleAttacks())
				{
					if(l.getX() == loc.getX() && l.getY() == loc.getY())
					{
						setBackground(Color.GRAY);
					}
				}
			}
		}
		/**
		 * Assigns the color of the Square. This color is overridden by all highlight methods.
		 * The method assigns brown or white color so that it creates an alternating pattern throughout the
		 * BoardDisplay. Two touching tiles will never have the same color, and uses the Square's location
		 * to assign white or brown color.
		 */
        private void color() 
        {
            if (loc.getX() % 2 == 0)
            {
                if (loc.getY() % 2 == 0)
                {
                    setBackground(white);
                }
                else
                {
                    setBackground(brown);
                }
            }
            else
            {
                if (loc.getY() % 2 != 0)
                {
                    setBackground(white);
                }
                else
                {
                    setBackground(brown);
                }
            }
        }
        /**
         * Accessor method for the Square's Location 
         * @return Location of Square
         */
        public Location getSquareLocation()
        {
        	return loc;
        }
    }
    /**
     * Creates a object that determines the current turn of the board game.
     * The turn order is this:
     * PlayerOne Moves
     * PlayerTwo Moves
     * PlayerTwo Attacks
     * PlayerOne Moves
     * 
     * Uses a string to keep track of the turn, including both elements of a turn
     * (move/attack and PlayerOne/PlayerTwo).
     * 
     * 	@author  Matthew Wang and Ajeet Kotturu
     * 	@version May 28, 2019
     *  @author  Period: 4th
     *  @author  Assignment: APCSFinalProject
     *
     *  @author  Sources: None
     *
     */
    public class TurnDecider
    {
    	String turn;
    	/**
    	 * sets a new TurnDecider object to the first turn, PlayerOne Move
    	 */
    	TurnDecider()
    	{
    		turn = "PlayerOne Move";
    	}
    	/**
    	 * Overridden method of contains in the String class
    	 * Checks to see if the String player is in turn
    	 * through indexOf(). If it is not -1,
    	 * returns true, else returns false.
    	 * @param player string to check in turn
    	 * @return if player is in turn
    	 */
    	public boolean contains(String player) 
    	{
			if (turn.indexOf(player) != -1)
			{
				return true;
			}
			return false;
		}
    	/**
    	 * When called, checks to see what turn currently is and sets turn
    	 * to the string corresponding to the next move. If the turn goes from PlayerOne
    	 * to PlayerTwo, flips the board so the player making the move is always on the 
    	 * bottom.
    	 * 
    	 * Updates movesPanel accordingly, both the player making the move and the play being
    	 * made.
    	 */
		public void advanceTurn()
    	{
    		if (turn.equals("PlayerOne Move"))
    		{
    			turn = "PlayerTwo Move";
    			game.turnGameBoard();
    			movesPanel.updatePlayer(this);
    			movesPanel.updatePlay(this);
    		}
    		else if (turn.equals("PlayerTwo Move"))
    		{
    			turn = "PlayerTwo Attack";
    			movesPanel.updatePlay(this);
    		}
    		else if (turn.equals("PlayerTwo Attack"))
    		{
    			turn = "PlayerOne Attack";
    			game.turnGameBoard();
    			movesPanel.updatePlayer(this);
    			movesPanel.updatePlay(this);
    		}
    		else 
    		{
    			turn = "PlayerOne Move";
    			movesPanel.updatePlay(this);
    		}
    	}
    }
    /**
     * 
     *  @author  Matthew Wang and Ajeet Kotturu
     * 	@version May 28, 2019
     *  @author  Period: 4th
     *  @author  Assignment: APCSFinalProject
     *
     *  @author  Sources: None
     *
     *
     */
    private class MovesPanel extends JPanel 
    {

        private JPanel movebox = new JPanel();
        private JPanel attackbox = new JPanel();
        private JPanel combined = new JPanel();
        private JPanel playerbox = new JPanel();
        private JPanel playbox = new JPanel();
        private JPanel h1 = new JPanel();
        private JPanel h2 = new JPanel();
        private JPanel h3 = new JPanel();
        private JPanel h4 = new JPanel();
        private JPanel h5 = new JPanel();
        private JPanel h6 = new JPanel();
        private JPanel h7 = new JPanel();
        private JPanel h8 = new JPanel();
        private JPanel h9 = new JPanel();
        private JPanel h10 = new JPanel();
        private JPanel h21 = new JPanel();
        private JLabel player;
        private JLabel play;
        private JPanel currenthp = new JPanel();
        private JLabel hp = new JLabel("");
        private JLabel h11 = new JLabel("Hover Your Mouse Over");
        private JLabel h12 = new JLabel("a Game Piece To Check");
        private JLabel h13 = new JLabel("Its Attack Range.");
        private JLabel h14 = new JLabel("Current Health:");
        private JLabel type = new JLabel("");
        private JLabel color = new JLabel("");
        private JLabel damage = new JLabel("");
        
        private Dimension MOVES_PANEL = new Dimension(150, 300);
        /**
         * creates a movesPanel, which is a BorderLayout. MovesPanel consists of 
         * two parts:
         * Combined - this JPanel displays the current player making the play and
         * the type of play, move or attack
         * Currenthp - this Jpanel displays the statistics of ghostPiece. Includes 
         * the health, attack, piece type and color of the piece.
         * 
         * Adds Combined to the North of the BorderLayout in movesPanel, and adds
         * Currenthp to the Center.
         * @param td TurnDecider to display
         */
        MovesPanel(TurnDecider td) 
        {
            this.setLayout(new BorderLayout());
            this.setBackground(Color.decode("#ff0000"));
            JLabel heading = new JLabel("Making The Next Move:");
            if(td.contains("PlayerOne"))
            {
            	player = new JLabel("Blue");
            }
            else
            {
            	player = new JLabel("Red");
            }
            if(td.contains("Move"))
            {
            	play = new JLabel("Move");
            }
            else
            {
            	play = new JLabel("Attack");
            }
            JLabel am = new JLabel("Next Action to Execute:");

            combined.add(movebox.add(heading));
            combined.add(playerbox.add(player));
            player.setPreferredSize(new Dimension(100, 75));
            player.setHorizontalAlignment(JLabel.CENTER);
            combined.add(attackbox.add(am));
            combined.add(playbox.add(play));
            play.setPreferredSize(new Dimension(100, 75));
            play.setHorizontalAlignment(JLabel.CENTER);
            combined.setPreferredSize(MOVES_PANEL);
            currenthp.add(h9.add(h14));
            currenthp.add(h4.add(hp));
            hp.setPreferredSize(new Dimension(100, 50));
            hp.setHorizontalAlignment(JLabel.CENTER);
            currenthp.add(h10.add(new JLabel("Attack Damage:")));
            currenthp.add(h21.add(damage));
            damage.setPreferredSize(new Dimension(100, 50));
            damage.setHorizontalAlignment(JLabel.CENTER);
            currenthp.add(h5.add(new JLabel( "Piece Type:")));
            currenthp.add(h6.add(type));
            type.setPreferredSize(new Dimension(100, 50));
            type.setHorizontalAlignment(JLabel.CENTER);
            currenthp.setPreferredSize(MOVES_PANEL);
            currenthp.add(h7.add(new JLabel( "Piece Color:")));
            currenthp.add(h8.add(color));
            color.setPreferredSize(new Dimension(100, 50));
            color.setHorizontalAlignment(JLabel.CENTER);
            currenthp.add(h1.add(h11));
            currenthp.add(h2.add(h12));
            currenthp.add(h3.add(h13));
            combined.setBackground(Color.decode("#8080ff"));
            currenthp.setBackground(Color.decode("#ffffff"));
            this.add(combined, BorderLayout.NORTH);
            this.add(currenthp, BorderLayout.CENTER);
            this.setVisible(true);
        }
        /**
         * Updates the player in combined. If the TurnDecider contains()
         * PlayerOne, sets the JLabel text to Blue and changes the background
         * color to blue. If it is PlayerTwo, sets the JLabel text to Red 
         * and changes the background color to red.
         * @param td TurnDecider object
         */
        public void updatePlayer(TurnDecider td)
        {
        	if(td.contains("PlayerOne"))
            {
            	player.setText("Blue");
            	 combined.setBackground(Color.decode("#8080ff"));
            }
            else
            {
            	player.setText("Red");
            	 combined.setBackground(Color.decode("#ff8080"));
            }
        }
        /**
         * Updates the play in combined. If the TurnDecider contains()
         * Move, sets the JLabel text to Move. If it is Attack, sets the JLabel text to Attack.
         * @param td TurnDecider object
         */
        public void updatePlay(TurnDecider td)
        {
        	if(td.contains("Move"))
            {
            	play.setText("Move");
            }
            else
            {
            	play.setText("Attack");
            }
        }
        /**
         * Updates the statistics in Currenthp. Gets the pieceType of p
         * and prints Knight for PieceOne, CrossBow for PieceTwo, and
         * LongBow for PieceThree. Changes the color of Currenthp depending upon
         * if the piece is healthy or not.
         * Green - healthy
         * Orange - damaged
         * Red - severely damaged
         * Also gets the health and attack of the piece and updates them accordingly in
         * hp and damage.
         * Gets the player string the piece. If it is "PlayerOne", changes color to "Blue".
         * If "PlayerTwo", changes color to "Red".
         * 
         * @param p GamePiece
         * @param i health of p
         */
        public void updateHealth(GamePiece p, int i)
        {
        	if (i == 0)
        	{
        		hp.setText("");
        		type.setText("");
        		color.setText("");
        		damage.setText("");
        		currenthp.setBackground(Color.decode("#ffffff"));
        	}
        	else
        	{
        		hp.setText(i + "");
        		if (p.getPieceType().equals(PieceType.ONE))
        		{
        			type.setText("Knight");
        			if (i >= 6)
        			{
        				currenthp.setBackground(Color.decode("#66ff99"));
        			}
        			else if (i >= 3)
        			{
        				currenthp.setBackground(Color.decode("#ffff66"));
        			}
        			else
        			{
        				currenthp.setBackground(Color.decode("#ff3333"));
        			}
        		}
        		else if (p.getPieceType().equals(PieceType.TWO))
        		{
        			type.setText("CrossBow");
        			if (i >= 4)
        			{
        				currenthp.setBackground(Color.decode("#66ff99"));
        			}
        			else if (i >= 2)
        			{
        				currenthp.setBackground(Color.decode("#ffff66"));
        			}
        			else
        			{
        				currenthp.setBackground(Color.decode("#ff3333"));
        			}
        		}
        		else if (p.getPieceType().equals(PieceType.THREE))
        		{
        			type.setText("LongBow");
        			if (i >= 2)
        			{
        				currenthp.setBackground(Color.decode("#66ff99"));
        			}
        			else 
        			{
        				currenthp.setBackground(Color.decode("#ff3333"));
        			}
        		}
        		if (p.getPlayer().equals("PlayerOne"))
        		{
        			color.setText("Blue");
        		}
        		else if (p.getPlayer().equals("PlayerTwo"))
        		{
        			color.setText("Red");
        		}
        		
        		damage.setText(p.getAttack() + "");
        	}
        }
        /**
         * Updates Currenthp depending if ghost is true or false.
         * If true, adds info that hovering a piece will show attack range.
         * If false, remove this info.
         */
        public void updateToggle()
        {
        	if (ghost)
        	{
        		currenthp.add(h1.add(h11));
                currenthp.add(h2.add(h12));
                currenthp.add(h3.add(h13));
                this.add(currenthp, BorderLayout.CENTER);
        	}
        	else if (!ghost)
        	{
        		currenthp.remove(h1.add(h11));
                currenthp.remove(h2.add(h12));
                currenthp.remove(h3.add(h13));
                this.add(currenthp, BorderLayout.CENTER);
        	}
        }
    }

}