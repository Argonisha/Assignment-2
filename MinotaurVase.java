import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class MinotaurVase {
	public static final int guestCount = 3;
	public static ConcurrentLinkedQueue<ViewerThread> queue = new ConcurrentLinkedQueue<ViewerThread>();
	public static void main(String[] args) {
		long timeStart = System.currentTimeMillis();
		int rand;
		boolean stopFlag = false;
		int visitChance = 5;
		ConcurrentLinkedQueue<ViewerThread> queue = new ConcurrentLinkedQueue<ViewerThread>();
		ViewerThread[] viewers = new ViewerThread[guestCount];
		MinotaurThread minotaur = new MinotaurThread(queue);
		for(int i = 0;i<guestCount;i++) {
			viewers[i] = new ViewerThread(queue);
			System.out.println(viewers[i].getId());
			System.out.println(viewers[i].getName());
			viewers[i].start();
		}
		minotaur.start();
		/*
		while(stopFlag == false) {
			for(int i = 0;i<guestCount;i++) {
				rand = (int)(Math.random()*100);
				if(rand > visitChance) {
					queue.add(guests[i]);
				}
			}
			
			
		}
		*/
		/*
		for(int i = 0;i<guestCount;i++)
		{
			try {
				viewers[i].join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		*/
		for(int i = 0;i<queue.size();i++)
		System.out.println(queue.poll());
		
	}
	

}
class MinotaurThread extends Thread{
	ConcurrentLinkedQueue<ViewerThread> queue;
	boolean runFlag;
	public MinotaurThread(ConcurrentLinkedQueue<ViewerThread> queue){
		this.queue = queue;
		runFlag = true;
	}
	
	public void run() {
		cycleViewers();
	}
	public void cycleViews() {
		while (!queue.isEmpty()) {
	       // message = dataQueue.remove();
	       // dataQueue.notifyAllForFull();
	       // useMessage(message);
	    }
	}
	public void cycleViewers() {
		ViewerThread curViewer;
		while(true)
		{
			curViewer = queue.poll();
			if(curViewer != null) {
			curViewer.leaveQueue();
			System.out.println(curViewer.getName()+" has left the showroom.");
			try {
				synchronized(this) {
					this.wait(100);
				}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			}
			
		}
	}
}
class ViewerThread extends Thread{
	AtomicInteger queuePos = new AtomicInteger(0);
	ConcurrentLinkedQueue<ViewerThread> queue;
	String curName = this.getName();
	int visitChance = 95;
	int rand;
	boolean inQueue = false;
	
	public ViewerThread(ConcurrentLinkedQueue<ViewerThread> queue) {
		this.queue = queue;
	}
	public void run() {
			visitVase();
	}
	public void visitVase() {
		while(visitChance > 0) {
			while(!inQueue) {
				rand = (int)(Math.random()*100);
				if(rand <= visitChance) {
					visitChance-=10;
					queue.add(this);
					inQueue = true;
					//System.out.println(curName+" has entered the queue at position: "+queuePos.getAndIncrement());
				}
				else {
					visitChance-=5;
				}
			}
		}
		System.out.println(curName+" is done trying.");
	}
	public void leaveQueue() {
		inQueue = false;
	}

}
