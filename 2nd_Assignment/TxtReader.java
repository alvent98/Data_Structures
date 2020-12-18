import java.io.*;

public class TxtReader{
	// An empty constructor
	public TxtReader() {}
	// the method that reads a txt file with appropriate format.
	public static int[] readTxt(String name){
		File f = null;
		BufferedReader in1 = null;
		BufferedReader in2 = null;
		int numOfLines=0;
		int currentline = 0;
		String line;
		int[] array = new int[1];
		// Try to find the file
		try {			
            f = new File(name);			
        } catch (NullPointerException e) {		
		    System.out.println("File not found.");
        }
		// Try to create a buffer to open the file
		try{
			in1 = new BufferedReader(new FileReader(f));
		} catch(IOException e) {
			System.out.println("Error opening file!");
		}
		// Try to read the file's data
		try{
			// Count the lines of the file
			while(true) {
				line = in1.readLine();
				if(line==null) break;
				numOfLines++;
			}
			// Create an array with same number of cells as the lines of the txt
			array = new int[numOfLines];
		} catch (IOException e) {
				System.out.println("Error while counting a line.");
		}
		// Try to close the first buffer for the counting of lines.
		try{
			in1.close();
		} catch (IOException e) {
            System.err.println("Error closing file.");
        }
		//Try to create a buffer to open the file
		try{
			in2 = new BufferedReader(new FileReader(f));
		} catch(IOException e) {
			System.out.println("Error opening file!");
		}
		// Try to read the contents of file.
		try {
			for(int i=0;i<numOfLines;i++) {
				currentline = i;
				line = in2.readLine();
				line = line.trim();
				// Check if the line has any other chars other than digits.
				for(int y=0;y<line.length();y++) {
					if(!Character.isDigit(line.charAt(y))) {
						System.out.println("Found other char than digit. Possibly found a non integer number.");
						break;
					}
				}
				// put the data you red in the array.
				array[i] = Integer.parseInt(line);
			}
			// Warning in case format is wrong (Number of procedures declared not equal with the number of procedures that follow.)
			if(array[1]!=numOfLines-2) {
				System.out.println("File in wrong format: Number of procedures declared not equal");
				System.out.println("with the number of procedures that follow.");
			}
		} catch (IOException e) {
			System.out.println("Error while reading  line no"+currentline);
		}
		// Try to close the first buffer for the reading of file.
		try{
			in2.close();
		} catch (IOException e) {
            System.err.println("Error closing file.");
        }
		return array;
	}		
}