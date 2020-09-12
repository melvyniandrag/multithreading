package reduce.sum;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.io.File;
import java.io.FileNotFoundException;

public class SingleThreadSum2 {
	private static int total = 0;
	
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
	
	private static void computeSum(List<Integer> list) {
		for(Integer i : list) {
			total += i;
			try {
				Thread.sleep(10);
			}
			catch(Exception e) {
				
			}
		}
	}
	
	public static void main(String[] args) {
		List<Integer> numbers = readIntFile("AThousandNumbers.txt");
		
		if(numbers.size() == 0) {
			System.out.println("Nothing to be done! Exiting.");
			return;
		}
		//t_0
		final long T0 = System.nanoTime();
		computeSum(numbers);
		//t_f
		final long TF = System.nanoTime();

		
		
		System.out.println("Computed the sum: " + total);
		System.out.println(String.format(
			"The sum took %d nanoseconds to compute.",
			TF - T0)
		);

	}

}
