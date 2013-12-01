public class Main
{
	public static void main(String[] args)
	{
		//Game object contains main game loop
		Game game;
		boolean newGame = true;
		
		
		while(newGame)
		{
			//do not start a new game, unless otherwise specified.
			newGame = false;
			game = new Game();
			
			newGame = game.getStartNewGame();
		}
		
	}
}
