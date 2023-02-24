import java.util.concurrent.atomic.AtomicBoolean;

public class MinotaurBirthday {
	public static final int guestCount = 100;
	public static void main(String[] args) {
		long timeStart = System.currentTimeMillis();
		int rand;
		GuestThread[] guests = new GuestThread[guestCount];
		for(int i = 0;i<guestCount;i++) {
			guests[i] = new GuestThread();
			System.out.println(guests[i].getId());
			System.out.println(guests[i].getName());
			guests[i].start();
		}
		while(GuestThread.stopFlag.get() == false) {
			rand = (int)(Math.random()*guestCount);
			guests[rand].labyrinth();
		}
		for(int i = 0;i<guestCount;i++)
		{
			try {
				guests[i].join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		long timeFinish = System.currentTimeMillis();
		//Check if everyone visited
		System.out.println("Checking results");
		for(int i = 0;i<guestCount;i++) {
			System.out.println("Guest "+i+": "+guests[i].visited);
		}
		System.out.println("Final time: "+(timeFinish-timeStart));
	}

}
class GuestThread extends Thread{
	public static AtomicBoolean cake = new AtomicBoolean(true);
	//public static boolean[] visited = new boolean[MinotaurBirthday.guestCount];
	public static int visitedCount = 0;
	public static AtomicBoolean stopFlag = new AtomicBoolean(false);
	int curInd = (int)(this.getId()-14);
	String curName = this.getName();
	boolean visited = false;
	public void run() {
		
	}
	public void labyrinth() {
		System.out.println(this.getName()+" is in the labyrinth");
		if(curInd == 0 || this.getName().equals("Thread-0"))
		{
			if(cake.get() == false)
			{
				cake.set(true);
				visitedCount++;
				if(visitedCount == MinotaurBirthday.guestCount)
				{
					stopFlag.set(true);
				}
			}
			if(visited == false) {
				visitedCount++;
				visited = true;
			}
		}
		else {
			if(cake.get() == true && visited == false) {
				cake.set(false);
				visited = true;
			}
		}
	}
}