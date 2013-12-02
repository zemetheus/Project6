/** 
     * Cogan Shimizu
     * CS-1180L-90
     * Kurtis Glendenning
     * Michael Ondrasek
     * 
     * PURPOSE:
     * This static class verifies the integrity of the filesystem as well
     * ensuring the existence of all necessary graphics resources.
     * 
     */

import java.io.IOException;
import java.nio.file.*;
import java.util.*;

public class EnvironmentManager
{
	/**
	 * setUpEnvironment method is the workhorse of this static class; it verifies
	 * the integrity of the filesystem by ensuring that the Images directory
	 * and requiredResources.txt file both exist, as well as checking
	 * the actual present resources against those specified in the 
	 * requiredResources.txt file.
	 * 
	 * The system will exit with a message upon failure to return true at any
	 * point in the verification as these are non-recoverable problems.
	 */
	public static void setUpEnvironment()
	{
		//working directory
		String wdir = System.getProperty("user.dir");
		
		//Paths for main folders
		Path images = Paths.get(wdir,"Images"),
			 requiredResourcesFile = Paths.get(wdir,"requiredResources.txt");
		
		//required resources
		ArrayList<String> requiredResources, availableResources;
		
		if(checkFile(images) && checkFile(requiredResourcesFile))
		{
			requiredResources = getRequiredResourcesList(requiredResourcesFile);
			availableResources = getAvailableResourcesList(images);
			
			for(String s : requiredResources)
			{
				if(availableResources.contains(s))
				{
					System.out.println(s+" exists.");
				}
				else
				{
					System.out.println("Error");
					System.exit(1);
				}
					
			}
			System.out.println("File System is intact.");
		}
		else
		{
			System.out.println("The File System is not intact.");
			System.out.println("Please verify that the Images folder " +
							   "and requiredResourceFile exist.");
			System.exit(1);
		}
	}
	/**
	 * getAvailableResourcesList method obtains all resources present in the Images folder,
	 * located at Path target;.
	 * 
	 * @param target Path object specifying location of the Images directory
	 * @return list ArrayList<String> of available resources
	 */
	public static ArrayList<String> getAvailableResourcesList(Path target)
	{
		ArrayList<String> list = new ArrayList<>();
		String fileName;
		
		try (DirectoryStream<Path> stream = Files.newDirectoryStream(target))
		{
		    for (Path file: stream)
		    {
		    	fileName = file.getFileName().toString();
		        list.add(fileName);
		    }
		    
		    stream.close();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		
		return list;
	}
	/**
	 * getRequiredResourcesList method gets a list of required resources from the
	 * requiredResources.txt file at Path target; it loads it into an ArrayList<String>
	 * and passes it to the setUpEnvironment method.
	 * 
	 * @param target Path object specifying requiredResources.txt location
	 * 
	 * @return list ArrayList<String> holding requiredResources
	 */
	public static ArrayList<String> getRequiredResourcesList(Path target)
	{
		ArrayList<String> list = new ArrayList<>();
		String line;
		
		try
		{
			Scanner reader = new Scanner(target);
			
			while(reader.hasNextLine())
			{
				line = reader.nextLine();
				
				list.add(line);
			}
			
			reader.close();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		
		return list;
	}
	/**
	 * checkFile method checks whether or not a file exists at the specified Path target.
	 * @param target Path object detailing a file location
	 * @return true/false on Files.exist(target) outcome
	 */
	public static boolean checkFile(Path target)
	{
		return Files.exists(target);
	}	
}
