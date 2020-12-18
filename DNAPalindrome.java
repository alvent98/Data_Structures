import java.util.Scanner;

public class DNAPalindrome {
	public static void main(String args[]) {
		//Instantiation of a double queue (from subquestion A)
		CharDoubleEndedQueueImpl<Character> queue = new CharDoubleEndedQueueImpl();
		// Is the String valid (contains only capitals A, T, G, or C) ?
		boolean valid = true;
		// Is a Watson Crick complemented palindrome?
		boolean WCCompPalindrome = true;
		// The size of the queue.
		int size;
		Scanner in = new Scanner(System.in);
		// Prompt message
		System.out.println("Input the DNA sequence: (Only capital A,T,C,G are valid) ");
		String sequence = in.nextLine();
		for(int i = 0;i < sequence.length();i++) {
			// Is current character from inserted string invalid? 
			if(isInvalid(sequence.charAt(i))) {
				System.out.println("The sequence is not valid.");
				valid = false;
				break;
			}
		}
		// If the above for loop didn't found invalid data:
		if(valid) {
			// Put every char of the String in the queue
			for(int i = 0;i < sequence.length();i++) {
				queue.addFirst(sequence.charAt(i));
			}
			size = queue.size(); 
			// Check if the first half of the queue is equal with the complement of the last half of the queue
			// We put the floor of the middle of the queue as a condition for the termination, to avoid nullPointerException.
			for(int i = 0; i < size/2 -1;i++) {
				// If you find that the current char of first half is not equal with the current char of the last half, put the flag false, and break.
				if(queue.removeFirst() != getSupplement(queue.removeLast())) {
					WCCompPalindrome = false;
					break;
				}
			}
		}
		// If you didn't found invalid data, and it is a Watson Crick complemented palindrome:
		if(WCCompPalindrome && valid) {
			System.out.println("It is a Watson-Crick complemented palindrome!");
			// Print the equal complement of the String:
			for(int i = sequence.length()-1;i>=0;i--) System.out.print(sequence.charAt(i));
		} else {
			// Else print a dissapointment message:
			if(valid) System.out.println("It is not a Watson-Crick complemented palindrome.");
		}
		// And close the stream because we might get cold:
		in.close();
		
		
	}
	// Is the not char a DNA nucleotide? (A or T or C or G)
	public static boolean isInvalid(char c) {
		return !(c=='A' || c=='T' || c=='G' || c=='C');
	}
	// Returns the supplement of each DNA nucleotide.
	public static char getSupplement(char c) {
		if(c=='A') {
			return 'T';
		} else if(c=='T') {
			return 'A';
		} else if(c=='G') {
			return 'C';
		} else {
			return 'G';
		}
	}
}