import java.util.ArrayList;

public class Santa implements Runnable
{
	private Sleigh sleigh_;
	private ArrayList<Reindeer> reindeer_ = new ArrayList<Reindeer>();
	private ArrayList<Elf> elves_ = new ArrayList<Elf>();

	private static final int NUM_REINDEER = 9;
	private static final int NUM_ELVES_IN_GROUP = 3;

	private static final int REINDEER = 0;
	private static final int ELVES = 1;

	public Santa(Sleigh sleigh)
	{
		sleigh_ = sleigh;
	}

	public synchronized void arrive(Reindeer deer)
	{
		reindeer_.add(deer);
		notify();
	}

	public synchronized void arrive(Elf elf)
	{
		elves_.add(elf);
		notify();
	}

	@Override
	public void run()
	{
		try
		{
			while (true)
			{	
				if (sleep() == REINDEER)
				{
					deliver_toys();	
				}
				else
				{
					consult_with_elves();
				}
			}
		}
		catch (InterruptedException e)
		{
			System.exit(1);
		}
	}

	private synchronized int sleep() throws InterruptedException
	{
		while ((reindeer_.size() < NUM_REINDEER) && (elves_.size() < NUM_ELVES_IN_GROUP))
		{
			wait();
			System.out.println("Santa woken up by someone. " + reindeer_.size() + " reindeer " +
			                   "waiting. " + elves_.size() + " elves waiting.");
		}

		return reindeer_.size() >= NUM_REINDEER ? REINDEER : ELVES;
	}

	private void deliver_toys() throws InterruptedException
	{
		sleigh_.harness_deer(NUM_REINDEER);
		sleigh_.fly();
		sleigh_.unharness_deer();
		reindeer_.clear();	
	}

	private void consult_with_elves() throws InterruptedException
	{
		System.out.println("Santa is consulting with the elves.");
		for (int i = 0; i < NUM_ELVES_IN_GROUP; i++)
		{
			Elf current_elf = remove_first_elf();
			current_elf.consult_with();
		}
		System.out.println("Santa is done consulting with the elves.");
	}

	private synchronized Elf remove_first_elf()
	{
		return elves_.remove(0);
	}
}
