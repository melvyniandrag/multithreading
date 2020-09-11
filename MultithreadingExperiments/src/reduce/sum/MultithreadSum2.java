package reduce.sum;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MultithreadSum2 extends Thread{
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
	
	private List<Integer> numbers;
	private int startIndex;
	private int count;
	
	MultithreadSum2(List<Integer> numbers, int startIndex, int count){
		this.numbers = numbers;
		this.startIndex = startIndex;
		this.count = count;
	}
	
	public void run() {
		int partialSum = 0;
		for(int i = startIndex; i < startIndex + count; ++i ) {
			partialSum += numbers.get(i);
			try {
				Thread.sleep(10);
			}
			catch(Exception e) {
				
			}
		}
		updateTotal(partialSum);
	}
	
	private static void computeSum(List<Integer> numbers) throws InterruptedException{
		final int N_BATCHES = 4;
		final int BATCH_SIZE = numbers.size() / N_BATCHES;

		ArrayList<Thread> threads = new ArrayList<Thread>();
		for(int i = 0; i < N_BATCHES; ++i) {
			threads.add( new MultithreadSum2(numbers, BATCH_SIZE*i, BATCH_SIZE) );
		}
		
		final long T0 = System.nanoTime();
		for(Thread t : threads) {
			t.start();
		}

		for(Thread t : threads) {
			t.join();
		}
		final long TF = System.nanoTime();

		System.out.println(String.format(
			"The sum took %d nanoseconds to compute.",
			TF - T0));
	}
	
	public static void main(String[] args) {
		List<Integer> numbers = readIntFile("AThousandNumbers.txt");

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
