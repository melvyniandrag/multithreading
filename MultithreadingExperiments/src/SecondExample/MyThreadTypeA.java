package SecondExample;

public class MyThreadTypeA extends Thread{
	private static int numThreads = 0;
	
	private int threadNum = 0;
	
	MyThreadTypeA(){
		numThreads++;
		threadNum = numThreads;	
	}
	
	public void run() {
		System.out.printf("[TYPE A] #%d\n", threadNum);
	}
	
}
