/*
 * !!! Multithreading !!!
 * All programmers are expected to understand multi-
 * threading.
 * 
 * Make your computer do multiple things at once
 * Instead of using one thread of execution, 
 * make your computer use multiple threads.
 * 
 * This is good: More workers means things get done faster.
 * Your computer is very powerful; use all that power.
 */
package FirstExample;

public class YourFirstExample extends Thread{
	private static int numThreads = 0;
	
	private int threadNum = 0;
	
	YourFirstExample(){
		threadNum++;
	}
	
	public void run() {
		System.out.printf("Hello from thread %d", threadNum);
	}
	
	public static void main(String[] args) {
		Thread t0 = new YourFirstExample();
		Thread t1 = new YourFirstExample();
		Thread t2 = new YourFirstExample();
		Thread t3 = new YourFirstExample();
		
		t0.start();
		t1.start();
		t2.start();
		t3.start();
		
		try {
			t0.join();
			t1.join();
			t2.join();
			t3.join();
		}
		catch(InterruptedException ie) {
			System.err.println(ie.getLocalizedMessage());
		}
	}
}
