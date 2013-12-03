/** 
     * Cogan Shimizu
     * CS-1180L-90
     * Kurtis Glendenning
     * Michael Ondrasek
     * 
     * PURPOSE:
     * This class holds only a single method
     * for pausing the thread.
     */
public class Pauser
{
	/**
     * Pause command used to sleep a thread.
     * pauseTime is in ms.
     */
    public static void pause(int pauseTime)
    {
    	try
	    {
    		Thread.sleep(pauseTime); //pause for 10 ms
	    }
	    catch (Exception e)
	    {
	    	System.out.println(e);
	        e.printStackTrace();
	    }
    }
}
