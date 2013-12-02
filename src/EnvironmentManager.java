import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.Scanner;

public class EnvironmentManager
{
	public static void setUpEnvironment()
	{
		//working directory
		String wdir = System.getProperty("user.dir");
		
		//Paths for main folders
		Path images = Paths.get(wdir,"Images"),
			 requiredResourcesFile = Paths.get(wdir,"requiredResources.txt");
		
		//required resources
		ArrayList<String> resourcesRequired, resourcesAvailable;
		
		if(checkFile(images) && checkFile(requiredResourcesFile))
		{
			resourcesRequired = loadRequiredResourcesList(requiredResourcesFile);
			resourcesAvailable = loadAvailableResourcesList(images);
			
			for(String s : resourcesRequired)
			{
				if(!resourcesAvailable.contains(s));
				{
					System.out.println("Resource Unavailable: "+s);
					System.out.println("Please reverify installation");
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
	
	public static ArrayList<String> loadAvailableResourcesList(Path target)
	{
		ArrayList<String> list = new ArrayList<>();
		String fileName;
		try (DirectoryStream<Path> stream = Files.newDirectoryStream(target))
		{
		    for (Path file: stream)
		    {
		    	fileName = file.getFileName().toString();
		    	System.out.println(fileName);
		        list.add(fileName);
		    }
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		
		return list;
	}
	
	public static ArrayList<String> loadRequiredResourcesList(Path target)
	{
		ArrayList<String> list = new ArrayList<>();
		String line;
		try
		{
			Scanner reader = new Scanner(target);
			
			while(reader.hasNextLine())
			{
				line = reader.nextLine();
				System.out.println(line);
				list.add(line);
			}
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		
		return list;
	}
	
	public static boolean checkFile(Path target)
	{
		return Files.exists(target);
	}	
}
