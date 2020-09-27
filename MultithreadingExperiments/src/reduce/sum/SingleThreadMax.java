package reduce.sum;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.io.File;
import java.io.FileNotFoundException;

public class SingleThreadMax {
	private static int maximum = 0;
	
	private static ArrayList<Integer> readIntFile(String filename){
		File f = new File( filename );
		Scanner s = null;
		try{
			s= new Scanner(f);
		}
		catch (FileNotFoundException ex) {
			System.err.println(ex.getMessage());
			return new ArrayList<Integer>();
		}
		ArrayList<Integer> arr = new ArrayList<Integer>();
		while(s.hasNextInt()) {
			arr.add(s.nextInt());
		}
		s.close();
		return arr;
	}
	
	private static void computeMax(List<Integer> list) {
		maximum = list.get(0);
		for(int i = 1; i < list.size(); ++i) {
			if(list.get(i) > maximum) {
				maximum = list.get(i);
			}
		}
	}
	
	public static void main(String[] args) {
		List<Integer> numbers = readIntFile("AThousandNumbers.txt");
		
		if(numbers.size() == 0) {
			System.out.println("Nothing to be done! Exiting.");
			return;
		}
		
		computeMax(numbers);		

		System.out.println("Computed the max: " + maximum);

	}

}
