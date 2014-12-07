import java.util.Random;

public class Reindeer extends SantaHelper implements Runnable
{
	private static final int MAX_HOLIDAY_TIME = 10;
	private static int num_reindeer_ = 0;

	private int id_;
	private Santa santa_;
	private Sleigh sleigh_;

	public Reindeer(Santa santa, Sleigh sleigh)
	{
		santa_ = santa;
		sleigh_ = sleigh;
		id_ = num_reindeer_;
		num_reindeer_++;
	}

	@Override
	public void run()
	{
		try
		{
			while (true)
			{
				go_on_holiday();
				santa_.arrive(this);
				sleigh_.be_harnessed_and_fly();
			}
		}
		catch (InterruptedException e)
		{
			System.exit(1);
		}
	}

	private void go_on_holiday() throws InterruptedException
	{
		System.out.println("Reindeer " + id_ + " on holiday.");
		Thread.sleep(random_int(1, MAX_HOLIDAY_TIME));
	}
}
