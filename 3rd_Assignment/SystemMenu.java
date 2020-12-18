import java.util.Scanner;

public class SystemMenu {
	public static void main(String args[]) {
		int choice,id,isbn,copies;
		String city;
		Scanner in = new Scanner(System.in);
		Scanner in2 = new Scanner(System.in);
		ST warehouseNetwork = new ST();
		System.out.print(" 1. Add a warehouse. \n 2. Remove a warehouse. \n");
		System.out.print(" 3. Insert a book at a warehouse. \n 4. Remove a book from a warehouse. \n 5. Search a warehouse.\n");
		System.out.print(" 6. Search a book at a specific warehouse. \n 7. Search at which warehouses exist a book. \n");
		System.out.println(" 8. Print the whole network. \n 0.exit.");
		while(true) {
			System.out.println("Please select one of the choices.");
			choice = in.nextInt();
			if(choice==0) {
				break;
			} else if(choice==1) {
				System.out.println("Input the id of the warehouse, and the city where it is:");
				id = in.nextInt();
				city = in2.nextLine();
				warehouseNetwork.insertWarehouse(id,city);
			} else if(choice==2) {
				System.out.println("Input the id of the warehouse:");
				id = in.nextInt();
				warehouseNetwork.removeWarehouse(id);
			} else if(choice==3) {
				System.out.println("Input the id of the warehouse:");
				id = in.nextInt();
				System.out.println("Input the isbn of the book:");
				isbn = in.nextInt();
				System.out.println("Input the number of copies of the book:");
				copies = in.nextInt();
				warehouseNetwork.insertBookAtWarehouse(id,isbn,copies);
			} else if(choice==3) {
				System.out.println("Input the id of the warehouse:");
				id = in.nextInt();
				System.out.println("Input the isbn of the book:");
				isbn = in.nextInt();
				System.out.println("Input the number of copies of the book:");
				copies = in.nextInt();
				warehouseNetwork.insertBookAtWarehouse(id,isbn,copies);
			} else if(choice==4) {
				System.out.println("Input the id of the warehouse:");
				id = in.nextInt();
				System.out.println("Input the isbn of the book:");
				isbn = in.nextInt();
				warehouseNetwork.removeBook(id,isbn);
			} else if(choice==5) {
				System.out.println("Input the id of the warehouse:");
				id = in.nextInt();
				warehouseNetwork.searchByWarehouse(id);
			} else if(choice==6) {
				System.out.println("Input the id of the warehouse:");
				id = in.nextInt();
				System.out.println("Input the isbn of the book:");
				isbn = in.nextInt();
				warehouseNetwork.searchBookInWarehouse(id,isbn);
			} else if(choice==7) {
				System.out.println("Input the isbn of the book:");
				isbn = in.nextInt();
				warehouseNetwork.searchBook(isbn);
			} else if(choice==8) {
				System.out.println("Our great network is: ");
				warehouseNetwork.printTree(System.out);
			} else {
				System.out.println("No such number for a current function. Please retype:");
			}
		}
		in2.close();
		in.close();
	} 
}