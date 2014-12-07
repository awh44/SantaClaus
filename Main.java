public class Main
{
	public static void main(String[] args)
	{
		Sleigh sleigh = new Sleigh();
		Santa santa = new Santa(sleigh);
		
		Thread santa_thread = new Thread(santa);
		
		Thread reindeer_threads[] = new Thread[9];
		for (int i = 0; i < 9; i++)
		{
			reindeer_threads[i] = new Thread(new Reindeer(santa, sleigh));
		}
		
		Thread elf_threads[] = new Thread[10];
		for (int i = 0; i < 10; i++)
		{
			elf_threads[i] = new Thread(new Elf(santa));
		}

		santa_thread.start();
		for (int i = 0; i < 9; i++)
		{
			reindeer_threads[i].start();
		}
		for (int i = 0; i < 10; i++)
		{
			elf_threads[i].start();
		}

		try
		{
			santa_thread.join();
			for (int i = 0; i < 9; i++)
			{
				reindeer_threads[i].start();
			}
			for (int i = 0; i < 10; i++)
			{
				elf_threads[i].start();
			}
		}
		catch (InterruptedException e)
		{
			System.exit(1);
		}

	}
}
