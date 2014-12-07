public class Sleigh
{
	private static final int FLY_TIME = 5;

	private int waiting_deer_ = 0;
	private boolean done_flying_ = false;

	public synchronized void be_harnessed_and_fly() throws InterruptedException
	{
		
		waiting_deer_++;
		System.out.println("New reindeer waiting to be harnessed. " + waiting_deer_ + " deer now" + 
		                   " waiting.");
		notifyAll();
		while (!done_flying_)
		{
			wait();
		}

		waiting_deer_--;
		if (waiting_deer_ == 0)
		{
			done_flying_ = false;
		}
	}

	public synchronized void harness_deer(int num_deer) throws InterruptedException
	{
		System.out.println("Santa is harnessing the deer.");
		while (waiting_deer_ < num_deer)
		{
			wait();
		}
		System.out.println("Santa is done harnessing the deer. Departing with " + waiting_deer_ + " deer.");
	}

	public synchronized void fly() throws InterruptedException
	{
		Thread.sleep(FLY_TIME);
		done_flying_ = true;
	}

	public synchronized void unharness_deer()
	{
		System.out.println("Santa is unharnessing the deer.");
		notifyAll();
	}
}
