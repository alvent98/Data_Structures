import java.io.*;
import java.util.Random;
import java.lang.Math.*;

public class TxtWriter{
		private int[] array;
		Random rand = new Random();
		// higher and lower value of random class.
		int low = 20;
		int high = 2000;
		// An empty constructor
		public TxtWriter() {}
		// Creates a txt file with appropriate format.
		public void createFile (String path,int procedures) {

		File f = null;
		BufferedWriter writer = null;
		
		// Create the file
		try	{
			f = new File(path);
		} catch (NullPointerException e) {
			System.err.println ("File couldn't be created.");
		}
		// Create the buffer to write at file
		try	{
			writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(f)));
		} catch (FileNotFoundException e) {
			System.err.println("Error while opening file for writing!");
		}
		// Write all the data at file (number of processors, number of procedures and the time each procedure takes.)
		try {
			int processors = (int) Math.floor(Math.sqrt(procedures));
			// Write numbers of processors and procedures
			writer.write(Integer.toString(processors)+"\n"+Integer.toString(procedures)+"\n");
			for(int i=0;i<procedures;i++)  {
				// Create a random number between high and low, and make it unsigned ("Integer.MAX_VALUE")
				int randomTime = rand.nextInt(high - low) + low & Integer.MAX_VALUE;
				// Write the random number at a new line
				writer.write(Integer.toString(randomTime));
				// If this line is not the last one, goto next line in txt
				if(i!=procedures-1) writer.write("\n");
			}			
		} catch (IOException e) {
			System.err.println("Write error!");
		}
		
		try {
			writer.close();
		}
		catch (IOException e) {
			System.err.println("Error closing file.");
		}
	}
}