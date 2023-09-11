package principal;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import model.entities.Product;

public class Principal {

	public static void main(String[] args) {
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);

		List<Product> product = new ArrayList<>();

		System.out.print("Enter Product data: ");
		Integer n = sc.nextInt();

		for (int i = 0; i < n; i++) {
			System.out.print("Name: ");
			String name = sc.next();
			System.out.print("Price: ");
			Double price = sc.nextDouble();
			System.out.print("Quantity: ");
			Integer quantity = sc.nextInt();

			product.add(new Product(name, price, quantity));

		}

		System.out.print("Enter a folder path: ");
		String path = sc.next();

		try (BufferedWriter bw = new BufferedWriter(new FileWriter(path + "\\out.csv"))) {
			for (Product prod : product) {
				bw.write(prod.getName() + ", " + String.format(" %.2f", prod.getPrice()) + ", " + prod.getQuantity());
				bw.newLine();
			}

		boolean success = new File(path + "\\out").mkdir();
		try (BufferedWriter bw1 = new BufferedWriter(new FileWriter(path + "\\out" + "\\summary.csv"))) {
			for (Product prod : product) {
				bw1.write(prod.getName() + ", " + String.format(" %.2f", + prod.totalProduct()));
				bw1.newLine();
			}
		}

		} catch (IOException e) {
			System.out.println("Error writing file: " + e.getMessage());
		}

		sc.close();
	}

}


/*


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;



public class Program {

	public static void main(String[] args) throws ParseException {

		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);

		List<Product> list = new ArrayList<>();

		System.out.println("Enter file path: ");
		String sourceFileStr = sc.nextLine();

		File sourceFile = new File(sourceFileStr);
		String sourceFolderStr = sourceFile.getParent();
		
		boolean success = new File(sourceFolderStr + "\\out").mkdir();
		
		String targetFileStr = sourceFolderStr + "\\out\\summary.csv";

		try (BufferedReader br = new BufferedReader(new FileReader(sourceFileStr))) {

			String itemCsv = br.readLine();
			while (itemCsv != null) {

				String[] fields = itemCsv.split(",");
				String name = fields[0];
				double price = Double.parseDouble(fields[1]);
				int quantity = Integer.parseInt(fields[2]);

				list.add(new Product(name, price, quantity));

				itemCsv = br.readLine();
			}

			try (BufferedWriter bw = new BufferedWriter(new FileWriter(targetFileStr))) {

				for (Product item : list) {
					bw.write(item.getName() + "," + String.format("%.2f", item.total()));
					bw.newLine();
				}

				System.out.println(targetFileStr + " CREATED!");
				
			} catch (IOException e) {
				System.out.println("Error writing file: " + e.getMessage());
			}

		} catch (IOException e) {
			System.out.println("Error reading file: " + e.getMessage());
		}

		sc.close();
	}
}

*/
