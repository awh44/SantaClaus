public class Elf extends SantaHelper implements Runnable
{
	private static final int MAX_WORK_TIME = 10;
	private static final int MAX_CONSULT_TIME = 10;
	private static int num_elves_ = 0;

	private int id_;
	private Santa santa_;


	public Elf(Santa santa)
	{
		santa_ = santa;
		id_ = num_elves_;
		num_elves_++;
	}

	public synchronized void consult_with() throws InterruptedException
	{
		Thread.sleep(random_int(1, MAX_WORK_TIME));
		notify();
	}

	@Override
	public void run()
	{
		try
		{
			while (true)
			{
				work();
				consult_santa();
			}
		}
		catch (InterruptedException e)
		{
			System.exit(1);
		}
	}

	private void work() throws InterruptedException
	{
		System.out.println("Elf " + id_ + " working.");
		Thread.sleep(random_int(1, MAX_WORK_TIME));
	}

	private synchronized void consult_santa() throws InterruptedException
	{
		santa_.arrive(this);
		wait();
		System.out.println("Elf " + id_ + " done being consulted with.");
	}
}
