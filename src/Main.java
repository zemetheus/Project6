/** 
     * Cogan Shimizu
     * CS-1180L-90
     * Kurtis Glendenning
     * Michael Ondrasek
     * 
     * PURPOSE:
     * MAIN:
     * Welcome to Alien Invaders!
     * You are the lonely hero saving the galaxy from a foreign, violent and malicious menace!
     * 
     * Progress through the levels destroying all the aliens before you!
     * Be sure to collect upgrades along the way!
     * 
     * The main class, here, instantiates a new game based on player input (first time is free).
     */

public class Main
{
	public static void main(String[] args)
	{
		EnvironmentManager.setUpEnvironment();
		
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
