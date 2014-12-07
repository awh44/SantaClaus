import java.util.Random;

public abstract class SantaHelper
{
	private static final Random random_ = new Random();

	protected int random_int(int min, int max)
	{
		return random_.nextInt(max - min) + 1;
	}
}
