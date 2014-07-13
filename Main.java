/**
 * The Class Main.
 */
public class Main
{
	
	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args)
	{
		Grid grid = new Grid(2,2);
		Agent agent = new Agent(grid, 0, 0);
		System.out.println("INITIAL STATE:");
		System.out.println(agent);
		agent.findSolution();
		System.out.println("FINAL STATE:");
		System.out.println(agent);
	}

}
