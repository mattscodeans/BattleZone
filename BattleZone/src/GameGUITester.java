
public class GameGUITester extends GameGUI 
{
	public static void main(String args[]) 
	{
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new GameGUI();
            }
        });
	}
}
