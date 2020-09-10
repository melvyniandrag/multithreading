package cs365;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MultithreadSumWithRunnable extends Thread{
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
	
	private synchronized static void updateTotal(int partialSum) {
		total += partialSum;
	}
	
	MultithreadSumWithRunnable(List<Integer> numbers, int startIndex, int count){
		int partialSum = 0;
		for(int i = startIndex; i < startIndex + count; ++i ) {
			partialSum += numbers.get(i);
		}
		updateTotal(partialSum);
	}
	
	private static void computeSum(List<Integer> numbers) throws InterruptedException{
		final int batchSize = numbers.size() / 4;


		Thread t1 = new Thread(new MultithreadSumWithRunnable(numbers, batchSize*0, batchSize));
		Thread t2 = new Thread(new MultithreadSumWithRunnable(numbers, batchSize*1, batchSize));
		Thread t3 = new Thread(new MultithreadSumWithRunnable(numbers, batchSize*2, batchSize));
		Thread t4 = new Thread(new MultithreadSumWithRunnable(numbers, batchSize*3, batchSize));
		
		final long T0 = System.nanoTime();

		t1.start();
		t2.start();
		t3.start();
		t4.start();
		
		t1.join();
		t2.join();
		t3.join();
		t4.join();
		
		final long TF = System.nanoTime();

		System.out.println(String.format(
			"The sum took %d nanoseconds to compute.",
			TF - T0));
	}
	
	public static void main(String[] args) {
		List<Integer> numbers = readIntFile("AMillionNumbers.txt");

		System.out.println(numbers.size());
		if(numbers.size() == 0) {
			System.out.println("Nothing to be done! Exiting.");
			return;
		}
		
		try{ 
			computeSum(numbers);		
		}
		catch(InterruptedException ex) {
			System.err.println(ex.getMessage());
			return;
		}

		System.out.println("Computed the sum: " + total);

		
	}
}
