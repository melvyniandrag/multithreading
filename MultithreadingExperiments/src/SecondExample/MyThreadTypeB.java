package SecondExample;

public class MyThreadTypeB extends Thread{
	private static int numThreads = 0;
	
	private int threadNum = 0;
	
	MyThreadTypeB(){
		numThreads++;
		threadNum = numThreads;	
	}
	
	public void run() {
		System.out.printf("[TYPE B] #%d\n", threadNum);
	}
	
}
